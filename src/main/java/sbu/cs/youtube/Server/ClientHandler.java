package sbu.cs.youtube.Server;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import sbu.cs.youtube.Server.Database.DatabaseManager;
import sbu.cs.youtube.Shared.POJO.*;
import sbu.cs.youtube.Shared.Request;
import sbu.cs.youtube.Shared.Response;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.UUID;

public class ClientHandler implements Runnable {

    //region [ - Fields - ]
    private final Socket client;
    private BufferedReader bufferedReader;
    private DatabaseManager databaseManager;
    private String request;
    private final Gson gson;
    //endregion

    //region [ - Constructor - ]
    public ClientHandler(Socket client) {
        this.gson = new Gson();
        this.client = client;
        try {
            this.bufferedReader = new BufferedReader(new InputStreamReader((client.getInputStream())));
            this.databaseManager = new DatabaseManager();
        } catch (IOException ioe) {
            System.out.println("!!Exception : " + ioe.getMessage());
        }
    }
    //endregion

    //region [ - Methods - ]

    //region [ - run() - ]
    @Override
    public void run() {
        try {
            try {
                while (!client.isClosed()) {
                    receiveRequest();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    bufferedReader.close();
                    client.close();
                } catch (IOException ioe) {
                    System.out.println("!!Exception : " + ioe.getMessage());
                }
            }
        } finally {
            System.out.println("Client with IP :  " + client.getRemoteSocketAddress() + " disconnected");
        }
    }
    //endregion

    //region [ - receiveRequest() - ]
    public void receiveRequest() {
        try {
            String request = bufferedReader.readLine();
            handleRequest(request);
        } catch (IOException ioe) {
            try {
                if (client != null) {
                    client.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                System.out.println("!!Exception : " + e.getMessage());
            }
        }
    }
    //endregion

    //region [ - handleRequest(String request) - ]
    public void handleRequest(String request) {
        this.request = request;
        TypeToken<Request<Object>> responseTypeToken = new TypeToken<>() {
        };
        Request<Object> objectRequest = gson.fromJson(request, responseTypeToken.getType());
        switch (objectRequest.getType()) {
            case "CheckExistingUser":
                CheckExistingUser();
                break;
            case "SignUp":
                signUp();
                break;
            case "SignIn":
                signIn();
                break;
            case "GetRecommendedVideos":
                GetRecommendedVideos();
                break;
            case "CheckSubscriptionExistence":
                checkSubscriptionExistence();
                break;
            case "Subscribe":
                subscribe();
                break;
            case "Unsubscribe":
                unsubscribe();
                break;
            case "CheckViewVideoExistence":
                checkViewVideoExistence();
                break;
            case "LikeVideo":
                likeVideo();
                break;
            case "DislikeVideo":
                dislikeVideo();
                break;
            case "LikeComment":
                likeComment();
                break;
            case "DislikeComment":
                dislikeComment();
                break;
            case "Comment":
                comment();
                break;
            case "GetVideoComments":
                getVideoComments();
                break;
            case "GetVideo":
                getVideo();
                break;
            case "GetVideoLikesStatus":
                getVideoLikesStatus();
                break;
            case "GetCommentLikesStatus":
                getCommentLikesStatus();
                break;
            case "GetChannelVideos":
                getChannelVideos();
                break;
            case "GetChannel":
                getChannel();
                break;
            case "GetPlaylist":
                GetPlaylist();
                break;
            case "GetUserPlaylists":
                getUserPlaylists();
                break;
            case "AddVideoToPlaylist":
                addVideoToPlaylist();
                break;
            case "GetLikedVideos":
                getLikedVideos();
                break;
            case "GetHistory":
                getHistory();
                break;
            case "GetUserVideos":
                getUserVideos();
                break;
            case "GetVideoViewCount":
                getVideoViewCount();
                break;
            case "CreateVideo":
                createVideo();
                break;
            case "GetCategories":
                getCategories();
                break;
            case "GetCategoryVideos":
                getCategoryVideos();
                break;
            case "GetVideoCategories":
                getVideoCategories();
                break;
            case "ChangeUserInfo":
                changeUserInfo();
                break;
            case "CreatePlaylist":
                createPlaylist();
                break;
            case "SearchVideo":
                searchVideo();
                break;
            case "SearchChannel":
                searchChannel();
                break;
            case "SearchPlaylist":
                searchPlaylist();
                break;
            case "GetUserChannel":
                getUserChannel();
                break;
        }
    }
    //endregion

    //region [ - signUp() - ]
    private void signUp() {
        TypeToken<Request<User>> responseTypeToken = new TypeToken<>() {
        };
        Request<User> userRequest = gson.fromJson(request, responseTypeToken.getType());
        Response<User> response;

        User user = userRequest.getBody();
        databaseManager.insertUser(user);

        response = new Response<>(client, userRequest.getType(), true, "Signed up successfully");
        response.send(user);
    }
    //endregion

    //region [ - signIn() - ]
    private void signIn() {
        TypeToken<Request<User>> responseTypeToken = new TypeToken<>() {
        };
        Request<User> userRequest = gson.fromJson(request, responseTypeToken.getType());
        Response<User> response;


        User requestedUser = userRequest.getBody();
        User user;
        if (requestedUser.getEmail().isEmpty()) {
            user = databaseManager.selectUserByUsername(requestedUser.getUsername());
        } else {
            user = databaseManager.selectUserByEmail(requestedUser.getEmail());
        }

        if (user != null) {
            if (requestedUser.getPassword().equals(user.getPassword())) {
                response = new Response<>(client, userRequest.getType(), true, "Signed in successfully");
            } else {
                response = new Response<>(client, userRequest.getType(), true, "Password is incorrect");
                user = null;
            }
        } else {
            response = new Response<>(client, userRequest.getType(), true, "User not found");
        }
        response.send(user);
    }
    //endregion

    //region [ - CheckExistingUser() - ]
    private void CheckExistingUser() {
        TypeToken<Request<User>> responseTypeToken = new TypeToken<>() {
        };
        Request<User> userRequest = gson.fromJson(request, responseTypeToken.getType());
        Response<User> response;

        User requestedUser = userRequest.getBody();
        User user;
        if (requestedUser.getEmail().isEmpty()) {
            user = databaseManager.selectUserByUsername(requestedUser.getUsername());
        } else {
            user = databaseManager.selectUserByEmail(requestedUser.getEmail());
        }

        if (user != null) {
            response = new Response<>(client, userRequest.getType(), true, "There is already a user with this email");
        } else {
            response = new Response<>(client, userRequest.getType(), true, "User not found");
        }

        response.send(user);
    }
    //endregion

    //region [ - GetRecommendedVideos() - ]
    private void GetRecommendedVideos() {
        TypeToken<Request<ArrayList<Video>>> responseTypeToken = new TypeToken<>() {
        };
        Request<ArrayList<Video>> videosRequest = gson.fromJson(request, responseTypeToken.getType());
        Response<ArrayList<Video>> response;

        ArrayList<Video> videos = databaseManager.selectVideosBriefly();

        response = new Response<>(client, videosRequest.getType(), true, "Signed up successfully");
        response.send(videos);
    }
    //endregion

    //region [ - getSubscription() - ]
    public void checkSubscriptionExistence() {
        TypeToken<Request<Subscription>> responseTypeToken = new TypeToken<>() {
        };
        Request<Subscription> subscriptionRequest = gson.fromJson(request, responseTypeToken.getType());
        Subscription requestedSubscription = subscriptionRequest.getBody();

        Subscription subscription = databaseManager.subscriptionExistence(requestedSubscription.getSubscriberId(), requestedSubscription.getChannelId());

        Response<Subscription> response;
        response = new Response<>(client, subscriptionRequest.getType(), true, "Subscription checked");
        response.send(subscription);
    }
    //endregion

    //region [ - subscribe() - ]
    public void subscribe() {
        TypeToken<Request<Subscription>> responseTypeToken = new TypeToken<>() {
        };
        Request<Subscription> subscriptionRequest = gson.fromJson(request, responseTypeToken.getType());
        Subscription subscription = subscriptionRequest.getBody();

        databaseManager.insertSubscription(subscription);

        Response<Subscription> response;
        response = new Response<>(client, subscriptionRequest.getType(), true, "Channel subscribed");
        response.send();
    }
    //endregion

    //region [ - unsubscribe() - ]
    public void unsubscribe() {
        TypeToken<Request<Subscription>> responseTypeToken = new TypeToken<>() {
        };
        Request<Subscription> subscriptionRequest = gson.fromJson(request, responseTypeToken.getType());
        Subscription subscription = subscriptionRequest.getBody();

        databaseManager.deleteSubscription(subscription.getSubscriberId(), subscription.getChannelId());

        Response<Subscription> response;
        response = new Response<>(client, subscriptionRequest.getType(), true, "Channel unsubscribed");
        response.send();
    }
    //endregion

    //region [ - checkViewVideoExistence() - ]
    private void checkViewVideoExistence() {
        TypeToken<Request<UserVideo>> responseTypeToken = new TypeToken<>() {
        };
        Request<UserVideo> userVideoRequest = gson.fromJson(request, responseTypeToken.getType());
        UserVideo requestedUserVideo = userVideoRequest.getBody();

        Response<UserVideo> response;
        UserVideo userVideo = databaseManager.selectUserVideo(requestedUserVideo.getUserId(), requestedUserVideo.getVideoId());
        if (userVideo != null) {
            response = new Response<>(client, userVideoRequest.getType(), true, "Video has already viewed");
        } else {
            databaseManager.insertUserVideo(requestedUserVideo);
            response = new Response<>(client, userVideoRequest.getType(), true, "Video has just viewed");
        }
        response.send(userVideo);
    }
    //endregion

    //region [ - likeVideo() - ]
    private void likeVideo() {
        TypeToken<Request<UserVideo>> responseTypeToken = new TypeToken<>() {
        };
        Request<UserVideo> userVideoRequest = gson.fromJson(request, responseTypeToken.getType());
        UserVideo requestedUserVideo = userVideoRequest.getBody();

        Response<UserVideo> response;
        UserVideo userVideo = databaseManager.selectUserVideo(requestedUserVideo.getUserId(), requestedUserVideo.getVideoId());

        if (userVideo.getLike() == null || !userVideo.getLike()) {
            requestedUserVideo.setLike(true);
            response = new Response<>(client, userVideoRequest.getType(), true, "Video liked");
        } else {
            requestedUserVideo.setLike(null);
            response = new Response<>(client, userVideoRequest.getType(), true, "Video unliked");
        }
        databaseManager.updateUserVideo(requestedUserVideo);

        response.send();
    }
    //endregion

    //region [ - dislikeVideo() - ]
    private void dislikeVideo() {
        TypeToken<Request<UserVideo>> responseTypeToken = new TypeToken<>() {
        };
        Request<UserVideo> userVideoRequest = gson.fromJson(request, responseTypeToken.getType());
        UserVideo requestedUserVideo = userVideoRequest.getBody();

        Response<UserVideo> response;
        UserVideo userVideo = databaseManager.selectUserVideo(requestedUserVideo.getUserId(), requestedUserVideo.getVideoId());

        if (userVideo.getLike() == null || userVideo.getLike()) {
            requestedUserVideo.setLike(false);
            response = new Response<>(client, userVideoRequest.getType(), true, "Video disliked");
        } else {
            requestedUserVideo.setLike(null);
            response = new Response<>(client, userVideoRequest.getType(), true, "Video undisliked");
        }
        databaseManager.updateUserVideo(requestedUserVideo);

        response.send();
    }
    //endregion

    //region [ - likeComment() - ]
    private void likeComment() {
        TypeToken<Request<UserComment>> responseTypeToken = new TypeToken<>() {
        };
        Request<UserComment> userCommentRequest = gson.fromJson(request, responseTypeToken.getType());
        UserComment requestedUserComment = userCommentRequest.getBody();

        Response<UserComment> response;
        UserComment userComment = databaseManager.selectUserComment(requestedUserComment.getUserId(), requestedUserComment.getCommentId());

        if (userComment == null) {
            requestedUserComment.setLike(true);
            databaseManager.insertUserComment(requestedUserComment);
            response = new Response<>(client, userCommentRequest.getType(), true, "Comment liked");
        } else if (userComment.getLike()) {
            databaseManager.deleteUserComment(userComment.getUserId(), userComment.getCommentId());
            response = new Response<>(client, userCommentRequest.getType(), true, "Comment unliked");
        } else {
            requestedUserComment.setLike(true);
            response = new Response<>(client, userCommentRequest.getType(), true, "Comment liked");
            databaseManager.updateUserComment(requestedUserComment);
        }

        response.send();
    }
    //endregion

    //region [ - dislikeComment() - ]
    private void dislikeComment() {
        TypeToken<Request<UserComment>> responseTypeToken = new TypeToken<>() {
        };
        Request<UserComment> userCommentRequest = gson.fromJson(request, responseTypeToken.getType());
        UserComment requestedUserComment = userCommentRequest.getBody();

        Response<UserComment> response;
        UserComment userComment = databaseManager.selectUserComment(requestedUserComment.getUserId(), requestedUserComment.getCommentId());

        if (userComment == null) {
            requestedUserComment.setLike(false);
            databaseManager.insertUserComment(requestedUserComment);
            response = new Response<>(client, userCommentRequest.getType(), true, "Comment disliked");
        } else if (!userComment.getLike()) {
            databaseManager.deleteUserComment(userComment.getUserId(), userComment.getCommentId());
            response = new Response<>(client, userCommentRequest.getType(), true, "Comment undisliked");
        } else {
            requestedUserComment.setLike(false);
            response = new Response<>(client, userCommentRequest.getType(), true, "Comment disliked");
            databaseManager.updateUserComment(requestedUserComment);
        }

        response.send();
    }
    //endregion

    //region [ - getVideoComments() - ]
    private void getVideoComments() {
        TypeToken<Request<Video>> responseTypeToken = new TypeToken<>() {
        };
        Request<Video> videosRequest = gson.fromJson(request, responseTypeToken.getType());
        Video video = videosRequest.getBody();

        ArrayList<Comment> comments = databaseManager.selectComments(video.getId());

        Response<ArrayList<Comment>> response;
        response = new Response<>(client, videosRequest.getType(), true, "Video Comments fetched");
        response.send(comments);
    }
    //endregion

    //region [ - comment() - ]
    private void comment() {
        TypeToken<Request<Comment>> responseTypeToken = new TypeToken<>() {
        };
        Request<Comment> commentRequest = gson.fromJson(request, responseTypeToken.getType());
        Comment comment = commentRequest.getBody();

        databaseManager.insertComment(comment);
        Response<Comment> response = new Response<>(client, commentRequest.getType(), true, "Comment posted");
        response.send();
    }
    //endregion

    //region [ - getVideo() - ]
    private void getVideo() {
        TypeToken<Request<Video>> responseTypeToken = new TypeToken<>() {
        };
        Request<Video> videoRequest = gson.fromJson(request, responseTypeToken.getType());
        Response<Video> response;


        Video requestedVideo = videoRequest.getBody();
        Video video;

        video = databaseManager.selectVideo(requestedVideo.getId());

        if (video != null) {
            response = new Response<>(client, videoRequest.getType(), true, "video received successfully");
        } else {
            response = new Response<>(client, videoRequest.getType(), true, "video not found");
        }
        response.send(video);
    }
    //endregion

    //region [ - getVideoLikesStatus() - ]
    private void getVideoLikesStatus() {
        TypeToken<Request<Video>> responseTypeToken = new TypeToken<>() {
        };
        Request<Video> videoRequest = gson.fromJson(request, responseTypeToken.getType());
        Video requestedVideo = videoRequest.getBody();

        Video videoLikesStatus = databaseManager.selectVideoLikesStatus(requestedVideo.getId());

        Response<Video> response;
        response = new Response<>(client, videoRequest.getType(), true, "Video likes status fetched");
        response.send(videoLikesStatus);
    }
    //endregion

    //region [ - getCommentLikesStatus() - ]
    private void getCommentLikesStatus() {
        TypeToken<Request<Comment>> responseTypeToken = new TypeToken<>() {
        };
        Request<Comment> commentRequest = gson.fromJson(request, responseTypeToken.getType());
        Comment requestedComment = commentRequest.getBody();

        Comment commentLikesStatus = databaseManager.selectCommentLikesStatus(requestedComment.getId());

        Response<Comment> response;
        response = new Response<>(client, commentRequest.getType(), true, "Comment likes status fetched");
        response.send(commentLikesStatus);
    }
    //endregion

    //region [ - getChannelVideos() - ]

    private void getChannelVideos() {
        TypeToken<Request<Channel>> responseTypeToken = new TypeToken<>() {
        };
        Request<Channel> channelRequest = gson.fromJson(request, responseTypeToken.getType());
        Response<ArrayList<Video>> response;

        Channel channel = channelRequest.getBody();
        ArrayList<Video> videos = databaseManager.selectVideosByChannel(channel.getId());

        response = new Response<>(client, channelRequest.getType(), true, "ChannelVideos Received Successfully");
        response.send(videos);
    }
    //endregion

    //region [ - getChannel() - ]
    private void getChannel() {
        TypeToken<Request<Channel>> responseTypeToken = new TypeToken<>() {
        };
        Request<Channel> channelRequest = gson.fromJson(request, responseTypeToken.getType());
        Response<Channel> response;

        Channel requestedChannel = channelRequest.getBody();
        Channel channel;

        channel = databaseManager.selectChannel(requestedChannel.getId());

        if (channel != null) {
            response = new Response<>(client, channelRequest.getType(), true, "channel received successfully");
        } else {
            response = new Response<>(client, channelRequest.getType(), true, "channel not found");
        }
        response.send(channel);
    }
    //endregion

    //region [ - GetPlaylist() - ]
    private void GetPlaylist() {
        TypeToken<Request<Playlist>> responseTypeToken = new TypeToken<>() {
        };
        Request<Playlist> playlistRequest = gson.fromJson(request, responseTypeToken.getType());
        Response<Playlist> response;


        Playlist requestedPlaylist = playlistRequest.getBody();
        Playlist playlist;

        playlist = databaseManager.selectPlaylist(requestedPlaylist.getId());

        response = new Response<>(client, playlistRequest.getType(), true, "Playlist received successfully");

        response.send(playlist);
    }
    //endregion

    //region [ - getUserPlaylists() - ]
    private void getUserPlaylists() {
        TypeToken<Request<User>> responseTypeToken = new TypeToken<>() {
        };
        Request<User> userRequest = gson.fromJson(request, responseTypeToken.getType());
        Response<ArrayList<Playlist>> response;

        User user = userRequest.getBody();
        ArrayList<Playlist> playlists = databaseManager.selectPlaylistsBrieflyByUser(user.getId());

        response = new Response<>(client, userRequest.getType(), true, "userPlaylists received successfully");
        response.send(playlists);
    }
    //endregion

    //region [ - addVideoToPlaylist() - ]
    public void addVideoToPlaylist() {
        TypeToken<Request<PlaylistDetail>> responseTypeToken = new TypeToken<>() {
        };
        Request<PlaylistDetail> playlistDetailRequest = gson.fromJson(request, responseTypeToken.getType());
        PlaylistDetail playlistDetail = playlistDetailRequest.getBody();

        databaseManager.insertPlaylistDetail(playlistDetail);

        Response<PlaylistDetail> response;
        response = new Response<>(client, playlistDetailRequest.getType(), true, "Video Added");
        response.send();
    }
    //endregion

    //region [ - getLikedVideos() - ]

    private void getLikedVideos() {
        TypeToken<Request<User>> responseTypeToken = new TypeToken<>() {
        };
        Request<User> userRequest = gson.fromJson(request, responseTypeToken.getType());
        Response<ArrayList<Video>> response;

        User user = userRequest.getBody();
        ArrayList<Video> videos = databaseManager.selectLikedVideos(user.getId());

        response = new Response<>(client, userRequest.getType(), true, "LikedVideos Received Successfully");
        response.send(videos);
    }
    //endregion

    //region [ - getHistory() - ]
    private void getHistory() {
        TypeToken<Request<User>> responseTypeToken = new TypeToken<>() {
        };
        Request<User> userRequest = gson.fromJson(request, responseTypeToken.getType());
        Response<ArrayList<Video>> response;

        User user = userRequest.getBody();
        ArrayList<Video> videos = databaseManager.selectHistory(user.getId());

        response = new Response<>(client, userRequest.getType(), true, "History Received Successfully");
        response.send(videos);
    }
    //endregion

    //region [ - getUserVideos() - ]
    private void getUserVideos() {
        TypeToken<Request<User>> responseTypeToken = new TypeToken<>() {
        };
        Request<User> userRequest = gson.fromJson(request, responseTypeToken.getType());
        Response<ArrayList<Video>> response;

        User user = userRequest.getBody();
        ArrayList<Video> videos = databaseManager.selectVideosByCreator(user.getId());

        response = new Response<>(client, userRequest.getType(), true, "UserVideos Received Successfully");
        response.send(videos);
    }
    //endregion

    //region [ - getVideoViewCount() - ]
    private void getVideoViewCount() {
        TypeToken<Request<Video>> responseTypeToken = new TypeToken<>() {
        };
        Request<Video> videoRequest = gson.fromJson(request, responseTypeToken.getType());
        Video requestedVideo = videoRequest.getBody();

        Video videoViewCount = databaseManager.selectVideoViewCount(requestedVideo.getId());

        Response<Video> response;
        response = new Response<>(client, videoRequest.getType(), true, "Video View Count fetched");
        response.send(videoViewCount);
    }
    //endregion

    //region [ - createVideo() - ]
    private void createVideo() {
        TypeToken<Request<Video>> responseTypeToken = new TypeToken<>() {
        };
        Request<Video> videoRequest = gson.fromJson(request, responseTypeToken.getType());
        Response<Video> response;

        Video video = videoRequest.getBody();
        String videoPath = "/Videos/" + video.getFileName();
        try (FileOutputStream fos = new FileOutputStream("src/main/resources" + videoPath)) {
            video.setPath(videoPath);
            fos.write(video.getVideoBytes());
            System.out.println("Video saved successfully to " + videoPath);
        } catch (IOException e) {
            System.err.println("Error while saving the video: " + e.getMessage());
        }

        String thumbnailPath = "/Images/" + video.getFileName().substring(0 , video.getFileName().length()-3) + "jpg" ;
        try (FileOutputStream fos = new FileOutputStream("src/main/resources" + thumbnailPath)) {
            video.setThumbnailPath(thumbnailPath);
            fos.write(video.getThumbnailBytes());
            System.out.println("Video saved successfully to " + thumbnailPath);
        } catch (IOException e) {
            System.err.println("Error while saving the video: " + e.getMessage());
        }

        databaseManager.insertVideo(video);
        response = new Response<>(client, videoRequest.getType(), true, "Video created successfully");
        response.send();
    }
    //endregion

    //region [ - getCategories() - ]
    private void getCategories() {
        TypeToken<Request<ArrayList<Category>>> responseTypeToken = new TypeToken<>() {
        };
        Request<ArrayList<Category>> categoriesRequest = gson.fromJson(request, responseTypeToken.getType());
        Response<ArrayList<Category>> response;

        ArrayList<Category> categories = databaseManager.selectCategories();

        response = new Response<>(client, categoriesRequest.getType(), true, "categories received successfully");
        response.send(categories);
    }
    //endregion

    //region [ - getCategoryVideos() - ]
    private void getCategoryVideos() {
        TypeToken<Request<Category>> responseTypeToken = new TypeToken<>() {
        };
        Request<Category> categoryRequest = gson.fromJson(request, responseTypeToken.getType());
        Response<ArrayList<Video>> response;

        Category category = categoryRequest.getBody();
        ArrayList<Video> videos = databaseManager.selectVideosByCategory(category.getId());

        response = new Response<>(client, categoryRequest.getType(), true, "CategoryVideos Received Successfully");
        response.send(videos);
    }
    //endregion

    //region [ - getVideoCategories() - ]
    private void getVideoCategories() {
        TypeToken<Request<Video>> responseTypeToken = new TypeToken<>() {
        };
        Request<Video> videoRequest = gson.fromJson(request, responseTypeToken.getType());
        Response<ArrayList<Category>> response;

        Video video = videoRequest.getBody();
        ArrayList<Category> videos = databaseManager.selectCategoriesByVideo(video.getId());

        response = new Response<>(client, videoRequest.getType(), true, "VideoVideos Received Successfully");
        response.send(videos);
    }
    //endregion

    //region [ - changeUserInfo() - ]
    public void changeUserInfo() {
        TypeToken<Request<User>> responseTypeToken = new TypeToken<>() {
        };
        Request<User> userRequest = gson.fromJson(request, responseTypeToken.getType());
        User user = userRequest.getBody();

        databaseManager.updateUser(user);

        Response<User> response;
        response = new Response<>(client, userRequest.getType(), true, "User Info Changed ");
        response.send();
    }
    //endregion

    //region [ - creatPlaylist() - ]
    private void createPlaylist() {
        TypeToken<Request<Playlist>> responseTypeToken = new TypeToken<>() {
        };
        Request<Playlist> playlistRequest = gson.fromJson(request, responseTypeToken.getType());
        Response<Playlist> response;

        Playlist playlist = playlistRequest.getBody();

        databaseManager.insertPlaylist(playlist);
        response = new Response<>(client, playlistRequest.getType(), true, "Playlist created successfully");
        response.send();
    }
    //endregion

    //region [ - searchVideo() - ]

    private void searchVideo() {
        TypeToken<Request<Video>> responseTypeToken = new TypeToken<>() {
        };
        Request<Video> videoRequest = gson.fromJson(request, responseTypeToken.getType());
        Response<ArrayList<Video>> response;

        Video video = videoRequest.getBody();
        ArrayList<Video> videos = databaseManager.selectVideosByTitle(video.getTitle());

        response = new Response<>(client, videoRequest.getType(), true, "Search For Video Successfully");
        response.send(videos);
    }
    //endregion

    //region [ - searchChannel() - ]
    private void searchChannel() {
        TypeToken<Request<Channel>> responseTypeToken = new TypeToken<>() {
        };
        Request<Channel> channelRequest = gson.fromJson(request, responseTypeToken.getType());
        Response<ArrayList<Channel>> response;

        Channel channel = channelRequest.getBody();
        ArrayList<Channel> channels = databaseManager.selectChannelByTitle(channel.getTitle());

        response = new Response<>(client, channelRequest.getType(), true, "Search For Channel Successfully");
        response.send(channels);
    }
    //endregion

    //region [ - searchChannel() - ]

    private void searchPlaylist() {
        TypeToken<Request<Playlist>> responseTypeToken = new TypeToken<>() {
        };
        Request<Playlist> playlistRequest = gson.fromJson(request, responseTypeToken.getType());
        Response<ArrayList<Playlist>> response;

        Playlist playlist = playlistRequest.getBody();
        ArrayList<Playlist> playlists = databaseManager.selectPlaylistsByTitle(playlist.getTitle());

        response = new Response<>(client, playlistRequest.getType(), true, "Search For Playlist Successfully");
        response.send(playlists);
    }
    //endregion

    //region [ - getUserChannel() - ]
    private void getUserChannel() {
        TypeToken<Request<User>> responseTypeToken = new TypeToken<>() {
        };
        Request<User> userRequest = gson.fromJson(request, responseTypeToken.getType());
        Response<Channel> response;

        User requestedUser = userRequest.getBody();
        Channel channel;

        channel = databaseManager.selectChannelByUser(requestedUser.getId());

        if (channel != null) {
            response = new Response<>(client, userRequest.getType(), true, "channel received successfully");
        } else {
            response = new Response<>(client, userRequest.getType(), true, "channel not found");
        }
        response.send(channel);
    }
    //endregion

    //endregion

}

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
            case "GetVideo" :
                getVideo();
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
        }
        else {
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
        } else{
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


    //region [ - getPlaylist - ]
    private void getVideo() {
        TypeToken<Request<Video>> responseTypeToken = new TypeToken<>() {
        };
        Request<Video> userRequest = gson.fromJson(request, responseTypeToken.getType());
        Response<Video> response;


        Video requestedVideo = userRequest.getBody();
        Video video;

        video = databaseManager.selectVideo(requestedVideo.getId());

        if (video != null) {
            response = new Response<>(client, userRequest.getType(), true, "video received successfully");
        } else {
            response = new Response<>(client, userRequest.getType(), true, "video not found");
        }
        response.send(video);
    }
    //endregion

    //endregion

}

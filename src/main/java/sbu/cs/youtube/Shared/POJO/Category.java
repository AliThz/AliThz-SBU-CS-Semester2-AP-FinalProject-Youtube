package sbu.cs.youtube.Shared.POJO;

import java.util.UUID;

public class Category {
    private UUID Id ;
    private String title ;

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

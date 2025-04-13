package models;

import java.sql.Timestamp;

public class Story {
    private int storyId;
    private String title;
    private int authorID;
    private int genreID;
    private String description;
    private String status;
    java.sql.Timestamp createdAt;
    private String image;
    private String authorName;
    private String genreName;

    public Story() {
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }
    
    public Story(int storyId, String title, String description,int authorID,  int genreID,String status,java.sql.Timestamp createdAt,String image) {
        this.storyId = storyId;
        this.title = title;
        this.authorID = authorID;
        this.description = description;
        this.status = status;
        this.genreID = genreID;
        this.createdAt = createdAt;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    
    
    

    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    public int getGenreID() {
        return genreID;
    }

    public void setGenreID(int genreID) {
        this.genreID = genreID;
    }
    
    
    public int getStoryId() {
        return storyId;
    }

    public void setStoryId(int storyId) {
        this.storyId = storyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Story{" + "storyId=" + storyId + ", title=" + title + ", authorID=" + authorID + ", genreID=" + genreID + ", description=" + description + ", status=" + status + ", createdAt=" + createdAt + ", image=" + image + ", authorName=" + authorName + ", genreName=" + genreName + '}';
    }

    
}
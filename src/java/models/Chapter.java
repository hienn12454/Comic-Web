/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Timestamp;

public class Chapter {
    private int chapterId;
    private int storyId;
    private int chapterNumber;
    private String title;
    private java.sql.Timestamp createdAt;

    public Chapter(int chapterId, int storyId, int chapterNumber, String title, Timestamp createdAt) {
        this.chapterId = chapterId;
        this.storyId = storyId;
        this.chapterNumber = chapterNumber;
        this.title = title;
        this.createdAt = createdAt;
    }

    public Chapter() {
    }  
    
    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public int getStoryId() {
        return storyId;
    }

    public void setStoryId(int storyId) {
        this.storyId = storyId;
    }

    public int getChapterNumber() {
        return chapterNumber;
    }

    public void setChapterNumber(int chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Chapter{" + "chapterId=" + chapterId + ", storyId=" + storyId + ", chapterNumber=" + chapterNumber + ", title=" + title + ", createdAt=" + createdAt + '}';
    }
    
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Timestamp;

/**
 *
 * @author SHD
 */
public class ReadingHistory {
     private int historyId;
    private int userId;
    private int storyId;
    private int chapterId;
    private java.sql.Timestamp readAt;

    public ReadingHistory(int historyId, int userId, int storyId, int chapterId, Timestamp readAt) {
        this.historyId = historyId;
        this.userId = userId;
        this.storyId = storyId;
        this.chapterId = chapterId;
        this.readAt = readAt;
    }

    public int getHistoryId() {
        return historyId;
    }

    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStoryId() {
        return storyId;
    }

    public void setStoryId(int storyId) {
        this.storyId = storyId;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public Timestamp getReadAt() {
        return readAt;
    }

    public void setReadAt(Timestamp readAt) {
        this.readAt = readAt;
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author SHD
 */
public class ChapterImage {
     private int imageId;
    private int chapterId;
    private String imageUrl;
    private int orderNumber;

    public ChapterImage(int imageId, int chapterId, String imageUrl, int orderNumber) {
        this.imageId = imageId;
        this.chapterId = chapterId;
        this.imageUrl = imageUrl;
        this.orderNumber = orderNumber;
    }

    public ChapterImage() {
    }
    
    

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public String toString() {
        return "ChapterImage{" + "imageId=" + imageId + ", chapterId=" + chapterId + ", imageUrl=" + imageUrl + ", orderNumber=" + orderNumber + '}';
    }
    
    
    
}

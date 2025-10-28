/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Author;
import models.Chapter;
import models.ChapterImage;
import models.Genre;
import models.Story;

/**
 *
 * @author SHD
 */
public class StoryDAO extends DBContext {

    public List<Story> getAllStories() {
        List<Story> stories = new ArrayList<>();
        String sql = "SELECT * FROM Stories";
        try ( PreparedStatement stm = connection.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int storyId = rs.getInt("StoryId");
                String title = rs.getString("Title");
                int authorId = rs.getInt("AuthorId");
                int genreId = rs.getInt("GenreId");
                String description = rs.getString("Description");
                String status = rs.getString("Status");
                java.sql.Timestamp createdAt = rs.getTimestamp("CreatedAt");
                String image = rs.getString("Image");
                Story story = new Story(storyId, title, description, authorId, genreId, status, createdAt, image);
                stories.add(story);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return stories;
    }

    public boolean addStory(Story story) {
        String sql = "INSERT INTO Stories (Title, AuthorId, GenreId, Description, Status, CreatedAt, Image) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try ( PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, story.getTitle());
            stm.setInt(2, story.getAuthorID());
            stm.setInt(3, story.getGenreID());
            stm.setString(4, story.getDescription());
            stm.setString(5, story.getStatus());
            stm.setTimestamp(6, story.getCreatedAt());
            stm.setString(7, story.getImage());
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean addChapter(Chapter chapter) {
        String sql = "INSERT INTO Chapters (StoryId, ChapterNumber, title, CreatedAt) "
                + "VALUES (?, ?, ?, ?)";
        try ( PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, chapter.getStoryId());
            stm.setInt(2, chapter.getChapterNumber());
            stm.setString(3, chapter.getTitle());
            stm.setTimestamp(4, chapter.getCreatedAt());
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean addChapterImage(ChapterImage chapterimage) {
        String sql = "INSERT INTO ChapterImages (ChapterId, ImageUrl, OrderNumber) "
                + "VALUES (?, ?, ?)";
        try ( PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, chapterimage.getChapterId());
            stm.setString(2, chapterimage.getImageUrl());
            stm.setInt(3, chapterimage.getOrderNumber());
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean updateStory(Story story) {
        String sql = "UPDATE Stories SET Title = ?, AuthorId = ?, GenreId = ?, Description = ?, "
                + "Status = ?, Image = ? WHERE StoryId = ?";
        try ( PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, story.getTitle());
            stm.setInt(2, story.getAuthorID());
            stm.setInt(3, story.getGenreID());
            stm.setString(4, story.getDescription());
            stm.setString(5, story.getStatus());
            stm.setString(6, story.getImage());
            stm.setInt(7, story.getStoryId());
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean deleteStory(int storyId) {
        List<Chapter> chapters = getListChapterByStoryId(storyId);
        for (Chapter c : chapters) {
            deleteChapterImagesByChapterId(c.getChapterId());
        }
        deleteChapterByStoryId(storyId);
        deleteCommentsByStoryId(storyId);
        deleteReadingHistoryByStoryId(storyId);
        deleteFavoritesByStoryId(storyId);
        String sql = "DELETE FROM Stories WHERE StoryId = ?";
        try ( PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, storyId);
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Story getStoryById(int storyId) {
        String sql = "SELECT s.StoryId, s.Title, s.Description, s.AuthorId, s.GenreId, s.Status, s.CreatedAt,s.Image, "
                + "a.AuthorName, g.GenreName "
                + "FROM Stories s "
                + "INNER JOIN Authors a ON s.AuthorId = a.AuthorId "
                + "INNER JOIN Genres g ON s.GenreId = g.GenreId "
                + "WHERE s.StoryId = ?";
        try ( PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, storyId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("StoryId");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                int authorId = rs.getInt("AuthorId");
                int genreId = rs.getInt("GenreId");
                String status = rs.getString("Status");
                java.sql.Timestamp createdAt = rs.getTimestamp("CreatedAt");
                String Image = rs.getString("Image");

                return new Story(id, title, description, authorId, genreId, status, createdAt, Image);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String getAuthor(int authorID) {
        String sql = "select AuthorName from Authors where AuthorId = ?";
        try ( PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, authorID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String getGenre(int genID) {
        String sql = "select GenreName from Genres where GenreId = ?";
        try ( PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, genID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Story> getCompletedStoriesWithImages() {
        List<Story> stories = new ArrayList<>();
        try {
            String sql = "SELECT TOP 1000 StoryId, Title, Description, AuthorId, GenreId, Status, CreatedAt, Image FROM Stories WHERE Status = 'Completed' ORDER BY CreatedAt DESC";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Story story = new Story();
                story.setStoryId(rs.getInt("StoryId"));
                story.setTitle(rs.getString("Title"));
                story.setDescription(rs.getString("Description"));
                story.setAuthorID(rs.getInt("AuthorId"));
                story.setGenreID(rs.getInt("GenreId"));
                story.setStatus(rs.getString("Status"));
                story.setCreatedAt(rs.getTimestamp("CreatedAt"));
                story.setImage(rs.getString("Image"));
                stories.add(story);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stories;
    }

    public List<Story> getNewestStoriesWithImages() {
        List<Story> stories = new ArrayList<>();
        try {
            String sql = "SELECT TOP 1000 StoryId, Title, Description, AuthorId, GenreId, Status, CreatedAt, Image FROM Stories ORDER BY CreatedAt DESC";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Story story = new Story();
                story.setStoryId(rs.getInt("StoryId"));
                story.setTitle(rs.getString("Title"));
                story.setDescription(rs.getString("Description"));
                story.setAuthorID(rs.getInt("AuthorId"));
                story.setGenreID(rs.getInt("GenreId"));
                story.setStatus(rs.getString("Status"));
                story.setCreatedAt(rs.getTimestamp("CreatedAt"));
                story.setImage(rs.getString("Image"));
                stories.add(story);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stories;
    }

    //---------------------------------------------------------------------------------------
    public List<Author> getListAuthor() {
        List<Author> auth = new ArrayList<>();
        try {
            String sql = "SELECT distinct * FROM Authors";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Author a = new Author(rs.getInt("AuthorId"), rs.getString("AuthorName"));
                auth.add(a);
            }
            return auth;
        } catch (SQLException ex) {
            Logger.getLogger(StoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return auth;
    }

    public List<Genre> getListGenre() {
        List<Genre> genre = new ArrayList<>();
        try {
            String sql = "SELECT distinct * FROM Genres";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Genre g = new Genre(rs.getInt("GenreId"), rs.getString("GenreName"));
                genre.add(g);
            }
            return genre;
        } catch (SQLException ex) {
            Logger.getLogger(StoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return genre;
    }

    public List<Chapter> getListChapterByStoryId(int storyId) {
        List<Chapter> chapters = new ArrayList<>();
        try {
            String sql = "SELECT distinct * FROM Chapters where StoryId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, storyId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Chapter c = new Chapter(rs.getInt("ChapterId"), storyId, rs.getInt("ChapterNumber"), rs.getString("Title"), rs.getTimestamp("CreatedAt"));
                chapters.add(c);
            }
            return chapters;
        } catch (SQLException ex) {
            Logger.getLogger(StoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return chapters;
    }

    public boolean deleteChapterByStoryId(int storyId) {
        String sql = "DELETE FROM Chapters WHERE StoryId = ?";
        try ( PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, storyId);
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean deleteReadingHistoryByStoryId(int storyId) {
        String sql = "DELETE FROM ReadingHistory WHERE StoryId = ?";
        try ( PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, storyId);
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean deleteCommentsByStoryId(int storyId) {
        String sql = "DELETE FROM Comments WHERE StoryId = ?";
        try ( PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, storyId);
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean deleteFavoritesByStoryId(int storyId) {
        String sql = "DELETE FROM Favorites WHERE StoryId = ?";
        try ( PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, storyId);
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean deleteChapterById(int chapterId) {
        String sql = "DELETE FROM chapters WHERE chapterId = ?";
        try ( PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, chapterId);
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean deleteChapterImagesByChapterId(int chapterId) {
        String sql = "DELETE FROM ChapterImages WHERE chapterId = ?";
        try ( PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, chapterId);
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Chapter getChapterById(int chapterId) {
        String sql = "Select * from chapters where chapterId = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, chapterId);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Chapter c = new Chapter(rs.getInt("ChapterId"), rs.getInt("StoryId"), rs.getInt("ChapterNumber"), rs.getString("title"), rs.getTimestamp("CreatedAt"));
                return c;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Story> searchStoriesByTitle(String keyword) {
        List<Story> stories = new ArrayList<>();
        String sql = "SELECT StoryId, Title, Description, AuthorId, GenreId, Status, CreatedAt, Image FROM Stories WHERE Title LIKE ? ORDER BY CreatedAt DESC";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, "%" + keyword + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Story story = new Story();
                story.setStoryId(rs.getInt("StoryId"));
                story.setTitle(rs.getString("Title"));
                story.setDescription(rs.getString("Description"));
                story.setAuthorID(rs.getInt("AuthorId"));
                story.setGenreID(rs.getInt("GenreId"));
                story.setStatus(rs.getString("Status"));
                story.setCreatedAt(rs.getTimestamp("CreatedAt"));
                story.setImage(rs.getString("Image"));
                stories.add(story);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return stories;
    }

    public List<ChapterImage> getListChapterImagesByChapterId(int chapterId) {
        List<ChapterImage> list = new ArrayList<>();
        String sql = "select * from chapterImages where chapterId = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, chapterId);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                ChapterImage ci = new ChapterImage(rs.getInt("ImageId"), rs.getInt("ChapterId"), rs.getString("ImageUrl"), rs.getInt("OrderNumber"));
                list.add(ci);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getChapterNewestOfStory(int storyId) {
        String sql = "SELECT MAX(ChapterNumber) AS ChapterNumber\n"
                + "FROM chapters\n"
                + "WHERE StoryId = ?;";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, storyId);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                return rs.getInt("ChapterNumber");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    public int getChapterImageNewestOfChapter(int chapterId) {
        String sql = "SELECT MAX(OrderNumber) AS OrderNumber\n"
                + "FROM chapterImages\n"
                + "WHERE ChapterId = ?;";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, chapterId);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                return rs.getInt("OrderNumber");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean updateChapter(Chapter chapter) {
        String sql = "UPDATE Chapters SET Title = ?\n"
                + "WHERE ChapterId = ?";
        try ( PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, chapter.getTitle());
            stm.setInt(2, chapter.getChapterId());
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        StoryDAO dao = new StoryDAO();
        Chapter c = new Chapter();
        c.setChapterId(4);
        c.setTitle("3");
        System.out.println(dao.updateChapter(c));
    }
}

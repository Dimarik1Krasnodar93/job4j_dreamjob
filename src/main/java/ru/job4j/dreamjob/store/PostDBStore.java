package ru.job4j.dreamjob.store;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.service.CityService;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostDBStore {

    private final BasicDataSource pool;
    private final CityService cityService = new CityService();

    public PostDBStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public List<Post> findAll() {
        List<Post> posts = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("SELECT * FROM post")
        ) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    Post post = new Post(it.getInt("id"), it.getString("name"));
                    post.setDescription(it.getString("description"));
                    post.setCreated(it.getTimestamp("created").toLocalDateTime().toLocalDate());
                    post.setVisible(it.getBoolean("visible"));
                    post.setCity(cityService.findById(it.getInt("id_city")));
                    posts.add(post);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return posts;
    }


    public Post add(Post post) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("INSERT INTO post(name) VALUES (?)",
                     PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, post.getName());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    post.setId(id.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return post;
    }

    public void updatePost(Post post) {
        try (Connection cn = pool.getConnection()) {
            PreparedStatement ps =  cn.prepareStatement("UPDATE POST set name = ?, description = ?, created = ?, "
                    + "visible = ?, id_city = ? WHERE id = ?");
            ps.setString(1, post.getName());
            ps.setString(2, post.getDescription());
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.of(post.getCreated().getYear(),
                    post.getCreated().getMonth(), post.getCreated().getDayOfMonth(), 0, 0, 0)));
            ps.setBoolean(4, post.getVisible());
            ps.setInt(5, post.getCity().getId());
            ps.setInt(6, post.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPost(Post post) {
        try (Connection cn = pool.getConnection()) {
            PreparedStatement ps =  cn.prepareStatement("INSERT INTO POST (name, description, created, visible, id_city) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, post.getName());
            ps.setString(2, post.getDescription());
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.of(post.getCreated().getYear(),
            post.getCreated().getMonth(), post.getCreated().getDayOfMonth(), 0, 0, 0)));
            ps.setBoolean(4, post.getVisible());
            ps.setInt(5, post.getCity().getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Post findById(int id) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("SELECT * FROM post WHERE id = ?")
        ) {
            ps.setInt(1, id);
            try (ResultSet it = ps.executeQuery()) {
                if (it.next()) {
                    Post post = new Post(it.getInt("id"), it.getString("name"));
                    post.setCity(cityService.findById(it.getInt("id_city")));
                    return post;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

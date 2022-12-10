package ru.job4j.dreamjob.store;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.queries.PostQueries;
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
    private final Logger logger = LoggerFactory.getLogger(PostDBStore.class.getName());

    public PostDBStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public List<Post> findAll() {
        List<Post> posts = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(PostQueries.FIND_ALL)) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    Post post = new Post(it.getInt("id"), it.getString("name"),
                            it.getString("description"), it.getTimestamp("created").toLocalDateTime().toLocalDate(),
                            it.getBoolean("visible"), cityService.findById(it.getInt("id_city")));
                    posts.add(post);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return posts;
    }

    public void updatePost(Post post) {
        try (Connection cn = pool.getConnection()) {
            PreparedStatement ps =  cn.prepareStatement(PostQueries.UPDATE);
            ps.setString(1, post.getName());
            ps.setString(2, post.getDescription());
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.of(post.getCreated().getYear(),
                    post.getCreated().getMonth(), post.getCreated().getDayOfMonth(), 0, 0, 0)));
            ps.setBoolean(4, post.getVisible());
            ps.setInt(5, post.getCity().getId());
            ps.setInt(6, post.getId());
            ps.execute();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public void addPost(Post post) {
        try (Connection cn = pool.getConnection()) {
            PreparedStatement ps =  cn.prepareStatement(PostQueries.ADD);
            ps.setString(1, post.getName());
            ps.setString(2, post.getDescription());
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.of(post.getCreated().getYear(),
            post.getCreated().getMonth(), post.getCreated().getDayOfMonth(), 0, 0, 0)));
            ps.setBoolean(4, post.getVisible());
            ps.setInt(5, post.getCity().getId());
            ps.execute();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public Post findById(int id) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(PostQueries.FIND);
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
            logger.error(e.getMessage());
        }
        return null;
    }
}

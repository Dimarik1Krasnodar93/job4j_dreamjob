package ru.job4j.dreamjob.store;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.queries.PostQueries;

import java.sql.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostDBStore {

    private final BasicDataSource pool;
    private final Logger logger = LogManager.getLogger(PostDBStore.class);

    public PostDBStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public List<Post> findAll() {
        List<Post> posts = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(PostQueries.FIND_ALL)) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    Post post = getPost(it);
                    posts.add(post);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return posts;
    }

    public void updatePost(Post post) {
        try (Connection cn = pool.getConnection()) {
            PreparedStatement ps =  cn.prepareStatement(PostQueries.UPDATE, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, post.getName());
            ps.setString(2, post.getDescription());
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.of(post.getCreated().getYear(),
                    post.getCreated().getMonth(), post.getCreated().getDayOfMonth(), 0, 0, 0)));
            ps.setBoolean(4, post.getVisible());
            ps.setInt(5, post.getCity().getId());
            ps.setInt(6, post.getId());
            ps.execute();
            ResultSet gk = ps.getGeneratedKeys();
            if (gk.next()) {
                post.setId(gk.getInt("id"));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public void addPost(Post post) {
        try (Connection cn = pool.getConnection()) {
            PreparedStatement ps =  cn.prepareStatement(PostQueries.ADD, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, post.getName());
            ps.setString(2, post.getDescription());
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.of(post.getCreated().getYear(),
            post.getCreated().getMonth(), post.getCreated().getDayOfMonth(), 0, 0, 0)));
            ps.setBoolean(4, post.getVisible());
            ps.setInt(5, post.getCity().getId());
            ps.execute();
            ResultSet gk = ps.getGeneratedKeys();
            if (gk.next()) {
                post.setId(gk.getInt("id"));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public Post findById(int id) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(PostQueries.FIND);
        ) {
            ps.setInt(1, id);
            try (ResultSet it = ps.executeQuery()) {
                if (it.next()) {
                    return getPost(it);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    private Post getPost(ResultSet it) throws SQLException {
        return new Post(it.getInt("id"), it.getString("name"),
            it.getString("description"), it.getTimestamp("created").toLocalDateTime().toLocalDate(),
                it.getBoolean("visible"), new City(it.getInt("id_city"), ""));
    }
}

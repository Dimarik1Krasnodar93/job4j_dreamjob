package ru.job4j.dreamjob.store;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.queries.PostQueries;
import ru.job4j.dreamjob.service.CityService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CandidateDBStore {
    private BasicDataSource pool;
    private final CityService cityService = new CityService();
    private final Logger logger = LoggerFactory.getLogger(PostDBStore.class.getName());

    public CandidateDBStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public List<Post> findAll() {
        List<Post> posts = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(PostQueries.FIND_ALL);
        ) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    it.getInt("i3d");
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

}

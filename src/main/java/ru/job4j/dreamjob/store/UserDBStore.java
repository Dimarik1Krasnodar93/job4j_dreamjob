package ru.job4j.dreamjob.store;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.model.User;
import ru.job4j.dreamjob.queries.PostQueries;
import ru.job4j.dreamjob.queries.UserQueries;
import ru.job4j.dreamjob.service.CityService;

import java.sql.*;
import java.time.LocalDateTime;

@Repository
public class UserDBStore {
    private final BasicDataSource pool;
    private final CityService cityService = new CityService();
    private final Logger logger = LoggerFactory.getLogger(PostDBStore.class.getName());

    public UserDBStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public void addUser(User user) {
        try (Connection cn = pool.getConnection()) {
            PreparedStatement ps =  cn.prepareStatement(UserQueries.ADD);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.execute();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public User findUserByEmail(String email) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(PostQueries.FIND);
        ) {
            ps.setString(1, email);
            try (ResultSet it = ps.executeQuery()) {
                if (it.next()) {
                    User user = new User(it.getInt("id"), it.getString("email"),
                            it.getString("password"));
                    return user;
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }
}

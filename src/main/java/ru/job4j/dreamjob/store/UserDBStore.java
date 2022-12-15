package ru.job4j.dreamjob.store;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.User;
import ru.job4j.dreamjob.queries.UserQueries;
import java.sql.*;
import java.util.Optional;

@Repository
public class UserDBStore {
    private final BasicDataSource pool;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDBStore.class.getName());

    public UserDBStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public Optional<User> addUser(User user) {
        try (Connection cn = pool.getConnection()) {
            PreparedStatement ps =  cn.prepareStatement(UserQueries.ADD, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.execute();
            ResultSet  resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                return Optional.of(user);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return Optional.empty();
    }

    public Optional<User> findUserByEmailAndPassword(String email, String password) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(UserQueries.FIND_EMAIL_PASSWORD);
        ) {
            ps.setString(1, email);
            ps.setString(2, password);
            try (ResultSet it = ps.executeQuery()) {
                if (it.next()) {
                    User user = new User(it.getInt("id"), it.getString("email"),
                            it.getString("password"));
                    return Optional.of(user);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return Optional.empty();
    }

    public Optional<User> findUserByEmail(String email) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(UserQueries.FIND);
        ) {
            ps.setString(1, email);
            try (ResultSet it = ps.executeQuery()) {
                if (it.next()) {
                    User user = new User(it.getInt("id"), it.getString("email"),
                            it.getString("password"));
                    return Optional.of(user);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return Optional.empty();
    }
}

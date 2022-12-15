package ru.job4j.dreamjob.store;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.queries.CandidateQueries;
import java.sql.*;
import java.util.ArrayList;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Repository
public class CandidateDBStore {
    private BasicDataSource pool;
    private static final Logger LOGGER = LogManager.getLogger(CandidateDBStore.class);

    public CandidateDBStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public List<Candidate> findAll() {
        List<Candidate> candidates = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(CandidateQueries.FIND_ALL);
        ) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    candidates.add(getCandidateFromResultSet(it));
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return candidates;
    }

    public void updateCandidate(Candidate candidate) {
        try (Connection cn = pool.getConnection()) {
            PreparedStatement ps =  cn.prepareStatement(CandidateQueries.UPDATE, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, candidate.getName());
            ps.setString(2, candidate.getDescription());
            ps.setTimestamp(3, Timestamp.valueOf(candidate.getCreated()));
            ps.setBoolean(4, candidate.getVisible());
            ps.setInt(5, candidate.getCity().getId());
            ps.setBytes(6, candidate.getPhoto());
            ps.setInt(7, candidate.getId());
            ps.execute();
            ResultSet gk = ps.getGeneratedKeys();
            if (gk.next()) {
                candidate.setId(gk.getInt("id"));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public void addCandidate(Candidate candidate) {
        try (Connection cn = pool.getConnection()) {
            PreparedStatement ps =  cn.prepareStatement(CandidateQueries.ADD, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, candidate.getName());
            ps.setString(2, candidate.getDescription());
            ps.setTimestamp(3, Timestamp.valueOf(candidate.getCreated()));
            ps.setBoolean(4, candidate.getVisible());
            ps.setInt(5, candidate.getCity().getId());
            ps.setBytes(6, candidate.getPhoto());
            ps.execute();
            ResultSet gk = ps.getGeneratedKeys();
            if (gk.next()) {
                candidate.setId(gk.getInt("id"));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public Candidate findById(int id) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(CandidateQueries.FIND)) {
            ps.setInt(1, id);
            try (ResultSet it = ps.executeQuery()) {
                if (it.next()) {
                    return getCandidateFromResultSet(it);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    private Candidate getCandidateFromResultSet(ResultSet it) throws Exception {
        return new Candidate(it.getInt("id"), it.getString("name"),
                it.getString("description"),
                it.getTimestamp("created").toLocalDateTime(),
                new City(it.getInt("city_id"), ""),
                it.getBytes("photo"),
                it.getBoolean("visible")
                );
    }

}

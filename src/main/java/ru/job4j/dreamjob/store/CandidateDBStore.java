package ru.job4j.dreamjob.store;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.queries.CandidateQueries;
import ru.job4j.dreamjob.service.CityService;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;

@Repository
public class CandidateDBStore {
    private BasicDataSource pool;
    private final CityService cityService = new CityService();
    private final Logger logger = LoggerFactory.getLogger(PostDBStore.class.getName());

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
                    Candidate candidate = new Candidate(it.getInt("id"), it.getString("name"),
                            it.getString("description"), it.getTimestamp("created").toLocalDateTime().toLocalDate(),
                            cityService.findById(it.getInt("id_city")),
                            it.getBytes("photo"), it.getBoolean("visible"));
                    candidates.add(candidate);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return candidates;
    }

    public void updateCandidate(Candidate candidate) {
        try (Connection cn = pool.getConnection()) {
            PreparedStatement ps =  cn.prepareStatement(CandidateQueries.UPDATE);
            ps.setString(1, candidate.getName());
            ps.setString(2, candidate.getDescription());
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.of(candidate.getCreated().getYear(),
                    candidate.getCreated().getMonth(), candidate.getCreated().getDayOfMonth(), 0, 0, 0)));
            ps.setBoolean(4, candidate.getVisible());
            ps.setInt(5, candidate.getCity().getId());
            ps.setBytes(6, candidate.getPhoto());
            ps.setInt(7, candidate.getId());
            ps.execute();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public void addCandidate(Candidate candidate) {
        try (Connection cn = pool.getConnection()) {
            PreparedStatement ps =  cn.prepareStatement(CandidateQueries.ADD);
            ps.setString(1, candidate.getName());
            ps.setString(2, candidate.getDescription());
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.of(candidate.getCreated().getYear(),
                    candidate.getCreated().getMonth(), candidate.getCreated().getDayOfMonth(), 0, 0, 0)));
            ps.setBoolean(4, candidate.getVisible());
            ps.setInt(5, candidate.getCity().getId());
            ps.setBytes(6, candidate.getPhoto());
            ps.execute();
        } catch (SQLException e) {
            logger.error(e.getMessage());
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
            logger.error(e.getMessage());
        }
        return null;
    }

    private Candidate getCandidateFromResultSet(ResultSet it) throws Exception {
        return new Candidate(it.getInt("id"), it.getString("name"),
                it.getString("description"),
                it.getTimestamp("created").toLocalDateTime().toLocalDate(),
                cityService.findById(it.getInt("id_city")),
                it.getBytes("photo"),
                it.getBoolean("visible")
                );
    }

}

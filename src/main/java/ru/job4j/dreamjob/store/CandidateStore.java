package ru.job4j.dreamjob.store;

import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.model.City;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class CandidateStore {

    private Map<Integer, Candidate> map = new ConcurrentHashMap<>();
    private AtomicInteger id = new AtomicInteger(3);

    private CandidateStore() {
        map.put(1, new Candidate(1, "Ivan", "Programmer",
                LocalDateTime.of(2020, 2, 1, 0, 0, 0), new City(1, "Moscow")));
        map.put(2, new Candidate(2, "Petr", "Lead developer",
                LocalDateTime.of(2020, 1, 1, 0, 0, 0), new City(1, "Moscow")));
        map.put(3, new Candidate(3, "Dmitry", "Junior developer",
                LocalDateTime.of(2022, 1, 1, 0, 0, 0), new City(1, "Moscow")));
    }

    public Collection<Candidate> findAll() {
        return map.values();
    }

    public Candidate findById(int id) {
        return map.get(id);
    }

    public void addCandidate(Candidate candidate) {
        candidate.setId(id.incrementAndGet());
        map.put(candidate.getId(), candidate);
    }

    public void updateCandidate(Candidate candidate) {
        map.put(candidate.getId(), candidate);
    }
}

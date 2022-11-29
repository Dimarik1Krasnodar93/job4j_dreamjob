package ru.job4j.dreamjob.store;

import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.model.Post;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class CandidateStore {

    private static final CandidateStore INSTANCE = new CandidateStore();
    private Map<Integer, Candidate> map = new HashMap<>();
    private AtomicInteger id = new AtomicInteger(3);

    private CandidateStore() {
        map.put(1, new Candidate(1, "Ivan", "Programmer",
                LocalDate.of(2020, 2, 1)));
        map.put(2, new Candidate(2, "Petr", "Lead developer",
                LocalDate.of(2020, 1, 1)));
        map.put(3, new Candidate(3, "Dmitry", "Junior developer",
                LocalDate.of(2022, 1, 1)));
    }

    public static CandidateStore instanceOf() {
            return INSTANCE;
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

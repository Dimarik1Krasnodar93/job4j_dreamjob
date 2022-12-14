package ru.job4j.dreamjob.service;

import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.store.CandidateDBStore;
import java.util.Collection;

@Service
public class CandidatesService {
    private CandidateDBStore store;

    private CandidatesService(CandidateDBStore store) {
        this.store = store;
    }

    public CandidateDBStore getStore() {
        return store;
    }

    public Candidate findById(int id) {
        return store.findById(id);
    }

    public void addCandidate(Candidate candidate) {
        store.addCandidate(candidate);
    }

    public void updateCandidate(Candidate candidate) {
        store.updateCandidate(candidate);
    }

    public Collection<Candidate> findAll() {
        return store.findAll();
    }
}

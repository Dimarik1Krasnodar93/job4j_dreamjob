package ru.job4j.dreamjob.service;

import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.store.CandidateStore;

@Service
public class CandidatesService {
    private CandidateStore store;

    private CandidatesService(CandidateStore store) {
        this.store = store;
    }

    public CandidateStore getStore() {
        return store;
    }

    public Candidate getById(int id) {
        return store.findById(id);
    }
}

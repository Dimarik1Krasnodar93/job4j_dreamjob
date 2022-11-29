package ru.job4j.dreamjob.service;

import ru.job4j.dreamjob.store.CandidateStore;

public class CandidatesService {
    private static CandidatesService candidatesService;
    private CandidateStore store = CandidateStore.instanceOf();

    private CandidatesService() {

    }

    public static CandidatesService instanceOf() {
        if (candidatesService == null) {
            candidatesService = new CandidatesService();
        }
        return candidatesService;
    }

    public CandidateStore getStore() {
        return store;
    }
}

package ru.job4j.dreamjob.store;

import org.junit.jupiter.api.Test;
import ru.job4j.dreamjob.Main;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.model.Post;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class CandidateDBStoreTest {

    @Test
    void whenUpdateCandidate() {
        byte[] photo = new byte[1000];
        CandidateDBStore store = new CandidateDBStore(new Main().loadPool());
        Candidate candidate = new Candidate(1, "Ivan", "IT", LocalDateTime.now(),  new City(1, "Moscow"), photo, true);
        store.addCandidate(candidate);
        candidate = new Candidate(1, "Ivan2", "IT", LocalDateTime.now(),  new City(1, "Moscow"), photo, true);
        store.updateCandidate(candidate);
        Candidate candidateInDb = store.findById(candidate.getId());
        assertThat(candidateInDb.getName()).isEqualTo(candidate.getName());
    }

    @Test
    void whenAddCandidate() {
        byte[] photo = new byte[1000];
        CandidateDBStore store = new CandidateDBStore(new Main().loadPool());
        Candidate candidate = new Candidate(2, "Ivan", "IT", LocalDateTime.now(),  new City(1, "Moscow"), photo, true);
        store.addCandidate(candidate);
        Candidate candidateInDb = store.findById(candidate.getId());
        assertThat(candidateInDb.getName()).isEqualTo(candidate.getName());
    }
}
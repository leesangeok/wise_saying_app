package com.ll.wiseSaying.service;

import com.ll.wiseSaying.domain.WiseSaying;
import com.ll.wiseSaying.repository.WiseSayingRepository;

import java.util.List;

// 순수 비즈니스 로직을 처리

public class WiseSayingService {
    private final WiseSayingRepository repository = new WiseSayingRepository();

    public WiseSaying write(String content, String author) {
        int id = repository.getLastId() + 1;
        WiseSaying wiseSaying = new WiseSaying(id, content, author);
        repository.save(wiseSaying);
        repository.saveLastId(id);
        return wiseSaying;
    }

    public List<WiseSaying> findAll() {
        return repository.findAll();
    }

    public boolean delete(int id) {
        return repository.deleteById(id);
    }

    public WiseSaying findById(int id) {
        return repository.findById(id);
    }

    public void update(int id, String content, String author) {
        WiseSaying wiseSaying = new WiseSaying(id, content, author);
        repository.update(wiseSaying);
    }

    public void buildDataJson() {
        repository.buildDataJson();
    }
}

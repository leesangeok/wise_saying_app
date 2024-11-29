package com.ll.wiseSaying.service;

import com.ll.wiseSaying.domain.WiseSaying;
import com.ll.wiseSaying.repository.WiseSayingRepository;

import java.util.List;

public class WiseSayingService {
    private final WiseSayingRepository repository = new WiseSayingRepository();

    // 명언 등록
    public WiseSaying write(String content, String author) {
        int id = repository.getLastId() + 1;
        WiseSaying wiseSaying = new WiseSaying(id, content, author);
        repository.save(wiseSaying);
        repository.saveLastId(id);
        return wiseSaying;
    }

    // 전체 명언 목록 반환 (검색어와 페이지, 페이징 처리 포함)
    public List<WiseSaying> findAll(String searchQuery, int page, int pageSize) {
        return repository.findAll(searchQuery, page, pageSize);
    }

    // 명언 삭제
    public boolean delete(int id) {
        return repository.deleteById(id);
    }

    // 명언 ID로 조회
    public WiseSaying findById(int id) {
        return repository.findById(id);
    }

    // 명언 수정
    public void update(int id, String content, String author) {
        WiseSaying wiseSaying = new WiseSaying(id, content, author);
        repository.update(wiseSaying);
    }

    // data.json 파일 생성
    public void buildDataJson() {
        repository.buildDataJson();
    }

    // 데이터 초기화
    public void clearData() {
        repository.clearData();
    }


    // 총 명언 수 반환 (검색어를 반영한 개수)
    public int getTotalCount(String searchQuery) {
        return repository.getTotalCount(searchQuery);
    }


}

package com.ll.wiseSaying.domain;
// 명언 객체(번호, 명언 내용, 작가).
//컨트롤러, 서비스, 리포지터리 모두에서 사용 가능

public class WiseSaying {
    private int id;
    private String content;
    private String author;

    public WiseSaying(int id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }
}

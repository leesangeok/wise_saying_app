package com.ll.wiseSaying.controller;

import com.ll.wiseSaying.domain.WiseSaying;
import com.ll.wiseSaying.service.WiseSayingService;

import java.util.List;
import java.util.Scanner;

// 사용자의 명령을 처리하고 적절한 응답을 표현
public class WiseSayingController {
    private final WiseSayingService service = new WiseSayingService();
    private static final int PAGE_SIZE = 5; // 한 페이지에 표시할 명언의 수

    public void handleCommand(String cmd, Scanner scanner) {
        if (cmd.equals("등록")) {
            System.out.print("명언 : ");
            String content = scanner.nextLine();

            System.out.print("작가 : ");
            String author = scanner.nextLine();

            WiseSaying wiseSaying = service.write(content, author);
            System.out.println(wiseSaying.getId() + "번 명언이 등록되었습니다.");
        } else if (cmd.equals("목록")) {
            // 1페이지, 검색어 없이
            System.out.println("== 명언 목록 (1페이지) ==");
            List<WiseSaying> wiseSayings = service.findAll("", 1, PAGE_SIZE); // 검색어 없이 1페이지에 5개만

            if (wiseSayings.isEmpty()) {
                System.out.println("등록된 명언이 없습니다.");
                return;
            }

            for (WiseSaying wiseSaying : wiseSayings) {
                System.out.println(wiseSaying.getId() + " / " + wiseSaying.getAuthor() + " / " + wiseSaying.getContent());
            }

            // 페이징 정보 출력
            System.out.println("페이지 : [1]"); // 현재 페이지는 1페이지
        } else if (cmd.equals("삭제")) {
            System.out.print("삭제할 명언 번호 : ");
            int id = Integer.parseInt(scanner.nextLine());

            if (service.delete(id)) {
                System.out.println(id + "번 명언이 삭제되었습니다.");
            } else {
                System.out.println("해당 번호의 명언이 존재하지 않습니다.");
            }
        } else if (cmd.equals("수정")) {
            System.out.print("수정할 명언 번호 : ");
            int id = Integer.parseInt(scanner.nextLine());

            WiseSaying wiseSaying = service.findById(id);
            if (wiseSaying == null) {
                System.out.println("해당 번호의 명언이 존재하지 않습니다.");
                return;
            }

            System.out.println("명언(기존) : " + wiseSaying.getContent());
            System.out.print("명언 : ");
            String newContent = scanner.nextLine();

            System.out.println("작가(기존) : " + wiseSaying.getAuthor());
            System.out.print("작가 : ");
            String newAuthor = scanner.nextLine();

            service.update(id, newContent, newAuthor);
            System.out.println(id + "번 명언이 수정되었습니다.");
        } else if (cmd.equals("빌드")) {
            service.buildDataJson();
            System.out.println("data.json 파일이 생성되었습니다.");
        } else if (cmd.equals("목록?")) {
            // 검색어와 페이지를 반영한 목록 출력
            System.out.print("검색어 (없으면 엔터): ");
            String searchQuery = scanner.nextLine();

            System.out.print("페이지 번호 (기본 1): ");
            String pageInput = scanner.nextLine();
            int page = pageInput.isEmpty() ? 1 : Integer.parseInt(pageInput);

            int totalCount = service.getTotalCount(searchQuery);
            int totalPages = (int) Math.ceil((double) totalCount / PAGE_SIZE);

            List<WiseSaying> wiseSayings = service.findAll(searchQuery, page, PAGE_SIZE);

            if (wiseSayings.isEmpty()) {
                System.out.println("검색된 명언이 없습니다.");
                return;
            }

            for (WiseSaying wiseSaying : wiseSayings) {
                System.out.println(wiseSaying.getId() + " / " + wiseSaying.getAuthor() + " / " + wiseSaying.getContent());
            }

            System.out.println("페이지 : [" + page + "] / " + totalPages);
        }
    }

    public void clearData() {
        service.clearData();
    }
}

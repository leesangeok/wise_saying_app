package com.ll.wiseSaying.controller;

import com.ll.wiseSaying.domain.WiseSaying;
import com.ll.wiseSaying.service.WiseSayingService;

import java.util.List;
import java.util.Scanner;
// 사용자의 명령을 처리하고 적절한 응답을 표현
public class WiseSayingController {
    private final WiseSayingService service = new WiseSayingService();

    public void handleCommand(String cmd, Scanner scanner) {
        if (cmd.equals("등록")) {
            System.out.print("명언 : ");
            String content = scanner.nextLine();

            System.out.print("작가 : ");
            String author = scanner.nextLine();

            WiseSaying wiseSaying = service.write(content, author);
            System.out.println(wiseSaying.getId() + "번 명언이 등록되었습니다.");
        } else if (cmd.equals("목록")) {
            System.out.println("== 명언 목록 ==");
            List<WiseSaying> wiseSayings = service.findAll();

            if (wiseSayings.isEmpty()) {
                System.out.println("등록된 명언이 없습니다.");
                return;
            }

            for (WiseSaying wiseSaying : wiseSayings) {
                System.out.println(wiseSaying.getId() + " / " + wiseSaying.getAuthor() + " / " + wiseSaying.getContent());
            }
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
        } else if (cmd.equals("목록?")) { // 검색 명령 기본 입력 처리
            System.out.print("검색할 타입을 입력하세요 (예: author, content):");
            String keywordType = scanner.nextLine().trim();

            System.out.print("keyword: ");
            String keyword = scanner.nextLine().trim();

            System.out.println("----------------------");
            System.out.println("검색타입 : " + keywordType);
            System.out.println("검색어 : " + keyword);
            System.out.println("----------------------");

            // 검색 실행
            List<WiseSaying> filteredSayings = service.search(keywordType, keyword);

            // 결과 출력
            if (filteredSayings.isEmpty()) {
                System.out.println("검색 결과가 없습니다.");
            } else {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");
                for (WiseSaying wiseSaying : filteredSayings) {
                    System.out.println(wiseSaying.getId() + " / " + wiseSaying.getAuthor() + " / " + wiseSaying.getContent());
                }
            }
        }

        else {
            System.out.println("알 수 없는 명령입니다. 사용 가능한 명령: 등록, 목록, 삭제, 수정, 빌드, 종료");
        }
    }
    public void clearData() {
        service.clearData();
    }
}

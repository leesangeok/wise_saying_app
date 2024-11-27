package com.ll.wiseSaying;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WiseSayingControllerTest {

    @BeforeEach
    void beforeEach() {
        // 각 테스트 시작 전에 데이터 초기화
        System.out.println("=== 테스트 초기화 ===");
        AppTest.clear();
    }

    @Test
    @DisplayName("등록 명령어 테스트")
    void testRegisterCommand() {
        System.out.println("\n=== 등록 명령어 테스트 시작 ===");
        String input = """
                등록
                현재를 사랑하라.
                작자미상
                종료
                """;

        System.out.println("입력값:");
        System.out.println(input);

        String output = AppTest.run(input);

        System.out.println("출력값:");
        System.out.println(output);

        assertThat(output)
                .contains("명언 :")
                .contains("작가 :")
                .contains("1번 명언이 등록되었습니다.");
    }

    @Test
    @DisplayName("목록 명령어 테스트")
    void testListCommand() {
        System.out.println("\n=== 목록 명령어 테스트 시작 ===");
        String input = """
                등록
                현재를 사랑하라.
                작자미상
                등록
                내일은 내일의 태양이 뜬다.
                작자미상
                목록
                종료
                """;

        System.out.println("입력값:");
        System.out.println(input);

        String output = AppTest.run(input);

        System.out.println("출력값:");
        System.out.println(output);

        assertThat(output)
                .contains("1 / 작자미상 / 현재를 사랑하라.")
                .contains("2 / 작자미상 / 내일은 내일의 태양이 뜬다.");
    }

    @Test
    @DisplayName("삭제 명령어 테스트")
    void testDeleteCommand() {
        System.out.println("\n=== 삭제 명령어 테스트 시작 ===");
        String input = """
                등록
                현재를 사랑하라.
                작자미상
                삭제
                1
                종료
                """;

        System.out.println("입력값:");
        System.out.println(input);

        String output = AppTest.run(input);

        System.out.println("출력값:");
        System.out.println(output);

        assertThat(output)
                .contains("1번 명언이 삭제되었습니다.");
    }

    @Test
    @DisplayName("수정 명령어 테스트")
    void testUpdateCommand() {
        System.out.println("\n=== 수정 명령어 테스트 시작 ===");
        String input = """
                등록
                현재를 사랑하라.
                작자미상
                수정
                1
                과거를 기억하라.
                홍길동
                종료
                """;

        System.out.println("입력값:");
        System.out.println(input);

        String output = AppTest.run(input);

        System.out.println("출력값:");
        System.out.println(output);

        assertThat(output)
                .contains("1번 명언이 수정되었습니다.");
    }

    @Test
    @DisplayName("목록? 올바른 명령어 테스트")
    void testValidSearchCommand() {
        System.out.println("\n=== 목록? 명령어 테스트 시작 ===");
        String input = """
                등록
                과거를 기억하라.
                홍길동
                목록?keywordType=author&keyword=홍길동
                종료
                """;

        System.out.println("입력값:");
        System.out.println(input);

        String output = AppTest.run(input);

        System.out.println("출력값:");
        System.out.println(output);

        assertThat(output)
                .contains("검색타입 : author")
                .contains("검색어 : 홍길동")
                .contains("1 / 홍길동 / 과거를 기억하라.");
    }
}

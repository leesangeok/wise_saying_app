package com.ll.wiseSaying;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WiseSayingControllerTest {

    @BeforeEach
    void beforeEach() {
        // 각 테스트 시작 전에 데이터 초기화
        AppTest.clear();
    }

    @Test
    @DisplayName("등록 명령어 테스트")
    void testRegisterCommand() {
        String input = """
                등록
                현재를 사랑하라.
                작자미상
                종료
                """;

        String output = AppTest.run(input);

        assertThat(output)
                .contains("명언 :")
                .contains("작가 :")
                .contains("1번 명언이 등록되었습니다.");
    }

    @Test
    @DisplayName("목록 명령어 테스트")
    void testListCommand() {
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

        String output = AppTest.run(input);

        assertThat(output)
                .contains("1 / 작자미상 / 현재를 사랑하라.")
                .contains("2 / 작자미상 / 내일은 내일의 태양이 뜬다.");
    }

    @Test
    @DisplayName("삭제 명령어 테스트")
    void testDeleteCommand() {
        String input = """
                등록
                현재를 사랑하라.
                작자미상
                삭제
                1
                종료
                """;

        String output = AppTest.run(input);

        assertThat(output)
                .contains("1번 명언이 삭제되었습니다.");
    }

    @Test
    @DisplayName("수정 명령어 테스트")
    void testUpdateCommand() {
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

        String output = AppTest.run(input);

        assertThat(output)
                .contains("1번 명언이 수정되었습니다.");
    }
}

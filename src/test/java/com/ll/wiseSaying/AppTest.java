package com.ll.wiseSaying;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class AppTest {
    // 애플리케이션을 실행하고 출력을 반환하는 메서드
    public static String run(String input) {
        // 원래의 System.in과 System.out 저장
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        // 테스트용 InputStream과 OutputStream 설정
        InputStream testIn = TestUtil.createInputStream(input);
        ByteArrayOutputStream testOut = TestUtil.captureOutput();

        // System.in과 System.out을 테스트용으로 변경
        System.setIn(testIn);
        // System.out은 이미 TestUtil.captureOutput()에서 변경됨

        // 애플리케이션 실행
        App app = new App();
        app.run();

        // 출력 캡처 완료 후 원래대로 복원
        TestUtil.restoreSystemInputOutput(originalIn, originalOut);

        // 캡처된 출력 반환
        return testOut.toString();
    }

    // 테스트 전 데이터 초기화 메서드
    public static void clear() {
        // 애플리케이션 데이터 초기화 메서드 호출
        // 예: 데이터베이스 초기화, 파일 초기화 등
        // 여기서는 WiseSayingService를 통해 초기화
        new com.ll.wiseSaying.service.WiseSayingService().clearData();
    }
}

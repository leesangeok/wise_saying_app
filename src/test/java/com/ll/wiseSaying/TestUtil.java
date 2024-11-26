package com.ll.wiseSaying;

import java.io.*;
import java.util.Scanner;

public class TestUtil {
    // 테스트용 InputStream 생성
    public static InputStream createInputStream(final String input) {
        return new ByteArrayInputStream(input.getBytes());
    }

    // System.out의 출력을 스트림으로 캡처
    public static ByteArrayOutputStream captureOutput() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        return output;
    }

    // 입출력 스트림을 원래대로 복원
    public static void restoreSystemInputOutput(InputStream originalIn, PrintStream originalOut) {
        System.setIn(originalIn);
        System.setOut(originalOut);
        // PrintStream.close()는 IOException을 던지지 않으므로 try-catch 제거
        originalOut.close();
    }

    // 테스트 전 데이터 초기화 (필요 시)
    public static void clear() {
        // 데이터 초기화 메서드를 호출합니다.
        AppTest.clear();
    }
}

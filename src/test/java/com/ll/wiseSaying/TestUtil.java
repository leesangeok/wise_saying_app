package com.ll.wiseSaying;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

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
        originalOut.close();
    }

    // 테스트 전 데이터 초기화 (필요 시)
    public static void clear() {
        AppTest.clear();
    }
}

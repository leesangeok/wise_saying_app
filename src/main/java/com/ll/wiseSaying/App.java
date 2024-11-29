package com.ll.wiseSaying;

import com.ll.wiseSaying.controller.WiseSayingController;
import java.util.Scanner;

// 사용자 입력처리
public class App {
    private final WiseSayingController controller = new WiseSayingController();

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine().trim();

            if (cmd.equals("종료")) {
                break;
            }

            controller.handleCommand(cmd, scanner);
        }

        scanner.close();
    }

    // 테스트 모드를 위한 메서드 오버로딩 (필요 시)
    public void run(Scanner testScanner) {
        Scanner scanner = (testScanner != null) ? testScanner : new Scanner(System.in);

        // 테스트 모드일 때 데이터 초기화
//        if (testScanner != null) {
//            controller.clearData();
//        }

        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine().trim();

            if (cmd.equals("종료")) {
                break;
            }

            controller.handleCommand(cmd, scanner);
        }

        if (testScanner == null) {
            scanner.close();
        }
    }
}

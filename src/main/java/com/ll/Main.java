package com.ll;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        App app = new App();
        app.run();
    }
}

// --------------------1단계---------------------
//class App {
//    public void run() {
//        System.out.println("== 명언 앱 ==");
//
//        Scanner scanner = new Scanner(System.in);
//
//        while (true) {
//            System.out.print("명령) ");
//            String cmd = scanner.nextLine();
//
//            if (cmd.equals("종료")) {
//                break;
//            }
//        }
//
//        scanner.close();
//    }
//}

// --------------------2단계---------------------

//class App {
//    public void run() {
//        System.out.println("== 명언 앱 ==");
//
//        Scanner scanner = new Scanner(System.in);
//
//        while (true) {
//            System.out.print("명령) ");
//            String cmd = scanner.nextLine();
//
//
//            if (cmd.equals("등록")) {
//                System.out.print("명언 : ");
//                String wise = scanner.nextLine();
//                System.out.print("작가 : ");
//                String writer = scanner.nextLine();
//            }
//            if (cmd.equals("종료")) {
//                break;
//            }
//        }
//
//        scanner.close();
//    }
//}

//--------------------3,4단계---------------------

//class App {
//    public void run() {
//        System.out.println("== 명언 앱 ==");
//
//        Scanner scanner = new Scanner(System.in);
//        ArrayList<String> sayings = new ArrayList<>();
//
//        while (true) {
//            System.out.print("명령) ");
//            String cmd = scanner.nextLine();
//
//            if (cmd.equals("등록")) {
//                System.out.print("명언 : ");
//                String wise = scanner.nextLine();
//
//                System.out.print("작가 : ");
//                String writer = scanner.nextLine();
//
//                sayings.add(wise + " - " + writer);
//                System.out.println(sayings.size() + "번 명언이 등록되었습니다.");
//            }
//

//
//            if (cmd.equals("종료")) {
//                break;
//            }
//        }
//
//        scanner.close();
//    }
//}

//--------------------5단계---------------------
//class App {
//    public void run() {
//        System.out.println("== 명언 앱 ==");
//
//        Scanner scanner = new Scanner(System.in);
//        ArrayList<String> sayings = new ArrayList<>();
//
//        while (true) {
//            System.out.print("명령) ");
//            String cmd = scanner.nextLine();
//
//            if (cmd.equals("등록")) {
//                System.out.print("명언 : ");
//                String wise = scanner.nextLine();
//
//                System.out.print("작가 : ");
//                String writer = scanner.nextLine();
//
//                sayings.add(wise + " / " + writer);
//                System.out.println(sayings.size() + "번 명언이 등록되었습니다.");
//            }
//
//            if (cmd.equals("목록")) {
//                System.out.println("== 명언 목록 ==");
//                for (int i = 0; i < sayings.size(); i++) {
//                    System.out.println((i + 1) + " / " + sayings.get(i));
//                }
//            }
//
//            if (cmd.equals("종료")) {
//                break;
//            }
//        }
//
//        scanner.close();
//    }
//}

//--------------------6단계---------------------


//class App {
//    public void run() {
//        System.out.println("== 명언 앱 ==");
//
//        Scanner scanner = new Scanner(System.in);
//        ArrayList<String> sayings = new ArrayList<>();
//
//        while (true) {
//            System.out.print("명령) ");
//            String cmd = scanner.nextLine();
//
//            if (cmd.equals("등록")) {
//                System.out.print("명언 : ");
//                String wise = scanner.nextLine();
//
//                System.out.print("작가 : ");
//                String writer = scanner.nextLine();
//
//                sayings.add(wise + " / " + writer);
//                System.out.println(sayings.size() + "번 명언이 등록되었습니다.");
//            } else if (cmd.equals("목록")) {
//                if (sayings.isEmpty()) {
//                    System.out.println("등록된 명언이 없습니다.");
//                } else {
//                    System.out.println("== 명언 목록 ==");
//                    for (int i = 0; i < sayings.size(); i++) {
//                        System.out.println((i + 1) + " / " + sayings.get(i));
//                    }
//                }
//            } else if (cmd.equals("삭제")) {
//                System.out.print("삭제할 명언 번호 : ");
//                int deleteIndex = Integer.parseInt(scanner.nextLine()) - 1;
//
//                if (deleteIndex >= 0 && deleteIndex < sayings.size()) {
//                    System.out.println((deleteIndex + 1) + "번 명언이 삭제되었습니다: " + sayings.get(deleteIndex));
//                    sayings.remove(deleteIndex);
//                } else {
//                    System.out.println("잘못된 번호입니다. 다시 시도하세요.");
//                }
//            } else if (cmd.equals("종료")) {
//                break;
//            } else {
//                System.out.println("알 수 없는 명령입니다. 사용 가능한 명령: 등록, 목록, 삭제, 종료");
//            }
//        }
//
//        scanner.close();
//    }
//}


//--------------------7단계---------------------

class App {
    public void run() {
        System.out.println("== 명언 앱 ==");

        Scanner scanner = new Scanner(System.in);
        HashMap<Integer, String> sayings = new HashMap<>();
        int nextId = 1;

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();

            if (cmd.equals("등록")) {
                System.out.print("명언 : ");
                String wise = scanner.nextLine();

                System.out.print("작가 : ");
                String writer = scanner.nextLine();

                sayings.put(nextId, wise + " / " + writer);
                System.out.println(nextId + "번 명언이 등록되었습니다.");
                nextId++;
            } else if (cmd.equals("목록")) {
                if (sayings.isEmpty()) {
                    System.out.println("등록된 명언이 없습니다.");
                } else {
                    System.out.println("== 명언 목록 ==");
                    for (Integer id : sayings.keySet()) {
                        System.out.println(id + " / " + sayings.get(id));
                    }
                }
            } else if (cmd.equals("삭제")) {
                try {
                    System.out.print("삭제할 명언 번호 : ");
                    int deleteId = Integer.parseInt(scanner.nextLine());

                    if (sayings.containsKey(deleteId)) {
                        System.out.println(deleteId + "번 명언이 삭제되었습니다: " + sayings.get(deleteId));
                        sayings.remove(deleteId);
                    } else {
                        System.out.println("해당 번호의 명언이 존재하지 않습니다.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("올바른 번호를 입력하세요.");
                }
            } else if (cmd.equals("종료")) {
                break;
            } else {
                System.out.println("알 수 없는 명령입니다. 사용 가능한 명령: 등록, 목록, 삭제, 종료");
            }
        }

        scanner.close();
    }
}

package com.ll.wiseSaying;

// 애플리케이션 진입점
public class Main {
    public static void main(String[] args) {
        App app = new App();
        app.run();
    }
}








////--------------------10단계---------------------
////
//class App {
//    private static final String DB_FOLDER = "db/wiseSaying";
//    private static final String LAST_ID_FILE = DB_FOLDER + "/lastId.txt";
//    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
//    private static final String DATA_FILE = DB_FOLDER + "/data.json";
//
//    public void run() {
//        System.out.println("== 명언 앱 ==");
//
//        Scanner scanner = new Scanner(System.in);
//        int nextId = getLastId() + 1;
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
//                saveWiseSaying(nextId, wise, writer);
//                System.out.println(nextId + "번 명언이 등록되었습니다.");
//                nextId++;
//                saveLastId(nextId - 1);
//            } else if (cmd.equals("목록")) {
//                listWiseSayings();
//            } else if (cmd.equals("삭제")) {
//                try {
//                    System.out.print("삭제할 명언 번호 : ");
//                    int deleteId = Integer.parseInt(scanner.nextLine());
//                    deleteWiseSaying(deleteId);
//                } catch (NumberFormatException e) {
//                    System.out.println("올바른 번호를 입력하세요.");
//                }
//            } else if (cmd.equals("수정")) {
//                try {
//                    System.out.print("수정할 명언 번호 : ");
//                    int editId = Integer.parseInt(scanner.nextLine());
//
//                    if (editWiseSaying(editId, scanner)) {
//                        System.out.println(editId + "번 명언이 수정되었습니다.");
//                    }
//                } catch (NumberFormatException e) {
//                    System.out.println("올바른 번호를 입력하세요.");
//                }
//            } else if (cmd.equals("빌드")) {
//                buildDataFile();
//            } else if (cmd.equals("종료")) {
//                break;
//            } else {
//                System.out.println("알 수 없는 명령입니다. 사용 가능한 명령: 등록, 목록, 삭제, 수정, 종료");
//            }
//        }
//
//        scanner.close();
//    }
//
//    private int getLastId() {
//        File file = new File(LAST_ID_FILE);
//        if (!file.exists()) {
//            return 0;
//        }
//        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
//            return Integer.parseInt(reader.readLine());
//        } catch (IOException | NumberFormatException e) {
//            return 0;
//        }
//    }
//
//    private void saveLastId(int lastId) {
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LAST_ID_FILE))) {
//            writer.write(String.valueOf(lastId));
//        } catch (IOException e) {
//            System.out.println("마지막 ID 저장 중 오류가 발생했습니다.");
//        }
//    }
//
//    private void saveWiseSaying(int id, String wise, String writer) {
//        File folder = new File(DB_FOLDER);
//        if (!folder.exists()) {
//            folder.mkdirs();
//        }
//
//        String filePath = DB_FOLDER + "/" + id + ".json";
//        try (Writer fileWriter = new FileWriter(filePath)) {
//            gson.toJson(new WiseSaying(id, wise, writer), fileWriter);
//        } catch (IOException e) {
//            System.out.println("명언 저장 중 오류가 발생했습니다.");
//        }
//    }
//
//    private void listWiseSayings() {
//        File folder = new File(DB_FOLDER);
//        if (!folder.exists() || folder.listFiles() == null) {
//            System.out.println("등록된 명언이 없습니다.");
//            return;
//        }
//
//        File[] files = folder.listFiles((dir, name) -> name.endsWith(".json") && !name.equals("data.json"));
//        if (files == null || files.length == 0) {
//            System.out.println("등록된 명언이 없습니다.");
//            return;
//        }
//
//        System.out.println("== 명언 목록 ==");
//        for (File file : files) {
//            try (Reader reader = new FileReader(file)) {
//                WiseSaying wiseSaying = gson.fromJson(reader, WiseSaying.class);
//                System.out.println(wiseSaying.id + " / " + wiseSaying.author + " / " + wiseSaying.content);
//            } catch (IOException e) {
//                System.out.println(file.getName() + " 파일을 읽는 중 오류가 발생했습니다.");
//            } catch (JsonSyntaxException e) {
//                System.out.println(file.getName() + " 파일 형식이 잘못되었습니다.");
//            }
//        }
//    }
//
//
//    private void deleteWiseSaying(int id) {
//        File file = new File(DB_FOLDER + "/" + id + ".json");
//        if (file.exists() && file.delete()) {
//            System.out.println(id + "번 명언이 삭제되었습니다.");
//        } else {
//            System.out.println("해당 번호의 명언이 존재하지 않습니다.");
//        }
//    }
//
//    private boolean editWiseSaying(int id, Scanner scanner) {
//        File file = new File(DB_FOLDER + "/" + id + ".json");
//        if (!file.exists()) {
//            System.out.println("해당 번호의 명언이 존재하지 않습니다.");
//            return false;
//        }
//
//        try (Reader reader = new FileReader(file)) {
//            WiseSaying wiseSaying = gson.fromJson(reader, WiseSaying.class);
//
//            System.out.println("명언(기존) : " + wiseSaying.content);
//            System.out.print("명언 : ");
//            String newWise = scanner.nextLine();
//
//            System.out.println("작가(기존) : " + wiseSaying.author);
//            System.out.print("작가 : ");
//            String newWriter = scanner.nextLine();
//
//            saveWiseSaying(id, newWise, newWriter);
//            return true;
//        } catch (IOException e) {
//            System.out.println("명언 수정 중 오류가 발생했습니다.");
//            return false;
//        }
//    }
//
//    private void buildDataFile() {
//        File folder = new File(DB_FOLDER);
//        if (!folder.exists() || folder.listFiles() == null) {
//            System.out.println("등록된 명언이 없습니다.");
//            return;
//        }
//
//        // "data.json" 파일을 제외한 파일 목록을 가져옵니다.
//        File[] files = folder.listFiles((dir, name) -> name.endsWith(".json") && !name.equals("data.json"));
//        if (files == null || files.length == 0) {
//            System.out.println("등록된 명언이 없습니다.");
//            return;
//        }
//
//        List<WiseSaying> wiseSayings = new ArrayList<>();
//        for (File file : files) {
//            try (Reader reader = new FileReader(file)) {
//                WiseSaying wiseSaying = gson.fromJson(reader, WiseSaying.class);
//                wiseSayings.add(wiseSaying);
//            } catch (IOException e) {
//                System.out.println(file.getName() + " 파일을 읽는 중 오류가 발생했습니다.");
//            } catch (JsonSyntaxException e) {
//                System.out.println(file.getName() + " 파일 형식이 잘못되었습니다.");
//            }
//        }
//
//        // "data.json" 파일 생성
//        try (Writer writer = new FileWriter(DATA_FILE)) {
//            gson.toJson(wiseSayings, writer);
//            System.out.println("data.json 파일이 생성되었습니다.");
//        } catch (IOException e) {
//            System.out.println("data.json 파일 생성 중 오류가 발생했습니다.");
//        }
//    }
//    class WiseSaying {
//        int id;
//        String content;
//        String author;
//
//        WiseSaying(int id, String content, String author) {
//            this.id = id;
//            this.content = content;
//            this.author = author;
//        }
//    }
//}
//
//
//
//
//
//// --------------------1단계---------------------
////class App {
////    public void run() {
////        System.out.println("== 명언 앱 ==");
////
////        Scanner scanner = new Scanner(System.in);
////
////        while (true) {
////            System.out.print("명령) ");
////            String cmd = scanner.nextLine();
////
////            if (cmd.equals("종료")) {
////                break;
////            }
////        }
////
////        scanner.close();
////    }
////}
//
//// --------------------2단계---------------------
//
////class App {
////    public void run() {
////        System.out.println("== 명언 앱 ==");
////
////        Scanner scanner = new Scanner(System.in);
////
////        while (true) {
////            System.out.print("명령) ");
////            String cmd = scanner.nextLine();
////
////
////            if (cmd.equals("등록")) {
////                System.out.print("명언 : ");
////                String wise = scanner.nextLine();
////                System.out.print("작가 : ");
////                String writer = scanner.nextLine();
////            }
////            if (cmd.equals("종료")) {
////                break;
////            }
////        }
////
////        scanner.close();
////    }
////}
//
////--------------------3,4단계---------------------
//
////class App {
////    public void run() {
////        System.out.println("== 명언 앱 ==");
////
////        Scanner scanner = new Scanner(System.in);
////        ArrayList<String> sayings = new ArrayList<>();
////
////        while (true) {
////            System.out.print("명령) ");
////            String cmd = scanner.nextLine();
////
////            if (cmd.equals("등록")) {
////                System.out.print("명언 : ");
////                String wise = scanner.nextLine();
////
////                System.out.print("작가 : ");
////                String writer = scanner.nextLine();
////
////                sayings.add(wise + " - " + writer);
////                System.out.println(sayings.size() + "번 명언이 등록되었습니다.");
////            }
////
//
////
////            if (cmd.equals("종료")) {
////                break;
////            }
////        }
////
////        scanner.close();
////    }
////}
//
////--------------------5단계---------------------
////class App {
////    public void run() {
////        System.out.println("== 명언 앱 ==");
////
////        Scanner scanner = new Scanner(System.in);
////        ArrayList<String> sayings = new ArrayList<>();
////
////        while (true) {
////            System.out.print("명령) ");
////            String cmd = scanner.nextLine();
////
////            if (cmd.equals("등록")) {
////                System.out.print("명언 : ");
////                String wise = scanner.nextLine();
////
////                System.out.print("작가 : ");
////                String writer = scanner.nextLine();
////
////                sayings.add(wise + " / " + writer);
////                System.out.println(sayings.size() + "번 명언이 등록되었습니다.");
////            }
////
////            if (cmd.equals("목록")) {
////                System.out.println("== 명언 목록 ==");
////                for (int i = 0; i < sayings.size(); i++) {
////                    System.out.println((i + 1) + " / " + sayings.get(i));
////                }
////            }
////
////            if (cmd.equals("종료")) {
////                break;
////            }
////        }
////
////        scanner.close();
////    }
////}
//
////--------------------6단계---------------------
//
//
////class App {
////    public void run() {
////        System.out.println("== 명언 앱 ==");
////
////        Scanner scanner = new Scanner(System.in);
////        ArrayList<String> sayings = new ArrayList<>();
////
////        while (true) {
////            System.out.print("명령) ");
////            String cmd = scanner.nextLine();
////
////            if (cmd.equals("등록")) {
////                System.out.print("명언 : ");
////                String wise = scanner.nextLine();
////
////                System.out.print("작가 : ");
////                String writer = scanner.nextLine();
////
////                sayings.add(wise + " / " + writer);
////                System.out.println(sayings.size() + "번 명언이 등록되었습니다.");
////            } else if (cmd.equals("목록")) {
////                if (sayings.isEmpty()) {
////                    System.out.println("등록된 명언이 없습니다.");
////                } else {
////                    System.out.println("== 명언 목록 ==");
////                    for (int i = 0; i < sayings.size(); i++) {
////                        System.out.println((i + 1) + " / " + sayings.get(i));
////                    }
////                }
////            } else if (cmd.equals("삭제")) {
////                System.out.print("삭제할 명언 번호 : ");
////                int deleteIndex = Integer.parseInt(scanner.nextLine()) - 1;
////
////                if (deleteIndex >= 0 && deleteIndex < sayings.size()) {
////                    System.out.println((deleteIndex + 1) + "번 명언이 삭제되었습니다: " + sayings.get(deleteIndex));
////                    sayings.remove(deleteIndex);
////                } else {
////                    System.out.println("잘못된 번호입니다. 다시 시도하세요.");
////                }
////            } else if (cmd.equals("종료")) {
////                break;
////            } else {
////                System.out.println("알 수 없는 명령입니다. 사용 가능한 명령: 등록, 목록, 삭제, 종료");
////            }
////        }
////
////        scanner.close();
////    }
////}
//
//
////--------------------7단계---------------------
////class App {
////    public void run() {
////        System.out.println("== 명언 앱 ==");
////
////        Scanner scanner = new Scanner(System.in);
////        HashMap<Integer, String> sayings = new HashMap<>();
////        int nextId = 1;
////
////        while (true) {
////            System.out.print("명령) ");
////            String cmd = scanner.nextLine();
////
////            if (cmd.equals("등록")) {
////                System.out.print("명언 : ");
////                String wise = scanner.nextLine();
////
////                System.out.print("작가 : ");
////                String writer = scanner.nextLine();
////
////                sayings.put(nextId, wise + " / " + writer);
////                System.out.println(nextId + "번 명언이 등록되었습니다.");
////                nextId++;
////            } else if (cmd.equals("목록")) {
////                if (sayings.isEmpty()) {
////                    System.out.println("등록된 명언이 없습니다.");
////                } else {
////                    System.out.println("== 명언 목록 ==");
////                    for (Integer id : sayings.keySet()) {
////                        System.out.println(id + " / " + sayings.get(id));
////                    }
////                }
////            } else if (cmd.equals("삭제")) {
////                try {
////                    System.out.print("삭제할 명언 번호 : ");
////                    int deleteId = Integer.parseInt(scanner.nextLine());
////
////                    if (sayings.containsKey(deleteId)) {
////                        System.out.println(deleteId + "번 명언이 삭제되었습니다: " + sayings.get(deleteId));
////                        sayings.remove(deleteId);
////                    } else {
////                        System.out.println("해당 번호의 명언이 존재하지 않습니다.");
////                    }
////                } catch (NumberFormatException e) {
////                    System.out.println("올바른 번호를 입력하세요.");
////                }
////            } else if (cmd.equals("종료")) {
////                break;
////            } else if (cmd.equals("수정")) {
////                System.out.print("수정할 명언 번호 : ");
////                int editId = Integer.parseInt(scanner.nextLine());
////
////                if(sayings.containsKey(editId)){
////
////                }
////            } else {
////                System.out.println("알 수 없는 명령입니다. 사용 가능한 명령: 등록, 목록, 삭제, 종료");
////            }
////        }
////
////        scanner.close();
////    }
////}
////
//
//
//
//
//
//
////--------------------8단계---------------------
//
////class App {
////    public void run() {
////        System.out.println("== 명언 앱 ==");
////
////        Scanner scanner = new Scanner(System.in);
////        HashMap<Integer, String[]> sayings = new HashMap<>(); // HashMap 값 타입을 String[]로 변경
////        int nextId = 1;
////
////        while (true) {
////            System.out.print("명령) ");
////            String cmd = scanner.nextLine();
////
////            if (cmd.equals("등록")) {
////                System.out.print("명언 : ");
////                String wise = scanner.nextLine();
////
////                System.out.print("작가 : ");
////                String writer = scanner.nextLine();
////
////                sayings.put(nextId, new String[]{wise, writer}); // 명언과 작가를 배열로 저장
////                System.out.println(nextId + "번 명언이 등록되었습니다.");
////                nextId++;
////            } else if (cmd.equals("목록")) {
////                if (sayings.isEmpty()) {
////                    System.out.println("등록된 명언이 없습니다.");
////                } else {
////                    System.out.println("== 명언 목록 ==");
////                    for (Integer id : sayings.keySet()) {
////                        String[] data = sayings.get(id);
////                        System.out.println(id + " / " + data[0] + " / " + data[1]);
////                    }
////                }
////            } else if (cmd.equals("삭제")) {
////                try {
////                    System.out.print("삭제할 명언 번호 : ");
////                    int deleteId = Integer.parseInt(scanner.nextLine());
////
////                    if (sayings.containsKey(deleteId)) {
////                        System.out.println(deleteId + "번 명언이 삭제되었습니다: " + sayings.get(deleteId)[0]);
////                        sayings.remove(deleteId);
////                    } else {
////                        System.out.println("해당 번호의 명언이 존재하지 않습니다.");
////                    }
////                } catch (NumberFormatException e) {
////                    System.out.println("올바른 번호를 입력하세요.");
////                }
////            } else if (cmd.equals("수정")) {
////                try {
////                    System.out.print("수정할 명언 번호 : ");
////                    int editId = Integer.parseInt(scanner.nextLine());
////
////                    if (sayings.containsKey(editId)) {
////                        String[] currentData = sayings.get(editId);
////
////                        System.out.println("명언(기존) : " + currentData[0]);
////                        System.out.print("명언 : ");
////                        String newWise = scanner.nextLine();
////
////                        System.out.println("작가(기존) : " + currentData[1]);
////                        System.out.print("작가 : ");
////                        String newWriter = scanner.nextLine();
////
////                        sayings.put(editId, new String[]{newWise, newWriter});
////                        System.out.println(editId + "번 명언이 수정되었습니다.");
////                    } else {
////                        System.out.println(editId + "번 명언은 존재하지 않습니다.");
////                    }
////                } catch (Exception e) {
////                    System.out.println("올바른 명령어 형식을 입력하세요.");
////                }
////            } else if (cmd.equals("종료")) {
////                break;
////            } else {
////                System.out.println("알 수 없는 명령입니다. 사용 가능한 명령: 등록, 목록, 삭제, 수정, 종료");
////            }
////        }
////
////        scanner.close();
////    }
////}
//
//
////--------------------9단계---------------------
////class App {
////    private static final String DB_FOLDER = "db/wiseSaying";
////    private static final String LAST_ID_FILE = DB_FOLDER + "/lastId.txt";
////    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
////
////    public void run() {
////        System.out.println("== 명언 앱 ==");
////
////        Scanner scanner = new Scanner(System.in);
////        int nextId = getLastId() + 1;
////
////        while (true) {
////            System.out.print("명령) ");
////            String cmd = scanner.nextLine();
////
////            if (cmd.equals("등록")) {
////                System.out.print("명언 : ");
////                String wise = scanner.nextLine();
////
////                System.out.print("작가 : ");
////                String writer = scanner.nextLine();
////
////                saveWiseSaying(nextId, wise, writer);
////                System.out.println(nextId + "번 명언이 등록되었습니다.");
////                nextId++;
////                saveLastId(nextId - 1);
////            } else if (cmd.equals("목록")) {
////                listWiseSayings();
////            } else if (cmd.equals("삭제")) {
////                try {
////                    System.out.print("삭제할 명언 번호 : ");
////                    int deleteId = Integer.parseInt(scanner.nextLine());
////                    deleteWiseSaying(deleteId);
////                } catch (NumberFormatException e) {
////                    System.out.println("올바른 번호를 입력하세요.");
////                }
////            } else if (cmd.equals("수정")) {
////                try {
////                    System.out.print("수정할 명언 번호 : ");
////                    int editId = Integer.parseInt(scanner.nextLine());
////
////                    if (editWiseSaying(editId, scanner)) {
////                        System.out.println(editId + "번 명언이 수정되었습니다.");
////                    }
////                } catch (NumberFormatException e) {
////                    System.out.println("올바른 번호를 입력하세요.");
////                }
////            } else if (cmd.equals("종료")) {
////                break;
////            } else {
////                System.out.println("알 수 없는 명령입니다. 사용 가능한 명령: 등록, 목록, 삭제, 수정, 종료");
////            }
////        }
////
////        scanner.close();
////    }
////
////    private int getLastId() {
////        File file = new File(LAST_ID_FILE);
////        if (!file.exists()) {
////            return 0;
////        }
////        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
////            return Integer.parseInt(reader.readLine());
////        } catch (IOException | NumberFormatException e) {
////            return 0;
////        }
////    }
////
////    private void saveLastId(int lastId) {
////        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LAST_ID_FILE))) {
////            writer.write(String.valueOf(lastId));
////        } catch (IOException e) {
////            System.out.println("마지막 ID 저장 중 오류가 발생했습니다.");
////        }
////    }
////
////    private void saveWiseSaying(int id, String wise, String writer) {
////        File folder = new File(DB_FOLDER);
////        if (!folder.exists()) {
////            folder.mkdirs();
////        }
////
////        String filePath = DB_FOLDER + "/" + id + ".json";
////        try (Writer fileWriter = new FileWriter(filePath)) {
////            gson.toJson(new WiseSaying(id, wise, writer), fileWriter);
////        } catch (IOException e) {
////            System.out.println("명언 저장 중 오류가 발생했습니다.");
////        }
////    }
////
////    private void listWiseSayings() {
////        File folder = new File(DB_FOLDER);
////        if (!folder.exists() || folder.listFiles() == null) {
////            System.out.println("등록된 명언이 없습니다.");
////            return;
////        }
////
////        File[] files = folder.listFiles((dir, name) -> name.endsWith(".json"));
////        if (files == null || files.length == 0) {
////            System.out.println("등록된 명언이 없습니다.");
////            return;
////        }
////
////        System.out.println("== 명언 목록 ==");
////        for (File file : files) {
////            try (Reader reader = new FileReader(file)) {
////                WiseSaying wiseSaying = gson.fromJson(reader, WiseSaying.class);
////                System.out.println(wiseSaying.id + " / " + wiseSaying.author + " / " + wiseSaying.content);
////            } catch (IOException e) {
////                System.out.println(file.getName() + " 파일을 읽는 중 오류가 발생했습니다.");
////            }
////        }
////    }
////
////    private void deleteWiseSaying(int id) {
////        File file = new File(DB_FOLDER + "/" + id + ".json");
////        if (file.exists() && file.delete()) {
////            System.out.println(id + "번 명언이 삭제되었습니다.");
////        } else {
////            System.out.println("해당 번호의 명언이 존재하지 않습니다.");
////        }
////    }
////
////    private boolean editWiseSaying(int id, Scanner scanner) {
////        File file = new File(DB_FOLDER + "/" + id + ".json");
////        if (!file.exists()) {
////            System.out.println("해당 번호의 명언이 존재하지 않습니다.");
////            return false;
////        }
////
////        try (Reader reader = new FileReader(file)) {
////            WiseSaying wiseSaying = gson.fromJson(reader, WiseSaying.class);
////
////            System.out.println("명언(기존) : " + wiseSaying.content);
////            System.out.print("명언 : ");
////            String newWise = scanner.nextLine();
////
////            System.out.println("작가(기존) : " + wiseSaying.author);
////            System.out.print("작가 : ");
////            String newWriter = scanner.nextLine();
////
////            saveWiseSaying(id, newWise, newWriter);
////            return true;
////        } catch (IOException e) {
////            System.out.println("명언 수정 중 오류가 발생했습니다.");
////            return false;
////        }
////    }
////
////    class WiseSaying {
////        int id;
////        String content;
////        String author;
////
////        WiseSaying(int id, String content, String author) {
////            this.id = id;
////            this.content = content;
////            this.author = author;
////        }
////    }
////}
//

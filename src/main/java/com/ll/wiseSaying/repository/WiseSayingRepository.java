package com.ll.wiseSaying.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.ll.wiseSaying.domain.WiseSaying;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// 데이터의 조회, 생성, 수정, 삭제를 담당
public class WiseSayingRepository {
    private final String DB_FOLDER = "src/main/resources/db/wiseSaying";
    private final String LAST_ID_FILE = DB_FOLDER + "/lastId.txt";
    private final String DATA_FILE = DB_FOLDER + "/data.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public void save(WiseSaying wiseSaying) {
        File folder = new File(DB_FOLDER);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        String filePath = DB_FOLDER + "/" + wiseSaying.getId() + ".json";
        try (Writer fileWriter = new FileWriter(filePath)) {
            gson.toJson(wiseSaying, fileWriter);
        } catch (IOException e) {
            System.out.println("명언 저장 중 오류가 발생했습니다.");
        }
    }

    public WiseSaying findById(int id) {
        String filePath = DB_FOLDER + "/" + id + ".json";
        File file = new File(filePath);
        if (!file.exists()) {
            return null;
        }

        try (Reader reader = new FileReader(file)) {
            return gson.fromJson(reader, WiseSaying.class);
        } catch (IOException e) {
            return null;
        }
    }

    public List<WiseSaying> findAll() {
        List<WiseSaying> wiseSayings = new ArrayList<>();
        File folder = new File(DB_FOLDER);
        if (!folder.exists()) {
            return wiseSayings;
        }

        File[] files = folder.listFiles((dir, name) -> name.endsWith(".json") && !name.equals("data.json"));
        if (files == null) {
            return wiseSayings;
        }

        for (File file : files) {
            try (Reader reader = new FileReader(file)) {
                WiseSaying wiseSaying = gson.fromJson(reader, WiseSaying.class);
                wiseSayings.add(wiseSaying);
            } catch (IOException | JsonSyntaxException e) {
                System.out.println(file.getName() + " 파일을 읽는 중 오류가 발생했습니다.");
            }
        }

        return wiseSayings;
    }

    public boolean deleteById(int id) {
        String filePath = DB_FOLDER + "/" + id + ".json";
        File file = new File(filePath);
        return file.exists() && file.delete();
    }

    public void update(WiseSaying wiseSaying) {
        save(wiseSaying);
    }

    public int getLastId() {
        File file = new File(LAST_ID_FILE);
        if (!file.exists()) {
            return 0;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            return 0;
        }
    }

    public void saveLastId(int lastId) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LAST_ID_FILE))) {
            writer.write(String.valueOf(lastId));
        } catch (IOException e) {
            System.out.println("마지막 ID 저장 중 오류가 발생했습니다.");
        }
    }

    public void buildDataJson() {
        List<WiseSaying> wiseSayings = findAll();

        try (Writer writer = new FileWriter(DATA_FILE)) {
            gson.toJson(wiseSayings, writer);
        } catch (IOException e) {
            System.out.println("data.json 파일 생성 중 오류가 발생했습니다.");
        }
    }

    // 데이터 초기화 메서드 추가
    public void clearData() {
        File folder = new File(DB_FOLDER);
        if (folder.exists()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (!file.getName().equals("lastId.txt") && !file.getName().equals("data.json")) {
                        file.delete();
                    }
                }
            }
        }
        // lastId.txt와 data.json 초기화
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LAST_ID_FILE))) {
            writer.write("0");
        } catch (IOException e) {
            System.out.println("lastId.txt 초기화 중 오류가 발생했습니다.");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
            writer.write("[]");
        } catch (IOException e) {
            System.out.println("data.json 초기화 중 오류가 발생했습니다.");
        }
    }
}

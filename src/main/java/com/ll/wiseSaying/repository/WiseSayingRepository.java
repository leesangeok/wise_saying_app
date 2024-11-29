package com.ll.wiseSaying.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.ll.wiseSaying.domain.WiseSaying;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class WiseSayingRepository {
    private final String DB_FOLDER = "src/main/resources/db/wiseSaying";
    private final String LAST_ID_FILE = DB_FOLDER + "/lastId.txt";
    private final String DATA_FILE = DB_FOLDER + "/data.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private List<WiseSaying> wiseSayings;
    private int lastId;

    public WiseSayingRepository() {
        wiseSayings = new ArrayList<>();
        lastId = 0;
    }
    // 명언 저장
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

    // 명언 ID로 조회
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

    // 전체 명언 목록 반환 (검색어 및 페이징 처리)
    public List<WiseSaying> findAll(String searchQuery, int page, int pageSize) {
        List<WiseSaying> wiseSayings = loadAllWiseSayings();

        // 검색어가 있을 경우 필터링
        if (searchQuery != null && !searchQuery.isEmpty()) {
            wiseSayings = wiseSayings.stream()
                    .filter(wiseSaying -> wiseSaying.getContent().contains(searchQuery) || wiseSaying.getAuthor().contains(searchQuery))
                    .collect(Collectors.toList());
        }

        // 최신순으로 정렬
        wiseSayings.sort(Comparator.comparingInt(WiseSaying::getId).reversed());

        // 페이징 처리
        int fromIndex = (page - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, wiseSayings.size());

        if (fromIndex >= wiseSayings.size()) {
            return new ArrayList<>();
        }

        return wiseSayings.subList(fromIndex, toIndex);
    }

    // 모든 명언 목록 반환 (검색어 및 페이징 없이)
    public List<WiseSaying> findAll() {
        return loadAllWiseSayings();
    }

    // 명언 삭제
    public boolean deleteById(int id) {
        String filePath = DB_FOLDER + "/" + id + ".json";
        File file = new File(filePath);
        return file.exists() && file.delete();
    }

    // 명언 수정
    public void update(WiseSaying wiseSaying) {
        save(wiseSaying);
    }

    // 마지막 ID 가져오기
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

    // 마지막 ID 저장
    public void saveLastId(int lastId) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LAST_ID_FILE))) {
            writer.write(String.valueOf(lastId));
        } catch (IOException e) {
            System.out.println("마지막 ID 저장 중 오류가 발생했습니다.");
        }
    }

    // data.json 파일 생성
    public void buildDataJson() {
        List<WiseSaying> wiseSayings = findAll();

        try (Writer writer = new FileWriter(DATA_FILE)) {
            gson.toJson(wiseSayings, writer);
        } catch (IOException e) {
            System.out.println("data.json 파일 생성 중 오류가 발생했습니다.");
        }
    }

    // 데이터 초기화
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

    // 총 명언 수 반환 (검색어를 반영한 개수)
    public int getTotalCount(String searchQuery) {
        List<WiseSaying> wiseSayings = loadAllWiseSayings();

        if (searchQuery != null && !searchQuery.isEmpty()) {
            wiseSayings = wiseSayings.stream()
                    .filter(wiseSaying -> wiseSaying.getContent().contains(searchQuery) || wiseSaying.getAuthor().contains(searchQuery))
                    .collect(Collectors.toList());
        }

        return wiseSayings.size();
    }

    // 모든 명언을 로드하는 내부 메서드
    private List<WiseSaying> loadAllWiseSayings() {
        List<WiseSaying> wiseSayings = new ArrayList<>();
        File folder = new File(DB_FOLDER);
        if (!folder.exists()) {
            return wiseSayings;
        }

        File[] files = folder.listFiles((dir, name) -> name.endsWith(".json") && !name.equals("data.json") && !name.equals("lastId.txt"));
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


}

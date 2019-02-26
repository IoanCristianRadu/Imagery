package com.project.imagery.singletons;

import java.util.HashMap;

import static com.project.imagery.journal.JournalActivity.NUMBER_OF_POSTS;

public class IndexHelper {
    HashMap<Integer, Integer> reverseIndex = new HashMap<>();

    private static final IndexHelper instance = new IndexHelper();

    public static IndexHelper getInstance() {
        return instance;
    }

    private IndexHelper() {
        for (int i = 0; i < NUMBER_OF_POSTS; i++) {
            reverseIndex.put(i, NUMBER_OF_POSTS - i - 1);
        }
    }

    public HashMap<Integer, Integer> getReverseIndex() {
        return reverseIndex;
    }

    public HashMap<Integer, Integer> getReverseIndex(int size) {
        reverseIndex.clear();
        for (int i = 0; i < size; i++) {
            reverseIndex.put(i, size - i - 1);
        }
        return reverseIndex;
    }
}

package com.project.imagery.classes;

import java.util.HashMap;
import static com.project.imagery.Journal.NUMBER_OF_POSTS;

public class Helper {
    HashMap<Integer,Integer> reverseIndex = new HashMap<>();

    private static final Helper ourInstance = new Helper();
    public static Helper getInstance() {
        return ourInstance;
    }
    private Helper() {
        for(int i = 0; i< NUMBER_OF_POSTS; i++){
            reverseIndex.put(i, NUMBER_OF_POSTS-i-1);
        }
    }

    public HashMap<Integer,Integer> getReverseIndex(){
        return reverseIndex;
    }
}

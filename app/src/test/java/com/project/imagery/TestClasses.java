package com.project.imagery;

import com.project.imagery.singletons.IndexHelper;

import org.junit.Test;

import static org.junit.Assert.*;
import static com.project.imagery.journal.JournalActivity.NUMBER_OF_POSTS;

public class TestClasses {
    @Test
    public void HelperTest(){
        IndexHelper indexHelper = IndexHelper.getInstance();
        int position = indexHelper.getReverseIndex().get(0);
        assertEquals(position,99);
        position = indexHelper.getReverseIndex().get(50);
        assertEquals(position,49);
        position = indexHelper.getReverseIndex().get(99);
        assertEquals(position,0);
        position = indexHelper.getReverseIndex().get(7);
        assertEquals(position,92);
        position = indexHelper.getReverseIndex().get(85);
        assertEquals(position,14);
    }
    @Test
    public void HelperSizeTest(){
        IndexHelper indexHelper = IndexHelper.getInstance();
        assertEquals(indexHelper.getReverseIndex().size(), NUMBER_OF_POSTS);
    }

    @Test
    public void HelperTestNull1(){
        IndexHelper indexHelper = IndexHelper.getInstance();
        try{
            int position = indexHelper.getReverseIndex().get(NUMBER_OF_POSTS);
            assertFalse(true);
        } catch (NullPointerException e){
            assertTrue(true);
        }
    }

    @Test
    public void HelperTestNull2(){
        IndexHelper indexHelper = IndexHelper.getInstance();
        try{
            int position = indexHelper.getReverseIndex().get(-1);
            assertFalse(true);
        } catch (NullPointerException e){
            assertTrue(true);
        }
    }
}

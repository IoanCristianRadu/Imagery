package com.project.imagery;

import com.project.imagery.singletons.IndexSingleton;

import org.junit.Test;

import static com.project.imagery.journal.JournalActivity.NUMBER_OF_POSTS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestClasses {
    @Test
    public void HelperTest(){
        try {
            IndexSingleton indexSingleton = IndexSingleton.getInstance();
            int position = indexSingleton.getReverseIndex().get(0);
            assertEquals(position, 99);
            position = indexSingleton.getReverseIndex().get(50);
            assertEquals(position, 49);
            position = indexSingleton.getReverseIndex().get(99);
            assertEquals(position, 0);
            position = indexSingleton.getReverseIndex().get(7);
            assertEquals(position, 92);
            position = indexSingleton.getReverseIndex().get(85);
            assertEquals(position, 14);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void HelperSizeTest(){
        IndexSingleton indexSingleton = IndexSingleton.getInstance();
        assertEquals(indexSingleton.getReverseIndex().size(), NUMBER_OF_POSTS);
    }

    @Test
    public void HelperTestNull1(){
        IndexSingleton indexSingleton = IndexSingleton.getInstance();
        try{
            int position = indexSingleton.getReverseIndex().get(NUMBER_OF_POSTS);
            fail();
        } catch (NullPointerException e){
            assertTrue(true);
        }
    }

    @Test
    public void HelperTestNull2(){
        IndexSingleton indexSingleton = IndexSingleton.getInstance();
        try{
            int position = indexSingleton.getReverseIndex().get(-1);
            fail();
        } catch (NullPointerException e){
            assertTrue(true);
        }
    }
}

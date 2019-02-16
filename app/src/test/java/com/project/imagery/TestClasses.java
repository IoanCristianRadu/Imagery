package com.project.imagery;

import com.project.imagery.classes.Helper;

import org.junit.Test;

import static org.junit.Assert.*;
import static com.project.imagery.journal.Journal.NUMBER_OF_POSTS;

public class TestClasses {
    @Test
    public void HelperTest(){
        Helper helper = Helper.getInstance();
        int position = helper.getReverseIndex().get(0);
        assertEquals(position,99);
        position = helper.getReverseIndex().get(50);
        assertEquals(position,49);
        position = helper.getReverseIndex().get(99);
        assertEquals(position,0);
        position = helper.getReverseIndex().get(7);
        assertEquals(position,92);
        position = helper.getReverseIndex().get(85);
        assertEquals(position,14);
    }
    @Test
    public void HelperSizeTest(){
        Helper helper = Helper.getInstance();
        assertEquals(helper.getReverseIndex().size(), NUMBER_OF_POSTS);
    }

    @Test
    public void HelperTestNull1(){
        Helper helper = Helper.getInstance();
        try{
            int position = helper.getReverseIndex().get(NUMBER_OF_POSTS);
            assertEquals(position,99);
        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    @Test
    public void HelperTestNull2(){
        Helper helper = Helper.getInstance();
        try{
            int position = helper.getReverseIndex().get(-1);
            assertEquals(position,99);
        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }
}

package org.test.logic;

/**
 * Created by abara on 08.11.2016.
 */
public enum PageName {
    MENU("menu"),
    //---
    PROFILE("profile"),
    //------
        MATERIALS("materials"),
        INBOX("inbox"),
        SCHEDULE("schedule"),
        COURSES("courses"),
    //------
    FRIENDS("friends"),
    SEARCH("search"),
    CLASS("class"),
    TASKS("tasks"),;

    private String path;

    PageName(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return path;
    }
}

package com.intellij.action.data;

/**
 * @Description data
 * @Author Daw
 * @Date 2020-10-30 16:42
 * @Version 1.0
 **/
public class DataCenter {

    /**
     * tag text
     */
    public static String TAG_LIST = null;

    /**
     * last tag
     */
    public static String LAST_TAG_TEXT = null;

    public static String GIT_ACCOUNT = null;

    public static String GIT_PASSWORD = null;


    /**
     * rest data
     */
    public static void reset() {
        DataCenter.TAG_LIST = null;
        DataCenter.LAST_TAG_TEXT = null;
    }
}

package com.intellij.action.data;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

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

    /**
     * 配置对象
     */
    public static Config config;

    /**
     * 重置配置对象
     */
    public static void resetConfig(){
        DataCenter.config = null;
    }

    /**
     * 读取配置文件
     * @return
     */
    public static Config readConfig(){
        Config config = FishConfig.readConfig();
        DataCenter.config = config;
        return config;
    }

    /**
     * 写入配置文件
     * @param account
     * @param password
     * @return
     */
    public static boolean writeConfig(String account,String password){
        FishConfig.writeConfig(account,password);
        return true;
    }

    /**
     * rest data
     */
    public static void reset() {
        DataCenter.TAG_LIST = null;
        DataCenter.LAST_TAG_TEXT = null;
    }


    public static class Config{

        public String at;

        public String pw;

        public String prefix;

        public String getAt() {
            return at;
        }

        public void setAt(String at) {
            this.at = at;
        }

        public String getPw() {
            return pw;
        }

        public void setPw(String pw) {
            this.pw = pw;
        }

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        @Override
        public String toString() {
            return JSONObject.toJSONString(this, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat);
        }
    }
}

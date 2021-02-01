package com.intellij.action.data;

import com.alibaba.fastjson.JSONObject;
import com.intellij.openapi.ui.Messages;

import java.io.*;

/**
 * @Description grilledFish
 * @Author Daw
 * @Date 2021-01-31 10:47
 * @Version 1.0
 **/
public class FishConfig {

    public static File fishConfig;

    /**
     * init configuration
     * @param basePath
     */
    public static void init(String basePath){
        fishConfig = new File(basePath+"/.git/fish_config");
        if (!fishConfig.exists()) {
            //grilled fish config
            try {
                fishConfig.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                Messages.showDialog("create fish config error!!","Error", new String[]{"Close"},0,null);
            }
        }
    }

    /**
     * Cache git account and password!
     * @param account
     * @param password
     */
    public static void writeConfig(String account,String password){
        try {
            DataCenter.Config config = new DataCenter.Config();
            config.setAt(account);
            config.setPw(password);
            FileOutputStream fos = new FileOutputStream(fishConfig);
            fos.write(config.toString().getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Messages.showDialog("write fish config error!!","Error", new String[]{"Close"},0,null);
        } catch (IOException e) {
            e.printStackTrace();
            Messages.showDialog("write fish config error!!","Error", new String[]{"Close"},0,null);
        }
    }


    /**
     * Reading git account and password!
     * @return
     */
    public static DataCenter.Config readConfig(){
        try {
            FileInputStream fis = new FileInputStream(fishConfig);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuffer sb = new StringBuffer();
            String str ;
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            if(sb.toString().isEmpty()){
                return null;
            }
            DataCenter.Config config = JSONObject.parseObject(sb.toString(), DataCenter.Config.class);
            return config;
        } catch (Exception e) {
            e.printStackTrace();
            Messages.showDialog("read fish config error!!","Error", new String[]{"Close"},0,null);
        }
        return null;
    }

}

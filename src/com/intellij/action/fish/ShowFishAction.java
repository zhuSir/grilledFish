package com.intellij.action.fish;

import com.alibaba.fastjson.JSONObject;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.MessageType;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;

/**
 * @Description Give me fishes
 * @Author Daw
 * @Date 2020-11-03 21:25
 * @Version 1.0
 **/
public class ShowFishAction extends AnAction {

    /**
     * Implement method
     */
    @Override
    public void actionPerformed(AnActionEvent e) {
        //Get notification
        NotificationGroup notificationGroup = new NotificationGroup("showFish", NotificationDisplayType.BALLOON, false);
        //Show Warning message
        Notification notification = notificationGroup.createNotification(getContent(), MessageType.WARNING);
        Notifications.Bus.notify(notification);
        //pop window
        //Messages.showDialog(getContent(),"ENJOY", new String[]{"Close"},0,null);
    }

    /**
     * Get poisonous fish
     * @return
     */
    public static String getContent(){
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet get = new HttpGet("https://api.nextrt.com/V1/Dutang");
        try {
            CloseableHttpResponse response = httpclient.execute(get);
            if(response.getStatusLine().getStatusCode() == 200){
                HttpEntity entity = response.getEntity();
                String responseData = EntityUtils.toString(entity);
                JSONObject jsonObject = JSONObject.parseObject(responseData);
                String content = jsonObject.getJSONArray("data").getJSONObject(0).getString("content");
                return content;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Today haven't fish!";
        }
        return null;
    }

}

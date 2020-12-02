package com.intellij.action.git;

import com.intellij.action.data.DataCenter;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import groovy.util.logging.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Description pop window action
 * @Author Daw
 * @Date 2020-11-04 10:59
 * @Version 1.0
 **/
@Slf4j
public class PopupAction extends AnAction {

    /**
     * log output
     */
    Logger log = LoggerFactory.getLogger(this.getClass().getName());

    /**
     * JGit Object
     */
    Git git = null;

    @Override
    public void actionPerformed(AnActionEvent e) {
        log.info(" === >> appending node tag");
        //Get project root path
        String basePath = e.getProject().getBasePath();
        try{
            if(this.git == null){
                git = Git.open( new File(basePath+"/.git"));
            }
            //提交代码到本地仓库
            //RevCommit commit = git.commit().setMessage("initial commit").call();

            List<Ref> tags = git.tagList().call();
            List<String> tagList = new ArrayList<>();
            String str = "";
            for (Ref ref : tags) {
                tagList.add(ref.getName().split("/")[2]);
            }
            //sort list
            Collections.sort(tagList);
            //smart sort
            Collections.sort(tagList,new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    String v1 = o1.replaceAll("^[A-za-z]+","");
                    String t1 = o1.replace(v1,"");
                    String v2 = o2.replaceAll("^[A-za-z]+","");
                    String t2 = o2.replace(v2,"");
                    if(t1.equals(t2)){
                        // 获取比较的字符串
                        String compareValue1 = v1;
                        String compareValue2 = v2;
                        String[] valueSplit1 = compareValue1.split("[.]");
                        String[] valueSplit2 = compareValue2.split("[.]");
                        int minLength = valueSplit1.length;
                        if (minLength > valueSplit2.length) {
                            minLength = valueSplit2.length;
                        }
                        for (int i = 0; i < minLength; i++) {
                            String valueString1 = valueSplit1[i].replaceAll("[\\D]+","");
                            String valueString2 = valueSplit2[i].replaceAll("[\\D]+","");
                            if(StringUtils.isNotBlank(valueString1) && StringUtils.isNotBlank(valueString2)){
                                int value1 = Integer.parseInt(valueString1);
                                int value2 = Integer.parseInt(valueString2);
                                if(value1 > value2){
                                    return 1;
                                }else if(value1 < value2){
                                    return -1;
                                }
                            }
                        }
                        return valueSplit1.length - valueSplit2.length;
                    }
                    return 0;
                }
            });
            for (String s : tagList) {
                str+=s+"\n";
            }
            System.out.println("Committed to repository at " + git.getRepository().getDirectory());
            DataCenter.TAG_LIST = str;
            String lastTag = tagList.get(tagList.size()-1);
            if(StringUtils.isNotBlank(lastTag)){
                String oldLastNumber = lastTag.substring(lastTag.lastIndexOf(".")+1,lastTag.length());
                Integer lastNumber = Integer.valueOf(oldLastNumber.replaceAll("[\\D]+",""))+1;
                lastTag = lastTag.substring(0,lastTag.lastIndexOf(".")+1)+lastNumber.toString();
            }
            DataCenter.LAST_TAG_TEXT = lastTag;
        }catch (Exception exception) {
            exception.printStackTrace();
        }
        if(DataCenter.GIT_ACCOUNT == null || DataCenter.GIT_PASSWORD == null){
            AccountDialog dialog = new AccountDialog();
            dialog.show();
        }
        //Show custom dialog
        if(DataCenter.GIT_ACCOUNT != null || DataCenter.GIT_PASSWORD != null){
            PushTagDialog dialog = new PushTagDialog(git);
            dialog.show();
        }
    }

}

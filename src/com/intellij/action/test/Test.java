package com.intellij.action.test;

import com.alibaba.fastjson.JSONObject;
import com.intellij.action.git.JTextFieldHintListener;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.List;

/**
 * @Description TODO
 * @Author Daw
 * @Date 2021-02-01 11:59
 * @Version 1.0
 **/
public class Test {

    public static void main(String args[]) throws IOException, GitAPIException {
//        JFrame frame=new JFrame("Java第五个程序");    //创建Frame窗口
//        JPanel p1=new JPanel();    //面板1
//        JPanel p2=new JPanel();    //面板2
//        JPanel cards=new JPanel(new CardLayout());    //卡片式布局的面板
//        p1.add(new JButton("登录按钮"));
//        p1.add(new JButton("注册按钮"));
//        p1.add(new JButton("找回密码按钮"));
//        p2.add(new JTextField("用户名文本框",20));
//        p2.add(new JTextField("密码文本框",20));
//        p2.add(new JTextField("验证码文本框",20));
//        cards.add(p1,"card1");    //向卡片式布局面板中添加面板1
//        cards.add(p2,"card2");    //向卡片式布局面板中添加面板2
//        CardLayout cl=(CardLayout)(cards.getLayout());
//        cl.show(cards,"card1");    //调用show()方法显示面板2

        JFrame frame=new JFrame("Java第五个程序");    //创建Frame窗口

        JPanel panel = new JPanel(new FlowLayout());
        panel.setSize(319,165);

        JLabel titleLabel = new JLabel();
        titleLabel.setText("Input you git account");
        titleLabel.setPreferredSize(new Dimension(250,25));
        panel.add(titleLabel);

        JTextField etfAccount = new JTextField();
        etfAccount.addFocusListener(new JTextFieldHintListener(etfAccount, "account"));
        etfAccount.setPreferredSize(new Dimension(250,30));
        panel.add(etfAccount);

        JTextField etfPassword = new JTextField();
        etfPassword.addFocusListener(new JTextFieldHintListener(etfPassword, "password"));
        etfPassword.setPreferredSize(new Dimension(250,30));
        panel.add(etfPassword);

        frame.add(panel);
        frame.setBounds(300,200,319,165);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        Git git = Git.open( new File("D:/work/b-web/.git"));

        File fishConfig = new File("D:/work/b-web/.git/fish_config");
        if (!fishConfig.exists()) {
            fishConfig.createNewFile();//grilled fish config
        }

        System.out.println("can read:"+fishConfig.canRead()+" can write:"+fishConfig.canWrite());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("xxxx","xxxx");
        jsonObject.put("oooo","oooo");
        FileOutputStream fos = new FileOutputStream(fishConfig);
        fos.write(jsonObject.toJSONString().getBytes());


        FileInputStream fis = new FileInputStream(fishConfig);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();
        String str ;
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }

        System.out.println("==========================>");
        System.out.println(sb.toString());

        //提交代码到本地仓库
        //RevCommit commit = git.commit().setMessage("initial commit").call();

        List<Ref> tags = git.tagList().call();
        System.out.println("=======================> ");
//        System.out.println(JSONObject.toJSONString(tags));

        System.out.println("Committed to repository at " + git.getRepository().getDirectory());

    }

}

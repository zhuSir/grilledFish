package com.intellij.action.git;

import com.intellij.action.data.DataCenter;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.RefSpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * @Description Daw
 * @Author Daw
 * @Date 2020-10-30 16:33
 * @Version 1.0
 **/
public class AccountDialog extends DialogWrapper {

    private JTextField etfAccount;

    private JTextField etfPassword;

    Logger log = LoggerFactory.getLogger(this.getClass().getName());

    public AccountDialog() {
        super(true);
        init();
        setTitle("Input Git Account");
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setPreferredSize(new Dimension(260,100));

        JLabel titleLabel = new JLabel();
        titleLabel.setText("Input you git account");
        titleLabel.setPreferredSize(new Dimension(250,25));
        panel.add(titleLabel);

        etfAccount = new JTextField();
        etfAccount.addFocusListener(new JTextFieldHintListener(etfAccount, "account"));
        etfAccount.setPreferredSize(new Dimension(250,30));
        panel.add(etfAccount);

        etfPassword = new JPasswordField();
        etfPassword.addFocusListener(new JTextFieldHintListener(etfPassword, ""));
        etfPassword.setPreferredSize(new Dimension(250,30));
        panel.add(etfPassword);
        return panel;
    }

    @Override
    protected JComponent createSouthPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        JButton btnAdd = new JButton("Submit");
        //Button click function
        btnAdd.addActionListener(e -> {
            //git account
            String account = etfAccount.getText();
            String password = etfPassword.getText();
            log.info("save account information:"+account+" - "+password);
            if(StringUtils.isBlank(account) || StringUtils.isBlank(password) ){
                Messages.showDialog("You must tall me the git account","Error", new String[]{"Close"},0,null);
            }else{
                log.info("=======================================>>>>>>>>>");
                //cache git account
                DataCenter.writeConfig(account,password);
                //set config is null
                DataCenter.resetConfig();
                this.close(200);
                //toolWindow.hide(null);
            }
        });
        panel.add(btnAdd);
        return panel;
    }

    /**
     * git push
     * @param git
     * @param branch
     * @param provider
     * @throws GitAPIException
     * @throws IOException
     */
    public static void push(Git git, String branch, CredentialsProvider provider) throws GitAPIException, IOException {
        if (branch == null) {
            branch = git.getRepository().getBranch();
        }
        git.push()
                .setCredentialsProvider(provider)
                .setRemote("origin").setRefSpecs(new RefSpec(branch)).call();
    }

}

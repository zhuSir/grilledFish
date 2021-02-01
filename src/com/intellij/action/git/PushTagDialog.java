package com.intellij.action.git;

import com.intellij.action.data.DataCenter;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.MessageDialogBuilder;
import com.intellij.openapi.ui.Messages;
import com.intellij.ui.EditorTextField;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

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
public class PushTagDialog extends DialogWrapper {

    /**
     * current tag
     */
    private EditorTextField etfTag;
    /**
     * tag list
     */
    private EditorTextField etfMark;
    /**
     * git
     */
    private Git git;

    public PushTagDialog(Git git) {
        super(true);
        init();
        setTitle("Push All");
        this.git = git;
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setPreferredSize(new Dimension(480,450));

        etfMark = new EditorTextField(DataCenter.TAG_LIST == null ? "" : DataCenter.TAG_LIST);
        etfTag = new EditorTextField(DataCenter.LAST_TAG_TEXT == null ? "" : DataCenter.LAST_TAG_TEXT);
        // etfTag.setBorder(new MatteBorder( 0, 0, 6, 0,Color.WHITE));
        etfTag.setPreferredSize(new Dimension(474,30));

        etfMark.setSupplementary(true);
        etfMark.setOneLineMode(false);
        etfMark.setPreferredSize(new Dimension(472,401));
        panel.add(etfTag);
        panel.add(etfMark);
        return panel;
    }

    @Override
    protected JComponent createSouthPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        JButton btnAdd = new JButton("Push All");
        //Button click function
        btnAdd.addActionListener(e -> {
            //Get new tag
            String tag = etfTag.getText();
            //获取内容
            String content = etfMark.getText();
            System.out.println("Now take data to server : "+ tag + ":" + content);
            //添加
            //DataCenter.TABLE_MODEL.addRow(DataConvert.toStringArray(noteData));

            int isYes = MessageDialogBuilder.yesNo("grilled fish","Are you sure need : "+ tag).show();

            // 0 yes || 1 no
            if(isYes == 0){
                /**
                 * git tag 'tag version'
                 * git push --tags
                 * Popup window notification messages
                 */
                //RevCommit commit = git.commit().setMessage("initial commit").call();
                try {
                    //close window
                    this.close(200);

                    git.tag().setName(tag).call();
                    push(git,null,new UsernamePasswordCredentialsProvider(DataCenter.config.getAt(),DataCenter.config.getPw()));
                    //git.push().setCredentialsProvider().setPushTags().call();

                    Messages.showDialog("Push successful tag: "+ tag,"Grilled Fish", new String[]{"Close"},0,null);

                } catch (GitAPIException gitAPIException) {
                    gitAPIException.printStackTrace();
                    Messages.showDialog(gitAPIException.getMessage(),"Error", new String[]{"Close"}, 0, null);
                }catch (Exception exception){
                    exception.printStackTrace();
                    Messages.showDialog(exception.getMessage(),"Error", new String[]{"Close"}, 0, null);
                }
                //clear data
                DataCenter.reset();
            }
            //toolWindow.hide(null);
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
                .setPushTags()
                .setCredentialsProvider(provider)
                .setRemote("origin")
                //.setRefSpecs(new RefSpec(branch))
                .call();
    }

}

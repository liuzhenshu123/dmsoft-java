/*
 * Created by JFormDesigner on Wed Jun 05 11:52:31 CST 2024
 */

package cn.com.qjun.dmsoft.windows;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import cn.com.qjun.dmsoft.windows.index.IndexForm;
import net.miginfocom.swing.*;
import org.jdesktop.beansbinding.*;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;

/**
 * @author lzs
 */
public class MainForm extends JFrame {


    public MainForm() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // 在这里执行关闭前的清理工作
                // ...
                dispose(); // 可选：释放窗体资源
                System.exit(0); // 确保应用退出
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 默认关闭操作为退出应用程序
        initComponents();
    }

    private void ok(ActionEvent e) {
        // TODO add your code here
        if (e.getSource() == okButton) {
            // 获取用户输入的账户和密码
            String usernameStr = username.getText();
            String passwordStr = new String(passwordField1.getPassword());

            // 连接到数据库并验证用户
            if (validateUser(usernameStr, passwordStr)) {
                JOptionPane.showMessageDialog(this, "登录成功！");
                IndexForm indexForm = new IndexForm();
                indexForm.setVisible(true); // 显示新窗口
                this.dispose(); // 可选：关闭当前窗口
            } else {
                JOptionPane.showMessageDialog(this, "登录失败，请检查用户名和密码！");
            }
        }
    }
    private boolean validateUser(String username, String password) {
        try {
//            // 连接到 MySQL 数据库
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "username", "password");
//
//            // 查询用户表
//            String query = "SELECT * FROM user WHERE username = ? AND password = ?";
//            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//                preparedStatement.setString(1, username);
//                preparedStatement.setString(2, password);
//
//                // 执行查询
//                ResultSet resultSet = preparedStatement.executeQuery();
//
//                // 如果有匹配的记录，则验证通过
//                return resultSet.next();
//            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "数据库连接或查询出现问题：" + ex.getMessage());
            return false;
        }
    }

    private void cancel(ActionEvent e) {
        // TODO add your code here

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();
        helpButton = new JButton();
        username = new JTextField();
        passwordField1 = new JPasswordField();

        //======== this ========
        setTitle("\u767b\u5f55");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setLayout(new BorderLayout());

            //======== buttonBar ========
            {
                buttonBar.setLayout(new MigLayout(
                    "insets dialog,alignx right",
                    // columns
                    "[button,fill]" +
                    "[button,fill]" +
                    "[button,fill]",
                    // rows
                    null));

                //---- okButton ----
                okButton.setText("\u767b\u5f55");
                okButton.addActionListener(e -> ok(e));
                buttonBar.add(okButton, "cell 0 0");

                //---- cancelButton ----
                cancelButton.setText("\u65e0\u4e8b\u53d1\u751f");
                cancelButton.addActionListener(e -> cancel(e));
                buttonBar.add(cancelButton, "cell 1 0");

                //---- helpButton ----
                helpButton.setText("\u9000\u51fa");
                buttonBar.add(helpButton, "cell 2 0");
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);

            //---- username ----
            username.setText("123123");
            dialogPane.add(username, BorderLayout.NORTH);

            //---- passwordField1 ----
            passwordField1.setText("pass");
            dialogPane.add(passwordField1, BorderLayout.CENTER);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());

        //---- bindings ----
        bindingGroup = new BindingGroup();
        bindingGroup.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
            username, BeanProperty.create("text"),
            username, BeanProperty.create("text")));
        bindingGroup.bind();
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel dialogPane;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    private JButton helpButton;
    private JTextField username;
    private JPasswordField passwordField1;
    private BindingGroup bindingGroup;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on



}

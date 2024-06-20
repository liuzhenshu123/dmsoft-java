/*
 * Created by JFormDesigner on Wed Jun 05 13:44:09 CST 2024
 */

package cn.com.qjun.dmsoft.windows.index;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;

import cn.com.qjun.dmsoft.enums.DisplayMode;
import cn.com.qjun.dmsoft.enums.KeypadMode;
import cn.com.qjun.dmsoft.enums.MouseMode;
import cn.com.qjun.dmsoft.operations.DmOptions;
import cn.com.qjun.dmsoft.operations.DmSoft;
import cn.com.qjun.dmsoft.windows.game.mhxy.function.MhWebFengyaoFunctions;
import cn.com.qjun.dmsoft.windows.soft.wx.WXTestFunctions;
import net.miginfocom.swing.*;

/**
 * @author lzs
 */
public class IndexForm extends JFrame {

    private boolean isButtonPressed = false; // 记录按钮是否被按下
    public static DmSoft dmSoft;
    private SwingWorker<Void, Void> worker;  //后台任务——每十秒执行一次

    {
        dmSoft = new DmSoft(DmOptions.builder("E:\\IdeaWorkSpace\\dmsoft-java\\data", "jv965720b239b8396b1b7df8b768c919e86e10f", "jzv1plmlhlhkpt7").build());

    }

    public IndexForm() {
        initComponents();
        initializeListeners();
//        dmSoft.opsForBasic().setPath("E:\\IdeaWorkSpace\\dmsoft-java\\data");
    }

    private void initializeListeners() {
        //------------------------以下为窗口关闭事件，清理进程和释放数据----------------------------
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // 在这里执行关闭前的清理工作
                // ...
                dmSoft.opsForBackground().unBindWindow();
                dispose(); // 可选：释放窗体资源
                System.exit(0); // 确保应用退出
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 默认关闭操作为退出应用程序
        //---------------------以下为按下鼠标绑定窗口句柄，在窗口外的监听事件----------------------------
        // 添加鼠标按下的监听器
        window_bind.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                isButtonPressed = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isButtonPressed = false;
            }
        });

        // 添加鼠标按下的监听器
        window_bind.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                isButtonPressed = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isButtonPressed = false;
            }
        });

        // 在整个窗口上监听鼠标拖动
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                if (isButtonPressed) {
                    // 鼠标按下状态下移出窗口，执行方法
                    performAction(e);
                }
            }
        });
        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                checkAndPerformAction(e);
            }
        });


        //--------------------以下为监听10秒循环选择框功能----------------------------
        re_all_ten_sec.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                startTask();
            } else {
                stopTask();
            }
        });
    }
    private void startTask() {
        if (worker == null || worker.isDone()) {
            worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {
                    while (!isCancelled()) {
                        // 模拟后台执行的任务
                        WXTestFunctions.mouse_to_redheart(Long.parseLong(window_id_text.getText()));
                        Thread.sleep(10000); // 注意：不要在真实应用中直接睡眠主线程
                        publish(new Void[0]); // 触发process方法
                    }
                    return null;
                }

                @Override
                protected void process(java.util.List<Void> chunks) {
                    // 在这里可以更新UI，例如显示进度
                    System.out.println("Task running...");
                }
            };
            worker.addPropertyChangeListener(new PropertyChangeListener() {
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    if ("state".equals(evt.getPropertyName())
                            && SwingWorker.StateValue.DONE.equals(evt.getNewValue())) {
                        worker = null;
                    }
                }
            });
            worker.execute();
        }
    }

    private void stopTask() {
        if (worker != null && !worker.isDone()) {
            worker.cancel(true); // 尝试中断后台任务
        }
    }

    private void window_binding(ActionEvent e) {
        // TODO add your code here
        //获取鼠标指向的窗口句柄
        long windowId = dmSoft.opsForWindow().getMousePointWindow();
        window_id_text.setText(windowId + "");
        System.out.println("获取句柄完毕：" + windowId);
        window_name.setText(dmSoft.opsForWindow().getWindowTitle(windowId));
    }

    private void window_binding() {
        // TODO add your code here
        //获取鼠标指向的窗口句柄
        long windowId = dmSoft.opsForWindow().getMousePointWindow();
        window_id_text.setText(windowId + "");
        System.out.println("获取句柄完毕：" + windowId);
        window_name.setText(dmSoft.opsForWindow().getWindowTitle(windowId));
    }

    /**
     * 输入hwnd手动绑定窗口
     *
     * @param e
     */
    private void window_hand_bind(ActionEvent e) {
        // TODO add your code here
        long hwnd = Long.parseLong(window_id_text.getText());
        window_name.setText(dmSoft.opsForWindow().getWindowTitle(hwnd));
//        dmSoft.opsForBackground().bindWindow(hwnd, DisplayMode.GDI2, MouseMode.WINDOWS2, KeypadMode.WINDOWS,0);
    }

    private void mouse_to_redheart(ActionEvent e) {
        // TODO add your code here
        WXTestFunctions.mouse_to_redheart(Long.parseLong(window_id_text.getText()));
    }

    private void mouse_to_junjun(ActionEvent e) {
        // TODO add your code here
        WXTestFunctions.mouse_to_junjun(Long.parseLong(window_id_text.getText()));
    }

    private void re_all_ten_sec(ActionEvent e) {
        // TODO add your code here
        //便利勾选的，然后循环
        // 绑定事件监听器
        System.out.println("点击了10秒循环选项框");

    }

    /**
     * 点击主页
     * @param e
     */
    private void mh_click_home(ActionEvent e) {
        // TODO add your code here
        MhWebFengyaoFunctions.clickHome(Long.parseLong(window_id_text.getText()));
    }

    private void click_fengyao_home(ActionEvent e) {
        // TODO add your code here
        MhWebFengyaoFunctions.clickFengYaoHome(Long.parseLong(window_id_text.getText()));
    }

    private void click_start_fengyao(ActionEvent e) {
        // TODO add your code here
        MhWebFengyaoFunctions.clickFengYaoStart(Long.parseLong(window_id_text.getText()));
    }

    private void un_bind_windows(ActionEvent e) {
        // TODO add your code here
        dmSoft.opsForBackground().unBindWindow();
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menu2 = new JMenu();
        menu3 = new JMenu();
        window_hand_bind = new JButton();
        window_id_text = new JTextField();
        window_name = new JTextField();
        window_bind = new JButton();
        mouse_to_redheart = new JCheckBoxMenuItem();
        mh_click_home = new JCheckBoxMenuItem();
        un_bind_windows = new JButton();
        mouse_to_junjun = new JCheckBoxMenuItem();
        click_fengyao_home = new JCheckBoxMenuItem();
        re_all_ten_sec = new JCheckBoxMenuItem();
        click_start_fengyao = new JCheckBoxMenuItem();
        checkBoxMenuItem4 = new JCheckBoxMenuItem();
        checkBoxMenuItem10 = new JCheckBoxMenuItem();
        checkBoxMenuItem5 = new JCheckBoxMenuItem();
        checkBoxMenuItem6 = new JCheckBoxMenuItem();
        checkBoxMenuItem9 = new JCheckBoxMenuItem();
        checkBoxMenuItem8 = new JCheckBoxMenuItem();

        //======== this ========
        setTitle("mh\u811a\u672c");
        Container contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[24]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText("\u57fa\u672c\u4fe1\u606f");
            }
            menuBar1.add(menu1);

            //======== menu2 ========
            {
                menu2.setText("\u529f\u80fd");
            }
            menuBar1.add(menu2);

            //======== menu3 ========
            {
                menu3.setText("\u66f4\u65b0");
            }
            menuBar1.add(menu3);
        }
        setJMenuBar(menuBar1);

        //---- window_hand_bind ----
        window_hand_bind.setText("\u624b\u52a8\u7ed1\u5b9a\u7a97\u53e3");
        window_hand_bind.addActionListener(e -> window_hand_bind(e));
        contentPane.add(window_hand_bind, "cell 0 0");

        //---- window_id_text ----
        window_id_text.setMinimumSize(new Dimension(100, 30));
        contentPane.add(window_id_text, "cell 1 0");

        //---- window_name ----
        window_name.setMinimumSize(new Dimension(150, 30));
        contentPane.add(window_name, "cell 2 0");

        //---- window_bind ----
        window_bind.setText("\u7ed1\u5b9a\u7a97\u53e3");
        window_bind.addActionListener(e -> window_binding(e));
        contentPane.add(window_bind, "cell 3 0");

        //---- mouse_to_redheart ----
        mouse_to_redheart.setText("\u9f20\u6807\u79fb\u5230\u7ea2\u5fc3");
        mouse_to_redheart.addActionListener(e -> mouse_to_redheart(e));
        contentPane.add(mouse_to_redheart, "cell 0 1");

        //---- mh_click_home ----
        mh_click_home.setText("\u70b9\u51fb\u4e3b\u9875");
        mh_click_home.addActionListener(e -> mh_click_home(e));
        contentPane.add(mh_click_home, "cell 1 1");

        //---- un_bind_windows ----
        un_bind_windows.setText("\u89e3\u9664\u7ed1\u5b9a");
        un_bind_windows.setBackground(Color.yellow);
        un_bind_windows.setForeground(Color.red);
        un_bind_windows.addActionListener(e -> un_bind_windows(e));
        contentPane.add(un_bind_windows, "cell 3 1");

        //---- mouse_to_junjun ----
        mouse_to_junjun.setText("\u9f20\u6807\u79fb\u5230\u540d\u79f0");
        mouse_to_junjun.addActionListener(e -> mouse_to_junjun(e));
        contentPane.add(mouse_to_junjun, "cell 0 2");

        //---- click_fengyao_home ----
        click_fengyao_home.setText("\u70b9\u51fb\u5c01\u5996");
        click_fengyao_home.addActionListener(e -> click_fengyao_home(e));
        contentPane.add(click_fengyao_home, "cell 1 2");

        //---- re_all_ten_sec ----
        re_all_ten_sec.setText("10\u79d2\u91cd\u590d\u64cd\u4f5c");
        re_all_ten_sec.addActionListener(e -> re_all_ten_sec(e));
        contentPane.add(re_all_ten_sec, "cell 0 3");

        //---- click_start_fengyao ----
        click_start_fengyao.setText("\u5f00\u59cb\u5c01\u5996");
        click_start_fengyao.addActionListener(e -> click_start_fengyao(e));
        contentPane.add(click_start_fengyao, "cell 1 3");

        //---- checkBoxMenuItem4 ----
        checkBoxMenuItem4.setText("text");
        contentPane.add(checkBoxMenuItem4, "cell 0 4");

        //---- checkBoxMenuItem10 ----
        checkBoxMenuItem10.setText("text");
        contentPane.add(checkBoxMenuItem10, "cell 1 4");

        //---- checkBoxMenuItem5 ----
        checkBoxMenuItem5.setText("text");
        contentPane.add(checkBoxMenuItem5, "cell 0 5");

        //---- checkBoxMenuItem6 ----
        checkBoxMenuItem6.setText("text");
        contentPane.add(checkBoxMenuItem6, "cell 1 5");

        //---- checkBoxMenuItem9 ----
        checkBoxMenuItem9.setText("text");
        contentPane.add(checkBoxMenuItem9, "cell 0 6");

        //---- checkBoxMenuItem8 ----
        checkBoxMenuItem8.setText("text");
        contentPane.add(checkBoxMenuItem8, "cell 0 7");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    private void performAction(MouseEvent e) {
        System.out.println("Button was dragged outside the window!");
        // 在这里执行你希望的操作
        window_binding();
    }

    private void checkAndPerformAction(MouseEvent e) {
        if (isButtonPressed && !this.getBounds().contains(e.getPoint())) {
            performAction(e);
        }
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenu menu2;
    private JMenu menu3;
    private JButton window_hand_bind;
    private JTextField window_id_text;
    private JTextField window_name;
    private JButton window_bind;
    private JCheckBoxMenuItem mouse_to_redheart;
    private JCheckBoxMenuItem mh_click_home;
    private JButton un_bind_windows;
    private JCheckBoxMenuItem mouse_to_junjun;
    private JCheckBoxMenuItem click_fengyao_home;
    private JCheckBoxMenuItem re_all_ten_sec;
    private JCheckBoxMenuItem click_start_fengyao;
    private JCheckBoxMenuItem checkBoxMenuItem4;
    private JCheckBoxMenuItem checkBoxMenuItem10;
    private JCheckBoxMenuItem checkBoxMenuItem5;
    private JCheckBoxMenuItem checkBoxMenuItem6;
    private JCheckBoxMenuItem checkBoxMenuItem9;
    private JCheckBoxMenuItem checkBoxMenuItem8;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}

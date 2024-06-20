package cn.com.qjun.dmsoft;

import cn.com.qjun.dmsoft.windows.MainForm;
import cn.com.qjun.dmsoft.windows.index.IndexForm;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
//            MainForm loginForm = new MainForm();
            IndexForm indexForm = new IndexForm();
            indexForm.setVisible(true);
        });
    }
}

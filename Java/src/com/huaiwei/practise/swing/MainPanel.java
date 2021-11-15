package com.huaiwei.practise.swing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MainPanel extends JFrame implements ActionListener {

    private JTextArea area = new JTextArea();
    private JTextField textField1 = new JTextField("请选择或者直接输入文件路径");
    private JButton chooseFile = new JButton("选择文件");
    private JButton chooseDir = new JButton("选择文件夹");
    private JTextField textField2 = new JTextField("请选择或者直接输入文件夹路径");
    private JButton execute = new JButton("执行转换");


    public MainPanel() {
        this.setTitle("文件筛选器");
        this.setSize(1100, 500); //设置大小
        this.setVisible(true);//设置可见性
        this.setResizable(false);//设置可拖动
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置关闭时停止程序
        this.setLocationRelativeTo(null);//设置布局
        this.setLayout(null);//设置布局


        chooseFile.setBounds(550, 50, 100, 40);
        textField1.setBounds(100, 50, 350, 40);


        chooseDir.setBounds(550, 150, 100, 40);
        textField2.setBounds(100, 150, 350, 40);

        execute.setBounds(750, 100, 100, 40);

        //日志输出框
        area.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(area);
        scrollPane.setBounds(100, 250, 900, 180);

        // area.setLineWrap(true);//自动换行
        this.add(chooseFile);
        this.add(textField1);
        this.add(chooseDir);
        this.add(textField2);
        this.add(scrollPane);
        this.add(execute);

        chooseFile.addActionListener(this);
        chooseDir.addActionListener(this);
        execute.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        JFileChooser chooser = new JFileChooser();
        if (button == chooseFile) {
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            chooser.showDialog(new JLabel(), "选择");
            File file = chooser.getSelectedFile();
            textField1.setText(file.getAbsoluteFile().toString());

        } else if (button == chooseDir) {
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.showDialog(new JLabel(), "选择");
            File file = chooser.getSelectedFile();
            textField2.setText(file.getAbsoluteFile().toString());
        } else if (button == execute) {
            FileFilterMain fileFilterMain = new FileFilterMain();
            String file = textField1.getText();
            String dirName = textField2.getText();
            fileFilterMain.execute(file, dirName, area);
        }

    }
}

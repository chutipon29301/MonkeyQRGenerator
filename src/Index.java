/*
 * Copyright [2017] [Chutipon]

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Index extends JFrame {

    private JTextField textField;

    /**
     * Create the frame.
     */
    private Index() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblMonkeyQrGenerator = new JLabel("Monkey QR Generator");
        lblMonkeyQrGenerator.setFont(new Font("Cordia New", Font.PLAIN, 30));
        lblMonkeyQrGenerator.setBounds(122, 55, 195, 35);
        contentPane.add(lblMonkeyQrGenerator);

        textField = new JTextField();
        textField.setBounds(61, 126, 313, 35);
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyChar() == '\n') {
                    generate();
                }
            }
        });
        contentPane.add(textField);
        textField.setColumns(10);

        JButton btnGenerate = new JButton("Generate");
        btnGenerate.setBounds(272, 197, 100, 31);
        btnGenerate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                generate();
            }
        });
        contentPane.add(btnGenerate);

        JButton btnQRCode = new JButton("QRCode");
        btnQRCode.setBounds(162, 197, 100, 31);
        btnQRCode.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                generateQR();
            }
        });
        contentPane.add(btnQRCode);
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Index frame = new Index();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void generate() {
        String inputText = textField.getText();
        FileUtil testValid = new FileUtil(inputText);
        textField.setText("");
        LevelCode inputValid = new LevelCode(inputText);
        if (inputValid.isValid()) {
            if (testValid.isValid()) {
                testValid.generate();
                Summary.run(inputText);
            } else {
                JOptionPane.showMessageDialog(null, "Can't create QRCode, key already exist", "Error: File already exist", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Can't create QRCode, wrong level format", "Error: Wrong level format", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void generateQR() {
        String inputText = textField.getText();
        FileUtil generator = new FileUtil(inputText);
        textField.setText("");
        LevelCode inputValid = new LevelCode(inputText);
        if (inputValid.isValid()) {
            generator.generate();
            Summary.run(inputText);
        } else {
            JOptionPane.showMessageDialog(null, "Can't create QRCode, wrong level format", "Error: Wrong level format", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}

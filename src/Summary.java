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

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.util.ArrayList;

class Summary extends JFrame {

    private static String levelName;
    private static String levelCode;
    private static String levelRev;

    /**
     * Launch the application.
     */
    static void run(String levelName) {
        Summary.levelName = levelName;
        EventQueue.invokeLater(() -> {
            try {
                Summary frame = new Summary();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    private Summary() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        setLevelCode();
        setLevelRev();

        JLabel lblTheFollowingFile = new JLabel("The following file has been created.");
        lblTheFollowingFile.setFont(new Font("Cordia New", Font.PLAIN, 18));
        lblTheFollowingFile.setBounds(10, 11, 245, 26);
        contentPane.add(lblTheFollowingFile);

        ArrayList<JLabel> label = new ArrayList<>();

        label.add(new JLabel("-  " + levelCode + "SKILLKEY" + levelRev + ".pdf"));
        label.add(new JLabel("-  " + levelCode + "HWKEY" + levelRev + ".pdf"));
        label.add(new JLabel("-  " + levelCode + "TESTKEY" + levelRev + ".pdf"));
        label.add(new JLabel("-  " + levelCode + "HOTKEY" + levelRev + ".pdf"));

        for (int i = 0; i < label.size(); i++) {
            label.get(i).setVerticalAlignment(SwingConstants.TOP);
            label.get(i).setFont(new Font("Cordia New", Font.PLAIN, 18));
            label.get(i).setBounds(37, 48 + (i * 30), 361, 202);
            contentPane.add(label.get(i));
        }
    }

    private void setLevelCode() {
        levelCode = levelName.substring(0, levelName.indexOf('('));
    }

    private void setLevelRev() {
        levelRev = levelName.substring(levelName.indexOf('('));
    }

}
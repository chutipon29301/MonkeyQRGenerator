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

import java.io.File;
import java.util.ArrayList;

@SuppressWarnings("SpellCheckingInspection")
class FileUtil {

    private String levelCode;
    private String skillPath, hwPath, testPath, hotKeyPath;

    FileUtil(String levelCode) {
        this.levelCode = levelCode;
    }

    void generate() {
        DecodeSubject decoder = new DecodeSubject(levelCode);

        ArrayList<QRGenerator> qrList = new ArrayList<>();
        qrList.add(new QRGenerator(decoder.getDestinationSkillPath()));
        qrList.add(new QRGenerator(decoder.getDestinationHwPath()));
        qrList.add(new QRGenerator(decoder.getDestinationTestPath()));
        qrList.add(new QRGenerator(decoder.getDestinationHotkeyPath()));

        String[] savedName = {"SKILLKEY", "HWKEY", "TESTKEY", "HOTKEY"};

        for (int i = 0; i < qrList.size(); i++) {
            String savePath = "\\\\192.168.1.150\\tutorfiles\\2.QROUT";
            qrList.get(i).saveTo(savePath + "\\" + levelCode.substring(0, levelCode.indexOf('(')) + savedName[i] + levelCode.substring(levelCode.indexOf('(')) + ".png");
            System.out.println(qrList.get(i).getPathName().replace("/", "\\").replace("file:\\\\monkeycloud", "\\\\192.168.1.150"));
            PDFUtil pdfGenerator = new PDFUtil(qrList.get(i).getPathName().replace("/", "\\").replace("file:\\\\monkeycloud", "\\\\192.168.1.150"));
            pdfGenerator.create();
        }
    }

    boolean isValid() {
        getFilePath();
        System.out.println(skillPath);
        System.out.println(hwPath);
        System.out.println(testPath);
        System.out.println(hotKeyPath);
        return !(isSkillPathExist() || isHwPathExist() || isTestPathExist() || isHotKeyPathExist());
    }

    private void getFilePath() {
        DecodeSubject decoder = new DecodeSubject(levelCode);
        skillPath = decoder.getDestinationSkillPath().replace("file://monkeycloud", "\\\\192.168.1.150").replace('/', '\\');
        hwPath = decoder.getDestinationHwPath().replace("file://monkeycloud", "\\\\192.168.1.150").replace('/', '\\');
        testPath = decoder.getDestinationTestPath().replace("file://monkeycloud", "\\\\192.168.1.150").replace('/', '\\');
        hotKeyPath = decoder.getDestinationHotkeyPath().replace("file://monkeycloud", "\\\\192.168.1.150").replace('/', '\\');
    }

    private boolean isSkillPathExist() {
        File temp = new File(skillPath);
        return temp.exists();
    }

    private boolean isHwPathExist() {
        File temp = new File(hwPath);
        return temp.exists();
    }

    private boolean isTestPathExist() {
        File temp = new File(testPath);
        return temp.exists();
    }

    private boolean isHotKeyPathExist() {
        File temp = new File(hotKeyPath);
        return temp.exists();
    }
}

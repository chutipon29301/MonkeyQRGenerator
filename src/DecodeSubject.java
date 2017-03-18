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

@SuppressWarnings("SpellCheckingInspection")
public class DecodeSubject {
    private String subjectCode;
    private String commonPath = "file://monkeycloud/key-qrcode/";
    private String destinationSkillPath, destinationHwPath, destinationTestPath;
    private String destinationHotkeyPath = "file://monkeycloud/key-student/";

    public DecodeSubject(String subjectCode) {
        this.subjectCode = subjectCode;
        decode();
    }

    public String getDestinationSkillPath() {
        return destinationSkillPath;
    }

    public String getDestinationHwPath() {
        return destinationHwPath;
    }

    public String getDestinationTestPath() {
        return destinationTestPath;
    }

    public String getDestinationHotkeyPath() {
        return destinationHotkeyPath;
    }

    private boolean decode() {
        switch (subjectCode.charAt(0)) {
            case 'M':
                commonPath += "MATH/";
                destinationHotkeyPath += "MATH/";
                ;
                break;
            case 'P':
                commonPath += "PHYSICS/";
                destinationHotkeyPath += "PHYSICS/";
                break;
            default:
                return false;
        }
        commonPath += subjectCode.substring(0, getIndexOfFirstNum() - 1) + "/";
        destinationHotkeyPath += subjectCode.substring(0, getIndexOfFirstNum() - 1) + "/";


        commonPath += subjectCode.substring(0, getIndexOfFirstNum() - 1)
                + subjectCode.substring(subjectCode.indexOf('('), subjectCode.lastIndexOf('_'))
                + ")/"
                + subjectCode.substring(0, subjectCode.indexOf('('))
                + "/"
                + subjectCode.substring(0, subjectCode.indexOf('('));
        destinationHotkeyPath += subjectCode.substring(0, subjectCode.indexOf('('))
                + "/";
        destinationSkillPath = commonPath
                + "SKILLKEY"
                + subjectCode.substring(subjectCode.indexOf('('), subjectCode.indexOf(')') + 1) + ".pdf";
        destinationHwPath = commonPath
                + "HWKEY"
                + subjectCode.substring(subjectCode.indexOf('('), subjectCode.indexOf(')') + 1) + ".pdf";
        destinationTestPath = commonPath
                + "TESTKEY"
                + subjectCode.substring(subjectCode.indexOf('('), subjectCode.indexOf(')') + 1) + ".pdf";
        destinationHotkeyPath += subjectCode.substring(0, subjectCode.indexOf('('))
                + "HOTKEY" + subjectCode.substring(subjectCode.indexOf('('), subjectCode.indexOf(')') + 1) + ".pdf";
        return true;
    }

    private int getIndexOfFirstNum() {
        int index = subjectCode.length();
        for (int i = 0; i < 10; i++) {
            try {
                int temp = subjectCode.indexOf((char) (48 + i));
                if (temp == -1) continue;
                if (temp < index) index = temp;
            } catch (Exception ignored) {
            }
        }
        return index;
    }

    public static void main(String[] args) {
        DecodeSubject temp = new DecodeSubject("MH-BB01(REV1_0)");
        System.out.println(temp.getDestinationSkillPath());
        System.out.println(temp.getDestinationHwPath());
        System.out.println(temp.getDestinationTestPath());
        System.out.println(temp.getDestinationHotkeyPath());
        System.out.println(temp.getDestinationHotkeyPath().replace("file://monkeycloud", "\\\\192.168.1.150").replace('/', '\\'));
    }
}

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
class DecodeSubject {
    private String subjectCode;
    private String commonPath = "file://monkeycloud/key-qrcode/";
    private String destinationSkillPath, destinationHwPath, destinationTestPath;
    private String destinationHotkeyPath = "file://monkeycloud/key-student/";

    DecodeSubject(String subjectCode) {
        this.subjectCode = subjectCode;
        decode();
    }

    public static void main(String[] args) {

        DecodeSubject a = new DecodeSubject("MK-BB02b(REV1_0).");
        System.out.println("OUT:\t" + a.getDestinationSkillPath());
        System.out.println("IN:\t\tfile://monkeycloud/key-qrcode/MATH/MK-B/MK-B(REV1)/MK-BB02/MK-BB02bSKILLKEY(REV1_0).pdf");
        System.out.println("file://monkeycloud/key-qrcode/MATH/MK-B/MK-B(REV1)/MK-BB02/MK-BB02bSKILLKEY(REV1_0).pdf".equals(a.getDestinationSkillPath()));
        System.out.println("--------------------------------------------------------------------------------------------------------");

        DecodeSubject b = new DecodeSubject("MK-AB09a(REV1_0)");
        System.out.println("OUT:\t" + b.getDestinationHotkeyPath());
        System.out.println("IN:\t\tfile://monkeycloud/key-student/MATH/MK-A/MK-AB09/MK-AB09a/MK-AB09aHOTKEY(REV1_0).pdf");
        System.out.println("file://monkeycloud/key-student/MATH/MK-A/MK-AB09/MK-AB09a/MK-AB09aHOTKEY(REV1_0).pdf".equals(b.getDestinationHotkeyPath()));
        System.out.println("--------------------------------------------------------------------------------------------------------");

        DecodeSubject c = new DecodeSubject("MJ-XGA04(REV2_0)");
        System.out.println("OUT:\t" + c.getDestinationHotkeyPath());
        System.out.println("IN:\t\tfile://monkeycloud/key-student/MATH/MJ-XG/MJ-XG(REV2)/MJ-XGA04/MJ-XGA04HOTKEY(REV2_0).pdf");
        System.out.println("file://monkeycloud/key-student/MATH/MJ-XG/MJ-XG(REV2)/MJ-XGA04/MJ-XGA04HOTKEY(REV2_0).pdf".equals(c.getDestinationHotkeyPath()));
        System.out.println("--------------------------------------------------------------------------------------------------------");

        DecodeSubject d = new DecodeSubject("MJ-XGA04(REV2_0)");
        System.out.println("OUT:\t" + d.getDestinationSkillPath());
        System.out.println("IN:\t\tfile://monkeycloud/key-qrcode/MATH/MJ-XG/MJ-XG(REV2)/MJ-XGA04/MJ-XGA04SKILLKEY(REV2_0).pdf");
        System.out.println("file://monkeycloud/key-qrcode/MATH/MJ-XG/MJ-XG(REV2)/MJ-XGA04/MJ-XGA04SKILLKEY(REV2_0).pdf".equals(d.getDestinationSkillPath()));
    }

    String getDestinationSkillPath() {
        return destinationSkillPath;
    }

    String getDestinationHwPath() {
        return destinationHwPath;
    }

    String getDestinationTestPath() {
        return destinationTestPath;
    }

    String getDestinationHotkeyPath() {
        return destinationHotkeyPath;
    }

    private boolean decode() {
        switch (subjectCode.charAt(0)) {
            case 'M':
                commonPath += "MATH/";
                destinationHotkeyPath += "MATH/";
                break;
            case 'P':
                commonPath += "PHYSICS/";
                destinationHotkeyPath += "PHYSICS/";
                break;
            default:
                return false;
        }

        try {
            commonPath += subjectCode.substring(0, getIndexOfFirstNum() - 1) + "/";
            destinationHotkeyPath += subjectCode.substring(0, getIndexOfFirstNum() - 1) + "/";
        } catch (Exception e) {
            return false;
        }

        if (isSubLevel()) {
            try {
                commonPath += subjectCode.substring(0, getIndexOfFirstNum() - 1)
                        + subjectCode.substring(subjectCode.indexOf('('), subjectCode.lastIndexOf('_'))
                        + ")/"
                        + subjectCode.substring(0, subjectCode.indexOf('(') - 1)
                        + "/"
                        + subjectCode.substring(0, subjectCode.indexOf('('));
            } catch (Exception e) {
                return false;
            }
        } else {
            try {
                commonPath += subjectCode.substring(0, getIndexOfFirstNum() - 1)
                        + subjectCode.substring(subjectCode.indexOf('('), subjectCode.lastIndexOf('_'))
                        + ")/"
                        + subjectCode.substring(0, subjectCode.indexOf('('))
                        + "/"
                        + subjectCode.substring(0, subjectCode.indexOf('('));
            } catch (Exception e) {
                return false;
            }
        }
        try {
            destinationHotkeyPath = commonPath
                    + "HOTKEY"
                    + subjectCode.substring(subjectCode.indexOf('('), subjectCode.indexOf(')') + 1) + ".pdf";
            destinationHotkeyPath = destinationHotkeyPath.replace("key-qrcode","key-student");

            destinationSkillPath = commonPath
                    + "SKILLKEY"
                    + subjectCode.substring(subjectCode.indexOf('('), subjectCode.indexOf(')') + 1) + ".pdf";

            destinationHwPath = commonPath
                    + "HWKEY"
                    + subjectCode.substring(subjectCode.indexOf('('), subjectCode.indexOf(')') + 1) + ".pdf";

            destinationTestPath = commonPath
                    + "TESTKEY"
                    + subjectCode.substring(subjectCode.indexOf('('), subjectCode.indexOf(')') + 1) + ".pdf";

        } catch (Exception e) {
            return false;
        }
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

    private boolean isSubLevel() {
        return !Character.isDigit(subjectCode.charAt(subjectCode.indexOf('(') - 1));
    }
}

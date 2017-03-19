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

class LevelCode {
    private String levelCode;
    private String[] codePath = new String[6];

    LevelCode(String levelCode) {
        this.levelCode = levelCode;
        getCodeArray();
    }

    boolean isValid() {
        return false;
    }

    private void getCodeArray() {
        codePath[0] = levelCode.substring(0, levelCode.indexOf('-'));
        codePath[1] = levelCode.substring(levelCode.indexOf('-') + 1, getIndexOfFirstNumber() - 1);
        codePath[2] = String.valueOf(levelCode.charAt(getIndexOfFirstNumber() - 1));
        codePath[3] = levelCode.substring(getIndexOfFirstNumber(), getIndexOfFirstNumber() + 2);
        codePath[4] = ((levelCode.charAt(getIndexOfFirstNumber() + 2) == '(') ? null : String.valueOf(levelCode.charAt(getIndexOfFirstNumber() + 2)));
        codePath[5] = levelCode.substring(levelCode.indexOf('(') + 1, levelCode.indexOf(')'));
    }

    private int getIndexOfFirstNumber() {
        int index = levelCode.length();
        for (int i = 0; i < 10; i++) {
            try {
                int temp = levelCode.indexOf((char) (48 + i));
                if (temp == -1) continue;
                if (temp < index) index = temp;
            } catch (Exception ignored) {
            }
        }
        return index;
    }

    public static void main(String[] args) {
        LevelCode temp = new LevelCode("MJ-BB01(REV1_0)");
        for (String text : temp.codePath) {
            System.out.println(text + ", ");
        }
    }
}

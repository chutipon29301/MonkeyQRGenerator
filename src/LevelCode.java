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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

class LevelCode {
    private String levelCode;
    private String[] codePath = new String[6];
    private ArrayList<String> subLevelKey = new ArrayList<>();
    private ArrayList<String> subSheetKey = new ArrayList<>();

    LevelCode(String levelCode) {
        this.levelCode = levelCode;
        getCodeArray();
    }

    boolean isValid() {
        Collections.addAll(subLevelKey, "B", "I", "E", "P", "A", "T");
        Collections.addAll(subSheetKey, "a", "b", "c");
        return (codePath[0] != null && codePath[0].length() == 2) &&
                codePath[1] != null &&
                (codePath[2] != null && subLevelKey.contains(codePath[2])) &&
                (codePath[4] == null || subSheetKey.contains(codePath[4]));
    }

    private void getCodeArray() {
        codePath[0] = (Objects.equals(levelCode.substring(0, levelCode.indexOf('-')), "")) ?
                null :
                levelCode.substring(0, levelCode.indexOf('-'));
        codePath[1] = (Objects.equals(levelCode.substring(levelCode.indexOf('-') + 1, getIndexOfFirstNumber() - 1), "")) ?
                null :
                levelCode.substring(levelCode.indexOf('-') + 1, getIndexOfFirstNumber() - 1);
        codePath[2] = (Objects.equals(String.valueOf(levelCode.charAt(getIndexOfFirstNumber() - 1)), "")) ?
                null :
                String.valueOf(levelCode.charAt(getIndexOfFirstNumber() - 1));
        codePath[3] = (Objects.equals(levelCode.substring(getIndexOfFirstNumber(), getIndexOfFirstNumber() + 2), "")) ?
                null :
                levelCode.substring(getIndexOfFirstNumber(), getIndexOfFirstNumber() + 2);
        codePath[4] = (levelCode.charAt(getIndexOfFirstNumber() + 2) == '(') ?
                null :
                String.valueOf(levelCode.charAt(getIndexOfFirstNumber() + 2));
        codePath[5] = (Objects.equals(levelCode.substring(levelCode.indexOf('(') + 1, levelCode.indexOf(')')), "")) ?
                null :
                levelCode.substring(levelCode.indexOf('(') + 1, levelCode.indexOf(')'));
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
}

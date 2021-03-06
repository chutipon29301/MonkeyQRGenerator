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

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import java.io.File;
import java.io.IOException;

class PDFUtil {

    private String path;

    PDFUtil(String path) {
        this.path = path;
    }

    boolean create() {
        PDDocument doc = new PDDocument();
        doc.addPage(new PDPage());
        File testExist = new File(path);
        if (!testExist.exists()) {
            try {
                doc.save(path);
            } catch (IOException e) {
                System.out.println("Path doesn't existed");
                String dir = path.substring(0, path.lastIndexOf('\\'));
                File temp = new File(dir);
                //noinspection ResultOfMethodCallIgnored
                temp.mkdirs();
                try {
                    doc.save(path);
                } catch (IOException ignored) {
                }
                return false;
            } finally {
                try {
                    doc.close();
                } catch (IOException ignored) {
                }
            }
        } else {
            System.out.println("File existed, can't create file");
            return false;
        }
        return true;
    }
}

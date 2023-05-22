package io;

import java.io.File;
import java.io.IOException;

public class MainApp {
    public static void main(String[] args) throws IOException {
        File dir = new File("fileDir");
        if(!dir.exists()) {
            dir.mkdir();
        }

        File file = new File(dir, "demo.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
    }

}

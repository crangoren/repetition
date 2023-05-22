package io;

import java.io.*;
import java.util.Scanner;

public class DataStreamApp {

    public static void main(String[] args) {
        File file = new File("demo.txt");

        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file))) {
            dataOutputStream.writeUTF("segfsdg");
        }catch (IOException e) {
            e.printStackTrace();
        }

        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file))) {
            dataInputStream.readUTF();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}

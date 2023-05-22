package io;

import java.io.*;

public class StreamApp {
    public static void main(String[] args) {
        String str = "My string";
        try(FileOutputStream fos = new FileOutputStream(new File("demo.txt"), true)) {
            fos.write(str.getBytes());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try(FileInputStream fileInputStream = new FileInputStream("demo.txt")) {
            byte[] bytes = new byte[100];
            fileInputStream.read(bytes);
            System.out.println(new String(bytes));
        } catch (IOException ex) {
            ex.printStackTrace();
        }



//        byte[] bytes = {65, 66, 67};
//       try( ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);) {
//           int x;
//           while ((x = byteArrayInputStream.read()) != -1) {
//               System.out.println(x);
//           }
//       }catch (IOException exception) {
//           exception.printStackTrace();
//       }

    }
}

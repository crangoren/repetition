package io;

import java.io.*;
import java.util.Arrays;

public class SerializeApp {
    public static void main(String[] args) {
        File file = new File("bike.txt");
        Bike bike = new Bike("canyon");
        bike.setSerialNo("11121315646461");
        System.out.println(bike);

        byte[] bytes = new byte[0];

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream  = new ObjectOutputStream(byteArrayOutputStream)) {
            objectOutputStream.writeObject(bike);
            bytes = byteArrayOutputStream.toByteArray();
            System.out.println(Arrays.toString(bytes));

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream))
        {
            Bike desBike = (Bike) objectInputStream.readObject();

            System.out.println(desBike);
        }catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
    }

    }
}

package Generics;

public class SimpleBoxApp {

    public static void main(String[] args) {
        SimpleBox box1 = new SimpleBox(42);
        SimpleBox box2 = new SimpleBox(125);
        int sum = (Integer) box1.getObject() + (Integer) box2.getObject();
        System.out.println(sum);

        SimpleBox box3 = new SimpleBox("125");



        try {
            int sum1 = (Integer) box1.getObject() + (Integer) box3.getObject();
            System.out.println(sum1);
        } catch (ClassCastException cce) {
            System.out.println("one of objects is not int");
        }

        if (box1.getObject() instanceof Integer && box3.getObject() instanceof Integer) {
            int sum1 = (Integer) box1.getObject() + (Integer) box3.getObject();
            System.out.println(sum1);
        } else {
            System.out.println("one of objects is not int");
        }



       // System.out.println(sum1);

    }
}

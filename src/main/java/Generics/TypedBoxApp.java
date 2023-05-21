package Generics;

public class TypedBoxApp {

    public static void main(String[] args) {
        TypedBox<Integer> box1 = new TypedBox<>(42);
        TypedBox<Integer> box2 = new TypedBox<>(195);

        int sum = box1.getObject() + box2.getObject();
        System.out.println(sum);

        TypedBox<String> stringTypedBox3 = new TypedBox<>("195");

        box1.showType();
        stringTypedBox3.showType();

        TypedBox<SimpleBox> simpleBoxTypedBox = new TypedBox<>(new SimpleBox("0"));

        TwoTypedBox<Integer, String> twoTypedBox= new TwoTypedBox<>(42, "195");
        TwoTypedBox<Integer, Integer> twoTypedBox1 = new TwoTypedBox<>(42, 195);
        twoTypedBox.showTypes();
        twoTypedBox1.showTypes();


//

    }
}

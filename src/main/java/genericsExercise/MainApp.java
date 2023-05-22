package genericsExercise;

public class MainApp {
    public static void main(String[] args) {
//        int[] nums = new int[]{1, 2, 3};
//        arrToList(nums);


        Apple apple1 = new Apple();
        Apple apple2 = new Apple();
        Apple apple3 = new Apple();
        Apple apple4 = new Apple();
        Apple apple5 = new Apple();


        Orange orange1 = new Orange();
        Orange orange2 = new Orange();
        Orange orange3 = new Orange();
        Orange orange4 = new Orange();
        Orange orange5 = new Orange();

        Box<Apple> appleBox = new Box();
        Box<Orange> orangeBox = new Box();
        Box<Orange> emptyBox = new Box();

        appleBox.add(apple1, apple2, apple3, apple4, apple5);
        System.out.println("applebox = " + appleBox);
        System.out.println("applebox box wght = " + appleBox.getWeight());
        orangeBox.add(orange1, orange2, orange3, orange4, orange5);
        System.out.println("orangebox = " + orangeBox);
        System.out.println("orange box wght = " + orangeBox.getWeight());
        emptyBox.shift(orangeBox);

        System.out.println(emptyBox);
        System.out.println(orangeBox);
        System.out.println("or box wght after shift = " + orangeBox.getWeight());
        System.out.println(orangeBox.compare(appleBox));
        System.out.println(appleBox.compare(appleBox));




    }
//    public static List<Integer> arrToList(int[] arr) {
//        List<Integer> list = new ArrayList<>();
//        for (int i = 0; i < arr.length; i++) {
//            list.add(arr[i]);
//        }
//        System.out.println(list);
//        return list;
//    }
}

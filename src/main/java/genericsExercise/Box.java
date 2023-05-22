package genericsExercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Box <T extends Fruit>{

    public List<T> items;

    public Box(T... items) {
        this.items = new ArrayList<T>(Arrays.asList(items));
    }

    public float getWeight(){
        float weight = 0;

//        return items.size() != 0 ? Arrays.stream(items.stream().map(i -> i.getWeight()).toArray()..sum().forEach(i - > ); : 0;
        if (items.size() !=0) {
            for (T item : items) {
                weight+= item.getWeight();
            }
        }
        return weight;
    }

    public void add (T... items) {
        this.items.addAll(Arrays.asList(items));
    }

    public void shift(Box<T> box) {
        this.items.addAll(box.items);
        box.items.clear();
    }


    public boolean compare(Box<?> box) {
        return Math.abs(this.getWeight() - box.getWeight()) < 0.000001;
    }

    @Override
    public String toString() {
        return "Box{" +
                "items=" + items +
                '}';
    }
}

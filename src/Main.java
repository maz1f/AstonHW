import first.CustomArrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CustomArrayList<Integer> list = new CustomArrayList<>(52, 10, 3, 5, 102, 1952, 11, 0, 12, 54, 5, 10, 22, 192);
        List<Integer> listForAdd = new ArrayList<>(Arrays.asList(1, 12, 10, 3));

        list.add(2, 6);
        list.set(3, 8);
        list.addAll(listForAdd);
        list.remove(Integer.valueOf(5));
        list.remove(2);

        System.out.println(list);
        list.sort(Comparator.comparingInt(o -> o));
        System.out.println(list);

        System.out.println(list.get(5));
        list.clear();
        System.out.println(list + " IS EMPTY: " + list.isEmpty());
    }
}
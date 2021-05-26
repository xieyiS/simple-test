package test;

import java.util.ArrayList;

public class ListIterator {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        java.util.ListIterator<Integer> integerListIterator = list.listIterator();
        while(integerListIterator.hasNext()){
            /*if(integerListIterator.nextIndex() == 1){
                integerListIterator.set(22);
            }*/
            if(integerListIterator.nextIndex() == 1){
                integerListIterator.add(22);
            }
            System.out.println(integerListIterator.nextIndex() + " : " + integerListIterator.next());
        }

    }
}

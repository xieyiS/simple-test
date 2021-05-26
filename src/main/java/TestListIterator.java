import java.util.ArrayList;
import java.util.ListIterator;

public class TestListIterator {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        ListIterator<Integer> integerListIterator = list.listIterator();
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

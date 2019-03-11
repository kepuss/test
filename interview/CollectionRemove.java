import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CollectionRemove {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        //Skips element 3
        for(int i = 0; i <list.size(); i++){
            System.out.println(list.get(i));
            if( list.get(i).intValue() == 2){
                list.remove(i);
            }
        }

        // Exception
        for(Integer elem : list){
            System.out.println(elem);
            if(elem.intValue() == 3) {
                list.remove(elem);
            }
        }

        // Displays all elements
        for(Iterator<Integer> i = list.iterator(); i.hasNext();) {
            Integer elem = i.next();
            System.out.println(elem);
            if(elem == 3) {
                i.remove();
            }
        }

//        System.out.println(list.size());
    }




}

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class DoStuff {
    public String stuffName;
    public String result;

    public void displayFirstSmallerThan3(List<Integer> e){
        e.stream()
                .filter(n -> n < 3)
                .limit(1)
                .peek(System.out::print);
    }

    public List<Integer> createAscList(Integer size){
        List<Integer> ascList = new ArrayList<>();
        IntStream.range(0,size)
                .parallel()
                .forEach(ascList::add);
        return ascList;
    }

    public String getMessageWithoutPrefix(String a){
        if(a.startsWith("CUSTOM")){
            a.substring(0,6);
            return a;
        }else
            return a;
    }

    public Integer getSum(Object a, Object b){
        int temporaryVariable = (Integer)a + (Integer)b;
        return temporaryVariable;
    }

    public Integer makeDecision(String a, Integer b, Integer c, Integer d){
        if(a == "buy"){
            if(b == 2){
                if(c == 4){
                    if(d == 5)
                        return b * c % 5;
                }
            }
            if(b == 3){
                return 0;
            }
            if(b == 7){
                return 9;
            }
        }
        return 0;
    }

    public Integer divide(Integer a, Integer b){
        try {
            return a/b;
        }catch (Exception ex){
            return 1;
        }
    }


    public String getStuffName() {
        return "This is stuff name "+stuffName;
    }
}

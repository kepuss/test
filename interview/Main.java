import java.util.List;

public class Main {

    public static void main(String[] args) {
        assert longestIntersection("aaaaaa","bbbbb").equals("");
        assert longestIntersection("aaaaaa","bbbab").equals("a");
        assert longestIntersection("aaaaaa","baabbbb").equals("aa");
        assert longestIntersection("123456","bb234bb").equals("234");
        assert longestIntersection("AbbaAbbaAbbba","AbAbbBbbba").equals("bbba");
        assert longestIntersection("12ccc34","aaa34vvv12").equals("12");
    }

    /*
        LongestIntersection method returns the longest common String from input strings
        If there is no common string it returns empty string
        If there is more than one common string with the same length it returns the first from String a
     */
    public static String longestIntersection(String a, String b)
    {
        String intersection = "";
        for(int i=0; i<a.length(); i++){
            for(int pointer = i;pointer<=a.length();pointer++){
                String substring = a.substring(i,pointer);
                if(b.contains(substring)){
                    if(b.contains(substring) && substring.length() > intersection.length()){
                        intersection = substring;
                    }
                }
            }
        }
        return intersection;
    }



}

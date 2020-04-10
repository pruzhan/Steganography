import java.util.ArrayList;

public class FirstMethod {
    StringBuffer buf;
    ArrayList<Integer> wordCode;
    int i, j;

    public FirstMethod(StringBuffer buffer, ArrayList<Integer> arr) {
        this.buf = buffer;
        this.wordCode = arr;
    }

    public FirstMethod(StringBuffer buffer) {
        this.buf = buffer;
    }

    public StringBuffer setSymbols() {
        i = 0;
        j = 0;
        while (i < buf.length() & j < wordCode.size()) {
            if (buf.charAt(i) == 'о' & wordCode.get(j) == 0) {
                buf.setCharAt(i, 'o');
                j++;
                if (j == wordCode.size()) break;
            }
            if (buf.charAt(i) == 'е' & wordCode.get(j) == 1) {
                buf.setCharAt(i, 'e');
                j++;
                if (j == wordCode.size()) break;
            }
            i++;
        }
        return buf;
    }

    public ArrayList<Integer> getSymbols() {
        i = 0;
        ArrayList<Integer> binCode = new ArrayList<>();
        while (i < buf.length()) {
            if (buf.charAt(i) == 'o') binCode.add(0);
            else if (buf.charAt(i) == 'e') binCode.add(1);
            i++;
        }
        return binCode;
    }
}
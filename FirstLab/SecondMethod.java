import java.util.ArrayList;

public class SecondMethod {

    StringBuffer buf;
    ArrayList<Integer> wordCode;
    int i, j;

    public SecondMethod(StringBuffer buffer, ArrayList<Integer> arr) {
        this.buf = buffer;
        this.wordCode = arr;
    }

    public SecondMethod(StringBuffer buffer) {
        this.buf = buffer;
    }

    public StringBuffer setSpaces() {
        i = 0;
        j = 0;
        while (i < buf.length() & j < wordCode.size()) {
            if (buf.charAt(i) == '\n') {
                if (wordCode.get(j) == 0) {
                    buf.insert(i, " ");
                    i++;
                }
                if (wordCode.get(j) == 1) {
                    buf.insert(i, "  ");
                    i += 2;
                }
                j++;
            }
            i++;
        }
        return buf;
    }

    public ArrayList<Integer> getSpaces() {
        i = 0;
        ArrayList<Integer> binCode = new ArrayList<>();
        while (i < buf.length()) {
            if (buf.charAt(i) == '\n') {
                if (buf.charAt(i - 2) == ' ' & buf.charAt(i - 1) == ' ') binCode.add(1);
                else if (buf.charAt(i - 1) == ' ') binCode.add(0);
            }
            i++;
        }
        return binCode;
    }
}
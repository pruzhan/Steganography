import java.util.ArrayList;

public class ThirdMethod {
    StringBuffer buf;
    ArrayList<Integer> wordCode;
    int i, j;

    public ThirdMethod(StringBuffer buffer, ArrayList<Integer> arr) {
        this.buf = buffer;
        this.wordCode = arr;
    }

    public ThirdMethod(StringBuffer buffer) {
        this.buf = buffer;
    }

    public StringBuffer setSpec() {
        i = 0;
        j = 0;
        while (i < buf.length() & j < wordCode.size()) {
            if (i + 3 >= buf.length()) break;
            if (buf.substring(i, i + 3).equals("...") & wordCode.get(j) == 1) {
                buf.replace(i, i + 3, "…");
                j++;
                i += 3;
				if (j==wordCode.size()) break;
            }
            if (buf.charAt(i) == ':' & wordCode.get(j) == 0) {
                buf.setCharAt(i, '։');
                j++;
				if (j==wordCode.size()) break;
            }
            i++;
        }
        return buf;
    }

    public ArrayList<Integer> getSpec() {
        i = 0;
        ArrayList<Integer> binCode = new ArrayList<>();
        while (i < buf.length()) {
            if (buf.charAt(i) == '…') binCode.add(1);
            else if (buf.charAt(i) == '։') binCode.add(0);
            i++;
        }
        return binCode;
    }
}

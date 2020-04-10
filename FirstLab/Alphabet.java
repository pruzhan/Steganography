import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Alphabet {

    ArrayList<Integer> codelist = new ArrayList<>();
    HashMap<Character, String> charsCodes = new HashMap<>();

    public Alphabet() {
        charsCodes.put('A', "10000000");
        charsCodes.put('Б', "10000001");
        charsCodes.put('В', "10000010");
        charsCodes.put('Г', "10000011");
        charsCodes.put('Д', "10000100");
        charsCodes.put('Е', "10000101");
        charsCodes.put('Ж', "10000110");
        charsCodes.put('З', "10000111");
        charsCodes.put('И', "10001000");
        charsCodes.put('Й', "10001001");
        charsCodes.put('К', "10001010");
        charsCodes.put('Л', "10001011");
        charsCodes.put('М', "10001100");
        charsCodes.put('Н', "10001101");
        charsCodes.put('О', "10001110");
        charsCodes.put('П', "10001111");
        charsCodes.put('Р', "10010000");
        charsCodes.put('С', "10010001");
        charsCodes.put('Т', "10010010");
        charsCodes.put('У', "10010011");
        charsCodes.put('Ф', "10010100");
        charsCodes.put('Х', "10010101");
        charsCodes.put('Ц', "10010110");
        charsCodes.put('Ч', "10010111");
        charsCodes.put('Ш', "10011000");
        charsCodes.put('Щ', "10011001");
        charsCodes.put('Ъ', "10011010");
        charsCodes.put('Ы', "10011011");
        charsCodes.put('Ь', "10011100");
        charsCodes.put('Э', "10011101");
        charsCodes.put('Ю', "10011110");
        charsCodes.put('Я', "10011111");
        charsCodes.put('а', "11100000");
        charsCodes.put('б', "11100001");
        charsCodes.put('в', "11100010");
        charsCodes.put('г', "11100011");
        charsCodes.put('д', "11100100");
        charsCodes.put('е', "11100101");
        charsCodes.put('ж', "11100110");
        charsCodes.put('з', "11100111");
        charsCodes.put('и', "11101000");
        charsCodes.put('й', "11101001");
        charsCodes.put('к', "11101010");
        charsCodes.put('л', "11101011");
        charsCodes.put('м', "11101100");
        charsCodes.put('н', "11101101");
        charsCodes.put('о', "11101110");
        charsCodes.put('п', "11101111");
        charsCodes.put('р', "11110000");
        charsCodes.put('с', "11110001");
        charsCodes.put('т', "11110010");
        charsCodes.put('у', "11110011");
        charsCodes.put('ф', "11110100");
        charsCodes.put('х', "11110101");
        charsCodes.put('ц', "11110110");
        charsCodes.put('ч', "11110111");
        charsCodes.put('ш', "11111000");
        charsCodes.put('щ', "11111001");
        charsCodes.put('ъ', "11111010");
        charsCodes.put('ы', "11111011");
        charsCodes.put('ь', "11111100");
        charsCodes.put('э', "11111101");
        charsCodes.put('ю', "11111110");
        charsCodes.put('я', "11111111");
    }

    public ArrayList<Integer> wordToBinCode(String word) {
        char[] wordarr = word.toCharArray();
        for (char i : wordarr) {
            String code = charsCodes.get(i);
            char[] codearr = code.toCharArray();
            for (char j : codearr) codelist.add((int) j % 2);
        }
        return codelist;
    }

    public void binCodeToWord(ArrayList<Integer> bincode) {
        int i = 0;
        StringBuffer charcode = new StringBuffer();
        StringBuffer word = new StringBuffer();
        ArrayList<String> binword = new ArrayList<>();
        while (i < bincode.size()) {
            charcode.append(bincode.get(i));
            if (charcode.length() == 8) {
                binword.add(charcode.toString());
                charcode.delete(0, 8);
            }
            i++;
        }
        i = 0;
        while (i < binword.size()) {
            for (Map.Entry entry : charsCodes.entrySet()) {
                if (entry.getValue().equals(binword.get(i))) word.append(entry.getKey());
            }
            i++;
        }
        System.out.println(word.toString());
    }
}
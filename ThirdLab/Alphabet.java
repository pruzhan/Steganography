import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Alphabet {

    private ArrayList<Integer> codelist = new ArrayList<>();
    private HashMap<Character, String> charsCodes = new HashMap<>();

    Alphabet() {
        charsCodes.put(' ', "00100000");
        charsCodes.put('-', "00101101");
        charsCodes.put('+', "00101011");
        charsCodes.put('.', "00101110");
        charsCodes.put('!', "00100001");
        charsCodes.put('?', "00111111");
        charsCodes.put(':', "00111010");
        charsCodes.put(';', "00111011");
        charsCodes.put('0', "00110000");
        charsCodes.put('1', "00110001");
        charsCodes.put('2', "00110010");
        charsCodes.put('3', "00110011");
        charsCodes.put('4', "00110100");
        charsCodes.put('5', "00110101");
        charsCodes.put('6', "00110110");
        charsCodes.put('7', "00110111");
        charsCodes.put('8', "00111000");
        charsCodes.put('9', "00111001");
        charsCodes.put('(', "00101000");
        charsCodes.put(')', "00101001");
        charsCodes.put(',', "00101100");
        charsCodes.put('A', "11000000");
        charsCodes.put('Б', "11000001");
        charsCodes.put('В', "11000010");
        charsCodes.put('Г', "11000011");
        charsCodes.put('Д', "11000100");
        charsCodes.put('Е', "11000101");
        charsCodes.put('Ж', "11000110");
        charsCodes.put('З', "11000111");
        charsCodes.put('И', "11001000");
        charsCodes.put('Й', "11001001");
        charsCodes.put('К', "11001010");
        charsCodes.put('Л', "11001011");
        charsCodes.put('М', "11001100");
        charsCodes.put('Н', "11001101");
        charsCodes.put('О', "11001110");
        charsCodes.put('П', "11001111");
        charsCodes.put('Р', "11010000");
        charsCodes.put('С', "11010001");
        charsCodes.put('Т', "11010010");
        charsCodes.put('У', "11010011");
        charsCodes.put('Ф', "11010100");
        charsCodes.put('Х', "11010101");
        charsCodes.put('Ц', "11010110");
        charsCodes.put('Ч', "11010111");
        charsCodes.put('Ш', "11011000");
        charsCodes.put('Щ', "11011001");
        charsCodes.put('Ъ', "11011010");
        charsCodes.put('Ы', "11011011");
        charsCodes.put('Ь', "11011100");
        charsCodes.put('Э', "11011101");
        charsCodes.put('Ю', "11011110");
        charsCodes.put('Я', "11011111");
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

    ArrayList<Integer> wordToBinCode(String word) {
        char[] wordarr = word.toCharArray();
        ArrayList<String> oops = new ArrayList<>();
        for (char i : wordarr) {
            String code = charsCodes.get(i);
            oops.add(code);
            char[] codearr = code.toCharArray();
            for (char j : codearr) codelist.add((int) j % 2);
        }
        System.out.println(oops);
        return codelist;
    }

    String binCodeToWord(ArrayList<Integer> bincode, int wordlength) {
        int i = 0;
        StringBuilder charcode = new StringBuilder();
        StringBuilder word = new StringBuilder();
        ArrayList<String> binword = new ArrayList<>();
        while (i < bincode.size() && binword.size()<wordlength) {
            charcode.append(bincode.get(i));
            if (charcode.length() == 8) {
                binword.add(charcode.toString());
                charcode.delete(0, 8);
            }
            i++;
        }
        i = 0;
        System.out.println(binword);
        while (i < binword.size()) {
            for (Map.Entry entry : charsCodes.entrySet()) {
                if (entry.getValue().equals(binword.get(i))) word.append(entry.getKey());
            }
            i++;
        }
        return word.toString();
    }
}
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {

    StringBuffer buf = new StringBuffer();
    ArrayList<Integer> byteArray, methodCode;
    int action, method;
    Scanner name = new Scanner(System.in);

    public void mainController() {
        Scanner choose = new Scanner(System.in);
        System.out.println("Выберите действие (1-2):");
        System.out.println("1) Зашифровать");
        System.out.println("2) Расшифровать");
        try {
            action = choose.nextInt();
            if (action > 2 | action < 1) throw new Exception("Вы можете выбрать только 1 или 2.");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return;
        }
        System.out.println("Выберите метод (1-3):");
        try {
            method = choose.nextInt();
            if (method > 3 | method < 1) throw new Exception("Вы можете выбрать только 1 из 3 доступных методов.");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return;
        }
        System.out.println("Введите имя файла для чтения:");
        String inputFileName = name.nextLine();
        try (FileReader reader = new FileReader(inputFileName)) {
            int c;
            while ((c = reader.read()) != -1) {
                buf.append((char) c);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        Alphabet alphabet = new Alphabet();
        if (action == 1) {
            Scanner wordReader = new Scanner(System.in);
            System.out.println("Введите слово:");
            String word = wordReader.nextLine();
            byteArray = alphabet.wordToBinCode(word);
            switch (method) {
                case 1: {
                    FirstMethod firstMethod = new FirstMethod(buf, byteArray);
                    buf = firstMethod.setSymbols();
                    break;
                }
                case 2: {
                    SecondMethod secondMethod = new SecondMethod(buf, byteArray);
                    buf = secondMethod.setSpaces();
                    break;
                }
                case 3: {
                    ThirdMethod thirdMethod = new ThirdMethod(buf, byteArray);
                    buf = thirdMethod.setSpec();
                    break;
                }
            }
            System.out.println("Введите имя файла для вывода результата:");
            String outputFileName = wordReader.nextLine();
            try (FileWriter writer = new FileWriter(outputFileName)) {
                writer.write(buf.toString());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        if (action == 2) {
            switch (method) {
                case 1: {
                    FirstMethod firstMethod = new FirstMethod(buf);
                    methodCode = firstMethod.getSymbols();
                    break;
                }
                case 2: {
                    SecondMethod secondMethod = new SecondMethod(buf);
                    methodCode = secondMethod.getSpaces();
                    break;
                }
                case 3: {
                    ThirdMethod thirdMethod = new ThirdMethod(buf);
                    methodCode = thirdMethod.getSpec();
                    break;
                }
            }
            alphabet.binCodeToWord(methodCode);
        }
    }
}
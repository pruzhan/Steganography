import java.io.IOException;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) throws Exception {
        Scanner c = new Scanner(System.in);
        System.out.println("Выберите действие:\n1) Встроить информацию\n2) Извлечь информацию");
        int action = c.nextInt();
        switch (action) {
            case 1: {
                ImageEditor creator = new ImageEditor(args[0], args[1]);
                creator.createStgrImage();
                break;
            }
            case 2: {
                GetInformation getter = new GetInformation(args[0]);
                String info = getter.getInfo();
                System.out.println(info);
                break;
            }
            default: {
                throw new IOException("Неверное действие");
            }
        }
    }
}

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) throws Exception {
        ArrayList<Integer> Xarray = new ArrayList<>();
        ArrayList<Double> Yarray = new ArrayList<>();
        Scanner c = new Scanner(System.in);
        while (true) {
            System.out.println("Выберите действие:\n1) Встроить информацию\n2) Извлечь информацию\n3) График\n4) Выход");
            int action = c.nextInt();
            switch (action) {
                case 1: {
                    ImageEditor creator = new ImageEditor(args[0], args[1]);
                    creator.createStgrImage();
                    Xarray.add(creator.wordLength);
                    Yarray.add(creator.PSNR);
                    break;
                }
                case 2: {
                    GetInformation getter = new GetInformation(args[1]);
                    String info = getter.getInfo();
                    System.out.println(info);
                    break;
                }
                case 3: {
                    System.out.println(Xarray);
                    System.out.println(Yarray);
                    try (FileWriter writer = new FileWriter("Arrays.txt")) {
                        for (int i = 0; i < Xarray.size(); i++) {
                            writer.write(Xarray.get(i) + "," + Yarray.get(i)+"\n");
                        }
                        
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
					Process process = Runtime.getRuntime().exec("python3 Draw.py");
                    process.waitFor();
                    process.destroy();
                    break;
                }
                case 4: {
                    return;
                }
                default: {
                    throw new IOException("Неверное действие");
                }
            }
        }
    }
}

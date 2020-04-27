import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

class ImageEditor {
    private String inputFilePath, outputFilePath;

    ImageEditor(String inputFilePath, String outputFilePath) {
        this.inputFilePath = inputFilePath;
        this.outputFilePath = outputFilePath;
    }

    void createStgrImage() {
        try {
            File input = new File(inputFilePath);
            BufferedImage inputImage = ImageIO.read(input);
            BufferedImage outputImage = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(), inputImage.getType());
            Scanner wordReader = new Scanner(System.in);
            System.out.println("Встраиваемая информация:");
            String word = wordReader.nextLine();
            int wordLength = word.length();
            String binaryLength = toBinString(wordLength);
            int count = (inputImage.getWidth() - 1) * inputImage.getHeight();
            if (wordLength > count / 8) throw new Exception("Информация не поместится в контейнер");
            int mark = wordLength*8;
            Alphabet alphabet = new Alphabet();
            ArrayList<Integer> binword = alphabet.wordToBinCode(word);
            for (int y = 0; y < inputImage.getHeight() - binaryLength.length(); y++) {
                Color pixelColor = new Color(inputImage.getRGB(0, y));
                int green = pixelColor.getGreen();
                int blue = pixelColor.getBlue();
                int red = pixelColor.getRed() - pixelColor.getRed() % 2;
                Color newPixelColor = new Color(red, green, blue);
                outputImage.setRGB(0, y, newPixelColor.getRGB());
            }
            for (int y = inputImage.getHeight() - binaryLength.length(); y < inputImage.getHeight(); y++) {
                Color pixelColor = new Color(inputImage.getRGB(0, y));
                int green = pixelColor.getGreen();
                int blue = pixelColor.getBlue();
                int red = pixelColor.getRed();
                int bit = (int) binaryLength.charAt(y + binaryLength.length() - inputImage.getHeight()) % 2;
                if (red % 2 < bit) red += 1;
                else if (red % 2 > bit) red -= 1;
                Color newPixelColor = new Color(red, green, blue);
                outputImage.setRGB(0, y, newPixelColor.getRGB());
            }
            int pixelNumber = 0;
            int bitNumber = 0;
            for (int x = 1; x < inputImage.getWidth(); x++) {
                for (int y = 0; y < inputImage.getHeight(); y++) {
                    Color pixelColor = new Color(inputImage.getRGB(x, y));
                    int red = pixelColor.getRed();
                    int green = pixelColor.getGreen();
                    int blue = pixelColor.getBlue();
                    if (pixelNumber/mark == 0) {
                        if (red % 2 < binword.get(bitNumber)) red += 1;
                        else if (red % 2 > binword.get(bitNumber)) red -= 1;
                        bitNumber++;
                    }
                    Color newPixelColor = new Color(red, green, blue);
                    outputImage.setRGB(x, y, newPixelColor.getRGB());
                    pixelNumber++;
                }
            }
            File output = new File(outputFilePath);
            ImageIO.write(outputImage, "bmp", output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String toBinString(int number) {
        StringBuilder binaryString = new StringBuilder();
        while (number != 0) {
            binaryString.append(number % 2);
            number /= 2;
        }
        binaryString.reverse();
        return binaryString.toString();
    }
}

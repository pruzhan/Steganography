import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

class ImageEditor {
    private String inputFilePath, outputFilePath;
    int wordLength;
    double PSNR, RMSE;

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
            wordLength = word.length();
            int count = inputImage.getWidth() * inputImage.getHeight() - 8;
            if (wordLength > count / 8) throw new Exception("Информация не поместится в контейнер");
            Alphabet alphabet = new Alphabet();
            ArrayList<Integer> binword = alphabet.wordToBinCode(word);
            int bitNumber = 0;
            for (int x = 0; x < inputImage.getWidth(); x += 8) {
                for (int y = 0; y < inputImage.getHeight(); y += 8) {
                    Block block = new Block();
                    for (int xi = x; xi < x + 8; xi++) {
                        for (int yi = y; yi < y + 8; yi++) {
                            Color pixelColor = new Color(inputImage.getRGB(xi, yi));
                            block.colors[xi - x][yi - y] = pixelColor;
                        }
                    }
                    block.DCT();
                    bitNumber = block.SetInfo(binword, bitNumber);
                    block.IDCT();
                    block.SetBlock(outputImage, x, y);
                }
            }
            File output = new File(outputFilePath);
            ImageIO.write(outputImage, "bmp", output);
            PSNR psnr = new PSNR(inputFilePath, outputFilePath);
            PSNR = psnr.PSNR;
            RMSE = psnr.RMSE;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
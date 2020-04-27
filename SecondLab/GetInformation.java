import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

class GetInformation {
    private String filepath;
    GetInformation(String filepath) {
        this.filepath=filepath;
    }

    String getInfo() {
        StringBuilder info = new StringBuilder();
        try {
            File input = new File(filepath);
            BufferedImage image = ImageIO.read(input);
            StringBuilder binLength = new StringBuilder();
            ArrayList<Integer> bininfo = new ArrayList<>();
            for (int y = 0; y < image.getHeight(); y++) {
                Color pixelColor = new Color(image.getRGB(0, y));
                int red = pixelColor.getRed();
                binLength.append(red % 2);
            }
            binLength.delete(0, binLength.indexOf("1"));
            int lengthinfo = toInt(binLength.toString());
            int infopixelNumber = 0;
            int infomarker = lengthinfo*8;
            for (int x = 1; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    Color pixelColor = new Color(image.getRGB(x, y));
                    if (infopixelNumber/infomarker == 0) {
                        int red = pixelColor.getRed();
                        bininfo.add(red%2);
                    }
                    infopixelNumber++;
                }
            }
            Alphabet alphabet = new Alphabet();
            info.append(alphabet.binCodeToWord(bininfo));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return info.toString();
    }

    private int toInt(String binnumber) {
        int number = 0;
        char[] arr = binnumber.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '1') number += Math.pow(2, arr.length - i - 1);
        }
        return number;
    }
}

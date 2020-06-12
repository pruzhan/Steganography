import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

class GetInformation {
    private String filepath;
    private int wordlength;

    GetInformation(String filepath, int wordlength) {
        this.filepath = filepath;
        this.wordlength = wordlength;
    }

    String getInfo() {
        StringBuilder info = new StringBuilder();
        try {
            File input = new File(filepath);
            BufferedImage image = ImageIO.read(input);
            ArrayList<Integer> bininfo = new ArrayList<>();
            for (int x = 0; x < image.getWidth(); x += 8) {
                for (int y = 0; y < image.getHeight(); y += 8) {
                    Block block = new Block();
                    for (int xi = x; xi < x + 8; xi++) {
                        for (int yi = y; yi < y + 8; yi++) {
                            Color pixelColor = new Color(image.getRGB(xi, yi));
                            block.colors[xi - x][yi - y] = pixelColor;
                        }
                    }
                    block.DCT();
                    block.GetInfo(bininfo);
                }
            }
            Alphabet alphabet = new Alphabet();
            info.append(alphabet.binCodeToWord(bininfo, wordlength));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return info.toString();
    }
}
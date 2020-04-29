import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class PSNR {
    double PSNR;

    PSNR(String firstImage, String secondImage) throws IOException {
        File input_1 = new File(firstImage);
        BufferedImage first = ImageIO.read(input_1);
        File input_2 = new File(secondImage);
        BufferedImage second = ImageIO.read(input_2);
        double MSEr = 0;
        double MSEg = 0;
        double MSEb = 0;
        double MSE;
        int m = first.getWidth();
        int n = first.getHeight();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Color I = new Color(first.getRGB(i, j));
                Color K = new Color(second.getRGB(i, j));
                MSEr += (1.0 / (m * n)) * Math.pow(Math.abs((I.getRed() - K.getRed())), 2);
                MSEg += (1.0 / (m * n)) * Math.pow(Math.abs((I.getGreen() - K.getGreen())), 2);
                MSEb += (1.0 / (m * n)) * Math.pow(Math.abs((I.getBlue() - K.getBlue())), 2);
            }
        }
        MSE = (MSEr + MSEg + MSEb) / 3.0;
        System.out.println("MSE = " + MSE);
        PSNR = Math.round(10 * Math.log10(Math.pow(255, 2) / MSE) * 100) / 100.0;
        System.out.println("PSNR = " + PSNR + "dB");
    }
}

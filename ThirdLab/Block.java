import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

class Block {
    Color[][] colors = new Color[8][8];
    double[][] DCTcoeff = new double[8][8];
    private int[][] pixel = new int[8][8];
    private int[][] result = new int[8][8];
    private final double PI = 3.1415;

    private void Transform() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                pixel[x][y] = colors[x][y].getRed();
                pixel[x][y] -= 128;
            }
        }
    }

    void DCT() {
        Transform();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                double coef = 0.0;
                for (int x = 0; x < 8; x++) {
                    for (int y = 0; y < 8; y++) {
                        coef += C(i) * C(j) * pixel[x][y] * Math.cos((2 * x + 1) * PI * i / 16.0) * Math.cos((2 * y + 1) * PI * j / 16.0);
                    }
                }
                DCTcoeff[i][j] = coef;
            }
        }
    }

    int SetInfo(ArrayList<Integer> binWord, int bitNumber) {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (bitNumber < binWord.size()) {
                    DCTcoeff[x][y] = Math.round(DCTcoeff[x][y]);
                    if (Math.abs((int) DCTcoeff[x][y] % 2) == 0 && binWord.get(bitNumber) == 1) DCTcoeff[x][y] += 1.0;
                    else if (Math.abs((int) DCTcoeff[x][y] % 2) == 1 && binWord.get(bitNumber) == 0)
                        DCTcoeff[x][y] -= 1.0;
                    bitNumber++;
                }
            }
        }
        return bitNumber;
    }

    void GetInfo(ArrayList<Integer> binword) {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                DCTcoeff[x][y] = Math.round(DCTcoeff[x][y]);
                binword.add(Math.abs((int) DCTcoeff[x][y] % 2));
            }
        }
    }

    void IDCT() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                float pluha = (float) 0.0;
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        pluha += C(i) * C(j) * DCTcoeff[i][j] * Math.cos((2 * x + 1) * PI * i / 16.0) * Math.cos((2 * y + 1) * PI * j / 16.0);
                    }
                }
                result[x][y] = Math.round(pluha);
            }
        }
    }

    void SetBlock(BufferedImage out, int x, int y) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int red = result[i][j] + 128;
                if (red < 0) red = 0;
                if (red > 255) red = 255;
                int green = colors[i][j].getGreen();
                int blue = colors[i][j].getBlue();
                Color res = new Color(red, green, blue);
                out.setRGB(i + x, j + y, res.getRGB());
            }
        }
    }

    private double C(int f) {
        double c;
        if (f == 0) c = 1.0 / Math.sqrt(8.0);
        else c = Math.sqrt(2.0 / 8.0);
        return c;
    }
}

package com.yjymh.penny.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class QRCodeUtil {
    private static final String CHARSET = "utf-8";
    private static final String FORMAT_NAME = "JPG";
    // 二维码尺寸    
    private static final int QRCODE_SIZE = 300;
    // LOGO宽度    
    private static final int WIDTH = 300;
    // LOGO高度    
    private static final int HEIGHT = 300;
    private static final int BlACK = 0xff000000;
    private static final int WHITE = 0xFFFFFFFF;


    public static void encode(String contents) {
        encode(contents, new File("test.png"), BarcodeFormat.QR_CODE);
    }

    public static void encode(String contents, File file, BarcodeFormat format) {
        try {
            contents = new String(contents.getBytes(CHARSET), "ISO-8859-1");
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, format, WIDTH, HEIGHT);
            writeToFile(bitMatrix, "png", file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeToFile(BitMatrix matrix, String format, File file) throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        ImageIO.write(image, format, file);
    }

    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                image.setRGB(x, y, matrix.get(x, y) == true ? BlACK : WHITE);
            }
        }
        return image;
    }

    public static void main(String[] args) {
        QRCodeUtil qrCodeUtil = new QRCodeUtil();
        File file = new File("test.png");
        qrCodeUtil.encode("hello", file, BarcodeFormat.QR_CODE);
    }

    public void decode(File file) {
        BufferedImage image;

        try {
            ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}  
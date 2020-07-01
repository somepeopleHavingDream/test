package org.yangxin.test.zxing;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 二维码生成和读的工具类
 *
 * @author yangxin
 * 2020/06/30 16:30
 */
public class CreateQRCodeWithWords {

    /**
     * 生成包含字符串信息的二维码图片
     *
     * @param outputStream 文件输出流路径
     * @param content      二维码携带信息
     * @param qrCodeSize   二维码图片大小
     * @param imageFormat  二维码格式
     */
    @SuppressWarnings("SuspiciousNameCombination")
    public static void createQrCode(OutputStream outputStream, String content, int qrCodeSize, String imageFormat) {
        // 设置二维码纠错级别Map
        Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<>();
        // 纠错级别
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try {
            // 创建比特矩阵（位矩阵）的QR码编码的字符串
            BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, qrCodeSize, qrCodeSize, hintMap);

            // 使用BufferedImage勾画QRCode（matrixWidth是行二维码像素点）
            int matrixWidth = bitMatrix.getWidth();
            BufferedImage bufferedImage = new BufferedImage(matrixWidth - 200,
                    matrixWidth - 200,
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2D = bufferedImage.createGraphics();
//            Graphics2D graphics2D = (Graphics2D) bufferedImage.getGraphics();
            graphics2D.setColor(Color.WHITE);
            graphics2D.fillRect(0, 0, matrixWidth, matrixWidth);

            // 使用比特矩阵画并保存图像
            graphics2D.setColor(Color.BLACK);
            for (int i = 0; i < matrixWidth; i++) {
                for (int j = 0; j < matrixWidth; j++) {
                    if (bitMatrix.get(i, j)) {
                        graphics2D.fillRect(i - 100, j - 100, 1, 1);
                    }
                }
            }
            bufferedImage.flush();

//            ImageIO.write(bufferedImage, imageFormat, outputStream);

            BufferedImage outImage = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
//            BufferedImage outImage = new BufferedImage(800, 800, BufferedImage.TYPE_INT_ARGB);
            Graphics2D outImageGraphics = outImage.createGraphics();
            outImageGraphics.setColor(Color.WHITE);
            outImageGraphics.fillRect(0, 0, 800, 800);
            outImageGraphics.setColor(Color.BLACK);

            // 画二维码到新的面板
            outImageGraphics.drawImage(bufferedImage,
                    100,
                    100,
                    bufferedImage.getWidth(),
                    bufferedImage.getHeight(), null);
//
            // 画文字到新的面板
            // 字体、字型、字号
            Font font = new Font("宋体", Font.PLAIN, 50);
            outImageGraphics.setFont(font);

            // 文字长度
//            int strWidth = outImageGraphics.getFontMetrics().stringWidth("B0000001");
            int strWidth = graphics2D.getFontMetrics().stringWidth("B0000001");
            // 总长度减去文字长度的一般（居中显示）
            int wordStartX = (600 - strWidth) / 2;
//            int wordStartX = (700 - strWidth) / 2;
            // 画文字
            outImageGraphics.drawString(content, wordStartX, 750);
            outImage.flush();
//
            ImageIO.write(outImage, imageFormat, outputStream);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }

    }

//    public static String getWillNo(String no) {
//        String s = no.substring(0, 6);
//        int willNo = Integer.parseInt(no.substring(6, 8));
//
//        return s + willNo + 1;
//    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("W0000021");

        String no = "W0000021";
        String fileName = "image/qrCodeWithWords.png";
        createQrCode(new FileOutputStream(new File(fileName)), no, 800, "PNG");
    }
}

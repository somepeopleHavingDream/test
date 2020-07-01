package org.yangxin.test.zxing;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * 生成条码号，底部带文字
 *
 * @author yangxin
 * 2020/07/01 09:05
 */
public class CreateBarcodeWithWords {

    public static void main(String[] args) {
        Code128Writer code128Writer = new Code128Writer();
        try {
            BitMatrix bitMatrix = code128Writer.encode("uzi is god forever!",
                    BarcodeFormat.CODE_128,
                    264,
                    48,
                    new HashMap<>());

            // 使用BufferedImage勾画Barcode
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            BufferedImage bufferedImage = new BufferedImage(width,
                    height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2D = bufferedImage.createGraphics();
            graphics2D.setColor(Color.WHITE);
            graphics2D.fillRect(0, 0, width, height);

            // 使用比特矩阵画并保存图像
            graphics2D.setColor(Color.BLACK);
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    if (bitMatrix.get(i, j)) {
                        graphics2D.fillRect(i, j, 1, 1);
                    }
                }
            }
            bufferedImage.flush();

            // 新的图片
            BufferedImage outImage = new BufferedImage(270, 60, BufferedImage.TYPE_INT_RGB);
            Graphics2D outImageGraphics = outImage.createGraphics();
            outImageGraphics.setColor(Color.WHITE);
            outImageGraphics.fillRect(0, 0, 270, 60);
            outImageGraphics.setColor(Color.BLACK);

            // 画条形码到新的面板
            outImageGraphics.drawImage(bufferedImage, 5, 5, 264, 48, null);

            // 画文字到新的面板
            // 字体、字型、字号
            Font font = new Font("微软雅黑", Font.PLAIN, 8);
            outImageGraphics.setFont(font);

            // 文字长度
            int strWidth = graphics2D.getFontMetrics().stringWidth("uzi is god forever!");
            // 总长度减去文字长度的一般（居中显示）
            int wordStartX = (300 - strWidth) / 2;
            outImageGraphics.drawString("20154206110", wordStartX, 60);
            outImage.flush();

            ImageIO.write(outImage, "PNG", new File("image/barcodeWithWords.png"));
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }
}

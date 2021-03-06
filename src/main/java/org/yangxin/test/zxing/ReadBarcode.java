package org.yangxin.test.zxing;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 读取条形码
 *
 * @author yangxin
 * 2020/06/28 10:25
 */
public class ReadBarcode {

    @SuppressWarnings("all")
    public static void main(String[] args) {
        MultiFormatReader multiFormatReader = new MultiFormatReader();
        File file = new File("barcode.png");
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            BinaryBitmap binaryBitmap = new BinaryBitmap(
                    new HybridBinarizer(
                    new BufferedImageLuminanceSource(bufferedImage)));

            // 定义二维码的参数
            Map hintMap = new HashMap();
            hintMap.put(EncodeHintType.CHARACTER_SET, "utf-8");

            Result result = multiFormatReader.decode(binaryBitmap, hintMap);

            System.out.println("解析结果：" + result.toString());
            System.out.println("二维码格式类型：" + result.getBarcodeFormat());
            System.out.println("二维码文本内容：" + result.getText());
        } catch (IOException | NotFoundException e) {
            e.printStackTrace();
        }
    }
}

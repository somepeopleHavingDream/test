package org.yangxin.test.zxing;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * 生成二维码
 *
 * @author yangxin
 * 2020/06/27 19:48
 */
public class CreateQRCode {

    public static void main(String[] args) {
        int width = 300;
        int height = 300;
        String format = "png";
        String content = "uzi is good forever!";

        // 定义二维码的参数
        Map<EncodeHintType, Object> hintMap = new HashMap<>();
        hintMap.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hintMap.put(EncodeHintType.MARGIN, 2);

        // 生成二维码
        try {
            BitMatrix bitMatrix = new MultiFormatWriter()
                    .encode(content, BarcodeFormat.QR_CODE, width, height, hintMap);
            Path path = new File("image/qrcode.png").toPath();
            MatrixToImageWriter.writeToPath(bitMatrix, format, path);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }
}

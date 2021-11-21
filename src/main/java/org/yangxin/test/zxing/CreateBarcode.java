package org.yangxin.test.zxing;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;

/**
 * 创建条形码
 *
 * @author yangxin
 * 2020/06/28 10:16
 */
public class CreateBarcode {

    /**
     * 生成Code128的条形码
     */
    public static void main(String[] args) {
        Code128Writer code128Writer = new Code128Writer();
        try {
            BitMatrix bitMatrix = code128Writer.encode("uzi is god forever!",
                    BarcodeFormat.CODE_128,
                    264,
                    48,
                    new HashMap<>());
            Path path = new File("barcode.png").toPath();
            MatrixToImageWriter.writeToPath(bitMatrix, "png", path);
            //            return MatrixToImageWriter.toBufferedImage(bitMatrix);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

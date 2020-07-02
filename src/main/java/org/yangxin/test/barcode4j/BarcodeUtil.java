package org.yangxin.test.barcode4j;

import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;
import org.springframework.util.StringUtils;

import java.awt.image.BufferedImage;
import java.io.*;

/**
 * 生成条形码工具（条形码）
 *
 * @author yangxin
 * 2020/07/02 14:31
 */
public class BarcodeUtil {

    /**
     * 生成文件
     */
    public static void generateFile(String msg, String path) {
        File file = new File(path);
        try {
            generate(msg, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成字节
     */
    private static byte[] generate(String msg) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        generate(msg, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    /**
     * 生成到流
     */
    private static void generate(String msg, OutputStream outputStream) {
        if (StringUtils.isEmpty(msg) || outputStream == null) {
            return;
        }

        Code128Bean code128Bean = new Code128Bean();

        // 精细度
        final int dpi = 120;
        // module宽度
        final double moduleWidth = UnitConv.in2mm(1.2f / dpi);

        // 配置对象
        code128Bean.setModuleWidth(moduleWidth);
        code128Bean.doQuietZone(false);

        String format = "image/png";
        // 输出到流
        BitmapCanvasProvider bitmapCanvasProvider = new BitmapCanvasProvider(outputStream,
                format,
                dpi,
                BufferedImage.TYPE_BYTE_BINARY,
                false,
                0);
        // 生成条形码
        code128Bean.generateBarcode(bitmapCanvasProvider, msg);

        // 结束绘制
        try {
            bitmapCanvasProvider.finish();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String msg = "BJBDN201811280001";
        String path = "image/barcodeByBarcode4j.png";
        generateFile(msg, path);
    }
}

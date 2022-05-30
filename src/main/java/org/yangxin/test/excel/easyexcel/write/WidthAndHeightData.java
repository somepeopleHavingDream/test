package org.yangxin.test.excel.easyexcel.write;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author yangxin
 * 2022/5/30 15:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@HeadRowHeight(value = 30)
@ContentRowHeight(value = 25)
@ColumnWidth(value = 20)
public class WidthAndHeightData {

    @ExcelProperty(value = "字符串标题")
    private String string;

    @ExcelProperty(value = "日期标题")
    private Date date;

    @ExcelProperty(value = "数字标题")
    @ColumnWidth(value = 25)
    private Double doubleData;
}

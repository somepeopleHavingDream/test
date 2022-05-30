package org.yangxin.test.excel.easyexcel.read;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yangxin
 * 2022/5/24 15:39
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DemoData implements Serializable {

    private static final long serialVersionUID = 3503283582663059020L;

    /*
        根据Excel中指定列名或列的索引读取
     */

    @ExcelProperty(value = "字符串标题", index = 0)
    private String name;

    @ExcelProperty(value = "日期标题", index = 1)
    @DateTimeFormat(value = "yyyy年MM月dd日 HH时mm分ss秒")
    private Date hireDate;

    @ExcelProperty(value = "数字标题", index = 2)
    @NumberFormat(value = "###.#")
    private String salary;
}

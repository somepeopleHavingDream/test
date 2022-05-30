package org.yangxin.test.excel.easyexcel.write;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author yangxin
 * 2022/5/30 14:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @ExcelProperty(value = "用户编号", index = 0)
    private Integer userId;

    @ExcelProperty(value = "姓名", index = 1)
    private String userName;

    @ExcelProperty(value = "性别", index = 3)
    private String gender;

    @ExcelProperty(value = "工资", index = 4)
    @NumberFormat(value = "###.#")
    private Double salary;

    @ExcelProperty(value = "入职时间", index = 2)
    @DateTimeFormat(value = "yyyy年MM月dd日 HH时mm分ss秒")
    private Date hireDate;
}

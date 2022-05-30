package org.yangxin.test.excel.easyexcel.write;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author yangxin
 * 2022/5/30 15:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComplexHeadUser {

    @ExcelProperty(value = {"group1", "用户编号"}, index = 0)
    private Integer userId;

    @ExcelProperty(value = {"group1", "姓名"}, index = 1)
    private String userName;

    @ExcelProperty(value = {"group2", "入职时间"}, index = 2)
    private Date hireDate;
}

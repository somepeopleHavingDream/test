package org.yangxin.test.excel.poi;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yangxin
 * 2019/11/07 10:06
 */
public class PoiExcelUtil {

    public static void writeExcel(XSSFWorkbook workbook,
                                  List<String[]> resourceList,
                                  String[] headerNames,
                                  String sheetName,
                                  Integer sheetNum,
                                  Integer columnNum) {
        // 创建表格
        XSSFSheet sheet = workbook.createSheet();
        // 默认宽度
        sheet.setDefaultRowHeightInPoints(13);
        workbook.setSheetName(sheetNum, sheetName);

        // 设置列宽
        for (int i = 0; i < columnNum; i++) {
            sheet.setColumnWidth(i, 6000);
        }

        /*
            创建合并区域
            CellRangeAddress(int 首行, int 最后一行, int 首列, int 最后一列)
         */
        CellRangeAddress address = new CellRangeAddress(0, 0, 0, columnNum);
        // 将创建的合并区域设置到表格中
        sheet.addMergedRegion(address);

        // 创建行
        XSSFRow header = sheet.createRow(0);
        // 创建单元格，合并后的单元格，编号合并
        // 设置样式
        XSSFCellStyle titleStyle = workbook.createCellStyle();
        XSSFFont titleFont = workbook.createFont();
        titleFont.setFontName("黑体");
        titleFont.setFontHeightInPoints((short) 20);
        titleFont.setBold(true);

        titleStyle.setFont(titleFont);
        titleStyle.setAlignment(HorizontalAlignment.CENTER);

        XSSFCell cell = header.createCell(0);
        cell.setCellValue(sheetName);
        cell.setCellStyle(titleStyle);

        // 编写表头
        // 定义表头的样式
        XSSFCellStyle headerStyle = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 16);
        headerStyle.setFont(font);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        // 设置单元格样式
        XSSFRow headerRow = sheet.createRow(1);
        for (int i = 0; i < headerNames.length; i++) {
            XSSFCell headerRowCell = headerRow.createCell(i);
            headerRowCell.setCellStyle(headerStyle);
            headerRowCell.setCellValue(headerNames[i]);
        }

        // 设置表格数据的样式
        XSSFCellStyle bodyStyle = workbook.createCellStyle();
        XSSFFont bodyFont = workbook.createFont();
        bodyFont.setFontName("微软雅黑");
        bodyFont.setFontHeightInPoints((short) 12);
        bodyStyle.setFont(bodyFont);

        // 编辑表格体数据
        for (int i = 0; i < resourceList.size(); i++) {
            // 获取行数据
            String[] tmp = resourceList.get(i);

            // 创建行
            XSSFRow bodyRow = sheet.createRow(i + 2);
            for (int j = 0; j < tmp.length; j++) {
                XSSFCell bodyCell = bodyRow.createCell(j);
                bodyCell.setCellStyle(bodyStyle);
                bodyCell.setCellValue(tmp[j]);
            }
        }

        sheet.getRow(0).setHeightInPoints(24);
        sheet.getRow(1).setHeightInPoints(20);
    }

    public static void main(String[] args) {
        // 考勤统计数据
        List<String[]> statisticList = new ArrayList<>();
        // 考勤记录
        List<String[]> recordList = new ArrayList<>();
        // 创建工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();

        // 生成一个考勤统计sheet
        PoiExcelUtil.writeExcel(workbook, statisticList, new String[]{"人员名称",
                        "人员ID",
                        "所属单位",
                        "当月工作日数量",
                        "出勤次数",
                        "缺勤次数",
                        "迟到次数",
                        "早退次数",
                        "缺卡次数"},
                "考勤统计报表", 0, 8);
        // 生成一个考勤详细记录sheet
        PoiExcelUtil.writeExcel(workbook, recordList, new String[]{"人员名称",
                "人员ID",
                "所属单位",
                "考勤设备",
                "考勤日期",
                "上班打卡时间",
                "上班状态",
                "下班打卡时间",
                "下班状态"}, "原始考勤记录", 1, 8);

        // 输出
        try (OutputStream out = new FileOutputStream("D:\\IdeaProjects\\test\\excel\\" + "workbooks.xlsx")) {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

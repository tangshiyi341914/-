package com.project.utils;

import com.project.model.User;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class POIUtils {
    //   import
    public static ResponseEntity<byte[]> user2Excel(List<User> list) {
        //1. 创建一个 Excel 文档
        HSSFWorkbook workbook = new HSSFWorkbook();
        //2. 创建文档摘要
        workbook.createInformationProperties();
        //3. 获取并配置文档信息
        DocumentSummaryInformation docInfo = workbook.getDocumentSummaryInformation();
        //文档类别
        docInfo.setCategory("用户信息");
        //文档管理员
        docInfo.setManager("javaboy");
        //设置公司信息
        docInfo.setCompany("www.javaboy.org");
        //4. 获取文档摘要信息
        SummaryInformation summInfo = workbook.getSummaryInformation();
        //文档标题
        summInfo.setTitle("用户信息表");
        //文档作者
        summInfo.setAuthor("Sweary");
        // 文档备注
        summInfo.setComments("你好");
        //5. 创建样式
        //创建标题行的样式
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        HSSFCellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("yyyy-mm-dd"));
        HSSFSheet sheet = workbook.createSheet("用户信息表");
        //设置列的宽度
        sheet.setColumnWidth(0, 5 * 256);
        sheet.setColumnWidth(1, 12 * 256);
        sheet.setColumnWidth(2, 5 * 256);
        sheet.setColumnWidth(3, 12 * 256);
        sheet.setColumnWidth(4, 13 * 256);
        sheet.setColumnWidth(5, 20 * 256);
        sheet.setColumnWidth(6, 18 * 256);
        sheet.setColumnWidth(7, 13 * 256);
        //6. 创建标题行
        HSSFRow r0 = sheet.createRow(0);
        HSSFCell c0 = r0.createCell(0);
        c0.setCellValue("编号");
        c0.setCellStyle(headerStyle);
        HSSFCell c1 = r0.createCell(1);
        c1.setCellStyle(headerStyle);
        c1.setCellValue("姓名");
        HSSFCell c2 = r0.createCell(2);
        c2.setCellStyle(headerStyle);
        c2.setCellValue("性别");
        HSSFCell c3 = r0.createCell(3);
        c3.setCellStyle(headerStyle);
        c3.setCellValue("出生日期");
        HSSFCell c4 = r0.createCell(4);
        c4.setCellStyle(headerStyle);
        c4.setCellValue("电话号码");
        HSSFCell c5 = r0.createCell(5);
        c5.setCellStyle(headerStyle);
        c5.setCellValue("联系地址");
        HSSFCell c6 = r0.createCell(6);
        c6.setCellStyle(headerStyle);
        c6.setCellValue("电子邮箱");
        HSSFCell c7 = r0.createCell(7);
        c7.setCellStyle(headerStyle);
        c7.setCellValue("代表公司");
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            HSSFRow row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(user.getId());
            row.createCell(1).setCellValue(user.getName());
            row.createCell(2).setCellValue(user.getGender());
            HSSFCell cell3 = row.createCell(3);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String time= sdf.format(user.getBirthday());
            cell3.setCellStyle(dateCellStyle);
            cell3.setCellValue(time);
            row.createCell(4).setCellValue(user.getPhone());
            row.createCell(5).setCellValue(user.getAddress());
            row.createCell(6).setCellValue(user.getEmail());
            row.createCell(7).setCellValue(user.getCpnName());
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.setContentDispositionFormData("attachment", new String("员工表.xls".getBytes("UTF-8"), "ISO-8859-1"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            workbook.write(baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.CREATED);
    }

    //   export
    public static List<User> excel2User(MultipartFile file) {
        List<User> list = new ArrayList<>();
        User user = null;
        try {
            //1. 创建一个 workbook 对象
            HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
            //2. 获取 workbook 中表单的数量
            int numberOfSheets = workbook.getNumberOfSheets();
            for (int i = 0; i < numberOfSheets; i++) {
                //3. 获取表单
                HSSFSheet sheet = workbook.getSheetAt(i);
                //4. 获取表单中的行数
                int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
                for (int j = 0; j < physicalNumberOfRows; j++) {
                    //5. 跳过标题行
                    if (j == 0) {
                        continue;//跳过标题行
                    }
                    //6. 获取行
                    HSSFRow row = sheet.getRow(j);
                    if (row == null) {
                        continue;//防止数据中间有空行
                    }
                    //7. 获取列数
                    int physicalNumberOfCells = row.getPhysicalNumberOfCells();
                    user = new User();
                    for (int k = 0; k < physicalNumberOfCells; k++) {
                        HSSFCell cell = row.getCell(k);
                        switch (cell.getCellType()) {
                            case STRING:
                                String cellValue = cell.getStringCellValue();
                                switch (k) {
                                    case 1:
                                        user.setName(cellValue);
                                        break;
                                    case 2:
                                        user.setGender(cellValue);
                                        break;
                                    case 4:
                                        user.setPhone(cellValue);
                                        break;
                                    case 5:
                                        user.setAddress(cellValue);
                                        break;
                                    case 6:
                                        user.setEmail(cellValue);
                                    case 7:
                                        user.setCpnName(cellValue);
                                }
                                break;
                            default: {
                                switch (k) {
                                    case 3:
                                        user.setBirthday(cell.getDateCellValue());
                                        break;
                                }
                            }
                            break;
                        }
                    }
                    list.add(user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}

package com.toxa.ventilation;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExcelApachePOI {

    private BufferedReader br = null;

    private HSSFWorkbook wb;
    private HSSFSheet sheet;
    private HSSFRow row;
    private HSSFCell cell;

    int rowNum = 0;

    public ExcelApachePOI(){

        wb = new HSSFWorkbook();

        setDefaultSheetSettings();
        createRows(65);
        mergeCellsDefault();
        createButtonText();

        getJson();

        saveThis();

    }

    private void getJson(){

        try {
            br = new BufferedReader(new FileReader("base.json"));

            String line;
            while ((line = br.readLine()) != null){
//                System.err.println(line);

                if(line.contains("Базовая информация"))
                    createHeadText();

                if(line.contains("Параметры здания"))
                    createBuildingText();

                if(line.contains("Оборудование"))
                    createGeneralText();

                if(line.contains("Группы"))
                    createGroupsText();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private HSSFCellStyle getCellStyleTop(){
        HSSFCellStyle style = wb.createCellStyle();

        style.setBorderTop(HSSFCellStyle.BORDER_THIN);

        return style;
    }

    private HSSFCellStyle getCellStyleButton(){
        HSSFCellStyle style = wb.createCellStyle();

        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);

        return style;
    }

//    private HSSFCellStyle getCellStyleTopButton(){
//        HSSFCellStyle style = wb.createCellStyle();
//
//        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//
//        return style;
//    }

    private void setDefaultSheetSettings(){
        sheet = wb.createSheet("Вентиляция");

        sheet.setMargin(Sheet.LeftMargin, 0.8);
        sheet.setMargin(Sheet.RightMargin, 0.4);
        sheet.setMargin(Sheet.TopMargin, 0.25);
        sheet.setMargin(Sheet.BottomMargin, 0.25);
        sheet.setZoom(3, 4);
        wb.setPrintArea(0, 0, 9, 0, 63);
        PrintSetup ps = sheet.getPrintSetup();
        ps.setPaperSize(PrintSetup.A4_PAPERSIZE);
    }

    private void createRows(int n){
        for(int i = 0; i < n; i++)
            row = sheet.createRow(i);
    }

    private void mergeCellsDefault(){
        for(int i = 10; i < 45; i++){
            sheet.addMergedRegion(new CellRangeAddress(i, i, 0, 6));
            sheet.addMergedRegion(new CellRangeAddress(i, i, 7, 8));
        }

        for(int i = 0; i < 9; i++)
            sheet.addMergedRegion(new CellRangeAddress(i, i, 1, 2));

        for(int i = 0; i < 5; i++)
            sheet.addMergedRegion(new CellRangeAddress(i, i, 3, 6));

        sheet.addMergedRegion(new CellRangeAddress(9, 9, 0, 1));
        sheet.addMergedRegion(new CellRangeAddress(46, 46, 7, 8));
        sheet.addMergedRegion(new CellRangeAddress(50, 50, 7, 8));
        sheet.addMergedRegion(new CellRangeAddress(46, 46, 1, 2));
    }

    private void createHeadText() throws IOException {
        String line;
        String[] text;

        while (! (line = br.readLine()).contains("}")){
            text = parseLine(line);
            if(! text[1].equals("")){
                printText(text[0], 1, rowNum);
                printText(text[1], 3, rowNum);
                rowNum++;
            }
        }
    }

    private void createButtonText(){
        printText("Составил", 4, 46);
        printText("Проверил", 4, 50);

        printText("(Галатенко А.Н.)", 7, 46);
        printText("(Васильчук С.В.)", 7, 50);

        buttonTextCreateBorder();

        printText(new SimpleDateFormat("dd.MM.yyyy").format(new Date()), 1, 46);
    }

    private void buttonTextCreateBorder(){
        sheet.getRow(47).createCell(5).setCellStyle(getCellStyleTop());
        sheet.getRow(47).createCell(6).setCellStyle(getCellStyleTop());

        sheet.getRow(51).createCell(5).setCellStyle(getCellStyleTop());
        sheet.getRow(51).createCell(6).setCellStyle(getCellStyleTop());
    }

    private void createBuildingText() throws IOException {
        String line;
        String[] text;
        int i = 3;

        printText("Здание", 1, 6);

        while (! (line = br.readLine()).contains("}")){
            text = parseLine(line);
            if(! text[1].equals("")){
                printText(text[0], i, 6);
                printText(text[1], i++, 7);

                cell.setCellStyle(getCellStyleTop());
            }
        }
    }

    private void createGeneralText() throws IOException {
        printText("Наименование", 0, 9);
        printText("Тип", 7, 9);
        printText("Кол-во", 9, 9);

        sheet.getRow(8).setRowStyle(getCellStyleButton());
        sheet.getRow(10).setRowStyle(getCellStyleTop());

        String line;
        String[] text;
        rowNum = 10;

        while (! (line = br.readLine()).contains("} ],")){
            text = parseLine(line);

            if(text[0].equals("Подвид")){
                rowNum++;
                printText(text[1], 0, rowNum++);

            } else if(line.contains("Описание")){
                int[] c = new int[]{0, 7, 9};
                int i = 0;

                while (line.contains("\"")){
                    text = parseLine(line);

                    printText(text[1], c[i++], rowNum);

                    line = br.readLine();
                }

                rowNum++;
            }

            if(line.contains("} ]"))
                sheet.getRow(rowNum).setRowStyle(getCellStyleTop());
        }
    }

    private void createGroupsText() throws IOException {
        String line;
        String[] text;
        String i = "";

        while (! (line = br.readLine()).contains("}")){
            text = parseLine(line);
            System.err.println(i);
            if(text[0].contains("first_group"))
                i = text[1];
            else
                parseGroup(text[1]);

        }
    }

    private void parseGroup(String text){
        System.err.println(text);
    }

    private String[] parseLine(String line){
        if(! line.contains(":"))
            return new String[]{""};

        String[] result = line.split(" : ");
        if(result[0].contains("\""))
            result[0] = result[0].substring(result[0].indexOf("\"") + 1, result[0].lastIndexOf("\""));
        if(result[1].contains("\"") && result[1] != null)
            result[1] = result[1].substring(result[1].indexOf("\"") + 1, result[1].lastIndexOf("\""));

        System.out.println(result[0] + " " + result[1]);

        return result;
    }


    private void saveThis(){
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(new File("d:\\12\\tmp.xls"));
            wb.write(fos);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void printText(String text, int x, int y){
        row = sheet.getRow(y);
        cell = row.createCell(x);
        cell.setCellValue(text);

    }

}

package com.toxa.ventilation;

import com.toxa.ventilation.json.CreateJson;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ExcelApachePOI {

    private Scanner scanner;

    private HSSFWorkbook wb;
    private HSSFSheet sheet;
    private HSSFRow row;
    private HSSFCell cell;

    int rowNum = 0;

    String pathName;
    private boolean openExcel;

    public ExcelApachePOI(){

        wb = new HSSFWorkbook();

        setDefaultSheetSettings();
        createRows(65);
        mergeCellsDefault();
        createButtonText();

        getJson();

        setAlignmentCenter();

    }

    private void getJson(){
        try {

            String json = new CreateJson().getJson();

//            System.out.println(json);

            scanner = new Scanner(json);

            String line;
            while (scanner.hasNextLine()){
                line = scanner.nextLine();

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

    private HSSFCellStyle getCellStyleBorder(){
        HSSFCellStyle style = wb.createCellStyle();

        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);

        style.setAlignment(HSSFCellStyle.ALIGN_LEFT);

        return style;
    }

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

        for(int i = 0; i < 2; i++)
            sheet.addMergedRegion(new CellRangeAddress(i, i, 1, 2));

        for(int i = 6; i < 8; i++)
            sheet.addMergedRegion(new CellRangeAddress(i, i, 1, 2));


        for(int i = 0; i < 2; i++)
            sheet.addMergedRegion(new CellRangeAddress(i, i, 3, 7));

        for(int i = 6; i < 8; i++)
            sheet.addMergedRegion(new CellRangeAddress(i, i, 3, 8));


        sheet.addMergedRegion(new CellRangeAddress(9, 9, 0, 1));
        sheet.addMergedRegion(new CellRangeAddress(46, 46, 7, 8));
        sheet.addMergedRegion(new CellRangeAddress(50, 50, 7, 8));
        sheet.addMergedRegion(new CellRangeAddress(46, 46, 1, 2));
    }

    private void setAlignmentCenter(){
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        for(int i = 12; i < 44; i++)
            if(sheet.getRow(i).getCell(9) != null)
                sheet.getRow(i).getCell(9).setCellStyle(style);

        sheet.getRow(3).getCell(3).setCellStyle(style);
        sheet.getRow(3).getCell(4).setCellStyle(style);
        sheet.getRow(3).getCell(5).setCellStyle(style);
        if(sheet.getRow(3).getCell(6) != null)
            sheet.getRow(3).getCell(6).setCellStyle(style);

        HSSFCellStyle style1 = wb.createCellStyle();
        style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style1.setBorderTop(HSSFCellStyle.BORDER_THIN);

        sheet.getRow(4).getCell(3).setCellStyle(style1);
        sheet.getRow(4).getCell(4).setCellStyle(style1);
        sheet.getRow(4).getCell(5).setCellStyle(style1);
        if(sheet.getRow(4).getCell(6) != null)
            sheet.getRow(4).getCell(6).setCellStyle(style1);
    }

    private void createHeadText() throws IOException {
        String line;
        String[] text;

        int i = 0;

        while (! (line = scanner.nextLine()).contains("}")){
            i++;

            if(i == 5)
                rowNum = 6;

            text = parseLine(line);

            if(text[0].equals("Составил"))
                printText(text[1], 7, 46);
            else if(text[0].equals("Проверил"))
                printText(text[1], 7, 50);
            else if(text[0].equals("Название файла"))
                pathName = text[1];
            else if(! text[1].equals("")){
                printText(text[0], 1, rowNum);
                printText(text[1].replace("\\", ""), 3, rowNum);
                rowNum++;
            }
        }
    }

    private void createButtonText(){
        printText("Составил", 4, 46);
        printText("Проверил", 4, 50);

//        printText("(Галатенко А.Н.)", 7, 46);
//        printText("(Васильчук С.В.)", 7, 50);

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

        printText("Здание", 1, 3);

        while (! (line = scanner.nextLine()).contains("}")){
            text = parseLine(line);
            if(! text[1].equals("0")){
                printText(text[0], i, 3);
                printText(text[1], i++, 4);


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

        while (! (line = scanner.nextLine()).contains("} ],")){
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

                    line = scanner.nextLine();
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
        String firstGroup = "";
        int group = 0;

        int i = 0;

        rowNum = 55;

        while (! (line = scanner.nextLine()).contains("}")){
            text = parseLine(line);

            if(text[0].contains("first_group")){
                firstGroup = text[1].substring(2, 3);
                group = Integer.parseInt(firstGroup);
            } else{
                sheet.addMergedRegion(new CellRangeAddress(53, 53, i, i + 1));
                printBorderText(text[0], i, 53);
                printBorderText("", i + 1, 53);
                groupTextLeftAlignment(i);

                if(i < 4)
                    printBorderText("шт.", i + 1, 54);
                else
                    printBorderText("шт.", i, 54);

                if(i < 4)
                    printBorderText("гр.", i, 54);

                for(String s : parseGroup(text[1])){
                    if(s.equals("-1 ")){
                        copyCellForFan50TwoSide(i + 1, rowNum);
                    } else if(i < 4){
                        printBorderText(String.valueOf(group), i, rowNum);
                        printBorderText(s, i + 1, rowNum++);
                    } else
                        printBorderText(s, i, rowNum++);
                    group++;
                }

                if(i < 3){
                    i += 3;
                    rowNum = 55;
                }
                else{
                    i += 2;
                    rowNum = 55;
                }
            }
        }
    }

    private void copyCellForFan50TwoSide(int cellNum, int finishRowNum){
        int startRowNum = 54;

        HSSFCell cellTmp;
        do {
            row = sheet.getRow(startRowNum);
            cell = row.getCell(cellNum);

            cellTmp = row.createCell(cellNum + 1);
            cellTmp.setCellStyle(cell.getCellStyle());
            cellTmp.setCellValue(cell.getStringCellValue());


            startRowNum++;
        } while (startRowNum < finishRowNum);


    }

    private void groupTextLeftAlignment(int n){
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_LEFT);

        for(int i = 54; i < 63; i++){
            if(sheet.getRow(i).getCell(n) == null)
                sheet.getRow(i).createCell(n).setCellStyle(style);

            if(sheet.getRow(i).getCell(n + 1) == null)
                sheet.getRow(i).createCell(n + 1).setCellStyle(style);
        }
    }

    private String[] parseGroup(String text){
        text = text.substring(text.indexOf("[") + 2, text.indexOf("]"));

        String[] result = text.split(", ");

        return result;
    }

    private String[] parseLine(String line){
        if(! line.contains(":"))
            return new String[]{""};

        String[] result = line.split(" : ");
        if(result[0].contains("\""))
            result[0] = result[0].substring(result[0].indexOf("\"") + 1, result[0].lastIndexOf("\""));
        if(result[1].contains("\"") && result[1] != null)
            result[1] = result[1].substring(result[1].indexOf("\"") + 1, result[1].lastIndexOf("\""));

//        System.out.println(result[0] + " " + result[1]);

        return result;
    }

    public void saveThis(){
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(new File(pathNameForFileName() + ".xls"));
            wb.write(fos);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(isOpenExcel())
            openExcel();
    }

    public void openExcel(){
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.open(new File(pathNameForFileName() + ".xls"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String pathNameForFileName(){
        String result = pathName.replace("\"", "");
        return result;
    }

    private void printText(String text, int x, int y){
        row = sheet.getRow(y);
        cell = row.createCell(x);
        cell.setCellValue(text);

    }

    private void printBorderText(String text, int x, int y){
        row = sheet.getRow(y);
        cell = row.createCell(x);
        cell.setCellStyle(getCellStyleBorder());
        cell.setCellValue(text);
    }

    public boolean isOpenExcel() {
        return openExcel;
    }

    public void setOpenExcel(boolean openExcel) {
        this.openExcel = openExcel;
    }
}

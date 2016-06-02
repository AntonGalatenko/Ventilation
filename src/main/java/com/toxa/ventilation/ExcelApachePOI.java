package com.toxa.ventilation;

import com.toxa.ventilation.json.JsonObject;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelApachePOI {

    JsonObject json;

    private HSSFWorkbook wb;
    private HSSFSheet sheet;
    private HSSFRow row;
    private HSSFCell cell;

    public ExcelApachePOI(){

        getJson();

        wb = new HSSFWorkbook();

        setDefaultSheetSettings();
        createRows(65);
        mergeCellsDefault();

        int rowNum = 1;

        createHeadText();


        saveThis();

    }

    private void getJson(){
        ObjectMapper mapper = new ObjectMapper();

        try {
            json = mapper.readValue(new File("base.json"), JsonObject.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        for(int i = 11; i < 45; i++){
            sheet.addMergedRegion(new CellRangeAddress(i, i, 0, 6));
            sheet.addMergedRegion(new CellRangeAddress(i, i, 7, 8));
        }

        for(int i = 1; i < 9; i++)
            sheet.addMergedRegion(new CellRangeAddress(i, i, 1, 2));

        sheet.addMergedRegion(new CellRangeAddress(1, 1, 3, 6));
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 3, 6));
        sheet.addMergedRegion(new CellRangeAddress(3, 3, 3, 6));
        sheet.addMergedRegion(new CellRangeAddress(7, 7, 3, 6));
        sheet.addMergedRegion(new CellRangeAddress(10, 10, 0, 1));
        sheet.addMergedRegion(new CellRangeAddress(46, 46, 7, 8));
        sheet.addMergedRegion(new CellRangeAddress(50, 50, 7, 8));
    }

    private void createHeadText(){
        printText("Предприятие", 1, 1);
        printText("Страна", 1, 2);
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

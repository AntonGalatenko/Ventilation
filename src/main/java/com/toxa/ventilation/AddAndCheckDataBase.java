package com.toxa.ventilation;

import com.toxa.ventilation.Data.ActualValues;
import com.toxa.ventilation.Data.DataOfEquipment;
import com.toxa.ventilation.model.entity.Factory;
import com.toxa.ventilation.model.repository.Repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AddAndCheckDataBase {

    private Repository repository = new Repository();
    private BaseInfo baseInfo = BaseInfo.getInstance();
    private final String PATH = baseInfo.getFilePathText();
    private long fileSize = baseInfo.getFileSize();
    long fileSizeCurrent;

    public AddAndCheckDataBase(){
//        File file = new File(PATH);

//        checkDirectory1(file);
    }

    private void checkDirectory(File file){
        File[] files = file.listFiles();

        for(File f : files)
            if(f.getName().equals("Напольник") || f.getName().equals("ПЧ"))
                fileSizeCurrent += calcFilesSize(f);

        System.out.println(fileSizeCurrent + " || " + baseInfo.getFileSize());

        DataOfEquipment dataOfEquipment = new ActualValues().loadActualValue();
        dataOfEquipment.updateFileSize(fileSizeCurrent);
        baseInfo.saveActualValue(dataOfEquipment);



    }

    private long calcFilesSize(File file){
        long result = 0;
        File[] files = file.listFiles();

        for (File f : files)
            if(f.isFile())
                result += f.length();
            else
                result += calcFilesSize(f);

        return result;
    }


    private void checkDirectory1(File file){
        File[] files = file.listFiles();

        for(File f : files){
            if(f.isDirectory())
                checkDirectory1(f);
            else if(f.getName().contains("xls"))
                addToDataBase(f);
        }
    }

//    private

    public void addToDataBase(File file){
        try {
            String fileName = file.getName().replace(".xls", "");

            String[] fileNames= fileName.split(" ");

            String[] numOfHeads = findNumberOfHeads(fileNames);
            int num = Integer.parseInt(numOfHeads[1]);

            String cage = fileNames[--num];
            if(fileNames[num - 1].lastIndexOf(",") == fileNames[num - 1].length() - 1)
                cage = fileNames[--num] + fileNames[num + 1];

            String country = file.getPath().substring(file.getPath().indexOf("ПЧ") + 3, file.getPath().indexOf("\\", file.getPath().indexOf("ПЧ") + 3));

            String[] build = findBuild(fileNames[--num]);
            double length = Double.parseDouble(build[0]);
            double width = Double.parseDouble(build[1]);
            double height = Double.parseDouble(build[2]);

            String name = fileNames[0];
            for(int i = 1; i < num; i++)
                name += " " + fileNames[i];

            repository.addItem(new Factory(2016, name, country, cage, Integer.parseInt(numOfHeads[0]) ,length, width, height, 0, file.getPath()));

        } catch (Exception e){
            writeErrToFile(e.toString());
        }
    }

    private String[] findNumberOfHeads(String[] name){
        int num = name.length - 1;

        int numTemp = num;
        if(name[numTemp].matches("^-?\\d+$") && ! name[numTemp - 1].replace(",", "").matches("^-?\\d+$"))
            if(Integer.parseInt(name[numTemp]) > 100)
                return new String[]{name[numTemp], String.valueOf(numTemp)};

        if(name[--numTemp].matches("^-?\\d+$"))
            if(Integer.parseInt(name[numTemp]) > 100)
                return new String[]{name[numTemp], String.valueOf(numTemp)};

        if(name[--numTemp].matches("^-?\\d+$"))
            if(Integer.parseInt(name[numTemp]) > 100)
                return new String[]{name[numTemp], String.valueOf(numTemp)};

        numTemp = num;
        String name1 = null;
        String t = null;
        if(name[numTemp].contains("_"))
            t = "_";
        else if(name[numTemp].contains("-"))
            t = "-";
        else if(name[numTemp].contains("("))
            t = "\\(";
        else if(name[numTemp].contains(","))
            t = ",";
        else if(name[numTemp].contains(","))
            t = ",";
        else if(name[numTemp].contains(", "))
            t = ", ";
        else if(name[numTemp].contains(","))
            t = ",";
        else if(name[--numTemp].contains("_"))
            t = "_";
        else if(name[numTemp].contains("-"))
            t = "-";
        else if(name[numTemp].contains("("))
            t = "\\(";
        else if(name[numTemp].contains(", "))
            t = ", ";
        else if(name[numTemp].contains(","))
            t = ",";
        else if(name[numTemp].contains(", "))
            t = ", ";
        else if(name[numTemp].contains(","))
            t = ",";

        name1 = nameSplit(name[numTemp].split(t));

        if(name1 != null)
            return new String[]{name1, String.valueOf(numTemp)};

        System.err.println(name[name.length - 1]);

        return null;
    }

    private String nameSplit(String[] name){
        if(name[0].matches("^-?\\d+$"))
            if(Integer.parseInt(name[0]) > 100)
                return name[0];

        return null;
    }

    private String[] findBuild(String value){
        value = value.replace("х", "x");

        String[] t;
        if(value.contains("x"))
            t = value.split("x");
        else if(value.contains("х"))
            t = value.split("х");
        else
            return null;

        String[] result = new String[3];
        result[0] = t[0].replace(",", ".");
        result[1] = t[1].replace(",", ".");
        if(t.length > 2)
            result[2] = t[2].replace(",", ".");
        else
            result[2] = "0";

        return result;
    }

    private void writeErrToFile(String text){
        try {
            FileWriter fw = new FileWriter("error", true);
            fw.write(text + "\n");

            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

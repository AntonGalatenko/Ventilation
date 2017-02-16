package com.toxa.ventilation.model;

import com.toxa.ventilation.AddAndCheckDataBase;
import com.toxa.ventilation.BaseInfo;
import com.toxa.ventilation.model.entity.Factory;
import com.toxa.ventilation.model.repository.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CompareDBandFiles extends Thread{

    private AddAndCheckDataBase addAndCheckDataBase = new AddAndCheckDataBase();
    private BaseInfo baseInfo = BaseInfo.getInstance();
    private List<Factory> dbList = new ArrayList<>();
    private List<String> filesList = new ArrayList<>();
    private Repository repository = new Repository();

    @Override
    public void run(){
        createDBList();
        createFilesList();
        compare();
    }

    private void createDBList(){
        dbList = repository.getItemsByYear(Calendar.getInstance().get(Calendar.YEAR));
    }

    private void createFilesList(){
        String path = baseInfo.getFilePathText();
        File file = new File(path);

        File[] files = file.listFiles();
        for(File f : files)
        if(f.getName().equals("ПЧ") || f.getName().equals("ГЧ") || f.getName().equals("Напольник"))
            filesList = getAllFilesPath(file);
    }

    private List<String> getAllFilesPath(File file) {
        List<String> result = new ArrayList<>();

        File[] files = file.listFiles();
        for(File f : files)
            if (f.isFile())
                result.add(f.getAbsolutePath());
            else if(! f.getName().equals("ГЧ"))
                result.addAll(getAllFilesPath(f));

        return result;
    }

    private void compare(){
        deleteItemFromDB();
        addItemToDB();
    }

    private void deleteItemFromDB(){
        if(filesList.size() < 1)
            return;

        List<String> dbStrList = getDBPathList();

        List<String> different  = new ArrayList<>(dbStrList);
        different.removeAll(filesList);

        System.out.println("delete size: " + different.size());

        if(different.size() > 0)
            for(String s : different)
                repository.deleteItem(getFactoryByLink(s));
    }

    private void addItemToDB(){
        List<String> dbStrList = getDBPathList();

        List<String> different  = new ArrayList<>(filesList);
        different.removeAll(dbStrList);

        if (different.size() == 33)
            different.clear();

        System.out.println("add size: " + different.size());

        if(different.size() > 0)
            for(String s : different)
                addAndCheckDataBase.addToDataBase(new File(s));
    }

    private Factory getFactoryByLink(String link){
        for(Factory f : dbList)
            if(f.getLink().equals(link))
                return f;
        return  null;
    }

    private List<String> getDBPathList(){
        List<String> result = new ArrayList<>();

        for(Factory f : dbList)
            result.add(f.getLink());

        return result;
    }

}

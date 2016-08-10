package com.toxa.ventilation.model.repository;

import com.toxa.ventilation.model.config.RepositoryConfig;
import com.toxa.ventilation.model.entity.Factory;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.poi.util.StringUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Repository {

    @Autowired
    public SessionFactory sessionFactory = RepositoryConfig.sessionFactory();

    public List<Factory> getAllItems(){
        Session session = sessionFactory.openSession();
        List<Factory> result = session.createCriteria(Factory.class).list();
        session.close();
        return result;
    }

    public void addItem(Factory factory){
//        if(isFactoryExists(factory))
//            return;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(factory);
        session.getTransaction().commit();
        session.close();
    }

    public boolean isFactoryExists(Factory factory){
        boolean isFactoryExists = false;

        Session session = sessionFactory.openSession();

        List<Factory> factoryList = session.createCriteria(Factory.class).list();
        for(Factory f : factoryList)
            if(f.hashCode() == (factory.hashCode()))
                isFactoryExists = true;

        session.close();
        return isFactoryExists;
    }

    public List<Factory> getSimilar(Factory factory){
        Session session = sessionFactory.openSession();
        List<Factory> result = new ArrayList<>();

        List<Factory> factoryList = session.createCriteria(Factory.class).list();
        for(Factory f : factoryList)
            if(f.isSimilar(factory))
                result.add(f);

        Collections.reverse(result);
        session.close();
        return result;
    }

    public List<Factory> getNameEquals(String name){
        Session session = sessionFactory.openSession();
        List<Factory> result = new ArrayList<>();

        List<Factory> factoryList = session.createCriteria(Factory.class).list();
        for(Factory f : factoryList)
            if(parseName(f.getName().toLowerCase()).contains(parseName(name.toLowerCase())))
                result.add(f);

        session.close();
        return result;
    }

    private String parseName(String name){
        name = name.trim();

        if(name.contains("\""))
            name = name.substring(name.indexOf("\"") + 1, name.lastIndexOf("\""));

        if(name.contains("«") && name.contains("»"))
            name = name.substring(name.indexOf("«") + 1, name.indexOf("»"));

        name = name.replace("-", " ");
        return name;
    }


}

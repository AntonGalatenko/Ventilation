package com.toxa.ventilation.model.config;


import com.toxa.ventilation.BaseInfo;
import com.toxa.ventilation.gui.LoadingPanel;
import com.toxa.ventilation.model.repository.Repository;
import org.hibernate.SessionFactory;
import org.hibernate.tool.schema.spi.SchemaManagementException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class RepositoryConfig {

    private static SessionFactory sessionFactory;

    @Bean
    public static DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/ventilation?characterEncoding=utf8&useJDBCCompliantTimezoneShift=true&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("paranO9");
        return dataSource;
    }

    @Bean
    public static Properties hibernateProperties() {
        final Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.connection.Charset", "UTF-8");
        properties.put("hibernate.connection.useUnicode", "true");
        properties.put("hibernate.connection.characterEncoding", "UTF-8");
        return properties;
    }

    @Bean
    public static SessionFactory sessionFactory() {
        LoadingPanel loadingPanel;
        try{
            if (sessionFactory == null){
                sessionFactory = new LocalSessionFactoryBuilder(dataSource())
                        .scanPackages("com.toxa.ventilation.model.entity")
                        .addProperties(hibernateProperties())
                        .buildSessionFactory();
            }
        } catch (SchemaManagementException e){
            try {
                Runtime.getRuntime().exec("net START MySQL");

                loadingPanel = new LoadingPanel();
                int i = 0;

                while (sessionFactory == null && i < 15){
                    try{
                        if (sessionFactory == null){
                            sessionFactory = new LocalSessionFactoryBuilder(dataSource())
                                    .scanPackages("com.toxa.ventilation.model.entity")
                                    .addProperties(hibernateProperties())
                                    .buildSessionFactory();
                        }
                    } catch (SchemaManagementException e1){}

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                    i++;
                }

                loadingPanel.setVisible(false);

                if (sessionFactory == null)
                    BaseInfo.getInstance().setDataBaseStatus("База данных не подключена!!!");

            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }

        return sessionFactory;
    }

    @Bean
    public Repository repository(){
        return new Repository();
    }

}

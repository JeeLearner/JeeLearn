package com.learn.modules.showcase.excel.dao;
import java.sql.Connection;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;


public class ExcelDataDaoImpl{

	 @PersistenceContext
	 private EntityManager em;
	
	
	public void truncate() {
		em.unwrap(Session.class).doWork(new Work() {
            @Override
            public void execute(final Connection connection) throws SQLException {
                connection.createStatement().execute("truncate table showcase_excel_data");
            }
        });
	}
}

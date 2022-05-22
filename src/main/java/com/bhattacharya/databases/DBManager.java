package com.bhattacharya.databases;

import java.sql.ResultSet;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DBManager {
	@Autowired
	protected JdbcTemplate template;

	@Autowired
	DataSource source;

	public DBManager() {
		try {
			String query = "SELECT COUNT(*) FROM information_schema.tables WHERE table_schema gupshupdb";
			template.setDataSource(source);
			ResultSet rs = template.getDataSource().getConnection().createStatement().executeQuery(query);
			boolean exists = rs.getInt("COUNT(*)") > 0;
			System.out.println("exists = " + exists);
			if (!exists) {
				
			}
		} catch (Exception e) {
			//TODO: handle exception
		}
	}

	public boolean store(){
		System.out.println("Inside Dbmanager Store");
		try {
			String query = "SELECT COUNT(*) FROM information_schema.tables WHERE table_schema gupshupdb";
			ResultSet rs = template.getDataSource().getConnection().prepareStatement(query).executeQuery();
			boolean exists = rs.getInt("COUNT(*)") > 0;
			System.out.println("exists = " + exists);
			if (!exists) {
				
			}
		} catch (Exception e) {
			//TODO: handle exception
		}
		System.out.println(template.toString());
		System.out.println("Source="+template.getDataSource());
		return true;
	}

	
}

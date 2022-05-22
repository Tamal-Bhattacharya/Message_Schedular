package com.bhattacharya.databases;

import com.bhattacharya.entities.Entity;

import org.springframework.stereotype.Repository;

@Repository
public interface DBManager {
	public int insert(Entity entity);
	public int update(Entity entity);
	public int delete(Entity entity);
	public int retrieve(Entity entity);
}
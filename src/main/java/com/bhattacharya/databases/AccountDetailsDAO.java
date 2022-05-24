package com.bhattacharya.databases;

import com.bhattacharya.entities.Entity;
import com.bhattacharya.entities.Gupshup_Account_Details;
import com.bhattacharya.rowMappers.AccountDetailsMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class AccountDetailsDAO implements DBManager{

    @Autowired
    JdbcTemplate template;

    @Override
    public int insert(Entity entity) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int update(Entity entity) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int delete(Entity entity) {
        // TODO Auto-generated method stub
        return 0;
    }

    public String retrieve(int Account_ID) {
        String querry = "select * from Account_Credential where Account_ID = ?";
        RowMapper<Gupshup_Account_Details> rowMapper = new AccountDetailsMapper();
        Gupshup_Account_Details credential = this.template.queryForObject(querry, rowMapper, Account_ID);
        System.out.println(credential);
        return credential.getGupshup_API_Key();
    }
    
}

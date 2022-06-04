package com.bhattacharya.databases;

import com.bhattacharya.entities.Account_Credential;
import com.bhattacharya.entities.Entity;
import com.bhattacharya.rowMappers.AccountCredentialsMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class AccountCredentialDAO implements DBManager{

    @Autowired
    JdbcTemplate template;

    @Override
    public int insert(Entity entity) {
        String querry = "insert into Account_Credential (userName, password) values (?,?)";
        int res = template.update(querry,((Account_Credential)entity).getUserName(),((Account_Credential)entity).getPassword());
        return res;
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

    public Account_Credential retrieve(String userName) {
        String querry = "select * from Account_Credential where userName = ?";
        RowMapper<Account_Credential> rowMapper = new AccountCredentialsMapper();
        Account_Credential credential = this.template.queryForObject(querry, rowMapper, userName);
        System.out.println(credential);
        return credential;
    }
    
}

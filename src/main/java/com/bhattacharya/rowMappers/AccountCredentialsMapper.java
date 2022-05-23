package com.bhattacharya.rowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.bhattacharya.entities.Account_Credential;

import org.springframework.jdbc.core.RowMapper;

public class AccountCredentialsMapper implements RowMapper<Account_Credential>{

    @Override
    public Account_Credential mapRow(ResultSet rs, int rowNum) throws SQLException {
        // TODO Auto-generated method stub
        Account_Credential credential = new Account_Credential();
        credential.setAccount_ID(rs.getInt(1));
        credential.setUserName(rs.getString(2));
        credential.setPassword(rs.getString(3));
        return credential;
    }
    
}

package com.bhattacharya.rowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.bhattacharya.entities.Gupshup_Account_Details;

import org.springframework.jdbc.core.RowMapper;

public class AccountDetailsMapper implements RowMapper<Gupshup_Account_Details> {

    @Override
    public Gupshup_Account_Details mapRow(ResultSet rs, int rowNum) throws SQLException {
        // TODO Auto-generated method stub
        Gupshup_Account_Details accountDetails = new Gupshup_Account_Details();

        accountDetails.setAccount_ID(rs.getInt(1));
        accountDetails.setGupshup_API_Key(rs.getString(2));

        return accountDetails;
    }
    
}

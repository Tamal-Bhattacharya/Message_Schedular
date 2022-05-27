package com.bhattacharya.databases;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.bhattacharya.entities.Entity;
import com.bhattacharya.entities.Message;
import com.bhattacharya.rowMappers.MessageMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MessageDAO implements DBManager{

    @Autowired
    JdbcTemplate template;

    @Override
    public int insert(Entity entity) {
        // TODO Auto-generated method stub
        String query = "insert into Message (Account_ID, MSG, Scheduled_time, Send_To, Status) values (?,?,?,?,?)";
        int res = template.update(query, ((Message) entity).getAccount_ID(), ((Message) entity).getMsg(), ((Message) entity).getTimestamp(), ((Message) entity).getSend_To(), ((Message) entity).getStatus());
        return res;
    }

    @Override
    public int update(Entity entity) {
        // TODO Auto-generated method stub
        String query = "update Message set Status = ? where Message_ID = ?";
        int res = template.update(query, ((Message) entity).getStatus(), ((Message) entity).getMessage_ID());
        return res;
    }

    @Override
    public int delete(Entity entity) {
        // TODO Auto-generated method stub
        return 0;
    }

    public List<Message> retrieveScheduledMessages() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = localDateTime.format(formatter);
        System.out.println(formatDateTime);
        String querry = "select * from Message where Scheduled_Time <= '" + Timestamp.valueOf(LocalDateTime.parse(formatDateTime.replace(" ", "T"))).toString().replace(".0", "") +  "' and status = 0";
        RowMapper<Message> mapper = new MessageMapper();
        List<Message> messages = template.query(querry, mapper);
        return messages;
    }
    
    public int retrieveMessageID(Entity entity){
        String query = "select * from Message where Scheduled_Time = '" + ((Message) entity).getTimestamp() + "' and Account_ID = " + ((Message) entity).getAccount_ID() + " and Sent_To = " + ((Message) entity).getSend_To();
        RowMapper<Message> mapper = new MessageMapper();
        Message message = template.queryForObject(query, mapper);
        return message.getMessage_ID();
    }

    public Message retriveMessageById(int id) {
        String query = "select * from Message where Message_ID = " + id;
        RowMapper<Message> mapper = new MessageMapper();
        Message message = template.queryForObject(query, mapper);
        return message;
    }
}

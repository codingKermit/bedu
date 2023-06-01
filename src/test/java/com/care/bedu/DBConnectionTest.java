package com.care.bedu;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.jupiter.api.Test;

public class DBConnectionTest {

	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://172.30.1.46:3306";
    private static final String USER = "buser";
    private static final String PW = "bilstudy";
    
    @Test
    public void testConnection() throws Exception{
        
        Class.forName(DRIVER);
        
        try(Connection con = DriverManager.getConnection(URL, USER, PW)){
            System.out.println(con);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitoring.koneksi;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author IT
 */
public class koneksiDB {
    private static Connection koneksi;
    public static Connection getConnection() {  
        //SettingDatabase database = new SettingDatabase();
        //Map<String, String> map = database.bukaProperties();
        String host = "34.101.91.186";
        String port = "3306";
        String user = "jonas";
        String pass = "t8c4cX7aKJe97F6h";
        String base = "jonas_db";
        
        if(koneksi==null){
            try {
                MysqlDataSource dataSource = new MysqlDataSource();
                dataSource.setUrl("jdbc:mysql://"+host+":"+port+"/"+base+"?autoReconnect=true&failOverReadOnly=false&maxReconnects=10");
                dataSource.setUser(user);
                dataSource.setPassword(pass);
                koneksi = dataSource.getConnection();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Koneksi Ke Server Gagal");
            }
        }
        return koneksi;
    }

    public static void setConnection(Connection connection) {
        koneksiDB.koneksi = connection;
    }
}

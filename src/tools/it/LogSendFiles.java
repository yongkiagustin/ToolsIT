/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.it;

import java.sql.Date;

/**
 *
 * @author Jonas
 */
class LogSendFiles {

    private String so_number, real_name;
    private int photo_status;
    
    
    public LogSendFiles(String so_number, String real_name, int photo_status ){
        this.so_number = so_number;
        this.real_name = real_name;
        this.photo_status = photo_status;
       
        
      
    }

    public String getSo_number() {
        return so_number;
    }

    public void setSo_number(String so_number) {
        this.so_number = so_number;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public int getPhoto_status() {
        return photo_status;
    }

    public void setPhoto_status(int photo_status) {
        this.photo_status = photo_status;
    }

   
}

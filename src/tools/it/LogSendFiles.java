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
    
    
    public LogSendFiles(String real_name, int photo_status ){
        
        this.real_name = real_name;
        this.photo_status = photo_status;
       
        
      
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.it;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author Jonas
 */
public class ModelHistoryUpload {
    public static java.sql.Date convert(java.util.Date date)
    {
        return new java.sql.Date(date.getTime());
    }
 
    public static void main (String[] args)
    {
        java.util.Date utilDate = new java.util.Date();
        System.out.println("java.util.Date : " + utilDate);
 
        java.sql.Date sqlDate = convert(utilDate);
        System.out.println("java.sql.Date  : " + sqlDate);
    }
    
    private String so_number;
    private Timestamp create_at;

    private int total_file;

    public ModelHistoryUpload(String so_number, Timestamp create_at, int total_file) {
        
        this.so_number = so_number;
        this.create_at = create_at;
        this.total_file = total_file;
    }
    



    public String getSo_number() {
        return so_number;
    }

    public void setSo_number(String so_number) {
        this.so_number = so_number;
    }

    public Timestamp getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Timestamp create_at) {
        this.create_at = create_at;
    }



    public int getTotal_file() {
        return total_file;
    }

    public void setTotal_file(int total_file) {
        this.total_file = total_file;
    }
    
}

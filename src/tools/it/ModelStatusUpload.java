/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.it;

import java.sql.Timestamp;

/**
 *
 * @author Jonas
 */
public class ModelStatusUpload {
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
    private Timestamp production_finish_at,updated_at;
    private String store_name,so_number, name;
    private int so_status, status_upload;

    public ModelStatusUpload(String store_name,Timestamp production_finish_at, String so_number, String name, int so_status, int status_upload, Timestamp updated_at) {
        this.store_name = store_name;
        this.production_finish_at = production_finish_at;
        this.so_number = so_number;
        this.name = name;
        this.so_status = so_status;
        this.status_upload = status_upload;
        this.updated_at = updated_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public Timestamp getProduction_finish_at() {
        return production_finish_at;
    }

    public void setProduction_finish_at(Timestamp production_finish_at) {
        this.production_finish_at = production_finish_at;
    }

    public String getSo_number() {
        return so_number;
    }

    public void setSo_number(String so_number) {
        this.so_number = so_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSo_status() {
        return so_status;
    }

    public void setSo_status(int so_status) {
        this.so_status = so_status;
    }

    public int getStatus_upload() {
        return status_upload;
    }

    public void setStatus_upload(int status_upload) {
        this.status_upload = status_upload;
    }
    
}
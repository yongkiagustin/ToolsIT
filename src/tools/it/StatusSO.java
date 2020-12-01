/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.it;

/**
 *
 * @author Young
 */
public class StatusSO {
    private int id, status_order;
    private String so_number;

    public StatusSO(int id, int status_order, String so_number) {
        this.id = id;
        this.status_order = status_order;
        this.so_number = so_number;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus_order() {
        return status_order;
    }

    public void setStatus_order(int status_order) {
        this.status_order = status_order;
    }

    public String getSo_number() {
        return so_number;
    }

    public void setSo_number(String so_number) {
        this.so_number = so_number;
    }

   
    
}

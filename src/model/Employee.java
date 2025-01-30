/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Scanner;
import main.Logable;

/**
 *
 * @author erpys
 */
public class Employee extends Person implements Logable  {
    
    int employeeId;
    String password;

    public static final int EMPLOYEE_ID=123;
    public static final String PASSWORD="test";

    public Employee(int eployeeId, String password) {
        this.employeeId = eployeeId;
        this.password = password;
    }

   
    public boolean login(int employeeId, String password ) {
        
        if(employeeId == EMPLOYEE_ID){
            if(password.equals(PASSWORD)){
                System.out.println("Loged In!");
                return true;
            }else{
                System.out.println("La contraseña es incorrecta!");
                return false;
            }
        }else{
            System.out.println("No existe el empleado");
            return false;
        }  
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    
   
    
        
}

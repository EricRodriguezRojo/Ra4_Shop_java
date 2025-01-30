/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import main.Payable;
import static model.Employee.EMPLOYEE_ID;
import static model.Employee.PASSWORD;

/**
 *
 * @author erpys
 */
public class Client extends Person implements Payable {
    
    int memberld;
    Amount balance;
    
    public static final int MEMBER_ID = 123;
    public static final double BALANCE = 50.00;

    public Client(int memberld, Amount balance) {
        this.memberld = memberld;
        this.balance = balance;
    }
    
    public boolean pay(Amount amount) {

        
        
        return true;
    }

    public int getMemberld() {
        return memberld;
    }

    public void setMemberld(int memberld) {
        this.memberld = memberld;
    }

    public Amount getBalance() {
        return balance;
    }

    public void setBalance(Amount balance) {
        this.balance = balance;
    }
    
    
    
}

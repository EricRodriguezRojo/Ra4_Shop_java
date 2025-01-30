/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package main;

import model.Amount;

/**
 *
 * @author erpys
 */
public interface Payable {
    boolean pay(Amount amount);
}

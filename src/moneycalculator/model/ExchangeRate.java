/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moneycalculator.model;

/**
 *Digamos que es un objeto que simboliza que cambio debe haber
 * @author acer
 */
public class ExchangeRate {
    private final Currency from;
    private final Currency to;
    private final double amount;

    public ExchangeRate(Currency from, Currency to, double amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public Currency getFrom() {
        return from;
    }

    public Currency getTo() {
        return to;
    }

    public double getAmount() {
        return amount;
    }
    
}

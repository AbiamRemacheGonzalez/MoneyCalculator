/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moneycalculator.ui.swing;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import moneycalculator.model.Currency;
import moneycalculator.model.Money;
import moneycalculator.ui.MoneyDialog;

/**
 *
 * @author acer
 */
public class SwingMoneyDialog extends JPanel implements MoneyDialog {

    private Currency currency;
    private String amount;
    private final Currency[] currencies;

    public SwingMoneyDialog(Currency[] currencies) {
        this.currencies = currencies;
        this.add(amount());
        this.add(currency());
    }

    
    
    @Override
    public Money get() {
        return new Money(Double.parseDouble(amount),currency);
    }

    private Component amount() {
        JTextField textfield = new JTextField("100");
        textfield.setColumns(10);
        textfield.getDocument().addDocumentListener(amountChanged());
        amount = textfield.getText();
        return textfield;

    }

    private Component currency() {
        JComboBox combo = new JComboBox(currencies);
        combo.addItemListener(currencyChange());
        currency = (Currency) combo.getSelectedItem();
        return combo;
    }

    private ItemListener currencyChange() {
       return new ItemListener() {
           @Override
           public void itemStateChanged(ItemEvent ie) {
               if(ie.getStateChange() == ItemEvent.DESELECTED) return;
               currency = (Currency) ie.getItem();
           }
       };     
    }

    private DocumentListener amountChanged() {
        return new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent de) {
                amountChanged(de.getDocument());
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                amountChanged(de.getDocument());
                
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                amountChanged(de.getDocument());
                
            }

            private void amountChanged(Document document) {
                try {
                    amount =document.getText(0,document.getLength());
                            }
                catch (BadLocationException ex) {
                }
            }
        };
    }
    
}

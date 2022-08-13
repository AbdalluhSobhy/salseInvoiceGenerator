/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Abdalluh
 */
public class InvoiceLineTable extends AbstractTableModel{

    private ArrayList<InvoiceLine> Line;
    private String [] columeLine ={"No","ItemName","ItemPrice","Count","total"};

    public InvoiceLineTable(ArrayList<InvoiceLine> Lines) {
      Line = Lines; 
    }

    public ArrayList<InvoiceLine> getLine() {
        return Line;
    }

   
  
    @Override
    public int getRowCount() {
     return Line.size();
    }

    @Override
    public int getColumnCount() {
    return columeLine.length ;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
      InvoiceLine Lines=Line.get(rowIndex);
        switch (columnIndex) {
            case 0:
              return Lines.getHeader().getInvoiceNum();
               
            case 1:
             return Lines.getItemName();
              
            case 2:
              return Lines.getItemPrice();
               
            case 3:
               return Lines.getCount();
               
            case 4:
              return Lines.LineTotal();
           
        }
        return "";
    }
     @Override
    public String getColumnName(int column) {
        return columeLine[column]; 
    }
    
}

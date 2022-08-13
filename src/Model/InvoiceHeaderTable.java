
package Model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Abdalluh
 */
public class InvoiceHeaderTable extends AbstractTableModel{

private ArrayList<InvoiceHeader> header;
private String [] ColumnHead={"No","Date","Custmer","Total"};

    public InvoiceHeaderTable(ArrayList<InvoiceHeader> header) {
        this.header = header;
    }      
    @Override
    public int getRowCount() {
        
        return header.size() ;
    }

    @Override
    public int getColumnCount() {
        return ColumnHead.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceHeader head=header.get(rowIndex);
         switch (columnIndex) {
            case 0:
             return head.getInvoiceNum();
                
           case 1:
            return head.getInvoiceDate();
              
                case 2:
            return head.getCustomerName();
              
                case 3:
               return head.HeaderTotal();  
        }
            
        return "";
    }

    @Override
    public String getColumnName(int column) {
        return ColumnHead[column]; 
    }
   
}

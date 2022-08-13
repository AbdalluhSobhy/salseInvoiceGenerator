
package Model;

import java.util.ArrayList;

/**
 *
 * @author Abdalluh
 */
public class InvoiceHeader {
        private  int invoiceNum;
	private String invoiceDate;
	private String customerName;
	private ArrayList<InvoiceLine> invoiceList; 

    public InvoiceHeader(int invoiceNum, String invoiceDate, String costomerName) {
        this.invoiceNum = invoiceNum;
        this.invoiceDate = invoiceDate;
        this.customerName = costomerName;
       
    }

    public int getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(int invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getCustomerName() {
        return customerName;
    }
   

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public ArrayList<InvoiceLine> getInvoiceList() {
        if (invoiceList==null) {
            invoiceList=new ArrayList<InvoiceLine>();
        }
        return invoiceList;
    }
    
    public double  HeaderTotal(){
    double total = 0;
        for (InvoiceLine line :getInvoiceList() ) {
            total=line.LineTotal()+total;  
        }
    return total;
    }
   


    @Override
    public String toString() {
        return "InvoiceHeader{" + "invoiceNum=" + invoiceNum + ", invoiceDate=" + invoiceDate + ", costomerName=" + customerName + '}';
    }
        
  public String makeCsv(){
  
  return invoiceNum + "," + invoiceDate + "," + customerName;
  }
        
}

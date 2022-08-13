
package Model;

/**
 *
 * @author Abdalluh
 */
public class InvoiceLine {
        private String itemName;
	private double itemPrice;
	private int count; 
        private InvoiceHeader header ;
        
    public InvoiceLine(String itemName, double itemPrice, int count, InvoiceHeader header) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.count = count;
        this.header = header;
    }

    

   

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

     public InvoiceHeader getHeader() {
        return header;
    }

   
    
    public double LineTotal(){
    return itemPrice*count;
    }

    @Override
    public String toString() {
        return "InvoiceLine{" + "itemName=" + itemName + ", itemPrice=" + itemPrice + ", count=" + count + ", header=" + header +"LineTotal"+LineTotal()+ '}';
    }
     
    public String makelineCsv(){
    
     return header.getInvoiceNum() + "," + itemName + "," + itemPrice + "," + count;
    }
}

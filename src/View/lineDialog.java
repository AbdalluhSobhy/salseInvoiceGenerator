
package View;

import javax.swing.JDialog;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
/**
 *
 * @author Abdalluh
 */
public class lineDialog extends JDialog{
    
     private JTextField itemName;
    private JTextField itemCount;
    private JTextField itemPrice;
    private JLabel itemNameLbl;
    private JLabel itemCountLbl;
    private JLabel itemPriceLbl;
    private JButton ok;
    private JButton cancel;
    
    public lineDialog (Frame frame) {
        itemName = new JTextField(20);
        itemNameLbl = new JLabel("Item Name");
        
        itemCount = new JTextField(20);
        itemCountLbl = new JLabel("Item Count");
        
        itemPrice = new JTextField(20);
        itemPriceLbl = new JLabel("Item Price");
        
        ok = new JButton("OK");
        cancel = new JButton("Cancel");
        
        ok.setActionCommand("createLineOK");
        cancel.setActionCommand("createLineCancel");
        
        ok.addActionListener(frame.getController());
        cancel.addActionListener(frame.getController());
        setLayout(new GridLayout(4, 2));
        
        add(itemNameLbl);
        add(itemName);
        add(itemCountLbl);
        add(itemCount);
        add(itemPriceLbl);
        add(itemPrice);
        add(ok);
        add(cancel);
        
        pack();
    }

    public JTextField getItemName() {
        return itemName;
    }

    public JTextField getItemCount() {
        return itemCount;
    }

    public JTextField getItemPrice() {
        return itemPrice;
    } 
    
    
}


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
public class headerDialog extends  JDialog{
     private JTextField custName;
    private JTextField invDate;
    private JLabel custNameLbl;
    private JLabel invDateLbl;
    private JButton ok;
    private JButton cancel;

    public headerDialog(Frame frame) {
        custNameLbl = new JLabel("Customer Name:");
        custName = new JTextField(20);
        invDateLbl = new JLabel("Invoice Date:");
        invDate = new JTextField(20);
        ok = new JButton("OK");
        cancel = new JButton("Cancel");
        
        ok.setActionCommand("createInvoiceOK");
        cancel.setActionCommand("createInvoiceCancel");
        
        ok.addActionListener(frame.getController());
        cancel.addActionListener(frame.getController());
        setLayout(new GridLayout(3, 2));
        
        add(invDateLbl);
        add(invDate);
        add(custNameLbl);
        add(custName);
        add(ok);
        add(cancel);
        
        pack();
        
    }

    public JTextField getCustmerName() {
        return custName;
    }

    public JTextField getInvoiceDate() {
        return invDate;
    }
    
    
}

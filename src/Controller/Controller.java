
package Controller;

import Model.InvoiceHeader;
import Model.InvoiceLine;
import Model.InvoiceLineTable;
import View.Frame;
import View.headerDialog;
import View.lineDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Abdalluh
 */
public class Controller implements ActionListener,ListSelectionListener{
private Frame frame;
private headerDialog heDialog;
private lineDialog liDialog;
    public Controller(Frame frame) {
       this.frame=frame;      
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String ActionCommend = e.getActionCommand();
        
        switch (ActionCommend) {
            case "Load File":
            {
                try {
                    LoadFile();
                } catch (IOException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                break;

            case "Save File":
            {
                try {
                    SaveFile();
                } catch (IOException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                break;

            case "Create New Invoice":
                CreateNewInvoice();
                break;
            case "Delete Invoice":
                DeleteInvoice();
                break;
            case "Create New Line":
                CreateNewLine();
                break;
            case "Delete line":
                Deleteline();
                break;
            case "createInvoiceOK":
            createInvoiceOK();
            break;
            case "createInvoiceCancel":
            createInvoiceCancel () ;
            break;
            case "createLineOK" :
            createLineOK();
            break;
            case "createLineCancel":
            createLineCancel();
            break;
        }
        
    }
    

    @Override
    public void valueChanged(ListSelectionEvent e) {
       
      int SelectedRow = frame.getInvoiceHederTable().getSelectedRow();
     
        if (SelectedRow!=-1) {
  InvoiceHeader currentInvoice = frame.getHeader().get(SelectedRow);
      frame.getNumber().setText(""+currentInvoice.getInvoiceNum());
      frame.getjTextField2().setText(currentInvoice.getInvoiceDate());
      frame.getjTextField1().setText(currentInvoice.getCustomerName());
      frame.getTotal().setText(""+currentInvoice.HeaderTotal());
      InvoiceLineTable linesTableModel =new InvoiceLineTable(currentInvoice.getInvoiceList());
      frame.getLineTable().setModel(linesTableModel);
      linesTableModel.fireTableDataChanged();
    }
    }
    
    
    private void LoadFile() throws IOException {
        JFileChooser fc =new JFileChooser();
      int ras=  fc.showOpenDialog(frame);
        if (ras==JFileChooser.APPROVE_OPTION) {
           
            String HeaderPath =fc.getSelectedFile().getAbsolutePath();
            Path pathHeader = Paths.get(HeaderPath);           
            List<String> HLine = Files.lines(pathHeader).collect(Collectors.toList());
            
            ArrayList<InvoiceHeader> invoiceHeaderlist = new ArrayList<>();                   
            for (String w : HLine) {
                String part [] =w.split(",");
                int NumInvoice = Integer.parseInt(part[0]);
                InvoiceHeader invoiceHeader= new InvoiceHeader(NumInvoice,part[1], part[2]); 
                invoiceHeaderlist.add(invoiceHeader);
                        
            }
           // System.out.println("chek");
            ras = fc.showOpenDialog(frame);
            if (ras==JFileChooser.APPROVE_OPTION) {
               String Linepath = fc.getSelectedFile().getAbsolutePath();
               Path pathLine =Paths.get(Linepath);
                List<String> LLine = Files.lines(pathLine).collect(Collectors.toList());
                
                for (String x : LLine) {
                    String part [] =x.split(",");
                    int HeaderNum=Integer.parseInt(part[0]);
                    String itemName =part[1];
                    double price = Double.parseDouble(part[2]);
                    int count  =Integer.parseInt(part[3]);
                    
                   InvoiceHeader header = getInvoiceHeder(invoiceHeaderlist, HeaderNum);
                   
                   
                   InvoiceLine invLine = new InvoiceLine(itemName, price, count, header);
                   header.getInvoiceList().add(invLine);
                     System.out.println(invLine);
                }
              
                frame.setHeader(invoiceHeaderlist);
                frame.getHeaderTable().fireTableDataChanged();
            }
             
        }
      
    }
    
    private InvoiceHeader getInvoiceHeder(ArrayList<InvoiceHeader> invoice ,int id){
        
        for (InvoiceHeader x : invoice ) {
            if ( x.getInvoiceNum()==id) {
                
                return x;
            }
        }
    return null;
    }
            
    
    private void SaveFile() throws IOException {
        ArrayList<InvoiceHeader> invoiceHeaders= frame.getHeader();
        String invoices="";
        String lines="";
        for (InvoiceHeader invoiceHeader : invoiceHeaders) {
          String invoiceCsv =invoiceHeader.makeCsv();
          invoices=invoices+invoiceCsv;
          invoices+="\n";
            for (InvoiceLine invoiceLine :invoiceHeader.getInvoiceList() ) {
                String lineCsv =invoiceLine.makelineCsv();
                lines=lines+lineCsv;
                lines+="\n";
            }
        }
        JFileChooser fc = new JFileChooser();
        int result =fc.showSaveDialog(frame);
        if (result==JFileChooser.APPROVE_OPTION) {
           File fileHeader =fc.getSelectedFile();
           FileWriter hedFileWriter =new FileWriter(fileHeader);
           hedFileWriter.write(invoices);
            hedFileWriter.flush();
            hedFileWriter.close();
          result =fc.showSaveDialog(frame);
            if (result==JFileChooser.APPROVE_OPTION) {
              File fileLine =fc.getSelectedFile();
              FileWriter lineFileWriter = new FileWriter(fileLine);
              lineFileWriter.write(lines);
              lineFileWriter.flush();
              lineFileWriter.close();;
            }
        }
    }

    private void CreateNewInvoice() {
        heDialog =new headerDialog(frame);
         heDialog.setVisible(true);
    }

    private void DeleteInvoice() {
     int selectedRow =frame.getInvoiceHederTable().getSelectedRow();
        if (selectedRow!=-1) {
            frame.getHeader().remove(selectedRow);
          frame.getHeaderTable().fireTableDataChanged();
        }
    }

    private void CreateNewLine() {
     liDialog =new lineDialog(frame);
    liDialog.setVisible(true);
    }

    private void Deleteline() {
      int selectedRow =frame.getLineTable().getSelectedRow();
        if (selectedRow!=-1) {
      InvoiceLineTable    linesTableModel  = ( InvoiceLineTable)frame.getLineTable().getModel();
      linesTableModel.getLine().remove(selectedRow);
      linesTableModel.fireTableDataChanged();
      frame.getHeaderTable().fireTableDataChanged();
    }

   
    
}

    private void createInvoiceOK() {
String name =heDialog.getCustmerName().getText();
String date =heDialog.getInvoiceDate().getText();
int num = frame.getInvoiceNumber();


 InvoiceHeader header =new InvoiceHeader(num,date  ,name );
   frame.getHeader().add(header);
   frame.getHeaderTable().fireTableDataChanged();
   heDialog.setVisible(false);
   heDialog.dispose();
   heDialog=null;
    }

    private void createInvoiceCancel() {
        heDialog.setVisible(false);
        heDialog.dispose();
        heDialog=null;
        
    }

    private void createLineOK() {
     String item = liDialog.getItemName().getText();
     String count =liDialog.getItemCount().getText();
     String price =liDialog.getItemPrice().getText();
     int countInt =Integer.parseInt(count);
     double pricedo =Double.parseDouble(price);
    int selctedRowInvoice =frame.getTableHeader().getSelectedRow();
        if (selctedRowInvoice!=-1) {
         InvoiceHeader invoiceHeader=frame.getHeader().get(selctedRowInvoice);
         InvoiceLine invoiceLine =new InvoiceLine(item, pricedo, countInt, invoiceHeader);
         invoiceHeader.getInvoiceList().add(invoiceLine);
        InvoiceLineTable ilt = (InvoiceLineTable) frame. getLineTable().getModel();
        ilt.fireTableDataChanged();
        frame.getHeaderTable().fireTableDataChanged();
        }
    liDialog.setVisible(false);
    liDialog.dispose();
    liDialog = null;
    }

    private void createLineCancel() {
    liDialog.setVisible(false);
    liDialog.dispose();
    liDialog=null;
    }
}
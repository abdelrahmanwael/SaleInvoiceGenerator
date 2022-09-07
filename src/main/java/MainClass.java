//import sun.applet.Main;

import model.FileOperations;
import model.InvoiceHeader;
import model.InvoiceLine;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class MainClass extends JFrame {

    private JPanel Leftpanel;
    private JPanel Rightpanel;
    private JButton CreateInv;
    private JButton DeleteInv;
    private JButton SaveBtn;
    private JButton CancelBtn;

    private JLabel InvTable;
    private JLabel InvNum;
    private JLabel InvCount;
    private JLabel InvDate;
    private JLabel CustomerName;
    private JLabel InvTOT;
    private JLabel InvAmnt;
    private JLabel InvItems;

    private JTextField InvDateTxt;
    private JTextField CustomerNameTxt;

    private JTable InvoiceTable;
    private JTable InvItemsTable;

    private JMenuBar MainMenu;
    private JMenu MenuFile;
    private JMenuItem LoadFile;
    private JMenuItem SaveFile;

    public static void main(String[] args) {
        new MainClass().setVisible(true);
     }
     public MainClass() {
        super();
        setLayout(new BoxLayout(getContentPane(),BoxLayout.X_AXIS));
         //setLayout(new FlowLayout());

         CreateInv = new JButton();
         DeleteInv = new JButton();
         SaveBtn = new JButton();
         CancelBtn = new JButton();

         InvTable = new JLabel();
         InvNum = new JLabel();
         InvCount = new JLabel();
         InvDate = new JLabel();
         CustomerName = new JLabel();
         InvTOT = new JLabel();
         InvAmnt = new JLabel();
         InvItems = new JLabel();


         InvDateTxt = new JTextField();
         CustomerNameTxt = new JTextField();

         InvoiceTable = new JTable();
         InvItemsTable = new JTable();
         MainMenu = new JMenuBar();
         MenuFile = new JMenu("File");
         LoadFile = new JMenuItem("Load File");
         SaveFile = new JMenuItem("Save File");


         MenuFile.add(LoadFile);
         MenuFile.add(SaveFile);
         MainMenu.add(MenuFile);
         setJMenuBar(MainMenu);
         Rightpanel =new JPanel();
         Leftpanel = new JPanel();



         CreateInv.setText("Create New Invoice");
         DeleteInv.setText("Delete Invoice");
         SaveBtn.setText("Save");
         CancelBtn.setText("Cancel");

         InvTable.setText("Invoices Table");
         InvNum.setText("Invoice Number");
         InvCount.setText("0");
         InvDate.setText("Invoices Date");
         CustomerName.setText("Customer Name");
         InvTOT.setText("Invoices Total");
         InvAmnt.setText("0 EGP");
         InvItems.setText("Invoices Items");


         InvoiceTable.setModel(new DefaultTableModel(
                 new Object[][] {
                 },
                 new String[] {
                         "No.", "Date", "Customer", "Total"
                 }
         ));

         InvItemsTable.setModel(new DefaultTableModel(
                 new Object[][] {
                 },
                 new String[] {
                         "No.", "Item Name", "Item Price", "Count","Item Total"
                 }
         ));
         JScrollPane scroll1 =  new JScrollPane(InvoiceTable);
         JScrollPane scroll2 =  new JScrollPane(InvItemsTable);

         Dimension size = InvoiceTable.getPreferredSize();
         CreateInv.setBounds(30,600,200,30);
         DeleteInv.setBounds(250,600,200,30);
         InvTable.setBounds(30,0,200,30);
         scroll1.setBounds(30,65,600,450);
         Leftpanel.setLayout(null);

         InvTable.setSize(1000,100);
         Leftpanel.add(InvTable);
         Leftpanel.add(scroll1);
         Leftpanel.add(CreateInv);
         Leftpanel.add(DeleteInv);

         Rightpanel.setLayout(null);

         SaveBtn.setBounds(30,600,200,30);
         CancelBtn.setBounds(250,600,200,30);
         InvNum.setBounds(30,40,100,30);
         InvCount.setBounds(150,40,100,30);
         InvDate.setBounds(30,70,100,30);
         InvDateTxt.setBounds(150,70,100,30);
         CustomerName.setBounds(30,100,100,30);
         CustomerNameTxt.setBounds(150,100,100,30);

         InvTOT.setBounds(30,130,100,30);
         InvAmnt.setBounds(150,130,100,30);
         InvItems.setBounds(30,170,100,30);
         scroll2.setBounds(30,200,600,370);


         Rightpanel.add(InvDate);
         Rightpanel.add(InvDateTxt);

         Rightpanel.add(InvNum);
         Rightpanel.add(InvCount);
         Rightpanel.add(CustomerName);
         Rightpanel.add(InvTOT);
         Rightpanel.add(InvAmnt);
         Rightpanel.add(InvItems);
         Rightpanel.add(CustomerNameTxt);
         Rightpanel.add(scroll2);
         Rightpanel.add(SaveBtn);
         Rightpanel.add(CancelBtn);


         add(Leftpanel);
         add(Rightpanel);
         InvoiceTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
         InvItemsTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

         //Load Last inital status
         DefaultTableModel InvoiceTablemodel = (DefaultTableModel) InvoiceTable.getModel();
         DefaultTableModel InvItemModel1 = (DefaultTableModel) InvItemsTable.getModel();

         InvoiceTablemodel.setRowCount(0);

//         try {
//             File myObj = new File("DataFiles/InvoiceHeader.csv");
//             Scanner myReader = new Scanner(myObj);
//             while (myReader.hasNextLine()) {
//                 String data = myReader.nextLine();
//                 data += ",0";
//                 String[] dataLine = data.split(",");
//                 InvoiceTablemodel.addRow(dataLine);
//             }
//             myReader.close();
//         } catch (FileNotFoundException e1) {
//             System.out.println("An error occurred.");
//             e1.printStackTrace();
//         }

         ArrayList<InvoiceHeader> InVlist = FileOperations.readFile();
         for (int i = 0; i < InVlist.size(); i++) {
             InvoiceTablemodel.addRow(new Object[]{InVlist.get(i).getInvNumber(),
                     InVlist.get(i).getDate(),InVlist.get(i).getCustomerName(),InVlist.get(i).getTotalAmt()});
         }


//         List<String> InvItemList = new ArrayList<String>();
//         try {
//             File myObj = new File("DataFiles/InvoiceLine.csv");
//             Scanner myReader = new Scanner(myObj);
//             while (myReader.hasNextLine()) {
//                 String data = myReader.nextLine();
//                 String[] dataLine = data.split(",");
//                 InvItemList.add(data+"," + (Integer.parseInt(dataLine[2]) * Integer.parseInt(dataLine[3])) );
//             }
//             myReader.close();

//     } catch (Exception e1) {
//        System.out.println("An error occurred.");
//        e1.printStackTrace();
//    }
             ArrayList<InvoiceLine> InvItemList = FileOperations.InvItemreadFile();


             //Adding Items To total Invoice Amount
             for (int i = 0; i < InvItemList.size(); i++) {
                 for (int j = 0; j < InvoiceTablemodel.getRowCount(); j++) {
                         if ((InvoiceTablemodel.getValueAt(j,0)+"").equals(InvItemList.get(i).getInvNumber()+""))
                         {
                             Double TableValue = Double.parseDouble(InvoiceTablemodel.getValueAt(j,3).toString());
                             Double ItemPrice = InvItemList.get(i).getItemFullPrice();
                             InvoiceTablemodel.setValueAt( TableValue +ItemPrice
                                     ,j,3);
                         }
                    }
             }
             for (int i = 0; i < InVlist.size(); i++) {
                 String out =  "Invoice" + InVlist.get(i).getInvNumber()+"\n" +
                         "{\n" +
                         "\t" + InVlist.get(i).getDate()+","+InVlist.get(i).getCustomerName()+"\n";
                        for (int j = 0; j < InvItemList.size(); j++) {
                            if (InVlist.get(i).getInvNumber() == InvItemList.get(j).getInvNumber())
                            out += "\t" + InvItemList.get(j).getItemName()+"," + InvItemList.get(j).getItemPrice()
                                    + ","+ InvItemList.get(j).getQuantity()+ "\n";
                        }
                        out += "}";
                 System.out.println(out);
             }

         InvoiceTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
             public void valueChanged(ListSelectionEvent event) {
                 try {
                     InvItemModel1.setRowCount(0);
                     for (int i = 0; i < InvItemList.size(); i++) {
                         if ((InvItemList.get(i).getInvNumber() + "").equals(InvoiceTable.getValueAt(InvoiceTable.getSelectedRow(), 0).toString())) {

                             InvItemModel1.addRow(new Object[]{InvItemList.get(i).getInvNumber(),
                                     InvItemList.get(i).getItemName(),
                                     InvItemList.get(i).getItemPrice(),
                                     InvItemList.get(i).getQuantity(),
                                     InvItemList.get(i).getItemFullPrice()});
                             InvCount.setText(InvoiceTable.getValueAt(InvoiceTable.getSelectedRow(), 0).toString());
                             InvDateTxt.setText(InvoiceTable.getValueAt(InvoiceTable.getSelectedRow(), 1).toString());
                             CustomerNameTxt.setText(InvoiceTable.getValueAt(InvoiceTable.getSelectedRow(), 2).toString());
                             InvAmnt.setText(InvoiceTable.getValueAt(InvoiceTable.getSelectedRow(), 3).toString());
                             InvoiceTable.addNotify();
                         }
                     }
                 }catch (Exception e){
                     //do nothing
                     e.printStackTrace();
                 }
             }
         });

         setSize(800,800);
         setLocation(200,200);
         setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
         setExtendedState(JFrame.MAXIMIZED_BOTH);
         setTitle("Sales Invoice Generator");
         CreateInv.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 InvoiceTablemodel.addRow(new Object[]{"", "", ""});
             }
         });
         DeleteInv.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 if(InvoiceTable.getSelectedRow() != -1) {
                     // remove selected row from the model
                     int selectedRows = InvoiceTable.getSelectedRow();

                     for (int i = 0; i < InvItemList.size(); i++) {
                         String CustID = InvoiceTablemodel.getValueAt(selectedRows,0).toString();
                         if ((InvItemList.get(i).getInvNumber()+"").equals(CustID)){
                             InvItemList.remove(i);
                         }
                     }
                     InvoiceTablemodel.removeRow(selectedRows);

                     InvoiceTable.addNotify();
                     JOptionPane.showMessageDialog(null, "Selected row deleted successfully");
                 }
             }
         });

         SaveBtn.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 for (int j = 0; j < InvoiceTablemodel.getRowCount(); j++) {
                     String Invno = InvCount.getText();
                     if ((InvoiceTablemodel.getValueAt(j,0)+"").equals(Invno))
                     {
                         InvoiceTablemodel.setValueAt(CustomerNameTxt.getText(),j,2);
                         InvoiceTablemodel.setValueAt(InvDateTxt.getText(),j,1);
                         InvoiceTable.addNotify();
                         JOptionPane.showMessageDialog(null, "Data Update Successfully");
                     }
                 }
             }
         });
         CancelBtn.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {

                 for (int j = 0; j < InvoiceTablemodel.getRowCount(); j++) {
                     String Invno = InvCount.getText();
                     if ((InvoiceTablemodel.getValueAt(j,0)+"").equals(Invno))
                     {
                         InvDateTxt.setText(InvoiceTablemodel.getValueAt(j,1).toString());
                         CustomerNameTxt.setText(InvoiceTablemodel.getValueAt(j,2).toString());

                     }
                 }

             }
         });
         LoadFile.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 try {
                     InvoiceTablemodel.setRowCount(0);

                     File myObj = new File("DataFiles/EditedInvoiceHeader.csv");
                     Scanner myReader = new Scanner(myObj);
                     while (myReader.hasNextLine()) {
                         String data = myReader.nextLine();
                         data += ",0";
                         String[] dataLine = data.split(",");
                         InvoiceTablemodel.addRow(dataLine);
                     }
                     myReader.close();
                 } catch (FileNotFoundException e1) {
                     System.out.println("An error occurred.");
                     e1.printStackTrace();
                 }

                 List<String> InvItemList = new ArrayList<String>();
                 try {
                     File myObj = new File("DataFiles/EditedInvoiceLine.csv");
                     Scanner myReader = new Scanner(myObj);
                     int CustomerTotInvAmt = 0;
                     String Customer_id = "";
                     while (myReader.hasNextLine()) {
                         String data = myReader.nextLine();
                         String[] dataLine = data.split(",");
                         InvItemList.add(data+"," + (Integer.parseInt(dataLine[2]) * Integer.parseInt(dataLine[3])) );
                     }
                     myReader.close();
                     for (int i = 0; i < InvItemList.size(); i++) {
                         for (int j = 0; j < InvoiceTablemodel.getRowCount(); j++) {
                             if (InvoiceTablemodel.getValueAt(j,0).equals(InvItemList.get(i).split(",")[0]))
                             {
                                 InvoiceTablemodel.setValueAt(Integer.parseInt(InvoiceTablemodel.getValueAt(j,3).toString())+ Integer.parseInt(InvItemList.get(i).split(",")[4]),j,3);
                             }
                         }
                     }
                     JOptionPane.showMessageDialog(null, "Load File successfully");

                 } catch (FileNotFoundException e1) {
                     System.out.println("An error occurred.");
                     e1.printStackTrace();
                 }
             }
         });
         SaveFile.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {

                 String fileContent = "";
                 ArrayList<InvoiceHeader> EditInvList = new ArrayList<>();

                 for (int j = 0; j < InvoiceTablemodel.getRowCount(); j++) {
//                     fileContent +=  InvoiceTablemodel.getValueAt(j,0).toString() + ","
//                             + InvoiceTablemodel.getValueAt(j,1).toString() + ","
//                             + InvoiceTablemodel.getValueAt(j,2).toString() + ","
//                             + InvoiceTablemodel.getValueAt(j,3).toString() + "\r\n";

                     InvoiceHeader inv = new InvoiceHeader();
                     inv.setInvNumber(Integer.parseInt(InvoiceTablemodel.getValueAt(j,0).toString()));
                     inv.setDate(InvoiceTablemodel.getValueAt(j,1).toString());
                     inv.setCustomerName(InvoiceTablemodel.getValueAt(j,2).toString());
                     inv.setTotalAmt(Double.parseDouble(InvoiceTablemodel.getValueAt(j,3).toString()));
                     EditInvList.add(inv);
                 }
                 FileOperations.Write(EditInvList);

                 FileOperations.InvItemWrite(InvItemList);

             }

         });

     }
}

package Controller;

import model.InvoiceLine;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class Controller {

    public static void DeleteBtn(JTable InvItemsTable, DefaultTableModel InvItemModel1, ArrayList<InvoiceLine> InvItemList){
        if(InvItemsTable.getSelectedRow() != -1) {
            // remove selected row from the model
            int selectedRows = InvItemsTable.getSelectedRow();
            String CustID = InvItemModel1.getValueAt(selectedRows,1).toString();
            for (int i = 0; i < InvItemList.size(); i++) {
                if ((InvItemList.get(i).getItemName()).equals(CustID)){
                    InvItemList.remove(i);
                }
            }
            InvItemModel1.removeRow(selectedRows);

            InvItemsTable.addNotify();
            JOptionPane.showMessageDialog(null, "Selected row deleted successfully");
        }else {
            JOptionPane.showMessageDialog(null, "Kindly Select row to delete");
        }
    }
    public static void CancelBtn(DefaultTableModel InvoiceTablemodel, JTextField CustomerNameTxt, JTextField InvDateTxt, JTable InvoiceTable, JLabel InvCount){
        for (int j = 0; j < InvoiceTablemodel.getRowCount(); j++) {
            String Invno = InvCount.getText();
            if ((InvoiceTablemodel.getValueAt(j,0)+"").equals(Invno))
            {
                InvDateTxt.setText(InvoiceTablemodel.getValueAt(j,1).toString());
                CustomerNameTxt.setText(InvoiceTablemodel.getValueAt(j,2).toString());

            }
        }
    }
    public static void savebtn(DefaultTableModel InvoiceTablemodel, JTextField CustomerNameTxt, JTextField InvDateTxt, JTable InvoiceTable, JLabel InvCount){
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
    public static void DeleteInv( JTable InvoiceTable,ArrayList<InvoiceLine> InvItemList,DefaultTableModel InvoiceTablemodel){
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
}

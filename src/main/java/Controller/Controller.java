package Controller;

import model.InvoiceLine;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Arrays;

public class Controller {

    public static void DeleteBtn(JTable InvItemsTable, DefaultTableModel InvItemModel1, ArrayList<InvoiceLine> InvItemList, DefaultTableModel InvoiceTablemodel){
        if(InvItemsTable.getSelectedRow() != -1) {
            // remove selected row from the model
            int selectedRows = InvItemsTable.getSelectedRow();
            String ItemName = InvItemModel1.getValueAt(selectedRows,1).toString();
            int InvNum = Integer.parseInt(InvItemModel1.getValueAt(selectedRows,0).toString());

            for (int i = 0; i < InvItemList.size(); i++) {
                if ((InvItemList.get(i).getItemName()).equals(ItemName)){
                    InvItemList.remove(i);
                }
            }
            InvItemModel1.removeRow(selectedRows);
            for (int i = 0; i < InvoiceTablemodel.getRowCount(); i++) {
                if (Integer.parseInt(InvoiceTablemodel.getValueAt(i,0).toString()) == InvNum){
                    int sum = 0;
                    for (int j = 0; j < InvItemList.size(); j++) {
                        if (InvNum == InvItemList.get(j).getInvNumber()){
                            sum += InvItemList.get(j).getItemFullPrice();
                        }
                    }
                    InvoiceTablemodel.setValueAt(sum,i,3);
                }
            }
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
    public static void savebtn(DefaultTableModel InvoiceTablemodel, JTextField CustomerNameTxt
            , JTextField InvDateTxt, JTable InvoiceTable, JLabel InvCount,ArrayList<InvoiceLine> InvItemList,DefaultTableModel InvItemModel1){
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
        if (InvItemModel1.getRowCount() > 0){
            int InvoiceNumber = Integer.parseInt(InvItemModel1.getValueAt(0,0).toString());
            ArrayList<Integer> deletedindexs = new ArrayList<Integer>();
            for (int i = 0; i < InvItemList.size(); i++) {
                if (InvItemList.get(i).getInvNumber() == InvoiceNumber){
                    deletedindexs.add(i);
                }
            }

            for (int i = deletedindexs.size()-1; i >= 0; i--) {
                int index = deletedindexs.get(i);
                InvItemList.remove(index);
            }

            for (int i = 0; i < InvItemModel1.getRowCount(); i++) {
                InvoiceLine Item = new InvoiceLine();
                Item.setInvNumber( Integer.parseInt(InvItemModel1.getValueAt(i,0).toString()));
                Item.setItemName(InvItemModel1.getValueAt(i,1).toString());
                Item.setItemPrice(Double.parseDouble(InvItemModel1.getValueAt(i,2).toString()));
                Item.setQuantity(Integer.parseInt(InvItemModel1.getValueAt(i,3).toString()));
                InvItemList.add(Item);
            }

            for (int i = 0; i < InvoiceTablemodel.getRowCount(); i++) {
                if (Integer.parseInt(InvoiceTablemodel.getValueAt(i,0).toString())
                        == Integer.parseInt(InvItemModel1.getValueAt(0,0).toString())){
                    int sum = 0;
                    for (int j = 0; j < InvItemList.size(); j++) {
                        if (InvItemList.get(j).getInvNumber() == Integer.parseInt(InvoiceTablemodel.getValueAt(i,0).toString()) ){
                            sum += InvItemList.get(j).getItemFullPrice();
                        }
                    }
                    InvoiceTablemodel.setValueAt(sum , i,3);
                }
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

package model;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileOperations {

    public static ArrayList<InvoiceHeader> readFile(){
        ArrayList<InvoiceHeader> InvHDRList = new ArrayList<InvoiceHeader>();

        try {
            File myObj = new File("DataFiles/InvoiceHeader.csv");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                InvoiceHeader invHdr = new InvoiceHeader();
                String data = myReader.nextLine();
                String[] dataLine = data.split(",");
                invHdr.setInvNumber(Integer.parseInt(dataLine[0]));
                invHdr.setDate(dataLine[1]);
                invHdr.setCustomerName(dataLine[2]);
                invHdr.setTotalAmt(0d);
                InvHDRList.add(invHdr);
            }
            myReader.close();
        } catch (FileNotFoundException e1) {
            System.out.println("An error occurred.");
            e1.printStackTrace();
        }
        return InvHDRList;
    }
    public static ArrayList<InvoiceLine> InvItemreadFile(){
        ArrayList<InvoiceLine> InvItemList = new ArrayList<InvoiceLine>();

        try {
            File myObj = new File("DataFiles/InvoiceLine.csv");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                InvoiceLine InvItem = new InvoiceLine();
                String data = myReader.nextLine();
                String[] dataLine = data.split(",");
                InvItem.setInvNumber(Integer.parseInt(dataLine[0]));
                InvItem.setItemName(dataLine[1]);
                InvItem.setItemPrice(Double.parseDouble(dataLine[2]));
                InvItem.setQuantity(Integer.parseInt(dataLine[3]));
                InvItemList.add(InvItem);
            }
            myReader.close();
        } catch (FileNotFoundException e1) {
            System.out.println("An error occurred.");
            e1.printStackTrace();
        }
        return InvItemList;
    }
    public static void Write(ArrayList<InvoiceHeader> InvHdrList) {
        String fileContent = "";
        for (int i = 0; i < InvHdrList.size(); i++) {
            fileContent += InvHdrList.get(i).getInvNumber() + "," + InvHdrList.get(i).getDate() + ","
                    + InvHdrList.get(i).getCustomerName() + "," + InvHdrList.get(i).getTotalAmt() + "\r\n";
        }
        try {
            String FileName = "DataFiles/EditedInvoiceHeader.csv";
            FileWriter fw = new FileWriter(FileName, false); //the true will append the new data
            fw.write(fileContent);//appends the string to the file
            fw.close();
        } catch (IOException e1) {
            e1.printStackTrace();

        }
    }
    public static void InvItemWrite(ArrayList<InvoiceLine> InvItemList) {
        String fileContent = "";
        for (int j = 0; j < InvItemList.size(); j++) {
            fileContent += InvItemList.get(j).getInvNumber()+","
                    +InvItemList.get(j).getInvNumber()+","
                    +InvItemList.get(j).getItemName() +","
                    +InvItemList.get(j).getItemPrice() +","
                    +InvItemList.get(j).getQuantity()+ ","
                    + InvItemList.get(j).getItemFullPrice()
                    +"\r\n";
        }

        try {
            String FileName = "DataFiles/EditedInvoiceLine.csv";
            FileWriter fw = new FileWriter(FileName,false); //the true will append the new data
            fw.write(fileContent);//appends the string to the file
            fw.close();
            JOptionPane.showMessageDialog(null, "Save File successfully");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

}

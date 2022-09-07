package model;

public class InvoiceLine {
    private int InvNumber;
    private String ItemName;
    private double ItemPrice;
    private int Quantity;
     private Double ItemFullPrice;

    public void setItemPrice(double itemPrice) {
        ItemPrice = itemPrice;
    }

    public Double getItemFullPrice() {
        return Quantity * ItemPrice;
    }



    public int getInvNumber() {
        return InvNumber;
    }

    public void setInvNumber(int invNumber) {
        InvNumber = invNumber;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public Double getItemPrice() {
        return ItemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        ItemPrice = itemPrice;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}

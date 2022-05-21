////////////////////////////////////////////////////////////////////
// Alberto Rizzo 2008079
// Alberto Franzin 2011879
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

public class EItem {
    public enum itemEnum{Processor, Motherboard, Mouse, Keyboard};
    private String name;
    private double price;
    private itemEnum itemType;

    public EItem(itemEnum itemType, String name, double price){
        if (price <= 0){
            throw new IllegalArgumentException("Il prezzo deve essere positivo e non nullo");
        }
        this.price = price;
        this.name = name;
        this.itemType = itemType;
    }
    public String getName(){
        return name;
    }
    public double getPrice(){
        return price;
    }
    public itemEnum getItemType(){
        return itemType;
    }
}

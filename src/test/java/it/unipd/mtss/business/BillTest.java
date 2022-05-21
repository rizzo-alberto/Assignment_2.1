////////////////////////////////////////////////////////////////////
// Alberto Rizzo 2008079
// Alberto Franzin 2011879
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.User;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BillTest {
    Bill bill = null;
    List<EItem> orderList = null;
    User usr = null;
    @Before
    public void beforeTests() {
        bill = new BillImplementation();
        orderList = new ArrayList<EItem>();
        usr = new User("The","Roccia", LocalDate.of(1961,5,11),"elonmaschera@fatturare.soldi.it");
    }

    @Test
    public void orderPrice_NullUserTest(){
        orderList.add(new EItem(EItem.itemEnum.Mouse, "Trust mePlease_black", 9.99));
        try {
            bill.getOrderPrice(orderList,null);
        }
        catch(BillException e){
            assertEquals("Il cliente deve essere definito",e.getMessage());
        }
    }

    @Test
    public void orderPrice_NullEItemTest(){
        orderList.add(null);
        try {
            bill.getOrderPrice(orderList,usr);
        }
        catch(BillException e){
            assertEquals("I prodotti nella lista non possono essere nulli",e.getMessage());
        }
    }

    @Test
    public void orderPrice_EmptyList(){
        try {
            bill.getOrderPrice(orderList,usr);
        }
        catch(BillException e){
            assertEquals("La lista degli ordini non può essere vuota",e.getMessage());
        }
    }

    @Test
    public void orderPrice_CalculateTotalTest() throws  BillException{
        orderList.add(new EItem(EItem.itemEnum.Mouse, "Trust mePlease_black", 9.99));
        orderList.add(new EItem(EItem.itemEnum.Mouse, "Logitech MAXNOISE_CURSED_SWITCH_BLACK", 14.99));
        orderList.add(new EItem(EItem.itemEnum.Motherboard, "MSI_PAOLA", 159.99));

        assertEquals(184.97,bill.getOrderPrice(orderList,usr),1e-8);
    }

    @Test
    public void orderPrice_LessThanSixCPUDiscountTest() throws  BillException{
        orderList.add(new EItem(EItem.itemEnum.Processor, "Ryzen 5 3600X", 250.99));
        orderList.add(new EItem(EItem.itemEnum.Processor, "Ryzen 7 4600X", 350.99));
        orderList.add(new EItem(EItem.itemEnum.Processor, "Ryzen 9 5900X", 450.37));


        assertEquals(1052.35,bill.getOrderPrice(orderList,usr),1e-8);
    }

    @Test
    public void orderPrice_MoreThanFiveCPUDiscountTest() throws  BillException{
        orderList.add(new EItem(EItem.itemEnum.Processor, "Ryzen 5 3600X", 250.99));
        orderList.add(new EItem(EItem.itemEnum.Processor, "Ryzen 7 4600X", 350.99));
        orderList.add(new EItem(EItem.itemEnum.Processor, "Ryzen 9 5900X", 450.37));
        orderList.add(new EItem(EItem.itemEnum.Processor, "Intel i5 6900", 150.80));
        orderList.add(new EItem(EItem.itemEnum.Processor, "Intel i7 7900", 330.99));
        orderList.add(new EItem(EItem.itemEnum.Processor, "Intel i9 Lake dragon", 500.99));

        assertEquals(1959.73,bill.getOrderPrice(orderList,usr),1e-8);
    }

    @Test
    public void orderPrice_MoreThanTenMouseDiscountTest() throws  BillException{
        orderList.add(new EItem(EItem.itemEnum.Mouse, "Asus Jotaro", 10.99));
        orderList.add(new EItem(EItem.itemEnum.Mouse, "Microsoft Giorno", 8.99));
        orderList.add(new EItem(EItem.itemEnum.Mouse, "LogiTech LisaLisa", 8.37));
        orderList.add(new EItem(EItem.itemEnum.Mouse, "Trust Avdol", 12.80));
        orderList.add(new EItem(EItem.itemEnum.Mouse, "Microsoft Bucciarati", 11.99));
        orderList.add(new EItem(EItem.itemEnum.Mouse, "Razer Joseph", 99.99));
        orderList.add(new EItem(EItem.itemEnum.Mouse, "Razer Polnareff", 16.99));
        orderList.add(new EItem(EItem.itemEnum.Mouse, "Asus Go Go Zeppeli", 34.99));
        orderList.add(new EItem(EItem.itemEnum.Mouse, "Asus ZaWardo", 45.37));
        orderList.add(new EItem(EItem.itemEnum.Mouse, "Apple no", 5.80));
        orderList.add(new EItem(EItem.itemEnum.Mouse, "LogiTech tekno", 6.99));


        assertEquals(257.47,bill.getOrderPrice(orderList,usr),1e-8);
    }

    @Test
    public void orderPrice_LessThanElevenMouseDiscountTest() throws  BillException{
        orderList.add(new EItem(EItem.itemEnum.Mouse, "Asus Jotaro", 10.99));
        orderList.add(new EItem(EItem.itemEnum.Mouse, "Microsoft Giorno", 8.99));
        orderList.add(new EItem(EItem.itemEnum.Mouse, "LogiTech LisaLisa", 8.37));

        assertEquals(28.35,bill.getOrderPrice(orderList,usr),1e-8);
    }

    @Test
    public void orderPrice_KeyboardEqualsMouseDiscountTest() throws  BillException{
        orderList.add(new EItem(EItem.itemEnum.Mouse, "Asus Jotaro", 10.99));
        orderList.add(new EItem(EItem.itemEnum.Mouse, "Microsoft Giorno", 8.99));
        orderList.add(new EItem(EItem.itemEnum.Keyboard, "Lenovo Master Mentadent", 10.37));
        orderList.add(new EItem(EItem.itemEnum.Keyboard, "LogiTech Bass", 20.47));

        assertEquals(41.83,bill.getOrderPrice(orderList,usr),1e-8);
    }

    @Test
    public void orderPrice_KeyboardNOTEqualsMouseDiscountTest() throws  BillException{
        orderList.add(new EItem(EItem.itemEnum.Mouse, "Asus Jotaro", 10.99));
        orderList.add(new EItem(EItem.itemEnum.Mouse, "Microsoft Giorno", 8.99));
        orderList.add(new EItem(EItem.itemEnum.Keyboard, "LogiTech Bass", 20.47));

        assertEquals(40.45,bill.getOrderPrice(orderList,usr),1e-8);
    }
}
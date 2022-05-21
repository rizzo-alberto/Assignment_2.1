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
        orderList.add(new EItem(EItem.itemEnum.Keyboard, "Logitech MAXNOISE_CURSED_SWITCH_BLACK", 14.99));
        orderList.add(new EItem(EItem.itemEnum.Motherboard, "MSI_PAOLA", 159.99));

        assertEquals(184.97,bill.getOrderPrice(orderList,usr),1e-8);
    }
}
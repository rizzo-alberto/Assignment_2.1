////////////////////////////////////////////////////////////////////
// Alberto Rizzo 2008079
// Alberto Franzin 2011879
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EItemTest {
    EItem itm;

    @Before
    public void beforeTests() {
        itm = new EItem(EItem.itemEnum.Keyboard, "Trust Taro_pink", 19.99);
    }

    @Test
    public void negativePriceTest(){
        try{
            itm=new EItem(EItem.itemEnum.Keyboard, "Trust Taro_pink", -0.01);
        }catch(IllegalArgumentException e){
            assertEquals("Il prezzo deve essere positivo e non nullo",e.getMessage());
        }
    }

    @Test
    public void getNameTest(){
        assertEquals("Trust Taro_pink",itm.getName());
    }

    @Test
    public void getPriceTest(){
        assertEquals(19.99,itm.getPrice(),1e-8);
    }

    @Test
    public void getItemTypeTest(){
        assertEquals(EItem.itemEnum.Keyboard,itm.getItemType());
    }

}
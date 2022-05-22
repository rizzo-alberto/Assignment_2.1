package it.unipd.mtss.business;

import it.unipd.mtss.model.User;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.*;

public class OrderGiveawayTest {
    User usr;
    OrderGiveawayImplementaion orderGiveaway;

    @Before
    public void beforTests(){
        usr=new User("Alberto","Rizzo", LocalDate.of(2010,1,1),"alberto.stufo@gmail.com");
        orderGiveaway=new OrderGiveawayImplementaion();
    }

    @Test
    public void giveawayOrder_CanGiveTest(){
        assertEquals(true,orderGiveaway.canGive(usr, LocalTime.of(18,30)));
    }

    @Test
    public void giveawayOrder_NotCanGiveForAgeTest(){
        User u=new User("Alberto","Rizzo", LocalDate.of(2000,1,1),"alberto.stufo@gmail.com");
        assertEquals(false,orderGiveaway.canGive(u, LocalTime.of(18,30)));
    }

    @Test
    public void giveawayOrder_NotCanGiveForTimeTest(){
        assertEquals(false,orderGiveaway.canGive(usr, LocalTime.of(19,30)));
    }

    @Test
    public void giveawayOrder_AlreadyChoosenTest(){
        orderGiveaway.choosenUser.add(usr);
        assertEquals(false,orderGiveaway.canGive(usr, LocalTime.of(19,30)));
    }

    @Test
    public void giveawayOrder_AlreadyTenPersonTest(){
        for(int i=0;i<10;i++){
            User u=new User("Alberto","Rizzo", LocalDate.of(2010,1,1),"alberto.stufo@gmail.com");
            orderGiveaway.choosenUser.add(u);
        }
        assertEquals(false,orderGiveaway.canGive(usr, LocalTime.of(19,30)));
    }
}
////////////////////////////////////////////////////////////////////
// Alberto Rizzo 2008079
// Alberto Franzin 2011879
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import it.unipd.mtss.model.User;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OrderGiveawayImplementaion implements  OrderGiveaway{
    List<User> choosenUser;
    Random r;
    public OrderGiveawayImplementaion(){
        choosenUser=new ArrayList<User>();;
        r=new Random();
    }
    public boolean canGive(User usr, LocalTime orderTime){
        if(choosenUser.size()>=10) return false;
        if(usr.getBirth()!=null && Period.between(usr.getBirth(), LocalDate.now()).getYears()<18){
            if(!choosenUser.contains(usr)){

                if(orderTime.getHour() ==18 || (orderTime.getHour()==19 && orderTime.getMinute()==0)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean giveawayOrder(User usr, LocalTime orderTime) {
        if(usr==null){
            throw new IllegalArgumentException("L'utente non può essere nullo");
        }
        if(orderTime==null){
            throw new IllegalArgumentException("L'orario non può essere nullo");
        }
        if(canGive(usr,orderTime)){
            if(r.nextInt(100)<50){
                choosenUser.add(usr);
                return true;
            }
        }
        return false;
    }
}

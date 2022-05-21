////////////////////////////////////////////////////////////////////
// Alberto Rizzo 2008079
// Alberto Franzin 2011879
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.User;

import java.util.List;

public class BillImplementation implements Bill {
    public double getOrderPrice(List<EItem> itemsOrdered, User user) throws BillException {
        double tot = 0.0;
        int numberOfProcessor = 0;
        int numberOfMouse = 0;
        int numberOfKeyboards = 0;

        double priceProcMIN = Double.MAX_VALUE;
        double priceMouseMIN = Double.MAX_VALUE;
        double priceKeyboardMIN = Double.MAX_VALUE;


        if(user == null) {
            throw new BillException("Il cliente deve essere definito");
        }

        if(itemsOrdered.contains(null)) {
            throw new BillException("I prodotti nella lista non possono essere nulli");
        }

        if(itemsOrdered == null) {
            throw new BillException("La lista degli ordini non può essere nulla");
        }

        if(itemsOrdered.isEmpty()) {
            throw new BillException("La lista degli ordini non può essere vuota");
        }

        //calcolo numero cpu, cpu + economica
        for (EItem itm : itemsOrdered){
            if (itm.getItemType() == EItem.itemEnum.Processor){
                numberOfProcessor++;
                if(itm.getPrice()<priceProcMIN){
                    priceProcMIN = itm.getPrice();
                }
            }
        }
        //calcolo numero mouse, mouse + economico
        for (EItem itm : itemsOrdered){
            if (itm.getItemType() == EItem.itemEnum.Mouse){
                numberOfMouse++;
                if(itm.getPrice()<priceMouseMIN){
                    priceMouseMIN = itm.getPrice();
                }
            }
        }

        //calcolo numero tastiere, tastiera + economico
        for (EItem itm : itemsOrdered){
            if (itm.getItemType() == EItem.itemEnum.Keyboard){
                numberOfKeyboards++;
                if(itm.getPrice()<priceKeyboardMIN){
                    priceKeyboardMIN = itm.getPrice();
                }
            }
        }

        //calcolo totale base
        for (EItem itm : itemsOrdered) {
            tot+=itm.getPrice();
        }

        //applico sconto processori, se necessario
        if(numberOfProcessor > 5){
           tot -= priceProcMIN/2;
        }

        //applico sconto mouse, se necessario
        if(numberOfMouse > 10){
            tot -= priceMouseMIN;
        }

        //applico sconto mouse = tastiere, se necessario
        if(numberOfMouse == numberOfKeyboards && numberOfMouse !=0){
            if(priceMouseMIN > priceKeyboardMIN){
                tot -= priceKeyboardMIN;
            }else{
                tot -= priceMouseMIN;
            }
        }

        //sconto 10% sul totale
        if(tot>1000){
            tot-=(tot/100) * 10;
        }

        return tot;
    }
}
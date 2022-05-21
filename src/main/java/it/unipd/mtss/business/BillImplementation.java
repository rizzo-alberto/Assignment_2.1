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


        return tot;
    }
}
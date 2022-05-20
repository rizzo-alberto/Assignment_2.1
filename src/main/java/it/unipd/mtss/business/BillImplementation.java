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
        return tot;
    }
}
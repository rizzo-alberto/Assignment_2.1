////////////////////////////////////////////////////////////////////
// Alberto Rizzo 2008079
// Alberto Franzin 2011879
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import java.time.LocalTime;
import it.unipd.mtss.model.User;

public interface OrderGiveaway {
    boolean giveawayOrder(User usr, LocalTime orderTime);
}
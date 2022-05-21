////////////////////////////////////////////////////////////////////
// Alberto Rizzo 2008079
// Alberto Franzin 2011879
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class UserTest {
    User usr;

    @Before
    public void beforeTests(){
        usr = new User("Elon","Maschera",LocalDate.of(1971,6,28),"elonmaschera@fatturare.soldi.it");
    }

    @Test
    public void getNameTest(){
        assertEquals("Elon",usr.getName());
    }

    @Test
    public void getSurnameTest(){
        assertEquals("Maschera",usr.getSurname());
    }

    @Test
    public void getBirthTest(){
        assertEquals(LocalDate.of(1971,6,28),usr.getBirth());
    }

    @Test
    public void getEmailTest(){
        assertEquals("elonmaschera@fatturare.soldi.it",usr.getEmail());
    }

    @Test
    public void emptyNameTest(){
        try{
            usr = new User("","Maschera",LocalDate.of(1971,6,28),"elonmaschera@fatturare.soldi.it");
        }catch(IllegalArgumentException e){
            assertEquals("Il nome non può essere vuoto!",e.getMessage());
        }
    }

    @Test
    public void emptySurnameTest(){
        try{
            usr = new User("Elon","",LocalDate.of(1971,6,28),"elonmaschera@fatturare.soldi.it");
        }catch(IllegalArgumentException e){
            assertEquals("Il cognome non può essere vuoto!",e.getMessage());
        }
    }

    @Test
    public void emptyEmailTest(){
        try{
            usr = new User("Elon","Maschera",LocalDate.of(1971,6,28),"");
        }catch(IllegalArgumentException e){
            assertEquals("L'email non può essere vuota!",e.getMessage());
        }
    }

    @Test
    public void futureBithTest(){
        try{
            usr = new User("Elon","Maschera",LocalDate.of(2071,6,28),"elonmaschera@fatturare.soldi.it");
        }catch(IllegalArgumentException e){
            assertEquals("La data di nascita non può essere nel futuro!",e.getMessage());
        }
    }

    @Test
    public void nullBirthTest(){
        try{
            usr = new User("Elon","Maschera",null,"elonmaschera@fatturare.soldi.it");
        }catch(IllegalArgumentException e){
            assertEquals("Bisogna specificare la data di nascita!",e.getMessage());
        }
    }
}
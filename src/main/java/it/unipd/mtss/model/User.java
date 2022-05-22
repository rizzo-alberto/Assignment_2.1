////////////////////////////////////////////////////////////////////
// Alberto Rizzo 2008079
// Alberto Franzin 2011879
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

import java.time.LocalDate;

public class User {
    private String name;
    private String surname;
    private LocalDate birth;
    private String email;

    public  User(String name, String surname, LocalDate birth, String email){
        if(email.trim() == ""){
            throw new IllegalArgumentException("L'email non può essere vuota!");
        }
        if(name.trim() == ""){
            throw new IllegalArgumentException("Il nome non può essere vuoto!");
        }
        if(surname.trim() == ""){
            throw new IllegalArgumentException("Il cognome non può essere vuoto!");
        }
        if(birth == null){
            throw new IllegalArgumentException("Bisogna specificare la data di nascita!");
        }
        if(birth.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("La data di nascita non può essere nel futuro!");
        }
        this.name = name;
        this.surname = surname;
        this.birth = birth;
        this.email = email;
    }

    public String getName() {
        return name;
    }
    public String getSurname(){
        return surname;
    }
    public LocalDate getBirth(){
        return birth;
    }
    public String getEmail(){
        return email;
    }
}

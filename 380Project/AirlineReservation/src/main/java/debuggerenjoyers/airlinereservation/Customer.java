/**
 * Customer.java
 * November 21, 2023
 * @author Diego Hernandez
 *
 * The Customer class is designed to represent a Customer.
 * Has 4 Fields to represent Firstname, Lastname, email address, and a List of cards.
 */
package debuggerenjoyers.airlinereservation;

import java.util.List;
public class Customer {

    private String firstName;
    private String lastName;
    private String emailAddress;
    private List<Card> creditCards;

    /**
     * Customer constructor with firstname, lastname, email address, and a list of cards as input parameters.
     * @param firstName
     * @param lastName
     * @param emailAddress
     * @param creditCards
     */
    public Customer(String firstName, String lastName, String emailAddress, List<Card> creditCards){
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.creditCards = creditCards;
    }

    /**
     * Default Customer Constructor
     */
    public Customer(){
        this.firstName = null;
        this.lastName = null;
        this.emailAddress = null;
        this.creditCards = null;
    }

    /**
     * Access method for Customer FullName. Returns String
     * @return firstName and lastName
     */
    public String getFullName(){
        return firstName+' '+lastName;
    }

    /**
     * Access method for Customer first name. Returns String
     * @return firstName
     */
    public String getFirstName(){
        return firstName;
    }

    /**
     * Access method for Customer Lastname. Returns String
     * @return lastName
     */
    public String getLastName(){
        return lastName;
    }

    /**
     * Access method for Customer email address. Returns String
     * @return emailAddress
     */
    public String getEmailAddress(){
        return emailAddress;
    }

    /**
     * Access method for Customer List of cards. Returns List of cards
     * @return creditCards
     */
    public List<Card> getCreditCards(){
        return creditCards;
    }
}

package debuggerenjoyers.airlinereservation;

public class Customer {

    private String firstName;
    private String lastName;
    private String emailAddress;
    private Card[] creditCards;

    public Customer(String firstName, String lastName, String emailAddress, Card[] creditCards){
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.creditCards = creditCards;
    }

    public Customer(){
        this.firstName = null;
        this.lastName = null;
        this.emailAddress = null;
        this.creditCards = null;
    }

    public String getFullName(){
        return firstName+' '+lastName;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getEmailAddress(){
        return emailAddress;
    }
    public Card[] getCreditCards(){
        return creditCards;
    }
}

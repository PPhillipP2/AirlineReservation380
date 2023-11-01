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

    public String getName(){
        return firstName+' '+lastName;
    }

    public String getEmailAddress(){
        return emailAddress;
    }

    public Card[] getCreditCards(){
        return creditCards;
    }
}

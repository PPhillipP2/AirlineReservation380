package debuggerenjoyers.airlinereservation;
import java.util.Date;
public class Card {

    private int cardNum;
    private Date experationDate;
    private int cvc;
    private String cardAddress;

    public Card(int cardNum, Date experationDate, int cvc, String cardAddress){
        this.cardNum = cardNum;
        this.experationDate = experationDate;
        this.cvc = cvc;
        this.cardAddress = cardAddress;
    }

    public int getCardNum(){
        return cardNum;
    }

    public Date getExperationDate(){
        return experationDate;
    }

    public int getCvc(){
        return cvc;
    }

    public String getCardAddress(){
        return cardAddress;
    }
}

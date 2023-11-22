/**
 * Purchase.java
 * November 21, 2023
 * @author Diego Hernandez
 *
 * The Purchase class is designed to represent a Purchase. Has 4 Fields
 * A Customer, Purchase Status, purchase total, and card used Index.
 */
package debuggerenjoyers.airlinereservation;

public class Purchase {
    private Customer customer;
    private Boolean purchaseStatus;
    private double purchaseTotal;
    private int cardUsedIndex;

    /**
     * Purchase Constructor with Customer, Purchase Status, Purchase Total, Card used Index as input parameters
     * @param customer
     * @param purchaseStatus
     * @param purchaseTotal
     * @param cardUsedIndex
     */
    public Purchase(Customer customer, Boolean purchaseStatus, double purchaseTotal, int cardUsedIndex){
        this.customer = customer;
        this.purchaseStatus = purchaseStatus;
        this.purchaseTotal = purchaseTotal;
        this.cardUsedIndex = cardUsedIndex;
    }

    /**
     * Default Purchase Constructor
     */
    public Purchase(){
        this.customer = null;
        this.purchaseStatus = false;
        this.purchaseTotal = -1;
        this.cardUsedIndex = -1;
    }

    //GETTER METHODS

    /**
     * Access method for Purchase Customer. Returns Customer
     * @return customer
     */
    public Customer getCustomer(){
        return customer;
    }

    /**
     * Access method for Purchase purchaseStatus. Returns Boolean
     * @return purchaseStatus
     */
    public boolean getPurchaseStatus(){
        return purchaseStatus;
    }

    /**
     * Access method for Purchase purchaseTotal. Returns Double
     * @return purchaseTotal
     */
    public double getPurchaseTotal() {
        return purchaseTotal;
    }

    /**
     * Access method for Purchase cardUserIndex. Returns int
     * @return cardUsedIndex
     */
    public int getCardUsedIndex(){
        return cardUsedIndex;
    }

    //SET METHODS

    /**
     * Modifier Method for Purchase Customer. Sets customer to parameter.
     * @param customer
     */
    public void setCustomer(Customer customer){
        this.customer = customer;
    }
}

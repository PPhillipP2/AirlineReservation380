package debuggerenjoyers.airlinereservation;

public class Purchase {
    private Customer customer;
    private Boolean purchaseStatus;
    private double purchaseTotal;
    private int cardUsedIndex;

    public Purchase(Customer customer, Boolean purchaseStatus, double purchaseTotal, int cardUsedIndex){
        this.customer = customer;
        this.purchaseStatus = purchaseStatus;
        this.purchaseTotal = purchaseTotal;
        this.cardUsedIndex = cardUsedIndex;
    }

    public Purchase(){
        this.customer = null;
        this.purchaseStatus = null;
        this.purchaseTotal = -1;
        this.cardUsedIndex = -1;
    }

    //GETTER METHODS
    public Customer getCustomer(){
        return customer;
    }
    public boolean getPurchaseStatus(){
        return purchaseStatus;
    }
    public double getPurchaseTotal() {
        return purchaseTotal;
    }
    public int getCardUsedIndex(){
        return cardUsedIndex;
    }
}

package debuggerenjoyers.airlinereservation;

public class Purchase {
    private Customer customer;
    private boolean purchaseStatus;
    private double purchaseTotal;

    public Purchase(Customer customer, boolean purchaseStatus, double purchaseTotal){
        this.customer = customer;
        this.purchaseStatus = purchaseStatus;
        this.purchaseTotal = purchaseTotal;
    }

    public Card[] getCustomerCards(){
        return customer.getCreditCards();
    }

    public boolean getPurchaseStatus(){
        return purchaseStatus;
    }

    public double getPurchaseTotal() {
        return purchaseTotal;
    }
}

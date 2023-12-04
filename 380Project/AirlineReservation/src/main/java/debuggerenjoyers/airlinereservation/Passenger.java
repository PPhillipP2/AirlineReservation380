/**
 * Passenger.java
 * November 21, 2023
 * @author Diego Hernandez
 *
 * The passenger class is designed to represent a Passenger. Has 4 fields to represent Firstname, Lastname, DOB, and bags.
 */
package debuggerenjoyers.airlinereservation;

public class Passenger {
    private String firstName;
    private String lastName;
    private String DOB;
    private Integer bags;

    /**
     * Passenger Constructor with Firstname, Lastname, DOB, and bags as input parameters.
     * @param firstName
     * @param lastName
     * @param DOB
     * @param bags
     */
    public Passenger(String firstName, String lastName, String DOB, int bags){
        this.firstName = firstName;
        this.lastName = lastName;
        this.DOB = DOB;
        this.bags = bags;
    }

    /**
     * Default Passenger Constructor
     */
    public Passenger(){
        this.firstName = null;
        this.lastName = null;
        this.DOB = null;
        this.bags = -1;
    }

    /**
     * Access method for Passenger Firstname. Returns String.
     * @return firstName
     */
    public String getFirstName(){
        return this.firstName;
    }

    /**
     * Access method for Passenger Lastname. Returns String.
     * @return lastName
     */
    public String getLastName(){
        return this.lastName;
    }

    /**
     * Access method for Passenger DateOfBirth(DOB). Returns String.
     * @return DOB
     */
    public String getDOB(){
        return this.DOB;
    }

    /**
     * Access method for Passenger bags. Returns Integer.
     * @return bags
     */
    public Integer getBags() {
        return bags;
    }

    /**
     * Modifier Method for Passenger firstName. Sets firstName to parameter.
     * @param firstName
     */
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    /**
     * Modifier Method for Passenger lastName. Sets lastName to parameter.
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Modifier Method for Passenger bags. Sets bags to parameter.
     * @param bags
     */
    public void setBags(int bags) {
        this.bags = bags;
    }

    /**
     * Modifier Method for Passenger DOB. Sets DOB to parameter.
     * @param DOB
     */
    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    /**
     * This method checks Passenger to ensure that no field in Passenger is null. Returns null if one field is null.
     * @return Boolean
     */
    public Boolean checkPassenger() {
        if (firstName == null || lastName == null || DOB == null) {
            return false;
        } else {
            return bags != null && bags >= 0; // Consider zero as an invalid value
        }
    }

}

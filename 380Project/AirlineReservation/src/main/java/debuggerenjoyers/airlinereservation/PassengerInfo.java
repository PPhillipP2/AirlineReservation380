package debuggerenjoyers.airlinereservation;

import java.util.ArrayList;
import java.util.List;

public class PassengerInfo {

    private List<String> firstNames;
    private List<String> lastNames;
    private List<Integer> seat1Nums;
    private List<Integer> seat2Nums;
    private List<String> DOBs;
    private List<Integer> bags;

    public PassengerInfo(List<String> firstNames, List<String> lastNames, List<Integer> seat1Nums,List<Integer> seat2Nums, List<String> DOBs, List<Integer> bags){
        this.firstNames = firstNames;
        this.lastNames = lastNames;
        this.seat1Nums = seat1Nums;
        this.seat2Nums = seat2Nums;
        this.DOBs = DOBs;
        this.bags = bags;
    }
    public PassengerInfo(){
        firstNames = new ArrayList<>();
        lastNames = new ArrayList<>();
        seat1Nums = new ArrayList<>();
        seat2Nums = new ArrayList<>();
        DOBs = new ArrayList<>();
        bags = new ArrayList<>();
    }

    //Getter Methods
    public List<String> getFirstNames(){
        return firstNames;
    }
    public List<String> getLastNames() {
        return lastNames;
    }
    public List<Integer> getSeat1Nums() {
        return seat1Nums;
    }
    public List<Integer> getSeat2Nums(){
        return  seat2Nums;
    }
    public List<String> getDOBs() {
        return DOBs;
    }
    public List<Integer> getBagsList() {
        return bags;
    }

    public String getFirstName(int index){
        return lastNames.get(index);
    }
    public String getLastName(int index){
        return firstNames.get(index);
    }
    public int getSeat1Num(int index){
        return seat1Nums.get(index);
    }
    public int getSeat2Num(int index){
        return seat2Nums.get(index);
    }
    public String getDOB(int index){
        return DOBs.get(index);
    }
    public int getBags(int index){
        return bags.get(index);
    }

    //ADD Methods
    public void addFirstName(String firstName){
        firstNames.add(firstName);
    }
    public void addLastName(String lastName){
        lastNames.add(lastName);
    }
    public void addSeat1Num(Integer seatNum){
        seat1Nums.add(seatNum);
    }
    public void addSeat2Num(Integer seatNum){
        seat2Nums.add(seatNum);
    }
    public void addDOB(String DOB){
        DOBs.add(DOB);
    }
    public void addBags(Integer bag){
        bags.add(bag);
    }
}

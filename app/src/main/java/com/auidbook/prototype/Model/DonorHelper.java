package com.auidbook.prototype.Model;

import com.auidbook.prototype.Model.Fields.Address;
import com.auidbook.prototype.Model.Fields.User;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by mgundappan on 18-05-2016.
 */
public class DonorHelper {

    private static Donor donor;
    //private static DonorHelper donorHelper;
    private ArrayList<Donor> donorList;
    private ArrayList<User> userList;
    private ArrayList<BloodRequest> bloodRequestList;

    public DonorHelper() {

        //bloodRequestList = setAllBloodRequest();
        donorList = setAllDonor();
        userList = getAllUser();

    }

    public ArrayList<BloodRequest> getAllBloodRequest() {
        return bloodRequestList;
    }




    /*private DonorHelper() {
        donorList = getAllDonor();
        userList = getAllUser();
    }

    public static DonorHelper newInstance(){
        if(donorHelper == null) {
            donorHelper = new DonorHelper();
        }
        return donorHelper;
    }*/

    public ArrayList<Donor> setAllDonor(){

        ArrayList<Donor> donorList = new ArrayList<Donor>();

        Address address1 =  new Address("Home","No,28, Kattabomman Street","Gandhiji Nagar","Chennai","Kanchipuram","TamilNadu","India","600044",null,null,"private");
        Address address2 =  new Address("Home","No,28, Kattabomman Street","Gandhiji Nagar","Chennai","Kanchipuram","TamilNadu","India","600044",null,null,"private");

        ArrayList<Address> addressList = new ArrayList<Address>();

        addressList.add(address1);
        addressList.add(address2);

        ArrayList<String> phoneList = new ArrayList<String>();

        phoneList.add("9566824544");
        phoneList.add("9941762630");

        BloodRequest requestBlood1 = new BloodRequest("Req001","Mohan","Male","A+ve",4.5,address1,"Accident",phoneList,new Date(),new ArrayList<Donor>(),"Pending","Donor001");
        BloodRequest requestBlood2 = new BloodRequest("Req002","Vignesh","Male","O+ve",4.5,address2,"Accident",phoneList,new Date(),new ArrayList<Donor>(),"Approved","Donor001");
        BloodRequest requestBlood3 = new BloodRequest("Req003","Raja","Male","A+ve",4.5,address1,"Accident",phoneList,new Date(),new ArrayList<Donor>(),"Pending","Donor001");
        BloodRequest requestBlood4 = new BloodRequest("Req004","Prakash","Male","O+ve",4.5,address2,"Accident",phoneList,new Date(),new ArrayList<Donor>(),"Approved","Donor001");

        ArrayList<BloodRequest> listRequest = new ArrayList<BloodRequest>();
        listRequest.add(requestBlood1);
        listRequest.add(requestBlood2);
        listRequest.add(requestBlood3);
        listRequest.add(requestBlood4);

        bloodRequestList  = listRequest;

        Donor donor1 = new Donor("BLD01","Aravinth","Male",22,new Date(),"O-ve","Organizer",addressList,"8939096634",new byte[10],listRequest,listRequest,false);
        Donor donor2 = new Donor("BLD02","Prakash vemuri","Male",22,new Date(),"O+ve","Member",addressList,"8801196441",new byte[10],listRequest,listRequest,false);
        Donor donor3 = new Donor("BLD03","Mani Maran","Male",22,new Date(),"O+ve","Member",addressList,"9092357837",new byte[10],listRequest,listRequest,false);
        Donor donor4 = new Donor("BLD04","Vignesh Mayilappan","Male",22,new Date(),"A+ve","Organizer",addressList,"8122829693",new byte[10],listRequest,listRequest,false);
        Donor donor5 = new Donor("BLD05","Mohankumar Krishnamoorthy","Male",22,new Date(),"B+ve","Member",addressList,"8675851237",new byte[10],listRequest,listRequest,false);
        Donor donor6 = new Donor("BLD06","Nandha","Male",22,new Date(),"A+ve","Member",addressList,"9042481230",new byte[10],listRequest,listRequest,false);
        Donor donor7 = new Donor("BLD07","Rawoof","Male",22,new Date(),"B+ve","Admin",addressList,"9789996960",new byte[10],listRequest,listRequest,false);
        Donor donor8 = new Donor("BLD08","Vignesh TN","Male",22,new Date(),"O+ve","Member",addressList,"9003713371",new byte[10],listRequest,listRequest,false);

        if(donorList.size()<=0) {
            donorList.add(donor1);
            donorList.add(donor2);
            donorList.add(donor3);
            donorList.add(donor4);
            donorList.add(donor5);
            donorList.add(donor6);
            donorList.add(donor7);
            donorList.add(donor8);
        }
        return donorList;
    }
    public ArrayList<Donor> getAllDonor(){
        return donorList;
    }

    public void setDonor(Donor donor){

        DonorHelper.donor  = donor;
    }

    public Donor getDonor(){
        if(DonorHelper.donor == null) {
            DonorHelper.donor = getAllDonor().get(0);
        }
        return DonorHelper.donor;
    }

    public ArrayList<BloodRequest> getPendingBloodRequestList(){

        ArrayList<BloodRequest> pendingRequest = new ArrayList<BloodRequest>();

        for (BloodRequest request:bloodRequestList) {

            if(request.getRequestStatus().equals("Pending")){

                pendingRequest.add(request);
            }
        }
        return  pendingRequest;
    }

    public ArrayList<BloodRequest> getApprovedBloodRequestList(){

        ArrayList<BloodRequest> pendingRequest = new ArrayList<BloodRequest>();

        for (BloodRequest request:bloodRequestList) {

            if(request.getRequestStatus().equals("Approved")){

                pendingRequest.add(request);
            }
        }
        return  pendingRequest;
    }


    public ArrayList<User> getAllUser(){

        ArrayList<User> userList = new ArrayList<User>();

        User user1 = new User("8122829693","heaven@123");
        User user2 = new User("9042481230","nandaragav");
        User user3 = new User("8939096634","aravind@123");
        User user4 = new User("9789996960","rawoof@123");
        User user5 = new User("8801196441","prakash@123");
        User user6 = new User("8675851237","mohan@07");
        User user7 = new User("9003713371","vignesh@123");
        User user8 = new User("9092357837","maran@123");

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        userList.add(user5);
        userList.add(user6);
        userList.add(user7);
        userList.add(user8);

        return userList;
    }

    public boolean checkValidUser(String userName, String password){

        boolean isValid = false;

        for (User user :userList) {

            if(user.getUserName().equals(userName) && user.getPassWord().equals(password)){

                isValid= true;
            }
        }
        return isValid;
    }

    public Donor getDonorByUserName(String userName){

        Donor currentUser = null;

        for (Donor donor:donorList) {

            if(donor.getMobileNumber().equals(userName)){

                currentUser = donor;
            }
        }
        return currentUser;
    }

}
package com.auidbook.prototype.Model;

import android.content.Context;
import android.util.Log;

import com.auidbook.prototype.Model.Fields.Address;
import com.auidbook.prototype.Model.Fields.User;
import com.auidbook.prototype.enums.RequestState;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mgundappan on 18-05-2016.
 */
public class DonorHelper {

    private static Donor donor;
    CRKApp crkApp;
    //private static DonorHelper donorHelper;
    private List<Donor> donorList;
    private List<User> userList;
    private List<BloodRequest> bloodRequestList;
    private Context mContext;

    public DonorHelper() {
        userList = getAllUser();
        donorList = setAllDonor();
    }

    public DonorHelper(Context context) {

        //bloodRequestList = setAllBloodRequest();
        userList = getAllUser();
        donorList = setAllDonor();
        mContext = context;
        crkApp = (CRKApp) mContext;

    }

    public List<BloodRequest> getAllBloodRequest() {
        return bloodRequestList;
    }

    public void setAllBloodRequest(List<BloodRequest> bloodRequestList) {
        Log.i("DonorHelper", "setAllBloodRequestCalled");
        this.bloodRequestList = bloodRequestList;

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

        Address address1 =  new Address("Home","No.28","Kattabomman Street","Gandhiji Nagar","Chennai","TamilNadu","India","600044",null,null,"private");
        Address address2 =  new Address("Office","No.28/82"," K.P Kovil Street","Saidapet","Chennai","TamilNadu","India","600044",null,null,"private");

        Address address3 =  new Address("Office","No.28","Kattabomman Street","Gandhiji Nagar","Chennai","TamilNadu","India","600044",null,null,"private");
        Address address4 =  new Address("Home","No.28/82"," K.P Kovil Street","Saidapet","Chennai","TamilNadu","India","600044",null,null,"private");

        ArrayList<Address> addressList1 = new ArrayList<Address>();

        addressList1.add(address1);
        addressList1.add(address2);

        ArrayList<Address> addressList2 = new ArrayList<Address>();

        addressList2.add(address3);
        addressList2.add(address4);

        ArrayList<String> phoneList = new ArrayList<String>();

        phoneList.add("9566824544");
        phoneList.add("9941762630");

        BloodRequest requestBlood1 = new BloodRequest("","Req001","Mohan","Male","A+ve",4.5,address1,"Accident",phoneList,new Date(),new ArrayList<Donor>(), RequestState.Pending,"Donor001");
        BloodRequest requestBlood2 = new BloodRequest("","Req002","Vignesh","Male","O+ve",4.5,address1,"Accident",phoneList,new Date(),new ArrayList<Donor>(),RequestState.Approved,"Donor001");
        BloodRequest requestBlood3 = new BloodRequest("","Req003","Raja","Male","A+ve",4.5,address2,"Accident",phoneList,new Date(),new ArrayList<Donor>(),RequestState.Pending,"Donor001");
        BloodRequest requestBlood4 = new BloodRequest("","Req004","Prakash","Male","O+ve",4.5,address2,"Accident",phoneList,new Date(),new ArrayList<Donor>(),RequestState.Approved,"Donor001");

        ArrayList<BloodRequest> listRequest = new ArrayList<BloodRequest>();
        listRequest.add(requestBlood1);
        listRequest.add(requestBlood2);
        listRequest.add(requestBlood3);
        listRequest.add(requestBlood4);

        bloodRequestList  = listRequest;

        Donor donor1 = new Donor("BLD01","Aravinth","Male",22,new Date(),"O-ve","Organizer",addressList1,"8939096634",new byte[10],listRequest,listRequest,false);
        Donor donor2 = new Donor("BLD02","Prakash vemuri","Male",22,new Date(),"O+ve","Member",addressList2,"8801196441",new byte[10],listRequest,listRequest,false);
        Donor donor3 = new Donor("BLD03","Mani Maran","Male",22,new Date(),"O+ve","Member",addressList1,"9092357837",new byte[10],listRequest,listRequest,false);
        Donor donor4 = new Donor("BLD04","Vignesh Mayilappan","Male",22,new Date(),"A+ve","Organizer",addressList2,"8122829693",new byte[10],listRequest,listRequest,false);
        Donor donor5 = new Donor("BLD05","Mohankumar Krishnamoorthy","Male",22,new Date(),"B+ve","Member",addressList1,"8675851237",new byte[10],listRequest,listRequest,false);
        Donor donor6 = new Donor("BLD06","Nandha","Male",22,new Date(),"A+ve","Member",addressList2,"9042481230",new byte[10],listRequest,listRequest,false);
        Donor donor7 = new Donor("BLD07","Rawoof","Male",22,new Date(),"B+ve","Admin",addressList1,"9789996960",new byte[10],listRequest,listRequest,false);
        Donor donor8 = new Donor("BLD08","Vignesh TN","Male",22,new Date(),"O+ve","Member",addressList2,"9003713371",new byte[10],listRequest,listRequest,false);

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
    public List<Donor> getAllDonor(){
        return donorList;
    }

    public Donor getDonor(){
        if(DonorHelper.donor == null) {
            DonorHelper.donor = getAllDonor().get(0);
        }
        return DonorHelper.donor;
    }

    public void setDonor(Donor donor){

        DonorHelper.donor  = donor;
    }

    public List<BloodRequest> getPendingBloodRequestList(){

        List<BloodRequest> pendingRequest = new ArrayList<BloodRequest>();

        for (BloodRequest request:bloodRequestList) {

            if(request.getRequestStatus().ordinal() == RequestState.Pending.ordinal()){

                pendingRequest.add(request);
            }
        }
        return  pendingRequest;
    }

    public List<BloodRequest> getApprovedBloodRequestList(){

        List<BloodRequest> pendingRequest = new ArrayList<BloodRequest>();

        for (BloodRequest request:bloodRequestList) {

            if(request.getRequestStatus().ordinal() == RequestState.Approved.ordinal()){

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

    public void updateBloodRequest(BloodRequest bloodRequest){

        for (BloodRequest blood:
             bloodRequestList) {

            if(blood.getRequestId().equals(bloodRequest.getRequestId())){

                blood.setPatientName(bloodRequest.getPatientName());
                blood.setBloodGroup(bloodRequest.getBloodGroup());
                blood.setDateOfDonation(bloodRequest.getDateOfDonation());
                blood.setGender(bloodRequest.getGender());
                blood.setNoOfUnitsRrequired(bloodRequest.getNoOfUnitsRrequired());
                blood.setDonateLocation(bloodRequest.getDonateLocation());
                blood.setReason(bloodRequest.getReason());
                blood.setContactNumbers(bloodRequest.getContactNumbers());
                blood.setDonorResponsed(bloodRequest.getDonorResponsed());
            }

        }
    }

}

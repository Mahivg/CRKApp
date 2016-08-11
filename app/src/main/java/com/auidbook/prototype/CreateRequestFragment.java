package com.auidbook.prototype;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.auidbook.prototype.Model.BloodRequest;
import com.auidbook.prototype.Model.CRKApp;
import com.auidbook.prototype.Model.DonorHelper;
import com.auidbook.prototype.Model.Fields.Address;
import com.auidbook.prototype.UIModel.DatePickerFragment;
import com.auidbook.prototype.enums.RequestStatus;
import com.auidbook.prototype.listener.ICommunicator;
//import com.wefika.horizontalpicker.HorizontalPicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Rawoof on 5/8/2016.
 */
public class CreateRequestFragment extends Fragment implements View.OnClickListener {

    private static final int REQUEST_DATE = 0;
    private static BloodRequest bloodRequestForEdit;
    private View v;
    private EditText edtPatientName;
    private RadioGroup rgpGender;
    private EditText edtBloodGroup;
    private EditText edtDonationDate;
    private EditText edtMobileNumber;
    private EditText edtAltMobileNumber;
    private EditText edtReason;
    private EditText edtAddressLine1;
    private EditText edtAddressLine2;
    private EditText edtAddressLine3;
    private Button btnSubmit;
   // private HorizontalPicker hp_units;
    private NumberPicker np_units;
    private CheckBox mCheckHalfUnits;
    private CRKApp crkApp;
    private DonorHelper donorHelper;
    private ICommunicator communicator;
    private Date mDate;
    private double mUnitsRequired;

    public CreateRequestFragment(){}

    public static CreateRequestFragment newInstance(BloodRequest bloodRequest){

        bloodRequestForEdit = bloodRequest;

        return new CreateRequestFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        crkApp = (CRKApp) getActivity().getApplicationContext();

        donorHelper = crkApp.getDonorHelper();

        communicator = (ICommunicator) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_create_request, container, false);
        initViews();
        if(bloodRequestForEdit!= null){
            populateBloodData(bloodRequestForEdit);
        }
        return v;
    }



    private void initViews() {

        edtPatientName = (EditText) v.findViewById(R.id.edt_patient_name);
        rgpGender = (RadioGroup) v.findViewById(R.id.rgpGender);
        edtBloodGroup = (EditText) v.findViewById(R.id.edt_blood_group);

        //hp_units = (HorizontalPicker) v.findViewById(R.id.hp_units);
        np_units = (NumberPicker) v.findViewById(R.id.numberPicker);
        mCheckHalfUnits = (CheckBox) v.findViewById(R.id.chkHalfunits);
        //txtseekunits = (TextView) v.findViewById(R.id.txtseekunits);
        edtDonationDate = (EditText) v.findViewById(R.id.edt_date_of_donation);
        edtMobileNumber = (EditText) v.findViewById(R.id.edt_mobile_number);
        edtAltMobileNumber = (EditText) v.findViewById(R.id.edt_alt_mobile_number);
        edtReason = (EditText) v.findViewById(R.id.edt_Reason);
        edtAddressLine1 = (EditText) v.findViewById(R.id.edt_addressline1);
        edtAddressLine2 = (EditText) v.findViewById(R.id.edt_addressline2);
        edtAddressLine3 = (EditText) v.findViewById(R.id.edt_addressline3);
        btnSubmit = (Button) v.findViewById(R.id.btnSubmitRequest);
        btnSubmit.setOnClickListener(this);
        np_units.setMinValue(1);
        np_units.setMaxValue(100);
        np_units.setValue(2);

        np_units.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mUnitsRequired = newVal;
            }
        });

        mCheckHalfUnits.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    mUnitsRequired = mUnitsRequired+0.5;
                }
            }
        });

        edtDonationDate.setOnClickListener(this);    }

    // TODO: Rename method, update argument and hook method into UI event


    @Override
    public void onDetach() {
        super.onDetach();
        crkApp.setDonorHelper(donorHelper);
    }


    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.btnSubmitRequest){

            submitRequest();
        }
        else if(v.getId() == R.id.edt_date_of_donation){

            setDate();
        }

    }

    private void setDate() {

        DialogFragment datePickerFragment = new DatePickerFragment();

        datePickerFragment.setTargetFragment(CreateRequestFragment.this, REQUEST_DATE);
        datePickerFragment.show(getFragmentManager(), "Date Picker");
    }

    private void submitRequest() {

       /* String patientName;
        String gender;
        String bloodGroup;
        String mobileNumber;
        String altMobNumber;
        String addressLine1;
        String addressLine2;
        String addressLine3;
        String reason;*/

        String reqId;
        String patientName = edtPatientName.getText().toString();
        String gender = getGenderFromRadioButton();
        String bloodGroup = edtBloodGroup.getText().toString();
        mUnitsRequired = np_units.getValue();
        if(mCheckHalfUnits.isChecked()){
            mUnitsRequired = mUnitsRequired+ 0.5;
        }
        String mobileNumber = edtMobileNumber.getText().toString();
        String altMobNumber = edtAltMobileNumber.getText().toString();
        String reason = edtReason.getText().toString();
        String addressLine1 = edtAddressLine1.getText().toString();
        String addressLine2 = edtAddressLine2.getText().toString();
        String addressLine3 = edtAddressLine3.getText().toString();
        ArrayList<String> mobileArray = new ArrayList<String>();
        Address hospitalAddress;
        BloodRequest request;
        mobileArray.add(mobileNumber);
        mobileArray.add(altMobNumber);
        hospitalAddress = new Address("",addressLine1,addressLine2,addressLine3,"","","","","","","");
//        request = new BloodRequest("","",patientName,gender,bloodGroup,mUnitsRequired,hospitalAddress,reason,mobileArray,mDate,null, RequestStatus.Pending,crkApp.getDonor().getDonorID());

        if(bloodRequestForEdit != null){

      /*      patientName = bloodRequestForEdit.getPatientName();
            gender = getGenderFromRadioButton();
            bloodGroup = bloodRequestForEdit.getBloodGroup();
            mobileNumber = bloodRequestForEdit.getContactNumbers().get(0);
            altMobNumber = bloodRequestForEdit.getContactNumbers().get(1);
            reason = bloodRequestForEdit.getReason();
            addressLine1 = bloodRequestForEdit.getDonateLocation().getAddressLine1();
            addressLine2 = bloodRequestForEdit.getDonateLocation().getAddressLine2();
            addressLine3 = bloodRequestForEdit.getDonateLocation().getAddressLine3();
            mobileArray.add(mobileNumber);
            mobileArray.add(altMobNumber);*/

            reqId = bloodRequestForEdit.getRequestId();
            if(mDate == null){
                mDate = bloodRequestForEdit.getDonationDate();
            }
//            request = new BloodRequest(reqId,patientName,null, age,hospitalAddress,reason,mobileArray,mDate, RequestStatus.Pending,crkApp.getDonor().getDonorID());
//
//            donorHelper.updateBloodRequest(request);
            bloodRequestForEdit = null;

            communicator.changeFragment(new ResponseFragment());

            Toast.makeText(getContext(),"Request Edited",Toast.LENGTH_SHORT).show();
        }
        else{
           /* patientName = edtPatientName.getText().toString();
            gender = getGenderFromRadioButton();
            bloodGroup = edtBloodGroup.getText().toString();
            mobileNumber = edtMobileNumber.getText().toString();
            altMobNumber = edtAltMobileNumber.getText().toString();
            reason = edtReason.getText().toString();
            addressLine1 = edtAddressLine1.getText().toString();
            addressLine2 = edtAddressLine2.getText().toString();
            addressLine3 = edtAddressLine3.getText().toString();
            mobileArray.add(mobileNumber);
            mobileArray.add(altMobNumber);

            hospitalAddress = new Address("",addressLine1,addressLine2,addressLine3,"","","","","","","");*/
            reqId = "Req" + Math.round(Math.random()*1000);

//            request = new BloodRequest("",reqId,patientName,gender,bloodGroup,mUnitsRequired,hospitalAddress,reason,mobileArray,mDate,null, RequestStatus.Pending,crkApp.getDonor().getDonorID());

//            donorHelper.getAllBloodRequest().add(request);

            Toast.makeText(getContext(), "Request Submitted To Add", Toast.LENGTH_SHORT).show();
        }

    }

    public String getGenderFromRadioButton() {

        RadioButton rbnGender = (RadioButton) v.findViewById(rgpGender.getCheckedRadioButtonId());

        return rbnGender.getText().toString();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_DATE) {
                Date date = (Date) data
                        .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
                mDate = date;
                setDateText(date);
            }
        }
    }

    private void setDateText(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String d = day+"-"+month+"-"+year;
        edtDonationDate.setText(d);

    }
    private void populateBloodData(BloodRequest bloodRequestForEdit) {

        edtPatientName.setText(bloodRequestForEdit.getPatientName());
//        String gender = bloodRequestForEdit.getGender();
//        setRadioButton(gender);
//        double val = bloodRequestForEdit.getNoOfUnitsRrequired()- Math.floor(bloodRequestForEdit.getNoOfUnitsRrequired());
//        if(val != 0){
//            Double intVal = Math.floor(bloodRequestForEdit.getNoOfUnitsRrequired());
//                    np_units.setValue(intVal.intValue());
//                    mCheckHalfUnits.setChecked(true);
//        }
//        else{
//            Double intVal = Math.floor(bloodRequestForEdit.getNoOfUnitsRrequired());
//            np_units.setValue(intVal.intValue());
//        }
//        setDateText(bloodRequestForEdit.getDateOfDonation());
//        edtBloodGroup.setText(bloodRequestForEdit.getBloodGroup());
//        edtMobileNumber.setText(bloodRequestForEdit.getContactNumbers().get(0));
//        edtAltMobileNumber.setText(bloodRequestForEdit.getContactNumbers().get(1));
//        edtAddressLine1.setText(bloodRequestForEdit.getDonateLocation().getAddressLine1());
//        edtAddressLine2.setText(bloodRequestForEdit.getDonateLocation().getAddressLine2());
//        edtAddressLine3.setText(bloodRequestForEdit.getDonateLocation().getAddressLine3());
//        edtReason.setText(bloodRequestForEdit.getReason());

    }

    private void setRadioButton(String gender) {

        RadioButton rbnMale = (RadioButton) v.findViewById(R.id.rbnMale);
        RadioButton rbnFemale = (RadioButton) v.findViewById(R.id.rbnFemale);

        if(gender.equals("Male")){

            rbnMale.setChecked(true);
        }
        else {
            rbnFemale.setChecked(true);
        }

    }
}
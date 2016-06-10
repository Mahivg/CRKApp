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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;

import com.auidbook.prototype.Model.BloodRequest;
import com.auidbook.prototype.Model.CRKApp;
import com.auidbook.prototype.Model.DonorHelper;
import com.auidbook.prototype.Model.Fields.Address;
import com.auidbook.prototype.UIModel.DatePickerFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created by Rawoof on 5/8/2016.
 */
public class CreateRequestFragment extends Fragment implements View.OnClickListener {

    private View v;
    private EditText edtPatientName;
    private RadioGroup rgpGender;
    private EditText edtBloodGroup;
    private SeekBar skbUnitsRequired;
    private EditText edtDonationDate;
    private EditText edtMobileNumber;
    private EditText edtAltMobileNumber;
    private EditText edtAddressLine1;
    private EditText edtAddressLine2;
    private EditText edtAddressLine3;
    private Button btnSubmit;

    private CRKApp crkApp;
    private DonorHelper donorHelper;
    private Date mDate;
    private static final int REQUEST_DATE = 0;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_create_request, container, false);
        initViews();

        btnSubmit.setOnClickListener(this);


        return v;
    }

    private void initViews() {

        crkApp = (CRKApp) getActivity().getApplicationContext();

        donorHelper = crkApp.getDonorHelper();

        edtPatientName = (EditText) v.findViewById(R.id.edt_patient_name);
        rgpGender = (RadioGroup) v.findViewById(R.id.rgpGender);
        edtBloodGroup = (EditText) v.findViewById(R.id.edt_blood_group);
        skbUnitsRequired = (SeekBar) v.findViewById(R.id.skbUnitsRequired);
        edtDonationDate = (EditText) v.findViewById(R.id.edt_date_of_donation);
        edtMobileNumber = (EditText) v.findViewById(R.id.edt_mobile_number);
        edtAltMobileNumber = (EditText) v.findViewById(R.id.edt_alt_mobile_number);
        edtAddressLine1 = (EditText) v.findViewById(R.id.edt_addressline1);
        edtAddressLine2 = (EditText) v.findViewById(R.id.edt_addressline2);
        edtAddressLine3 = (EditText) v.findViewById(R.id.edt_addressline3);
        btnSubmit = (Button) v.findViewById(R.id.btnSubmitRequest);

        skbUnitsRequired.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                seekBar.setProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                Toast.makeText(getContext(),seekBar.getProgress()*0.5+"",Toast.LENGTH_SHORT).show();
            }
        });

        edtDonationDate.setOnClickListener(this);

    }

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

        String patientName = edtPatientName.getText().toString();
        String gender = getGenderFromRadioButton();
        String bloodGroup = edtBloodGroup.getText().toString();
        double unitsRequired = skbUnitsRequired.getProgress()*0.5;
        String mobileNumber = edtMobileNumber.getText().toString();
        String altMobNumber = edtAltMobileNumber.getText().toString();
        String addressLine1 = edtAddressLine1.getText().toString();
        String addressLine2 = edtAddressLine2.getText().toString();
        String addressLine3 = edtAddressLine3.getText().toString();

        ArrayList<String> mobileArray = new ArrayList<String>();
        mobileArray.add(mobileNumber);
        mobileArray.add(altMobNumber);

        Address hospitalAddress = new Address("",addressLine1,addressLine2,addressLine3,"","","","","","","");


        BloodRequest request = new BloodRequest("",patientName,gender,bloodGroup,unitsRequired,hospitalAddress,"Accident",mobileArray,mDate,null,"Pending",crkApp.getDonor().getDonorID());

        donorHelper.getAllBloodRequest().add(request);

        Toast.makeText(getContext(),"Request Submitted",Toast.LENGTH_SHORT).show();

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
}
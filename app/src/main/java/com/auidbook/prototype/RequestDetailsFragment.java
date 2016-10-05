package com.auidbook.prototype;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.auidbook.prototype.Model.BloodGroup;
import com.auidbook.prototype.Model.BloodRequest;
import com.auidbook.prototype.Model.EmergencyType;
import com.auidbook.prototype.UIModel.DatePickerFragment;
import com.auidbook.prototype.databinding.FragmentRequestDetailsBinding;
import com.auidbook.prototype.enums.RequestStatus;
import com.auidbook.prototype.util.RetrofitUtils;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.DecimalMin;
import com.mobsandgeeks.saripaar.annotation.Future;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.util.Arrays.asList;

public class RequestDetailsFragment extends Fragment implements Validator.ValidationListener {

    private static final int REQUEST_DATE = 0;
    private BloodRequest bloodRequest;
    private MaterialSpinner genderSpinner;
    private MaterialSpinner bloodGroupSpinner;
    @NotEmpty(trim = true, messageResId = R.string.error_patient_name, sequence = 0)
    private EditText edtPatientName;
    @DecimalMin(value = 0.5, messageResId = R.string.error_number_of_units, sequence = 1)
    private EditText np_units;
    @NotEmpty(trim = true, messageResId = R.string.error_donation_date, sequence = 2)
    @Future(messageResId = R.string.error_donation_date, dateFormat = Constants.DATE_DISPLAY_FORMAT, sequence = 3)
    private EditText edtDonationDate;
    @NotEmpty(trim = true, messageResId = R.string.error_mobile_number, sequence = 4)
    private EditText edtMobileNumber;
    @NotEmpty(trim = true, messageResId = R.string.error_alternate_mobile_number, sequence = 5)
    private EditText edtAltMobileNumber;
    private EditText edtReason;
    @NotEmpty(trim = true, messageResId = R.string.error_hospital_address, sequence = 6)
    private EditText edtAddressLine1;
    private EditText edtAddressLine2;
    private EditText edtAddressLine3;
    private Date donationDate;
    private Validator validator;
    private boolean isReadOnly;
    private FragmentRequestDetailsBinding fragmentRequestDetailsBinding;
    private final List<String> genders = asList("Male", "Female");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bloodRequest = getArguments().getParcelable(Constants.BLOOD_REQUEST_KEY);
        isReadOnly = getArguments().getBoolean(Constants.IS_VIEW_REQUEST_DETAILS, false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentRequestDetailsBinding = FragmentRequestDetailsBinding.inflate(inflater);
        initViews();
        validator = new Validator(this);
        validator.setValidationListener(this);
        fragmentRequestDetailsBinding.setBloodRequest(bloodRequest);
        fragmentRequestDetailsBinding.setIsReadOnly(isReadOnly);
        fragmentRequestDetailsBinding.setEventHandler(this);
        fragmentRequestDetailsBinding.setIsLoading(false);
        fragmentRequestDetailsBinding.executePendingBindings();
        return fragmentRequestDetailsBinding.getRoot();
    }

    private void initViews() {
        List<String> bloodGroups = BloodGroup.all();
        View v = fragmentRequestDetailsBinding.getRoot();
        edtPatientName = (EditText) v.findViewById(R.id.edt_patient_name);
        genderSpinner = (MaterialSpinner) v.findViewById(R.id.gender_spinner);
        genderSpinner.setAdapter(new ArrayAdapter<>(getContext(), R.layout.spinner_item, genders));
        genderSpinner.setSelection(bloodRequest != null ? genders.indexOf(bloodRequest.getGender()) + 1 : 0);
        bloodGroupSpinner = (MaterialSpinner) v.findViewById(R.id.blood_group_spinner);
        bloodGroupSpinner.setAdapter(new ArrayAdapter<>(getContext(), R.layout.spinner_item, bloodGroups));
        bloodGroupSpinner.setSelection(bloodRequest != null ? bloodGroups.indexOf(bloodRequest.getBloodGroup().toString()) + 1 : 0);
        np_units = (EditText) v.findViewById(R.id.numberOfUnits);
        edtMobileNumber = (EditText) v.findViewById(R.id.edt_mobile_number);
        edtAltMobileNumber = (EditText) v.findViewById(R.id.edt_alt_mobile_number);
        edtDonationDate = (EditText) v.findViewById(R.id.edt_date_of_donation);
        edtReason = (EditText) v.findViewById(R.id.edt_Reason);
        edtAddressLine1 = (EditText) v.findViewById(R.id.edt_addressline1);
        edtAddressLine2 = (EditText) v.findViewById(R.id.edt_addressline2);
        edtAddressLine3 = (EditText) v.findViewById(R.id.edt_addressline3);
    }

    public void donationDateClicked(View v) {
        setDate();
    }

    public void submitButtonClicked(View v) {
        validator.validate();
    }

    public void donateButtonClicked(final View view) {
        //todo: get current user id
        fragmentRequestDetailsBinding.setIsLoading(true);
        fragmentRequestDetailsBinding.executePendingBindings();
        RetrofitUtils.getBloodDonationApi()
                .donate(bloodRequest.getRequestId(), "9838383838")
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            goToMainActivity(Constants.ACCEPT_DONATION_ACTION);
                        } else {
                            fragmentRequestDetailsBinding.setIsLoading(false);
                            Log.e(RequestDetailsFragment.class.getName(), response.message());
                            Toast.makeText(getContext(), "Unable to reach server now. Please try again later", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.e(RequestDetailsFragment.class.getName(), t.getMessage(), t);
                        fragmentRequestDetailsBinding.setIsLoading(false);
                        Toast.makeText(getContext(), "Unable to reach server now. Please try again later", Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void goToMainActivity(String intentAction) {
        Intent intent = new Intent();
        intent.setAction(intentAction);
        intent.setClass(getContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void setDate() {
        DialogFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.setTargetFragment(RequestDetailsFragment.this, REQUEST_DATE);
        datePickerFragment.show(getFragmentManager(), "Date Picker");
    }

    private void submitRequest() {
        String patientName = edtPatientName.getText().toString();
        String gender = genderSpinner.getSelectedItem().toString();
        BloodGroup bloodGroup = BloodGroup.get(bloodGroupSpinner.getSelectedItem().toString());
        double mUnitsRequired = Double.parseDouble(np_units.getText().toString());
        String mobileNumber = edtMobileNumber.getText().toString();
        String altMobNumber = edtAltMobileNumber.getText().toString();
        String reason = edtReason.getText().toString();
        String addressLine1 = edtAddressLine1.getText().toString();
        String addressLine2 = edtAddressLine2.getText().toString();
        String addressLine3 = edtAddressLine3.getText().toString();
        BloodRequest request = new BloodRequest(null, patientName, gender, bloodGroup, 0, EmergencyType.URGENT, donationDate, "", mUnitsRequired, mobileNumber, altMobNumber, reason, "", "", addressLine1, addressLine2, addressLine3, null, null, RequestStatus.Pending);
        fragmentRequestDetailsBinding.setIsLoading(true);
        RetrofitUtils.getBloodDonationApi()
                .placeNewRequest(request)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        fragmentRequestDetailsBinding.setIsLoading(false);
                        if (response.isSuccessful()) {
                            fragmentRequestDetailsBinding.setIsReadOnly(true);
                            goToMainActivity(Constants.LIST_PENDING_REQUESTS);
                            Toast.makeText(getContext(), "Your request has been submitted", Toast.LENGTH_LONG).show();
                        } else {
                            Log.e(RequestDetailsFragment.class.getName(), response.message());
                            Toast.makeText(getContext(), "Unable to reach server now. Please try again later", Toast.LENGTH_LONG).show();
                        }
                        fragmentRequestDetailsBinding.executePendingBindings();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        fragmentRequestDetailsBinding.setIsLoading(false);
                        Log.e(RequestDetailsFragment.class.getName(), t.getMessage(), t);
                        Toast.makeText(getContext(), "Unable to reach server now. Please try again later", Toast.LENGTH_LONG).show();
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_DATE) {
                donationDate = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
                edtDonationDate.setText(new SimpleDateFormat(Constants.DATE_DISPLAY_FORMAT).format(this.donationDate));
            }
        }
    }

    @Override
    public void onValidationSucceeded() {
        if (!genders.contains(genderSpinner.getSelectedItem().toString())) {
            Toast.makeText(getContext(), getContext().getResources().getText(R.string.error_gender), Toast.LENGTH_LONG).show();
            return;
        } else if (!BloodGroup.all().contains(bloodGroupSpinner.getSelectedItem().toString())) {
            Toast.makeText(getContext(), getContext().getResources().getText(R.string.error_blood_group), Toast.LENGTH_LONG).show();
            return;
        }
        submitRequest();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        //todo: sort failed rules based on sequence number and show the error message from the first view
        errors.get(0).getView().requestFocus();
        Toast.makeText(getContext(), errors.get(0).getFailedRules().get(0).getMessage(getContext()), Toast.LENGTH_LONG).show();
    }
}
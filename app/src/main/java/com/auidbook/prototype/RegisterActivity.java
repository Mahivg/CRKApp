package com.auidbook.prototype;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.auidbook.prototype.Model.BloodGroup;
import com.auidbook.prototype.Model.Donor;
import com.auidbook.prototype.util.RetrofitUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import fr.ganfra.materialspinner.MaterialSpinner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, View.OnFocusChangeListener {
    private static String EMAIL_ADDRESS_REGEX = "^.+@.+\\..+$";
    private static String DATE_FORMAT = "dd-MM-yyyy";
    Button signUp;
    Switch donatedBeforeSwitch;
    EditText lastDonationDateInput;

    private DatePickerDialog dateOfBirthPicker;
    private DatePickerDialog lastDonationDatePicker;
    private TextView dateOfBirthInput;
    private View dateOfBirthSection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        signUp = (Button) findViewById(R.id.signUp2);
        donatedBeforeSwitch = (Switch) findViewById(R.id.donated_before_switch);
        lastDonationDateInput = (EditText) findViewById(R.id.last_donation_date);
        dateOfBirthInput = (TextView) findViewById(R.id.new_user_date_of_birth);
        View lastDonationDateSection = findViewById(R.id.last_donation_date_section);
        dateOfBirthSection = findViewById(R.id.date_of_birth_section);
        Calendar calendar = Calendar.getInstance();
        donatedBeforeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                lastDonationDateInput.setEnabled(isChecked);
            }
        });
        dateOfBirthPicker = new DatePickerDialog(this, this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        lastDonationDatePicker = new DatePickerDialog(this, this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dateOfBirthPicker.getDatePicker().setMaxDate(Calendar.getInstance().getTimeInMillis());
        lastDonationDatePicker.getDatePicker().setMaxDate(Calendar.getInstance().getTimeInMillis());
        ArrayAdapter<BloodGroup> bloodGroupArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, BloodGroup.values());
        bloodGroupArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MaterialSpinner bloodGroupSpinner = (MaterialSpinner) findViewById(R.id.new_user_blood_group);
        bloodGroupSpinner.setAdapter(bloodGroupArrayAdapter);
        lastDonationDateInput.setInputType(InputType.TYPE_NULL);
        dateOfBirthInput.setInputType(InputType.TYPE_NULL);
        lastDonationDateSection.setOnFocusChangeListener(this);
        dateOfBirthSection.setOnFocusChangeListener(this);
        lastDonationDateInput.setOnFocusChangeListener(this);
        dateOfBirthInput.setOnFocusChangeListener(this);
        signUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                validateUserDetailsAndRegister();
            }
        });
    }

    private void validateUserDetailsAndRegister() {
        TextView emailInput = (TextView) findViewById(R.id.new_user_email);
        String email = emailInput.getText() != null ? emailInput.getText().toString() : "";
        MaterialSpinner bloodGroupInput = (MaterialSpinner) findViewById(R.id.new_user_blood_group);
        BloodGroup bloodGroup = BloodGroup.get(bloodGroupInput.getSelectedItem().toString());
        TextView firstNameInput = (TextView) findViewById(R.id.new_user_first_name);
        String firstName = firstNameInput.getText() != null ? firstNameInput.getText().toString() : "";
        TextView lastNameInput = (TextView) findViewById(R.id.new_user_last_name);
        String lastName = lastNameInput.getText() != null ? lastNameInput.getText().toString() : "";
        TextView mobileNumberInput = (TextView) findViewById(R.id.new_user_mobile_number);
        String mobileNumber = mobileNumberInput.getText() != null ? mobileNumberInput.getText().toString() : "";
        RadioGroup gender_radio_group = (RadioGroup) findViewById(R.id.new_user_gender_radio_group);
        String gender = gender_radio_group.getCheckedRadioButtonId() == R.id.male_radio_button ? "Male" : "Female";
        TextView homeLocationInput = (TextView) findViewById(R.id.new_user_home_location);
        String homeLocation = homeLocationInput.getText() != null ? homeLocationInput.getText().toString() : "";
        boolean hasDonatedBefore = donatedBeforeSwitch.isChecked();
        String lastDonationDateString = this.lastDonationDateInput.getText().toString();
        String dateOfBirthString = dateOfBirthInput.getText() != null ? dateOfBirthInput.getText().toString() : "";
        Date dateOfBirth = parseDate(dateOfBirthString);
        Date lastDonationDate = parseDate(lastDonationDateString);
        if (bloodGroup == null) {
            Toast.makeText(RegisterActivity.this, "Please select your blood group", Toast.LENGTH_SHORT).show();
            return;
        }
        if (firstName.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Please enter your first name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (lastName.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Please enter your last name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mobileNumber.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Please enter your mobile number", Toast.LENGTH_SHORT).show();
            return;
        }
        if (homeLocation.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Please enter your home location", Toast.LENGTH_SHORT).show();
            return;
        }
        if (email.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Please enter your email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!email.matches(EMAIL_ADDRESS_REGEX)) {
            Toast.makeText(RegisterActivity.this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (dateOfBirth == null) {
            Toast.makeText(RegisterActivity.this, "Please enter your date of birth", Toast.LENGTH_SHORT).show();
            return;
        }
        if (hasDonatedBefore && lastDonationDate == null) {
            Toast.makeText(RegisterActivity.this, "Please enter your last blood donation date", Toast.LENGTH_SHORT).show();
            return;
        }
        RetrofitUtils
                .getUserApi()
                .register(new Donor(firstName, lastName, gender, bloodGroup, email, mobileNumber, homeLocation, 0.0, 0.0, dateOfBirth, lastDonationDate))
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Your account has been created. Please login using that account", Toast.LENGTH_SHORT).show();
                            redirectToLoginActivity();
                        } else {
                            Log.e(RegisterActivity.class.getName(), response.message());
                            Toast.makeText(RegisterActivity.this, "Error: unable to create your account now. Please try later", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.e(RegisterActivity.class.getName(), t.getMessage(), t);
                        Toast.makeText(RegisterActivity.this, "Error: unable to create your account now. Please try later", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private Date parseDate(String dateString) {
        try {
            return new SimpleDateFormat(DATE_FORMAT).parse(dateString);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        redirectToLoginActivity();
    }

    private void redirectToLoginActivity() {
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        finish();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        String dateString = dayOfMonth + "-" + monthOfYear + "-" + year;
        if (view == dateOfBirthPicker.getDatePicker()) {
            dateOfBirthInput.setText(dateString);
            dateOfBirthInput.clearFocus();
        } else if (view == lastDonationDatePicker.getDatePicker()) {
            lastDonationDateInput.setText(dateString);
            lastDonationDateInput.clearFocus();
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        Log.e(RegisterActivity.class.getName(), "Focused " + v.toString());
        if (!hasFocus) return;
        if (v == dateOfBirthSection || v == dateOfBirthInput) {
            dateOfBirthPicker.show();
        } else {
            lastDonationDatePicker.show();
        }
    }
}






package com.auidbook.prototype;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.auidbook.prototype.Model.BloodRequest;
import com.auidbook.prototype.Model.CRKApp;
import com.auidbook.prototype.Model.DataStorage.SessionManager;
import com.auidbook.prototype.Model.Donor;
import com.auidbook.prototype.Model.DonorHelper;
import com.auidbook.prototype.Model.Function;
import com.auidbook.prototype.handler.BloodRequestHandler;
import com.auidbook.prototype.listener.ICommunicator;

import java.util.HashMap;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, ICommunicator {

    private DonorHelper donorHelper;
    private BloodRequestHandler bloodRequestHandler;
    private CRKApp crkApp;
    private SessionManager sessionManager;
    private EditText edtMobileNumber;
    private EditText edtPassword;
    private Button loginButton;
    private TextView signUp;
    private Switch switchKMLI;
    private boolean isLoggedIn;
    private boolean isRemembered;
    private Boolean exit = false;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        crkApp = (CRKApp) getApplicationContext();

        donorHelper = new DonorHelper();

        bloodRequestHandler = new BloodRequestHandler(this, this);

        sessionManager = new SessionManager(getApplicationContext());
        isRemembered = sessionManager.isLoggedIn();

        edtMobileNumber = (EditText) findViewById(R.id.loginMobileNumber);
        edtPassword = (EditText) findViewById(R.id.loginPassword);

        switchKMLI = (Switch) findViewById(R.id.swthLoggedIn);
        switchKMLI.setChecked(isRemembered);
        switchKMLI.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                isLoggedIn = isChecked;
            }
        });
        signUp = (TextView) findViewById(R.id.signup1);
        signUp.setOnClickListener(this);
        loginButton = (Button) findViewById(R.id.email_sign_in_button);
        loginButton.setOnClickListener(this);

        if (isRemembered) {
            HashMap<String, String> userCredentials = sessionManager.getUserDetails();
            edtMobileNumber.setText(userCredentials.get(SessionManager.KEY_USER_NAME));
            edtPassword.setText(userCredentials.get(SessionManager.KEY_PASSWORD));
        }

        Window window = this.getWindow();
        window.setStatusBarColor(Color.WHITE);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == loginButton.getId()) {
            loginUser();
        } else if (view.getId() == signUp.getId()) {
            Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(i);
            setContentView(R.layout.activity_register);
        }
    }

    private void loginUser() {

        final String userName = edtMobileNumber.getText().toString();
        final String password = edtPassword.getText().toString();
        if (userName.isEmpty()) {
            Toast.makeText(this, "Please Enter UserName", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.isEmpty()) {
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }
        donorHelper.checkValidUser(userName, password, new Function<Donor>() {
            @Override
            public void apply(Donor arg) {
                if (arg != null) {
                    sessionManager.createLoginSession(userName, password);
                    crkApp.setDonor(donorHelper.getDonorByUserName(userName));
                    crkApp.setDonorHelper(donorHelper);
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid UserName Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        if (exit) {
            //sessionManager.logoutUser();
            finishAffinity(); // finish activity
        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //crkApp.setDonorHelper(donorHelper);
    }

    @Override
    public List<BloodRequest> getBloodRequset() {
        Log.i("Login Activity", "getBloodRequestCalled");
        return donorHelper.getAllBloodRequest();
    }

    @Override
    public void setBloodRequset(List<BloodRequest> requestBloodList) {
        Log.i("Login Activity", "setBloodRequestCalled");
        donorHelper.setAllBloodRequest(requestBloodList);
        crkApp.setDonorHelper(donorHelper);
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }

    @Override
    public void changeFragment(Fragment fragment) {

    }
}

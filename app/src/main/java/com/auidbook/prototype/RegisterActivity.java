package com.auidbook.prototype;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {

    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        signUp = (Button) findViewById(R.id.signUp2);
        signUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
        //Intent myIntent = new Intent(view.getContext(), agones.class);
        //startActivityForResult(myIntent, 0);
        AlertDialog.Builder adb = new AlertDialog.Builder(RegisterActivity.this);
        adb.setTitle("Have you donated Before??");
        adb.setIcon(android.R.drawable.ic_dialog_alert);
        adb.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
        DialogFragment dFragment = new DatePickerFragment();
        // Show the date picker dialog fragment
        dFragment.show(getFragmentManager(), "Date Picker");
        }
                });


                adb.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i= new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(i);
                        setContentView(R.layout.activity_main);
                    }
                });
                adb.show();
            }
        });
    }

    public class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener{

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState){
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dpd = new DatePickerDialog(getActivity(),
                    AlertDialog.THEME_HOLO_LIGHT,this,year,month,day);

            // Return the DatePickerDialog
            return  dpd;
        }

        public void onDateSet(DatePicker view, int year, int month, int day){
            Intent i= new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
            setContentView(R.layout.activity_main);



            // Do something with the chosen date
        }

        // Set a Listener for Dialog Cancel button click event
        /*
            onCancel(DialogInterface dialog)
                This method will be invoked when the dialog is canceled.
         */
        public void onCancel(DialogInterface dialog){
            // Send a message to confirm cancel button click
            Toast.makeText(getActivity(),"Date Picker Canceled.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        finish();


    }
}






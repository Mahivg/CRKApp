package com.auidbook.prototype.UIModel;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.auidbook.prototype.Model.BloodRequest;
import com.auidbook.prototype.Model.Fields.Address;
import com.auidbook.prototype.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mgundappan on 02-07-2016.
 */
public class DialogFilterDonor extends DialogFragment implements View.OnClickListener {

    public static String REQUEST_ADDRESS = "requestaddress";
    private static ArrayList<String> addressList;
    private Dialog settingsDialog;
    private Spinner spnAddres;
    private Button btnfilterSubmit;

    public static DialogFilterDonor newInstance(ArrayList<String> addressList){


        DialogFilterDonor dialogFilterDonor = new DialogFilterDonor();

        DialogFilterDonor.addressList = addressList;        //bundle.put

        return  dialogFilterDonor;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        System.out.println(" I am in Oncreate");

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_filter_donor, null);

        spnAddres = (Spinner)view.findViewById(R.id.spn_address);

        btnfilterSubmit = (Button) view.findViewById(R.id.btnfilterSubmit);
        btnfilterSubmit.setOnClickListener(this);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, addressList);

        spinnerArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spnAddres.setAdapter(spinnerArrayAdapter);

        settingsDialog = new Dialog(getActivity());
        settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        settingsDialog.setContentView(view);
        settingsDialog.setCancelable(false);
        setCancelable(false);
        return settingsDialog;
    }

    @Override
    public void onClick(View v) {

        if(v.getId()== R.id.btnfilterSubmit){

            Intent i = new Intent();
            i.putExtra(REQUEST_ADDRESS, spnAddres.getSelectedItem().toString());

           // Toast.makeText(getActivity(), "Adapter Position : " + getAdapterPosition(), Toast.LENGTH_SHORT).show();

            getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, i);

            settingsDialog.dismiss();

        }

    }
}

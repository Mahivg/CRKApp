package com.auidbook.prototype;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.auidbook.prototype.Model.BloodRequest;
import com.auidbook.prototype.databinding.FragmentRecipientDetailsBinding;
import com.auidbook.prototype.util.RetrofitUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipientDetailsFragment extends Fragment {
    private BloodRequest bloodRequest;
    private FragmentRecipientDetailsBinding fragmentRecipientDetailsBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentRecipientDetailsBinding = FragmentRecipientDetailsBinding.inflate(inflater, container, false);
        bloodRequest = getArguments().getParcelable(Constants.BLOOD_REQUEST_KEY);
        fragmentRecipientDetailsBinding.setBloodRequest(bloodRequest);
        fragmentRecipientDetailsBinding.setEventHandler(this);
        fragmentRecipientDetailsBinding.setIsLoading(false);
        fragmentRecipientDetailsBinding.executePendingBindings();
        return fragmentRecipientDetailsBinding.getRoot();
    }

    public void donateButtonClicked(final View view) {
        //todo: get current user id
        fragmentRecipientDetailsBinding.setIsLoading(true);
        fragmentRecipientDetailsBinding.executePendingBindings();
        RetrofitUtils.getBloodDonationApi()
                .donate(bloodRequest.getRequestId(), null)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        fragmentRecipientDetailsBinding.setIsLoading(false);
                        if (response.isSuccessful()) {
                            Intent intent = new Intent();
                            intent.setAction(Constants.ACCEPT_DONATION_ACTION);
                            intent.setClass(getContext(), MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        } else {
                            Log.e(RecipientDetailsFragment.class.getName(), response.message());
                            Toast.makeText(getContext(), "Unable to reach server now. Please try again later", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.e(RecipientDetailsFragment.class.getName(), t.getMessage(), t);
                        fragmentRecipientDetailsBinding.setIsLoading(false);
                        Toast.makeText(getContext(), "Unable to reach server now. Please try again later", Toast.LENGTH_LONG).show();
                    }
                });
    }
}
package com.auidbook.prototype;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.auidbook.prototype.Model.BloodRequest;
import com.auidbook.prototype.databinding.FragmentHomeBinding;
import com.auidbook.prototype.util.RetrofitUtils;

import java.util.List;

import me.tatarka.bindingcollectionadapter.ItemView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding homeFragmentDataBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        homeFragmentDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        homeFragmentDataBinding.setVariable(com.auidbook.prototype.BR.listItemView, ItemView.of(com.auidbook.prototype.BR.bloodRequest, R.layout.request_card_view));
        homeFragmentDataBinding.setIsError(false);
        homeFragmentDataBinding.setIsEmpty(false);
        homeFragmentDataBinding.setIsLoading(true);
        homeFragmentDataBinding.executePendingBindings();
        return homeFragmentDataBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        fetchBloodRequests();
    }

    public void fetchBloodRequests() {
        homeFragmentDataBinding.setIsError(false);
        homeFragmentDataBinding.setIsEmpty(false);
        homeFragmentDataBinding.setIsLoading(true);
        homeFragmentDataBinding.executePendingBindings();
        RetrofitUtils.getBloodDonationApi().getAllBloodRequests().enqueue(new Callback<List<BloodRequest>>() {
            @Override
            public void onResponse(Call<List<BloodRequest>> call, Response<List<BloodRequest>> response) {
                homeFragmentDataBinding.setIsLoading(false);
                if (response.isSuccessful()) {
                    List<BloodRequest> bloodRequests = response.body();
                    homeFragmentDataBinding.setBloodRequests(bloodRequests);
                    homeFragmentDataBinding.setIsError(false);
                    homeFragmentDataBinding.setIsEmpty(bloodRequests.isEmpty());
                    homeFragmentDataBinding.executePendingBindings();
                } else {
                    homeFragmentDataBinding.setIsError(true);
                    homeFragmentDataBinding.setIsEmpty(false);
                    Log.e(HomeFragment.class.getName(), response.message());
                }
            }

            @Override
            public void onFailure(Call<List<BloodRequest>> call, Throwable t) {
                Log.e(HomeFragment.class.getName(), t.getMessage(), t);
                homeFragmentDataBinding.setIsEmpty(false);
                homeFragmentDataBinding.setIsLoading(false);
                homeFragmentDataBinding.setIsError(true);
            }
        });
    }

    public void onRetryButtonClick(View v) {
        fetchBloodRequests();
    }
}
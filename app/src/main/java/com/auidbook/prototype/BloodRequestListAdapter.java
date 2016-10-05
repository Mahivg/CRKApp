package com.auidbook.prototype;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.auidbook.prototype.Model.BloodRequest;

import java.util.List;

import me.tatarka.bindingcollectionadapter.BindingRecyclerViewAdapter;
import me.tatarka.bindingcollectionadapter.ItemViewArg;

public class BloodRequestListAdapter extends BindingRecyclerViewAdapter<BloodRequest> implements View.OnClickListener {

    public BloodRequestListAdapter(@NonNull ItemViewArg<BloodRequest> arg) {
        super(arg);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        MyViewHolder viewHolder = (MyViewHolder) holder;
        viewHolder.btn_donate.setOnClickListener(this);
        viewHolder.btn_donate.setTag(getAdapterItem(position));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewDataBinding binding) {
        return new MyViewHolder(binding.getRoot());
    }

    @Override
    public void onClick(View v) {
        BloodRequest bloodRequest = (BloodRequest) v.getTag();
        Intent bloodDonateActivityIntent = new Intent();
        bloodDonateActivityIntent.setClass(v.getContext(), MainActivity.class);
        bloodDonateActivityIntent.setAction(Constants.VIEW_REQUEST_DETAILS_ACTION);
        bloodDonateActivityIntent.putExtra(Constants.BLOOD_REQUEST_KEY, bloodRequest);
        bloodDonateActivityIntent.putExtra(Constants.IS_VIEW_REQUEST_DETAILS, true);
        v.getContext().startActivity(bloodDonateActivityIntent);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private Button btn_donate;

        public MyViewHolder(View itemView) {
            super(itemView);
            btn_donate = (Button) itemView.findViewById(R.id.btn_donate);
        }

    }
}

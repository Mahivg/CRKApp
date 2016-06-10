package com.auidbook.prototype;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.auidbook.prototype.Model.CRKApp;
import com.auidbook.prototype.Model.DonorHelper;
import com.auidbook.prototype.Model.BloodRequest;
import com.auidbook.prototype.UIModel.CurrentRequestListDividerItemDecoration;

import java.util.ArrayList;

/**
 * Created by Rawoof on 5/8/2016.
 */
public class CurrentRequestFragment extends Fragment {

    private CRKApp crkApp;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<BloodRequest> pendingBloodRequestList;
    private DonorHelper donorHelper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_current_requests, container, false);

        crkApp = (CRKApp) getActivity().getApplicationContext();

        donorHelper = crkApp.getDonorHelper();

        mRecyclerView = (RecyclerView) v.findViewById(R.id.current_request_recyclerview);

        mRecyclerView.setHasFixedSize(true);

        pendingBloodRequestList = donorHelper.getPendingBloodRequestList();

        mAdapter = new CurrentRequestAdapter(pendingBloodRequestList);

        mLayoutManager = new LinearLayoutManager(getContext());

        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.addItemDecoration(new CurrentRequestListDividerItemDecoration(Color.GRAY));

        mRecyclerView.setAdapter(mAdapter);

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event


    @Override
    public void onDetach() {
        super.onDetach();
    }


    public class CurrentRequestAdapter extends RecyclerView.Adapter<CurrentRequestAdapter.MyViewHolder>{

        private ArrayList<BloodRequest> requestBloodList;

        // private ICommunicator communicator;
        public CurrentRequestAdapter(ArrayList<BloodRequest> pendingBloodRequestList) {

            this.requestBloodList = pendingBloodRequestList;
            //this.communicator = communicator;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View cardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.current_request_listlayout,parent,false);

            MyViewHolder viewHolder = new MyViewHolder(cardView);

            return  viewHolder;

        }
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {

            holder.txt_requester_name.setText(requestBloodList.get(position).getPatientName());

            holder.txt_requester_blood_unit.setText(requestBloodList.get(position).getNoOfUnitsRrequired()+" Units Required");

            holder.txt_requester_phone_number.setText(requestBloodList.get(position).getContactNumbers().get(0)+"");

            holder.txt_requester_address.setText(requestBloodList.get(position).getDonateLocation().getAddressLine3());

            holder.image_request_user.setImageResource(R.drawable.no_profile_pic);

        }

        @Override
        public int getItemCount() {

            return requestBloodList.size();

        }

        public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            private ImageView image_request_user;
            private TextView txt_requester_name;
            private TextView txt_requester_blood_unit;
            private TextView txt_requester_phone_number;
            private TextView txt_requester_address;

            public MyViewHolder(View itemView) {
                super(itemView);

                txt_requester_name = (TextView) itemView.findViewById(R.id.txt_request_name);

                image_request_user = (ImageView) itemView.findViewById(R.id.image_request_user);

                txt_requester_blood_unit= (TextView) itemView.findViewById(R.id.txt_request_blood_unit);

                txt_requester_phone_number= (TextView) itemView.findViewById(R.id.txt_request_phone_number);

                txt_requester_address= (TextView) itemView.findViewById(R.id.txt_request_address);

            }

            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), "Donated Successfully", Toast.LENGTH_SHORT).show();

            }
        }
    }

 }
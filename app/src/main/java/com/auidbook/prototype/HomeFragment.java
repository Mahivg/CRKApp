package com.auidbook.prototype;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.auidbook.prototype.Model.CRKApp;
import com.auidbook.prototype.Model.DataStorage.DataStore;
import com.auidbook.prototype.Model.Donor;
import com.auidbook.prototype.Model.DonorHelper;
import com.auidbook.prototype.Model.BloodRequest;
import com.auidbook.prototype.UIModel.PagerAdapter;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rawoof on 5/8/2016.
 */
public class HomeFragment extends Fragment {

    private DataStore dataStore;
    private Donor donor;
    private CRKApp crkApp;
    private DonorHelper donorHelper;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<BloodRequest> bloodList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        crkApp = (CRKApp) getActivity().getApplicationContext();

        donor = crkApp.getDonor();

        donorHelper = crkApp.getDonorHelper();

        //System.out.println("BloodList Size :  "+donorHelper.getAllBloodRequest().size());

        dataStore = DataStore.getDataStore(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        initViews(v);

        return v;

    }

    private void initViews(View v) {

        mRecyclerView = (RecyclerView) v.findViewById(R.id.bloodrequest_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        bloodList = donorHelper.getApprovedBloodRequestList();

        mAdapter = new MyCardViewAdapter(bloodList);

        mLayoutManager = new LinearLayoutManager(getContext());

        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(mAdapter);
    }



    // TODO: Rename method, update argument and hook method into UI event


    @Override
    public void onDetach() {

        super.onDetach();
        System.out.println(" Home Fragment OnDetach Is called ");

        crkApp.setDonorHelper(donorHelper);
        crkApp.setDonor(donor);
    }


    public class MyCardViewAdapter extends RecyclerView.Adapter<MyCardViewAdapter.MyViewHolder>{

        private List<BloodRequest> bloodRequestList;
        private int mPosition;

        public MyCardViewAdapter(List<BloodRequest> bloodRequestList) {

            this.bloodRequestList = bloodRequestList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View cardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.request_card_view,parent,false);

            MyViewHolder viewHolder = new MyViewHolder(cardView);

            return  viewHolder;
        }
        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {

            mPosition = position-1;

            holder.txt_requester_name.setText((bloodRequestList.get(position).getPatientName()));

            holder.txt_blood_unit.setText(bloodRequestList.get(position).getNoOfUnitsRrequired()+" Units Required");

            holder.txt_hospital.setText(bloodRequestList.get(position).getDonateLocation().getAddressLine3());

            InputStream is = dataStore.getBloodImageInputStream(bloodRequestList.get(position).getBloodGroup());

            Bitmap bitmap = BitmapFactory.decodeStream(is);

            holder.image_blood_group.setImageBitmap(bitmap);

            holder.btn_donate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bloodRequestList.get(position).getDonorResponsed().add(donor);

                    donor.setIsRequestAccepted(true);
                    donorHelper.getDonorByUserName(donor.getMobileNumber()).setIsRequestAccepted(true);

                    getActivity().finish();
                    startActivity(getActivity().getIntent());
                   // Toast.makeText(getActivity(), "Clicked On position : "+ position, Toast.LENGTH_SHORT).show();
                }
            });

        }

        @Override
        public int getItemCount() {
            return bloodRequestList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder{

            private TextView txt_requester_name;
            private ImageView image_blood_group;
            private TextView txt_blood_unit;
            private TextView txt_hospital;
            private Button btn_donate;

            public MyViewHolder(View itemView) {
                super(itemView);

                txt_requester_name = (TextView) itemView.findViewById(R.id.txt_requester_name);

                image_blood_group = (ImageView) itemView.findViewById(R.id.image_blood_group);

                txt_blood_unit= (TextView) itemView.findViewById(R.id.txt_blood_unit);

                txt_hospital= (TextView) itemView.findViewById(R.id.txt_hospital);

                btn_donate= (Button) itemView.findViewById(R.id.btn_donate);
            }

        }
    }
}
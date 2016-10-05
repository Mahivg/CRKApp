package com.auidbook.prototype;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.auidbook.prototype.Model.BloodRequest;
import com.auidbook.prototype.Model.CRKApp;
import com.auidbook.prototype.Model.DataStorage.DataStore;
import com.auidbook.prototype.Model.DonorHelper;
import com.auidbook.prototype.listener.ICommunicator;

import java.io.InputStream;
import java.util.List;

public class ResponseFragment extends Fragment  {

    private final String DATA_POSITION = "mPosition";
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<BloodRequest> requestList;
    private CRKApp crkApp;
    private DonorHelper donorHelper;
    private DataStore dataStore;
    private ICommunicator communicator;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        crkApp = (CRKApp) getActivity().getApplicationContext();

        donorHelper = crkApp.getDonorHelper();

        dataStore = DataStore.getDataStore(getActivity());

        communicator = (ICommunicator) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_responses, container, false);



        mRecyclerView = (RecyclerView) v.findViewById(R.id.bloodresponse_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        requestList = donorHelper.getApprovedBloodRequestList();

        mAdapter = new ResponseAdapter(requestList);

        mLayoutManager = new LinearLayoutManager(getContext());

        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(mAdapter);

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event


    @Override
    public void onDetach() {
        super.onDetach();
    }

    public class ResponseAdapter extends RecyclerView.Adapter<ResponseAdapter.MyViewHolder>{

        private List<BloodRequest> requestBloodList;
        private int mPosition;
        public ResponseAdapter(List<BloodRequest> requestList) {

            this.requestBloodList = requestList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View cardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.request_card_view,parent,false);

            MyViewHolder viewHolder = new MyViewHolder(cardView);

            return  viewHolder;

        }
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {

            holder.txt_requester_name.setText((requestBloodList.get(position).getPatientName()));

            holder.txt_blood_unit.setText(requestBloodList.get(position).getNumberOfUnits()+" Units Required");

            holder.txt_hospital.setText(requestBloodList.get(position).getLocality());

            holder.txt_response_number.setText("??");

            InputStream is = dataStore.getBloodImageInputStream("O+ve");

            Bitmap bitmap = BitmapFactory.decodeStream(is);

            holder.image_blood_group.setImageBitmap(bitmap);

        }

        @Override
        public int getItemCount() {

            return requestBloodList.size();

        }

        public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            private TextView txt_requester_name;
            private ImageView image_blood_group;
            private TextView txt_blood_unit;
            private TextView txt_hospital;
            private Button btn_donate;
            private TextView txt_response_number;

            public MyViewHolder(View itemView) {
                super(itemView);

                txt_requester_name = (TextView) itemView.findViewById(R.id.txt_requester_name);

                image_blood_group = (ImageView) itemView.findViewById(R.id.image_blood_group);

                txt_blood_unit= (TextView) itemView.findViewById(R.id.txt_blood_unit);

                txt_hospital= (TextView) itemView.findViewById(R.id.txt_hospital);

                btn_donate= (Button) itemView.findViewById(R.id.btn_donate);

                btn_donate.setVisibility(View.GONE);

                txt_response_number = (TextView) itemView.findViewById(R.id.txt_response_number);

                txt_response_number.setVisibility(View.VISIBLE);

                txt_response_number.setOnClickListener(this);

                itemView.setOnClickListener(this);

            }

            @Override
            public void onClick(View v) {
                mPosition = getAdapterPosition();

                if(v.getId() == R.id.txt_response_number) {

//                    if (requestBloodList.get(mPosition).getDonorResponsed().size() > 0) {
//                        Intent i = new Intent(getContext(), DonorRespondedActivity.class);
//                        i.putExtra(DATA_POSITION, mPosition);
//                        startActivity(i);
//                    } else {
//                        Toast.makeText(getActivity(), "No Donor Responded " + mPosition, Toast.LENGTH_SHORT).show();
//                    }
                }
                else{
                    BloodRequest bloodRequest = requestBloodList.get(mPosition);

//                    RequestDetailsFragment requestDetailsFragment = RequestDetailsFragment.newInstance(bloodRequest);
//
//                    communicator.changeFragment(requestDetailsFragment);

                    Toast.makeText(getActivity(), "Goes to edit Response" + mPosition, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

}
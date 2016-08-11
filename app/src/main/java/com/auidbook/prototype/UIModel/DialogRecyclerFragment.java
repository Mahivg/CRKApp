package com.auidbook.prototype.UIModel;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.auidbook.prototype.Model.BloodRequest;
import com.auidbook.prototype.R;

import java.util.List;

/**
 * Created by mgundappan on 25-06-2016.
 */
public class DialogRecyclerFragment extends DialogFragment {

    public static String REQUEST_POSITION = "requestposition";
    private static List<BloodRequest> requestList;
    TextView txtActionTitle;
    RecyclerView mRecyclerView;
    RequestListAdapter mAdapter;
    //private Dialog dialog;
    private RecyclerView.LayoutManager mLayoutManager;


    public DialogRecyclerFragment(){

    }

    public static DialogRecyclerFragment newInstance(List<BloodRequest> requestList){


        DialogRecyclerFragment dialogRecyclerFragment = new DialogRecyclerFragment();

        DialogRecyclerFragment.requestList = requestList;
        //bundle.put

        return  dialogRecyclerFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_recycler_view, null, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_list);

        txtActionTitle = (TextView) view.findViewById(R.id.txtActionTitle);

        txtActionTitle.setText("Select Request");

        mAdapter = new RequestListAdapter(requestList);

        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setAdapter(mAdapter);

        Dialog dialog = new Dialog(getActivity());

        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        dialog.setContentView(view);

        return dialog;
    }

    public class RequestListAdapter extends RecyclerView.Adapter<RequestListAdapter.MyViewHolder>{

        private List<BloodRequest> requestBloodList;

        // private ICommunicator communicator;
        public RequestListAdapter(List<BloodRequest> pendingBloodRequestList) {

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

            holder.txt_requester_blood_unit.setText(requestBloodList.get(position).getNumberOfUnits()+" Units Required");

            holder.txt_requester_phone_number.setText(requestBloodList.get(position).getContactNumber()+"");

            holder.txt_requester_address.setText(requestBloodList.get(position).getLocality());

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

                itemView.setOnClickListener(this);

                txt_requester_name = (TextView) itemView.findViewById(R.id.txt_request_name);

                image_request_user = (ImageView) itemView.findViewById(R.id.image_request_user);

                txt_requester_blood_unit= (TextView) itemView.findViewById(R.id.txt_request_blood_unit);

                txt_requester_phone_number= (TextView) itemView.findViewById(R.id.txt_request_phone_number);

                txt_requester_address= (TextView) itemView.findViewById(R.id.txt_request_address);

            }

            @Override
            public void onClick(View v) {


                Intent i = new Intent();
                i.putExtra(REQUEST_POSITION, getAdapterPosition());

                Toast.makeText(getActivity(), "Adapter Position : " + getAdapterPosition(), Toast.LENGTH_SHORT).show();

                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, i);

                dismiss();

            }
        }
    }
}

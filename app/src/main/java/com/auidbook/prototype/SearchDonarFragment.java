package com.auidbook.prototype;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.auidbook.prototype.Model.CRKApp;
import com.auidbook.prototype.Model.DataStorage.DataStore;
import com.auidbook.prototype.Model.Donor;
import com.auidbook.prototype.Model.DonorHelper;
import com.auidbook.prototype.UIModel.AlertImageAdapter;
import com.auidbook.prototype.UIModel.CurrentRequestListDividerItemDecoration;
import com.auidbook.prototype.UIModel.DialogImageFragment;
import com.auidbook.prototype.UIModel.DialogRecyclerFragment;
import com.auidbook.prototype.UIModel.DividerLineItemDecoration;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class SearchDonarFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int SET_DONOR_CODE = 3;
    AlertDialog alertSelectBlood;
    String bloodSelected = "";

    private CRKApp crkApp;
    private DonorHelper donorHelper;
    private Donor donorOnLongClick;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private DataStore dataStore;
    private RecyclerView mRecyclerView;
    private DonorSearchViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private CardView ifRecycleEmpty;
    private List<Donor> donorList;
    private List<Donor> filteredDonorList = new ArrayList<Donor>();
    private EditText txt_search;
    private ImageView img_icon_filterByBlood;
    private GridView alertGrid;
    private AlertDialog alertSelectRequest;


    public SearchDonarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        crkApp = (CRKApp) getActivity().getApplicationContext();

        donorHelper = crkApp.getDonorHelper();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_search_donar, container, false);

        txt_search = (EditText) v.findViewById(R.id.txt_search);

        ifRecycleEmpty = (CardView) v.findViewById(R.id.card_empty_recycler);

        img_icon_filterByBlood = (ImageView) v.findViewById(R.id.img_bloodGroupFilter);

        img_icon_filterByBlood.setOnClickListener(this);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.donor_serach_recylerview);

        mRecyclerView.setHasFixedSize(true);

        dataStore = DataStore.getDataStore(getActivity());

        donorList = donorHelper.getAllDonor();

        filteredDonorList.addAll(donorList);

        mAdapter = new DonorSearchViewAdapter(filteredDonorList);

        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(mAdapter);

        Drawable d = ContextCompat.getDrawable(getActivity(), R.drawable.line_divider1);

        DividerLineItemDecoration dividerLineItemDecoration = new DividerLineItemDecoration(d);

        mRecyclerView.addItemDecoration(new CurrentRequestListDividerItemDecoration(Color.RED));

        txt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mAdapter.getFilter().filter(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        bloodSelected = "";

        setHasOptionsMenu(true);
        return v;
    }

    private void checkAdapterEmptyOrNot() {

        if (mAdapter.getItemCount() == 0) {

            ifRecycleEmpty.setVisibility(View.VISIBLE);
        } else {

            ifRecycleEmpty.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu_search_donor, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_filter){

            Toast.makeText(getContext(),"Filter Apply",Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    // TODO: Rename method, update argument and hook method into UI event


    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.img_bloodGroupFilter) {

           LayoutInflater inflater = getActivity().getLayoutInflater();

            View alert_layout = inflater.inflate(R.layout.alert_blood_filter, null);

            alertGrid = (GridView) alert_layout.findViewById(R.id.alert_grid);
            alertGrid.setAdapter(new AlertImageAdapter(getActivity()));

            alertGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    bloodSelected = DataStore.getBloodArray()[position];

                    Toast.makeText(view.getContext(), "Selected Blood is "+ DataStore.getBloodArray()[position],Toast.LENGTH_SHORT).show();
                    filteredDonorListByBlood(bloodSelected);
                    alertSelectBlood.dismiss();
                }
            });

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Select Blood Group");

            builder.setView(alert_layout);
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.setView(alert_layout);
            alertSelectBlood = builder.create();

            alertSelectBlood.show();
        }
    }

    private void filteredDonorListByBlood(String bloodSelected) {

        filteredDonorList.clear();
        for (int i = 0; i < donorList.size(); i++) {
            Donor donor = donorList.get(i);
            System.out.println("*** I am in inside For Loop");
            if (donor.getBloodGroup().equals(bloodSelected)) {
                filteredDonorList.add(donor);
            }
            System.out.println("***** Filtered DonorList Size : " + filteredDonorList.size());
            System.out.println("***** DonorList Size : " + donorList.size());
        }
        mAdapter.notifyDataSetChanged();
        checkAdapterEmptyOrNot();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(data==null){
            return;
        }

        if(resultCode == Activity.RESULT_OK) {
            if (requestCode == SET_DONOR_CODE) {

                int pos = data.getIntExtra(DialogRecyclerFragment.REQUEST_POSITION,0);
                if(donorOnLongClick!= null) {
                    System.out.println("***** DonorOnLongClick Is Added to List ");
                    donorHelper.getApprovedBloodRequestList().get(pos).getDonorResponsed().add(donorOnLongClick);
                }
                else{
                    System.out.println("***** DonorOnLongClick Is Null ");
                }
            }
        }
    }

    public class DonorSearchViewAdapter extends RecyclerView.Adapter<DonorSearchViewAdapter.MyViewHolder> implements Filterable {

        private List<Donor> donorObjectList;
        private CustomFilter mFilter;

        public DonorSearchViewAdapter(List<Donor> donorObjectList) {

            this.donorObjectList = donorObjectList;
            mFilter = new CustomFilter(DonorSearchViewAdapter.this);
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View cardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_donor_listlayout, parent, false);

            MyViewHolder viewHolder = new MyViewHolder(cardView);

            return viewHolder;

        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {

            final int cPosition = position;

            holder.image_user.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.no_profile_pic));

            holder.txt_name.setText(donorObjectList.get(position).getDonorName());

            holder.txt_phone_number.setText(donorObjectList.get(position).getMobileNumber() + "");

            holder.txt_address.setText(donorObjectList.get(position).getAddresses().get(0).getAddressLine3());

            InputStream is = dataStore.getBloodImageInputStream(donorObjectList.get(position).getBloodGroup());

            Bitmap bitmap = BitmapFactory.decodeStream(is);

            holder.image_blood_group.setImageBitmap(bitmap);

            holder.v.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    donorOnLongClick = donorObjectList.get(cPosition);

                    LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                    View alertView = inflater.inflate(R.layout.alert_serachdonor_longclick,null,false);

                    Button btn = (Button) alertView.findViewById(R.id.btnAddToRequest);

                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            alertSelectRequest.dismiss();

                            DialogRecyclerFragment dialogRecyclerFragment = DialogRecyclerFragment.newInstance(donorHelper.getApprovedBloodRequestList());

                            dialogRecyclerFragment.setTargetFragment(SearchDonarFragment.this, SET_DONOR_CODE);

                            dialogRecyclerFragment.show(getFragmentManager(),"Show RequestList");

                        }
                    });

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setView(alertView);
/*
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });*/

                    alertSelectRequest = builder.create();
                    alertSelectRequest.show();
                    return true;
                }
            });
        }

        @Override
        public int getItemCount() {

            return donorObjectList.size();
        }

        @Override
        public Filter getFilter() {
            return mFilter;
        }


        public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private ImageView image_user;
            private TextView txt_name;
            private TextView txt_phone_number;
            private TextView txt_address;
            private ImageView image_blood_group;
            private View v;


            public MyViewHolder(View itemView) {
                super(itemView);

                this.v = itemView;
                itemView.setOnClickListener(this);

                txt_name = (TextView) itemView.findViewById(R.id.txt_name);

                txt_address = (TextView) itemView.findViewById(R.id.txt_address);

                image_user = (ImageView) itemView.findViewById(R.id.image_user);

                image_user.setOnClickListener(this);

                txt_phone_number = (TextView) itemView.findViewById(R.id.txt_phone_number);

                image_blood_group = (ImageView) itemView.findViewById(R.id.image_blood_group);

            }

            @Override
            public void onClick(View v) {

                if (v.getId() == R.id.image_user) {

                    System.out.println(" I am in Onclick image");

                    Bitmap bitmap = ((BitmapDrawable) image_user.getDrawable()).getBitmap();

                    DialogImageFragment dialogImageFragment = DialogImageFragment.newInstance(bitmap);

                    dialogImageFragment.show(getFragmentManager(), "Show Dp");

                   /* Dialog settingsDialog = new Dialog(getContext());
                    settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                    settingsDialog.setContentView(image_user,null);
                    settingsDialog.show();*/
/*
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .addSharedElement(image_user,"DP_IMAGE")
                            .add(R.id.container_fragment_layout,dialougeImageFragment)
                            .commit();*/
                } else {
                    Toast.makeText(getActivity(), "Donated Successfully", Toast.LENGTH_SHORT).show();
                }

            }
        }

        public class CustomFilter extends Filter {
            private DonorSearchViewAdapter mAdapter;

            private CustomFilter(DonorSearchViewAdapter mAdapter) {
                super();
                this.mAdapter = mAdapter;
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                filteredDonorList.clear();

                final FilterResults results = new FilterResults();
                if (constraint.length() == 0) {
                    filteredDonorList.addAll(donorList);
                    bloodSelected = "";

                    System.out.println("*** I am in If length is 0");
                } else {
                    final String filterPattern = constraint.toString().toLowerCase().trim();
                    System.out.println("*** I am in If length is > 0");
                    System.out.println("*** Filtered pattern : " + filterPattern);

                    for (int i = 0; i < donorList.size(); i++) {
                        Donor donor = donorList.get(i);
                        System.out.println("*** I am in inside For Loop");
                        if(bloodSelected.length()>0){
                            if (donor.getDonorName().toLowerCase().startsWith(filterPattern)&& donor.getBloodGroup().equals(bloodSelected)) {
                                filteredDonorList.add(donor);
                            }
                        }
                        else{
                            if (donor.getDonorName().toLowerCase().startsWith(filterPattern)) {
                                filteredDonorList.add(donor);
                            }
                        }

                        System.out.println("***** Filtered DonorList Size : " + filteredDonorList.size());
                        System.out.println("***** DonorList Size : " + donorList.size());
                    }
                }
                System.out.println("Count Number " + filteredDonorList.size());
                results.values = filteredDonorList;
                results.count = filteredDonorList.size();
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                System.out.println("Result Count Number " + ((ArrayList<Donor>) results.values).size());

                this.mAdapter.notifyDataSetChanged();
                checkAdapterEmptyOrNot();
            }
        }
    }


}

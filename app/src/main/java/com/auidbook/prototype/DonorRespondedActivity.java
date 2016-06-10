package com.auidbook.prototype;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.auidbook.prototype.Model.BloodRequest;
import com.auidbook.prototype.Model.CRKApp;
import com.auidbook.prototype.Model.Donor;
import com.auidbook.prototype.Model.DonorHelper;
import com.auidbook.prototype.UIModel.DividerLineItemDecoration;

import java.util.ArrayList;

public class DonorRespondedActivity extends AppCompatActivity {
    private CRKApp crkApp;
    private DonorHelper donorHelper;
    private ArrayList<Donor> respondedDonorList;
    private final String DATA_POSITION = "mPosition";
    private Toolbar mToolBar;
    private RecyclerView mRecyclerView;
    private DonorResponsedViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView txt_request_id;
    private Paint p = new Paint();
    private AlertDialog.Builder alertDialog;
    private View view;
    private boolean add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_responded);

        mToolBar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(mToolBar);

        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);


        crkApp = (CRKApp) getApplicationContext();

        donorHelper = crkApp.getDonorHelper();

        int position = getIntent().getIntExtra(DATA_POSITION,0);

        respondedDonorList = donorHelper.getApprovedBloodRequestList().get(position).getDonorResponsed();

        System.out.println("Donor Responded : " + respondedDonorList.size());

        mRecyclerView = (RecyclerView) findViewById(R.id.donor_responded_recyclerview);

        txt_request_id = (TextView) findViewById(R.id.txt_request_id);

        txt_request_id.setText("Donor Responded For Request Id : " + donorHelper.getApprovedBloodRequestList().get(position).getRequestId());

        mRecyclerView.setHasFixedSize(true);

        mAdapter = new DonorResponsedViewAdapter(respondedDonorList);

        mLayoutManager = new LinearLayoutManager(this);

        Drawable d = getDrawable(R.drawable.line_divider1);

        DividerLineItemDecoration dividerLineItemDecoration = new DividerLineItemDecoration(d);

        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.addItemDecoration(dividerLineItemDecoration);

        mRecyclerView.setAdapter(mAdapter);

        initSwipe();

        initDialog();
    }
    private void initSwipe(){
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                CheckBox chkCommments;

                final int position = viewHolder.getAdapterPosition();
                view = getLayoutInflater().inflate(R.layout.alert_reject_donor,null);
                chkCommments = (CheckBox) view.findViewById(R.id.chkComment);
                alertDialog.setTitle("Comments");



                if (direction == ItemTouchHelper.LEFT){

                    chkCommments.setVisibility(View.VISIBLE);

                } else {
                   // removeView();
                    add = true;
                    chkCommments.setVisibility(View.GONE);
                }
                alertDialog.setView(view);
                alertDialog.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (add) {
                            add = false;
                           // mAdapter.removeItem(position);
                            mAdapter.notifyDataSetChanged();
                            Toast.makeText(getApplicationContext(),"Accepted",Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        } else {

                             mAdapter.notifyDataSetChanged();
                            Toast.makeText(getApplicationContext(),"Rejected",Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }

                    }
                });
                alertDialog.show();
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                Bitmap icon;
                if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){

                    View itemView = viewHolder.itemView;
                    float height = (float) itemView.getBottom() - (float) itemView.getTop();
                    float width = height / 3;

                    if(dX > 0){
                        p.setColor(Color.parseColor("#388E3C"));
                        RectF background = new RectF((float) itemView.getLeft(), (float) itemView.getTop(), dX,(float) itemView.getBottom());
                        c.drawRect(background,p);
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_action_accept);
                        RectF icon_dest = new RectF((float) itemView.getLeft() + width ,(float) itemView.getTop() + width,(float) itemView.getLeft()+ 2*width,(float)itemView.getBottom() - width);
                        c.drawBitmap(icon,null,icon_dest,p);
                    } else {
                        p.setColor(Color.parseColor("#D32F2F"));
                        RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(),(float) itemView.getRight(), (float) itemView.getBottom());
                        c.drawRect(background,p);
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_action_reject);
                        RectF icon_dest = new RectF((float) itemView.getRight() - 2*width ,(float) itemView.getTop() + width,(float) itemView.getRight() - width,(float)itemView.getBottom() - width);
                        c.drawBitmap(icon,null,icon_dest,p);
                    }
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }
    private void removeView(){
        if(view.getParent()!=null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
    }
    private void initDialog(){
        alertDialog = new AlertDialog.Builder(this);

    }

    public class DonorResponsedViewAdapter extends RecyclerView.Adapter<DonorResponsedViewAdapter.MyViewHolder>{

        private ArrayList<Donor> respondedDonorList;
        private int mPosition;

        public DonorResponsedViewAdapter(ArrayList<Donor> respondedDonorList) {

            this.respondedDonorList = respondedDonorList;
        }
        public void removeItem(int position) {
            respondedDonorList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, respondedDonorList.size());
        }
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View cardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.donor_responded_layout,parent,false);

            MyViewHolder viewHolder = new MyViewHolder(cardView);

            return  viewHolder;

        }
        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {



            holder.txt_donor_name.setText((respondedDonorList.get(position).getDonorName()));

            holder.txt_contact.setText(respondedDonorList.get(position).getMobileNumber());

            holder.btn_accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(DonorRespondedActivity.this, "Donor Accepted " , Toast.LENGTH_SHORT).show();
                }
            });

            holder.btn_reject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(DonorRespondedActivity.this, "Donor Rejected ", Toast.LENGTH_SHORT).show();
                }
            });

        }

        @Override
        public int getItemCount() {
            return respondedDonorList.size();
        }


        public class MyViewHolder extends RecyclerView.ViewHolder{

            private ImageView image_prof_pic;
            private TextView txt_donor_name;
            private TextView txt_contact;
            private Button btn_accept;
            private Button btn_reject;

            public MyViewHolder(View itemView) {
                super(itemView);

                image_prof_pic = (ImageView) itemView.findViewById(R.id.img_prof_pic);

                txt_donor_name = (TextView) itemView.findViewById(R.id.donor_name);

                txt_contact= (TextView) itemView.findViewById(R.id.txt_contact);

                btn_accept = (Button) itemView.findViewById(R.id.btn_accept);

                btn_reject = (Button) itemView.findViewById(R.id.btn_reject);

                //  btn_donate.setOnClickListener(this);
            }

        }
    }


}

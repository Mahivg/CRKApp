package com.auidbook.prototype;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.auidbook.prototype.Model.CRKApp;
import com.auidbook.prototype.Model.DataStorage.SessionManager;
import com.auidbook.prototype.Model.Donor;
import com.auidbook.prototype.UIModel.Map.IMap;
import com.auidbook.prototype.UIModel.Map.MapContainerFragment;
import com.auidbook.prototype.UIModel.Map.MapViewFragment;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, IMap {

    private static final String TAG = "MainActivity";
    private static final String SELECTED_POSITION = "selectedPosition";

    private CRKApp crkApp;
    private Donor userLogged;
    private SessionManager sessionManager;
    private int mCurrentNavPosition;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private Toolbar mToolbar;
    private FragmentTransaction fragmentTransaction;

    private TextView txt_userName;
    private TextView txt_lastDonated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        crkApp = (CRKApp) getApplicationContext();

        userLogged = crkApp.getDonor();

        sessionManager = new SessionManager(this);

        System.out.println(" Logged User Name : " + userLogged.getDonorName());

        System.out.println("OnCreate UserLogged IsRequest Accepted : " + userLogged.isRequestAccepted());
        if(userLogged.isRequestAccepted()){
            replaceNavigationFragment(new AcceptDonationFragment());
        }
        else{
            replaceNavigationFragment( new HomeFragment());
        }
        getSupportActionBar().setTitle("Home");
      /*  fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container_fragment_layout, new HomeFragment());
        fragmentTransaction.commit();
       */

        // Enable opening of drawer
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDrawerLayout.isDrawerVisible(GravityCompat.START))
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                else
                    mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });
        // Add drawer listeners
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mNavigationView.setNavigationItemSelectedListener(this);

        View navDrawerHeader = mNavigationView.inflateHeaderView(R.layout.nav_drawer_header);

        txt_userName = (TextView) navDrawerHeader.findViewById(R.id.txt_header_user_name);
        txt_lastDonated = (TextView) navDrawerHeader.findViewById(R.id.txt_header_last_donated);

        populateNavigationDrawerHeader();

        setNavigationDrawerItems(userLogged.getMemberType());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.app_name,R.string.app_name)
        {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().show();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().show();
            }

        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.closeDrawers();
        mDrawerToggle.syncState();
    }



    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.home_fragment:
                //getSupportFragmentManager().popBackStack(null, getSupportFragmentManager().POP_BACK_STACK_INCLUSIVE);

                System.out.println("OnNavigation :UserLogged IsRequest Accepted : " + userLogged.isRequestAccepted());
                if(userLogged.isRequestAccepted()){
                    replaceNavigationFragment(new AcceptDonationFragment());
                }
                else{
                    replaceNavigationFragment(new HomeFragment());
                }
                getSupportActionBar().setTitle("Home");
                mCurrentNavPosition = 0;
                break;

            case R.id.current_request_fragment:
                // getSupportFragmentManager().popBackStack(null, getSupportFragmentManager().POP_BACK_STACK_INCLUSIVE);
                replaceNavigationFragment(new CurrentRequestFragment());
                getSupportActionBar().setTitle("Current Request");
                mCurrentNavPosition = 1;
                break;
            case R.id.create_request_fragment:
                //getSupportFragmentManager().popBackStack(null, getSupportFragmentManager().POP_BACK_STACK_INCLUSIVE);
                replaceNavigationFragment(new CreateRequestFragment());
                getSupportActionBar().setTitle("Create Request");
                mCurrentNavPosition = 1;
                break;
            case R.id.view_response_fragment:
                //getSupportFragmentManager().popBackStack(null, getSupportFragmentManager().POP_BACK_STACK_INCLUSIVE);
                replaceNavigationFragment(new ResponseFragment());
                getSupportActionBar().setTitle("Responses");
                mCurrentNavPosition = 2;
                break;
            case R.id.view_donors_fragment:
                //getSupportFragmentManager().popBackStack(null, getSupportFragmentManager().POP_BACK_STACK_INCLUSIVE);
                replaceNavigationFragment(new SearchDonarFragment());
                getSupportActionBar().setTitle("Search Donors");
                mCurrentNavPosition = 3;
                break;
            case R.id.update_profile_fragment:
                //getSupportFragmentManager().popBackStack(null, getSupportFragmentManager().POP_BACK_STACK_INCLUSIVE);
                replaceNavigationFragment(new UpdateProfileFragment());
                getSupportActionBar().setTitle("Update Profile");
                mCurrentNavPosition = 4;
                break;
            case R.id.helpandfeedback:
                //getSupportFragmentManager().popBackStack(null, getSupportFragmentManager().POP_BACK_STACK_INCLUSIVE);
                replaceNavigationFragment(new FeedBackFragment());
                getSupportActionBar().setTitle("FeedBack Please");
                mCurrentNavPosition = 5;
                break;
            case R.id.donarLocation:
                replaceNavigationFragment(new MapContainerFragment());
                getSupportActionBar().setTitle("Surrounding Donars");
                mCurrentNavPosition = 6;
                break;
            default:
                Log.w(TAG, "Unknown drawer item selected");
                break;
        }

        menuItem.setChecked(true);
        //  setupTabs(mCurrentNavPosition);
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mCurrentNavPosition = savedInstanceState.getInt(SELECTED_POSITION, 0);
        final Menu menu = mNavigationView.getMenu();
        final MenuItem menuItem = menu.getItem(mCurrentNavPosition);
        menuItem.setChecked(true);
        // setupTabs(mCurrentNavPosition);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_tool_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_logout:

                sessionManager.logoutUser();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SELECTED_POSITION, mCurrentNavPosition);
    }
    @Override
    public void onBackPressed() {

        if (mDrawerLayout.isDrawerVisible(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Alert");
                builder.setMessage("Do you Want to Exit?");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent i = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(i);
                        //finishAffinity();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();

            } else {
                // super.onBackPressed();
                getSupportFragmentManager().popBackStack(null, getSupportFragmentManager().POP_BACK_STACK_INCLUSIVE);
                if(userLogged.isRequestAccepted()){
                    replaceNavigationFragment(new AcceptDonationFragment());
                }
                else{
                    replaceNavigationFragment(new HomeFragment());
                }
                getSupportActionBar().setTitle("Home");
            }
        }
    }

    @Override
    public void addMap(MapViewFragment fragment) {

        getFragmentManager().beginTransaction().add(R.id.map_container, fragment).addToBackStack(null).commit();
    }

    protected boolean replaceNavigationFragment(Fragment fragment) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment_layout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        return true;
    }

    private void populateNavigationDrawerHeader() {

        txt_userName.setText(userLogged.getDonorName());
    }

    private void setNavigationDrawerItems(String memberType) {

       if(memberType.equals("Member")){
           mNavigationView.getMenu().findItem(R.id.current_request_fragment).setVisible(false);
           mNavigationView.getMenu().findItem(R.id.create_request_fragment).setVisible(false);
           mNavigationView.getMenu().findItem(R.id.view_response_fragment).setVisible(false);
           mNavigationView.getMenu().findItem(R.id.view_donors_fragment).setVisible(false);
           mNavigationView.getMenu().findItem(R.id.donarLocation).setVisible(false);
       }

    }

}

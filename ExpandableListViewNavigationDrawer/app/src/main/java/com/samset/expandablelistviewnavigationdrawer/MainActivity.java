package com.samset.expandablelistviewnavigationdrawer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;


import com.samset.expandablelistviewnavigationdrawer.adapter.ExpandableListDrawerAdapter;
import com.samset.expandablelistviewnavigationdrawer.fragments.HomeFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends ActionBarActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    public ExpandableListView mExpandableListView;
    private ExpandableListDrawerAdapter mExpandableListAdapter;
    private Map<String, List<String>> mExpandableListData;
    private String selectedItem;
    private LinearLayout linearLayout;
    private Toolbar toolbar;

    private HashMap<String, List<String>> listDataChild;
    private List<String> listDataHeader;

    View listHeaderView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.tool);


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mExpandableListView = (ExpandableListView) findViewById(R.id.navList);
        //

        LayoutInflater inflater = getLayoutInflater();
        listHeaderView = inflater.inflate(R.layout.nav_header, null, false);
        linearLayout = (LinearLayout) listHeaderView.findViewById(R.id.upper_linear_layout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beginTransction(new HomeFragment());
                mDrawerLayout.closeDrawer(GravityCompat.START);
                toolbar.setTitle("MyDrawerSample");
            }
        });
        setToolbar();

        prepareListData();
        addDrawerItems();
        setupDrawer();


        beginTransction(new HomeFragment());


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);  // OPEN DRAWER
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
    // setting toolbar
    private void setToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);

            final ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setDisplayShowHomeEnabled(true);
                actionBar.setDisplayShowTitleEnabled(true);
                actionBar.setDisplayUseLogoEnabled(false);
                actionBar.setHomeButtonEnabled(true);
            }
        }
    }
    private void prepareListData() {

        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        listDataHeader = Arrays.asList(getResources().getStringArray(R.array.nav_drawer_items));

        List<String> home = Arrays.asList(getResources().getStringArray(R.array.home));

        List<String> menlist = Arrays.asList(getResources().getStringArray(R.array.men));

        List<String> womenlist =Arrays.asList(getResources().getStringArray(R.array.women));

        List<String> childlist = Arrays.asList(getResources().getStringArray(R.array.child));

        List<String> electronicslist = Arrays.asList(getResources().getStringArray(R.array.electronics));

        List<String> homedecorelist = Arrays.asList(getResources().getStringArray(R.array.homedecore));

        List<String> computerlist = Arrays.asList(getResources().getStringArray(R.array.computeraccess));

        List<String> personallist = Arrays.asList(getResources().getStringArray(R.array.personal));

        List<String> gaminglist = Arrays.asList(getResources().getStringArray(R.array.gaming));

        List<String> healthlist = Arrays.asList(getResources().getStringArray(R.array.health));



        listDataChild.put(listDataHeader.get(0), home);
        listDataChild.put(listDataHeader.get(1), menlist);
        listDataChild.put(listDataHeader.get(2), womenlist);
        listDataChild.put(listDataHeader.get(3), childlist);
        listDataChild.put(listDataHeader.get(4), electronicslist);
        listDataChild.put(listDataHeader.get(5), homedecorelist);
        listDataChild.put(listDataHeader.get(6), computerlist);
        listDataChild.put(listDataHeader.get(7), personallist);
        listDataChild.put(listDataHeader.get(8), gaminglist);
        listDataChild.put(listDataHeader.get(9), healthlist);


        mExpandableListView.addHeaderView(listHeaderView);
        mExpandableListData = listDataChild;
        // mExpandableListTitle = new ArrayList(listDataChild.keySet());
        Log.v("Main", " Key set " + listDataChild.keySet());
    }



    private void addDrawerItems() {
        mExpandableListAdapter = new ExpandableListDrawerAdapter(this, listDataHeader, mExpandableListData);
        mExpandableListView.setAdapter(mExpandableListAdapter);

        mExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Log.e("Main"," you click parent ");
                return false;
            }
        });
        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Log.e("Main"," you click child ");
                selectedItem = ((List) (mExpandableListData.get(listDataHeader.get(groupPosition)))).get(childPosition).toString();
                toolbar.setTitle(selectedItem);

                Log.e("Main"," you click child ");
                if (selectedItem.equalsIgnoreCase("Shirt")) {
                    beginTransction(new HomeFragment());
                }
                else if (selectedItem.equalsIgnoreCase("Paint")) {
                    beginTransction(new HomeFragment());
                }
                else if (selectedItem.equalsIgnoreCase("Watch")) {
                    beginTransction(new HomeFragment());
                }

                mDrawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
        mExpandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {

                toolbar.setTitle(listDataHeader.get(groupPosition).toString());
            }
        });

        mExpandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {


            }
        });


    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                toolbar.setTitle(R.string.app_name);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                if (selectedItem != null) {
                    toolbar.setTitle(selectedItem);
                } else {
                    toolbar.setTitle("MyShopingCart");
                }
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };


        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        // super.onBackPressed();
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(Intent.ACTION_MAIN);
                                intent.addCategory(Intent.CATEGORY_HOME);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                System.exit(0);
                            }
                        }).setNegativeButton("No", null).show();

    }
    private void beginTransction(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }
}

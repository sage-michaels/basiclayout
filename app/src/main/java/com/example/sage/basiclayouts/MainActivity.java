package com.example.sage.basiclayouts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.daimajia.swipe.util.Attributes;
import com.example.sage.basiclayouts.dao.Person;


import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by sage on 6/13/17.
 */

public class MainActivity extends AppCompatActivity implements WhiteListDisplay.ListItemClickListener {

    private RecyclerView mContactList;
    private Toast mToast;

    Person sage = new Person("Sage","Michaels","0018587768475");
    Person john = new Person("John","Smith","12345678910");
    Person jane = new Person("Jane","Doe","10987654321");
    //TODO set first and last names of the above people as well as phone numbers
    private Person[] adapterData = new Person[] {sage, john,jane};
    private ArrayList<Person> mDataSet = new ArrayList(Arrays.asList(adapterData));
    private WhiteListDisplay mAdapter = new WhiteListDisplay(mDataSet, this);






    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_white_list_display);

        mContactList = (RecyclerView) findViewById(R.id.whitelist);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mContactList.setLayoutManager(layoutManager);
        mContactList.setHasFixedSize(true);
        mAdapter = new WhiteListDisplay(mDataSet, this);
        mContactList.setAdapter(mAdapter);
    }


    public void onListItemClick(int clickedItemIndex) {
        if (mToast != null){
            mToast.cancel();
        }
        String toastMessage = "item #" + clickedItemIndex +"clicked.";
        mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);
        mToast.show();
    }
}

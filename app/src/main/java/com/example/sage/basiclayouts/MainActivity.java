package com.example.sage.basiclayouts;

import android.content.Intent;
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

    Person sage = new Person("Sage","Michaels","0018587768475");
    Person john = new Person("John","Smith","12345678910");
    Person jane = new Person("Jane","Doe","10987654321");
    Person carlos = new Person("Carlos","Mancia","188849339203");
    Person larry = new Person("Larry", "David", "9948573920");
    Person larryR = new Person("Larry","Rubin","99573663842");
    Person danny = new Person("Danny","Lim","885930521");
    Person lebron = new Person("Lebron", "James", "55473648");
    Person luke = new Person("Luke","Skywalker","994739958");
    private Person[] adapterData = new Person[] {luke,sage, john,larry,jane,danny,carlos, larryR,lebron};
    private ArrayList<Person> mDataSet = new ArrayList(Arrays.asList(adapterData));
    private WhiteListDisplay mAdapter = new WhiteListDisplay(mDataSet, this);






    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_white_list_display);

        mContactList = (RecyclerView) findViewById(R.id.whitelist);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mContactList.setLayoutManager(layoutManager);
        mContactList.setHasFixedSize(false);
        mAdapter = new WhiteListDisplay(mDataSet, this);
        mContactList.setAdapter(mAdapter);
    }


    public void onListItemClick(int clickedItemIndex) {
        Intent intent = new Intent(mContactList.getContext(), ContactPage.class );
        startActivity(intent);
    }
}

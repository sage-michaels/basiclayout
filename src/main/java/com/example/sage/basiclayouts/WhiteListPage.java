package com.example.sage.basiclayouts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.sage.basiclayouts.dao.Person;
import com.example.sage.basiclayouts.dao.PersonDao;
import com.example.sage.basiclayouts.dao.PersonDaoImpl;

public class WhiteListPage extends AppCompatActivity implements ListItemClickListener{

    private RecyclerView mContactList;
    private FloatingActionButton makeContact;
    public PersonDao contactsInMemory = new PersonDaoImpl();
    public Context context = this;


    private WhiteListDisplay mAdapter = new WhiteListDisplay(this);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_white_list_page);

        mContactList = (RecyclerView) findViewById(R.id.whitelist);
        makeContact = (FloatingActionButton) findViewById(R.id.add_contact);
        makeContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContactList.getContext(), ContactPage.class);
                startActivity(intent);
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mContactList.setLayoutManager(layoutManager);
        mContactList.setHasFixedSize(false);
        mContactList.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }



    public void onListItemClick(int clickedItemIndex) {
        Person toEdit = this.mAdapter.contactsInMemory.get(clickedItemIndex);
        String toEditNumber = toEdit.getPhoneNumber();
        Intent intent = new Intent(context, ContactPage.class );
        intent.putExtra("number", toEditNumber);
        startActivity(intent);
        //TODO send second parameter startActivity(intent, option) where option is a bundled reference to the
        //TODO to-be-edited contacts current location in memory so that the edit view hints can be changed
        //TODO to the current firstname, lastname, and phonenumber of the contact (THIS IS OPTIONAL)
    }
}


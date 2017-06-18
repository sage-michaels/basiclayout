package com.example.sage.basiclayouts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

/**
 * Created by sage on 6/13/17.
 */

public class MainActivity extends AppCompatActivity implements WhiteListDisplay.ListItemClickListener {

    private static final int CONTACT_LIST_ITEMS = 100;
    private WhiteListDisplay mAdapter;
    private RecyclerView mContactList;
    private Toast mToast;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_white_list_display);

        mContactList = (RecyclerView) findViewById(R.id.whitelist);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mContactList.setLayoutManager(layoutManager);
        mContactList.setHasFixedSize(true);
        mAdapter = new WhiteListDisplay(CONTACT_LIST_ITEMS, this);
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

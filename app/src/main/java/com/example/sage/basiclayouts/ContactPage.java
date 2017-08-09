package com.example.sage.basiclayouts;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.example.sage.basiclayouts.dao.Person;
import com.example.sage.basiclayouts.dao.PersonDao;
import com.example.sage.basiclayouts.dao.PersonDaoImpl;

/**
 * Created by sage on 6/27/17.
 */

public class ContactPage extends AppCompatActivity{
    private EditText firstName;
    private EditText lastName;
    private EditText phoneNumber;
    private LinearLayout submitContact;
    private PersonDao contacts = new PersonDaoImpl();
    public Context context = this;



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_submission_page);
        final Intent intentThatCreatedThisPage = getIntent();


        firstName = (EditText) findViewById(R.id.firstNameInput);
        lastName = (EditText) findViewById(R.id.lastNameInput);
        phoneNumber = (EditText) findViewById(R.id.phoneNumberInput);
        submitContact = (LinearLayout) findViewById(R.id.submit);
        if (intentThatCreatedThisPage.hasExtra("number")) {
            String prevNumber = intentThatCreatedThisPage.getStringExtra("number");

            String path;
            try{
                path = contacts.getDataDir(context);
                contacts.setFolderPath(path);
            }catch(Exception e){
                e.printStackTrace();
            }


            Person contactToEdit = contacts.getByPhoneNumber(prevNumber);
            firstName.setText(contactToEdit.getFirstName());
            lastName.setText(contactToEdit.getLastName());
            phoneNumber.setText(contactToEdit.getPhoneNumber());
            //We are editing a contact so change Hints to be previous first/last names and number
        }
        submitContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fName = getFirstName();
                String lName = getLastName();
                String pNumber = getPhoneNumber();
                if ((fName.isEmpty()) || (lName.isEmpty()) || (pNumber.isEmpty())) {
                    Toast.makeText(ContactPage.this, "Please complete all requested fields", Toast.LENGTH_SHORT).show();
                    if (fName.isEmpty()) {
                        firstName.setBackgroundColor(Color.parseColor("#0fff0000"));
                    }if(!fName.isEmpty()){
                        firstName.setBackgroundColor(Color.parseColor("#00000000"));
                    }if(lName.isEmpty()){
                            lastName.setBackgroundColor(Color.parseColor("#0fff0000"));
                    }if (!lName.isEmpty()){
                        lastName.setBackgroundColor(Color.parseColor("#00000000"));
                    }if (pNumber.isEmpty()){
                        phoneNumber.setBackgroundColor(Color.parseColor("#0fff0000"));
                    }if(!pNumber.isEmpty()){
                        phoneNumber.setBackgroundColor(Color.parseColor("#00000000"));
                    }
                } else if (intentThatCreatedThisPage.hasExtra("number")) {
                    String prevNumber = intentThatCreatedThisPage.getStringExtra("number");
                    Person contactToEdit = contacts.getByPhoneNumber(prevNumber);
                    contactToEdit.setFirstName(fName);
                    contactToEdit.setLastName(lName);
                    contactToEdit.setPhoneNumber(pNumber);
                    finish();
                } else {
                    Person person = new Person();
                    person.setFirstName(fName);
                    person.setLastName(lName);
                    person.setPhoneNumber(pNumber);
                    if (contacts.save(person)) {
                        finish();
                    }else{
                        Toast.makeText(ContactPage.this, "Entered Phone Number already exists in WhiteList", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }


    public String getFirstName() {
        return this.firstName.getText().toString();
    }

    public String getLastName() {
        return this.lastName.getText().toString();
    }

    public String getPhoneNumber() {
        return this.phoneNumber.getText().toString();
    }

}
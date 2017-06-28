package com.example.sage.basiclayouts;

import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sage.basiclayouts.dao.Person;

/**
 * Created by sage on 6/27/17.
 */

public class ContactPage extends AppCompatActivity implements View.OnClickListener {
    private EditText firstName;
    private EditText lastName = (EditText) findViewById(R.id.lastNameInput);
    private EditText phoneNumber = (EditText) findViewById(R.id.phoneNumberInput);
    private Button submitContact;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editcontactpage);

        firstName = (EditText) findViewById(R.id.firstNameInput);
        lastName = (EditText) findViewById(R.id.lastNameInput);
        phoneNumber = (EditText) findViewById(R.id.phoneNumberInput);
        submitContact = (Button) findViewById(R.id.add_contact);
    }


    public String getFirstName(){
        return this.firstName.getText().toString();
    }
    public String getLastName(){
        return this.lastName.getText().toString();
    }
    public String getPhoneNumber(){
        return this.phoneNumber.getText().toString();
    }

    public ContactPage(){
        this.submitContact = (Button) findViewById(R.id.submit);
        this.firstName = (EditText) findViewById(R.id.firstNameInput);
        this.lastName = (EditText) findViewById(R.id.lastNameInput);
        this.phoneNumber = (EditText) findViewById(R.id.phoneNumberInput);
        this.submitContact.setOnClickListener(this);
    }



    public void onClick(View view){
        //TODO upon click store Person containing the firstName, lastName, phoneNumber in memory
        //TODO pass new person's address in memory to WhiteListDisplay using intents.
    }


}

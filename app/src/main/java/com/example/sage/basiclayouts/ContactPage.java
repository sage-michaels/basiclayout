package com.example.sage.basiclayouts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sage.basiclayouts.dao.Person;
import com.example.sage.basiclayouts.dao.PersonDaoImpl;

/**
 * Created by sage on 6/27/17.
 */

public class ContactPage extends AppCompatActivity {
    private EditText firstName;
    private EditText lastName;
    private EditText phoneNumber;
    private Button submitContact;
    PersonDaoImpl mDataSet =new PersonDaoImpl();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editcontactpage);
        Intent intentThatCreatedThisPage = getIntent();



        firstName = (EditText) findViewById(R.id.firstNameInput);
        lastName = (EditText) findViewById(R.id.lastNameInput);
        phoneNumber = (EditText) findViewById(R.id.phoneNumberInput);
        submitContact = (Button) findViewById(R.id.submit);
        if (intentThatCreatedThisPage.hasExtra("number")){
            String prevNumber = intentThatCreatedThisPage.getStringExtra("number");
            Person contactToEdit = mDataSet.getFromNumber(prevNumber);
            firstName.setHint(contactToEdit.getFirstName());
            lastName.setHint(contactToEdit.getLastName());
            phoneNumber.setHint(contactToEdit.getPhoneNumber());
            //We are editing a contact so change Hints to be previous first/last names and number
        }else{
            firstName.setHint("Enter First Name");
            lastName.setHint("Enter Last Name");
            phoneNumber.setHint("Enter Phone Number");
        }
        submitContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fName = getFirstName();
                String lName = getLastName();
                String pNumber = getPhoneNumber();
                if (fName != "" && lName != "" && pNumber != ""){
                    Person person = new Person(fName,lName,pNumber);
                    if (mDataSet.save(person)){
                        finish();
                }
                }else{
                    Toast.makeText(ContactPage.this, "Please complete all requested fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
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



}

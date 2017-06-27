package com.example.sage.basiclayouts.dao;


import java.util.Collection;
import java.util.Collections;

/**
 * Created by ido on 11/06/17.
 */

public class PersonDaoImpl implements  PersonDao {

    @Override
    public Collection<Person> load(){
        //TODO read from a json file and convert it to Person objects
        return Collections.singleton(new Person("","","")); // TODO change once implemented
    }


    @Override
    public boolean save(Person person){

        // TODO read all the Persons from the file and merge/add the new one and save it to the file
        return  false;
    }

    // TODO implement the deletion using both methods
    @Override
    public boolean delete(Person person){
        return  false;
    }

    @Override
    public boolean delete(String phoneNumber){
        return  false;
    }

}

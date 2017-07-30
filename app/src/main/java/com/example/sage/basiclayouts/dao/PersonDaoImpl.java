package com.example.sage.basiclayouts.dao;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by ido on 11/06/17.
 */

public class PersonDaoImpl implements  PersonDao {
    static Person sage = new Person("Sage","Michaels","0018587768475");
    static Person john = new Person("John","Smith","12345678910");
    static Person jane = new Person("Jane","Doe","10987654321");
    static Person carlos = new Person("Carlos","Mancia","188849339203");
    static Person larry = new Person("Larry", "David", "9948573920");
    static Person larryR = new Person("Larry","Rubin","99573663842");
    static Person danny = new Person("Danny","Lim","885930521");
    static Person lebron = new Person("Lebron", "James", "55473648");
    static Person luke = new Person("Luke","Skywalker","994739958");
    static Person[] adapterData = new Person[] {luke,sage, john,larry,jane,danny,carlos, larryR,lebron};
    static ArrayList<Person> mDataSet = new ArrayList(Arrays.asList(adapterData));

    @Override
    public ArrayList<Person> load(){
        //TODO read from a json file and convert it to Person objects
        return mDataSet; // TODO change once implemented

    }


    @Override
    public boolean save(Person person){
        for (Person check: mDataSet){
            if (check.getPhoneNumber()== person.getPhoneNumber()){
                return false;
            }
        }mDataSet.add(person);
        // TODO read all the Persons from the file and merge/add the new one and save it to the file
        return true;
    }

    // TODO implement the deletion using both methods
    @Override
    public boolean delete(Person person){
        for (Person check:mDataSet){
            if (person == check){
                mDataSet.remove(check);
                return true;
            }
        }
        return  false;
    }

    @Override
    public boolean delete(String phoneNumber){
        for (Person contact:mDataSet){
            if (contact.getPhoneNumber() == phoneNumber ){
                mDataSet.remove(contact);
                return true;
            }
        }
        return false;
    }

    public int size(){
        return mDataSet.size();
    }
    public Person get(int index){
        return mDataSet.get(index);
    }

    public Person getByPhoneNumber(String phoneNumber){
        for (Person potentialMatch:mDataSet){
            if (potentialMatch.getPhoneNumber().equals(phoneNumber)){
                return potentialMatch;
            }
        }return new Person("","","");
    }
}

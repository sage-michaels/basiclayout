package com.example.sage.basiclayouts.dao;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Ido Izaks on 16/07/2017.
 */

public class PersonDaoImpl implements PersonDao {


    private File file = null;

    private String folderPath = null;

    @Override
    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }

    private void init() {
        file = new File(folderPath, "personList.txt");
    }

    /**
     * Use this method in order to get the folder path in the internal storage.
     */
    @Override
    public String getDataDir(Context context) throws Exception {
        return context.getPackageManager()
                .getPackageInfo(context.getPackageName(), 0)
                .applicationInfo.dataDir;
    }

    @Override
    public ArrayList<Person> load() {

        if(file == null){
            init();
        }
        ArrayList<Person> persons = null;
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Person>>() {
        }.getType();

        try {
            FileReader fileReader = new FileReader(file);
            persons = gson.fromJson(fileReader, type);
            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (persons == null){
            return new ArrayList<Person>();
        }

        Collections.sort(persons, new NameComparator());
        return persons;
    }

    private boolean flash(ArrayList<Person> personsList) {
        if(file == null){
            init();
        }
        try {
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<Person>>() {
            }.getType();
            FileWriter fileWriter = new FileWriter(file);
            gson.toJson(personsList, type, fileWriter);
            fileWriter.close();
            return true;

        } catch (IOException e) {
            throw new FilesException("Error occurred while writing into the file", e);
            // TODO: 30/07/2017 Log it!
        }
    }

    @Override
    public boolean save(Person person) {
        if (person.getPhoneNumber() == null){
            throw new PersonDetailsException("NOTICE: A contact must have a phone number");
            // TODO: 30/07/2017 Log it?
        }
        if (person.getFirstName() == null && person.getLastName() == null){
            throw new PersonDetailsException("NOTICE: A contact must have a name");
            // TODO: 30/07/2017 Log it?
        }
        ArrayList<Person> personsList = load();
        for (Person p : personsList) {

            if (p.getPhoneNumber().equals(person.getPhoneNumber())) {
                personsList.remove(p);
                personsList.add(person);
                return flash(personsList);
            }
        }
        personsList.add(person);
        return flash(personsList);
    }

    @Override
    public boolean delete(Person person) {
        if (person.getPhoneNumber() == null ||
                (person.getFirstName() == null && person.getLastName() == null)){
            throw new PersonDetailsException("NOTICE: A contact must have a name and phone number");
        }
        return delete(person.getPhoneNumber());
    }

    @Override
    public boolean delete(String phoneNumber) {
        if (phoneNumber == null){
            throw new PersonDetailsException("Please enter a phone number to delete");
        }
        ArrayList<Person> personsList = load();
        ArrayList<Person> personsToDelete = new ArrayList<>();
        if (personsList == null){
            return true;
        }

        for (Person person : personsList) {
            if (person.getPhoneNumber() != null &&
                    person.getPhoneNumber().equals(phoneNumber)) {
                personsToDelete.add(person);
            }
        }

        if (personsToDelete.size() == 0){
            return false;
        }
        personsList.removeAll(personsToDelete);
        return flash(personsList);
    }

    @Override
    public Person getByPhoneNumber(String phoneNumber){

        for (Person person : load()){
            if (person.getPhoneNumber().equals(phoneNumber)){
                return person;
            }
        }
        // The number wasn't found
        throw new PersonDetailsException("The number doesn't belong to any person");
    }

}

package com.example.sage.basiclayouts.dao;

import java.util.ArrayList;
import java.util.Collection;


public interface PersonDao {

    void setFolderPath(String folderPath);

    ArrayList<Person> load();

    boolean save(Person person);

    boolean delete(Person person);

    boolean delete(String phoneNumber);

    Person getByPhoneNumber(String phoneNumber);

}

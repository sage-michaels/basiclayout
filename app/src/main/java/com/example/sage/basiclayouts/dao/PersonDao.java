package com.example.sage.basiclayouts.dao;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collection;


public interface PersonDao {

    void setFolderPath(String folderPath);

    String getDataDir(Context context) throws Exception;

    ArrayList<Person> load();

    boolean save(Person person);

    boolean delete(Person person);

    boolean delete(String phoneNumber);

    Person getByPhoneNumber(String phoneNumber);

}

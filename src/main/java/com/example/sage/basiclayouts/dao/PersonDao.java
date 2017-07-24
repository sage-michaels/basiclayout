package com.example.sage.basiclayouts.dao;

import java.util.Collection;

/**
 * Created by ido on 11/06/17.
 */

public interface PersonDao {
    Collection<Person> load();

    boolean save(Person person);

    // TODO implement the deletion using both methods
    boolean delete(Person person);

    boolean delete(String phoneNumber);

    Person getByPhoneNumber(String phoneNumber);
}

package com.example.sage.basiclayouts.dao;

import java.util.Comparator;

/**
 * Created by Ido Izaks on 30/07/2017.
 */

public class NameComparator implements Comparator<Person> {

    public int compare(Person person1, Person person2){
        String name1;
        String name2;

        name1 = person1.getFirstName() + person1.getLastName();
        name2 = person2.getFirstName() + person2.getLastName();

        if (person1.getFirstName() == null){
            name1 = person1.getLastName();
        }
        if (person2.getFirstName() == null){
            name2 = person2.getLastName();
        }
        if (person1.getLastName() == null){
            name1 = person1.getFirstName();
        }
        if (person2.getLastName() == null){
            name2 = person2.getFirstName();
        }
        return String.CASE_INSENSITIVE_ORDER.compare(name1, name2);
    }

}

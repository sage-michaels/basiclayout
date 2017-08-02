package com.example.sage.basiclayouts;

import com.example.sage.basiclayouts.dao.Person;
import com.example.sage.basiclayouts.dao.PersonDaoImpl;
import com.example.sage.basiclayouts.dao.PersonDetailsException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import junit.framework.Assert;

import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Ido Izaks on 16/07/2017.
 */

public class PersonDaoTest {

    public static final String FOLDER_PATH = "c:\\tests";
    public static final String FILE_NAME = FOLDER_PATH + "\\personList.txt";
    private PersonDaoImpl personDao = new PersonDaoImpl();


    public void buildFile() {

        try {
            personDao = new PersonDaoImpl();
            Collection<Person> persons = new ArrayList<>();
            Type type = new TypeToken<Collection<Person>>() {
            }.getType();
            Gson gson = new GsonBuilder().create();
            FileWriter writer = new FileWriter(FILE_NAME);
            gson.toJson(persons, type, writer);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void loadEmptyTest() throws Exception {
//        new File(FILE_NAME);
        buildFile();
        personDao.setFolderPath(FOLDER_PATH);
        Collection<Person> persons = personDao.load();
        Assert.assertNotNull(persons);
        Assert.assertEquals(0, persons.size());
    }

    @Test
    public void deleteLegalPersonThatIsInFile() throws PersonDetailsException {
        buildFile();
        personDao.setFolderPath(FOLDER_PATH);

        Person person = new Person();
        person.setLastName("LastName");
        person.setPhoneNumber("phoneNumber");
        personDao.save(person);

        boolean result = personDao.delete(person);
        Assert.assertTrue(result);

        List<Person> personsCollection = personDao.load();
        Assert.assertFalse(personsCollection.contains(person));
    }

    @Test
    public void deletePersonByNumberNotInFile() throws PersonDetailsException {
        buildFile();
        personDao.setFolderPath(FOLDER_PATH);

        boolean result = personDao.delete("Number");
        Assert.assertFalse(result);
    }

    @Test
    public void deletePersonWithFirstNameNotInFile() throws PersonDetailsException {
        buildFile();
        personDao.setFolderPath(FOLDER_PATH);

        Person person = new Person();
        person.setPhoneNumber("Number");
        person.setFirstName("FirstName");
        boolean result = personDao.delete(person);
        Assert.assertFalse(result);
    }

    @Test
    public void deletePersonWithLastNameNotInFile() throws PersonDetailsException {
        buildFile();
        personDao.setFolderPath(FOLDER_PATH);

        Person person = new Person();
        person.setPhoneNumber("Number");
        person.setLastName("LastName");
        boolean result = personDao.delete(person);
        Assert.assertFalse(result);
    }

    @Test(expected = PersonDetailsException.class)
    public void deleteByNullNumber() throws PersonDetailsException {
        buildFile();
        personDao.setFolderPath(FOLDER_PATH);

        String nullString = null;
        personDao.delete(nullString);
    }

    @Test(expected = PersonDetailsException.class)
    public void deleteEmptyPerson() throws PersonDetailsException {
        buildFile();
        personDao.setFolderPath(FOLDER_PATH);

        Person person = new Person();
        personDao.delete(person);
    }

    @Test(expected = PersonDetailsException.class)
    public void deletePersonWithoutAnyName() throws PersonDetailsException {
        buildFile();
        personDao.setFolderPath(FOLDER_PATH);

        // Tries to delete a person without name (illegal)
        Person person = new Person();
        person.setPhoneNumber("Number");
        personDao.delete(person);
    }

    @Test
    public void loadSinglePersonTest() throws Exception {
        buildFile();
        personDao.setFolderPath(FOLDER_PATH);

        // Creates and sets values for a new Person
        Person p = new Person();
        p.setFirstName("FirstName");
        p.setLastName("LastName");
        p.setPhoneNumber("PhoneNumber");

        // Assures that the person is loaded as it should be, with the expected values
        personDao.save(p);
        List<Person> persons = personDao.load();
        Assert.assertEquals(1, persons.size());

        Person loadedPerson = persons.iterator().next();
        Assert.assertEquals("FirstName", loadedPerson.getFirstName());
        Assert.assertEquals("LastName", loadedPerson.getLastName());
        Assert.assertEquals("PhoneNumber", loadedPerson.getPhoneNumber());
    }

    @Test(expected = PersonDetailsException.class)
    public void saveEmptyPerson() throws Exception {
        buildFile();
        personDao.setFolderPath(FOLDER_PATH);

        personDao.save(new Person());
    }

    @Test
    public void saveDifferentPersonsWithSamePhoneNumber() throws Exception{
        buildFile();
        personDao.setFolderPath(FOLDER_PATH);

        Person a = new Person();
        a.setFirstName("a");
        a.setPhoneNumber("1234");

        Person b = new Person();
        b.setFirstName("b");
        b.setPhoneNumber("1234");

        personDao.save(a);
        personDao.save(b);
        Collection<Person> persons = personDao.load();

        // Assures that there is 1 person in the collection
        Assert.assertEquals(1, persons.size());

        // Assures that the second person is the one that was saved
        Assert.assertEquals(b, persons.iterator().next());
    }

    @Test
    public void changePhoneNumberAfterSavingTest() throws Exception{
        buildFile();
        personDao.setFolderPath(FOLDER_PATH);

        Person a = new Person();
        a.setFirstName("FirstName");
        a.setPhoneNumber("PhoneNumber");
        personDao.save(a);

        a.setPhoneNumber("AnotherPhoneNumber");
        personDao.save(a);
        List<Person> persons = personDao.load();

        // Checks if there are now 2 different persons
        Assert.assertEquals(2, persons.size());

        Iterator<Person> personIterator = persons.iterator();
        Person personA = personIterator.next();
        Person personB = personIterator.next();

        // Check that they are different objects
        Assert.assertNotSame(personA, personB);

        // Checks that deleting the one of them won't delete the other one
        personDao.delete(personA);
        persons = personDao.load();

        Assert.assertEquals(1, persons.size());
        Assert.assertTrue(persons.contains(personB));
    }

    @Test
    public void sortTest(){
        buildFile();
        personDao.setFolderPath(FOLDER_PATH);

        Person a = new Person();
        a.setFirstName("FirstName");
        a.setLastName("LastName");
        a.setPhoneNumber("PhoneNumber");

        Person b = new Person();
        b.setFirstName("Great");
        b.setPhoneNumber("123");

        Person c = new Person();
        c.setLastName("Cool");
        c.setPhoneNumber("123456");

        personDao.save(a);
        personDao.save(b);
        personDao.save(c);

        ArrayList<Person> persons = personDao.load();
        Assert.assertEquals(c,persons.get(0));
        Assert.assertEquals(a, persons.get(1));
        Assert.assertEquals(b, persons.get(2));
    }

    @Test
    public void getPersonByPhoneNumber(){
        buildFile();
        personDao.setFolderPath(FOLDER_PATH);
        String aNumber = "1234";
        String bNumber = "9876";

        Person a = new Person();
        a.setPhoneNumber(aNumber);
        a.setFirstName("FirstName");

        Person b = new Person();
        b.setPhoneNumber(bNumber);
        b.setLastName("LastName");

        personDao.save(a);
        personDao.save(b);

        Assert.assertEquals(a, personDao.getByPhoneNumber(aNumber));
        Assert.assertEquals(b, personDao.getByPhoneNumber(bNumber));
    }

    @Test(expected = PersonDetailsException.class)
    public void getPersonByPhoneNumberThatIsNotBelongToPerson() throws PersonDetailsException{
        buildFile();
        personDao.setFolderPath(FOLDER_PATH);

        personDao.getByPhoneNumber("1234");
    }

}


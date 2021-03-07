package com.proquest.interview.phonebook.dao;

import java.util.List;

import com.proquest.interview.phonebook.Person;
/**
 * Interface class contract for a DAO implementation
 * 
 */
public interface PhoneBookDAO {
	
	public Person findPersonByName(String name);
	public List<Person> findPersonsByName(String name);
	public Person findPerson(Person person);
	public int savePerson(Person newPerson);
	public List<Person> findAll();

}

package com.proquest.interview.phonebook.service;

import java.util.List;

import com.proquest.interview.phonebook.Person;

/**
 * Interface contract for a PhoneBook service implementation
 * 
 */
public interface PhoneBookService {
	
	public Person createPerson(String name, String phoneNumber, String address);
	public Person findPersonByNameInList(List<Person> people, String name);
	public Person findPersonInList(List<Person> people, Person personToFind);
	public Person findPersonByName(String name);
	public List<Person> findPersonsByName(String name);
	public int savePerson(Person newPerson);
	public List<Person> findAll();

}

package com.proquest.interview.phonebook;

import java.util.List;

/**
 * Interface contract for a PhoneBook implementation
 * 
 */
public interface PhoneBook {
	
	public Person findPerson(String firstName, String lastName);
	public void addPerson(Person newPerson);
	public Person createPerson(String name, String phoneNumber, String address);
	public List<Person> getPhoneBook();
	public void printPerson(Person person);
	public void printPersons();
	

	
	
}

package com.proquest.interview.phonebook.view;

import java.util.List;

import com.proquest.interview.phonebook.Person;
/**
 * Interface contract for a PhoneBook View implementation
 * 
 */
public interface PhoneBookView {

	public void printPerson(Person person);	
	public void printPersons(List<Person> persons);
}

package com.proquest.interview.phonebook.service;

import java.util.Iterator;
import java.util.List;

import com.proquest.interview.phonebook.Person;
import com.proquest.interview.phonebook.dao.PhoneBookDAO;

/**
 * Service class will hold methods for creating, saving, or finding phone book records.
 * @author Clifford
 *
 */
public class PhoneBookServiceImpl implements PhoneBookService {

	private PhoneBookDAO dao;
	
	/**
	 * Constructor which accepts constructor injected values.
	 * @param 
	 */
	public PhoneBookServiceImpl(PhoneBookDAO dao) {
		this.dao = dao;
	}
	
	public Person createPerson(String name, String phoneNumber, String address) {
		
		return new Person(name, phoneNumber, address);
	}


	/**
	 * Find the given person, of the given name and surname, in the given list. 
	 * If the person exists in the given list then the Person object, for that 
	 * person, in that list is returned.
	 * 
	 * @param people
	 * @param name
	 * @param surname
	 * @return
	 */
	public Person findPersonByNameInList(List<Person> people, String name) {
	
		return findPersonInList(people, new Person(name, null, null));
	}
	
	/**
	 * Find the given person in the given list. If the person exists in the given list then 
	 * the Person object, for that person, in that list is returned.
	 * 
	 * @param people
	 * @param personToFind
	 * @return
	 */
	public Person findPersonInList(List<Person> people, Person personToFind) {
		
		Person foundPerson = null;
		
		Iterator<Person> iter = people.iterator();
		
		while(iter.hasNext()) {
			Person person = iter.next();
			
			if(person.getName().equalsIgnoreCase(personToFind.getName())) {
				foundPerson = person;
				break;
			}
		}
	
		return foundPerson;
	}


	@Override
	public Person findPersonByName(String name) {
		
		return dao.findPersonByName(name);
	}


	@Override
	public List<Person> findPersonsByName(String name) {

		return dao.findPersonsByName(name);
	}


	@Override
	public int savePerson(Person newPerson) {

		return dao.savePerson(newPerson);
	}


	@Override
	public List<Person> findAll() {

		return dao.findAll();
	}
	
	
	
}

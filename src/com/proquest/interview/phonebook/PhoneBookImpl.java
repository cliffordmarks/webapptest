package com.proquest.interview.phonebook;

import java.util.ArrayList;
import java.util.List;


import com.proquest.interview.phonebook.config.PhoneBookConfig;
import com.proquest.interview.phonebook.service.PhoneBookService;
import com.proquest.interview.phonebook.view.PhoneBookView;
import com.proquest.interview.util.DatabaseUtil;

/**
 * This is the phonebook implementation class. Which uses constructor injection 
 * to get abstracted references to objects which perform certain tasks.
 * The class via composition reads and writes data to the underlying data store
 * (and the in memory list of phonebook entries) and uses a view to display the data.
 * 
 * @author Clifford
 *
 */
public class PhoneBookImpl implements PhoneBook {
	
	//set to private to only allow this class impl to edit the phone book
	private List<Person> people;

	private PhoneBookService phoneBookService;
	private PhoneBookView view;
	
	
	
	/**
	 * Constructor which accepts constructor injected values.
	 * @param 
	 */
	public PhoneBookImpl(PhoneBookView  view, PhoneBookService service) {
		this.view = view;
		this.phoneBookService = service;
		initPhoneBookList();
	}
	

	/**
	 * Initialise the list for first use. This method will create a list and 
	 * retrieve any Persons from the underlying data store.
	 * 
	 */
	private void initPhoneBookList() {
		
		people = new ArrayList<Person>();

		for(Person person : phoneBookService.findAll()) {
			people.add(person);
		}
	}
	
	/**
	 * Access method for phone book. Return a list of Person objects or an empty list.
	 * This method will also initialise the in memory list if this is the first use of the list,
	 * thus only creating it when needed.
	 * @return
	 */
	public List<Person> getPhoneBook() {
		return people;
	}	
	
	/**
	 * Create the person object using the given values...
	 */
	public Person createPerson(String name, String phoneNumber, String address) {
		//create a person using delegate helper...
		return phoneBookService.createPerson("John Smith", "(248) 123-4567", "1234 Sand Hill Dr, Royal Oak, MI");
	}

	
	/**
	 * Adds a new Person record to an existing list. If newPerson is null the list is not updated.
	 * 
	 * @param newPerson
	 */
	@Override
	public void addPerson(Person newPerson) {
		
		if(newPerson != null) {
			phoneBookService.savePerson(newPerson);
			people.add(newPerson);
		}
	}

	
	/**
	 * Attempts to find the Person object, using the given firstName and lastName,
	 * first within the list of person's and if unsuccessful attempts to find the 
	 * person in the underlying data store.
	 * 
	 * @param firstName
	 * @param lastName
	 */
	@Override
	public Person findPerson(String firstName, String lastName) {

		Person foundPerson = null;
		
		foundPerson = phoneBookService.findPersonByNameInList(people, firstName + " " + lastName);

		if(foundPerson == null) {
			foundPerson = phoneBookService.findPersonByName(firstName + " " + lastName);	
		}
			
		return foundPerson;
	}

	/**
	 * Uses the view to print out a a given person record 
	 */
	@Override
	public void printPerson(Person person) {
	
		view.printPerson(person);
	}
	
	/**
	 * Uses the view to print out the phonebook list, held in this 
	 * implementation, of person records.
	 */
	@Override
	public void printPersons() {
		view.printPersons(this.getPhoneBook());
	}

	
	public static void main(String[] args) {
		DatabaseUtil.initDB();  //You should not remove this line, it creates the in-memory database

		PhoneBookConfig phoneBookConfig = new PhoneBookConfig();
		
		/* TODO: create person objects and put them in the PhoneBook and database
		 * John Smith, (248) 123-4567, 1234 Sand Hill Dr, Royal Oak, MI
		 * Cynthia Smith, (824) 128-8758, 875 Main St, Ann Arbor, MI
		*/ 
		// TODO: print the phone book out to System.out
		// TODO: find Cynthia Smith and print out just her entry
		// TODO: insert the new person objects into the database
		
		try {
			PhoneBookImpl phoneBookImpl = 
					new PhoneBookImpl(phoneBookConfig.getPhoneBookView(), 
							phoneBookConfig.getPhoneBookService(phoneBookConfig.getPhoneBookDAO(DatabaseUtil.getConnection())));
			
			//create a person using delegate helper...
			Person person = null;
			person = phoneBookImpl.createPerson("John Smith", "(248) 123-4567", "1234 Sand Hill Dr, Royal Oak, MI");
			phoneBookImpl.addPerson(person);
			person = phoneBookImpl.createPerson("Cynthia Smith", "(824) 128-8758", "875 Main St, Ann Arbor, MI");
			phoneBookImpl.addPerson(person);
			
			//Print out the current phonebook entries...
			phoneBookImpl.printPersons();
	
			//Find a person by first name...
			Person foundPerson = phoneBookImpl.findPerson("Cynthia", "Smith");
			
			if(foundPerson != null) {
				//Print out the found persons record...
				phoneBookImpl.printPerson(foundPerson);
			}
			
			phoneBookImpl.createPerson("Will Smith", "0800 800 8080", "1 hollywood boulevard, Hollywood, US");
	
		}
		catch(Exception ex) {
			//
			ex.printStackTrace();
		}
	}
}

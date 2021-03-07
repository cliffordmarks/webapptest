package com.proquest.interview.phonebook.view;

import java.util.List;

import com.proquest.interview.phonebook.Person;

public class PhoneBookViewImpl implements PhoneBookView {

	/**
	 * Prints out the given Person object's name, phone number and address values
	 * on one line to the console..
	 * 
	 * @param person
	 */
	@Override
	public void printPerson(Person person) {
		
		if(person != null) {
			StringBuilder builder = new StringBuilder();
			builder.append("Name: ");
			builder.append(person.getName());
			builder.append(" ");
			builder.append("Phone Number: ");
			builder.append(person.getPhoneNumber());
			builder.append(" ");
			builder.append("Address: ");
			builder.append(person.getAddress());
			builder.append("\n");
			
			System.out.print(builder.toString());
		}
	}

	/**
	 * Prints out a list of person object's i.e. name, phone number and address values
	 * on one line to the console..
	 * 
	 * @param persons - a list containing Person objects
	 */
	@Override
	public void printPersons(List<Person> persons) {
		
		if(persons != null && !persons.isEmpty()) {

			System.out.println("Phone book entries...");
			System.out.println();
			
			for(Person person : persons) 
				printPerson(person);
			}
	}


}

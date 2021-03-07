package com.proquest.interview.phonebook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.proquest.interview.phonebook.Person;

/**
 * DAO implementation, providing the methods agreed in contract with PhoneBookDAO interface
 * @author Clifford
 *
 */
public class PhoneBookDAOImpl implements PhoneBookDAO {

	private Connection connection;
	private static final String PHONEBOOK_TBL_NAME = "PHONEBOOK";
	private static final String PHONEBOOK_FIELD_NAME = "NAME";
	private static final String PHONEBOOK_FIELD_PHONENUMBER = "PHONENUMBER";
	private static final String PHONEBOOK_FIELD_ADDRESS = "ADDRESS";
	
	/**
	 * Constructor
	 * @param connection
	 * @throws Exception
	 */
	public PhoneBookDAOImpl(Connection connection) throws Exception {
		
		if(connection == null)
			throw new Exception("DB connection should not be null!!");
		this.connection = connection;
	}

	/**
	 * Returns a list containing Person objects matching the given name or 
	 * an empty list if no Person objects are retrieved from underlying data store.
	 * 
	 * @param name - the name of a person to find e.g "Sean Combs"
	 */
	@Override
	public List<Person> findPersonsByName(String name) {
		List<Person> list = new ArrayList<Person>();
		
		try {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select NAME, PHONENUMBER, ADDRESS from " + PHONEBOOK_TBL_NAME);
			queryBuilder.append(" Where Upper(NAME) = " + name.toUpperCase());
			
			PreparedStatement statement = connection.prepareStatement(queryBuilder.toString());
			
			ResultSet rs = statement.executeQuery();

			while(rs.next()) {
			
				Person person = new Person();
				person.setName(rs.getString(1));
				person.setPhoneNumber(rs.getString(2));
				person.setAddress(rs.getString(3));
	
				list.add(person);
			}
		}
		catch(SQLException ex) {
			//printout an errors usually you woul add to logger.
			ex.printStackTrace();
		}
		
		return list;
	}

	/**
	 * Returns a Person object matching the given name or null if no matching 
	 * Person is retrieved from underlying data store.
	 * 
	 * @param name - the name of a person to find e.g "Sean Combs"
	 */
	@Override
	public Person findPersonByName(String name) {
		// TODO Auto-generated method stub
		
		Person personFound = null;
		
		try {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select NAME, PHONENUMBER, ADDRESS from " + PHONEBOOK_TBL_NAME);
			queryBuilder.append(" Where Upper(NAME) = ?");

			PreparedStatement statement = connection.prepareStatement(queryBuilder.toString());
			statement.setString(1, name.toUpperCase());

			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				personFound = new Person();
				personFound.setName(rs.getString(1));
				personFound.setPhoneNumber(rs.getString(2));
				personFound.setAddress(rs.getString(3));
			}
			
		}
		catch(SQLException ex) {
			//printout an errors usually you woul add to logger.
			ex.printStackTrace();
		}
		
		return personFound;
	}

	/**
	 * Convenience method.
	 * Returns a Person object matching the given name or null if no matching 
	 * Person is retrieved from underlying data store.
	 * 
	 * @param name - the name of a person to find e.g "Sean Combs"
	 */
	@Override
	public Person findPerson(Person person) {
		
		return findPersonByName(person.getName());
	}

	/**
	 * Saves the given Person to the underlying data store.
	 * 
	 * @param newPerson- Person object representing a record to be saved to the PHONEBOOK_TBL_NAME
	 */
	@Override
	public int savePerson(Person newPerson) {

		Person savedPerson = null;
		int result = 0;
		
		try {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("insert into " + PHONEBOOK_TBL_NAME);
			queryBuilder.append("(NAME, PHONENUMBER, ADDRESS)");
			queryBuilder.append("values (?, ?, ?)");
			
			PreparedStatement statement = connection.prepareStatement(queryBuilder.toString());
			statement.setString(1, PHONEBOOK_FIELD_NAME);
			statement.setString(2, PHONEBOOK_FIELD_PHONENUMBER);
			statement.setString(3, PHONEBOOK_FIELD_ADDRESS);

			result = statement.executeUpdate();
			commit();
		}
		catch(SQLException ex) {
			//printout any errors usually you would add to logger.
			ex.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * Convenience method.
	 * Commit the last transaction to the db
	 */
	public void commit() {
		try {
			this.connection.commit();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}


	/**
	 * Returns a list containing all Person objects found in the underlying data store.
	 * Or an empty list if no records were retrieved..
	 * 
	 */
	@Override
	public List<Person> findAll() {
		List<Person> persons = new ArrayList<Person>();
		
		try {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select NAME, PHONENUMBER, ADDRESS from " + PHONEBOOK_TBL_NAME);
			
			PreparedStatement statement = connection.prepareStatement(queryBuilder.toString());
			
			Person person = null;
			
			ResultSet rs = statement.executeQuery();
            
			while (rs.next()) {
            	person = new Person();
            	person.setName(rs.getString(1));
            	person.setPhoneNumber(rs.getString(2));
            	person.setAddress(rs.getString(3));

            	persons.add(person);
            }
			
		}
		catch(SQLException ex) {
			//printout an errors usually you would add to logger.
			ex.printStackTrace();
		}
		
		return persons;
	}
	
}

package com.proquest.interview.phonebook;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.proquest.interview.phonebook.config.PhoneBookConfig;
import com.proquest.interview.phonebook.dao.PhoneBookDAO;
import com.proquest.interview.phonebook.service.PhoneBookService;
import com.proquest.interview.phonebook.view.PhoneBookView;
import com.proquest.interview.util.DatabaseUtil;


@ContextConfiguration(classes=PhoneBookConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class PhoneBookImplTest {

	@Rule
	public final SystemOutRule log = new SystemOutRule().enableLog();

	
	private PhoneBook phoneBook;
	@Autowired
	private PhoneBookView view;
	@Autowired
	private PhoneBookService service;

	
	@BeforeClass
    public static void init() throws Exception {

    	//init the db
    	DatabaseUtil.initDB();
	}
    
    /**
     * Setup before each test 
     * @throws Exception
     */
    @Before
    public void setup() throws Exception {

    	try {
        	
			phoneBook = new PhoneBookImpl(view, service);
		}
		catch(Exception ex) {
			System.out.println("Exception thrown while trying to initialise PhoneBookDAOImpl. Exception: " + ex.getMessage());
			ex.printStackTrace();
			throw ex;
		}

    }
    
    @AfterClass
	public static void tearDown() {
    	
    	try {
	    	Connection connection = DatabaseUtil.getConnection();
	    	if(connection != null) 
	    		connection.close();
    	}
    	catch(Exception ex) {
    		ex.printStackTrace();
    	}
    }
    
	
	@Test
	public void shouldAddFindPerson() {
		Person person = phoneBook.createPerson("John Smith", "(248) 123-4567", "1234 Sand Hill Dr, Royal Oak, MI");
		phoneBook.addPerson(person);
		
		Person anotherPerson = phoneBook.createPerson("Tupac Shakur", "(700) 128-8758", "111 Mansion, Ann Arbor, LA");
		phoneBook.addPerson(anotherPerson);
		
		assertNotNull("Person was not added!!", phoneBook.findPerson("John", "Smith"));
	}
	

	@Test
	public void shouldPrintOutPerson() {
		
		Person person = phoneBook.createPerson("John Smith", "(248) 123-4567", "1234 Sand Hill Dr, Royal Oak, MI");
		phoneBook.printPerson(person);
		
		assertEquals("Name: John Smith Phone Number: (248) 123-4567 "
				+ "Address: 1234 Sand Hill Dr, Royal Oak, MI\n",
				log.getLog());
	}

}

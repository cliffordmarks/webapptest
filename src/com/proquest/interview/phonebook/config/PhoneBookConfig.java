package com.proquest.interview.phonebook.config;

import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.proquest.interview.phonebook.PhoneBook;
import com.proquest.interview.phonebook.PhoneBookImpl;
import com.proquest.interview.phonebook.dao.PhoneBookDAO;
import com.proquest.interview.phonebook.dao.PhoneBookDAOImpl;
import com.proquest.interview.phonebook.service.PhoneBookService;
import com.proquest.interview.phonebook.service.PhoneBookServiceImpl;
import com.proquest.interview.phonebook.view.PhoneBookView;
import com.proquest.interview.phonebook.view.PhoneBookViewImpl;
import com.proquest.interview.util.DatabaseUtil;

/**
 * This class will hold the config for this application. And create the spring bean references
 * in the spring context. 
 * @author Clifford
 *
 */

//@ComponentScan("com.proquest.interview.phonebook")
@Configuration
public class PhoneBookConfig {
	
	@Bean
	public PhoneBookDAO getPhoneBookDAO() throws Exception {
		
		return new PhoneBookDAOImpl(DatabaseUtil.getConnection());
	}

	@Bean
	public PhoneBookDAO getPhoneBookDAO(Connection connection) throws Exception {
		
		return new PhoneBookDAOImpl(connection);
	}

	@Bean
	public PhoneBookView getPhoneBookView() {
		return new PhoneBookViewImpl();
	}

	@Bean
	public PhoneBookService getPhoneBookService(PhoneBookDAO dao) {
		return new PhoneBookServiceImpl(dao);
	}

	@Autowired
	@Bean
	public PhoneBook getPhoneBook(PhoneBookView view, PhoneBookService service) {
		return new PhoneBookImpl(view, service);
	}

}

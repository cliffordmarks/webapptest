package com.proquest.interview.phonebook;

/**
 * Person Pojo/Bean
 * @author Clifford
 *
 */
public class Person {
	private String name;
	private String phoneNumber;
	private String address;

	/**
	 * No args constructor
	 */
	public Person() {
	}
	
	/**
	 * Convenience Constructor...
	 * @param name
	 * @param phoneNumber
	 * @param address
	 */
	public Person(String name, String phoneNumber, String address) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}

package edu.nau.CS477.Contacts;

import java.io.Serializable;

public class ContactObject implements Serializable{

	private static final long serialVersionUID = -8386098666918666048L;
	private int id;
	private String fullName = null;
	private String firstName = null;
	private String lastName = null;
	private String emailAddr = null;
	
	/*Should we include a string array for permissioning?
	 * Something where we'd allow you to edit your contact and add
	 * in absolute path to folders that you want to grant them access to
	 */
	
	public ContactObject(){
	
	}
	
	public ContactObject(String firstName,String lastName, String emailAddr ){
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEmailAddr(emailAddr);
		this.setFullName(firstName + " " + lastName);
		
	}
	
	public ContactObject(int id,String firstName,String lastName, String emailAddr ){
		this.setId(id);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEmailAddr(emailAddr);
		this.setFullName(firstName + " " + lastName);
		
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddr() {
		return emailAddr;
	}

	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}

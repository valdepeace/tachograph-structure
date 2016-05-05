package com.thingtrack.parse;

import java.util.ArrayList;
import java.util.Date;

import org.tacografo.file.cardblockdriver.CardIdentification;
import org.tacografo.file.vublock.subblock.VuCardIWRecord;

public class Driver {
	private String name;
	private String email = null;
	private boolean active = true;
	private String birthday = null;
	private ArrayList<com.thingtrack.parse.CardNumber> cardNumber;
	private String description;

	public Driver(){

	}
	public Driver(VuCardIWRecord vc){
		this.name=vc.getCardHolderName().getHolderFirstNames()+" "+vc.getCardHolderName().getHolderSurname();
		this.active=true;
		this.cardNumber=new ArrayList();
		com.thingtrack.parse.CardNumber number=new com.thingtrack.parse.CardNumber();				
		number.setNumber(vc.getFullCardNumber().getCardNumber().getDriverIdentification()+
						vc.getFullCardNumber().getCardNumber().getDrivercardRenewalIndex()+
						vc.getFullCardNumber().getCardNumber().getDrivercardReplacementIndex());
		number.setActive(true);
		this.cardNumber.add(number);
		this.description="Create Driver with file vehicle";

	}

	public Driver(CardIdentification ci){
		this.name=ci.getDriverCardHolderIdentification().getCardHolderName().getHolderFirstNames()+" "+
				ci.getDriverCardHolderIdentification().getCardHolderName().getHolderSurname();
		this.birthday=ci.getDriverCardHolderIdentification().getCardHolderBirthDate();
		this.active=true;
		this.cardNumber=new ArrayList();
		com.thingtrack.parse.CardNumber number=new com.thingtrack.parse.CardNumber();
		number.setNumber(ci.getCardNumber().getDriverIdentification()+ci.getCardNumber().getDrivercardRenewalIndex()+ci.getCardNumber().getDrivercardReplacementIndex());
		number.setActive(true);
		this.cardNumber.add(number);
		this.description="Create Driver with file driver";
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the cardNumber
	 */
	public ArrayList<CardNumber> getCardNumber() {
		return cardNumber;
	}

	/**
	 * @param cardNumber the cardNumber to set
	 */
	public void setCardNumber(ArrayList<CardNumber> cardNumber) {
		this.cardNumber = cardNumber;
	}

	/**
	 * @return the descriptcion
	 */
	public String getDescriptcion() {
		return description;
	}

	/**
	 * @param descriptcion the descriptcion to set
	 */
	public void setDescriptcion(String descriptcion) {
		this.description = descriptcion;
	}
	
}

package com.dubhe.tests;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;
	private transient Address address;
	private Person person;

	// setters and getters

	private void writeObject(ObjectOutputStream outpuStream)
			throws IOException {
		outpuStream.defaultWriteObject();
		outpuStream.writeObject(getAddress().getHouseNumber());
	}

	private void readObject(ObjectInputStream inputStream)
			throws ClassNotFoundException, IOException {
		inputStream.defaultReadObject();
		Integer houseNumber = (Integer) inputStream.readObject();
		Address address = new Address();
		address.setHouseNumber(houseNumber);
		this.setAddress(address);
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}

class Address {
	private int houseNumber;

	public int getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}
}
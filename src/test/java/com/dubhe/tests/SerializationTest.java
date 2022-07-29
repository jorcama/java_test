package com.dubhe.tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.jupiter.api.Test;

public class SerializationTest {

	@Test
	public void whenSerializingAndDeserializing_ThenObjectIsTheSame()
			throws IOException, ClassNotFoundException {
		Person person = new Person();
		person.setAge(20);
		person.setName("Joe");
		person.setHeight(18);

		ByteArrayOutputStream byteArrayOuputStream = new ByteArrayOutputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOuputStream);
		objectOutputStream.writeObject(person);
		objectOutputStream.flush();
		objectOutputStream.close();

		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOuputStream.toByteArray());
		ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
		Person p2 = (Person) objectInputStream.readObject();
		objectInputStream.close();

		assertTrue(p2.getAge() == person.getAge());
		assertTrue(p2.getName().equals(person.getName()));
		assertFalse(p2.getHeight() == person.getHeight());
	}

	@Test
	public void whenCustomSerializingAndDeserializing_ThenObjectIsTheSame()
			throws IOException, ClassNotFoundException {
		Person p = new Person();
		p.setAge(20);
		p.setName("Joe");

		Address a = new Address();
		a.setHouseNumber(1);

		Employee e = new Employee();
		e.setPerson(p);
		e.setAddress(a);

		FileOutputStream fileOutputStream = new FileOutputStream("target/yourfile.txt");
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		//Serialize
		objectOutputStream.writeObject(e);
		objectOutputStream.flush();
		objectOutputStream.close();

		FileInputStream fileInputStream = new FileInputStream("target/yourfile.txt");
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		//Deserialize
		Employee e2 = (Employee) objectInputStream.readObject();
		objectInputStream.close();

		assertTrue(e2.getPerson().getAge() == e.getPerson().getAge());
		assertTrue(e2.getAddress().getHouseNumber() == e.getAddress().getHouseNumber());
	}
}

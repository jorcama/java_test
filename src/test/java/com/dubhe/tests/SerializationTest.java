package com.dubhe.tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

import org.junit.jupiter.api.Test;

public class SerializationTest
{

	@Test
	public void whenSerializingAndDeserializing_ThenObjectIsTheSame()
			throws IOException, ClassNotFoundException
	{
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
			throws IOException, ClassNotFoundException
	{
		Person p = new Person();
		p.setAge(20);
		p.setName("Joe");

		Address a = new Address();
		a.setHouseNumber(1);

		Employee e = new Employee();
		e.setPerson(p);
		e.setAddress(a);

		FileOutputStream fileOutputStream = new FileOutputStream("target/yourfile.bin");
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		//Serialize
		objectOutputStream.writeObject(e);
		objectOutputStream.flush();
		objectOutputStream.close();

		FileInputStream fileInputStream = new FileInputStream("target/yourfile.bin");
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		//Deserialize
		Employee e2 = (Employee) objectInputStream.readObject();
		objectInputStream.close();

		assertTrue(e2.getPerson().getAge() == e.getPerson().getAge());
		assertTrue(e2.getAddress().getHouseNumber() == e.getAddress().getHouseNumber());
	}

	@Test
	public void serializeAndDeserialezeFilesWithFileWriter()
			throws IOException, ClassNotFoundException
	{

		FileOutputStream fileOutputStream = new FileOutputStream("target/yourfile.bin");
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

		for (int i = 0; i < 3; i++)
		{
			Path tempFile = Files.createTempFile("~dubhe", ".tmp");
			File file = new File(tempFile.toString());
			file.deleteOnExit();
			String str = "Hello Dubhe" + i;
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile.toString()));
			writer.write(str);
			writer.close();

			//Serialize
			objectOutputStream.writeObject(Files.readAllBytes(tempFile));

		}

		objectOutputStream.flush();
		objectOutputStream.close();

		FileInputStream fileInputStream = new FileInputStream("target/yourfile.bin");

		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		for (int i = 0; i < 3; i++)
		{
			byte[] bytes = (byte[]) objectInputStream.readObject();
			Path tempFileResult = Files.createTempFile("~dubhe", ".tmp");
			File file = new File(tempFileResult.toString());
			file.deleteOnExit();
			try (FileOutputStream stream = new FileOutputStream(tempFileResult.toString()))
			{
				stream.write(bytes);
			}

		}
		objectInputStream.close();

	}

	@Test
	public void serializeAndDeserialezeFilesWithBuffer()
			throws IOException, ClassNotFoundException
	{
		final int FILE_CHUNCK_SIZE = 16 * 1024;
		FileOutputStream fileOutputStream = new FileOutputStream("target/yourfile.bin");
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

		int fileNumber = 3;
		objectOutputStream.writeInt(fileNumber);

		byte[] buffer = new byte[FILE_CHUNCK_SIZE];

		for (int i = 0; i < fileNumber; i++)
		{
			//Create temporal file
			Path tempFile = Files.createTempFile("~dubhe", ".tmp");
			String str = "Hello Dubhe" + i;
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile.toString()));
			writer.write(str);
			writer.close();

			//Serialize
			File file = new File(tempFile.toString());
			file.deleteOnExit();
			objectOutputStream.writeObject(file.getName());
			//File Size
			long bytesToWrite = file.length();
			objectOutputStream.writeLong(bytesToWrite);
			if (bytesToWrite > 0)
			{
				//File content
				try (FileInputStream fis = new FileInputStream(file))
				{
					int count = 0;
					while ((count = fis.read(buffer)) > -1)
					{
						objectOutputStream.write(buffer, 0, count);
					}
				}
			}
		}

		objectOutputStream.flush();
		objectOutputStream.close();

		FileInputStream fileInputStream = new FileInputStream("target/yourfile.bin");

		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

		fileNumber = objectInputStream.readInt();
		if (fileNumber > 0)
		{
			for (int i = 0; i < fileNumber; i++)
			{

				String fileName = (String) objectInputStream.readObject();
				long bytesToRead = objectInputStream.readLong();

				Path tempFileResult = Files.createTempFile("~dubhedos", ".tmp");
				File file = new File(tempFileResult.toString());
				file.deleteOnExit();
				if (bytesToRead > 0)
				{
					try (FileOutputStream fos = new FileOutputStream(tempFileResult.toString()))
					{
						while (bytesToRead > 0)
						{

							int length = buffer.length;
							if (bytesToRead < length)
							{
								length = (int) bytesToRead;
							}
							int count = objectInputStream.read(buffer, 0, length);
							if (count < 0)
							{
								throw new EOFException();
							}
							bytesToRead -= count;

							fos.write(buffer, 0, count);
						}
					}
				}
			}
		}
		objectInputStream.close();

	}
}

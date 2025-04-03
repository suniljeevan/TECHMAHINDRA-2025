package org.cms.MODEL;
import java.io.*;
public class TestSerilization {
	public static void main(String[] args) throws Exception{
         File file=new File("C:\\Techmahindra\\studentser.dat");
         FileOutputStream fw=new FileOutputStream(file);
         System.out.println("file created");
         ObjectOutputStream out = new ObjectOutputStream(fw);
         System.out.println("object bytestream ");
         Student s=new Student("1005","sunil","s@gmail.com","bengaluru",2022);
         out.writeObject(s);
         System.out.println("object serialized");
	}

}

package com.github.interview.reflection;

import java.lang.reflect.Field;

public class ToStrongOverride {

	public static void main(String[] args) {
		Person person = new Person();
		person.setAddress("不知道哪里");
		person.setAge(1000);
		person.setName("doctor who");
		System.out.println(person);

	}

}

class Person{
	private String name;
	private String address;
	private int age;
	
	
	
	
	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getAddress() {
		return address;
	}




	public void setAddress(String address) {
		this.address = address;
	}




	public int getAge() {
		return age;
	}




	public void setAge(int age) {
		this.age = age;
	}




	@Override
	public String toString(){
		StringBuffer stringBuffer = new StringBuffer(512);
		Field[] fields = this.getClass().getDeclaredFields();
		stringBuffer.append("{");
		for (Field field : fields) {
			try {
				stringBuffer.append(field.getName()).append(":").append(field.get(this));
				stringBuffer.append(",");
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		stringBuffer.deleteCharAt(stringBuffer.lastIndexOf(","));
		stringBuffer.append("}");
		return stringBuffer.toString();
		
	}
}

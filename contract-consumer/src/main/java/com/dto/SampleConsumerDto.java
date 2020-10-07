package com.dto;

public class SampleConsumerDto
{
	private int id;

	public String fname;

	public String lname;

	public Double salary;

	public String gender;

	public SampleConsumerDto(int id, String fName, String lName)
	{
		this.id = id;
		this.fname = fName;
		this.lname = lName;
//		this.salary = salary;
//		this.gender = gender;
	}

	public Double getSalary()
	{
		return salary;
	}

	public void setSalary(Double salary)
	{
		this.salary = salary;
	}

	public String getGender()
	{
		return gender;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}


	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getFname()
	{
		return fname;
	}

	public void setFname(String fname)
	{
		this.fname = fname;
	}

	public String getLname()
	{
		return lname;
	}

	public void setLname(String lname)
	{
		this.lname = lname;
	}


}

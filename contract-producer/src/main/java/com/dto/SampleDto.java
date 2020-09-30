package com.dto;

public class SampleDto
{

	public Integer id;

	public String fname;

	public String lname;

	public Double salary;

	public String gender;

	public SampleDto(int id, String fName, String lName, double salary, String gender)
	{
		this.id = id;
		this.fname = fName;
		this.lname = lName;
		this.gender = gender;
		this.salary = salary;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
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


}

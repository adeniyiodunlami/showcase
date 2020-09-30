package com.dto;

public class SampleConsumerDto
{
	private int id;

	public String fname;

	public String lname;

	public SampleConsumerDto(int id, String fname, String lname)
	{
		this.id = id;
		this.fname = fname;
		this.lname = lname;
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

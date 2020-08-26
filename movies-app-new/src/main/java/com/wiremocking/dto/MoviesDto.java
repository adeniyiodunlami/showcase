package com.wiremocking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoviesDto
{
	public String cast;
	public long movie_id;
	public String name;
	public LocalDate release_date;
	public Integer year;
}

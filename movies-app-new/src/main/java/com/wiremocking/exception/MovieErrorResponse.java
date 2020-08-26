package com.wiremocking.exception;

import org.springframework.web.reactive.function.client.WebClientResponseException;

public class MovieErrorResponse extends RuntimeException
{
	public MovieErrorResponse(String statusText, WebClientResponseException wecr)
	{
		super(statusText,wecr);
	}

	public MovieErrorResponse(Exception wecr)
	{
		super(wecr);
	}
}

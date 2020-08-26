package com.wiremocking.service;

import com.wiremocking.constants.MoviesConstant;
import com.wiremocking.dto.MoviesDto;
import com.wiremocking.exception.MovieErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Slf4j
public class MoviesRestClient
{
	private WebClient webClient;

	public MoviesRestClient(WebClient webClient)
	{
		this.webClient = webClient;
	}

	public List<MoviesDto> rtrvAllMovies(){

		return webClient.get().uri(MoviesConstant.GET_ALL_VALUES)
			.retrieve()
			.bodyToFlux(MoviesDto.class)
			.collectList()
			.block();
	}

	public MoviesDto rtrvById(Integer movieId)
	{
		try
		{
			return webClient.get().uri(MoviesConstant.GET_BY_ID, movieId)
				.retrieve()
				.bodyToMono(MoviesDto.class)
				.block();

		}
		catch (WebClientResponseException wecr)
		{
			log.error("WebClientResponseException in rtrvMovieByid" + wecr.getResponseBodyAsString());
			throw new MovieErrorResponse(wecr.getStatusText(), wecr);
		}
		catch (Exception e)
		{
			log.error("WebClientResponseException in rtrvMovieByid" + e);
			throw new MovieErrorResponse(e);
		}
	}

		public List<MoviesDto> movienameList(String movieName){
		String rtrvByName=	UriComponentsBuilder.fromUriString(MoviesConstant.GET_BY_NAME)
				.queryParam("movie_name", movieName)
					.buildAndExpand()
					.toUriString();

		try{
			return webClient.get().uri(rtrvByName)
				.retrieve()
				.bodyToFlux(MoviesDto.class)
				.collectList()
				.block();
		}catch (WebClientResponseException wecr)
		{
			log.error("WebClientResponseException in rtrvMovieByname" + wecr.getResponseBodyAsString());
			throw new MovieErrorResponse(wecr.getStatusText(), wecr);
		}
		catch (Exception e)
		{
			log.error("WebClientResponseException in rtrvMovieByName" + e);
			throw new MovieErrorResponse(e);
		}

	}

	public List<MoviesDto> movieByYearList(Integer year){
		String rtrvByName=	UriComponentsBuilder.fromUriString(MoviesConstant.GET_BY_YEAR)
			.queryParam("year", year)
			.buildAndExpand()
			.toUriString();

		try{
			return webClient.get().uri(rtrvByName)
				.retrieve()
				.bodyToFlux(MoviesDto.class)
				.collectList()
				.block();
		}catch (WebClientResponseException wecr)
		{
			log.error("WebClientResponseException in movieByYearList" + wecr.getResponseBodyAsString());
			throw new MovieErrorResponse(wecr.getStatusText(), wecr);
		}
		catch (Exception e)
		{
			log.error("WebClientResponseException in movieByYearList" + e);
			throw new MovieErrorResponse(e);
		}

	}

	public MoviesDto addMovie(MoviesDto movie)
	{

		try
		{
			return webClient.post().uri(MoviesConstant.ADD_MOVIE)
				.syncBody(movie)
				.retrieve()
				.bodyToMono(MoviesDto.class)
				.block();
		}
		catch (WebClientResponseException wecr)
		{
			log.error("WebClientResponseException in addMovie" + wecr.getResponseBodyAsString());
			throw new MovieErrorResponse(wecr.getStatusText(), wecr);
		}
		catch (Exception e)
		{
			log.error("WebClientResponseException in addMovie" + e);
			throw new MovieErrorResponse(e);
		}

	}

	public MoviesDto updateMovie(Integer movieId, MoviesDto movie)
	{

		try
		{
			return webClient.put().uri(MoviesConstant.GET_BY_ID, movieId)
				.syncBody(movie)
				.retrieve()
				.bodyToMono(MoviesDto.class)
				.block();
		}
		catch (WebClientResponseException wecr)
		{
			log.error("WebClientResponseException in updateMovie" + wecr.getResponseBodyAsString());
			throw new MovieErrorResponse(wecr.getStatusText(), wecr);
		}
		catch (Exception e)
		{
			log.error("WebClientResponseException in updateMovie" + e);
			throw new MovieErrorResponse(e);
		}

	}

	public String deleteMovie(Integer movieID)
	{

		try
		{
			return webClient.delete().uri(MoviesConstant.GET_BY_ID, movieID)
				.retrieve()
				.bodyToMono(String.class)
				.block();
		}
		catch (WebClientResponseException wecr)
		{
			log.error("WebClientResponseException in deleteMovie" + wecr.getResponseBodyAsString());
			throw new MovieErrorResponse(wecr.getStatusText(), wecr);
		}
		catch (Exception e)
		{
			log.error("WebClientResponseException in deleteMovie" + e);
			throw new MovieErrorResponse(e);
		}

	}

}




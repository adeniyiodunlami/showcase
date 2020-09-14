package com.service;

import com.github.jenspiegsa.wiremockextension.ConfigureWireMock;
import com.github.jenspiegsa.wiremockextension.InjectServer;
import com.github.jenspiegsa.wiremockextension.WireMockExtension;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.core.Options;
import com.intuit.karate.junit5.Karate;
import com.wiremocking.constants.MoviesConstant;
import com.wiremocking.dto.MoviesDto;
import com.wiremocking.exception.MovieErrorResponse;
import com.wiremocking.service.MoviesRestClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@ExtendWith(WireMockExtension.class)

public class MovieKarateTest
{
//	MoviesRestClient movieRestClient;
//	WebClient webClient;
//
//	@InjectServer
//	WireMockServer wireMockServer;
//
//	@ConfigureWireMock
//	Options options = wireMockConfig().port(8088)
//						.notifier(new ConsoleNotifier(true));
//
//	@BeforeEach
//	void setUp(){
//		int port = wireMockServer.port();
//		String baseurl = String.format("http://localhost:%s/", port);
//		//String baseurl = String.format("http://localhost:8081/", port);
//		webClient = WebClient.create(baseurl);
//		movieRestClient = new MoviesRestClient(webClient);
//	}

//	@Karate.Test
//	Karate testSample() {
//		return Karate.run("movie").relativeTo(getClass());
//	}

//	@Karate.Test
//	Karate testTags() {
//		return Karate.run("tags").tags("@second").relativeTo(getClass());
//	}
//
//	@Karate.Test
//	Karate testFullPath() {
//		return Karate.run("classpath:karate/tags.feature").tags("@first");
//	}
//
//	@Karate.Test
//	Karate testAll() {
//		return Karate.run().relativeTo(getClass());
//	}

//	@Test
//	void rtrvAllMovies(){
//		//given
//		stubFor(get(anyUrl())
//			.willReturn(WireMock.aResponse()
//			.withStatus(HttpStatus.OK.value())
//				.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//			.withBodyFile("all-movies.json")));
//
//		//when block
//		List<MoviesDto> movieList = movieRestClient.rtrvAllMovies();
//		System.out.println("movieList ::"+movieList);
//
//		//then block
//		assertTrue(movieList.size()>0);
//	}
//
//	@Test
//	void rtrvMovies_matchesURL(){
//		//given
//		stubFor(get(urlPathEqualTo(MoviesConstant.GET_ALL_VALUES))
//			.willReturn(WireMock.aResponse()
//				.withStatus(HttpStatus.OK.value())
//				.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//				.withBodyFile("all-movies.json")));
//
//		//when block
//		List<MoviesDto> movieList = movieRestClient.rtrvAllMovies();
//		System.out.println("movieList ::"+movieList);
//
//		//then block
//		assertTrue(movieList.size()>0);
//	}
//
//	@Test
//	void rtrvMovieByID(){
//		//given block
//		Integer id = 9;
//		//stubFor(get(urlPathEqualTo("/movieservice/v1/movie/1"))
//			stubFor(get(urlMatching("/movieservice/v1/movie/[0-9]"))
//			.willReturn(WireMock.aResponse()
//				.withStatus(HttpStatus.OK.value())
//				.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//				.withBodyFile("movie.json")));
//
//		//when
//		MoviesDto movieDetails = movieRestClient.rtrvById(id);
//		System.out.println("movieDetails ::"+movieDetails);
//
//		//then
//		assertEquals("Batman Begins", movieDetails.getName());
//	}
//
//	@Test
//	void rtrvMovieByID_NotFound(){
//		//given block
//		Integer id = 100;
//
//		//when
//		Assertions.assertThrows(MovieErrorResponse.class, () -> movieRestClient.rtrvById(id));
//	}
//
//	@Test
//	void rtrvMovieByName(){
//		//given block
//		String movieName = "Avengers";
//
//		//when
//		List<MoviesDto> movieList = movieRestClient.movienameList(movieName);
//
//		//then
//		assertEquals(4, movieList.size());
//	}
//
//	@Test
//	void rtrvMovieYear(){
//		//given block
//		Integer year = 2012;
//
//		//when
//		List<MoviesDto> movieList = movieRestClient.movieByYearList(year);
//
//		//then
//		assertEquals(2, movieList.size());
//	}
//
//	@Test
//	void rtrvMovieByYear_NotFound(){
//		//given block
//		Integer year = 2030;
//
//		//when
//		Assertions.assertThrows(MovieErrorResponse.class, () -> movieRestClient.movieByYearList(year));
//	}
//
//	@Test
//	void addMovie(){
//		//given block
//		MoviesDto movie = new MoviesDto("Goku, Vegeta", 0, "Dragon Ball Z", LocalDate.of(2020, 8, 01),2019);
//
//		//when
//		MoviesDto newMovie = movieRestClient.addMovie(movie);
//		System.out.println("newMovie ::"+newMovie);
//
//		//then
//		assertTrue(newMovie.getMovie_id()!= 0);
//	}
//
////	@Test
////	void addMovie_NOTWorks(){
////		//given block
////		Integer year = 2030;
////
////		//when
////		Assertions.assertThrows(MovieErrorResponse.class, () -> movieRestClient.movieByYearList(year));
////	}
//
//	@Test
//	void updateMovie(){
//		//given block
//		Integer id = 11;
//		String cast = "Gohan";
//		MoviesDto movie = new MoviesDto(cast, 0, null, null,null);
//
//		//when
//		MoviesDto updtdMovie = movieRestClient.updateMovie(id, movie);
//		System.out.println("newMovie ::"+updtdMovie);
//
//		//then
//		assertTrue(updtdMovie.getCast().contains("Gohan"));
//	}
//
//	@Test
//	void updtMovie_NotFound(){
//		//given block
//		//given block
//		Integer id = 100;
//		String cast ="Goku, Vegeta, Gohan";
//		MoviesDto movie = new MoviesDto(cast, 0, null, null,null);
//
//		//when
//		Assertions.assertThrows(MovieErrorResponse.class, () -> movieRestClient.updateMovie(id, movie));
//	}
//
//	@Test
//	void deleteMovie(){
//		//given block
//		MoviesDto movie = new MoviesDto("Goku, Vegeta, Krillin", 0, "Dragon Ball Kai", LocalDate.of(2020, 8, 01),2019);
//		MoviesDto newMovie = movieRestClient.addMovie(movie);
//
//
//		//when
//		Long longVal = new Long(newMovie.getMovie_id());
//		String deleteMovie = movieRestClient.deleteMovie(longVal.intValue());
//		System.out.println("newMovie ::"+deleteMovie);
//
//		//then
//		String message = "Movie Deleted Successfully";
//		assertEquals(deleteMovie, message);
//	}
}

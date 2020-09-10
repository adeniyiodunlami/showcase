package com.service;

import com.github.jenspiegsa.wiremockextension.ConfigureWireMock;
import com.github.jenspiegsa.wiremockextension.InjectServer;
import com.github.jenspiegsa.wiremockextension.WireMockExtension;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.core.Options;
import com.wiremocking.constants.MoviesConstant;
import com.wiremocking.dto.MoviesDto;
import com.wiremocking.exception.MovieErrorResponse;
import com.wiremocking.service.MoviesRestClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
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

@ExtendWith(WireMockExtension.class)
public class MovieRestClientTest
{
	MoviesRestClient movieRestClient;
	WebClient webClient;

	@InjectServer
	WireMockServer wireMockServer;

	@ConfigureWireMock
	Options options = wireMockConfig().port(8088)
						.notifier(new ConsoleNotifier(true));

	@BeforeEach
	void setUp(){
		int port = wireMockServer.port();
		String baseurl = String.format("http://localhost:%s/", port);
		//String baseurl = String.format("http://localhost:8081/", port);
		System.out.println("baseUrl ::"+baseurl);
		webClient = WebClient.create(baseurl);
		movieRestClient = new MoviesRestClient(webClient);
	}

	@Test
	void rtrvAllMovies(){
		//given
		stubFor(get(anyUrl())
			.willReturn(WireMock.aResponse()
			.withStatus(HttpStatus.OK.value())
				.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			.withBodyFile("all-movies.json")));

		//when block
		List<MoviesDto> movieList = movieRestClient.rtrvAllMovies();
		System.out.println("movieList ::"+movieList);

		//then block
		assertTrue(movieList.size()>0);
	}

	@Test
	void rtrvMovies_matchesURL(){
		//given
		stubFor(get(urlPathEqualTo(MoviesConstant.GET_ALL_VALUES))
			.willReturn(WireMock.aResponse()
				.withStatus(HttpStatus.OK.value())
				.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.withBodyFile("all-movies.json")));

		//when block
		List<MoviesDto> movieList = movieRestClient.rtrvAllMovies();
		System.out.println("movieList ::"+movieList);

		//then block
		assertTrue(movieList.size()>0);
	}

	@Test
	void rtrvMovieByID(){
		//given block
		Integer id = 9;
		//stubFor(get(urlPathEqualTo("/movieservice/v1/movie/1"))
			stubFor(get(urlMatching("/movieservice/v1/movie/[0-9]"))
			.willReturn(WireMock.aResponse()
				.withStatus(HttpStatus.OK.value())
				.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.withBodyFile("movie.json")));

		//when
		MoviesDto movieDetails = movieRestClient.rtrvById(id);
		System.out.println("movieDetails ::"+movieDetails);

		//then
		assertEquals("Batman Begins", movieDetails.getName());
	}

	@Test
	void rtrvMovieByID_NotFound(){
		//given block
		Integer id = 100;

		//when
		Assertions.assertThrows(MovieErrorResponse.class, () -> movieRestClient.rtrvById(id));
	}

	@Test
	void rtrvMovieByName(){
		//given block
		String movieName = "Avengers";
		stubFor(get(urlEqualTo(MoviesConstant.GET_BY_NAME+"?movie_name="+movieName))
			.willReturn(WireMock.aResponse()
				.withStatus(HttpStatus.OK.value())
				.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.withBodyFile("rtrv_Movie.json")));

		//when
		List<MoviesDto> movieList = movieRestClient.movienameList(movieName);
		System.out.println("rtrvMovie ::"+movieList);

		//then
		assertEquals(4, movieList.size());
	}

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

//	@Test
//	void rtrvMovieByYear_NotFound(){
//		//given block
//		Integer year = 2030;
//
//		//when
//		Assertions.assertThrows(MovieErrorResponse.class, () -> movieRestClient.movieByYearList(year));
//	}

	@Test
	void addMovie(){
		//given block
		MoviesDto movie = new MoviesDto("Goku, Vegeta", null, "Dragon Ball Z Kai", LocalDate.of(2020, 11, 01),2020);
		stubFor(post(urlEqualTo(MoviesConstant.ADD_MOVIE))
			.withRequestBody(matchingJsonPath(("$.name"), equalTo("Dragon Ball Z Kai")))
			.withRequestBody(matchingJsonPath(("$.cast"), equalTo("Goku, Vegeta")))
			.willReturn(WireMock.aResponse()
				.withStatus(HttpStatus.OK.value())
				.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.withBodyFile("add_movie.json")));
		//when
		MoviesDto newMovie = movieRestClient.addMovie(movie);
		System.out.println("newMovie ::"+newMovie);

		//then
		assertTrue(newMovie.getMovie_id()!= 0);
	}

//	@Test
//	void addMovie_NOTWorks(){
//		//given block
//		Integer year = 2030;
//
//		//when
//		Assertions.assertThrows(MovieErrorResponse.class, () -> movieRestClient.movieByYearList(year));
//	}

	@Test
	void updateMovie(){
		//given block
		Integer id = 11;
		String cast = "Gohan";
		MoviesDto movie = new MoviesDto(cast, null, null, null,null);
		stubFor(put(urlPathMatching("/movieservice/v1/movie/[0-9]+"))
			.withRequestBody(matchingJsonPath(("$.cast"), containing(cast)))
			.willReturn(WireMock.aResponse()
				.withStatus(HttpStatus.OK.value())
				.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.withBodyFile("update_movie.json")));

		//when
		MoviesDto updtdMovie = movieRestClient.updateMovie(id, movie);
		System.out.println("updtdMovie ::"+updtdMovie);

		//then
		assertTrue(updtdMovie.getCast().contains(cast));
	}

//	@Test
//	void updtMovie_NotFound(){
//		//given block
//		//given block
//		Integer id = 100;
//		String cast ="Goku, Vegeta, Gohan";
//		MoviesDto movie = new MoviesDto(cast, null, null, null,null);
//
//		//when
//		Assertions.assertThrows(MovieErrorResponse.class, () -> movieRestClient.updateMovie(id, movie));
//	}

	@Test
	void deleteMovie(){
		//given block
		MoviesDto movie = new MoviesDto("Goku", null, "DBGT", LocalDate.of(2020, 11, 01),2020);
		stubFor(post(urlEqualTo(MoviesConstant.ADD_MOVIE))
			.withRequestBody(matchingJsonPath(("$.name"), equalTo("DBGT")))
			.withRequestBody(matchingJsonPath(("$.cast"), equalTo("Goku")))
			.willReturn(WireMock.aResponse()
				.withStatus(HttpStatus.OK.value())
				.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.withBodyFile("add_movie.json")));

		MoviesDto newMovie = movieRestClient.addMovie(movie);

		stubFor(delete(urlMatching("/movieservice/v1/movie/[0-9]+"))
			.willReturn(WireMock.aResponse()
				.withStatus(HttpStatus.OK.value())
				.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.withBody("Movie Deleted Successfully")));

		//when
		Long longVal = new Long(newMovie.getMovie_id());
		String deleteMovie = movieRestClient.deleteMovie(longVal.intValue());
		System.out.println("newMovie ::"+deleteMovie);

		//then
		String message = "Movie Deleted Successfully";
		assertEquals(deleteMovie, message);
	}
}

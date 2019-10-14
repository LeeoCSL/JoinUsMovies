package com.lr.welcomejoinus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lr.welcomejoinus.Models.Movie;
import com.lr.welcomejoinus.Models.Search;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MoviesResponse {


    private String totalResults;


    private boolean Response;

    private List<Movie> Search;


    public MoviesResponse(){}

    public MoviesResponse(String totalResults, boolean Response, List<Movie> Search) {
        this.totalResults = totalResults;
        this.Response = Response;
        Search = Search;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public boolean getResponse() {
        return Response;
    }

    public void setResponse(boolean Response) {
        this.Response = Response;
    }

    public List<Movie> getSearch() {
        return Search;
    }

    public void setSearch(List<Movie> search) {
        Search = search;
    }

    @Override
    public String toString() {
        return "MoviesResponse aaa{" +
                " totalResults=" + totalResults +
                " Response=" + Response +
                ", Search='" + Search + '\'' +
                '}';
    }
}
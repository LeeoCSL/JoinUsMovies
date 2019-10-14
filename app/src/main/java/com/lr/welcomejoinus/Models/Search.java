package com.lr.welcomejoinus.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Search {
    @JsonProperty("Search")
    private List<Movie> Search;

    @JsonProperty("totalResults")
    private String totalResults;

    @JsonProperty("Response")
    private String Response;

    @JsonProperty("Error")
    private String Error;


    public Search() {
    }

    public Search(List<Movie> search) {
        this.Search = search;
    }

    public List<Movie> getSearch() { return this.Search; }

    public void setSearch(List<Movie> search) { this.Search = search; }


    public String getTotalResults() { return this.totalResults; }

    public void setTotalResults(String totalResults) { this.totalResults = totalResults; }



    public String getResponse() { return this.Response; }

    public void setResponse(String Response) { this.Response = Response; }

    public String getError() {
        return Error;
    }

    public void setError(String error) {
        Error = error;
    }
}
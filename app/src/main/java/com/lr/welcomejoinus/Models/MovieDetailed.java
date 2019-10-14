package com.lr.welcomejoinus.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MovieDetailed {

    @JsonProperty("Title")
    String Title;

    @JsonProperty("Year")
    String Year;

    @JsonProperty("Rated")
    String Rated;

    @JsonProperty("Released")
    String Released;

    @JsonProperty("Runtime")
    String Runtime;

    @JsonProperty("Genre")
    String Genre;

    @JsonProperty("Director")
    String Director;

    @JsonProperty("Writer")
    String Writer;

    @JsonProperty("Actors")
    String Actors;

    @JsonProperty("Plot")
    String Plot;

    @JsonProperty("Language")
    String Language;

    @JsonProperty("Country")
    String Country;

    @JsonProperty("Awards")
    String Awards;

    @JsonProperty("Poster")
    String Poster;

    @JsonProperty("Ratings")
    List<Ratings> Ratings;

    @JsonProperty("Metascore")
    String Metascore;

    @JsonProperty("imdbRating")
    String imdbRating;

    @JsonProperty("imdbVotes")
    String imdbVotes;

    @JsonProperty("ImdbID")
    String ImdbID;

    @JsonProperty("Type")
    String Type;

    @JsonProperty("DVD")
    String DVD;

    @JsonProperty("BoxOffice")
    String BoxOffice;

    @JsonProperty("Production")
    String Production;

    @JsonProperty("Website")
    String Website;

    @JsonProperty("Response")
    String Response;

    public MovieDetailed() {
    }

    public MovieDetailed(String title, String year, String rated, String released, String runtime, String genre, String director, String writer, String actors, String plot, String language, String country, String awards, String poster, List<com.lr.welcomejoinus.Models.Ratings> ratings, String metascore, String imdbRating, String imdbVotes, String imdbID, String type, String DVD, String boxOffice, String production, String website, String response) {
        Title = title;
        Year = year;
        Rated = rated;
        Released = released;
        Runtime = runtime;
        Genre = genre;
        Director = director;
        Writer = writer;
        Actors = actors;
        Plot = plot;
        Language = language;
        Country = country;
        Awards = awards;
        Poster = poster;
        Ratings = ratings;
        Metascore = metascore;
        this.imdbRating = imdbRating;
        this.imdbVotes = imdbVotes;
        ImdbID = imdbID;
        Type = type;
        this.DVD = DVD;
        BoxOffice = boxOffice;
        Production = production;
        Website = website;
        Response = response;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getRated() {
        return Rated;
    }

    public void setRated(String rated) {
        Rated = rated;
    }

    public String getReleased() {
        return Released;
    }

    public void setReleased(String released) {
        Released = released;
    }

    public String getRuntime() {
        return Runtime;
    }

    public void setRuntime(String runtime) {
        Runtime = runtime;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String director) {
        Director = director;
    }

    public String getWriter() {
        return Writer;
    }

    public void setWriter(String writer) {
        Writer = writer;
    }

    public String getActors() {
        return Actors;
    }

    public void setActors(String actors) {
        Actors = actors;
    }

    public String getPlot() {
        return Plot;
    }

    public void setPlot(String plot) {
        Plot = plot;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getAwards() {
        return Awards;
    }

    public void setAwards(String awards) {
        Awards = awards;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }

    public String getMetascore() {
        return Metascore;
    }

    public void setMetascore(String metascore) {
        Metascore = metascore;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public String getImdbID() {
        return ImdbID;
    }

    public void setImdbID(String imdbID) {
        ImdbID = imdbID;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getDVD() {
        return DVD;
    }

    public void setDVD(String DVD) {
        this.DVD = DVD;
    }

    public String getBoxOffice() {
        return BoxOffice;
    }

    public void setBoxOffice(String boxOffice) {
        BoxOffice = boxOffice;
    }

    public String getProduction() {
        return Production;
    }

    public void setProduction(String production) {
        Production = production;
    }

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String website) {
        Website = website;
    }

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }

    @Override
    public String toString() {
        return "MovieDetailed{" +
                "Title='" + Title + '\'' +
                ", Year='" + Year + '\'' +
                ", Rated='" + Rated + '\'' +
                ", Released='" + Released + '\'' +
                ", Runtime='" + Runtime + '\'' +
                ", Genre='" + Genre + '\'' +
                ", Director='" + Director + '\'' +
                ", Writer='" + Writer + '\'' +
                ", Actors='" + Actors + '\'' +
                ", Plot='" + Plot + '\'' +
                ", Language='" + Language + '\'' +
                ", Country='" + Country + '\'' +
                ", Awards='" + Awards + '\'' +
                ", Poster='" + Poster + '\'' +
                ", Metascore='" + Metascore + '\'' +
                ", imdbRating='" + imdbRating + '\'' +
                ", imdbVotes='" + imdbVotes + '\'' +
                ", ImdbID='" + ImdbID + '\'' +
                ", Type='" + Type + '\'' +
                ", DVD='" + DVD + '\'' +
                ", BoxOffice='" + BoxOffice + '\'' +
                ", Production='" + Production + '\'' +
                ", Website='" + Website + '\'' +
                ", Response='" + Response + '\'' +
                '}';
    }
}

class Ratings {

    @JsonProperty("Source")
    String Source;

    @JsonProperty("Value")
    String Value;



    public String getSource() {
        return Source;
    }

    public void setSource(String source) {
        Source = source;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    @Override
    public String toString() {
        return "Ratings{" +
                "Source='" + Source + '\'' +
                ", Value='" + Value + '\'' +
                '}';
    }
}
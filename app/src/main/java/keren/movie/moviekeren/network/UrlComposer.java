package keren.movie.moviekeren.network;

import android.net.Uri;

import keren.movie.moviekeren.BuildConfig;

/**
 * Url composer for themoviedb.org APIs
 * Documentation can be found at https://developers.themoviedb.org/3/movies
 *
 * @author hendrawd on 6/23/16
 */

public class UrlComposer {

    public static final String BASE_URL = "http://api.themoviedb.org/3/";

    /**
     * Compose API url for movie list based on category
     *
     * @param category String
     * @param page     page you want to get
     * @return Movie list url String
     * example: "http://api.themoviedb.org/3/movie/popular?api_key=x"
     */
    public static String getMovieUrl(String category, int page) {
        return Uri.parse(BASE_URL).buildUpon()
                .appendPath("movie")
                .appendPath(category)
                .appendQueryParameter("api_key", BuildConfig.THE_MOVIE_DB_API)
                .appendQueryParameter("page", page + "")
                .build()
                .toString();
    }

    /**
     * Compose API url for genre list
     *
     * @return Genre list url String
     * example: "http://api.themoviedb.org/3/genre/movie/list?api_key=x&language=en-US"
     */
    public static String getGenreListUrl() {
        return Uri.parse(BASE_URL).buildUpon()
                .appendPath("genre")
                .appendPath("movie")
                .appendPath("list")
                .appendQueryParameter("api_key", BuildConfig.THE_MOVIE_DB_API)
                .appendQueryParameter("language", "en-US")
                .build()
                .toString();
    }

    /**
     * Compose API url for movie list based on search query of movieName
     *
     * @param movieName part of the name of the movie
     * @param page      page you want to get
     * @return Movie list url String with search query
     */
    public static String getSearchUrl(String movieName, int page) {
        return Uri.parse(BASE_URL).buildUpon()
                .appendPath("search")
                .appendPath("movie")
                .appendQueryParameter("api_key", BuildConfig.THE_MOVIE_DB_API)
                .appendQueryParameter("query", movieName)
                .appendQueryParameter("page", page + "")
                .build()
                .toString();
    }

    /**
     * Get poster url(portrait) with size 185x278
     *
     * @param posterPath poster path that provided by movieList
     * @return String of poster url
     */
    public static String getPosterUrl(String posterPath) {
        return Uri.parse("https://image.tmdb.org").buildUpon()
                .appendPath("t")
                .appendPath("p")
                .appendEncodedPath("w185" + posterPath)
                .build().toString();
    }

    /**
     * Get backdrop url(landscape) with size 600x338
     *
     * @param backdropPath backdrop path that provided by movieList
     * @return String of backdrop url
     */
    public static String getBackdropUrl(String backdropPath) {
        return Uri.parse("https://image.tmdb.org").buildUpon()
                .appendPath("t")
                .appendPath("p")
                .appendEncodedPath("w600" + backdropPath)
                .build().toString();
    }

    /**
     * Compose API url for video list based on movieId
     *
     * @param movieId String
     * @return Video list url String
     * example: "http://api.themoviedb.org/3/movie/76341/videos?api_key=x"
     */
    public static String getVideoUrl(String movieId) {
        return Uri.parse(BASE_URL).buildUpon()
                .appendPath("movie")
                .appendPath(movieId)
                .appendPath("videos")
                .appendQueryParameter("api_key", BuildConfig.THE_MOVIE_DB_API)
                .build()
                .toString();
    }

    /**
     * Compose API url for review list based on movieId
     *
     * @param movieId String
     * @param page    page you want to get
     * @return Review list url String
     * example: "https://api.themoviedb.org/3/movie/76341/reviews?api_key=x&language=en-US&page=1"
     */
    public static String getReviewUrl(String movieId, int page) {
        return Uri.parse(BASE_URL).buildUpon()
                .appendPath("movie")
                .appendPath(movieId)
                .appendPath("reviews")
                .appendQueryParameter("api_key", BuildConfig.THE_MOVIE_DB_API)
                .appendQueryParameter("language", "en-US")
                .appendQueryParameter("page", page + "")
                .build()
                .toString();
    }

    /**
     * Get youtube thumbnail preview of the video
     *
     * @param videoId key of youtube video
     * @return thumbnail url
     * <p>
     * It is possible to change the resolution of the thumbnail, but beware of unavailable thumb
     * we can use:
     * default.jpg
     * hqdefault.jpg
     * mqdefault.jpg
     * sddefault.jpg
     * maxresdefault.jpg
     */
    public static String getYoutubeThumbnail(String videoId) {
        return Uri.parse("https://img.youtube.com").buildUpon()
                .appendPath("vi")
                .appendPath(videoId)
                .appendPath("default.jpg")
                .build()
                .toString();
    }
}

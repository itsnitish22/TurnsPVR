package com.nitishsharma.domain.models.TurnsPVRModels.home


import com.google.gson.annotations.SerializedName

data class HomeMoviesModel(
    @SerializedName("page")
    val pageNumber: Int?,
    @SerializedName("results")
    val movieList: ArrayList<Result?>,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)

data class Result(
    @SerializedName("adult")
    val isAdult: Boolean?,
    @SerializedName("id")
    val movieId: Int?,
    @SerializedName("original_language")
    val originalLanguage: String?,
    @SerializedName("original_title")
    val originalTitle: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("popularity")
    val popularity: Double?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("video")
    val video: Boolean?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("vote_count")
    val voteCount: Int?
)
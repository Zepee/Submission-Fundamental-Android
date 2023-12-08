package com.adhit.submission1.data.remote.retrofit

import com.adhit.submission1.data.remote.response.DetailUserResponse
import com.adhit.submission1.data.remote.response.GithubResponse
import com.adhit.submission1.data.remote.response.Items
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("search/users")
    fun searchUsers(
        @Query("q") query: String
    ): Call<GithubResponse>

    @GET("users/{username}")
    fun getDetailUser(@Path("username") username: String): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    fun getFollowers(@Path("username") username: String): Call<List<Items>>
    @GET("users/{username}/following")
    fun getFollowing(@Path("username") username: String): Call<List<Items>>
}

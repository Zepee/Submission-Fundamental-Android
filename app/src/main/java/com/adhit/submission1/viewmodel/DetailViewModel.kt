package com.adhit.submission1.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adhit.submission1.data.remote.response.DetailUserResponse
import com.adhit.submission1.data.remote.response.Items
import com.adhit.submission1.data.remote.retrofit.ApiConfig
import com.adhit.submission1.ui.DetailActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel: ViewModel() {

    private val _username = MutableLiveData<DetailUserResponse>()
    val username: LiveData<DetailUserResponse> = _username

    private val _followers = MutableLiveData<List<Items>>()
    val followers: LiveData<List<Items>> = _followers

    private val _following = MutableLiveData<List<Items>>()
    val following: LiveData<List<Items>> = _following

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getDetail(user:String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailUser(user)
        client.enqueue(object : Callback<DetailUserResponse> {
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            ){
                _isLoading.value = false
                if (response.isSuccessful){
                    _username.value = response.body()
                } else {
                    Log.e(DetailActivity.TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable){
                _isLoading.value = false
                Log.e(DetailActivity.TAG, "onFailure: ${t.message}")
            }
        })
    }
    fun getFollowers (user: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFollowers(user)
        client.enqueue(object : Callback<List<Items>> {
            override fun onResponse(
                call: Call<List<Items>>,
                response: Response<List<Items>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful){
                    _followers.value = response.body()
                } else {
                    Log.e(DetailActivity.TAG, "onFailure ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<Items>>, t: Throwable) {
                _isLoading.value = false
                Log.e(DetailActivity.TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun getFollowing (user: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFollowing(user)
        client.enqueue(object  : Callback<List<Items>>{
            override fun onResponse(
                call: Call<List<Items>>,
                response: Response<List<Items>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful){
                    _following.value = response.body()
                } else {
                    Log.e(DetailActivity.TAG, "onFailure ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<Items>>, t: Throwable) {
                _isLoading.value = false
                Log.e(DetailActivity.TAG, "onFailure: ${t.message}")
            }
        })
    }
}
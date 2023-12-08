package com.adhit.submission1.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adhit.submission1.data.remote.response.GithubResponse
import com.adhit.submission1.data.remote.response.Items
import com.adhit.submission1.data.remote.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListViewModel : ViewModel() {

    private val searchList = MutableLiveData<List<Items>>()
    val getSearchList: LiveData<List<Items>> = searchList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object{
        private const val TAG = "USERNAME"
        private const val username = "Zepee"
    }

    init {
        findUsers(username)
    }

    fun findUsers(query: String) {
           _isLoading.value = true
           val client = ApiConfig.getApiService().searchUsers(query)
           client.enqueue(object : Callback<GithubResponse> {
               override fun onResponse(
                   call: Call<GithubResponse>,
                   response: Response<GithubResponse>
               ) {
                   _isLoading.value = false
                   if (response.isSuccessful) {
                       searchList.value = response.body()?.items
                   } else {
                       Log.e(TAG, "onFailure: ${response.message()}")
                   }
               }

               override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                   _isLoading.value = false
                   Log.e(TAG, "onFailure: ${t.message}")
               }
           })
    }
}


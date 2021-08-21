package com.example.whotouchesmycountry.networking

import com.example.whotouchesmycountry.data.Country
import com.example.whotouchesmycountry.data.Failure
import com.example.whotouchesmycountry.data.Success
import com.google.gson.GsonBuilder
import com.example.whotouchesmycountry.data.Result
import okhttp3.*
import java.io.IOException

class RequestManager {

    private val url = "https://restcountries.eu/rest/v2/all"
    private val  client = OkHttpClient()

    fun makeRequest(completion: (Result<Array<Country>,NetworkErrors>) -> Unit)  {

        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object: Callback{

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val gson = GsonBuilder().create()
                    val body = response.body?.string()
                    body?.let { // it in
                        val countryArr = gson.fromJson(it, Array<Country>::class.java)
                        completion.invoke(Success(countryArr))
                        return
                    }
                    completion.invoke(Failure(NetworkErrors.PARSING_ERROR))
                } else {
                    completion.invoke(Failure(NetworkErrors.UNSUCCESSFUL_RESPONSE_ERROR))
                }
            }
            override fun onFailure(call: Call, e: IOException) {
                completion.invoke(Failure(NetworkErrors.OKHTTP_ERROR))
            }
        })
    }
}
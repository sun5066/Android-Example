package github.sun5066.covid_19.net

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    private lateinit var instance: Retrofit
    private val gSon = GsonBuilder().setLenient().create()

    companion object {
        private const val BASE_URL = ""
    }

    fun getInstance(): Retrofit {
        if (instance == null) {
            instance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gSon))
                .build()
        }
        return instance!!
    }
}
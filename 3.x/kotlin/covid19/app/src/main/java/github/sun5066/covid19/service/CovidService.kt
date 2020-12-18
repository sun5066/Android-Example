package github.sun5066.covid19.service

import github.sun5066.covid19.model.StateVO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

class CovidApi {
    companion object {
        val DOMAIN = "https://api.corona-19.kr"
        val TOKEN = "931894787783b592835283b2e1ec05492"
    }
}

interface CovidService {
    @GET("/korea/country/new/")
    fun getDocument(@Query("serviceKey") serviceKey: String): Call<StateVO>
}
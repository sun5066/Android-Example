package github.sun5066.covid19.component

import github.sun5066.covid19.model.CovidVO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

class CovidApi {
    companion object {
        val DOMAIN = "https://api.corona-19.kr/"
        val TOKEN = "korea/country/new/?serviceKey=931894787783b592835283b2e1ec05492"
    }
}

interface CovidService {

    @GET("{token}")
    fun getDocument(@Path("token") token: String): Call<CovidVO>
}
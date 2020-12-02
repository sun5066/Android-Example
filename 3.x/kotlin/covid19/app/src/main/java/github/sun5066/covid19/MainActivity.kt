package github.sun5066.covid19

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import github.sun5066.covid19.adapter.CovidViewAdapter
import github.sun5066.covid19.component.CovidApi
import github.sun5066.covid19.component.CovidService
import github.sun5066.covid19.model.CovidVO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var covidViewAdapter: CovidViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var covidList: MutableList<CovidVO> = mutableListOf()
        covidViewAdapter = CovidViewAdapter(covidList)

        val recyclerView: RecyclerView = findViewById(R.id.covid_list_view)
        recyclerView.adapter = covidViewAdapter

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        this.loadCovidList()
    }

    fun loadCovidList() {
        val retrofit = Retrofit.Builder()
            .baseUrl(CovidApi.DOMAIN)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val covidService = retrofit.create(CovidService::class.java)
        covidService
            .getDocument(CovidApi.TOKEN)
            .enqueue(object : Callback<CovidVO> {
                override fun onFailure(call: Call<CovidVO>, t: Throwable) {
//                    Toast.makeText(baseContext, "서버에서 데이터를 가져올 수 없습니다.", Toast.LENGTH_LONG).show()
                    Log.d("실패실패..", "${t.message.toString()}")
                }

                override fun onResponse(call: Call<CovidVO>, response: Response<CovidVO>) {
                    Log.d("성공성공!!", response.body().toString())
                }
            })
    }
}

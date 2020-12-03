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
import github.sun5066.covid19.model.StateVO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var covidViewAdapter: CovidViewAdapter
    private lateinit var covidList: MutableList<CovidVO>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.getCovidApi()
    }

    fun getCovidApi() {
        val retrofit = Retrofit.Builder()
            .baseUrl(CovidApi.DOMAIN)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val covidService = retrofit.create(CovidService::class.java)
        covidService
            .getDocument(CovidApi.TOKEN)
            .enqueue(object : Callback<StateVO> {
                override fun onFailure(call: Call<StateVO>, t: Throwable) {
//                    Toast.makeText(baseContext, "서버에서 데이터를 가져올 수 없습니다.", Toast.LENGTH_LONG).show()
                    Log.d("실패실패..", "${t.message.toString()}")
                }

                override fun onResponse(call: Call<StateVO>, response: Response<StateVO>) {
                    if (response.isSuccessful) {
                        Log.d("성공성공!", response!!.body().toString())

                        covidList = mutableListOf<CovidVO>(
                            response.body()!!.korea,
                            response.body()!!.seoul,
                            response.body()!!.busan,
                            response.body()!!.incheon,
                            response.body()!!.gwangju,
                            response.body()!!.jeonbuk,
                            response.body()!!.chungbuk,
                            response.body()!!.jeonnam,
                            response.body()!!.gyeongbuk,
                            response.body()!!.daegu,
                            response.body()!!.ulsan,
                            response.body()!!.daejeon,
                            response.body()!!.sejong,
                            response.body()!!.chungnam,
                            response.body()!!.gyeonggi,
                            response.body()!!.gyeongnam,
                            response.body()!!.gangwon,
                            response.body()!!.jeju,
                            response.body()!!.quarantine
                        ) ?: mutableListOf()

                        covidViewAdapter = CovidViewAdapter(covidList)

                        val recyclerView: RecyclerView = findViewById(R.id.covid_list_view)
                        recyclerView.adapter = covidViewAdapter

                        val layoutManager = LinearLayoutManager(baseContext)
                        recyclerView.layoutManager = layoutManager
                    }
                }
            })
    }
}

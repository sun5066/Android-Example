package github.sun5066.covid_19

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import github.sun5066.covid_19.adapter.CovidViewAdapter
import github.sun5066.covid_19.model.CovidVO
import okhttp3.OkHttpClient
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    private lateinit var covidViewAdapter: CovidViewAdapter

    private val client = OkHttpClient.Builder().build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var covidList: MutableList<CovidVO> = mutableListOf()
        covidViewAdapter = CovidViewAdapter(covidList = covidList)

        val recyclerView: RecyclerView = findViewById(R.id.covid_list_view)
        recyclerView.adapter = covidViewAdapter

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

    }
}
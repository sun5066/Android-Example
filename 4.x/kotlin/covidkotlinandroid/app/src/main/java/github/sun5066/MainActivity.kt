package github.sun5066

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import github.sun5066.adapter.CovidViewAdapter
import github.sun5066.model.CovidVO

class MainActivity : AppCompatActivity() {

    private lateinit var covidViewAdapter: CovidViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val covidList: MutableList<CovidVO> = mutableListOf();

        for (i in 0..50) {
            var covidVO: CovidVO = CovidVO()
            covidVO.country = "대한민국"
            covidVO.state = "광주"
            covidList.add(covidVO)
        }
        covidViewAdapter = CovidViewAdapter(this, covidList)
        val recyclerView: RecyclerView = findViewById(R.id.data_list_view)
        recyclerView.adapter = covidViewAdapter

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
    }
}
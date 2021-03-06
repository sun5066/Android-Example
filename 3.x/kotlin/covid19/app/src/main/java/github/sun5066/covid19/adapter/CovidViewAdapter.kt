package github.sun5066.covid19.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import github.sun5066.covid19.R
import github.sun5066.covid19.model.CovidVO

class CovidViewAdapter(var covidList: MutableList<CovidVO>) :
    RecyclerView.Adapter<CovidViewAdapter.CovidHolder?>() {

    fun setList(covidList: MutableList<CovidVO>) {
        this.covidList = covidList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CovidHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.covid_item, parent, false)
        return CovidHolder(view)
    }

    override fun getItemCount(): Int {
        return covidList.size
    }

    override fun onBindViewHolder(holder: CovidHolder, position: Int) {
        holder.txtCountry.text = covidList[position]?.countryName ?: "null"
        holder.txtNewCase.text = "신규확진자 수 : ${covidList[position]?.newCase}" ?: "null"
        holder.txtTotalCase.text = "확진자 수 : ${covidList[position]?.totalCase}" ?: "null"
        holder.txtRecovered.text = "왼치자 수 : ${covidList[position]?.recovered}" ?: "null"
        holder.txtDeath.text = "사망자 수 : ${covidList[position]?.death}" ?: "null"
        holder.txtPercent.text = "발생률 : ${covidList[position]?.percentage}%" ?: "null"
        holder.txtNewFCase.text = "전일대비증감-해외유입 : ${covidList[position]?.newFCase}" ?: "null"
        holder.txtNewCCase.text = "전일대비증감-지역발생 : ${covidList[position]?.newCCase}" ?: "null"
    }

    class CovidHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtCountry: TextView = itemView.findViewById(R.id.txt_country)
        var txtRecovered: TextView = itemView.findViewById(R.id.txt_recovered)
        var txtNewCase: TextView = itemView.findViewById(R.id.txt_new_case)
        var txtTotalCase: TextView = itemView.findViewById(R.id.txt_total)
        var txtDeath: TextView = itemView.findViewById(R.id.txt_death)
        var txtPercent: TextView = itemView.findViewById(R.id.txt_percent)
        var txtNewFCase: TextView = itemView.findViewById(R.id.txt_new_f_case)
        var txtNewCCase: TextView = itemView.findViewById(R.id.txt_new_c_case)
    }
}
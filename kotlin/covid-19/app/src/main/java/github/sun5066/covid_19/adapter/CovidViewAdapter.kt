package github.sun5066.covid_19.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import github.sun5066.covid_19.R
import github.sun5066.covid_19.model.CovidVO

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

    override fun onBindViewHolder(holder: CovidHolder, position: Int) {
        holder.txtCountryName.text = covidList[position].countryName
        holder.txtNewCase.text = covidList[position].newCase
        holder.txtTotalCase.text = covidList[position].totalCase
        holder.txtDeath.text = covidList[position].death
        holder.txtPercent.text = covidList[position].percent
        holder.txtNewFCase.text = covidList[position].newFCase
        holder.txtNewCCase.text = covidList[position].newCCase
    }

    override fun getItemCount(): Int {
        return covidList.size
    }

    class CovidHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtCountryName: TextView = itemView.findViewById(R.id.txt_country_name)
        var txtNewCase: TextView = itemView.findViewById(R.id.txt_new_case)
        var txtTotalCase: TextView = itemView.findViewById(R.id.txt_total_case)
        var txtDeath: TextView = itemView.findViewById(R.id.txt_death)
        var txtPercent: TextView = itemView.findViewById(R.id.txt_percent)
        var txtNewFCase: TextView = itemView.findViewById(R.id.txt_new_f_case)
        var txtNewCCase: TextView = itemView.findViewById(R.id.txt_new_c_case)
    }
}


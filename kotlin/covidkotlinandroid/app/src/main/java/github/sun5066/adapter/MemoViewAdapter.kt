package github.sun5066.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import github.sun5066.R
import github.sun5066.model.CovidVO

class MemoViewAdapter(var context: Context, var covidList: MutableList<CovidVO>) :
    RecyclerView.Adapter<MemoViewAdapter.CovidHolder>() {

    fun setList(covidList: MutableList<CovidVO>) {
        this.covidList = covidList
    }

    class CovidHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var country: TextView = itemView.findViewById(R.id.country)
        var state: TextView = itemView.findViewById(R.id.state)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CovidHolder {
        val view: View = LayoutInflater
            .from(context)
            .inflate(R.layout.covid_item, parent, false)
        return CovidHolder(view)
    }

    override fun onBindViewHolder(holder: CovidHolder, position: Int) {
        holder.country.text = covidList[position]?
    }

    override fun getItemCount(): Int {

    }
}
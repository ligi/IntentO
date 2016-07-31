package org.ligi.intento

import android.content.Context
import android.content.pm.ResolveInfo
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

internal class ResolveInfoAdapter(private val data: List<ResolveInfo>, private val onClickListener: (info: ResolveInfo) -> Unit) : RecyclerView.Adapter<ResolveInfoHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResolveInfoHolder {
        val inflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.item_resolve_info, parent, false)
        return ResolveInfoHolder(view)
    }

    override fun onBindViewHolder(holder: ResolveInfoHolder, position: Int) {
        holder.bind(data[position])
        holder.itemView.setOnClickListener({ onClickListener(data[position]) })
    }


    override fun getItemCount(): Int {
        return data.size
    }

}

package org.ligi.intentchoice

import android.content.pm.ResolveInfo
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

internal class ResolveInfoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val imgIcon by lazy { itemView.findViewById(R.id.appIcon) as ImageView}
    private val txtTitle by lazy { itemView.findViewById(R.id.appTitle) as TextView}
    private val context by lazy { itemView.context }

    fun bind(resolveInfo: ResolveInfo) {
        txtTitle.text = resolveInfo.loadLabel(context.packageManager)
        imgIcon.setImageDrawable(resolveInfo.loadIcon(context.packageManager))
    }
}

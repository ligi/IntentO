package org.ligi.intento

import android.app.AlertDialog
import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import org.ligi.intento.utils.IntentDescriber

class InfoDialog(val ctx: Context) {

    val mimeTextView by lazy { infoView.findViewById(R.id.mime) as TextView }

    val actionTextView by lazy { infoView.findViewById(R.id.action) as TextView }
    val dataTextView by lazy { infoView.findViewById(R.id.data) as TextView }
    val extrasTextView by lazy { infoView.findViewById(R.id.extras) as TextView }
    val catTextView by lazy { infoView.findViewById(R.id.categories) as TextView }

    val infoView: View by lazy { LayoutInflater.from(ctx).inflate(R.layout.dialog_intentinfo, null) }

    fun show(intentDescriber: IntentDescriber) {
        val intent = intentDescriber.intent

        // remove redundant information
        actionTextView.text = Html.fromHtml("<b>Action:</b> ${intentDescriber.userFacingActionString}")

        if (intent.data != null) {
            dataTextView.text = Html.fromHtml("<b>Data:</b> ${intent.data.toString()}")
        } else {
            dataTextView.visibility = View.GONE
        }

        if (intent.extras.isNotEmpty()) {
            extrasTextView.text = Html.fromHtml("<b>Extras:</b><br/>" +
                    intent.extras.map { "${it.first}=${it.second}" }.joinToString("<br/>"))
        } else {
            extrasTextView.visibility = View.GONE
        }

        if (intent.categories.isNotEmpty()) {
            catTextView.text = Html.fromHtml("<b>Category: </b>${intentDescriber.userFacingCategoriesString}")
        } else {
            catTextView.visibility = View.GONE
        }

        if (intent.type != null) {
            mimeTextView.text = Html.fromHtml("<b>Type:</b> ${intent.type}")
        } else {
            mimeTextView.visibility = View.GONE
        }

        AlertDialog.Builder(ctx)
                .setTitle("Intent Info")
                .setView(infoView)
                .setIcon(R.drawable.ic_action_info)
                .setPositiveButton(android.R.string.ok, null)
                .show()
    }


}
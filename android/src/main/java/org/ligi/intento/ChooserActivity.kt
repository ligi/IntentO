package org.ligi.intento

import android.annotation.TargetApi
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Icon
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import org.ligi.axt.AXT
import org.ligi.intento.utils.IntentDescriber
import java.util.ArrayList

class ChooserActivity : AppCompatActivity() {

    val actionTextView by lazy { findViewById(R.id.action) as TextView }
    val dataTextView by lazy { findViewById(R.id.data) as TextView }
    val dataValTextView by lazy { findViewById(R.id.data_val) as TextView }
    val catTextView by lazy { findViewById(R.id.categories) as TextView }
    val catValTextView by lazy { findViewById(R.id.categories_val) as TextView }
    val alwaysCheckBox by lazy { findViewById(R.id.always_checkbox) as CheckBox }
    val addConditionButton by lazy { findViewById(R.id.add_condition) as Button }
    val intentList by lazy { findViewById(R.id.intentList) as RecyclerView }

    private var intentDescriber: IntentDescriber? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intentDescriber = IntentDescriber(intent)

        val pm = packageManager
        val targetIntent = Intent(intent.action)

        if (intent.categories != null) {
            for (s in intent.categories) {
                targetIntent.addCategory(s)
            }
        }

        targetIntent.data = intent.data

        val resolveInfoList = pm.queryIntentActivities(targetIntent, PackageManager.MATCH_DEFAULT_ONLY)

        val filteredResolveInfoList = ArrayList<ResolveInfo>()

        for (resolveInfo in resolveInfoList) {
            // exclude our stuff
            if (resolveInfo.activityInfo.packageName != packageName) {
                filteredResolveInfoList.add(resolveInfo)
            }
        }

        // if we have only one result left we can safely choose it
        if (filteredResolveInfoList.size == 1) {
            startAppFromResolveInfo(filteredResolveInfoList[0])
            return
        }

        setUpUI(filteredResolveInfoList)
    }

    private fun setUpUI(filteredResolveInfoList: List<ResolveInfo>) {
        setContentView(R.layout.activity_chooser)

        showIntentDetails()

        setupIntentList(filteredResolveInfoList)

        alwaysCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            AXT.at(addConditionButton).setVisibility(isChecked)
        }
    }

    private fun setupIntentList(filteredResolveInfoList: List<ResolveInfo>) {
        intentList.layoutManager = LinearLayoutManager(this)
        intentList.adapter = ResolveInfoAdapter(filteredResolveInfoList) {
            startAppFromResolveInfo(it)
        }

    }

    private fun startAppFromResolveInfo(resolveInfo: ResolveInfo) {

        val drawable = resolveInfo.loadIcon(packageManager)
        val label = resolveInfo.loadLabel(packageManager)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notificationIntent = intent
        val contentIntent = PendingIntent.getActivity(this,
                1, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT)

        val bitmap = (drawable as BitmapDrawable).bitmap


        val notification = notification(bitmap, contentIntent, label)

        notificationManager.notify(1, notification)

        val component = ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name)
        Log.i("Intentify", " start Intent " + resolveInfo.activityInfo.packageName + " " + resolveInfo.activityInfo.name)
        intent.component = component
        startActivity(intent)
        finish()
    }

    @TargetApi(23)
    private fun notification(bitmap: Bitmap?, contentIntent: PendingIntent?, label: CharSequence?): Notification? {
        val notification = if (BuildConfig.VERSION_CODE < 23) {
            NotificationCompat.Builder(this)
                    .setLargeIcon(bitmap)
                    .setSmallIcon(R.drawable.ic_launcher)
                    .setContentText(intentDescriber!!.userFacingIntentDescription)
                    .setContentTitle(label)
                    .setContentIntent(contentIntent)
                    .setAutoCancel(true)
                    .build()
        } else {

            Notification.Builder(this)
                    .setSmallIcon(Icon.createWithBitmap(bitmap))
                    .setLargeIcon(bitmap)
                    .setContentText(intentDescriber!!.userFacingIntentDescription)
                    .setContentTitle(label)
                    .setContentIntent(contentIntent)
                    .setAutoCancel(true)
                    .build()
        }
        return notification
    }


    private fun showIntentDetails() {
        if (intent.action != null) {
            // remove redundant information
            actionTextView.text = intentDescriber!!.userFacingActionString
        } else {
            actionTextView.visibility = View.GONE
        }

        if (intent.data != null) {
            dataValTextView.text = intent.data.toString()
        } else {
            dataTextView.visibility = View.GONE
            dataValTextView.visibility = View.GONE
        }

        if (intent.categories != null) {
            catValTextView.text = intentDescriber!!.userFacingCategoriesString
        } else {
            catTextView.visibility = View.GONE
            catValTextView.visibility = View.GONE
        }
    }


}

package org.ligi.intento

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import org.ligi.intento.utils.IntentDescriber
import java.util.*
import javax.inject.Inject

class IntroActivity : AppCompatActivity() {

    @Inject
    lateinit var intentRulesProvider: IntentRuleProvider

    val rulesRecycler by lazy { findViewById(R.id.rulesRecycler) as RecyclerView }
    val emptyView by lazy { findViewById(R.id.emptyView) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_info)

        App.component.inject(this)

        rulesRecycler.adapter = RuleRecycler(intentRulesProvider.intentRules)

        rulesRecycler.layoutManager = LinearLayoutManager(this)

    }

    override fun onResume() {
        super.onResume()

        emptyView.visibility = if (intentRulesProvider.intentRules.isEmpty()) VISIBLE else GONE
        rulesRecycler.adapter.notifyDataSetChanged()
    }

    class RuleRecycler(val list: ArrayList<IntentRule>) : RecyclerView.Adapter<RuleViewHolder>() {
        override fun onBindViewHolder(holder: RuleViewHolder, position: Int) {
            holder.bind(list[position])
        }

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RuleViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rule, parent, false)
            return RuleViewHolder(view)
        }

    }

    class RuleViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        val text by lazy { view.findViewById(R.id.ruleText) as TextView }
        val icon by lazy { view.findViewById(R.id.ruleIcon) as ImageView }


        fun bind(intentRule: IntentRule) {
            when (intentRule) {
                is IntentRuleProvider.SimpleIntentRule -> {
                    val intentDescriber = IntentDescriber(intentRule.simpleIntent)
                    text.text = intentDescriber.userFacingIntentDescription
                    icon.setImageBitmap(intentRule.icon)
                }
            }

        }

    }

}

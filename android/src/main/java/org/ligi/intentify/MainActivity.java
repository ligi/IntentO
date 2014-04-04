package org.ligi.intentify;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends Activity {

    @InjectView(R.id.intentList)
    ListView intentList;

    @InjectView(R.id.action)
    TextView actionTextView;

    @InjectView(R.id.data)
    TextView dataTextView;

    @InjectView(R.id.data_val)
    TextView dataValTextView;

    @InjectView(R.id.categories)
    TextView catTextView;

    @InjectView(R.id.categories_val)
    TextView catValTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        showIntentDetails();

        final PackageManager pm = getPackageManager();

        Intent targetIntent = new Intent(getIntent().getAction());

        if (getIntent().getCategories() != null) {
            for (String s : getIntent().getCategories()) {
                targetIntent.addCategory(s);
            }
        }

        targetIntent.setData(getIntent().getData());

        final List<ResolveInfo> resolveInfoList = pm.queryIntentActivities(targetIntent, PackageManager.MATCH_DEFAULT_ONLY);

        final List<ResolveInfo> filteredResolveInfoList=new ArrayList<>();

        for (ResolveInfo resolveInfo : resolveInfoList) {
            // exclude our stuff
            if (!resolveInfo.activityInfo.packageName.equals(getPackageName())) {
                filteredResolveInfoList.add(resolveInfo);
            }
        }

        intentList.setAdapter(new ResolveInfoAdapter( this, R.layout.item_resolve_info, filteredResolveInfoList) );
        intentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ResolveInfo resolveInfo = filteredResolveInfoList.get(position);
                ComponentName component = new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
                Log.i("Intentify", " start Intent " + resolveInfo.activityInfo.packageName + " " + resolveInfo.activityInfo.name);
                getIntent().setComponent(component);
                startActivity(getIntent());
                finish();
            }
        });
    }

    private void showIntentDetails() {
        actionTextView.setText(getIntent().getAction());

        if (getIntent().getData()!=null) {
            dataValTextView.setText(getIntent().getData().toString());
        } else {
            dataTextView.setVisibility(View.GONE);
            dataValTextView.setVisibility(View.GONE);
        }

        if (getIntent().getCategories()!=null)  {
            String categories="";
            for (String categorie : getIntent().getCategories()) {
                categories+=categorie+" ";
            }
            catValTextView.setText(categories);
        } else {
            catTextView.setVisibility(View.GONE);
            catValTextView.setVisibility(View.GONE);
        }
    }

}

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

import java.util.List;

public class MainActivity extends Activity {

    private ListView intentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("Intentify", "Incoming Action " + getIntent().getAction());
        Log.i("Intentify", "Incoming Data " + getIntent().getData());
        Log.i("Intentify", "Incoming Data " + getIntent().getCategories());

        getIntent().getAction();

        final PackageManager pm = getPackageManager();

        Intent targetIntent = new Intent(getIntent().getAction());

        if (getIntent().getCategories() != null) {
            for (String s : getIntent().getCategories()) {
                targetIntent.addCategory(s);
            }
        }

        targetIntent.setData(getIntent().getData());

        final List<ResolveInfo> resolveInfos = pm.queryIntentActivities(targetIntent, PackageManager.MATCH_DEFAULT_ONLY);

        intentList = (ListView) findViewById(R.id.intentList);

        intentList.setAdapter(new ResolveInfoAdapter( this, R.layout.item_resolve_info, resolveInfos) );
        intentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ResolveInfo resolveInfo = resolveInfos.get(position);
                ComponentName component = new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
                Log.i("Intentify", " start Intent " + resolveInfo.activityInfo.packageName + " " + resolveInfo.activityInfo.name);
                getIntent().setComponent(component);
                startActivity(getIntent());
                finish();
            }
        });
    }

}

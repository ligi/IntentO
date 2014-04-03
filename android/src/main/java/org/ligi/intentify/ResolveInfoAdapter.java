package org.ligi.intentify;

import android.content.Context;
import android.content.pm.ResolveInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

class ResolveInfoAdapter extends ArrayAdapter<ResolveInfo> {

    private final Context context;
    private final int layoutResourceId;
    private final List<ResolveInfo> data;

    public ResolveInfoAdapter( Context context, int layoutResourceId, List<ResolveInfo> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        ResolveInfoHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ResolveInfoHolder();
            holder.imgIcon = (ImageView) row.findViewById(R.id.icon);
            holder.txtTitle = (TextView) row.findViewById(R.id.text);

            row.setTag(holder);
        } else {
            holder = (ResolveInfoHolder) row.getTag();
        }


        final ResolveInfo resolveInfo = data.get(position);
        holder.txtTitle.setText(resolveInfo.loadLabel(context.getPackageManager()));
        holder.imgIcon.setImageDrawable(resolveInfo.loadIcon(context.getPackageManager()));

        return row;
    }

    class ResolveInfoHolder {
        ImageView imgIcon;
        TextView txtTitle;
    }
}

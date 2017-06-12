package number.android.wdvendor.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import number.android.wdvendor.R;
import number.android.wdvendor.adapters.entities.Preference;

/**
 * Created by user on 8/25/2016.
 */
public class PreferencesListAdapter extends BaseAdapter {

    Context context;
    ArrayList<Preference> preferences;

    public PreferencesListAdapter(Context context, ArrayList<Preference> preferences) {
        this.context = context;
        this.preferences = preferences;
    }

    @Override
    public int getCount() {
        return preferences.size();
    }

    @Override
    public Preference getItem(int position) {
        return preferences.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(R.layout.preference_list_item, null);
        }

        ViewHolder viewHolder = new ViewHolder(view);

        Preference preference = getItem(position);

        viewHolder.icon.setImageDrawable(preference.getIcon());
        viewHolder.name.setText(preference.getName());

        return view;
    }

    private class ViewHolder {

        ImageView icon;
        TextView name;

        ViewHolder(View row) {
            this.icon = (ImageView) row.findViewById(R.id.preference_icon);
            this.name = (TextView) row.findViewById(R.id.preference_name);
        }
    }
}

package com.example.tmnt.test4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by tmnt on 2016/1/25.
 */
public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<Crime> list;

    public MyAdapter(Context context, List<Crime> list) {
        this.context = context;
        this.list = list;
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return list.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.crimine_list_item, null, false);
            viewHolder = new ViewHolder();
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();

        }
        viewHolder.crimeListTitle = (TextView) view.findViewById(R.id.crime_list_title);
        viewHolder.crimeListContent = (TextView) view.findViewById(R.id.crime_list_content);
        viewHolder.crimeSolved = (CheckBox) view.findViewById(R.id.crime_list_solved);
        viewHolder.crimeListTitle.setText(list.get(position).getTitle());
        String s = CriminaFragment.getSimpleDate(list.get(position).getDate());
        viewHolder.crimeListContent.setText(s);
        viewHolder.crimeSolved.setEnabled(false);
        viewHolder.crimeSolved.setFocusable(false);
        viewHolder.crimeSolved.setChecked(list.get(position).isSolved());
        return view;
    }

    class ViewHolder {
        TextView crimeListTitle;
        TextView crimeListContent;
        CheckBox crimeSolved;
    }
}

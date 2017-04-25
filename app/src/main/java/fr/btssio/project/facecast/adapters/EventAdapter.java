package fr.btssio.project.facecast.adapters;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import fr.btssio.project.facecast.R;
import fr.btssio.project.facecast.objects.Event;


public class EventAdapter extends ArrayAdapter<Event> {

    private Activity activity;
    private List<Event> items;
    private Event objBean;
    private int row;

    public EventAdapter(Activity act, int resource, List<Event> arrayList) {
        super(act, resource, arrayList);
        this.activity = act;
        this.row = resource;
        this.items = arrayList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(row, null);

            holder = new ViewHolder();
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        if ((items == null) || ((position + 1) > items.size()))
            return view;

        objBean = items.get(position);

        holder.name = (TextView) view.findViewById(R.id.name);
        holder.date = (TextView) view.findViewById(R.id.date);
        holder.numberDays = (TextView) view.findViewById(R.id.numberDays);
        holder.departement = (TextView) view.findViewById(R.id.departement);
        holder.city = (TextView) view.findViewById(R.id.city);
        holder.eventType = (TextView) view.findViewById(R.id.eventType);

        if (holder.name != null && null != objBean.getName()
                && objBean.getName().trim().length() > 0) {
            holder.name.setText(Html.fromHtml(objBean.getName()));
        }

        if (holder.date != null && null != objBean.getDate()
                && objBean.getDate().trim().length() > 0) {
            holder.date.setText(Html.fromHtml(objBean.getDate()));
        }

        if (holder.numberDays != null && null != objBean.getNumberDays()
                && objBean.getNumberDays().trim().length() > 0) {
            holder.numberDays.setText(Html.fromHtml(objBean.getNumberDays()));
        }

        if (holder.departement != null && null != objBean.getDepartement()
                && objBean.getDepartement().trim().length() > 0) {
            holder.departement.setText(Html.fromHtml(objBean.getDepartement()));
        }

        if (holder.city != null && null != objBean.getCity()
                && objBean.getCity().trim().length() > 0) {
            holder.city.setText(Html.fromHtml(objBean.getCity()));
        }

        if (holder.eventType != null && null != objBean.getEventType()
                && objBean.getEventType().trim().length() > 0) {
            holder.eventType.setText(Html.fromHtml(objBean.getEventType()));
        }

        return view;
    }

    public class ViewHolder {
        public TextView name, date, numberDays, departement, city, eventType;
    }
}
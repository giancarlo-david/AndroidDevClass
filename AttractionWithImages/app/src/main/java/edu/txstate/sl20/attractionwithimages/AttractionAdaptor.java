package edu.txstate.sl20.attractionwithimages;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AttractionAdaptor extends ArrayAdapter<Attraction> {
    private Context context;
    private int resource;

    public AttractionAdaptor(@NonNull Context context, int resource, @NonNull List<Attraction> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;

    }
    private class ViewHolder{ //reuse
        ImageView imageView;
        TextView textView;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        Attraction bean = getItem(position);
        LayoutInflater mInflator = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        ViewHolder holder = null;
        //For the first row
        if (convertView == null) {

            holder = new ViewHolder();
            convertView = mInflator.inflate(resource, null);
            holder.textView = convertView.findViewById(R.id.txtName);
            holder.imageView = convertView.findViewById(R.id.ivAttraction);
            convertView.setTag(holder);
        } else {//reuse
            holder = (ViewHolder) convertView.getTag() ;
        }

        holder.textView.setText(bean.getName());
        holder.imageView.setImageResource(bean.getImage());


        return convertView;

    }
}

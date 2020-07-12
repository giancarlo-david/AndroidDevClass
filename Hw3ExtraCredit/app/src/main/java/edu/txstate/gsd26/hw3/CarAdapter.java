package edu.txstate.gsd26.hw3;

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

import org.w3c.dom.Text;

import java.util.List;

public class CarAdapter extends ArrayAdapter<Car> {
    private Context context;
    private int resource;

    public CarAdapter(@NonNull Context context, int resource, @NonNull List<Car> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView textView;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Car selectedCar = getItem(position);
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        ViewHolder holder = null;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(resource, null);
            holder.textView = convertView.findViewById(R.id.txtView);
            holder.imageView = convertView.findViewById(R.id.imgView);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textView.setText(selectedCar.getBrand() + " " + selectedCar.getName());
        holder.imageView.setImageResource(selectedCar.getImage());

        return convertView;
    }
}

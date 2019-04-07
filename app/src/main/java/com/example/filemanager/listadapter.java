package com.example.filemanager;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class listadapter extends ArrayAdapter<String> {

    private String [] str1;
    private String [] str2;
    private Integer [] image;
    private Activity context;
    public listadapter(Activity context, String [] str1, String [] str2, Integer [] image) {
        super(context, R.layout.list_layout, str1);
        this.context=context;
        this.str1 = str1;
        this.str2 = str2;
        this.image = image;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View r=convertView;
        ViewHolder viewHolder=null;
        if(r==null)
        {
            LayoutInflater la=context.getLayoutInflater();
            r=la.inflate(R.layout.list_layout,null,true);
            viewHolder=new ViewHolder(r);
            r.setTag(viewHolder);
        }
        else
        {
            viewHolder=(ViewHolder) r.getTag();
        }
        viewHolder.i1.setImageResource(image[position]);
        viewHolder.t1.setText(str1[position]);
        viewHolder.t2.setText(str2[position]);
        return r;
    }

    class ViewHolder
    {
        TextView t1,t2;
        ImageView i1;
        ViewHolder(View v)
        {
            t1=(TextView)v.findViewById(R.id.name);
            t2=(TextView)v.findViewById(R.id.size);
            i1=(ImageView)v.findViewById(R.id.image_to_view);

        }

    }



}
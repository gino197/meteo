package com.zebutech.meteo.adapter;
import android.widget.ArrayAdapter;
import android.content.Context;
import com.zebutech.meteo.R;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.zebutech.meteo.model.VilleModel;
import android.widget.TextView;

public class VilleListAdapter extends ArrayAdapter<String> { 
       
          private final Context context; 
          private final String[] items, date;            
          public VilleListAdapter(Context context, String[] items, String[] date) { 
                  super(context, R.layout.item_ville, items); 
                  this.context = context; 
                  this.items = items;
                  this.date = date;
              } 
      
          @Override 
         public View getView(int position, View view, ViewGroup parent) { 
                  LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
                  View rowView= inflater.inflate(R.layout.item_ville, null, true);     
                  TextView title = rowView.findViewById(R.id.ville_textView);      
                  TextView dateTxt = rowView.findViewById(R.id.Date_textView);
                  title.setText(items[position]);   
                  dateTxt.setText(date[position]);
                  return rowView; 
              } 
	} 

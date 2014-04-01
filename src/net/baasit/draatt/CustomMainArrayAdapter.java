package net.baasit.draatt;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomMainArrayAdapter extends ArrayAdapter<Draatt> {

	private final LayoutInflater mInflater;
	
	public CustomMainArrayAdapter(Context context){
		super(context, android.R.layout.simple_list_item_2);
		mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	public void setData(List<Draatt> data) {
		clear();
		if(data != null){
			for(Draatt appEntry : data) {
				add(appEntry);
			}
		}
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		View view;
		
		if(convertView == null) {
			view = mInflater.inflate(R.layout.single_item_main, parent, false);
		}else{
			view = convertView;
		}
		
		setHeaderItem();
		setMainList(position, view);
		/*
		Draatt item = getItem(position);
		((TextView)view.findViewById(R.id.draatt_stedsnavn)).setText(item.getStedsnavn());
		((TextView)view.findViewById(R.id.draatt_fylkenavn)).setText(item.getFylke());
		*/
		
		return view;
		
	}
	
	public void setMainList(int position, View view) {
		String header = "";
		if(position == 0){
			header = "Nær deg nå!";
		}else if(position > 0){
			header = "Søkeresultat " + (position);
		}
		
		Draatt item = getItem(position);
		
		((TextView)view.findViewById(R.id.list_headers)).setText(header);
		((TextView)view.findViewById(R.id.draatt_stedsnavn)).setText(item.getStedsnavn());
		((TextView)view.findViewById(R.id.draatt_fylkenavn)).setText(item.getFylke());
	}
	
	public void setHeaderItem(){
		
	}
	
}

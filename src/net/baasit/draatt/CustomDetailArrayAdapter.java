package net.baasit.draatt;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class CustomDetailArrayAdapter extends ArrayAdapter<DraattDetails> {

	private final LayoutInflater mInflater;
	
	public CustomDetailArrayAdapter (Context context){
		super(context, android.R.layout.simple_list_item_2);
		mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	public void setData(List<DraattDetails> data) {
		clear();
		if(data != null){
			for(DraattDetails appEntry : data) {
				add(appEntry);
			}
		}
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		View view;
		
		if(convertView == null) {
			view = mInflater.inflate(R.layout.single_item_details, parent, false);
		}else{
			view = convertView;
		}
		
		
		DraattDetails item = getItem(position);
		//((TextView)view.findViewById(R.id.xml_time)).setText(item.getFrom());
		//((TextView)view.findViewById(R.id.draatt_fylkenavn)).setText(item.getFylke());
		
		return view;
		
	}
	
}

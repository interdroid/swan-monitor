package interdroid.swan.swanmonitor;

import interdroid.swan.SensorInfo;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SensorSelectSpinnerAdapter extends ArrayAdapter<String> {

	String[] names, description;
	Context context;
	List<SensorInfo> swanSensorList;

	public SensorSelectSpinnerAdapter(Context context, int textViewResourceId, String[] objects,
			List<SensorInfo> swanSensorList) {
		super(context, textViewResourceId, objects);
		this.context = context;
		this.names = objects;
		// Might be neater to replace with list of drawables
		this.swanSensorList = swanSensorList;
		// description = names, but might be useful later
		this.description = objects;
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		return getCustomView(position, convertView, parent);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return getCustomView(position, convertView, parent);
	}

	public View getCustomView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View row = inflater.inflate(R.layout.spinner_row, parent, false);
		TextView label = (TextView) row.findViewById(R.id.company);
		label.setText(names[position]);

		TextView sub = (TextView) row.findViewById(R.id.sub);
		sub.setText("Click to add the " + description[position] + " sensor");

		ImageView icon = (ImageView) row.findViewById(R.id.image);
		icon.setImageDrawable(swanSensorList.get(position).getIcon());

		return row;
	}
}
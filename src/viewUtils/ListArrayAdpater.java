package viewUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.main.rexx.R;

public class ListArrayAdpater extends ArrayAdapter<String> {
	private final Context context;
	private final String[] values;

	public ListArrayAdpater(Context context, String[] values) {
		super(context, R.layout.list_item_layout, values);
		this.context = context;
		this.values = values;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = null;
		
		//if (context.getClass().getSimpleName().equals("HomeActivity")) {
			rowView = inflater.inflate(R.layout.list_item_layout, parent,false);
			TextView txtTitle = (TextView) rowView.findViewById(R.id.title);
			TextView txtDescription = (TextView) rowView.findViewById(R.id.description);
			ImageView imgvIcon = (ImageView) rowView.findViewById(R.id.icon);
			txtTitle.setText(values[position]);
			String s = values[position];
			if (s.equals(context.getString(R.string.find_partners))) {
				imgvIcon.setImageResource(R.drawable.ic_action_find_partner);
				txtDescription.setText(R.string.d_find_partners);
			} else if (s.equals(context.getString(R.string.chat))) {
				imgvIcon.setImageResource(R.drawable.ic_action_chat);
				txtDescription.setText(R.string.d_chat);
			} else if (s.equals(context.getString(R.string.animals))) {
				imgvIcon.setImageResource(R.drawable.dog_footprint);
				txtDescription.setText(R.string.d_animals);
			} else if (s.equals(context.getString(R.string.profile))) {
				imgvIcon.setImageResource(R.drawable.ic_action_person);
				txtDescription.setText(R.string.d_profile);
			} else if (s.equals(context.getString(R.string.settings))) {
				imgvIcon.setImageResource(R.drawable.ic_action_settings);
				txtDescription.setText(R.string.d_settings);
			} else if (s.equals(context.getString(R.string.invite))) {
				imgvIcon.setImageResource(R.drawable.ic_action_share);
				txtDescription.setText(R.string.d_invite);
			} else {
				imgvIcon.setImageResource(R.drawable.ic_launcher);
				txtDescription.setText(R.string.d_default);
			}
			/*} else {
			rowView = inflater.inflate(R.layout.contact_list_item_layout, parent,false);
			TextView txtTitle = (TextView) rowView.findViewById(R.id.title);
			TextView txtDescription = (TextView) rowView.findViewById(R.id.description);
			ImageView imgvIcon = (ImageView) rowView.findViewById(R.id.icon);
			txtTitle.setText(values[position]);
			String s = values[position];
			if (s.equals(context.getString(R.string.marco))) {
				imgvIcon.setImageResource(R.drawable.marco);
				txtDescription.setText(R.string.dog);
			} else if (s.equals(context.getString(R.string.antonio))) {
				imgvIcon.setImageResource(R.drawable.antonio);
				txtDescription.setText(R.string.cat);
			} else if (s.equals(context.getString(R.string.victoria))) {
				imgvIcon.setImageResource(R.drawable.victoria);
				txtDescription.setText(R.string.both);
			} else if (s.equals(context.getString(R.string.ana))) {
				imgvIcon.setImageResource(R.drawable.ana);
				txtDescription.setText(R.string.dog);
			} else if (s.equals(context.getString(R.string.virgilio))) {
				imgvIcon.setImageResource(R.drawable.virgilio);
				txtDescription.setText(R.string.cat);
			} else if (s.equals(context.getString(R.string.intasqui))) {
				imgvIcon.setImageResource(R.drawable.intasqui);
				txtDescription.setText(R.string.cat);
			} else if (s.equals(context.getString(R.string.michelle))) {
				imgvIcon.setImageResource(R.drawable.michelle);
				txtDescription.setText(R.string.both);
			} else if (s.equals(context.getString(R.string.alemao))) {
				imgvIcon.setImageResource(R.drawable.bruno);
				txtDescription.setText(R.string.dog);
			} else {
				imgvIcon.setImageResource(R.drawable.ic_launcher);
				txtDescription.setText(R.string.default_person);
			}
		}*/
		return rowView;
	}
}

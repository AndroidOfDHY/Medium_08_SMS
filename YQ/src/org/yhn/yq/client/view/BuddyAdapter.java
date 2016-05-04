package org.yhn.yq.client.view;
import java.util.List;

import org.yhn.yq.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BuddyAdapter extends BaseAdapter{
	private Context context;
	private List<BuddyEntity> list;
	LayoutInflater inflater;
	
	public BuddyAdapter(Context context,List<BuddyEntity> list){
		this.context = context;
		this.list = list;
		inflater = LayoutInflater.from(context);
	}

	public View getView(int position, View convertView, ViewGroup root) {
		convertView = inflater.inflate(R.layout.buddy_listview_item, null);
		
		ImageView avatar=(ImageView) convertView.findViewById(R.id.iv_avatar);
		TextView nick=(TextView) convertView.findViewById(R.id.tv_nick);
		TextView trends=(TextView) convertView.findViewById(R.id.tv_trends);
		
		BuddyEntity be=list.get(position);
		int id=be.getAvatar();
		avatar.setImageResource(ChatActivity.avatar[id]);
		nick.setText(be.getNick());
		trends.setText(be.getTrends());

		return convertView;
	}
	public int getCount() {
		return list.size();
	}

	public Object getItem(int position) {
		return list.get(position);
	}

	public long getItemId(int position) {
		return position;
	}
}

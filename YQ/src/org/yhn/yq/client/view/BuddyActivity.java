package org.yhn.yq.client.view;
import java.util.ArrayList;
import java.util.List;

import org.yhn.yq.R;
import org.yhn.yq.client.model.SendMessage;
import org.yhn.yq.common.YQMessageType;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class BuddyActivity extends Activity {
	ListView listView;
	public static String buddyStr=""; 
	List<BuddyEntity> buddyEntityList = new ArrayList<BuddyEntity>();//好友列表
	BuddyAdapter ba;//好友列表的adapter
	BuddyEntity temp;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_buddy);
		
		ImageView avatarIv=(ImageView) findViewById(R.id.buddy_top_avatar);
		TextView nickTv=(TextView) findViewById(R.id.buddy_top_nick);
		
		nickTv.setText(MoreActivity.me.getNick());
		avatarIv.setImageResource(ChatActivity.avatar[MoreActivity.me.getAvatar()]);
		
		listView = (ListView) findViewById(R.id.listview);
        //填充数据
		ba=new BuddyAdapter(this,jieXi(buddyStr));
        listView.setAdapter(ba);
        setListViewListener();
	}
	
	private void setListViewListener() {
		listView.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> a, View v, int position,long l) {
				temp= (BuddyEntity) listView.getItemAtPosition(position);
				//打开聊天页面
				Intent intent=new Intent(BuddyActivity.this,ChatActivity.class);
				intent.putExtra("avatar", temp.getAvatar());
				intent.putExtra("account",temp.getAccount());
				intent.putExtra("nick", temp.getNick());
				startActivity(intent);
			}
        });
        listView.setOnItemLongClickListener(new OnItemLongClickListener(){
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,int position, long arg3) {
				temp= (BuddyEntity) listView.getItemAtPosition(position);
				listView.setOnCreateContextMenuListener(new OnCreateContextMenuListener(){
					public void onCreateContextMenu(ContextMenu menu,
							View arg1, ContextMenuInfo arg2) {
						menu.setHeaderTitle("操作");
						menu.add(0,0,0,"发起会话");
						menu.add(0,1,0,"删除好友");
						menu.add(0,2,0,"查看好友资料");
					}
				});
				return false;
			}
        });
	}
	public boolean onContextItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case 0:
			//打开聊天页面
			Intent intent=new Intent(BuddyActivity.this,ChatActivity.class);
			intent.putExtra("avatar", temp.getAvatar());
			intent.putExtra("account",temp.getAccount());
			intent.putExtra("nick", temp.getNick());
			startActivity(intent);
			break;
		case 1:
			//向服务器发送一个删除好友的包
			SendMessage.sendADbuddy(MoreActivity.me.getAccount(), 
					temp.getAccount(), 
					YQMessageType.DEL_BUDDY);
			//删除好友列表中的该好友
			for(int i=0;i<buddyEntityList.size();i++){
				if((buddyEntityList.get(i).getAccount())==temp.getAccount()){
					buddyEntityList.remove(i);
				}
			}
			listView = (ListView) findViewById(R.id.listview);
			ba=new BuddyAdapter(this,buddyEntityList);
	        listView.setAdapter(ba);
			break;
		case 2:
			//
			break;
		}
		return super.onContextItemSelected(item);
	}

	private List<BuddyEntity> jieXi(String s){
        String ss[] = buddyStr.split(" ");
        for(String a: ss){
        	if(a!=""){
	        	String b[]=a.split("_");
	            buddyEntityList.add(new BuddyEntity(
	            		Integer.parseInt(b[2]), 
	            		Integer.parseInt(b[0]), 
	            		b[1], 
	            		b[3],
	            		Integer.parseInt(b[4])));
        	}
        }
		return buddyEntityList;
	}
}

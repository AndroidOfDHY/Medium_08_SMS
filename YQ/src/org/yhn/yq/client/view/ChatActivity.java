package org.yhn.yq.client.view;
import java.util.ArrayList;
import java.util.List;

import org.yhn.yq.R;
import org.yhn.yq.client.model.SendMessage;
import org.yhn.yq.common.MyTime;
import org.yhn.yq.common.YQMessageType;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ChatActivity extends Activity {
	EditText et_input;
	private String chatContent;//消息内容
	ListView chatListView;
	public List<ChatEntity> chatEntityList=new ArrayList<ChatEntity>();//所有聊天内容
	private int chatAccount;
	private String chatNick;
	public static int[] avatar=new int[]{R.drawable.avatar_default,R.drawable.h001,R.drawable.h002,R.drawable.h003,
			R.drawable.h004,R.drawable.h005,R.drawable.h006,R.drawable.group_avatar};
	MyBroadcastReceiver br;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_chat);
		//设置top面板信息
		int chatAvatar=getIntent().getIntExtra("avatar", 0);
		chatAccount=getIntent().getIntExtra("account", 0);
		chatNick=getIntent().getStringExtra("nick");
		ImageView avatar_iv=(ImageView) findViewById(R.id.chat_top_avatar);
		avatar_iv.setImageResource(avatar[chatAvatar]);
		TextView nick_tv=(TextView) findViewById(R.id.chat_top_nick);
		nick_tv.setText(chatNick);
		
		findViewById(R.id.ib_send).setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				//得到输入的数据，并清空EditText
				et_input=(EditText) findViewById(R.id.et_input);
				chatContent=et_input.getText().toString();
				et_input.setText("");
				//更新聊天内容
				updateChatView(new ChatEntity(
						MoreActivity.me.getAvatar(),
						MoreActivity.me.getNick(),
						chatContent,
			    		MyTime.geTime(),
			    		false));
				//发送消息
				if(getIntent().getIntExtra("avatar", 0)==7){ //群消息
					SendMessage.sendMes(chatAccount, chatContent,YQMessageType.GROUP_MES);
				}else { //好友消息
					SendMessage.sendMes(chatAccount, chatContent,YQMessageType.COM_MES);
				}
			}
		});
		 //注册广播
		IntentFilter myIntentFilter = new IntentFilter(); 
        myIntentFilter.addAction("org.yhn.yq.mes");
        br=new MyBroadcastReceiver();
        registerReceiver(br, myIntentFilter);
		ManageActivity.addActiviy("ChatActivity", this);
	}
	@Override
	public void finish() {
		 unregisterReceiver(br);
		super.finish();
	}
	
	//广播接收器
	public class MyBroadcastReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			String[] mes = intent.getStringArrayExtra("message");
		    //更新聊天内容
		    updateChatView(new ChatEntity(
		    		Integer.parseInt(mes[4]),
		    		mes[3],
		    		mes[5],
		    		mes[6],
		    		true));
		}
	}
	public void updateChatView(ChatEntity chatEntity){
		chatEntityList.add(chatEntity);
		chatListView=(ListView) findViewById(R.id.lv_chat);
		chatListView.setAdapter(new ChatAdapter(this,chatEntityList));
	}

}

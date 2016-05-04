package org.yhn.yq.client.view;
import org.yhn.yq.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

public class MainActivity extends TabActivity {
	public static String myInfo;
	private TabHost tabHost;
	private RadioGroup radioGroup;
	private static final String RECENT = "会话";
	private static final String BUDDY = "好友";
	private static final String GROUP = "群组";
	private static final String TRENDS = "动态";
	private static final String MORE= "更多";
	public Intent recentIntent;
	public Intent buddyIntent;
	public Intent groupIntent;
	public Intent trendsIntent;
	public Intent moreIntent;


	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.tabhost);
		tabHost = this.getTabHost();
		setupIntent();
		radioGroup = (RadioGroup) findViewById(R.id.main_radiogroup);
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch(checkedId){
				case R.id.tab_recent:
					tabHost.setCurrentTabByTag(RECENT);
					break;
				case R.id.tab_buddy:
					tabHost.setCurrentTabByTag(BUDDY);
					break;
				case R.id.tab_group:
					tabHost.setCurrentTabByTag(GROUP);
					break;
				case R.id.tab_trends:
					tabHost.setCurrentTabByTag(TRENDS);
					break;
				case R.id.tab_more:
					tabHost.setCurrentTabByTag(MORE);
					break;
				}
			}
		});
	}

	@Override
	protected void onStop() {
		showNotification();//显示通知栏图标
		super.onStop();
	}
	@Override
	protected void onResume() {
		//取消通知栏图标
		NotificationManager notificationManager = (NotificationManager) this 
                .getSystemService(NOTIFICATION_SERVICE); 
        notificationManager.cancel(0); 
		super.onResume();
	}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch(item.getItemId()){
		case R.id.menu_login_out:
			Toast.makeText(this, "你选择了："+item.getTitle(), Toast.LENGTH_SHORT).show();
			break;
		case R.id.menu_about:
			Dialog alertDialog = new AlertDialog.Builder(this)
            	.setTitle("关于YQ")
            	.setMessage("只为学习之用，勿用于商业用途！\nQQ：569629066\nBy：柒小狐")
            	.create();
			alertDialog.show();
			break;
		case R.id.menu_setting:
			Toast.makeText(this, "你选择了："+item.getTitle(), Toast.LENGTH_SHORT).show();
			break;
		case R.id.menu_close:
			closeAllActivity();
			break;
    	}
        return true;
    }
    //退出所有activity
    public void closeAllActivity(){
    	if(ManageActivity.getActivity("loginActivity")!=null){
    		ManageActivity.getActivity("loginActivity").finish();
    	}
    	this.finish();
		System.exit(0);
    }

	//按下返回键后不应该去LoginActivity，应该使其直接回到桌面，
	//次方法只适用于 2.0 以上版本，
	//低于2.0 使用public boolean onKeyDown(int keyCode, KeyEvent event) 
	public void onBackPressed() {
		Intent i = new Intent(Intent.ACTION_MAIN);
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.addCategory(Intent.CATEGORY_HOME);
		startActivity(i);
		super.onBackPressed();
	}
	
	private void setupIntent() {
		recentIntent= new Intent(this, RecentActivity.class);
		buddyIntent= new Intent(this, BuddyActivity.class);
		groupIntent= new Intent(this, GroupActivity.class);
		trendsIntent= new Intent(this, TrendsAcitivy.class);
		moreIntent= new Intent(this, MoreActivity.class);
		
		TabSpec tabSpec1 = tabHost.newTabSpec(RECENT).setIndicator(RECENT)
				.setContent(recentIntent);
		tabHost.addTab(tabSpec1);
		TabSpec tabSpec2 = tabHost.newTabSpec(BUDDY).setIndicator(BUDDY)
				.setContent(buddyIntent);
		tabHost.addTab(tabSpec2);
		TabSpec tabSpec3 = tabHost.newTabSpec(GROUP).setIndicator(GROUP)
				.setContent(groupIntent);
		tabHost.addTab(tabSpec3);
		TabSpec tabSpec4 = tabHost.newTabSpec(TRENDS).setIndicator(TRENDS)
				.setContent(trendsIntent);
		tabHost.addTab(tabSpec4);
		TabSpec tabSpec5 = tabHost.newTabSpec(MORE).setIndicator(MORE)
				.setContent(moreIntent);
		tabHost.addTab(tabSpec5);
	}
	
	//显示通知栏图标
	private void showNotification() { 
        NotificationManager notificationManager = (NotificationManager) 
            this.getSystemService(android.content.Context.NOTIFICATION_SERVICE); 
        // 定义Notification的各种属性 
        Notification notification =new Notification(R.drawable.logo, 
                "YQ 正在运行", System.currentTimeMillis()); 
        notification.flags |= Notification.FLAG_ONGOING_EVENT; //加入通知栏的"Ongoing"中 
        notification.flags |= Notification.FLAG_NO_CLEAR; //点击了通知栏中的"清除通知"后不清除
        notification.flags |= Notification.FLAG_SHOW_LIGHTS; 
        notification.defaults = Notification.DEFAULT_LIGHTS; 
        // 设置通知的事件消息
        Intent notificationIntent =new Intent(this, MainActivity.class);  
        PendingIntent contentItent = PendingIntent.getActivity(this, 0, 
                notificationIntent, 0); 
        notification.setLatestEventInfo(this, 
        		"YQ",  // 通知栏标题
        		"Android 即时通信工具 YQ",  // 通知栏内容
        		contentItent); // 点击该通知后要跳转的Activity
        // 把Notification传递给NotificationManager，id为0
        notificationManager.notify(0, notification); 
    }
}

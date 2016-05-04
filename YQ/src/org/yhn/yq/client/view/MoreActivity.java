package org.yhn.yq.client.view;
import org.yhn.yq.R;
import org.yhn.yq.common.User;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class MoreActivity extends Activity{
	public static User me; //当前账号的个人资料
	//因为在别的类中有用到me，所以提前解析了个人资料，
	static{
		me=jieXi(MainActivity.myInfo);
	}
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_more);
		
		ImageView avatar=(ImageView) findViewById(R.id.avatar_more);
		TextView nick=(TextView) findViewById(R.id.nick_more);
		TextView sex=(TextView) findViewById(R.id.sex_more);
		TextView age=(TextView) findViewById(R.id.age_more);
		TextView trends=(TextView) findViewById(R.id.trends_more);
		
		avatar.setImageResource(ChatActivity.avatar[me.getAvatar()]);
		nick.setText(me.getNick()+"(Lev:"+me.getLev()+")");
		sex.setText(me.getSex());
		age.setText(me.getAge()+"岁");
		trends.setText(me.getTrends());
	}

	private static User jieXi(String str) {
		User user=new User();
		String s[] = str.split("_");
    	if(s!=null){
	        user.setAccount(Integer.parseInt(s[0]));
	        user.setNick(s[1]);
	        user.setAvatar(Integer.parseInt(s[2]));
	        user.setTrends(s[3]);
	        user.setSex(s[4]);
	        user.setAge(Integer.parseInt(s[5]));
	        user.setLev(Integer.parseInt(s[6]));
    	}
		return user;
	}
}

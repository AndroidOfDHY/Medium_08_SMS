package org.yhn.yq.client.view;
import java.util.Date;

import org.yhn.yq.R;
import org.yhn.yq.client.model.YQClient;
import org.yhn.yq.common.MyTime;
import org.yhn.yq.common.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	String sex="男";
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.activity_register);
		
		findViewById(R.id.rigister_btn_register).setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				//简单写了下，
				EditText accountEt=(EditText) findViewById(R.id.register_account);
				EditText passwordEt=(EditText) findViewById(R.id.register_password);
				EditText nickEt=(EditText) findViewById(R.id.register_nick);
				RadioGroup group = (RadioGroup)findViewById(R.id.register_radiogroup);
				group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					public void onCheckedChanged(RadioGroup arg0,int id) {
						if(id==R.id.register_radio_nv){
							sex="女";
						}
					}
				});
				if(accountEt.getText().equals("") || passwordEt.getText().equals("")){
					Toast.makeText(RegisterActivity.this, "账号或密码不能为空！", Toast.LENGTH_SHORT).show();
				}else {
					User user=new User();
					user.setAccount(Integer.parseInt(accountEt.getText().toString()));
					user.setPassword(passwordEt.getText().toString());
					user.setNick(nickEt.getText().toString());
					user.setTrends("该用户暂时没有动态");
					user.setSex(sex);
					user.setAvatar(4);
					user.setLev(0);
					user.setAge(0);
					user.setTime(MyTime.geTimeNoS());
					user.setOperation("register");
					boolean b=new YQClient(RegisterActivity.this).sendRegisterInfo(user);
					if(b){
						//注册成功跳转到登陆
						Toast.makeText(RegisterActivity.this, "恭喜你，注册成功 ！", Toast.LENGTH_SHORT).show();
						startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
					}
				}
			}
		});
	}
}

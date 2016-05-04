package org.yhn.yq.client.view;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.yhn.yq.R;
import org.yhn.yq.client.model.ManageClientConServer;
import org.yhn.yq.client.model.YQClient;
import org.yhn.yq.common.User;
import org.yhn.yq.common.YQMessage;
import org.yhn.yq.common.YQMessageType;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	public static String userInfo;
	EditText accountEt,passwordEt;
	Dialog dialog;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	    setContentView(R.layout.activity_login);
	    
	    accountEt=(EditText) findViewById(R.id.et_account);
	    passwordEt=(EditText) findViewById(R.id.et_password);
	    Button btnLogin=(Button) findViewById(R.id.btn_login);
	    btnLogin.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				if(accountEt.getText().equals("") || passwordEt.getText().equals("")){
					Toast.makeText(LoginActivity.this, "账号或密码不能为空！", Toast.LENGTH_SHORT).show();
				}else{
					dialog = ProgressDialog.show(LoginActivity.this, null,  "正在登陆中 …", true, true);
					handler.post(new Runnable(){
						public void run() {
							boolean b=login(Integer.parseInt(accountEt.getText().toString()), passwordEt.getText().toString());
							if(b){
								Message m=new Message();  
	                            m.what=1;  
	                            handler.sendMessage(m);
								//转到主界面
								startActivity(new Intent(LoginActivity.this, MainActivity.class));
							}else {
								Toast.makeText(LoginActivity.this, "登陆失败！不告诉你为什么，", Toast.LENGTH_SHORT).show();
								dialog.dismiss();
							}
						}
					});
				}
			}
	    });
	    findViewById(R.id.btn_register).setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
			}
	    });
	    ManageActivity.addActiviy("LoginActivity", this);
	}
	
	boolean login(int a, String p){
		User user=new User();
		user.setAccount(a);
		user.setPassword(p);
		user.setOperation("login");
		boolean b=new YQClient(this).sendLoginInfo(user);
		//登陆成功
		if(b){
			try {
				//发送一个要求返回在线好友的请求的Message
				ObjectOutputStream oos = new ObjectOutputStream	(
						ManageClientConServer.getClientConServerThread(user.getAccount()).getS().getOutputStream());
				YQMessage m=new YQMessage();
				m.setType(YQMessageType.GET_ONLINE_FRIENDS);
				m.setSender(user.getAccount());
				oos.writeObject(m);
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.finish();
			return true;
		}else {
			return false;
		}
	}
	
	private Handler handler=new Handler(){  
        public void handleMessage(Message msg){  
            switch(msg.what){  
            case 1:
                dialog.dismiss();  
                break;  
            }  
        }  
    }; 

}

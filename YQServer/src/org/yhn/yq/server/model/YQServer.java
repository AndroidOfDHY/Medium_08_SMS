package org.yhn.yq.server.model;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import org.yhn.yq.common.User;
import org.yhn.yq.common.YQMessage;
import org.yhn.yq.common.YQMessageType;
import org.yhn.yq.server.dao.UserDao;

public class YQServer {
	public YQServer(){
		ServerSocket ss = null;
		try {
			ss=new ServerSocket(5469);
			System.out.println("服务器已启动 in "+new Date());
			while(true){
				Socket s=ss.accept();
				//接受客户端发来的信息
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				User u=(User) ois.readObject();
				YQMessage m=new YQMessage();
				ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
		        if(u.getOperation().equals("login")){ //登录
		        	int account=u.getAccount();
		        	UserDao udao=new UserDao();
		        	boolean b=udao .login(account, u.getPassword());//连接数据库验证用户
					if(b){
						System.out.println("["+account+"]上线了！");
						//更改数据库用户状态
						udao.changeState(account, 1);
						//得到个人信息
						String user=udao.getUser(account);
						//返回一个成功登陆的信息包，并附带个人信息
						m.setType(YQMessageType.SUCCESS);
						m.setContent(user);
						oos.writeObject(m);
						ServerConClientThread cct=new ServerConClientThread(s);//单开一个线程，让该线程与该客户端保持连接
						ManageServerConClient.addClientThread(u.getAccount(),cct);
						cct.start();//启动与该客户端通信的线程
					}else{
						m.setType(YQMessageType.FAIL);
						oos.writeObject(m);
					}
		        }else if(u.getOperation().equals("register")){
		        	UserDao udao=new UserDao();
		        	if(udao.register(u)){
		        		//返回一个注册成功的信息包
						m.setType(YQMessageType.SUCCESS);
						oos.writeObject(m);
		        	}
		        }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

}

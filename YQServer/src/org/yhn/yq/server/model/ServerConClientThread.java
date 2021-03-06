/**
 * 服务器和某个客户端的通信线程
 */
package org.yhn.yq.server.model;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.yhn.yq.common.YQMessage;
import org.yhn.yq.common.YQMessageType;
import org.yhn.yq.server.dao.GroupDao;
import org.yhn.yq.server.dao.UserDao;

public class ServerConClientThread extends Thread {
	Socket s;
	public ServerConClientThread(Socket s){
		this.s=s;
	}

	public void run() {
		while(true){
			ObjectInputStream ois = null;
			YQMessage m = null;
			try {
				ois=new ObjectInputStream(s.getInputStream());
				m=(YQMessage) ois.readObject();
				//对从客户端取得的消息进行类型判断，做相应的处理
				if(m.getType().equals(YQMessageType.COM_MES)){//如果是普通消息包
					DoWhatAndSendMes.sendMes(m);
				}else if(m.getType().equals(YQMessageType.GROUP_MES)){ //如果是群消息
					DoWhatAndSendMes.sendGroupMes(m);
				}else if(m.getType().equals(YQMessageType.GET_ONLINE_FRIENDS)){//如果是请求好友列表
					DoWhatAndSendMes.sendBuddyList(m);
				}else if(m.getType().equals(YQMessageType.DEL_BUDDY)){ //如果是删除好友
					DoWhatAndSendMes.delBuddy(m);
				}
			} catch (Exception e) {
				try {
					s.close();
					ois.close();
				} catch (IOException e1) {	
				}
				e.printStackTrace();
			}
		}
	}
}

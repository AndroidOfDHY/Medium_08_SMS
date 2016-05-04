package org.yhn.yq.server.model;
import java.io.ObjectOutputStream;
import java.util.List;

import org.yhn.yq.common.YQMessage;
import org.yhn.yq.common.YQMessageType;
import org.yhn.yq.server.dao.GroupDao;
import org.yhn.yq.server.dao.UserDao;

public class DoWhatAndSendMes {
	static UserDao udao=new UserDao();
	static GroupDao gdao=new GroupDao();
	
	public static void sendMes(YQMessage m){
		try{
			//ȡ�ý����˵�ͨ���߳�
			ServerConClientThread scc=ManageServerConClient.getClientThread(m.getReceiver());
			ObjectOutputStream oos=new ObjectOutputStream(scc.s.getOutputStream());
			//������˷�����Ϣ
			oos.writeObject(m);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void sendBuddyList(YQMessage m){
		try{
			//�������ݿ⣬���غ����б�˳��Ⱥ�б�
			String res=udao.getBuddy(m.getSender())+","+gdao.getGroup();
			//���ͺ����б��ͻ���
			ServerConClientThread scc=ManageServerConClient.getClientThread(m.getSender());
			ObjectOutputStream oos=new ObjectOutputStream(scc.s.getOutputStream());
			YQMessage ms=new YQMessage();
			ms.setType(YQMessageType.RET_ONLINE_FRIENDS);
			ms.setContent(res);
			oos.writeObject(ms);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void delBuddy(YQMessage m){
		try{
			if(udao.delBuddy(m.getSender(), m.getReceiver())){
				ServerConClientThread scc=ManageServerConClient.getClientThread(m.getSender());
				ObjectOutputStream oos=new ObjectOutputStream(scc.s.getOutputStream());
				YQMessage ms=new YQMessage();
				ms.setType(YQMessageType.SUCCESS);
				oos.writeObject(ms);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void sendGroupMes(YQMessage m){
		try{
			List<Integer> list=gdao.getGroupMember(m.getReceiver());
			for(int raccount:list){
				//��ֻ֧�������ߵ�Ⱥ��Ա������Ϣ
				if(ManageServerConClient.isOnline(raccount)){
					System.out.println(raccount+":"+ManageServerConClient.isOnline(raccount));
					//������Լ��򲻷���
					if(raccount!=m.getSender()){
						System.out.println(m.getSender()+"-"+raccount+"�ѷ���");
						ServerConClientThread scc=ManageServerConClient.getClientThread(raccount);
						ObjectOutputStream oos=new ObjectOutputStream(scc.s.getOutputStream());
						//ֻ��ı�����ߺͷ�������Ϣ
						YQMessage mes=new YQMessage();
						
						mes.setGaccount(m.getReceiver());
						mes.setGroupNick(gdao.getGroupNick(m.getReceiver()));
						mes.setSender(m.getReceiver());
						mes.setSenderAvatar(m.getSenderAvatar());
						mes.setSenderNick(m.getSenderNick());
						mes.setReceiver(raccount);
						mes.setContent(m.getContent());
						mes.setSendTime(m.getSendTime());
						mes.setType(m.getType());
						oos.writeObject(mes);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

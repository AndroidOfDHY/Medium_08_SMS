/**
 * 管理客户端连接的类
 */
package org.yhn.yq.server.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class ManageServerConClient {
	public static HashMap hm=new HashMap<Integer,ServerConClientThread>();
	
	//添加一个客户端通信线程
	public static void addClientThread(int account, ServerConClientThread cc){
		hm.put(account,cc);
	}
	//得到一个客户端通信线程
	public static ServerConClientThread getClientThread(int i){
		return (ServerConClientThread)hm.get(i);
	}
	//返回当前在线人的情况
	public static List getAllOnLineUserid(){
		List list=new ArrayList();
		//使用迭代器完成
		Iterator it=hm.keySet().iterator();
		while(it.hasNext()){
			list.add((int) it.next());
		}
		return list;
	}
	
	public static boolean isOnline(int a){
		List list=new ArrayList();
		//使用迭代器完成
		Iterator it=hm.keySet().iterator();
		while(it.hasNext()){
			int account=(int) it.next();
			if(account==a){
				return true;
			}
		}
		return false;
	}
}

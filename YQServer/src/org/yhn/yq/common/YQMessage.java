package org.yhn.yq.common;
import java.io.Serializable;

public class YQMessage implements Serializable{
	String type;
	int sender;
	String senderNick;
	int senderAvatar;
	int receiver;
	String content;
	String sendTime;
	int gaccount;
	String groupNick;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getSender() {
		return sender;
	}
	public void setSender(int sender) {
		this.sender = sender;
	}
	public String getSenderNick() {
		return senderNick;
	}
	public void setSenderNick(String senderNick) {
		this.senderNick = senderNick;
	}
	public int getSenderAvatar() {
		return senderAvatar;
	}
	public void setSenderAvatar(int senderAvatar) {
		this.senderAvatar = senderAvatar;
	}
	public int getReceiver() {
		return receiver;
	}
	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public int getGaccount() {
		return gaccount;
	}
	public void setGaccount(int gaccount) {
		this.gaccount = gaccount;
	}
	public String getGroupNick() {
		return groupNick;
	}
	public void setGroupNick(String groupNick) {
		this.groupNick = groupNick;
	}
	
	
}

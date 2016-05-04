package org.yhn.yq.client.view;

public class ChatEntity {
	private int avatar;
	private String content;
	private String nick;
	private String time;
	private boolean isLeft;//是否为收到的消息，在左边
	
	public ChatEntity(int avatar,String nick,String content,String time,boolean isLeft){
		this.avatar = avatar;
		this.nick=nick;
		this.content = content;
		this.time = time;
		this.isLeft = isLeft;
	}

	public int getAvatar() {
		return avatar;
	}

	public void setAvatar(int avatar) {
		this.avatar = avatar;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public boolean isLeft() {
		return isLeft;
	}

	public void setLeft(boolean isLeft) {
		this.isLeft = isLeft;
	}
}

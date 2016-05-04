/**
 * 好友实体类
 */
package org.yhn.yq.client.view;
public class BuddyEntity {
	private int avatar;
	private int account;
	private String nick;
	private String trends;
	private int isOnline;
	public BuddyEntity(int avatar,int account,String nick,String trends,int isOnline){
		this.avatar=avatar;
		this.account=account;
		this.nick=nick;
		this.trends=trends;
		this.isOnline=isOnline;
	}

	public int getAvatar() {
		return avatar;
	}

	public void setAvatar(int avatar) {
		this.avatar = avatar;
	}

	public int getAccount() {
		return account;
	}

	public void setAccount(int account) {
		this.account = account;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getTrends() {
		return trends;
	}

	public void setTrends(String trends) {
		this.trends = trends;
	}	

	public int getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(int isOnline) {
		this.isOnline = isOnline;
	}
}

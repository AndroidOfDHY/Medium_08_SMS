package org.yhn.yq.server.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.yhn.yq.common.User;
import org.yhn.yq.server.model.YQServer;

public class UserDao {
	public boolean login(int account, String password) {
		try {
			String sql = "select * from yq_user where uaccount=? and upassword=?";
			Connection conn = DBUtil.getDBUtil().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, account);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs != null && rs.next() == true) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean register(User u) {
		try {
			String sql = "insert into yq_user values(?,?,?,?,?,?,?,?,?)";
			Connection conn = DBUtil.getDBUtil().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, u.getAccount());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getNick());
			ps.setInt(4, u.getAvatar());
			ps.setString(5, u.getTrends());
			ps.setString(6, u.getSex());
			ps.setInt(7, u.getAge());
			ps.setInt(8, u.getLev());
			ps.setString(9, u.getTime());
			int r = ps.executeUpdate();
			if (r > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean delBuddy(int myAccount,int dfAccount){
		try {
			String sql = "delete  from yq_buddy where baccount=? and bbuddy=?";
			Connection conn = DBUtil.getDBUtil().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, myAccount);
			ps.setInt(2, dfAccount);
			int r = ps.executeUpdate();
			if (r > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public String getBuddy(int account){
		String res="";
		try {
			String sql = "select * from yq_buddy where baccount="+account;
			Connection conn = DBUtil.getDBUtil().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String s="";
				String sql2 = "select * from yq_user where uaccount="+rs.getInt("bbuddy");
				Connection conn2 = DBUtil.getDBUtil().getConnection();
				PreparedStatement ps2 = conn2.prepareStatement(sql2);
				ResultSet rs2 = ps2.executeQuery();
				while(rs2.next()){
					s=rs2.getInt("uaccount")+"_"+rs2.getString("unick")+"_"
							+rs2.getString("uavatar")+"_"+rs2.getString("utrends")+"_"+rs2.getInt("uisonline")+" ";
				}
				res+=s;	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	public String getUser(int account){
		String res="";
		try {
			String sql = "select * from yq_user where uaccount="+account;
			Connection conn = DBUtil.getDBUtil().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				res=res+rs.getInt("uaccount")+"_"+rs.getString("unick")+"_"
						+rs.getString("uavatar")+"_"+rs.getString("utrends")+"_"
						+rs.getString("usex")+"_"+rs.getInt("uage")+"_"+rs.getInt("ulev");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public boolean changeState(int account,int state){
		try {
			String sql = "update yq_user set uisonline=? where uaccount=?";
			Connection conn = DBUtil.getDBUtil().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, state);
			ps.setInt(2, account);
			int r = ps.executeUpdate();
			if (r > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
package cn.net.withub.busiGate.ezy.model;

import cn.net.withub.busiGate.loginInfo.LoginInfo;

public class User extends LoginInfo{
	private String username;// ��ʦ����
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}

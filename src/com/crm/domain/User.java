package com.crm.domain;

/**
 * 用户的模块
 * @author Administrator
 */
public class User {
	
	// 主键
	private Long user_id;
	// 登录名称
	private String user_code;
	// 用户姓名
	private String user_name;
	// 密码（保存的时候，需要加密处理）
	private String user_password;
	// 用户的状态 1正常 0暂停
	private String user_state;
	
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public String getUser_code() {
		return user_code;
	}
	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_state() {
		return user_state;
	}
	public void setUser_state(String user_state) {
		this.user_state = user_state;
	}
}














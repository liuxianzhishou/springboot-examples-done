package com.neo.model;

import java.io.Serializable;

import com.neo.enums.UserSexEnum;

//对象的序列化处理非常简单，只需对象实现了Serializable 接口即可（该接口仅是一个标记，没有方法）
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String userName;
	private String passWord;
	private UserSexEnum userSex;
	private String nickName;

	public User() {
		super();
	}

	public User(Long id,String userName, String passWord, UserSexEnum userSex) {
		super();
		this.id=id;
		this.passWord = passWord;
		this.userName = userName;
		this.userSex = userSex;
	}

	// 右键 Generate -> Setter and Getter -> Shift全选 -> ok 生成如下代码
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public UserSexEnum getUserSex() {
		return userSex;
	}

	public void setUserSex(UserSexEnum userSex) {
		this.userSex = userSex;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", userName='" + userName + '\'' +
				", passWord='" + passWord + '\'' +
				", userSex=" + userSex +
				", nickName='" + nickName + '\'' +
				'}';
	}
}
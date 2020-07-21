package com.pdt.moduleorder9002.model;

/**
 * 用户模型
 * @author Louis
 * @date Jun 29, 2019
 */
public class User {

    private Long id;

    private String username;

    private String password;

	private String token;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", token='" + token + '\'' +
				'}';
	}
}
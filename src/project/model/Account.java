package project.model;

public class Account {
    private String name;
    private String email;
    private String password;
    private String salt;
    private byte[] avatar;
    private int loginFailTime;

    
    public Account(String name, String email, String password, String salt, byte[] avatar, int loginFailTime) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.avatar = avatar;
        this.loginFailTime = loginFailTime;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

	public byte[] getAvatar() {
		return avatar;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}

	public int getLoginFailTime() {
		return loginFailTime;
	}
}


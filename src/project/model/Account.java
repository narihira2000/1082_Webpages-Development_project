package project.model;

public class Account {
    private String name;
    private String email;
    private String password;
    private String salt;
    private byte[] avatar;

    
    public Account(String name, String email, String password, String salt, byte[] avatar) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.avatar = avatar;
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
}


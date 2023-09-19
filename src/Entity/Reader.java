package Entity;

public class Reader {
	private String readerid;
    private String readername;
	private String password;
    private String sex;
    private int status;
    private String mail;
    private String tel;
    private int borrow;

    public Reader() {
    }
    public String getReaderid() {
		return readerid;
	}

	public void setReaderid(String readerid) {
		this.readerid = readerid;
	}
    public String getReadername() {
        return readername;
    }

    public void setReadername(String readername) {
        this.readername = readername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMail() {
        return mail;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getBorrow() {
        return borrow;
    }

    public void setBorrow(int borrow) {
        this.borrow = borrow;
    }
 
}

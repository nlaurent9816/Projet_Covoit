package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Login {

	@Id
	private String login;
	
	private String password;// à hasher
	
	@OneToOne
	private InfoUtilisateur infos;//A un login, il y a un utilisateur (MD)

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public InfoUtilisateur getInfos() {
		return infos;
	}

	public void setInfos(InfoUtilisateur infos) {
		this.infos = infos;
	}
	


}

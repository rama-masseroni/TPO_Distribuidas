package modelo;

import daos.LoginDAO;
import views.LoginView;

public class Login {
	private String usuario;
	private String password;
	private String documento;
	
	public Login () {}

	public Login(String usuario, String password, String persona) {
		super();
		this.usuario = usuario;
		this.password = password;
		this.documento = persona;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String persona) {
		this.documento = persona;
	}
	
	public LoginView toView() {
		return new LoginView(usuario, password, documento);
	}
	
	public void save() {
		new LoginDAO().save(this);
	}
	
	public void update() {
		new LoginDAO().update(this);
	}
}

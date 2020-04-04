package views;



public class LoginView {
	private String usuario;
	private String password;
	private String documento;
	
	public LoginView () {}

	public LoginView(String usuario, String password, String persona) {
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
}

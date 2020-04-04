package exceptions;

public class LoginException extends Exception {

	private static final long serialVersionUID = -2835873129858130160L;

	public LoginException(String mensaje) {
		super(mensaje);
	}
}

package uade.edu.tpo;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private String nomSession, apeSession, fdnSession;
	private int dniSession;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/verificarLogin", method = RequestMethod.GET, produces = {"application/json"})
	public @ResponseBody<json> boolean verificarLogin(@RequestParam(value="usuario", required = true) String usuario,  
			@RequestParam(value="password", required = true) String password) throws JsonProcessingException{
		boolean b = false;
		Usuario usr= Controlador.getInstancia().verficarLogin(usuario, password);
		if(usr != null) {
			b= true;
			this.nomSession= usr.getNombre();
			this.apeSession= usr.getApellido();
			this.fdnSession= usr.getFechaDeNacimiento();
			this.dniSession = usr.getDni();
		}
//		System.out.println(this.usuario);
		return b;
	}

	
}

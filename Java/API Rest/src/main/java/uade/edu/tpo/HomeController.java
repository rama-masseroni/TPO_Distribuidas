package uade.edu.tpo;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import controlador.Controlador;
import daos.UsuarioDAO;
import modelo.Paciente;
import modelo.Turno;
import modelo.Usuario;
import views.RolView;
import views.TurnoView;
import views.UsuarioView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private UsuarioView userSession;
	private ObjectMapper om = new ObjectMapper();
	private String res = null;

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

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}
	
	@RequestMapping(value = "/buscarTurnos", method = RequestMethod.POST, produces = { "application/json" })
	public @ResponseBody <json> String buscarTurno(@RequestParam(value = "dia", required = true) Date dia,
			@RequestParam(value = "esp", required = true) String especialidad) throws JsonProcessingException{
		System.out.println("Active!");
		List<TurnoView> turnos = Controlador.getInstancia().buscarTurnos(dia, especialidad);
		for(TurnoView tv : turnos) System.out.println(tv.toString());
		return om.writeValueAsString(turnos);
//		return om.writeValueAsString(Controlador.getInstancia().buscarTurnos(dia, especialidad));
	}


	@RequestMapping(value = "/getUserByID", method = RequestMethod.POST, produces = { "application/json" })
	public @ResponseBody <json> String getUsuarioByID(@RequestParam(value = "id", required = true) int id)
			throws JsonProcessingException {
		Usuario usr = new UsuarioDAO().getUsuarioByID(id);
		if (usr != null)
			res = om.writeValueAsString(usr);
		return res;
	}
	

	@RequestMapping(value = "/getMedicos", method = RequestMethod.GET, produces = { "application/json" })
	public @ResponseBody <json> String getUsuarioByID() throws JsonProcessingException {
		List<UsuarioView> select = Controlador.getInstancia().getAllMeds();
		return om.writeValueAsString(select);

	}

	@RequestMapping(value = "/getEspecialidades", method = RequestMethod.GET, produces = { "application/json" })
	public @ResponseBody <json> String getEspecialidades() throws JsonProcessingException {
		return om.writeValueAsString(Controlador.getInstancia().getEspecialidades());

	}

	@RequestMapping(value = "/getRoles", method = RequestMethod.GET, produces = { "application/json" })
	public @ResponseBody <json> String getRoles() throws JsonProcessingException {
		// 0 = Paciente; 1 = Médico; 2 = ambos.
		List<RolView> roles = userSession.getRoles();
		switch (roles.size()) {
		case 1:
			if (roles.get(0).getNombreRol().equals("Paciente"))
				return om.writeValueAsString(0);
			else if (roles.get(0).getNombreRol().equals("Medico"))
				return om.writeValueAsString(1);
			else
				return om.writeValueAsString(null);

		case 2:
			return om.writeValueAsString(2);
		default:
			return om.writeValueAsString(null);
		}

	}

	@RequestMapping(value = "/misTurnos", method = RequestMethod.GET, produces = { "application/json" })
	public @ResponseBody <json> String misTurnos() throws JsonProcessingException {
		List<TurnoView> res = Controlador.getInstancia().proxTurnosPaciente(userSession.getRoles().get(0).getIdUsr());
		return om.writeValueAsString(res);
	}

	@RequestMapping(value = "/verificarLogin", method = RequestMethod.POST, produces = { "application/json" })
	public @ResponseBody <json> String verificarLogin(@RequestParam(value = "usuario", required = true) String usuario,
			@RequestParam(value = "password", required = true) String password) throws JsonProcessingException {
		boolean b = Controlador.getInstancia().verificarLogin(usuario, password);
		if (b) {
			userSession = Controlador.getInstancia().inicioDeSesion(usuario, password);
			return om.writeValueAsString(userSession);
		} else
			return om.writeValueAsString(null);
	}

}

package uade.edu.tpo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import controlador.Controlador;
import daos.UsuarioDAO;
import modelo.Usuario;
import utilitarios.CalculosFechas;
import views.RolView;
import views.TurnoView;
import views.UsuarioView;
import modelo.Medico;

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

	@RequestMapping(value = "/aColaDeEsperaGenerico", method = RequestMethod.POST, produces = { "application/json" })
	public @ResponseBody <json> String aColaDeEspera(
			@RequestParam(value = "especialidad", required = true) String especialidad,
			@RequestParam(value = "idPaciente", required = true) int idPaciente,
			@RequestParam(value = "idMedico", required = false) int idMedico) throws JsonProcessingException {
		String res;
		if (idMedico != 1)
			res = Controlador.getInstancia().pacienteAColaDeEspera(especialidad, idPaciente, idMedico);
		else
			res = Controlador.getInstancia().pacienteAColaDeEspera(especialidad, idPaciente, 1);
		return om.writeValueAsString(res);
	}

	@RequestMapping(value = "/getPacientesEsperando", method = RequestMethod.POST, produces = { "application/json" })
	public @ResponseBody <json> String getPacientesEsperando(
			@RequestParam(value = "especialidad", required = true) String especialidad) throws JsonProcessingException {
		int count = Controlador.getInstancia().countPacientesEsperando(especialidad, 1);
		return om.writeValueAsString(count);
	}

	@RequestMapping(value = "/uploadMultiples", method = RequestMethod.POST, produces = { "application/json" })
	public @ResponseBody <json> String uploadTurno(@RequestParam(value = "dias", required = true) List<String> listDias,
			@RequestParam(value = "hora", required = true) String hora,
			@RequestParam(value = "especialidad", required = true) String especialidad) throws JsonProcessingException {
		Map<String, List<String>> horarios = new HashMap<String, List<String>>();
		List<String> assignedHour = new ArrayList<String>();
		assignedHour.add(hora);
		for (String day : listDias) {
			horarios.put(day, assignedHour);
		}
		String resultado = Controlador.getInstancia().agendarPeriodoMedico(userSession.getRoles().get(0).getIdUsr(),
				especialidad, horarios);
		return om.writeValueAsString(resultado);
	}

	@RequestMapping(value = "/uploadTurno", method = RequestMethod.POST, produces = { "application/json" })
	public @ResponseBody <json> String uploadTurno(@RequestParam(value = "dia", required = true) String dia,
			@RequestParam(value = "hora", required = true) String hora,
			@RequestParam(value = "especialidad", required = true) String especialidad) throws JsonProcessingException {
		String resultado = Controlador.getInstancia()
				.agendarNuevoTurnoIndividual(userSession.getRoles().get(0).getIdUsr(), especialidad, dia, hora);
		return om.writeValueAsString(resultado);
	}

	@RequestMapping(value = "/getEspByMed", method = RequestMethod.GET, produces = { "application/json" })
	public @ResponseBody <json> String getEspByMed() throws JsonProcessingException {
		List<String> especialidades = new Medico(userSession.getRoles().get(0).getIdUsr()).getEspecialidades();
		return om.writeValueAsString(especialidades);
	}

	@RequestMapping(value = "/reservarTurno", method = RequestMethod.POST, produces = { "application/json" })
	public @ResponseBody <json> String reservarTurno(@RequestParam(value = "idM", required = true) int idMed,
			@RequestParam(value = "esp", required = true) String especialidad,
			@RequestParam(value = "fecha", required = true) String fecha,
			@RequestParam(value = "hora", required = true) String hora) throws JsonProcessingException {
		String answer = Controlador.getInstancia().reservarTurno(userSession.getId(), idMed, especialidad, fecha, hora);
		System.out.println(answer);
		return om.writeValueAsString(answer);
	}

	@RequestMapping(value = "/buscarTurnos", method = RequestMethod.POST, produces = { "application/json" })
	public @ResponseBody <json> String buscarTurno(@RequestParam(value = "dia", required = true) Date dia,
			@RequestParam(value = "esp", required = true) String especialidad,
			@RequestParam(value = "idMed", required = true) int idMed) throws JsonProcessingException {
//		System.out.println("Active!");
		Date aux = new Date(dia.getTime());
		SimpleDateFormat formatFecha = new SimpleDateFormat("YYYY-MM-dd");
		String fecha = formatFecha.format(dia);
		SimpleDateFormat formatHora = new SimpleDateFormat("kk:mm");
		String hour = formatHora.format(dia);
		TurnoView turno = Controlador.getInstancia().buscarTurnoIndividual(idMed, fecha, hour);
		System.out.println(turno.toString());
		List<TurnoView> listaTurnos = new ArrayList<TurnoView>();
		if (turno.getId() == 0) {
			aux = CalculosFechas.getInstancia().sumarMesesAFecha(aux, 2);
			System.out.println(aux.toString());
			List<TurnoView> turnosEnDia = Controlador.getInstancia().buscarTurnos(aux, especialidad, idMed);
			for (TurnoView ted : turnosEnDia) {
				System.out.println(ted.toString());
				listaTurnos.add(ted);
			}
		} else
			listaTurnos.add(turno);
		return om.writeValueAsString(listaTurnos);
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
		// 1 = Paciente; 2 = Médico; 3 = ambos.
		List<RolView> roles = userSession.getRoles();
		switch (roles.size()) {
		case 1:
			if (roles.get(0).getNombreRol().equals("Paciente"))
				return om.writeValueAsString(1);
			else if (roles.get(0).getNombreRol().equals("Medico"))
				return om.writeValueAsString(2);
			else
				return om.writeValueAsString(0);

		case 2:
			return om.writeValueAsString(3);
		default:
			return om.writeValueAsString(0);
		}

	}

	@RequestMapping(value = "/misTurnos", method = RequestMethod.GET, produces = { "application/json" })
	public @ResponseBody <json> String misTurnos() throws JsonProcessingException {
		List<TurnoView> res = new ArrayList<TurnoView>();
		if (userSession.getRoles().size() == 1 && userSession.getRoles().get(0).getNombreRol().equals("Paciente")) {
			res = Controlador.getInstancia().proxTurnosPaciente(userSession.getRoles().get(0).getIdUsr());
		}
		if (userSession.getRoles().size() == 1 && userSession.getRoles().get(0).getNombreRol().equals("Medico")) {
			res = Controlador.getInstancia().proxTurnosMedico(userSession.getRoles().get(0).getIdUsr());
		}
		return om.writeValueAsString(res);
	}

	@RequestMapping(value = "/verificarLogin", method = RequestMethod.POST, produces = { "application/json" })
	public @ResponseBody <json> String verificarLogin(@RequestParam(value = "usuario", required = true) String usuario,
			@RequestParam(value = "password", required = true) String password) throws JsonProcessingException {
		boolean b = Controlador.getInstancia().verificarLogin(usuario, password);
		if (b) {
			userSession = Controlador.getInstancia().inicioDeSesion(usuario, password);
			System.out.println(userSession.getNombre() + ' ' + userSession.getApellido());
			return om.writeValueAsString(userSession);
		} else
			return om.writeValueAsString(null);
	}

}

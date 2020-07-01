package views;

public class TurnoView {
	
	private int id;
	private String fecha, hora, especialidad, estado;
	private int idUsrMed, idUsrPac;
	private UsuarioView paciente, medico;
	
	public TurnoView() {
	}
	
//	public TurnoView(int id, String fecha, String hs, String esp, String estado, int idUsrMed, int idUsrPac) {
//		this.id = id;
//		this.fecha = fecha;
//		this.hora = hs;
//		this.especialidad = esp;
//		this.estado = estado;
//		this.idUsrMed = idUsrMed;
//		this.idUsrPac = idUsrPac;
//	}
	
	public TurnoView(int id, String fecha, String hs, String esp, String estado, int idUsrPac, UsuarioView paciente, int idUsrMed, UsuarioView medico) {
		this.id = id;
		this.fecha = fecha;
		this.hora = hs;
		this.especialidad = esp;
		this.estado = estado;
		this.idUsrPac = idUsrPac;
		this.paciente = paciente;
		this.idUsrMed = idUsrMed;
		this.medico = medico;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getFecha() {
		return fecha;
	}
	
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public String getHora() {
		return hora;
	}
	
	public void setHora(String hora) {
		this.hora = hora;
	}
	
	public String getEspecialidad() {
		return this.especialidad;
	}
	
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	
	public int getIdUsrMed() {
		return idUsrMed;
	}
	
	public void setIdUsrMed(int idUsrMed) {
		this.idUsrMed = idUsrMed;
	}
	
	public int getIdUsrPac() {
		return idUsrPac;
	}
	
	public void setIdUsrPac(int idUsrPac) {
		this.idUsrPac = idUsrPac;
	}
	
	public String getEstado() {
		return this.estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}

	public UsuarioView getPaciente() {
		return paciente;
	}

	public void setPaciente(UsuarioView paciente) {
		this.paciente = paciente;
	}

	public UsuarioView getMedico() {
		return medico;
	}

	public void setMedico(UsuarioView medico) {
		this.medico = medico;
	}
	
	
	/*
	public String getUsernamePac() {
		return paciente.getUsername();
	}
	
	public String getNombrePac() {
		return paciente.getNombre();
	}
	
	public String getApellPac() {
		return paciente.getApellido();
	}
	
	public String getUsernameMed() {
		return medico.getUsername();
	}
	
	public String getNombreMed() {
		return medico.getNombre();
	}
	
	public String getApellMed() {
		return medico.getApellido();
	}
	*/

	@Override
	public String toString() {
		return "TurnoView [id=" + id + ", fecha=" + fecha + ", hora=" + hora + ", especialidad=" + especialidad
				+ ", estado=" + estado + ", idUsrMed=" + idUsrMed + ", idUsrPac=" + idUsrPac + ", paciente=" + paciente
				+ ", medico=" + medico + "]";
	}

	/*
	@Override
	public String toString() {
		return "TurnoView [id=" + id + ", fecha=" + fecha + ", hora=" + hora + ", especialidad=" + especialidad
				+ ", estado=" + estado + ", idUsrMed=" + idUsrMed + ", idUsrPac=" + idUsrPac + "]";
	}
	*/
	
}

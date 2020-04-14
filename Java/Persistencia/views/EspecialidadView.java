package views;

public class EspecialidadView {

	private int id;
	private String nomEspe;

	public EspecialidadView() {}

	public EspecialidadView(int id, String nomEspe) {
		super();
		this.id = id;
		this.nomEspe = nomEspe;
	}

	public int getId() {
		return id;
	}

	public String getNomEspe() {
		return nomEspe;
	}

	@Override
	public String toString() {
		return "EspecialidadView [id=" + id + ", nomEspe=" + nomEspe + "]";
	}
	
	
	
}

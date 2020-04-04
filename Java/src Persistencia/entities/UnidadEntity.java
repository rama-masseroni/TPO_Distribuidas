package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="unidades")
public class UnidadEntity {

	@Id
	@ManyToOne
	@Column (name = "identificador")
	private int identificador;
	@Column (name = "piso")
	private String piso;
	@Column (name="numero")
	private String numero;
	@Column (name="habitado")
	private boolean habitado;
	@ManyToOne
	@JoinColumn (name="codigoedificio")
	private EdificioEntity edificio;
	
	public UnidadEntity() { }

	public UnidadEntity(int identificador, String piso, String numero, boolean habitado, EdificioEntity edificio) {
		super();
		this.identificador = identificador;
		this.piso = piso;
		this.numero = numero;
		this.habitado = habitado;
		this.edificio = edificio;
	}


	public String toStringNum() {
		return "ClubEntity [numero=" + numero + "]";
	}

	public String toStringHabitado() {
		return "ClubEntity [habitado=" + habitado + "]";
	}

	public int getIdentificador() {
		return identificador;
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public boolean isHabitado() {
		return habitado;
	}

	public void setHabitado(boolean habitado) {
		this.habitado = habitado;
	}

	public EdificioEntity getEdificio() {
		return edificio;
	}

	public void setEdificio(EdificioEntity edificio) {
		this.edificio = edificio;
	}



}

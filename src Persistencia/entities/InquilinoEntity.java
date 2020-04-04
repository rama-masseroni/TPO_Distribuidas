package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="duenios")
public class InquilinoEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="id")
	private int id;
	
	@Column (name="identificador")
	private int identificador;
	
	@Column (name="documento")
	private String documento;

	public InquilinoEntity() {}

	public InquilinoEntity(int id, int identificador, String documento) {
		super();
		this.id = id;
		this.identificador = identificador;
		this.documento = documento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIndentificador() {
		return identificador;
	}

	public void setIndentificador(int identificador) {
		this.identificador = identificador;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}
		
}

package models;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class LocaliteActivitePk {	
	
	  public LocaliteActivitePk(Long idLocalite, Long idActivite) {
	
		this.idLocalite = idLocalite;
		this.idActivite = idActivite;
	}
	@Column(name = "ID_LOCALITE", nullable=false)
	private Long idLocalite;
	  @Column(name = "ID_ACTIVITE", nullable=false)
	private Long idActivite;
	public Long getIdLocalite() {
		return idLocalite;
	}
	public void setIdLocalite(Long idLocalite) {
		this.idLocalite = idLocalite;
	}
	public Long getIdActivite() {
		return idActivite;
	}
	public void setIdActivite(Long idActivite) {
		this.idActivite = idActivite;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idActivite == null) ? 0 : idActivite.hashCode());
		result = prime * result
				+ ((idLocalite == null) ? 0 : idLocalite.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LocaliteActivitePk other = (LocaliteActivitePk) obj;
		if (idActivite == null) {
			if (other.idActivite != null)
				return false;
		} else if (!idActivite.equals(other.idActivite))
			return false;
		if (idLocalite == null) {
			if (other.idLocalite != null)
				return false;
		} else if (!idLocalite.equals(other.idLocalite))
			return false;
		return true;
	}
	
	  

}

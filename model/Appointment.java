package dentalappt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(nullable = false)
	private int dentistId;
	@Column(nullable = false)
	private int patientId;
	@Column(nullable = false)
	private long startTime;
	@Column(nullable = false)
	private long endTime;

	public Appointment() {
		super();
	}

	public Appointment(int dentistId, int patientId, long startTime,
			long endTime) {
		super();
		this.dentistId = dentistId;
		this.patientId = patientId;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDentistId() {
		return dentistId;
	}

	public void setDentistId(int dentistId) {
		this.dentistId = dentistId;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "Appointment [id=" + id + ", dentistId=" + dentistId
				+ ", patientId=" + patientId + ", startTime=" + startTime
				+ ", endTime=" + endTime + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dentistId;
		result = prime * result + (int) (endTime ^ (endTime >>> 32));
		result = prime * result + id;
		result = prime * result + patientId;
		result = prime * result + (int) (startTime ^ (startTime >>> 32));
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
		Appointment other = (Appointment) obj;
		if (dentistId != other.dentistId)
			return false;
		if (endTime != other.endTime)
			return false;
		if (id != other.id)
			return false;
		if (patientId != other.patientId)
			return false;
		if (startTime != other.startTime)
			return false;
		return true;
	}

}

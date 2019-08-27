package dentalappt.persistance;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import dentalappt.model.Appointment;
//@Component
public interface AppointmentRepository extends CrudRepository<Appointment, Integer>  {
	List<Appointment> findByDentistId(int dentistId);
}

package dentalappt.web;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dentalappt.exception.AppointmentNotFoundException;
import dentalappt.model.Appointment;
import dentalappt.persistance.AppointmentRepository;

@RestController
@RequestMapping("/dentalAppointments")
public class AppointmentController {
	
	@Autowired
	private AppointmentManager appointmentManager;
	
	/*@Autowired
	private AppointmentRepository appointmentRepository;*/
	
	@GetMapping("/{id}")
	public Appointment findAppointmentByID(@PathVariable Integer id) {
		return appointmentManager.findAppointment(id);
		/*return appointmentRepository.findById(id)
				.orElseThrow(AppointmentNotFoundException::new);*/
	}
	
	@GetMapping
	public Iterable<Appointment> findAllAppointments() {
		return appointmentManager.findAllAppointments();
		/*Iterable<Appointment> appts = appointmentRepository.findAll();
		System.out.println("appt details are : ");
		appts.forEach(appt -> {
			System.out.println("appt ID is : "+ appt.getId());
			System.out.println("appt Doc is : "+ appt.getDoctorId());
		});
		return appts;*/
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Appointment makeAppointment (@RequestBody Appointment appointment) {
		return appointmentManager.createAppointment(appointment);
		/*System.out.println("Appointment is - \n" + appointment.toString());
		return appointmentRepository.save(appointment);*/
	}

}

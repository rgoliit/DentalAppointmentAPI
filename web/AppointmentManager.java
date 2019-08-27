package dentalappt.web;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import dentalappt.exception.AppointmentNotFoundException;
import dentalappt.model.Appointment;
import dentalappt.persistance.AppointmentRepository;

@Component
/*@DependsOn({"appointmentRepository"})*/
public class AppointmentManager {
	
	@Autowired
	AppointmentRepository appointmentRepository;
	
	private int thirtyMinutes = 30*60*1000; 
	private int minRounder = 60*1000;
	
	public Appointment createAppointment(Appointment appointment) {
		System.out.println("\nAppointment details submitted are : " + appointment.toString());
		
		validateRequest(appointment);
		
		//making sure 2 appointments(web-requests/threads) for a dentist are not scheduled at the same apartment startTime in MINUTES.
		synchronized(this) {
			checkForApptCollision(appointment);
			
			appointment= appointmentRepository.save(appointment);
		}
		
		return appointment;
	}
	
	//validating if startTime and endTime pass the criteria, of greater than current-time
	//and if endTime-startTimeis greater than 30 minutes.
	void validateRequest(Appointment appointment) {
		System.out.println("\nvalidateRequest START");
		long currentTime = Calendar.getInstance().getTimeInMillis();
		long startTime = appointment.getStartTime()*1000;
		long endTime = appointment.getEndTime()*1000;
		System.out.println("Current calendar time in milliseconds is : " + currentTime);
		System.out.println("Start Time in milliseconds is : " + startTime);
		System.out.println("End Time in milliseconds is : " + endTime);
		
		if (startTime <= currentTime && endTime <= currentTime) {
			System.out.println("Not creating an appointment as the start-time and the end-time are not greater than the current time.");
			throw new AppointmentNotFoundException("Not creating an appointment as the start-time and the end-time are not greater than the current time.");
		}
		if (startTime <= currentTime) {
			System.out.println("Not creating an appointment as the startTime is not greater than the current time.");
			throw new AppointmentNotFoundException("Not creating an appointment as the startTime is not greater than the current time.");
		} 
		if (endTime <= currentTime) {
			System.out.println("Not creating an appointment as the endTime is not greater than the current time.");
			throw new AppointmentNotFoundException("Not creating an appointment as the endTime is not greater than the current time.");
		}
		//System.out.println("
		if ((endTime - startTime) < thirtyMinutes) {
			System.out.println("A dental appointment (endTime minus startTime) should be at least 30 minutes");
			throw new AppointmentNotFoundException("A dental appointment (endTime minus startTime) should be at least 30 minutes");
		}
	}
	
	// 2 appointments cannot start for a dentist at the same minute (same-time)
	// though we save appointment start and end times in seconds, its logical to evaluate start-time overlap by minutes.
	// so, the below logic checks for start-time occurrence by minutes.
	void checkForApptCollision(Appointment appointment) {
		System.out.println("\ncheckForApptCollision START");
		List<Appointment> curAppts = appointmentRepository.findByDentistId(appointment.getDentistId());
		System.out.println("Below are the current appointments retrieved for this detistID - " + appointment.getDentistId());
		curAppts.forEach(appt -> {
			System.out.println(appt.toString());
			// checking for startTime match by rounding them to minutes.
			if (appt.getStartTime()/60 == appointment.getStartTime()/60){
				System.out.println("The requested dentist is already booked for an appointment at this requested start-time in minutes.  \nPlease select another start-time.");
				throw new AppointmentNotFoundException("The requested dentist is already booked for an appointment at this requested start-time in minutes.  Please select another start-time.");
			}
		});
	}
	
	public Appointment findAppointment(int id) {
		Appointment appointment = appointmentRepository.findById(id)
				.orElseThrow(AppointmentNotFoundException::new);
		System.out.println("Appointment retrieved is : " + appointment.toString());
		return appointment;
	}
	
	public Iterable<Appointment> findAllAppointments() {
		System.out.println("\nfindAllAppointments START");
		//return appointmentRepository.findAll();
		Iterable<Appointment> appts = appointmentRepository.findAll();
		System.out.println("All the appointments details retrieved are : ");
		appts.forEach(appt -> System.out.println(appt.toString()));
		return appts;
	}

}

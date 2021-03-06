package com.bookingOffice.www.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.bookingOffice.www.DAO.Flight;
import com.bookingOffice.www.services.AdministratorService;
import com.bookingOffice.www.util.CitiesList;

@Named
@Scope("session")
public class AdministratorBean {
	@Inject
	AdministratorService administratorService;

	private List<Flight> flights = null;
	private Flight flight = null;

	private Date departureTime = new Date();
	private Date arrivalTime = new Date();
	
	private String releaseMessage = "";
	
	private Map<String, String> cities = new CitiesList().getCities();

	public String getCityFromCode(String code) {
		for (Map.Entry<String, String> city : cities.entrySet()) {
			if (city.getValue().equals(code)) return city.getKey();
		}
		return "";		
	}
	
	public String formatDate(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");						
		String formatted = dateFormat.format(date).toString();
		return formatted;
	}
	
	public void refreshFlights() {
		flights = administratorService.getFlights();
	}

	public String releaseUnpaidTickets() {
		administratorService.releaseUnpaidTickets();
		releaseMessage = "Tickets been released successfully";
		return "Administrator";
	}

	public String editFlight(String stringId) {
		releaseMessage = "";
		int id = Integer.parseInt(stringId);
		if (id != 0) {
			flight = administratorService.getFlight(id);
		} else {
			flight = new Flight();
		}
		departureTime = flight.getDepartureTime();
		arrivalTime = flight.getArrivalTime();
		return "newFlight";
	}

	public String addFlight() {
		flight.setDepartureTime(Timestamp.from(departureTime.toInstant()));
		flight.setArrivalTime(Timestamp.from(arrivalTime.toInstant()));
		administratorService.addFlight(flight);
		return "Administrator";
	}

	public String deleteFlight() {
		administratorService.deleteFlight(flight.getId());
		return "Administrator";
	}

	public AdministratorBean() {
		flight = new Flight();
	}

	/**
	 * @return the flights
	 */
	public List<Flight> getFlights() {
		return flights;
	}

	/**
	 * @param flights
	 *            the flights to set
	 */
	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	/**
	 * @return the flight
	 */
	public Flight getFlight() {
		return flight;
	}

	/**
	 * @param flight
	 *            the flight to set
	 */
	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	/**
	 * @return the departureDate
	 */
	public Date getDepartureDate() {
		return departureTime;
	}

	/**
	 * @param departureDate
	 *            the departureDate to set
	 */
	public void setDepartureDate(Date departureDate) {
		this.departureTime = departureDate;
	}

	/**
	 * @return the arrivalDate
	 */
	public Date getArrivalDate() {
		return arrivalTime;
	}

	/**
	 * @param arrivalDate
	 *            the arrivalDate to set
	 */
	public void setArrivalDate(Date arrivalDate) {
		this.arrivalTime = arrivalDate;
	}

	/**
	 * @return the departureTime
	 */
	public Date getDepartureTime() {
		return departureTime;
	}

	/**
	 * @param departureTime
	 *            the departureTime to set
	 */
	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}

	/**
	 * @return the arrivalTime
	 */
	public Date getArrivalTime() {
		return arrivalTime;
	}

	/**
	 * @param arrivalTime
	 *            the arrivalTime to set
	 */
	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	/**
	 * @return the releaseMessage
	 */
	public String getReleaseMessage() {
		return releaseMessage;
	}

	/**
	 * @param releaseMessage the releaseMessage to set
	 */
	public void setReleaseMessage(String releaseMessage) {
		this.releaseMessage = releaseMessage;
	}

	/**
	 * @return the cities
	 */
	public Map<String, String> getCities() {
		return cities;
	}

	/**
	 * @param cities the cities to set
	 */
	public void setCities(Map<String, String> cities) {
		this.cities = cities;
	}

}

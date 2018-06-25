package ru.novand.orientexpress.controllers;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.novand.orientexpress.domain.dto.OrderDTO;
import ru.novand.orientexpress.domain.entities.Passenger;
import ru.novand.orientexpress.domain.entities.Ticket;
import ru.novand.orientexpress.services.PassengerService;
import ru.novand.orientexpress.services.TicketService;

import javax.servlet.http.HttpServletRequest;

@RestController
public class OrderController {

	DateTimeFormatter ddMMyyyyformatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

	@Autowired
    private PassengerService passengerService;

	@Autowired
	private TicketService ticketService;

	/**
	 * Handle request to the default page
	 */
//	@RequestMapping(value = "/lk", method = RequestMethod.GET)
//	public String viewPdf() {
//		return "lk";
//	}


	@RequestMapping(value = "/downloadPDF", method = RequestMethod.GET)
	public ModelAndView downloadPDF(HttpServletRequest request) {

		String username=request.getUserPrincipal().getName();

		Passenger passenger = passengerService.getPassengerByUserName(username);

		List<Ticket> tickets = ticketService.getTicketByPassengerID(passenger.getIdpassenger());

		Ticket ticket = tickets.get(0);

		Format formatter = new SimpleDateFormat("dd.MM.yyyy");
		String depdate = formatter.format(ticket.getDeparturedate());

		List<OrderDTO> listBooks = new ArrayList<>(1);
		listBooks.add(new OrderDTO(passenger.getFamilyname(), passenger.getFirstname(), passenger.getBirthdate().format(ddMMyyyyformatter), ticket.getTrainCode(), depdate));

		// return a view which will be resolved by an excel view resolver
		return new ModelAndView("pdfView", "listBooks", listBooks);
	}
}
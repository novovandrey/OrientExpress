package ru.novand.orientexpress.controllers;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.novand.orientexpress.domain.dto.OrderDTO;
import ru.novand.orientexpress.domain.entities.Passenger;
import ru.novand.orientexpress.domain.entities.Ticket;
import ru.novand.orientexpress.services.impl.PassengerServiceImpl;
import ru.novand.orientexpress.services.impl.TicketServiceImpl;
import ru.novand.orientexpress.utils.Routes;

import javax.servlet.http.HttpServletRequest;

@RestController
public class OrderController {

	DateTimeFormatter ddMMyyyyformatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
	//TODO divide services OrderController
	@Autowired
    private PassengerServiceImpl passengerService;

	@Autowired
	private TicketServiceImpl ticketService;

	@GetMapping (Routes.downloadPDF)
	public ModelAndView downloadPDF(HttpServletRequest request) {

		String username=request.getUserPrincipal().getName();
		List<OrderDTO> listBooks=null;
		if (!username.isEmpty())
		{

			Passenger passenger = passengerService.getPassengerByUserName(username);

			List<Ticket> tickets = ticketService.getTicketByPassengerID(passenger.getIdpassenger());

			Ticket ticket = tickets.get(0);

			Format formatter = new SimpleDateFormat("dd.MM.yyyy");
			String depdate = formatter.format(ticket.getDeparturedate());

			listBooks = new ArrayList<>(1);
			listBooks.add(new OrderDTO(passenger.getFamilyname(), passenger.getFirstname(), passenger.getBirthdate().toString(), ticket.getTrainCode(), depdate));

		}

		return new ModelAndView("pdfView", "listBooks", listBooks);
	}
}
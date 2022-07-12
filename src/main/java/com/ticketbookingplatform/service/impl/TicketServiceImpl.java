/**
 * 
 */
package com.ticketbookingplatform.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketbookingplatform.adapter.TicketAdapter;
import com.ticketbookingplatform.dto.BookTicketRequestDto;
import com.ticketbookingplatform.dto.TicketDto;
import com.ticketbookingplatform.exception.DependencyException;
import com.ticketbookingplatform.model.OfferEntity;
import com.ticketbookingplatform.model.ShowEntity;
import com.ticketbookingplatform.model.ShowSeatsEntity;
import com.ticketbookingplatform.model.TicketEntity;
import com.ticketbookingplatform.model.UserEntity;
import com.ticketbookingplatform.repository.OfferRepository;
import com.ticketbookingplatform.repository.ShowRepository;
import com.ticketbookingplatform.repository.TicketRepository;
import com.ticketbookingplatform.repository.UserRepository;
import com.ticketbookingplatform.service.TicketService;

import lombok.extern.log4j.Log4j2;

/**
 * @author Sonal Kumbhare
 *
 * @since 10-July-2022
 */
@Log4j2
@Service
public class TicketServiceImpl implements TicketService {

	private static final Logger logger = LoggerFactory.getLogger(TicketServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ShowRepository showRepository;

	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private OfferRepository offerRepository;

	@Override
	public TicketDto bookTicket(BookTicketRequestDto bookTicketRequestDto) {

		logger.info("Searching User by id: " + bookTicketRequestDto.getUserId());

		Optional<UserEntity> optionalUser = userRepository.findById(bookTicketRequestDto.getUserId());

		if (!optionalUser.isPresent()) {
			throw new DependencyException("User Not Found with ID: " + bookTicketRequestDto.getUserId() + " to book ticket");
		}

		logger.info("Searching Show by id: " + bookTicketRequestDto.getShowId());

		Optional<ShowEntity> optionalShow = showRepository.findById(bookTicketRequestDto.getShowId());

		if (!optionalShow.isPresent()) {
			throw new DependencyException("Show Not Found with ID: " + bookTicketRequestDto.getUserId() + " to book ticket");
		}

		Set<String> requestedSeats = bookTicketRequestDto.getSeatsNumbers();

		logger.info("Requested Bookings For Seats: " + requestedSeats + " of Show: " + bookTicketRequestDto.getShowId() + " by User: " + bookTicketRequestDto.getUserId());

		List<ShowSeatsEntity> showSeatsEntities = optionalShow.get().getSeats();
		/****************Locking seat for given time***************
		
		List<ShowSeatsEntity> requestedSeatEntities = showSeatsEntities
				.stream()
				.filter(seat -> requestedSeats.contains(seat.getSeatNumber())
						&& !seat.isBooked())
				.collect(Collectors.toList());
		
		if (requestedSeatEntities.size() != requestedSeats.size()) {
			throw new DependencyException("Seats Not Available for Booking");
		}else {
			List<ShowSeatsDto> requestSeatsDto = ShowSeatsAdapter.toDto(requestedSeatEntities);
			seatLockProvider.lockSeats(ShowAdapter.toDto(optionalShow.get()), requestSeatsDto, bookTicketRequestDto.getUserId());
		}
		***********************************************************/
		logger.info("Total Number of Seats in Show: " + bookTicketRequestDto.getShowId() + " are " + showSeatsEntities.size());
		showSeatsEntities =
				showSeatsEntities
						.stream()
						.filter(seat -> seat.getSeatType().equals(bookTicketRequestDto.getSeatType())
								&& !seat.isBooked()
								&& requestedSeats.contains(seat.getSeatNumber()))
						.collect(Collectors.toList());
		
		if (showSeatsEntities.size() != requestedSeats.size()) {
			throw new DependencyException("Seats Not Available for Booking");
		}

		logger.info("Seats: " + requestedSeats + " are avaialble in Show: " + bookTicketRequestDto.getShowId() + " for booking to User " + bookTicketRequestDto.getUserId());

		TicketEntity ticketEntity =
				TicketEntity.builder()
						.user(optionalUser.get())
						.show(optionalShow.get())
						.seats(showSeatsEntities)
						.build();

		double amount = 0.0;
		String allotedSeats = "";

		int numberOfSeatsBooked = showSeatsEntities.size();
		int counter = 0;
		OfferEntity offerEntity = offerRepository.getByNoOfSeats(numberOfSeatsBooked);
		float offerRate = offerEntity.getOfferMultiplier();

		for (ShowSeatsEntity seatsEntity : showSeatsEntities) {
			++counter;
			seatsEntity.setBooked(true);
			seatsEntity.setBookedAt(new Date());
			seatsEntity.setTicket(ticketEntity);
			if(counter == 3) {
				amount += seatsEntity.getRate()-(seatsEntity.getRate()*offerRate);
				logger.info("######OFFER#####" + " 50% discount on the third ticket for Show No : " + bookTicketRequestDto.getShowId() +" and Movie : "+ optionalShow.get().getMovie().getName() +" for booking to User " + bookTicketRequestDto.getUserId());
			}else
				amount += seatsEntity.getRate();

			allotedSeats += seatsEntity.getSeatNumber() + " ";
		}

		ticketEntity.setAmount(amount);
		ticketEntity.setAllottedSeats(allotedSeats);

		if (CollectionUtils.isEmpty(optionalUser.get().getTicketEntities())) {
			optionalUser.get().setTicketEntities(new ArrayList<>());
		}

		optionalUser.get().getTicketEntities().add(ticketEntity);

		if (CollectionUtils.isEmpty(optionalShow.get().getTickets())) {
			optionalShow.get().setTickets(new ArrayList<>());
		}

		optionalShow.get().getTickets().add(ticketEntity);

		logger.info("Creating Booking for User: " + bookTicketRequestDto.getUserId() + " in Show: " + bookTicketRequestDto.getShowId() + " for Seats: " + allotedSeats);

		ticketEntity = ticketRepository.save(ticketEntity);

		return TicketAdapter.toDto(ticketEntity);
	}

	@Override
	public TicketDto getTicket(long id) {
		logger.info("Searching Ticket by id: " + id);

		Optional<TicketEntity> ticketEntity = ticketRepository.findById(id);

		if (!ticketEntity.isPresent()) {
			logger.error("Ticket not found for id: " + id);
			throw new EntityNotFoundException("Ticket Not Found with ID: " + id);
		}

		return TicketAdapter.toDto(ticketEntity.get());
	}

}
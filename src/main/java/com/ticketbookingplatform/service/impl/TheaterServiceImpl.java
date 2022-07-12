/**
 * 
 */
package com.ticketbookingplatform.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketbookingplatform.adapter.TheaterAdapter;
import com.ticketbookingplatform.dto.TheaterDto;
import com.ticketbookingplatform.enums.SeatType;
import com.ticketbookingplatform.model.TheaterEntity;
import com.ticketbookingplatform.model.TheaterSeatsEntity;
import com.ticketbookingplatform.repository.TheaterRepository;
import com.ticketbookingplatform.repository.TheaterSeatsRepository;
import com.ticketbookingplatform.service.TheaterService;

import lombok.extern.log4j.Log4j2;

/**
 * @author Sonal Kumbhare
 *
 * @since 10-July-2022
 */
@Log4j2
@Service
public class TheaterServiceImpl implements TheaterService {

	private static final Logger logger = LoggerFactory.getLogger(TheaterServiceImpl.class);
	
	@Autowired
	private TheaterRepository theaterRepository;

	@Autowired
	private TheaterSeatsRepository theaterSeatsRepository;

	@Override
	public TheaterDto addTheater(TheaterDto theaterDto) {
		logger.info("Adding New Theater: " + theaterDto);

		TheaterEntity theaterEntity = TheaterAdapter.toEntity(theaterDto);

		theaterEntity.getSeats().addAll(getTheaterSeats());

		for (TheaterSeatsEntity seatsEntity : theaterEntity.getSeats()) {
			seatsEntity.setTheater(theaterEntity);
		}

		theaterEntity = theaterRepository.save(theaterEntity);

		logger.info("Added New User [id: " + theaterEntity.getId() + ", Name: " + theaterEntity.getName() + "]");

		return TheaterAdapter.toDto(theaterEntity);
	}

	private List<TheaterSeatsEntity> getTheaterSeats() {

		List<TheaterSeatsEntity> seats = new ArrayList<>();

		seats.add(getTheaterSeat("1A", SeatType.CLASSIC, 100));
		seats.add(getTheaterSeat("1B", SeatType.CLASSIC, 100));
		seats.add(getTheaterSeat("1C", SeatType.CLASSIC, 100));
		seats.add(getTheaterSeat("1D", SeatType.CLASSIC, 100));
		seats.add(getTheaterSeat("1E", SeatType.CLASSIC, 100));

		seats.add(getTheaterSeat("2A", SeatType.PREMIUM, 150));
		seats.add(getTheaterSeat("2B", SeatType.PREMIUM, 150));
		seats.add(getTheaterSeat("2C", SeatType.PREMIUM, 150));
		seats.add(getTheaterSeat("2D", SeatType.PREMIUM, 150));
		seats.add(getTheaterSeat("2E", SeatType.PREMIUM, 150));

		seats = theaterSeatsRepository.saveAll(seats);

		logger.info("Added " + seats.size() + " Seats for New Theater");

		return seats;
	}

	private TheaterSeatsEntity getTheaterSeat(String seatNumber, SeatType seatType, int rate) {
		return TheaterSeatsEntity.builder().seatNumber(seatNumber).seatType(seatType).rate(rate).build();
	}

	@Override
	public TheaterDto getTheater(long id) {
		logger.info("Searching Theater by id: " + id);

		Optional<TheaterEntity> theaterEntity = theaterRepository.findById(id);

		if (!theaterEntity.isPresent()) {
			logger.error("Theater not found for id: " + id);
			throw new EntityNotFoundException("Theater Not Found with ID: " + id);
		}

		return TheaterAdapter.toDto(theaterEntity.get());
	}

}
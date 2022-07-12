package com.ticketbookingplatform;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ticketbookingplatform.dto.BookTicketRequestDto;
import com.ticketbookingplatform.enums.CertificateType;
import com.ticketbookingplatform.enums.MovieLanguage;
import com.ticketbookingplatform.enums.SeatType;
import com.ticketbookingplatform.enums.TheaterType;
import com.ticketbookingplatform.model.MovieEntity;
import com.ticketbookingplatform.model.OfferEntity;
import com.ticketbookingplatform.model.ShowEntity;
import com.ticketbookingplatform.model.ShowSeatsEntity;
import com.ticketbookingplatform.model.TheaterEntity;
import com.ticketbookingplatform.model.TheaterSeatsEntity;
import com.ticketbookingplatform.model.UserEntity;
import com.ticketbookingplatform.repository.MovieRepository;
import com.ticketbookingplatform.repository.OfferRepository;
import com.ticketbookingplatform.repository.ShowRepository;
import com.ticketbookingplatform.repository.ShowSeatsRepository;
import com.ticketbookingplatform.repository.TheaterRepository;
import com.ticketbookingplatform.repository.TheaterSeatsRepository;
import com.ticketbookingplatform.repository.TicketRepository;
import com.ticketbookingplatform.repository.UserRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class TicketServiceTest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ShowRepository showsRepository;

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private TheaterRepository theaterRepository;

	@Autowired
	private ShowSeatsRepository showSeatsRepository;

	@Autowired
	private TheaterSeatsRepository theaterSeatsRepository;
	
	@Autowired
	private OfferRepository offerRepository;
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	@BeforeEach
	void loadApplicationContext() {
	    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
    @Test
    public void addEmployee() throws Exception {
    	Set<String> seatNumbers = new HashSet<>(Arrays.asList("ROW1-A", "ROW1-B", "ROW1-C"));
		BookTicketRequestDto bookTicketRequestDto = 
									BookTicketRequestDto.builder()
									.seatType(SeatType.CLASSIC)
									.showId(1L)
									.userId(1)
									.seatsNumbers(seatNumbers)
									.build();
        mockMvc.perform(post("/ticket/book")
                .content(asJsonString(bookTicketRequestDto))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
	
    @BeforeEach
	public void generate() {

		userRepository.deleteAllInBatch();
		ticketRepository.deleteAllInBatch();

		showSeatsRepository.deleteAllInBatch();
		theaterSeatsRepository.deleteAllInBatch();

		showsRepository.deleteAllInBatch();
		movieRepository.deleteAllInBatch();
		theaterRepository.deleteAllInBatch();
		movieRepository.deleteAllInBatch();

		UserEntity userEntity = UserEntity.builder().name("Test1").mobile("8888888888").build();
		UserEntity userEntity1 = UserEntity.builder().name("Test2").mobile("9999999999").build();
		userRepository.save(userEntity);
		userRepository.save(userEntity1);

		OfferEntity offer = OfferEntity.builder().noOfSeats(3).offerMultiplier(0.5f).name("Third Booking Offer")
				.build();
		offerRepository.save(offer);
		
		TheaterEntity pvrPune = getTheater("PVR", "Pune", "Koregaon Park");
		TheaterEntity imaxAmanora = getTheater("IMax", "Pune", "Amanora");
		TheaterEntity pvrSeaons = getTheater("Seasons", "Pune", "Magarpatta");

		MovieEntity transformerMovie = getMovie("Transformer", MovieLanguage.ENGLISH, CertificateType.UA);
		MovieEntity pacificRimMovie = getMovie("Pacific Rim", MovieLanguage.ENGLISH, CertificateType.UA);

		MovieEntity xxxMovie = getMovie("XXX", MovieLanguage.ENGLISH, CertificateType.UA);
		MovieEntity lorMovie = getMovie("The Lord of the Rings", MovieLanguage.ENGLISH, CertificateType.UA);

		List<ShowEntity> showEntities = new ArrayList<>();

		showEntities.add(getShow(LocalDate.now(), LocalTime.MIN, 1.0f, pvrPune, xxxMovie));
		showEntities.add(getShow(LocalDate.now(), LocalTime.MIN, 1.0f, pvrPune, transformerMovie));
		showEntities.add(getShow(LocalDate.now(), LocalTime.NOON, 0.8f, pvrPune, pacificRimMovie));
		showEntities.add(getShow(LocalDate.now().plusDays(1), LocalTime.NOON, 0.8f, pvrPune, lorMovie));

		showEntities.add(getShow(LocalDate.now().plusDays(1), LocalTime.NOON, 0.8f, imaxAmanora, xxxMovie));
		showEntities.add(getShow(LocalDate.now(), LocalTime.NOON, 0.8f, imaxAmanora, lorMovie));
		showEntities.add(getShow(LocalDate.now(), LocalTime.NOON.plusHours(1), 0.8f, imaxAmanora, pacificRimMovie));
		showEntities.add(getShow(LocalDate.now(), LocalTime.MIN, 1.0f, imaxAmanora, transformerMovie));

		showEntities.add(getShow(LocalDate.now(), LocalTime.MIN, 1.0f, pvrSeaons, transformerMovie));
		showEntities.add(getShow(LocalDate.now(), LocalTime.MIN, 1.0f, pvrSeaons, pacificRimMovie));
		showEntities.add(getShow(LocalDate.now().plusDays(1), LocalTime.NOON, 0.8f, pvrSeaons, xxxMovie));
		showEntities.add(getShow(LocalDate.now(), LocalTime.NOON, 0.8f, pvrSeaons, lorMovie));
		showsRepository.saveAll(showEntities);

		System.out.println("Added " + showEntities.size() + " Shows");
	}
	
	private ShowEntity getShow(LocalDate showDate, LocalTime showTime, float multiplier, TheaterEntity theaterEntity,
			MovieEntity movieEntity) {

		ShowEntity showEntity = ShowEntity.builder().showDate(showDate).showTime(showTime).rateMultiplier(multiplier)
				.theater(theaterEntity).movie(movieEntity).build();

		theaterEntity.getShows().add(showEntity);
		movieEntity.getShows().add(showEntity);
		showEntity.setSeats(generateShowSeats(theaterEntity.getSeats(), showEntity));

		for (ShowSeatsEntity seatsEntity : showEntity.getSeats()) {
			seatsEntity.setShow(showEntity);
		}

		return showEntity;

	}

	private List<ShowSeatsEntity> generateShowSeats(List<TheaterSeatsEntity> theaterSeatsEntities,
			ShowEntity showEntity) {

		List<ShowSeatsEntity> showSeatsEntities = new ArrayList<>();

		for (TheaterSeatsEntity theaterSeatsEntity : theaterSeatsEntities) {

			ShowSeatsEntity showSeatsEntity = ShowSeatsEntity.builder().seatNumber(theaterSeatsEntity.getSeatNumber())
					.seatType(theaterSeatsEntity.getSeatType())
					.rate((int) (theaterSeatsEntity.getRate() * showEntity.getRateMultiplier())).build();

			showSeatsEntities.add(showSeatsEntity);
		}

		return showSeatsRepository.saveAll(showSeatsEntities);
	}

	private TheaterEntity getTheater(String name, String city, String address) {

		TheaterEntity theaterEntity = TheaterEntity.builder().name(name).type(TheaterType.SINGLE_SCREEN).city(city)
				.address(address).build();

		theaterEntity.getSeats().addAll(getTheaterSeats());

		for (TheaterSeatsEntity seatsEntity : theaterEntity.getSeats()) {
			seatsEntity.setTheater(theaterEntity);
		}

		theaterEntity = theaterRepository.save(theaterEntity);

		return theaterEntity;
	}

	private List<TheaterSeatsEntity> getTheaterSeats() {

		List<TheaterSeatsEntity> seats = new ArrayList<>();

		seats.add(getTheaterSeat("ROW1-A", SeatType.CLASSIC, 200));
		seats.add(getTheaterSeat("ROW1-B", SeatType.CLASSIC, 200));
		seats.add(getTheaterSeat("ROW1-C", SeatType.CLASSIC, 200));
		seats.add(getTheaterSeat("ROW1-D", SeatType.CLASSIC, 200));
		seats.add(getTheaterSeat("ROW1-E", SeatType.CLASSIC, 200));

		seats.add(getTheaterSeat("ROW2-A", SeatType.PREMIUM, 300));
		seats.add(getTheaterSeat("ROW2-B", SeatType.PREMIUM, 300));
		seats.add(getTheaterSeat("ROW2-C", SeatType.PREMIUM, 300));
		seats.add(getTheaterSeat("ROW2-D", SeatType.PREMIUM, 300));
		seats.add(getTheaterSeat("ROW2-E", SeatType.PREMIUM, 300));

		seats = theaterSeatsRepository.saveAll(seats);

		return seats;
	}

	private TheaterSeatsEntity getTheaterSeat(String seatNumber, SeatType seatType, int rate) {
		return TheaterSeatsEntity.builder().seatNumber(seatNumber).seatType(seatType).rate(rate).build();
	}

	private MovieEntity getMovie(String name, MovieLanguage language, CertificateType certificateType) {
		MovieEntity movieEntity = MovieEntity.builder().name(name).language(language).certificateType(certificateType)
				.build();

		movieEntity = movieRepository.save(movieEntity);

		return movieEntity;
	}
}
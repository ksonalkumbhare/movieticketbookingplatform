/**
 * 
 */
package com.ticketbookingplatform.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Sonal Kumbhare
 *
 * @since 10-July-2022
 */
@Getter
@Setter
@Entity
@EntityListeners(value = { AuditingEntityListener.class })
@Table(name = "shows")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class ShowEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "show_date", columnDefinition = "DATE", nullable = false)
	private LocalDate showDate;

	@Column(name = "show_time", columnDefinition = "TIME", nullable = false)
	private LocalTime showTime;

	@Column(name = "rate_multiplier", columnDefinition = "float default 1.0", nullable = false)
	private float rateMultiplier;

	@ManyToOne
	@JsonIgnore
	private MovieEntity movie;

	@ManyToOne
	@JsonIgnore
	private TheaterEntity theater;

	@OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<TicketEntity> tickets;

	@OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<ShowSeatsEntity> seats;
}
/**
 * 
 */
package com.ticketbookingplatform.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name = "offers")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class OfferEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "noOfSeats", nullable = false)
	private int noOfSeats;

	@Column(name = "offer_multiplier", columnDefinition = "float default 1.0", nullable = false)
	private float offerMultiplier;
}
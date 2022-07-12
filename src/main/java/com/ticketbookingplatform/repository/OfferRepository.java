package com.ticketbookingplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.ticketbookingplatform.model.OfferEntity;

/**
 * 
 * @author Sonal Kumbhare
 *
 * @since 10-July-2022
 */
@Repository
public interface OfferRepository extends JpaRepository<OfferEntity, Long>, JpaSpecificationExecutor<OfferEntity> {

	OfferEntity getByNoOfSeats(int noOfSeats);
}
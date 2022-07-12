package com.ticketbookingplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.ticketbookingplatform.model.TheaterSeatsEntity;

/**
 * 
 * @author Sonal Kumbhare
 *
 * @since 10-July-2022
 */
@Repository
public interface TheaterSeatsRepository extends JpaRepository<TheaterSeatsEntity, Long>, JpaSpecificationExecutor<TheaterSeatsEntity> {

}
package com.ticketbookingplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.ticketbookingplatform.model.ShowSeatsEntity;

/**
 * 
 * @author Sonal Kumbhare
 *
 * @since 10-July-2022
 */
@Repository
public interface ShowSeatsRepository extends JpaRepository<ShowSeatsEntity, Long>, JpaSpecificationExecutor<ShowSeatsEntity> {

}
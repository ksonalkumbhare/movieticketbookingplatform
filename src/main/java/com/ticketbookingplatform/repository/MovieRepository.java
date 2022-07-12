package com.ticketbookingplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.ticketbookingplatform.enums.MovieLanguage;
import com.ticketbookingplatform.model.MovieEntity;

/**
 * 
 * @author Sonal Kumbhare
 *
 * @since 10-July-2022
 */
@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long>, JpaSpecificationExecutor<MovieEntity> {

	boolean existsByNameAndLanguage(String name, MovieLanguage language);
}
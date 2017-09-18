package com.vanke.journal.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.vanke.journal.entity.Journal;

public interface JournalRepository extends JpaRepository<Journal, Serializable>, JpaSpecificationExecutor<Journal> {

	List<Journal> findByStartTimeGreaterThanAndEndTimeLessThanOrderByStartTime(Date startTime, Date endTime);

}

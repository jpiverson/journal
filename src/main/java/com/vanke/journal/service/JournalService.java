package com.vanke.journal.service;

import java.util.Date;
import java.util.List;

import com.vanke.journal.entity.Journal;

public interface JournalService {

	Journal save(Journal journal);
	
	Journal findOne(int id);
	
	void delete(int id);

	List<Journal> findAll();

	List<Journal> findByStartTimeGreaterThanAndEndTimeLessThanOrderByStartTime(Date startTime, Date endTime);

}

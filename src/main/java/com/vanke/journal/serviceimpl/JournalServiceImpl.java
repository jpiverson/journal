package com.vanke.journal.serviceimpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vanke.journal.entity.Journal;
import com.vanke.journal.repository.JournalRepository;
import com.vanke.journal.service.JournalService;

@Service
public class JournalServiceImpl implements JournalService {

	@Autowired
	private JournalRepository journalRepository;

	@Override
	public Journal save(Journal journal) {
		return journalRepository.save(journal);
	}
	
	@Override
	public Journal findOne(int id) {
		return journalRepository.findOne(id);
	}


	@Override
	public List<Journal> findAll() {
		return journalRepository.findAll();
	}

	@Override
	public List<Journal> findByStartTimeGreaterThanAndEndTimeLessThanOrderByStartTime(Date startTime, Date endTime) {
		return journalRepository.findByStartTimeGreaterThanAndEndTimeLessThanOrderByStartTime(startTime, endTime);
	}
	
}

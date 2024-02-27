package com.hotel.checkin.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hotel.checkin.model.CheckInrecord;
import com.hotel.checkin.repository.checkinrepository;

@Service
public class CheckinServiceImpl implements CheckinService {

	@Autowired
	private checkinrepository repo;

	@Override
	public CheckInrecord addcheckin(CheckInrecord checkin) {
		
		CheckInrecord c = repo.save(checkin);
		return c;
	}

	@Override
	public List<CheckInrecord> getcheckin() {
		List<CheckInrecord> list = repo.findAll();
		return list;
	}

	@Override
	public CheckInrecord getcheckinbyid(long checkinId) {
		CheckInrecord c = repo.findById(checkinId).get();
		return c;
	}

	@Override
	public CheckInrecord getcheckinbyuserid(long userId) {
		CheckInrecord check = repo.findByUserId(userId);
		return check;
	}

}

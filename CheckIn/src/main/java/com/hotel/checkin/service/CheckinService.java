package com.hotel.checkin.service;

import java.util.List;

import com.hotel.checkin.model.CheckInrecord;

public interface CheckinService {

	CheckInrecord addcheckin(CheckInrecord checkin);

	List<CheckInrecord> getcheckin();

	CheckInrecord getcheckinbyid(long checkinId);

	CheckInrecord getcheckinbyuserid(long userId);
}

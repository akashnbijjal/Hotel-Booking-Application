package com.hotel.checkin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.checkin.model.CheckInrecord;
import com.hotel.checkin.service.CheckinServiceImpl;

@RestController
@RequestMapping("checkin")
public class checkincontroller {

	@Autowired
	private CheckinServiceImpl service;

	@PostMapping("addcheckin")
	public CheckInrecord addcheckin(@RequestBody CheckInrecord checkin) {
		CheckInrecord check = service.addcheckin(checkin);
		return check;
	}

	@GetMapping("allcheckindetails")
	public List<CheckInrecord> getcheckin() {
		List<CheckInrecord> check = service.getcheckin();
		return check;
	}

	@GetMapping("/Id/{checkinId}")
	public CheckInrecord getcheckinbyid(@PathVariable("checkinId") long checkinId) {
		CheckInrecord check = service.getcheckinbyid(checkinId);
		return check;
	}

	@GetMapping("{userId}")
	public CheckInrecord getcheckinuserid(@PathVariable("userId") long userId) {
		CheckInrecord check = service.getcheckinbyuserid(userId);
		return check;
	}

}

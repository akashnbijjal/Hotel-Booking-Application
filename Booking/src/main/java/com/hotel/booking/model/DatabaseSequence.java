package com.hotel.booking.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@Document(collection = "database_sequences")
public class DatabaseSequence {

	@Id
	private String id;

	private long seq;
}

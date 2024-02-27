package com.hotel.booking.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "Payment")
public class Payment {

	@Id
	private long paymentId;
	private String orderId;
	private String transactionId;
	private Double amount;
	private String currency;
	private String status;
	private String description;
	private Date createdDate;
	private Date updatedDate;
}

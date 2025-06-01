package com.learning.hotelmanagement.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Setter
@Getter
@NoArgsConstructor
@Table(name = "Rooms")
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer roomId;

	@Column(unique = true, nullable = false, length = 20)
	private String roomNumber;

	@Column(nullable = false, length = 20)
	private Double price;

	@Column(nullable = false, length = 100)
	private String roomDescription;
	
	@Enumerated(EnumType.STRING)
	private RoomType roomType;

	@Enumerated(EnumType.STRING)
	private RoomStatus status;

	@ManyToOne
	@JoinColumn(name = "hotel_id")
	private Hotel hotel;

}

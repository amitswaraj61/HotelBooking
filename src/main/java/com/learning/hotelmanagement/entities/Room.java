package com.learning.hotelmanagement.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Booking> booking =  new ArrayList<>();;

}

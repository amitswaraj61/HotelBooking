package com.learning.hotelmanagement.payloads;

import com.learning.hotelmanagement.entities.RoomStatus;
import com.learning.hotelmanagement.entities.RoomType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class RoomDto {

	
	@Schema(hidden = true)
	private Integer roomId;

	@NotEmpty(message = "Room Number is Required")
	@Size(min = 3, max = 20, message = "Room Number Must be Min 3 Chars and Max of 100 Chars !!")
	private String roomNumber;

	@NotNull(message = "Price is required")
	@Positive(message = "Price Must Be Positive")
	private Double price;

	@NotEmpty(message = "Room Description is Required")
	@Size(min = 10, max = 100, message = "Room Description Must be Min 10 Chars and Max of 100 Chars !!")
	@Schema(example = "string")
	private String roomDescription;

	@NotNull(message = "Room status is required")
	@Schema(example = "string")
	private RoomStatus status;

	@NotNull(message = "Room Type is required")
	@Schema(example = "string")
	private RoomType roomType;

//	@Schema(hidden = true)
//	private HotelDto hotelDto;
}

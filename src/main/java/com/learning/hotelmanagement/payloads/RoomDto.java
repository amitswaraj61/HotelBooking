package com.learning.hotelmanagement.payloads;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.learning.hotelmanagement.entities.RoomStatus;
import com.learning.hotelmanagement.entities.RoomType;
import com.learning.hotelmanagement.services.ValidationGroup.CreateGroup;
import com.learning.hotelmanagement.services.ValidationGroup.UpdateGroup;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoomDto {

	@Schema(hidden = true)
	private Integer roomId;

	@NotBlank(message = "Room Number is Required", groups = CreateGroup.class)
	@Size(max = 20, message = "Room Number Must be up to Max of 100 Characters", groups = { CreateGroup.class,
			UpdateGroup.class })
	private String roomNumber;

	@NotNull(message = "Price is required",groups = CreateGroup.class)
	@Positive(message = "Price Must Be Positive", groups = { CreateGroup.class,
			UpdateGroup.class })
	private Double price;

	@NotBlank(message = "Room Description is Required",groups = CreateGroup.class)
	@Size(max = 100, message = "Room Description Must be up to Max of 100 Characters",groups = { CreateGroup.class,
			UpdateGroup.class })
	@Schema(example = "string")
	private String roomDescription;

	@NotNull(message = "Room status is required",groups = CreateGroup.class)
	@Schema(example = "string")
	private RoomStatus status;

	@NotNull(message = "Room Type is required",groups = CreateGroup.class)
	@Schema(example = "string")
	private RoomType roomType;

	@Schema(hidden = true)
	private HotelDto hotel;
}

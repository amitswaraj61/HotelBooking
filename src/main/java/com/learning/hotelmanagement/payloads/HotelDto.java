package com.learning.hotelmanagement.payloads;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.learning.hotelmanagement.services.ValidationGroup.CreateGroup;
import com.learning.hotelmanagement.services.ValidationGroup.UpdateGroup;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class HotelDto {

	@Schema(hidden = true)
	private Integer id;

	@NotBlank(message = "Hotel Name is required", groups = CreateGroup.class)
	@Size(max = 50, message = "Hotel Name must be up to 50 characters", groups = { CreateGroup.class,
			UpdateGroup.class })
	private String hotelName;

	@NotBlank(message = "Hotel Address is required", groups = CreateGroup.class)
	@Size(max = 100, message = "Hotel Address must be up to 50 characters", groups = { CreateGroup.class,
			UpdateGroup.class })
	private String hotelAddress;

	@NotBlank(message = "Hotel Description is required", groups = CreateGroup.class)
	@Size(min = 10, max = 100, message = "Hotel Description must be between 10 and 100 characters", groups = {
			CreateGroup.class, UpdateGroup.class })
	@Schema(example = "string")
	private String hotelDescription;
}

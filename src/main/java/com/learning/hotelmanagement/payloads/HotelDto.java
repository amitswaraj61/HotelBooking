package com.learning.hotelmanagement.payloads;

import com.learning.hotelmanagement.services.ValidationGroup.CreateGroup;
import com.learning.hotelmanagement.services.ValidationGroup.UpdateGroup;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class HotelDto {

//	@Schema(hidden = true)
//	private Integer id;
//
//	//@NotEmpty(message = "Hotel Name is Required")
//	@NotBlank
//	@Size(min = 3, max = 100, message = "Hotel Name Must be Min 3 Chars and Max of 100 Chars !!")
//	private String hotelName;
//
//	//@NotEmpty(message = "Hotel Address is Required")
//	@Size(max = 100, message = "Hotel Address Must be Max of 20 Length !!")
//	private String hotelAddress;
//
//	//@NotEmpty(message = "Hotel Description is Required")
//	@Size(min = 10, max = 100, message = "Hotel Description Must be Min 10 Chars and Max of 100 Chars !!")
//	private String hotelDescription;

	@Schema(hidden = true)
	private Integer id;

	@NotBlank(message = "Hotel Name is required", groups = CreateGroup.class)
	@Size(min = 3, max = 100, message = "Hotel Name must be between 3 and 100 characters", groups = {
			CreateGroup.class })
	private String hotelName;

	@NotBlank(message = "Hotel Address is required", groups = CreateGroup.class)
	@Size(max = 100, message = "Hotel Address must be up to 100 characters", groups = { CreateGroup.class })
	private String hotelAddress;

	@NotBlank(message = "Hotel Description is required", groups = CreateGroup.class)
	@Size(min = 10, max = 100, message = "Hotel Description must be between 10 and 100 characters", groups = {
			CreateGroup.class })
	@Schema(example = "string")
	private String hotelDescription;
}

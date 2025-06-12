package com.learning.hotelmanagement.payloads;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.learning.hotelmanagement.services.ValidationGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GuestDto {

    @Schema(hidden = true)
    private Integer guestId;

    @NotBlank(message = "Full name cannot be empty", groups = {ValidationGroup.CreateGroup.class})
    @Size(max = 20, message = "Full name max of 20 char", groups = {ValidationGroup.CreateGroup.class, ValidationGroup.UpdateGroup.class})
    private String fullName;

    @NotBlank(message = "mobile number cannot be empty",groups = {ValidationGroup.CreateGroup.class })
    @Size(max = 10, message = "Mobile number must be 10 digits", groups = {
            ValidationGroup.CreateGroup.class, ValidationGroup.UpdateGroup.class })
    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must contain only 10 digits", groups = {
            ValidationGroup.CreateGroup.class, ValidationGroup.UpdateGroup.class })
    private String mobileNumber;

    @NotBlank(message = "Email cannot be empty", groups = {
            ValidationGroup.CreateGroup.class })
    @Email(message = "Invalid email format", groups = {
            ValidationGroup.CreateGroup.class})
    private String email;

    @NotBlank(message = "identification number cannot be empty", groups = {
            ValidationGroup.CreateGroup.class})
    @Size(max = 20, message = "Max digit should be 20", groups = {
            ValidationGroup.CreateGroup.class})
    private String identificationNumber;


}

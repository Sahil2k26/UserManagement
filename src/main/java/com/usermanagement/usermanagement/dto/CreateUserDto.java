package com.usermanagement.usermanagement.dto;

import com.usermanagement.usermanagement.utils.customAnotations.StrongPassword;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto {

    @NotBlank(message = "Phone number is required")
    @Size(min=10 ,max=10 , message = "Phone number must be of length 10")
    @Pattern(regexp = "\\d+",message = "Must contain only digits")
    private String phoneNo;


    @StrongPassword
    @NotBlank(message = "Password is required")
    private String password;
}

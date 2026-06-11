package com.skyway.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
@Data
@Builder
public class UserDTO {

    private String name;
    private String gender;
    private String email;
    private String aadharNo;
    private String mobileNo;
    private LocalDate dob;
}

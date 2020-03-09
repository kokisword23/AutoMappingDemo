package org.demo.automapping.service.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeCreateDto {

    private String firstName;
    private String lastName;
    private Date birthday;
    private double salary;
    private long manager;
}

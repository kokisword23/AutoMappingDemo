package org.demo.automapping.service.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class ManagerDto {

    private long id;
    private String firstName;
    private String lastName;
    private List<EmployeeViewDto> employees;
}

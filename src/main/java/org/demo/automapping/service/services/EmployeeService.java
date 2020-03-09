package org.demo.automapping.service.services;

import javassist.NotFoundException;
import org.demo.automapping.data.entities.Employee;
import org.demo.automapping.service.dtos.EmployeeCreateDto;
import org.demo.automapping.service.dtos.EmployeeViewDto;
import org.demo.automapping.service.dtos.ManagerDto;

import java.util.List;

public interface EmployeeService {

    void save(EmployeeCreateDto employee) throws NotFoundException;

    EmployeeViewDto findByFirstAndLastName(String fn, String ln);

    List<ManagerDto> findAllManagers();
}

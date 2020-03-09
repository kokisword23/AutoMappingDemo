package org.demo.automapping.service.services;

import javassist.NotFoundException;
import org.demo.automapping.data.entities.Employee;
import org.demo.automapping.data.repositories.EmployeeRepository;
import org.demo.automapping.service.dtos.EmployeeCreateDto;
import org.demo.automapping.service.dtos.EmployeeViewDto;
import org.demo.automapping.service.dtos.ManagerDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final ModelMapper modelMapper;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(ModelMapper modelMapper, EmployeeRepository employeeRepository) {
        this.modelMapper = modelMapper;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void save(EmployeeCreateDto employee) {
        Employee employee1 = modelMapper.map(employee, Employee.class);
        Employee manager = employeeRepository.findById(employee.getManager())
                .orElse(null);

        if (manager == null) {
            System.out.println("Such manager doesn't exist !");
        } else {
            employee1.setManager(manager);
        }

        employeeRepository.save(employee1);
    }

    @Override
    public EmployeeViewDto findByFirstAndLastName(String fn, String ln) {
        return modelMapper.map(employeeRepository.findByFirstNameAndLastName(fn, ln), EmployeeViewDto.class);
    }

    @Override
    public List<ManagerDto> findAllManagers() {
        List<Employee> employees = employeeRepository.findAllByManagerIsNull();
        List<ManagerDto> managerDtos = employees.stream()
                .map(e -> modelMapper.map(e, ManagerDto.class))
                .collect(Collectors.toList());

        for (ManagerDto managerDto : managerDtos) {
            List<EmployeeViewDto> employeeViewDtos =employeeRepository.findAllByManagerId(managerDto.getId())
                    .stream()
                    .map(e -> modelMapper.map(e, EmployeeViewDto.class))
                    .collect(Collectors.toList());
            managerDto.setEmployees(employeeViewDtos);
        }
        return managerDtos;
    }
}

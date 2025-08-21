package com.gulkalkan.Service.impl;

import com.gulkalkan.Dto.DtoDepartment;
import com.gulkalkan.Dto.DtoEmployee;
import com.gulkalkan.Service.IEmployeeService;
import com.gulkalkan.model.Department;
import com.gulkalkan.model.Employee;
import com.gulkalkan.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public DtoEmployee findEmployeeById(Long id) {
        DtoEmployee dtoEmployee = new DtoEmployee();
        DtoDepartment dtoDepartment = new DtoDepartment();
        Optional<Employee> optional = employeeRepository.findById(id);
        if (optional.isEmpty()) {
            return null;
        }
        Employee employee = optional.get();
        Department department = employee.getDepartment();
        BeanUtils.copyProperties(employee, dtoEmployee);
        BeanUtils.copyProperties(department, dtoDepartment);
        dtoEmployee.setDepartment(dtoDepartment);
        return dtoEmployee;
    }
}

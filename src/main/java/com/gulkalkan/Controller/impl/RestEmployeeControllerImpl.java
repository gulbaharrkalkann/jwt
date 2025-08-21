package com.gulkalkan.Controller.impl;

import com.gulkalkan.Controller.IRestEmployeeController;
import com.gulkalkan.Dto.DtoEmployee;
import com.gulkalkan.Service.IEmployeeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class RestEmployeeControllerImpl implements IRestEmployeeController {


    @Autowired
    private IEmployeeService employeeService;
    @GetMapping("/{id}")
    @Override
    public DtoEmployee findEmployeeById(@Valid @NotEmpty @PathVariable(value = "id") Long id) {
        return employeeService.findEmployeeById(id);
    }
}

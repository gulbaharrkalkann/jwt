package com.gulkalkan.Service;

import com.gulkalkan.Dto.DtoEmployee;

public interface IEmployeeService {


    DtoEmployee findEmployeeById(Long id);
}

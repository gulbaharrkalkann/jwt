package com.gulkalkan.Controller;

import com.gulkalkan.Dto.DtoEmployee;

public interface IRestEmployeeController {
    public DtoEmployee findEmployeeById(Long id);

}

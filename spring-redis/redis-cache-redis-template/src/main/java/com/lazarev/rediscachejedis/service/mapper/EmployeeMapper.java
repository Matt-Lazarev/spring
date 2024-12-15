package com.lazarev.rediscachejedis.service.mapper;

import com.lazarev.rediscachejedis.dto.employee.EmployeeDto;
import com.lazarev.rediscachejedis.dto.employee.EmployeeInfoDto;
import com.lazarev.rediscachejedis.entity.Employee;
import org.mapstruct.*;

import java.util.List;

import static org.mapstruct.NullValuePropertyMappingStrategy.*;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee toEntity(EmployeeDto dto);

    EmployeeDto toDto(Employee entity);

    EmployeeInfoDto toEmployeeInfo(Employee employee);

    List<EmployeeDto> toDtoList(List<Employee> entities);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Employee entity, EmployeeDto dto);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    void patch(@MappingTarget Employee entity, EmployeeDto dto);
}

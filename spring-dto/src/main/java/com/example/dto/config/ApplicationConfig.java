package com.example.dto.config;

import com.example.dto.dto.EmployeeDto;
import com.example.dto.entity.Department;
import com.example.dto.entity.Employee;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ApplicationConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(AccessLevel.PRIVATE);

//        mapper.createTypeMap(Employee.class, EmployeeDto.class)
//                .addMappings(m -> m.skip(EmployeeDto::setDepartment))
//                .setPostConverter(toDtoConverter());
//
//        mapper.createTypeMap(EmployeeDto.class, Employee.class)
//                .addMappings(m -> m.skip(Employee::setDepartment))
//                .setPostConverter(toEntityConverter());
        return mapper;
    }

//    public Converter<EmployeeDto, Employee> toEntityConverter() {
//        return context -> {
//            EmployeeDto source = context.getSource();
//            Employee destination = context.getDestination();
//
//            destination.setDepartment(new Department(null, source.getDepartment(), source.getEmployeesInDepartment()));
//
//            return context.getDestination();
//        };
//    }
//
//    public Converter<Employee, EmployeeDto> toDtoConverter() {
//        return context -> {
//            Employee source = context.getSource();
//            EmployeeDto destination = context.getDestination();
//
//            destination.setDepartment(source.getDepartment().getName());
//            destination.setEmployeesInDepartment(source.getDepartment().getEmployees());
//
//            return context.getDestination();
//        };
//    }
}

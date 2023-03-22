package com.example.employeeservice.query.projection;

import com.example.employeeservice.command.data.Employee;
import com.example.employeeservice.command.data.EmployeeRepository;
import com.example.employeeservice.query.model.EmployeeResponseModel;
import com.example.employeeservice.query.queries.GetAllEmployeeQuery;
import com.example.employeeservice.query.queries.GetEmployeeQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EmployeeProjection {

    private final EmployeeRepository employeeRepository;

    @QueryHandler
    public EmployeeResponseModel getEmployee(GetEmployeeQuery employeeQuery){
        EmployeeResponseModel responseModel = new EmployeeResponseModel();
        Employee employee = employeeRepository.getReferenceById(employeeQuery.getEmployeeId());
        BeanUtils.copyProperties(employee, responseModel);
        return responseModel;
    }

    @QueryHandler
    public List<EmployeeResponseModel> getAllEmployee(GetAllEmployeeQuery employeeQuery){
        List<EmployeeResponseModel> modelList = new ArrayList<>();
        List<Employee> employees = employeeRepository.findAll();
        employees.forEach(s -> {
            EmployeeResponseModel model = new EmployeeResponseModel();
            BeanUtils.copyProperties(s,model);
            modelList.add(model);
        });
        return modelList;
    }
}

package com.example.employeeservice.query.controller;

import com.example.employeeservice.query.model.EmployeeResponseModel;
import com.example.employeeservice.query.queries.GetAllEmployeeQuery;
import com.example.employeeservice.query.queries.GetEmployeeQuery;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employees")
public class EmployeeQueryController {

    private final QueryGateway queryGateway;

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponseModel> getEmployee(@PathVariable(value = "employeeId") String employeeId){
        GetEmployeeQuery getEmployeeQuery = new GetEmployeeQuery();
        getEmployeeQuery.setEmployeeId(employeeId);
        EmployeeResponseModel model = queryGateway.query(getEmployeeQuery, ResponseTypes.instanceOf(EmployeeResponseModel.class)).join();
        return ResponseEntity.ok(model);
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeResponseModel>> getAllEmployee(){
        GetAllEmployeeQuery getAllEmployeeQuery = new GetAllEmployeeQuery();
        List<EmployeeResponseModel> models = queryGateway.query(getAllEmployeeQuery, ResponseTypes.multipleInstancesOf(EmployeeResponseModel.class)).join();
        return ResponseEntity.ok(models);
    }
}

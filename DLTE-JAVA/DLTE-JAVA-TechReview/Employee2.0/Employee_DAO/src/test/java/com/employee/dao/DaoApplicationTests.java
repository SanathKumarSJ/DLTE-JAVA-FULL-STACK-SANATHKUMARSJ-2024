package com.employee.dao;

import com.employee.dao.entity.EmployeeAddressDetails;
import com.employee.dao.entity.EmployeePersonalDetails;
import com.employee.dao.implementation.EmployeeService;
import com.employee.dao.repository.MyInterface;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.relational.core.sql.In;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class DaoApplicationTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private ResourceBundle resourceBundle;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    public void testFindPincode() throws SQLSyntaxErrorException {

        List<EmployeePersonalDetails> mockList = new ArrayList<>();
        // Creating the expected employee object
        EmployeePersonalDetails expectedEmployee = new EmployeePersonalDetails();

        expectedEmployee.setFirstNameOfEmployee("Sanath");
        expectedEmployee.setMiddleNameOfEmployee("kumar");
        expectedEmployee.setLastNameOfEmployee("sringeri");
        expectedEmployee.setEmployeeID(12345);
        expectedEmployee.setEmployeeContactNumber(7417417410L);
        expectedEmployee.setEmployeeEmail("san@gmail.com");

        EmployeeAddressDetails permanentAddress = new EmployeeAddressDetails();
        permanentAddress.setHouseName("nilaya");
        permanentAddress.setState("California");
        permanentAddress.setCity("Los Angeles");
        permanentAddress.setPincode(900010);

        EmployeeAddressDetails temporaryAddress = new EmployeeAddressDetails();
        temporaryAddress.setHouseName("home");
        temporaryAddress.setState("California");
        temporaryAddress.setCity("San Francisco");
        temporaryAddress.setPincode(900010);

        expectedEmployee.setPermanentAddress(permanentAddress);
        expectedEmployee.setTemporaryAddress(temporaryAddress);

        mockList.add(expectedEmployee);

        when(jdbcTemplate.query(any(String.class), any(Object[].class), any(EmployeeService.EmployeeDetailsRowMapper.class)))
                .thenReturn(mockList);

        // Call the method to be tested
        int pinCode = 123; // Example pin code
        List<EmployeePersonalDetails> result = employeeService.searchByPinCode(pinCode);

        // Verify that jdbcTemplate.query was called with the correct arguments
//            verify(jdbcTemplate).query(any(String.class), any(Object[].class), any(EmployeeService.EmployeeDetailsRowMapper.class));
            verify(jdbcTemplate).query(anyString(), eq(new Object[]{pinCode,pinCode}), any(EmployeeService.EmployeeDetailsRowMapper.class));

        // Verify the result
        assertEquals(expectedEmployee.getFirstNameOfEmployee(), result.get(0).getMiddleNameOfEmployee());


    }

//    @Test
//    public void testDisplayBasedOnEmployeeIdNotFound() {
//        String employeeId = "123";
//
//        // Mocking the queryForObject method of JdbcTemplate to throw an EmptyResultDataAccessException
//        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), any(DatabaseRepositoryImplementation.EmployeeRowMapper.class)))
//                .thenThrow(new EmptyResultDataAccessException(1));
//
//        // Verifying that EmployeeNotFoundException is thrown
//        assertThrows(EmployeeNotFoundException.class, () -> employeeService.displayBasedOnEmployeeId(employeeId));
//    }
}





//            // Mocking the queryForObject method of JdbcTemplate to return the expectedEmployee
//            when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), any(EmployeeService.EmployeeDetailsRowMapper.class)))
//                    .thenReturn(expectedEmployee);
//
//            // Calling the method under test
//            List<EmployeePersonalDetails> actualEmployee = employeeService.searchByPinCode(employeePin);
//
//            // Verifying that the queryForObject method was called with the correct parameters
//            verify(jdbcTemplate).queryForObject(anyString(), eq(new Object[]{employeePin}), any(EmployeeService.EmployeeDetailsRowMapper.class));
//
//            // Verifying that the returned employee matches the expected employee
//            assertEquals(expectedEmployee.getFirstNameOfEmployee(), actualEmployee.get(0).getMiddleNameOfEmployee());
//        }

//
//        @Test
//        public void testDisplayBasedOnEmployeeIdNotFound() {
//            String employeeId = "123";
//
//            // Mocking the queryForObject method of JdbcTemplate to throw an EmptyResultDataAccessException
//            when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), any(DatabaseRepositoryImplementation.EmployeeRowMapper.class)))
//                    .thenThrow(new EmptyResultDataAccessException(1));
//
//            // Verifying that EmployeeNotFoundException is thrown
//            assertThrows(EmployeeNotFoundException.class, () -> employeeService.displayBasedOnEmployeeId(employeeId));
//        }
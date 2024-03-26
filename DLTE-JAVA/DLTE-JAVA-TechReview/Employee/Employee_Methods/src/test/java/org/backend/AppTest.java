//package org.backend;
//
//import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.*;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import org.exception.ConnectionException;
//import org.exception.UserExist;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//
//
//import org.junit.Test;
//import org.mockito.junit.MockitoJUnitRunner;
//
////Junit and Mockito is used for testing
//@RunWith(MockitoJUnitRunner.class)
//public class AppTest {
//
//    @Mock
//    private Connection mockConnection;
//
//    @Mock
//    private PreparedStatement mockPreparedStatement;
//
//    @Mock
//    private ResultSet mockResultSet;
//
//    @Mock
//    private MyInterface myInterface;
//
//    @Mock
//    private DriverInitializer mockDriverInitializer;
//
//    @Mock
//    private EmployeeDB backend;
//
//
//    @Test
//    public void testWriteEmployeeDetails() throws SQLException, UserExist, ConnectionException {
//
//
//        org.backend.EmployeePersonalDetails employeePersonalDetails = new org.backend.EmployeePersonalDetails("Sanath", "kumar", "sj", 98000, 9876743634L, "sanath@gmail.com", new org.backend.EmployeeAddressDetails("vinayaka", "BSK", "Bangalore", "Karnataka", 577120), new org.backend.EmployeeAddressDetails("vinayaka", "BSK", "Bangalore", "Karnataka", 577120));
//        org.backend.EmployeePersonalDetails employeePersonalDetails2 = new org.backend.EmployeePersonalDetails("mahesh", "kumar", "sj", 85900, 9696743634L, "samh@gmail.com", new org.backend.EmployeeAddressDetails("vinayaka", "BSK", "Bangalore", "Karnataka", 577120), new org.backend.EmployeeAddressDetails("vinayaka", "BSK", "Bangalore", "Karnataka", 577120));
//
//        when(mockDriverInitializer.makeConnection()).thenReturn(mockConnection);
//        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
//        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
//
//        when(backend.input(employeePersonalDetails)).thenReturn("added");
//        String check = backend.input(employeePersonalDetails);
//
//        assertEquals("added", check);
////        assertNotSame(employeeOne,employee);
//
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////    @Test
////    public void TestFindAll() throws SQLException {
////        org.backend.EmployeePersonalDetails employeePersonalDetails = new org.backend.EmployeePersonalDetails("Sanath", "kumar", "sj", 98789, 9876743634L, "sanath@gmail.com", new org.backend.EmployeeAddressDetails("vinayaka", "BSK", "Bangalore", "Karnataka", 577120), new org.backend.EmployeeAddressDetails("vinayaka", "BSK", "Bangalore", "Karnataka", 577120));
////        org.backend.EmployeePersonalDetails employeePersonalDetails2 = new org.backend.EmployeePersonalDetails("mahesh", "kumar", "sj", 85965, 9696743634L, "samh@gmail.com", new org.backend.EmployeeAddressDetails("vinayaka", "BSK", "Bangalore", "Karnataka", 577120), new org.backend.EmployeeAddressDetails("vinayaka", "BSK", "Bangalore", "Karnataka", 577120));
////        EmployeePersonalDetails employeePersonalDetails1 = new org.backend.EmployeePersonalDetails("Varshini", "Kiran", "shetty", 99655, 9876543634L, "varsh@gmail.com", new EmployeeAddressDetails("vinayaka", "BSK2nd", "Bangaloru", "Karnataka", 577420), new org.backend.EmployeeAddressDetails("vinayaka", "BSK", "Bangalore", "Karnataka", 577120));
////        List<EmployeePersonalDetails> expected = Stream.of(employeePersonalDetails1, employeePersonalDetails2, employeePersonalDetails).collect(Collectors.toList());
////        List<EmployeePersonalDetails> actual = Stream.of( employeePersonalDetails2, employeePersonalDetails).collect(Collectors.toList());
////
////        when(backend.display()).thenReturn(expected);
////
////        when(mockDriverInitializer.makeConnection()).thenReturn(mockConnection);
////
////        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
////        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
//////        mockDBRepository.display();
//////        verify(mockDBRepository, times(1)).display();/
////        //        assertEquals(mockDBRepository.display().get(1).getFirstNameOfEmployee(),expected.get(1).getLastNameOfEmployee());
////                assertEquals(actual.size(),expected.size());
////
////    }//        mockConnection = mock(Connection.class);
//////        mockPreparedStatement = mock(PreparedStatement.class);
//////        mockResultSet = mock(ResultSet.class);
//

package org.employee.console;

import static org.mockito.Mockito.*;

import org.backend.EmployeeAddressDetails;
import org.backend.EmployeeDB;
import org.backend.EmployeePersonalDetails;
import org.backend.MyInterface;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;



/**
 * Unit test for simple App.
 */
@RunWith(MockitoJUnitRunner.class)
public class AppTest
{
    @Mock
    private EmployeeDB mockDBRepository;
    private EmployeeConsole mockConsole;
    private MyInterface myInterface;

    /**
     * Rigorous Test :-)
     */
    @Test
    public void testSave() throws SQLException {
        org.backend.EmployeePersonalDetails employeePersonalDetails=new org.backend.EmployeePersonalDetails("Sanath","kumar","sj",98789,9876743634L,"sanath@gmail.com",new org.backend.EmployeeAddressDetails("vinayaka","BSK","Bangalore","Karnataka",577120),new org.backend.EmployeeAddressDetails("vinayaka","BSK","Bangalore","Karnataka",577120));
       EmployeePersonalDetails employeePersonalDetails1=new org.backend.EmployeePersonalDetails("Varshini","Kiran","shetty",99655,9876543634L,"varsh@gmail.com",new EmployeeAddressDetails("vinayaka","BSK2nd","Bangaloru","Karnataka",577420),new org.backend.EmployeeAddressDetails("vinayaka","BSK","Bangalore","Karnataka",577120));

       EmployeeDB employeeDB=mock(EmployeeDB.class);
       when(employeeDB.input(employeePersonalDetails)).thenReturn("added");
//        String check=mockDBRepository.input(employeePersonalDetails);

        String check = employeeDB.input(employeePersonalDetails);

        assertEquals("added", check);



//        EmployeeDB mockRepository = mock(EmployeeDB.class);
//        // Assuming the method for saving objects is named "save"
//        when(mockRepository).thenReturn(employeePersonalDetails);
//
//        // Call the method in your application that saves the object
//        EmployeeDB yourApp = new EmployeeDB();
//        yourApp.input(employeePersonalDetails);
//
//        // Verify that the object was saved correctly
//        verify(mockRepository, times(1)).input(employeePersonalDetails);
    }
    @Test
    public void testFindAll() throws SQLException, IOException {
        org.backend.EmployeePersonalDetails employeePersonalDetails=new org.backend.EmployeePersonalDetails("Sanath","kumar","sj",98789,9876743634L,"sanath@gmail.com",new org.backend.EmployeeAddressDetails("vinayaka","BSK","Bangalore","Karnataka",577120),new org.backend.EmployeeAddressDetails("vinayaka","BSK","Bangalore","Karnataka",577120));
        org.backend.EmployeePersonalDetails employeePersonalDetails2=new org.backend.EmployeePersonalDetails("mahesh","kumar","sj",85965,9696743634L,"samh@gmail.com",new org.backend.EmployeeAddressDetails("vinayaka","BSK","Bangalore","Karnataka",577120),new org.backend.EmployeeAddressDetails("vinayaka","BSK","Bangalore","Karnataka",577120));
        EmployeePersonalDetails employeePersonalDetails1=new org.backend.EmployeePersonalDetails("Varshini","Kiran","shetty",99655,9876543634L,"varsh@gmail.com",new EmployeeAddressDetails("vinayaka","BSK2nd","Bangaloru","Karnataka",577420),new org.backend.EmployeeAddressDetails("vinayaka","BSK","Bangalore","Karnataka",577120));
        List<EmployeePersonalDetails> expected= Stream.of(employeePersonalDetails1,employeePersonalDetails2,employeePersonalDetails).collect(Collectors.toList());

        when(mockDBRepository.display()).thenReturn(expected);
        mockDBRepository.display();
        verify(mockDBRepository, times(1)).display();
//        assertEquals(mockDBRepository.display().get(1).getFirstNameOfEmployee(),expected.get(1).getLastNameOfEmployee());
//        assertEquals(actual.size(),expected.size());
    }

}

//package org.example;
//
//import static junit.framework.TestCase.assertEquals;
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.*;
//
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.mockito.verification.Timeout;
//
///*
// * Unit test for simple App.
// */
//@RunWith(MockitoJUnitRunner.class)
//public class AppTest {
//    @Mock
//    private StorageTarget mockStorageTarget;
//    @Mock
//    private UserFileRepository mockUserRepo;
//    private UserServices userServices;
//
//    @Before
//    public void prepareStore() {
//        when(mockStorageTarget.getUserFileRepository()).thenReturn(mockUserRepo);
//        userServices = new UserServices(mockStorageTarget);
//    }
//
//    @Test
//    public void checkExistance() {
//        User user = new User("varshini", "vV@12345", "bengalore", "varsh@gmail.com", 9876543210L, 1000.0);
//        User user2 = new User("vibha", "vV@123456", "bengaloru", "varshi@gmail.com", 9876543219L, 10000.0);
//        doNothing().when(mockUserRepo).save(user2);
//        userServices.callSave(user);
//        verify(mockUserRepo).save(user);
//    }
//    @Test
//    public void findUsername(){
//        User user3 = new User("dhanush", "dD@12345", "hosnagar", "dk@gmail.com", 9876543298L, 5000.0);
//        User user = new User("vibha", "vV@123456", "bengaloru", "varshi@gmail.com", 9876543219L, 10000.0);
//        mockUserRepo.save(user3);
//        userServices.callSave(user3);
//        assertEquals("dhanush",user3.getUserName());
//    }
//
//    @Test
//    public void validatePassword(){
//        String userName="vibha";
//        String userName1="dhanush";
//        User user = new User("vibha", "vV123456", "bengaloru", "varshi@gmail.com", 9876543219L, 10000.0);
//        User user1 = new User("dhanush", "dD@12345", "hosnagar", "dk@gmail.com", 9876543298L, 5000.0);
//
//        when(mockUserRepo.findById(userName)).thenReturn(user);
//
//        User actualUser = userServices.callFindById(userName);
//
//        assertEquals(actualUser.getUserPassword(),user.getUserPassword());
//    }
//    @Test
//    public void checkEmailID() throws InterruptedException {
//
//        // 3 second waiting
//
//        Thread.sleep(3000);
//
//        String userName="vibha";
//        String userName1="dhanush";
//        User user = new User("vibha", "vV123456", "bengaloru", "varshi@gmail.com", 9876543219L, 10000.0);
//        User user1 = new User("dhanush", "dD@12345", "hosnagar", "dk@gmail.com", 9876543298L, 5000.0);
//
//        when(mockUserRepo.findById(userName1)).thenReturn(user1);
//
//        User actualUser = userServices.callFindById(userName1);// passed username1
//
//        assertNotEquals(actualUser.getUserMailId(),user1.getUserMailId()); // checking id of user1
//    }
//
//    @Test(timeout = 2000)  //3 second time limit
//    public void checkBalance() throws InterruptedException {
//
//        String userName="vibha";
//        String userName1="dhanush";
//        User user = new User("vibha", "vV123456", "bengaloru", "varshi@gmail.com", 9876543219L, 10000.0);
//        User user1 = new User("dhanush", "dD@12345", "hosnagar", "dk@gmail.com", 9876543298L, 5000.0);
//
//        when(mockUserRepo.findById(userName1)).thenReturn(user1);
//
//
//        User actualUser = userServices.callFindById(userName1);// passed username1
//
//        assertNotEquals(actualUser.getInitialBalance(),user1.getInitialBalance()); // checking id of user1
//    }
//}

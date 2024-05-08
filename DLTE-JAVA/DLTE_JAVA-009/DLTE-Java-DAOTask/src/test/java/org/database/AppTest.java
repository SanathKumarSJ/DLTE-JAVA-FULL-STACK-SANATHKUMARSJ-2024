//package org.database;
//import static org.mockito.Mockito.*;
//import org.junit.Before;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//
//
///**
// * Unit test for simple App.
// */
//@RunWith(MockitoJUnitRunner.class)
//public class AppTest
//{
//
//    @Mock
//    private StorageTarget mockStorageTarget;
//    @Mock
//    private UserDatabaseRepository mockUserRepository;
//    private UserServices services;
//    @Before
//    public void prepareStore(){
//        when(mockStorageTarget.getUserRepository()).thenReturn(mockUserRepository);
//        services=new UserServices(mockStorageTarget);
//    }
//
//    /**
//     * Rigorous Test :-)
//     */
//    @Test
//    public void testSave(){
//        User user=new User("akash","aK@123456","kerala","akash@gmail.com",7896541230L,15000.0);
//        User user1=new User("elroy","eL@123456","mandya","elroy@gmail.com",9996541230L,35000.0);
//
//        doNothing().when(mockUserRepository).save(user);
//
//        services.callSave(user);
//        verify(mockUserRepository).save(user);
//    }
//
//    @Test
//    public void testFindById(){
//        String username= "Nishmitha";
//        String username1= "sanath";
//
//        User user=new User("akash","aK@123456","kerala","akash@gmail.com",7896541230L,15000.0);
//        User user1=new User("sanath","Ss@12345","begar","sanath@gmail.com",7894561230L,1000.0);
//
//        when(mockUserRepository.findById(username1)).thenReturn(user1);
//
//        User realUser = services.callFindById(username1);
//
//        assertNotSame(user.getUsername(),realUser.getUsername());
//
//        assertEquals(user1.getUsername(),realUser.getUsername());
//
//        assertNotSame(user1.getUsername(),realUser.getUsername());
//    }
//
//    @Test
//    public void testBalance(){
//        Double balance1=1000.0;
//        Double balance2=52000.0;
//        User user=new User("akash","aK@123456","kerala","akash@gmail.com",7896541230L,15000.0);
//        User user1=new User("sanath","Ss@12345","begar","sanath@gmail.com",7894561230L,1000.0);
//
//        when(mockUserRepository.findById("sanath")).thenReturn(user1);
//
//        User realUser = services.callFindById("sanath");
//        assertNotEquals(user1.getBalance(),realUser.getBalance());
//
//    }
//
//
//}

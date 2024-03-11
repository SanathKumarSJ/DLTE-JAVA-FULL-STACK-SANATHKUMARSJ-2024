package org.employeefile;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class OperationOnEmployee{

    static File myfile= new File("employee.doc");

        public OperationOnEmployee() throws IOException {
            System.out.println("inside constructor");
            if (myfile.exists()){
                System.out.println("File present");
            }
            else
                System.out.println("file absent");
            }
        public void writeIntoFile(ArrayList<Object> employee){
            readFromFile();
            System.out.println("Inside write");
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(myfile);
                ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(employee);
                System.out.println("file written");
                objectOutputStream.close();
                fileOutputStream.close();
            }
            catch (IOException expection){
                System.out.println(expection);
            }
        }
        public ArrayList<Objects> readFromFile(){
            try{
                //Employee employee=new Employee();
                System.out.println("inside read");
                ArrayList<Objects> employeeArray=new ArrayList<>();
                FileInputStream fileInputStream=new FileInputStream(myfile);
                ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
                employeeArray.addAll((ArrayList<Objects>) objectInputStream.readObject());
                objectInputStream.close();
                fileInputStream.close();
                return employeeArray;
            }
            catch (IOException | ClassNotFoundException  ioException){
                System.out.println(ioException);
            }
            return null;
        }

    }
//    private ArrayList<>
//    private File myFile = new File("C:\\DLTE-JAVA-FULL-STACK-SANATHKUMARSJ-2024\\DLTE-JAVA\\DLTE-JAVA-TechReview\\Employee_Console\\src\\main\\employee.txt");
//    public boolean initializeFile() throws IOException {
//        if(myFile.createNewFile()){
//            System.out.println("File is created");
//            return true;
//        }
//        else
//            System.out.println("File is not created");
//        return false;
//    }
//    public void displayList() throws IOException, ClassNotFoundException {
//        System.out.println("On operation class");
//        readFromFile();
//    }
//
//    public void addEmployeeDetails(EmployeePersonalDetails employeeInfo, EmployeeAddressDetails employeeAddressInfo) throws ClassNotFoundException, IOException {
//        readFromFile();
//        employeeArray.add(employeeInfo);
//        employeeArray2.add(employeeAddressInfo);
//        writeToFile();
//        System.out.println("Employee Details Added Successfully");
//    }
//    public void readFromFile() throws IOException, ClassNotFoundException {
//        try{
//            FileInputStream fileInputStream = new FileInputStream(myFile);
//            ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
//            employeeArray.addAll((ArrayList<EmployeePersonalDetails>) objectInputStream.readObject());
//            employeeArray2.addAll((ArrayList<EmployeeAddressDetails>) objectInputStream.readObject());
//            objectInputStream.close();
//            fileInputStream.close();
//        }
//        catch (FileNotFoundException files){
//            writeToFile();
//        }
//    }
//
//    public void writeToFile() throws IOException,ClassNotFoundException {
//        FileOutputStream fileOutputStream = new FileOutputStream(myFile);
//        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(myFile, true));
//        objectOutputStream.writeObject(employeeArray);
//        objectOutputStream.writeObject(employeeArray2);
//        objectOutputStream.close();
//        fileOutputStream.close();
//    }


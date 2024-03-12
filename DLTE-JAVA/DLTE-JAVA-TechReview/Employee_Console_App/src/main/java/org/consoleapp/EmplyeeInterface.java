package org.consoleapp;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public interface EmplyeeInterface {
    public abstract void input(EmployeePersonalDetails personalDetails);
    public abstract EmployeeAddressDetails inputAdd();
    public abstract void display() throws IOException;
    public abstract void search() throws IOException;
}

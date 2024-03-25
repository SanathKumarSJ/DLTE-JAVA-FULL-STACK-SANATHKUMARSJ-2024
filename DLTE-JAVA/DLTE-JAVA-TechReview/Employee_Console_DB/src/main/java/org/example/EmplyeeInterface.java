package org.example;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface EmplyeeInterface {
    public abstract void inputPersonal(EmployeePersonalDetails personalDetails) throws SQLException;
    public abstract EmployeeAddressDetails inputAdd();
    public abstract void display() throws IOException;
    public abstract void search() throws IOException;
    void input(EmployeePersonalDetails employee);
}

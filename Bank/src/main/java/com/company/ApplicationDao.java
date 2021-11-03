package com.company;

import java.sql.SQLException;
import java.util.List;

public interface ApplicationDao {

    void addApplication(Application app) throws SQLException;

    void updateApplication(String state, Application app) throws SQLException;

    List<Application> checkApplication() throws SQLException;
}

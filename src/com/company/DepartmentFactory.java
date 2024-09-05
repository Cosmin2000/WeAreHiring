package com.company;

public class DepartmentFactory {

    public static Department getDepartment (String departmentName){
        if (departmentName.equalsIgnoreCase("IT"))
            return new IT();
        if (departmentName.equalsIgnoreCase("Marketing"))
            return new Marketing();
        if (departmentName.equalsIgnoreCase("Management"))
            return new Management();
        if (departmentName.equalsIgnoreCase("Finance"))
            return new Finance();
        return null;
    }
}

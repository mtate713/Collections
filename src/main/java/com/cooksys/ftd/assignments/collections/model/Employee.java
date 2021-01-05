package com.cooksys.ftd.assignments.collections.model;

import java.util.List;

public interface Employee {

    String getName();

    boolean hasManager();

    Manager getManager();

    List<Manager> getChainOfCommand();
    
    boolean equals(Object obj);
    
    int hashCode();


}

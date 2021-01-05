package com.cooksys.ftd.assignments.collections.model;


import java.util.ArrayList;
import java.util.List;


public class Manager implements Employee {

    private final String name;
    private Manager boss;

    public Manager(String name) {
        this.name = name;
        this.boss = null;
    }

 
    public Manager(String name, Manager manager) {
        this.name = name;
        this.boss = manager;
    }

   
    public String getName() {
        return this.name;
    }

    public boolean hasManager() {
    	 if (this.boss == null)
         	return false;
         else
         	return true;
    }

  
    public Manager getManager() {
        return this.boss;
    }
    
    public List<Manager> getChainOfCommand() {
    	List<Manager> bossList = new ArrayList<Manager>();
      
    	Manager temp = this;
    	while (temp.hasManager()) {
    		bossList.add(temp.getManager());
    		temp = temp.getManager();
    	}
      
        return bossList;
    }
    
    public int hashCode() {
    	final int prime = 73;
    	int result = prime;
    	if (!(this.getName() == null))
    		result += this.getName().hashCode();
    	if (!(this.getManager() == null))
    		result += this.getManager().hashCode();
    	
    	return result;
    		
    }
    
    public boolean equals(Object obj) {
    	if (this == obj)
    		return true;
    	if ((obj == null) || (this.getClass() != obj.getClass()))
    		return false;
    	Manager temp = (Manager) obj;
    	if (this.getName().equals(temp.getName())) {
    			if (((this.getManager() == null) && (temp.getManager() == null)) || (this.getManager().equals(temp.getManager())))
    				return true;
    			else 
    				return false;
    	}
    	else
    		return false;
    	
    }
    



}

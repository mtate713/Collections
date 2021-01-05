package com.cooksys.ftd.assignments.collections.model;

import com.cooksys.ftd.assignments.collections.model.Employee;
import com.cooksys.ftd.assignments.collections.model.Worker;
import com.cooksys.ftd.assignments.collections.model.Manager;

import java.util.*;


public class OrgChart {

    // TODO: this class needs to store employee data in private fields in order for the other methods to work as intended.
    //  Add those fields here. Consider how you want to store the data, and which collection types to use to make
    //  implementing the other methods as easy as possible. There are several different ways to approach this problem, so
    //  experiment and don't be afraid to change how you're storing your data if it's not working out!

	private Map<Manager, Set<Employee>> organization;
	
	
	public OrgChart() {
		this.organization = new HashMap<Manager, Set<Employee>>();
	}
	
	public Map<Manager, Set<Employee>> getOrganizationMap(){
		return this.organization;
	}
	

    public boolean addEmployee(Employee employee) {
        if (employee instanceof Worker) {
        	if (!(employee.hasManager()))
        		return false;
        	else {
        		if (this.getOrganizationMap().containsKey(employee.getManager())) {
        			if (this.getOrganizationMap().get(employee.getManager()).contains(employee))
        				return false;
        			else {
        				this.getOrganizationMap().get(employee.getManager()).add(employee);
            			return true;
        			}
        			
        		}
        			
        		else {
        			while ((employee.hasManager() && (!this.getOrganizationMap().containsKey(employee.getManager())))) {
	        			Set<Employee> subordinates = new HashSet<Employee>();
	        			subordinates.add(employee);
	        			this.getOrganizationMap().put(employee.getManager(), subordinates);
	        			employee = employee.getManager();
        			}
        			return true;	
        		}
        	}   
        	
        }
        
        else if (employee instanceof Manager) {
        	
        	if (!employee.hasManager()) {
        			if (this.getOrganizationMap().isEmpty()) {
        				this.getOrganizationMap().put((Manager) employee, new HashSet<Employee>());
        				return true;
        			}
        			else if (this.getOrganizationMap().containsKey((Manager) employee))
        				return false;
        			else {
        				this.getOrganizationMap().put((Manager) employee, new HashSet<Employee>());
        				return true;
        			}
        	}
        		
        	else {	
        		
        		boolean insertKey = false;
        		if (this.getOrganizationMap().containsKey(employee.getManager())) {
        			if (this.getOrganizationMap().get(employee.getManager()).contains(employee)) {
					} 
        			else {
        				this.getOrganizationMap().get(employee.getManager()).add(employee);
        			}		
    			}
        			
        		else {
        			Manager temp = (Manager) employee;
        			while ((temp.hasManager() && (!this.getOrganizationMap().containsKey(temp.getManager())))) {
	        			Set<Employee> subordinates = new HashSet<Employee>();
	        			subordinates.add(temp);
	        			this.getOrganizationMap().put(temp.getManager(), subordinates);
	        			temp = temp.getManager();
        			}
        				
        		}
        		
        		if (!this.getOrganizationMap().containsKey(employee)) {
    				this.getOrganizationMap().put((Manager) employee, new HashSet<Employee>());
    				insertKey = true;
    			}
        		return insertKey;
        		
        	} 
        	
        }
        	
        else
        	return false;
    }


    public boolean hasEmployee(Employee employee) {
    	if (getOrganizationMap().isEmpty())
    		return false;
    	
    	if (employee instanceof Manager)
    		return getOrganizationMap().containsKey(employee);
    	else if ((employee instanceof Worker) && (employee.hasManager()) ) {
	    	return getOrganizationMap().get(employee.getManager()).contains(employee);
    	}
    	else if (employee == null)
    		return true;
    	else
    		return false;
        
        	
    }

    public Set<Employee> getAllEmployees() {
    	Set<Employee> roll = new HashSet<Employee>();
    	
    	if ((this.getOrganizationMap() != null) && (!this.getOrganizationMap().isEmpty())) {
    		for (Map.Entry<Manager, Set<Employee>> entry : this.getOrganizationMap().entrySet()) {
    			if (!entry.getValue().isEmpty()) {	
	    			for (Employee e : entry.getValue()) {
	    				roll.add(e);
	    				if (e.hasManager())
	    					roll.addAll(e.getChainOfCommand());
	    			}
    			}
    			roll.add(entry.getKey());
        	}
    		
    	}
    	
        return roll;
    }

    
    public Set<Manager> getAllManagers() {
    	Set<Manager> bossList = new HashSet<Manager>();
    	if (!this.getOrganizationMap().isEmpty()) {
    		for (Map.Entry<Manager, Set<Employee>> entry : this.getOrganizationMap().entrySet()) {
    			if (!entry.getValue().isEmpty()) {	
	    			for (Employee e : entry.getValue()) {
	    				if (e instanceof Manager)
	    					bossList.add((Manager) e);
	    				if (e.hasManager())
	    					bossList.addAll(e.getChainOfCommand());
	    			}
    			}
    			bossList.add(entry.getKey());
        	}
    			
    	}
    	
    	return bossList;
    		
    		
    }

    public Set<Employee> getDirectSubordinates(Manager manager) {
    	Set<Employee> result = new HashSet<Employee>();
    	if (this.getOrganizationMap().get(manager) != null) {
    		result = clone(this.getOrganizationMap().get(manager));
    	}
        return result;
    }

    public Map<Manager, Set<Employee>> getFullHierarchy() {
    	
    	Map<Manager, Set<Employee>> result = new HashMap<Manager, Set<Employee>>();
    	if (!this.getOrganizationMap().isEmpty()) {
	    	for (Manager person : this.getAllManagers()) {
	    		result.put((Manager) clone(person), getDirectSubordinates(person));
	    	}
    	}
    	return result;
    }
    

    public Employee clone(Employee copy) {
    	if (copy instanceof Manager) {
    		Manager mCopy = new Manager(copy.getName(), copy.getManager());
    		return mCopy;
    	}
    	else {
    		Worker wCopy = new Worker(copy.getName(), copy.getManager());
    		return wCopy;
    	}
    		
    }
    
    public Set<Employee> clone(Set<Employee> copy) {
    	Set<Employee> c = new HashSet<Employee>();
    	for (Employee e : copy) {
    		c.add(clone(e));
    	}
    	return c;
    		
    }


}

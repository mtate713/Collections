package com.cooksys.ftd.assignments.collections.model;


import java.util.ArrayList;
import java.util.List;


public class Worker implements Employee {

    private final String name;
    private Manager boss;

    public Worker(String name) {
        this.name = name;
        this.boss = null;
    }


    public Worker(String name, Manager manager) {
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

    /**
     * TODO: Implement this method.
     *  <br><br>
     *  Retrieves the worker's chain of command as a {@code List<Manager>}, starting with their direct {@code Manager},
     *  followed by that {@code Manager}'s {@code Manager}, and so on, until the top of the hierarchy is reached.
     *  <br><br>
     *  The returned list should never be or contain {@code null}.
     *  <br><br>
     *  If the worker does not have a {@code Manager}, an empty
     *  {@code List<Manager>} should be returned.
     *
     * @return a {@code List<Manager>} that represents the worker's chain of command,
     */
    
    public List<Manager> getChainOfCommand() {
        List<Manager> bossList = new ArrayList<Manager>();
        bossList.clear();
        if (this.hasManager()) {
        	bossList.add(this.getManager());
        	bossList.addAll(this.getManager().getChainOfCommand());
        }
        return bossList;
    }
    
    public int hashCode() {
    	final int prime = 101;
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
    	Worker temp = (Worker) obj;
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

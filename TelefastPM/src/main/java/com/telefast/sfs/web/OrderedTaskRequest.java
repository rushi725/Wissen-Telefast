package com.telefast.sfs.web;

import com.telefast.sfs.model.Employee;
import com.telefast.sfs.model.OrderedService;
import com.telefast.sfs.model.OrderedTask;
import com.telefast.sfs.model.Project;

public class OrderedTaskRequest {
	private OrderedTask orderedTask;
	private OrderedService orderedService;
	private Project project;
	private Employee employee;
	
	@Override
	public String toString() {
		return "OrderedTaskRequest [orderedTask=" + orderedTask + ", orderedService=" + orderedService + ", project="
				+ project + ", employee=" + employee + "]";
	}
	public OrderedTask getOrderedTask() {
		return orderedTask;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public void setOrderedTask(OrderedTask orderedTask) {
		this.orderedTask = orderedTask;
	}
	public OrderedService getOrderedService() {
		return orderedService;
	}
	public void setOrderedService(OrderedService orderedService) {
		this.orderedService = orderedService;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	
	

}

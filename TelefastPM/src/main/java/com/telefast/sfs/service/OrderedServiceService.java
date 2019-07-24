package com.telefast.sfs.service;

import java.util.List;

import com.telefast.sfs.model.OrderedService;


public interface OrderedServiceService {
	
	boolean addService(OrderedService orderedService);
	
	List<OrderedService> findAllOrderedServicesByServiceManagerId(int serviceManagerId);

	List<OrderedService> findAllOrderedServiceByProjectManagerId(int projectManagerId);
	

	
	boolean completeService(int orderedServiceId);

	void updateProgress(OrderedService orderedService);

}

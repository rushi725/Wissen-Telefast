package com.telefast.sfs.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;

import com.telefast.sfs.model.Employee;
import com.telefast.sfs.model.OrderedService;
import com.telefast.sfs.model.OrderedTask;
import com.telefast.sfs.model.Project;
import com.telefast.sfs.model.Service;
import com.telefast.sfs.model.Status;
import com.telefast.sfs.repository.EmployeeRepository;
import com.telefast.sfs.repository.OrderedServiceRepository;
import com.telefast.sfs.repository.OrderedTaskRepository;
import com.telefast.sfs.repository.ProjectRepository;
import com.telefast.sfs.repository.ServiceRepository;

@org.springframework.stereotype.Service
public class OrderedServiceServiceImpl implements OrderedServiceService {
	
	@Autowired
	private OrderedServiceRepository orderedServiceRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private ServiceRepository serviceRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private OrderedTaskRepository orderedTaskRepository;



	@Override
	public List<OrderedService> findAllOrderedServicesByServiceManagerId(int serviceManagerId) {
		List<OrderedService> list = orderedServiceRepository.findAllOrderedServicesByServiceManagerId(serviceManagerId);
		return list;
	}

	@Override
	public List<OrderedService> findAllOrderedServiceByProjectManagerId(int projectManagerId) {
		//get all projects assigned to projectManager
		List<Integer> projectIds = projectRepository.findProjectIdsByProjectManagerId(projectManagerId);
		
		//get orderedServices by projectIds
		List<OrderedService> list = orderedServiceRepository.findAllOrderedServicesByProjectIds(projectIds);
		return list;
	}

	@Override
	public boolean addService(OrderedService orderedService) {
		Service service = new Service();
		Project project = new Project();
		Employee serviceManager = new Employee();

		service = serviceRepository.findById(orderedService.getService().getId()).get();
		project = projectRepository.findById(orderedService.getProject().getProjectId()).get();
		serviceManager = employeeRepository.findById(orderedService.getEmployee().getId()).get();

		orderedService.setService(service);
		orderedService.setProject(project);
		orderedService.setEmployee(serviceManager);

		orderedService = orderedServiceRepository.save(orderedService);
		if(orderedService == null)
			return false;
		else return true;
	}



	@Override
	public boolean completeService(int orderedServiceId) {
		OrderedService orderedService = orderedServiceRepository.findById(orderedServiceId).get();
		orderedService.setServiceStatus(Status.CANCELLED);
		orderedServiceRepository.save(orderedService);		
		orderedService = orderedServiceRepository.save(orderedService);
		if(orderedService == null)
			return false;
		else return true;
	}

	@Override
	public void updateProgress(OrderedService orderedService) {
		
		List<OrderedTask> list = orderedTaskRepository.findByOrderedService(orderedService);
		
		double totalOrderedTask = list.size();
		
		double completedOrderedTask = list.stream().filter(e->e.getTaskStatus()==Status.COMPLETED).count();
		
		double progress = (completedOrderedTask/totalOrderedTask)*100;
		
		orderedService.setProgress((int)progress);
		if(progress==100) {
			orderedService.setServiceStatus(Status.COMPLETED);
		}
		orderedServiceRepository.save(orderedService);
	}

	@Override
	public void updateProjectProgress(Project project) {
		
		List<OrderedService> serviceList = orderedServiceRepository.findByProject(project);
		
		double totalServices = serviceList.size();
		
		double completedOrderedServices = serviceList.stream().filter(e->e.getServiceStatus()==Status.COMPLETED).count();
		
		double progress = (completedOrderedServices/totalServices)*100;
		project.setProgress((int)progress);
		if(progress==100) {
			project.setStatus(Status.COMPLETED);
		}
		projectRepository.save(project);
	}
	

}

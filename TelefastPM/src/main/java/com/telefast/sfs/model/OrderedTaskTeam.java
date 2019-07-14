package com.telefast.sfs.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ORDEREDTASKTEAM")
public class OrderedTaskTeam {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int orderedTaskId;
	
	@ManyToOne
	@JoinColumn(name = "teamId")
	private Team team;
	
	@OneToOne
	@JoinColumn(name = "orderedTaskId")
	private OrderedTask orderedTask;
	
	
	public int getId() {
		return orderedTaskId;
	}
	public void setId(int id) {
		this.orderedTaskId = id;
	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
//	public OrderedTask getOrderedTask() {
//		return orderedTask;
//	}
//	public void setOrderedTask(OrderedTask orderedTask) {
//		this.orderedTask = orderedTask;
//	}
//	
	
}

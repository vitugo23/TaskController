package com.example.demo.model;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {
	public List<Task> findByTasktitle(String tasktitle);
	@Query("select e from Employee e where e.salary >5000 and e.salary<10000")
	public List<Task> getTaskByDuration();
	public List<Task> findByOrderByAssignedTo();
}
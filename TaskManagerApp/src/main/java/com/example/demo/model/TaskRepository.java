package com.example.demo.model;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {
	public List<Task> findByTasktitle(String tasktitle);
	@Query("select t from Task t where t.tasktitle = ? and t.title = ?")
	public List<Task> findByAssignedTo(String assignedTo );

}

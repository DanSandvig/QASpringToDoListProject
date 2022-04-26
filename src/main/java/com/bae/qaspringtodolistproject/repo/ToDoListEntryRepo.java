package com.bae.qaspringtodolistproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bae.qaspringtodolistproject.domain.ToDoListEntry;

@Repository
public interface ToDoListEntryRepo extends JpaRepository<ToDoListEntry, Long> {

}

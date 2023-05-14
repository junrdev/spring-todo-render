package com.springtodo.todoapp.repository;

import com.springtodo.todoapp.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepo extends JpaRepository<Todo, Long> {

    @Query(value = "select * from todo n where is_done =:status", nativeQuery = true)
    List<Todo> getTodosByStatus(Boolean status);
}

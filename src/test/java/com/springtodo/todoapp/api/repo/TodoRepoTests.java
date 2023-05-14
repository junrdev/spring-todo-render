package com.springtodo.todoapp.api.repo;

import com.springtodo.todoapp.model.Todo;
import com.springtodo.todoapp.repository.TodoRepo;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TodoRepoTests {


    private static final Logger LOGGER = LoggerFactory.getLogger(TodoRepoTests.class);

    private final TodoRepo todoRepo;

    @Autowired
    public TodoRepoTests(TodoRepo todoRepo) {
        this.todoRepo = todoRepo;
    }


    @Test
    void contextLoads(){}

    @Test
    void getAllTodos(){
        List<Todo> todos = todoRepo.findAll();

        LOGGER.info("Todos : {}",todos);
    }
}

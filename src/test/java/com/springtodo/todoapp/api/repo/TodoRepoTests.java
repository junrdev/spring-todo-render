package com.springtodo.todoapp.api.repo;

import com.springtodo.todoapp.model.Todo;
import com.springtodo.todoapp.repository.TodoRepo;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class TodoRepoTests {


    private static final Logger LOGGER = LoggerFactory.getLogger(TodoRepoTests.class);

    private final TodoRepo todoRepo;

    @Autowired
    public TodoRepoTests(TodoRepo todoRepo) {
        this.todoRepo = todoRepo;
    }


    @Test
    void contextLoads() {
    }

    @Test
    void getAllTodos() {
        List<Todo> todos = todoRepo.findAll();

        LOGGER.info("Todos : {}", todos);
    }

    @Test
    void createTodo() {
        Todo todo = Todo.builder()
                .title("First Todo")
                .content("Hello world")
                .isDone(false)
                .build();

        LOGGER.info("Saved Todo : {} ", todoRepo.save(todo));
    }

    @Test
    void updateStatus() {
        Optional<Todo> optionalTodo = todoRepo.findById(1l);

        if (optionalTodo.isEmpty())
            LOGGER.error("Todo with id : 1 not found");
        else {

            Todo update = optionalTodo.get();
            update.setIsDone(!update.getIsDone());

            LOGGER.info("Updated todo {}", todoRepo.save(update));
        }
    }

    @Test
    void getTodoById() {
        Optional<Todo> optionalTodo = todoRepo.findById(1l);

        if (optionalTodo.isEmpty())
            LOGGER.error("Todo with id : 1 not found");
        else {
            LOGGER.info("Todo 1 : {}", optionalTodo.get());
        }
    }

    @Test
    void getTodosByStatus(){
        Boolean status = true;

        List<Todo> todos = todoRepo.getTodosByStatus(status);

        LOGGER.info("Todos with status "+status+" :{}", todos);
    }
    @Test
    void deleteTodo() {
        Optional<Todo> optionalTodo = todoRepo.findById(1l);

        if (optionalTodo.isEmpty())
            LOGGER.error("Todo with id : 1 not found");
        else {
            todoRepo.delete(optionalTodo.get());
            LOGGER.info("Todo with id 1 deleted");
        }
    }

    @Test
    void deleteTodos() {
            todoRepo.deleteAll();
            LOGGER.info("All Todos deleted");
    }
}

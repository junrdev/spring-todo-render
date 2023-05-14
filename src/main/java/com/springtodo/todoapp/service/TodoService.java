package com.springtodo.todoapp.service;

import com.springtodo.todoapp.dto.TodoDto;
import com.springtodo.todoapp.model.Todo;
import com.springtodo.todoapp.repository.TodoRepo;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepo todoRepo;

    @Autowired
    public TodoService(TodoRepo todoRepo) {
        this.todoRepo = todoRepo;
    }

    public ResponseEntity<List<Todo>> getAllTodos(){
        return ResponseEntity.ok(todoRepo.findAll());
    }

    public ResponseEntity<Todo> getTodoById(String id){

        Optional<Todo> optionalTodo = todoRepo.findById(Long.parseLong(id));

        if(optionalTodo.isEmpty())
            throw new RuntimeException("Invalid Todo Id");

        return ResponseEntity.ok(optionalTodo.get());
    }

    public ResponseEntity<List<Todo>> getTodoByStatus(Boolean status){
        return ResponseEntity.ok(todoRepo.getTodosByStatus(status));
    }

    public ResponseEntity<Todo> createTodo(TodoDto todoDto){
        Todo newTodo = Todo.builder()
                .title(todoDto.getTitle())
                .content(todoDto.getContent())
                .isDone(false)
                .build();

        return new ResponseEntity<>(todoRepo.save(newTodo), HttpStatus.CREATED);
    }

    public ResponseEntity<String> updateTodoStatus(String id, Boolean status){
        Optional<Todo> optionalTodo = todoRepo.findById(Long.parseLong(id));

        if(optionalTodo.isEmpty())
            throw new RuntimeException("Invalid Todo Id");

        Todo savedTodo = optionalTodo.get();
        savedTodo.setIsDone(status);

        todoRepo.save(savedTodo);

        return new ResponseEntity("Updates", HttpStatus.CREATED);

    }

    public ResponseEntity<String> deleteTodo(String id){
        Optional<Todo> optionalTodo = todoRepo.findById(Long.parseLong(id));

        if(optionalTodo.isEmpty())
            throw new RuntimeException("Invalid Todo Id");

        todoRepo.delete(optionalTodo.get());

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<String> deleteAllTodos(){
        todoRepo.deleteAll();

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}

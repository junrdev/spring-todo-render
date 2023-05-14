package com.springtodo.todoapp.controller;

import com.springtodo.todoapp.dto.TodoDto;
import com.springtodo.todoapp.model.Todo;
import com.springtodo.todoapp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class ApiController {

    private final TodoService todoService;

    @Autowired
    public ApiController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Todo>> getAll(){
        return todoService.getAllTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodo(@PathVariable String id){
        return todoService.getTodoById(id);
    }

    @PostMapping("/")
    public ResponseEntity<Todo> createTodo(@RequestBody TodoDto todoDto){
        return todoService.createTodo(todoDto);
    }

    @GetMapping("/{status}")
    public ResponseEntity<List<Todo>> getTodosByStatus(@PathVariable Boolean status){
        return todoService.getTodoByStatus(status);
    }

    @PatchMapping("/{id}/{status}")
    public ResponseEntity<String> updateTodoStatus(@PathVariable(name = "id")String id, @PathVariable(name = "status") Boolean status){
        return todoService.updateTodoStatus(id, status);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable(name = "id") String id){
        return todoService.deleteTodo(id);
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<String> deleteAll(){
        return todoService.deleteAllTodos();
    }
}

package com.springtodo.todoapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "todos")
public class Todo {

    @SequenceGenerator(name = "todo_seq", allocationSize = 1, sequenceName = "todo_seq")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "todo_seq")
    private Long Id;

    private String title;

    private String content;

    private Boolean isDone;

}

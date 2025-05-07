package com.isep.lab2todoapi.presentation;

import com.isep.lab2todoapi.Todo;
import com.isep.lab2todoapi.application.TodoManager;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/todos")
public class TodoController {


    private final TodoManager todoManager;

    public TodoController(TodoManager todoManager) {
        this.todoManager = todoManager;
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos() {
        return ResponseEntity.ok(todoManager.GetAllTodos());
    }

    @PostMapping
    public ResponseEntity createTodo(@RequestBody @Valid Todo todo) {
        todoManager.addTodo(todo);
        return ResponseEntity.ok().build();
    }
}

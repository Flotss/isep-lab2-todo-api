package com.isep.lab2todoapi.application;

import com.isep.lab2todoapi.Todo;

import java.util.List;

public interface ITodoRepository {
    void addTodo(Todo todo);
    List<Todo> getAllTodos();
}
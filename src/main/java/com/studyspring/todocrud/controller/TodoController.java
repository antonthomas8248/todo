package com.studyspring.todocrud.controller;

import com.studyspring.todocrud.dto.TodoDto;
import com.studyspring.todocrud.exception.AppException;
import com.studyspring.todocrud.model.Todo;
import com.studyspring.todocrud.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.HTML;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoDto> addTodo(@Valid @RequestBody TodoDto todoDto) {
                return ResponseEntity.status(HttpStatus.CREATED).body(todoService.addTodo(todoDto));
    }

    @GetMapping
    public ResponseEntity <List<TodoDto>> getAllTodos() {
        List<TodoDto> todos = todoService.getAllTodo();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable int id) {

        TodoDto todo = todoService.getTodo(id);
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoDto> updateTodo(@PathVariable int id, @RequestBody TodoDto todoDto) {

        TodoDto updatedTodo = todoService.updateTodo(id, todoDto);
        return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TodoDto> deleteTodo(@PathVariable int id) {
        todoService.deleteTodo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
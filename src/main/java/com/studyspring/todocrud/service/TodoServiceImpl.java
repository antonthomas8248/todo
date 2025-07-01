package com.studyspring.todocrud.service;

import com.studyspring.todocrud.dto.TodoDto;
import com.studyspring.todocrud.exception.AppException;
import com.studyspring.todocrud.model.Todo;
import com.studyspring.todocrud.repo.TodoRepo;
import com.studyspring.todocrud.utils.mapper.TodoMapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class TodoServiceImpl implements TodoService{
    private static final Logger logger = LogManager.getLogger(TodoServiceImpl.class);
    private final TodoRepo todoRepo;
    private final TodoMapper todoMapper;

    public TodoServiceImpl(TodoRepo todoRepo, TodoMapper todoMapper) {
        this.todoRepo = todoRepo;
        this.todoMapper = todoMapper;
    }

    @Override
    public TodoDto addTodo(TodoDto todoDto) {
        Todo todo = todoRepo.save(todoMapper.dtoToEntity(todoDto));
        logger.info("Adding a new todo: {}",todo.getTitle());
        return todoMapper.entityToDto(todo);
    }

    @Override
    public List<TodoDto> getAllTodo()  {
        logger.info("Fetching all todos");
        return todoRepo.findAll()
                .stream()
                .map(todoMapper::entityToDto)
                .toList();
    }

        @Override
        public TodoDto getTodo(int id) {

            return todoRepo.findById(id)
                    .map(todoMapper::entityToDto)
                    .orElseThrow(() -> new AppException("Todo not found with id " +id, HttpStatus.NOT_FOUND));
        }

    @Override
    public TodoDto updateTodo(int id, TodoDto todoDto) {
        Todo currentTodo=todoRepo.findById(id)
                .orElseThrow(()->new AppException("Todo not found with id "+id,HttpStatus.NOT_FOUND));
        System.out.println(currentTodo.getId());
        currentTodo.setTitle(todoDto.getTitle());

        currentTodo.setDescription(todoDto.getDescription());
        currentTodo.setDate(todoDto.getDate());
        Todo update= todoRepo.save(currentTodo);

        return todoMapper.entityToDto(update);
    }

    @Override
    public void deleteTodo(int id) {
        Todo todo = todoRepo.findById(id)
                .orElseThrow(() -> new AppException("Todo not found with id "+id,HttpStatus.NOT_FOUND));
        todoRepo.deleteById(id);
        logger.info("Todo deleted successfully: " + id);
    }

}

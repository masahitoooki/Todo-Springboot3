package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Todo;
import com.example.demo.repository.TodoRepository;

@Service
public class TodoService {

	@Autowired
	TodoRepository todoRepository;

	// todolistを全件取得
	public List<Todo> searchAll(){
		return todoRepository.findAll();
	}

	// 追加(Todoを新たにDBに登録する)
	public void addTodo(Todo todo) {
		todoRepository.save(todo);
	}

	// 追加(idに応じたTodoを返却する)
	public Todo findById(Integer id) {
		Optional<Todo> updateTodo = todoRepository.findById(id);
	    return updateTodo.get();
	}
}

package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Todo;
import com.example.demo.service.TodoService;

@Controller
public class TodoController {

	@Autowired
	TodoService todoService;

	@GetMapping("/")
	public String home(@ModelAttribute Todo todo, Model model) {
		List<Todo> allTodo = todoService.searchAll();
		model.addAttribute("allTodo", allTodo);
		return "home";
	}

	// 追加(formのth:actionと結びついている)
	@PostMapping("/")
	public String create(@Validated Todo todo, BindingResult result, Model model) {
		// 入力値にエラーがあった時
		if(result.hasErrors()) {
			List<Todo> allTodo = todoService.searchAll();
			model.addAttribute("allTodo", allTodo);
			model.addAttribute("todo", todo);
			return "home";
		}

		// エラーがなかった時は、Todoを新規登録する
		todoService.addTodo(todo);
		return "redirect:/";
	}

	// 追加(未完了タスク一覧にある丸いボタンが押されたら、この処理が実行される)
	@PostMapping("/done")
	public String done(@RequestParam(name="id") Integer todoId) {
		Todo updateTodo = todoService.findById(todoId);
		updateTodo.setDone(true);
		todoService.addTodo(updateTodo);
		return "redirect:/";
	}
}

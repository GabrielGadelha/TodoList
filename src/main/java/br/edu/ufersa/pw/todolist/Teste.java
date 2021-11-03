package br.edu.ufersa.pw.todolist;

import org.springframework.web.client.RestTemplate;

import br.edu.ufersa.pw.todolist.dto.TodoDTO;

public class Teste {
	public static void main (String args[])
	{
		RestTemplate restTemplate = new RestTemplate();
		TodoDTO todo[]= restTemplate.getForObject("https://teste-gadelha1.herokuapp.com/api/todos/search/byEmail?email=nanda@teste.com", TodoDTO[].class);		
		System.out.println(todo[0].getTodo());
	}
}

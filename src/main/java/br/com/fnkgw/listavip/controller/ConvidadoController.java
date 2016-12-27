package br.com.fnkgw.listavip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.fnkgw.enviadorEmail.EmailService;
import br.com.fnkgw.listavip.model.Convidado;
import br.com.fnkgw.listavip.repository.ConvidadoRepository;
import br.com.fnkgw.listavip.service.ConvidadoService;

@Controller
public class ConvidadoController {

	@Autowired
	private ConvidadoService service;
	
	@RequestMapping("/")
	public String home() {
		return "index";
	}
	
	@RequestMapping("listaconvidados")
	public String listaconvidados(Model model) {
		
		Iterable<Convidado> convidados = service.obterTodos();
		model.addAttribute("convidados", convidados);
		
		return "listaconvidados";
	}
	
	@RequestMapping(value="salvar", method=RequestMethod.POST)
	public String salvar(@RequestParam("nome") String nome, @RequestParam("email") String email, 
			@RequestParam("telefone") String telefone, Model model) {
		Convidado novoConvidado = new Convidado(nome, email, telefone);
		service.salvar(novoConvidado);
		
		//new EmailService().enviar(nome, email);
		
		Iterable<Convidado> convidados = service.obterTodos();
		model.addAttribute("convidados", convidados);
		
		return "listaconvidados";
	}
}

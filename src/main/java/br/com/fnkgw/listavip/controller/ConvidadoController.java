package br.com.fnkgw.listavip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.fnkgw.listavip.model.Convidado;
import br.com.fnkgw.listavip.repository.ConvidadoRepository;

@Controller
public class ConvidadoController {

	@Autowired
	private ConvidadoRepository repository;
	
	@RequestMapping("/")
	public String home() {
		return "index";
	}
	
	@RequestMapping("listaconvidados")
	public String listaconvidados(Model model) {
		
		Iterable<Convidado> convidados = repository.findAll();
		model.addAttribute("convidados", convidados);
		
		return "listaconvidados";
	}
}

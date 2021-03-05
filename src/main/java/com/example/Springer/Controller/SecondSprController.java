package com.example.Springer.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import com.example.Springer.Exception.ResourceNotFoundException;
import com.example.Springer.Respository.SecondSprRepository;
import com.example.Springer.Telefone.Telefone;

@RestController
@RequestMapping("/api/tel")
public class SecondSprController {
	
	@Autowired
	private SecondSprRepository Repositorio;
	
	//retorno de todos os telefones da api
	@GetMapping("/telefones")
	public List<Telefone> getall(){
		return Repositorio.findAll();
		
		//Em retorno/get no spring, só realizamos uma lista com o nome
		//da classe realizada, junto do getall, para selecionar todas as info
		//a seguir um retorno da variavel do tipo repositorio instanciado.
	}
	
	//criar novos telefones dentro da api
	@PostMapping("/telefones")
	public Telefone createTelefone(@Validated @RequestBody Telefone telefone) {
		return Repositorio.save(telefone);
		
		//A criação é feita através da inserção no banco de dados, validando(Validated) pelo RequestBody
		//Após, inserir somente um retorno do tipo da variavel repositorio com o .save()
	}
	
	//retornar telefones pelo id do mesmo
	@GetMapping("/telefones/{num_tel}")
	public ResponseEntity<Telefone> getById(@PathVariable(value= "num_tel") long num_tel){
		 Telefone telefone = Repositorio.findById(num_tel)
				 .orElseThrow(() -> new ResourceNotFoundException("Sem registros encontrados pelo numero :: " + num_tel));
		return ResponseEntity.ok().body(telefone);
		
		//Essa parte ele retorna pelo ResponseEntity, manipulador da classe, pelo ID, que seria o numero de telefone
		//Se há registros no banco ou não, se negativo, retorna o notfoundexception, se positivo, retorna 
		//responseentity.ok() , com o body, que seria os dados retornados.
	}
	
	//atualizar telefones
	
	@PutMapping("/telefones/{num_tel}")
	public ResponseEntity<Telefone> getBydId(@PathVariable(value="num_tel") long num_tel , @RequestBody Telefone telefoneUpdate)
			throws ResourceNotFoundException{
		Telefone telUp = Repositorio.findById(num_tel)
				.orElseThrow(() -> new ResourceNotFoundException("Sem registros encontrados pelo numero :: " + num_tel));
		telUp.setNumero(telefoneUpdate.getNumero());
		telUp.setDdd(telefoneUpdate.getDdd());
		telUp.setUsuario(telefoneUpdate.getUsuario());
		
		Repositorio.save(telUp);
		return ResponseEntity.ok().body(telUp);
		
	}
	//deletar telefones
	@DeleteMapping("/telefones/{num_tel}")
	public ResponseEntity<Telefone> deleteTelefone(@PathVariable(value="num_tel") long num_tel) 
			throws ResourceNotFoundException{
		Repositorio.findById(num_tel)
		.orElseThrow(() -> new ResourceNotFoundException("Sem registros encontrados pelo numero :: " + num_tel));
		
		Repositorio.deleteById(num_tel);
		return ResponseEntity.ok().build();
	}

}

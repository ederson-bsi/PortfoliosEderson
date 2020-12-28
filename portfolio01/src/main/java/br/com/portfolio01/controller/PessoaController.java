package br.com.portfolio01.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.portfolio01.entity.Pessoa;
import br.com.portfolio01.repository.PessoaRepository;

@RestController
@RequestMapping(value = "api")
@CrossOrigin(origins = "*")
public class PessoaController {
	
	@Autowired
	PessoaRepository _pessoaRepository;
	
	@GetMapping(value = "/pessoas")
	public List<Pessoa> listarTodas() {
		return _pessoaRepository.findAll();
	}
	
	@GetMapping(value = "/pessoa/{id}")
	public ResponseEntity<Pessoa> listarPorId(@PathVariable(value = "id") long id) {
		Optional<Pessoa> pessoa = _pessoaRepository.findById(id);
		if (pessoa.isPresent()) {
			return new ResponseEntity<Pessoa>(pessoa.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<Pessoa>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value = "/pessoa")
	public Pessoa salvarPessoa(@Valid @RequestBody Pessoa pessoa) {
		return _pessoaRepository.save(pessoa);
	}
	
	@PutMapping(value = "/pessoa/{id}")
	public ResponseEntity<Pessoa> editarPessoa(@Valid @PathVariable(value = "id") long id, @RequestBody Pessoa newPessoa) {
		Optional<Pessoa> oldPessoa = _pessoaRepository.findById(id);
		if (oldPessoa.isPresent()) {
			Pessoa pessoa = oldPessoa.get();
			pessoa.setNome(newPessoa.getNome());
			pessoa.setCelular(newPessoa.getCelular());
			pessoa.setAtivo(newPessoa.getAtivo());
			pessoa.setSexo(newPessoa.getSexo());
			pessoa.setEndereco(newPessoa.getEndereco());
			
			_pessoaRepository.save(pessoa);
			return new ResponseEntity<Pessoa>(pessoa, HttpStatus.OK);
		}else {
			return new ResponseEntity<Pessoa>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(value = "/pessoa/{id}")
	public ResponseEntity<Object> excluirPessoa(@PathVariable(value = "id") long id) {
		Optional<Pessoa> pessoa = _pessoaRepository.findById(id);
		if (pessoa.isPresent()) {
			_pessoaRepository.delete(pessoa.get());
			
			return new ResponseEntity<Object>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
	}

}

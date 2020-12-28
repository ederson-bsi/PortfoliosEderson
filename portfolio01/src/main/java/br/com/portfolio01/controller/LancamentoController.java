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

import br.com.portfolio01.entity.Lancamento;
import br.com.portfolio01.repository.LancamentoRepository;

@RestController
@RequestMapping(value = "api")
@CrossOrigin(origins = "*")
public class LancamentoController {
	
	@Autowired
	LancamentoRepository _lancamentoRepository;
	
	@GetMapping(value = "/lancamentos")
	public List<Lancamento> listarTodos() {
		return _lancamentoRepository.findAll();
	}
	
	@GetMapping(value = "/lancamento/{id}")
	public ResponseEntity<Lancamento> listarPorId(@PathVariable(value = "id") long id) {
		Optional<Lancamento> lancamento = _lancamentoRepository.findById(id);
		if (lancamento.isPresent()) {
			return new ResponseEntity<Lancamento>(lancamento.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<Lancamento>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value = "/lancamento")
	public Lancamento salvarLancamento(@Valid @RequestBody Lancamento lancamento) {
		return _lancamentoRepository.save(lancamento);
	}
	
	@PutMapping(value = "/lancamento/{id}")
	public ResponseEntity<Lancamento> editarLancamento(@Valid @PathVariable(value = "id") long id, @RequestBody Lancamento newLancamento) {
		Optional<Lancamento> oldLancamento = _lancamentoRepository.findById(id);
		if (oldLancamento.isPresent()) {
			Lancamento lancamento = oldLancamento.get();
			lancamento.setDescricao(newLancamento.getDescricao());
			lancamento.setDataPagamento(newLancamento.getDataPagamento());
			lancamento.setDataVencimento(newLancamento.getDataVencimento());
			lancamento.setObservacao(newLancamento.getObservacao());
			lancamento.setTipo(newLancamento.getTipo());
			lancamento.setValor(newLancamento.getValor());
			lancamento.setCategoria(newLancamento.getCategoria());
			lancamento.setPessoa(newLancamento.getPessoa());
			
			_lancamentoRepository.save(lancamento);
			return new ResponseEntity<Lancamento>(lancamento, HttpStatus.OK);
		}else {
			return new ResponseEntity<Lancamento>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(value = "/lancamento/{id}")
	public ResponseEntity<Object> excluirLancamento(@PathVariable(value = "id") long id) {
		Optional<Lancamento> lancamento = _lancamentoRepository.findById(id);
		if (lancamento.isPresent()) {
			_lancamentoRepository.delete(lancamento.get());
			return new ResponseEntity<Object>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
	}

}

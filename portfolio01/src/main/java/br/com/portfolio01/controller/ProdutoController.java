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

import br.com.portfolio01.entity.Produto;
import br.com.portfolio01.repository.ProdutoRepository;

@RestController
@RequestMapping(value = "api")
@CrossOrigin(origins = "*")
public class ProdutoController {

	@Autowired
	ProdutoRepository _produtoRepository;
	
	@GetMapping(value = "/produtos")
	public List<Produto> listarTodos() {
		return _produtoRepository.findAll();
	}
	
	@GetMapping(value = "/produto/{id}")
	public ResponseEntity<Produto> listarPorId(@PathVariable(value = "id") long id) {
		Optional<Produto> produto = _produtoRepository.findById(id);
		if (produto.isPresent()) {
			return new ResponseEntity<Produto>(produto.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<Produto>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value = "/produto")
	public Produto salvarProduto(@Valid @RequestBody Produto produto) {
		return _produtoRepository.save(produto);
	}
	
	@PutMapping(value = "/produto/{id}")
	public ResponseEntity<Produto> editarLancamento(@Valid @PathVariable(value = "id") long id, @RequestBody Produto newProduto) {
		Optional<Produto> oldProduto = _produtoRepository.findById(id);
		if (oldProduto.isPresent()) {
			Produto produto = oldProduto.get();
			produto.setNome(newProduto.getNome());
			produto.setDataCompra(newProduto.getDataCompra());
			produto.setQuantidade(newProduto.getQuantidade());
			produto.setValor(newProduto.getValor());
			
			_produtoRepository.save(produto);
			return new ResponseEntity<Produto>(produto, HttpStatus.OK);
		}else {
			return new ResponseEntity<Produto>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(value = "/produto/{id}")
	public ResponseEntity<Object> excluirProduto(@PathVariable(value = "id") long id) {
		Optional<Produto> produto = _produtoRepository.findById(id);
		if (produto.isPresent()) {
			_produtoRepository.delete(produto.get());
			
			return new ResponseEntity<Object>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
	}
}

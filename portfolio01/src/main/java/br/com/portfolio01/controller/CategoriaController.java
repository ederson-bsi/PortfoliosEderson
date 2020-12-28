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

import br.com.portfolio01.entity.Categoria;
import br.com.portfolio01.repository.CategoriaRepository;

@RestController
@RequestMapping(value = "api")
@CrossOrigin(origins = "*")
public class CategoriaController {
	
	@Autowired
	CategoriaRepository _categoriaRepository;
	
	@GetMapping(value = "/categorias")
	public List<Categoria> listarTodas() {
		return _categoriaRepository.findAll();
	}
	
	@GetMapping(value = "/categoria/{id}")
	public ResponseEntity<Categoria> listarPorId(@PathVariable(value = "id") long id) {
		Optional<Categoria> categoria = _categoriaRepository.findById(id);
		if (categoria.isPresent()) {
			return new ResponseEntity<Categoria>(categoria.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<Categoria>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value = "/categoria")
	public Categoria salvarCategoria(@Valid @RequestBody Categoria categoria) {
		return _categoriaRepository.save(categoria);
	}
	
	@PutMapping(value = "/categoria/{id}")
	public ResponseEntity<Categoria> editarCategoria(@Valid @PathVariable(value = "id") long id, @RequestBody Categoria newCategoria) {
		Optional<Categoria> oldCategoria = _categoriaRepository.findById(id);
		if (oldCategoria.isPresent()) {
			Categoria categoria = oldCategoria.get();
			categoria.setNome(newCategoria.getNome());
			
			_categoriaRepository.save(categoria);
			return new ResponseEntity<Categoria>(categoria, HttpStatus.OK);
		}else {
			return new ResponseEntity<Categoria>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(value = "/categoria/{id}")
	public ResponseEntity<Object> excluirCategoria(@PathVariable(value = "id") long id) {
		Optional<Categoria> categoria = _categoriaRepository.findById(id);
		if (categoria.isPresent()) {
			_categoriaRepository.delete(categoria.get());
			
			return new ResponseEntity<Object>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
	}

}

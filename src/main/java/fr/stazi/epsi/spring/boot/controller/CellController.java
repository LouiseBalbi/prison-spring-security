package fr.stazi.epsi.spring.boot.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.stazi.epsi.spring.boot.entity.Cell;
import fr.stazi.epsi.spring.boot.entity.DangerLevel;
import fr.stazi.epsi.spring.boot.entity.Prisoner;
import fr.stazi.epsi.spring.boot.exception.AlreadyExistsException;
import fr.stazi.epsi.spring.boot.exception.NotFoundException;
import fr.stazi.epsi.spring.boot.repository.Service;
import fr.stazi.epsi.spring.boot.repository.cellRepository;

@RestController
@RequestMapping("/api/cell")
public class CellController {
	
	@Autowired
	private cellRepository cellReposotiry;
	
	private Service service;
	
	@GetMapping
	public List<Cell> getCells() {
		return cellReposotiry.findAll();
	}
	
	@GetMapping("/{id}")
	public Cell getCell(@PathVariable Long id) {
		return cellReposotiry.findById(id).orElse(null);
	}
	
	
	
	@PreAuthorize("hasAuthority('CELL_CREATE')")
	@PostMapping
	public Cell createCell(@RequestBody Cell entity) throws AlreadyExistsException {
		if (entity.getId() == null) {
			return cellReposotiry.save(entity);
		} 
		
		throw new AlreadyExistsException();
	}
	
	
	@PreAuthorize("@securityMethodsService.canManage(#id, principal)")
	@PutMapping("/{id}")
	public Cell updateCell(@PathVariable Long id, @RequestBody Cell cell) throws NotFoundException {
		Optional<Cell> existingCell = cellReposotiry.findById(id);
		if (existingCell.isPresent()) {
			cell.setId(id);
			return cellReposotiry.save(cell);
		}
		
		throw new NotFoundException();
	}
	

	
	@PreAuthorize("hasAuthority('CELL_DELETE')")
	@DeleteMapping("/{id}")
	public void deleteCell(@PathVariable Long id) throws NotFoundException {
		Cell existingCell = cellReposotiry.findById(id).orElseThrow(() -> new NotFoundException());
		cellReposotiry.delete(existingCell);
	}
	

}

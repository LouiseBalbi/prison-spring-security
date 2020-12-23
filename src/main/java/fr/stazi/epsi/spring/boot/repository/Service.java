package fr.stazi.epsi.spring.boot.repository;

import java.util.List;

import fr.stazi.epsi.spring.boot.entity.Cell;

public class Service {
	
	private cellRepository cellRepo;

    public Service(cellRepository cellRepo) {
        super();
        this.cellRepo = cellRepo;
    }

    public List<Cell> findAll() {
        return cellRepo.findAll();
    }

    public Cell update(Cell entity) {

        return cellRepo.save(entity);
    }

}

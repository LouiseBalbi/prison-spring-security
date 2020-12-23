package fr.stazi.epsi.spring.boot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import fr.stazi.epsi.spring.boot.entity.Cell;
import fr.stazi.epsi.spring.boot.entity.user.User;
import fr.stazi.epsi.spring.boot.repository.UserRepository;
import fr.stazi.epsi.spring.boot.repository.cellRepository;


@Service
public class SecurityMethodsService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private cellRepository cellRepository;
	
	
	public boolean canManage (Long cellId, UserDetails connectedUser) {	
		
		User user = userRepository.findByUsername(connectedUser.getUsername()).get();
		return user.getCells().stream().anyMatch(cell -> cell.getId().equals(cellId));

	}
}

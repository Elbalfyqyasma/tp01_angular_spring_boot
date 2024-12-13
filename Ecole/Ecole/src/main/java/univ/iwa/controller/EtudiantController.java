package univ.iwa.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import univ.iwa.model.Etudiant;
import univ.iwa.service.EtudiantService;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api")
public class EtudiantController {
	@Autowired EtudiantService etudiantService;
	@GetMapping("/etudiants")
public List<Etudiant> getAllEtudiants() {
	return etudiantService.getAllEtudiants();
}
	@GetMapping("/etudiants/{id}")
public Optional<Etudiant> getEtudiantById(@PathVariable Long id) {
	return etudiantService.getEtudiantById(id);
}
	
	
	
	
	
	
	
	
	
	
	@PostMapping("/etudiants")
public Etudiant addEtudiant(@RequestParam Long id,
		@RequestParam String nom,
		@RequestParam int age,@RequestParam Long idFiliere,@RequestParam MultipartFile photo

) throws IllegalStateException, IOException {
		
	return etudiantService.addEtudiant(id,nom,age,idFiliere,photo);
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	@DeleteMapping("/etudiants/{id}")
public void deleteEtudiant(@PathVariable Long id) {
		String path="src/main/resources/static/photos/"+id+".png";
		File f=new File(path);
		if (f.exists())f.delete();
	etudiantService.deleteEtudiant(id);
}
	@PutMapping("/etudiants")
public Etudiant updateEtudiant(@RequestBody Etudiant etudiant) {
	return etudiantService.updateEtudiant(etudiant);
}
}

package univ.iwa.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import univ.iwa.model.Filiere;import univ.iwa.repository.FiliereRepository;
import univ.iwa.service.EtudiantService;
import univ.iwa.service.FiliereService;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api")
public class FiliereController {
	@Autowired FiliereService filiereService;
	@Autowired EtudiantService etudiantService;
	@GetMapping("/filieres")
	public List<Filiere> getAllFilieres() {
		return filiereService.getAllFilieres();
	}
	
	@GetMapping("/filieres/{id}/etudiants")
	public List<Etudiant> getEtudiantsByFiliere(@PathVariable Long id){
		return etudiantService.getEtudiantsByFiliereId(id);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/filieres/{id}")
public Optional<Filiere> getFiliereById(@PathVariable Long id) {
	return filiereService.getFiliereById(id);
}
	@PostMapping("/filieres")
public Filiere addFiliere(@RequestBody Filiere filiere) {
	return filiereService.addFiliere(filiere);
}
	@DeleteMapping("/filieres/{id}")
public void deleteFiliere(@PathVariable Long id) {
	filiereService.deleteFiliere(id);
}
	@PutMapping("/filieres")
public Filiere updateFiliere(@RequestBody Filiere filiere) {
	return filiereService.updateFiliere(filiere);
}
	@PostMapping("/filieres/{idFil}/etudiants")
public Etudiant addEtudiantToFiliere(
				@PathVariable Long idFil, 
				@RequestParam Long id,
				@RequestParam String nom,
				@RequestParam int age,
				@RequestParam MultipartFile photo
				) throws IllegalStateException, IOException {
		Etudiant etudiant=new Etudiant(id, nom, age, null, null);
		etudiant.setFiliere(filiereService.getFiliereById(idFil).get());
		//Add Photo file to Etudiant
		String path="src/main/resources/static/photos/"+etudiant.getId()+".png";
		photo.transferTo(Path.of(path));
		String photoUrl="http://localhost:8080/api/photos/"+etudiant.getId();
		etudiant.setPhoto(photoUrl);
		return etudiantService.addEtudiant(id, nom,age,idFil,photo);
	}
	

	@GetMapping("/photos/{id}")
	public ResponseEntity<Resource> getImage(@PathVariable String id){
	String path="src/main/resources/static/photos/"+id+".png";
	FileSystemResource file=new FileSystemResource(path);
	if (!file.exists()) {
	return ResponseEntity.notFound().build();
	}
	return ResponseEntity.ok()
	.contentType(MediaType.IMAGE_PNG)
	.body(file);
	}

}

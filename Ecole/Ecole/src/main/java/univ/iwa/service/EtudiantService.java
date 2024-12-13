package univ.iwa.service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import univ.iwa.model.Etudiant;
import univ.iwa.model.Filiere;
import univ.iwa.repository.EtudiantRepository;
import univ.iwa.repository.FiliereRepository;
@Service
public class EtudiantService {
	@Autowired EtudiantRepository etudiantRepository;
	@Autowired  FiliereRepository filiereRepository;
public List<Etudiant> getAllEtudiants() {
	return etudiantRepository.findAll();
}

public Optional<Etudiant> getEtudiantById(Long id) {
	return etudiantRepository.findById(id);
}











public Etudiant addEtudiant(long id , String nom, int age,Long idFiliere , MultipartFile photo) throws IllegalStateException, IOException {
	String path="src/main/resources/static/photos/"+id+".png";
	photo.transferTo(Path.of(path));
	String url="http://localhost:8080/photos/"+id;	
	Filiere f  = filiereRepository.findById(idFiliere).get();
	Etudiant etudiant=new Etudiant(id, nom, age, url,f);
	
	return etudiantRepository.save(etudiant);
}













public List<Etudiant> getEtudiantsByFiliereId(Long id) {
	return etudiantRepository.findByFiliereId(id);
}












public void deleteEtudiant(Long id) {
	etudiantRepository.deleteById(id);
}

public Etudiant updateEtudiant(Etudiant etudiant) {
	return etudiantRepository.save(etudiant);
}


}

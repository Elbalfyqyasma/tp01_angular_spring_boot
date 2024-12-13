package univ.iwa.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourcesController {
	@GetMapping("/photos/{id}")
	public ResponseEntity<Resource> getImage(@PathVariable String id){
	String path="src/main/resources/static/photos/"+id+".png";
	//c'est une classe qui permet de manupiler les fichier stocker ds system
	FileSystemResource file=new FileSystemResource(path);
	if (!file.exists()) {
	//ResponseEntity est une classe de Spring utilisée pour créer des réponses HTTP personnalisées.
	//Avec ResponseEntity, vous pouvez renvoyer des statuts HTTP comme 200 OK, 404 Not Found, 500 Internal Server Error, etc.,
	return ResponseEntity.notFound().build();
	}
	return ResponseEntity.ok()  // Crée une réponse HTTP avec le statut 200 OK (succès).
		    .contentType(MediaType.IMAGE_PNG)  // Définit le type de contenu de la réponse comme étant une image PNG (image/png).
		    .body(file);  // Ajoute l'image, représentée par file, dans le corps de la réponse.

	}

}

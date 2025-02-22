import { HttpClient } from '@angular/common/http';
import { Injectable, signal } from '@angular/core';
import { Filiere } from './etudiant/filiere.model';
import { Etudiant } from './etudiant/etudiant.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FiliereService {
  backEndURL="http://localhost:8080/api/filieres"
  filieres=signal<Filiere[]>([])
  constructor(private http:HttpClient) { 
    this.getAllFiliere()
  }

  getAllFiliere():void{
    this.http.get<Filiere[]>(this.backEndURL).subscribe(data=>
      {this.filieres.set(data)}) 
  }

  getEtudiantsByFiliere(idFiliere: number): Observable<Etudiant[]> {
       console.log(idFiliere)

    return this.http.get<Etudiant[]>(`${this.backEndURL}/${idFiliere}/etudiants`);
   
  }




}

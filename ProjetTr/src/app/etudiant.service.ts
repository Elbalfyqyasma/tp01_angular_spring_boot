import { HttpClient } from '@angular/common/http';
import { Injectable, signal } from '@angular/core';
import { Etudiant } from './etudiant/etudiant.model';

@Injectable({
  providedIn: 'root'
})
export class EtudiantService {
  backEndURL="http://localhost:8080/api/etudiants"
  etudiants=signal<Etudiant[]>([])
  constructor(private http:HttpClient ) { 
    this.getAllEtudiants()
  }
  getAllEtudiants():void{
    // return this.etudiants;  
    this.http.get<Etudiant[]>(this.backEndURL).subscribe(data=>
      {
        this.etudiants.set(data)
      
      }
      )
  
  }

  addEtudiant(etudiant:any,photo:File){
    console.log(etudiant)
    // this.etudiants.push(etudiant)
    // this.http.post<Etudiant>(this.backEndURL,etudiant).subscribe(newEtudiant=>{
    //   this.etudiants.update(state=>[...state,newEtudiant])
    // })

    const formData=new FormData()
    formData.append('id',etudiant.id)
    formData.append('nom',etudiant.nom)
    formData.append('age',etudiant.age)
    formData.append('idFiliere',etudiant.filiere)
    formData.append('photo',photo)
    this.http.post<Etudiant>(this.backEndURL,formData).subscribe(newEtudiant=>{
      this.etudiants.update(state=>[...state,newEtudiant])
    })

  }

  updateEtudiant(etudiant: Etudiant) {
    // const index=this.etudiants.findIndex(currentEtudiant=>(currentEtudiant.id==etudiant.id))
    // this.etudiants[index]=etudiant
    // this.http.put<Etudiant>(this.backEndURL,etudiant).subscribe(updatedEtudiant=>{
    //   this.etudiants.update(state=>state.map(e=>(e.id===etudiant.id)?updatedEtudiant:e))
    // })

    this.http.put<Etudiant>(this.backEndURL,etudiant).subscribe(updatedEtudiant=>{
      this.etudiants.update(state=>state.map(e=>(e.id===etudiant.id)?updatedEtudiant:e))
    })
  }
  deleteEtudiant(id:number){
    // const index=this.etudiants.findIndex(etudiant=>(etudiant.id==id))
    // console.log(index)
    // this.etudiants.splice(index,1)
    this.http.delete<boolean>(this.backEndURL+"/"+id).subscribe(retour=>{
      if(retour){  
        this.etudiants.update(state => state.filter(e => e.id !== id));
      }
    })
  
  }

  
}

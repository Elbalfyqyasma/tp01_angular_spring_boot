import { Component, OnInit, inject } from '@angular/core';
import { EtudiantService } from '../../etudiant.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FormComponent } from '../form/form.component';
import { DeletemodalComponent } from './deletemodal/deletemodal.component';
import { FiliereService } from '../../filiere.service';

@Component({
  selector: 'app-list',
  standalone: true,
  imports: [],
  templateUrl: './list.component.html',
  styleUrl: './list.component.css'
})
export class ListComponent  {
  private etudiantService=inject(EtudiantService)
  
  private filiereService=inject(FiliereService)
  filieres = this.filiereService.filieres
  etudiants=this.etudiantService.etudiants
  
  constructor(private modal:NgbModal){ 
    console.log(this.etudiants)
  }


  openModal(){
    this.modal.open(FormComponent)
  }

  deleteEtudiant(id:number){
    
    this.etudiantService.deleteEtudiant(id)
  }

  updateEtudiant(etudiant:any){
    const ref=this.modal.open(FormComponent)
    ref.componentInstance.etudiantData=etudiant
    ref.componentInstance.action="Modifier"
  }
  
  openDeleteModal(etudiant:any){
    const ref = this.modal.open(DeletemodalComponent)
    ref.componentInstance.action="delete"
    ref.componentInstance.etudiantData=etudiant
    ref.result.then(result=>{
      if(result =='oui'){
        this.deleteEtudiant(etudiant.id)
      }
    })
  }


  onSelectFiliere(event:any){
    const idFiliere = event.target.value;
    
    this.filiereService.getEtudiantsByFiliere(idFiliere).subscribe(data=>
      this.etudiants.set(data)
    )
    
  }

}

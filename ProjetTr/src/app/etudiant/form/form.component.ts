import { Component, OnInit, inject } from '@angular/core';
import {  FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { EtudiantService } from '../../etudiant.service';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { DeletemodalComponent } from '../list/deletemodal/deletemodal.component';
import { FiliereService } from '../../filiere.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-form',
  standalone: true,
  imports: [ReactiveFormsModule,CommonModule],
  templateUrl: './form.component.html',
  styleUrl: './form.component.css'
})
export class FormComponent implements OnInit {
  action:string="Ajouter"
  etudiantData:any
  private filiereService=inject(FiliereService)
  filieres = this.filiereService.filieres
  private fb = inject(FormBuilder)
  photo!:File
  formEtudiant =this.fb.group({
    id:['',Validators.required],
    nom:['',Validators.required],
    age:['',Validators.required],
    filiere:['',Validators.required],
  })
  
  constructor(private etudiantService:EtudiantService,public activeModal:NgbActiveModal){}
 
  addEtudiant(){
    this.etudiantService.addEtudiant(this.formEtudiant.value,this.photo)
  }
  closeModal(){
    this.activeModal.close()
  }

  updateEtudiant(etudiant:any){
    this.etudiantService.updateEtudiant(etudiant)
  }

  ngOnInit() {
    if (this.action=="Modifier")
    this.formEtudiant.setValue(this.etudiantData)
  }

  actionEtudiant(){
    if (this.action=="Ajouter"){
      this.addEtudiant()
    }
    else{
      this.updateEtudiant(this.formEtudiant.value)
    }
    this.activeModal.close()
  }

  fileSelected(event:any){
    const file=event.target.files[0]
    if (file)this.photo=file
  }
  





}

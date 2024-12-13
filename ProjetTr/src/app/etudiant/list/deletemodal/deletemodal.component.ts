import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-deletemodal',
  standalone: true,
  imports: [],
  templateUrl: './deletemodal.component.html',
  styleUrl: './deletemodal.component.css'
})
export class DeletemodalComponent {
  etudiantData:any
  constructor(public activeModal:NgbActiveModal){}
  
  deleteEtudiant(id:number){

  }
}

import { Filiere } from "./filiere.model"

export interface Etudiant{
    id:number
    nom:string
    age:number
    photo:string
    filiere: Filiere
  }
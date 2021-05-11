import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { environment } from 'src/environments/environment.prod';
import { Tema } from '../model/Tema';
import { TemaService } from '../service/tema.service';

@Component({
  selector: 'app-tema',
  templateUrl: './tema.component.html',
  styleUrls: ['./tema.component.css']
})
export class TemaComponent implements OnInit {

  tema: Tema = new Tema()
  listaTemas: Tema[]

  constructor(
    private router: Router,
    private temaService: TemaService,

    private route: ActivatedRoute

    ) {

    }

  ngOnInit() {
    if(environment.token == ''){
      //alert('Sua sessão expirou, faça login novamente')
      this.router.navigate(['/login'])
    }

    this.findAllTemas()
  }

  findAllTemas(){
    this.temaService.getAllTema().subscribe((resp: Tema[]) => {
      this.listaTemas = resp
    })

  }

  // findByIdTema(id: number){
  //   this.temaService.getByIdTema(id).subscribe((resp: Tema) => {
  //     this.tema = resp
  //   })
  // }

  cadastrar(){
    this.temaService.postTema(this.tema).subscribe((resp: Tema) => {
      alert('Tema cadastrado com sucesso!')
      this.findAllTemas()
    })
  }

  apagar(){
    this.temaService.deleteTema(this.tema.id).subscribe(() => {
      alert("Tema apagado com sucesso!")
      this.findAllTemas()
    })
  }

  atualizar(){
    this.temaService.putTema(this.tema).subscribe((resp: Tema) =>{
      alert("Tema atualizado com sucesso!")
      this.findAllTemas()
    })
  }

}

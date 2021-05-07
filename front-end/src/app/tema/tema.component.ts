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

  idTema: number

  constructor(
    private router: Router,
    private temaService: TemaService,

    private route: ActivatedRoute

    ) { }

  ngOnInit() {
    if(environment.token == ''){
      //alert('Sua sessão expirou, faça login novamente')
      this.router.navigate(['/login'])
    }

      this.findAllTemas()

      this.idTema = this.route.snapshot.params['id']
      this.findByIdTema(this.idTema)

      let id = this.route.snapshot.params["id"]
      this.findByIdTema(id)

  }

  findAllTemas(){

    this.temaService.getAllTema().subscribe((resp: Tema[]) => {
      this.listaTemas = resp

    })

  }

  findByIdTema(id: number){
    this.temaService.getByIdTema(id).subscribe((resp: Tema) =>{
      this.tema = resp
    })
  }

  cadastrar(){
    this.temaService.postTema(this.tema).subscribe((resp: Tema) => {
      this.tema = resp
      alert('Tema cadastrado com sucesso!')
      this.findAllTemas()
      this.tema = new Tema()
    })
  }

  apagar(){
    this.temaService.deleteTema(this.idTema).subscribe(() => {
      alert("Tema apagado com sucesso!")
      this.router.navigate(["/tema"])
    })
  }

  atualizar(){
    this.temaService.putTema(this.tema).subscribe((resp: Tema) =>{
      this.tema = resp
      alert("Tema atualizado com sucesso!")
      this.router.navigate(["/tema"])
    })
  }

}

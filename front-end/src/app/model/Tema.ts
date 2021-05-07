import { Postagem } from "./Postagem"

export class Tema{
  public id: number
  public descricao: string
  public postagem: Postagem[]   //array de postagens, varias postagem pra um tema/ many to one
}

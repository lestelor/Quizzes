package uoc.quizzes

class Questions {
    var id: Int = 0
    var Titulo: Int? = null
    var Imagen: String? = null
    var Respuestaok: Int? = null
    var Respuesta1: String? = null
    var Respuesta2: String? = null

    constructor(id: Int, Titulo: Int, Imagen:String, Respuestaok:Int, Respuesta1:String, Respuesta2:String) {
        this.id = id
        this.Titulo = Titulo
        this.Imagen = Imagen
        this.Respuestaok = Respuestaok
        this.Respuesta1=Respuesta1
        this.Respuesta2=Respuesta2
    }
    constructor(Titulo: Int, Imagen:String, Respuestaok:Int, Respuesta1:String, Respuesta2:String) {
        this.Titulo = Titulo
        this.Imagen = Imagen
        this.Respuestaok = Respuestaok
        this.Respuesta1=Respuesta1
        this.Respuesta2=Respuesta2
    }
    constructor() {
    }
}
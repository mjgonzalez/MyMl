package myml



class PreguntasPendientesWebTests extends grails.util.WebTest {
    void testExistePaginaPreguntasPendientes() {
	   irAPreguntasPendientes()
	   validarTitulo("Preguntas pendientes")
    }

	void testActualizarListadoCuandoRespondo() {
		irAPreguntasPendientes()
        def preguntasPendientes = obtenerPreguntasPendientes()
        preguntasPendientes.each {
			chequearExistePregunta(it)
		}
		setInputField value:"respuesta", htmlId:"pregunta1"
		clickButton htmlId:"boton1"
        //TODO: chequear que el texto de la pregunta respondida no esté
    }
	
	void irAPreguntasPendientes() {
		 invoke '/preguntaspendientes'
	}
	
	void validarTitulo(titulo) {
		verifyElementText type:"h1", text:titulo
	}
	
	void chequearExistePregunta(pregunta) {
		verifyText text:pregunta.text
	}
}
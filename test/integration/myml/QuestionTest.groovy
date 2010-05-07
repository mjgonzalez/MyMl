package myml

import java.util.Date;

import grails.test.GrailsUnitTestCase

class QuestionTest extends GrailsUnitTestCase{
	void testMaxAnswerText(){
		Customer r = new Customer(nickname:"BB")
		Customer s = new Customer(nickname:"BB")
		
		Item i = new Item(title:"Item de testeo para preguntar", siteId:"MLA", seller:s, status:"A", 
				vipURL:"http://www.mercadolibre.com.ar/jm/item?site=MLA&id=84213167",
				mainImageURL:"http://img1.mlstatic.com/jm/img?s=MLA&f=84213167_3249.jpg&v=I", currentPrice:75,
	    		quantitySelled:5, offersReceived:1,quantityAvailable:2)
		
		def q1 = new Question(questionText:"hola?", sender:s, receiver:r, item:i)
		def q2 = new Question(questionText:getLargeQuestionText(), sender:s, receiver:r, item:i)

		assertTrue "pregunta ok", q1.validate()
		assertFalse "pregunta con texto largo", q2.validate()
	}
	
	void testAnswer(){
		Customer r = new Customer(nickname:"AA")
		r.save()
		Customer s = new Customer(nickname:"BB")
		s.save()
		
		Item i = new Item(title:"Item de testeo para preguntar", siteId:"MLA", seller:s, status:"A", 
				vipURL:"http://www.mercadolibre.com.ar/jm/item?site=MLA&id=84213167",
				mainImageURL:"http://img1.mlstatic.com/jm/img?s=MLA&f=84213167_3249.jpg&v=I", currentPrice:75,
	    		quantitySelled:5, offersReceived:1,quantityAvailable:2)
		
		i.save()
		
		Question q1 = new Question(questionText:"hola?", sender:s, receiver:r, item:i)
		
		q1.save()
		
		q1.answer "chau"
		
		assertProcessed(q1)
		
		def answer = Answer.findByQuestion(q1)
		assertNotNull "se creo la respuesta", answer
		assertEquals "se creo la respuesta correcta", "chau", answer.answerText
		
		def questionAction = QuestionAction.findByQuestion(q1)
		assertNotNull "se creo la accion", questionAction
		assertEquals "se creo la accion correcta", "ANSWER", questionAction.actionType	
	}
	
	void testDelete(){
		Customer r = new Customer(nickname:"AA")
		r.save()
		Customer s = new Customer(nickname:"BB")
		s.save()
		
		Item i = new Item(title:"Item de testeo para preguntar", siteId:"MLA", seller:s, status:"A", 
				vipURL:"http://www.mercadolibre.com.ar/jm/item?site=MLA&id=84213167",
				mainImageURL:"http://img1.mlstatic.com/jm/img?s=MLA&f=84213167_3249.jpg&v=I", currentPrice:75,
	    		quantitySelled:5, offersReceived:1,quantityAvailable:2)
		
		i.save()
		
		Question q1 = new Question(questionText:"hola?", sender:s, receiver:r, item:i)
		
		q1.save()
		
		q1.markAsDelete()
		
		assertProcessed(q1)
		
		def questionAction = QuestionAction.findByQuestion(q1)
		assertNotNull "se creo la accion", questionAction
		assertEquals "se creo la accion correcta", "DELETE", questionAction.actionType
	}

	void assertProcessed(q){
		assertTrue "se marco como procesada", q.processed
	}
	
	def getLargeQuestionText = {
		StringBuffer sb = new StringBuffer()
		for(int i = 0; i<3000; i++)
			sb.append("0123456789")
		return sb.toString()
	}
}

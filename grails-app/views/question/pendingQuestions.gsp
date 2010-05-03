<head>
	<title>Preguntas</title>
	<meta name="layout" content="main"></meta>
</head>

<body>
	<h1>${message(code:'questions.page.title')}</h1>
	<!--Long id
	String questionText
	String answerText
	Long itemId
	Long askerId
	Long sellerId-->
	
	<g:hasErrors>
	   	<div class="errors">
	       <g:renderErrors bean="${answer}" as="list" />
	   	</div>
	</g:hasErrors>
	
	
	<g:if test="${questions}">
		<g:each var="q" in="${questions}">
			<div id="div${q.id}">
				${q.questionText}
				<g:form name="questionAnswerForm${q.id}" url="[controller:'question']">
					<g:hiddenField name = "questionId" value="${q.id}"/>
					<g:textArea name="answerText" rows="5" cols="40" value=${answer?.answerText}/>
					<g:submitToRemote value="${message(code:'questions.answer')}" action="answerQuestion" update="messages" onSuccess="deleteQuestionDiv(${q.id})"/>
					<g:submitToRemote value="${message(code:'questions.delete')}" action="deleteQuestion" update="messages" onSuccess="deleteQuestionDiv(${q.id})"/>
				</g:form>
			</div>
		</g:each>
	</g:if>
	<g:else>
		<g:message code="questions.notPending"/>
	</g:else>

	<script>
		function deleteQuestionDiv(id){
			$("div"+id).style.visible = false;
			$("div"+id).style.display = 'none';
		}
	</script>
</body>
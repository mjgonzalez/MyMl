<head>
	<title>Preguntas</title>
	<meta name="layout" content="main"></meta>
</head>

<body>
	<!--Long id
	String questionText
	String answerText
	Long itemId
	Long askerId
	Long sellerId-->
	<g:if test="${items}">
		<g:each var="item" in="${items}">
			${item.title}
			<g:each var="q" in="${item.pendingQuestions}">
				<div id="div${q.id}">
					${q.sender.nickname}: ${q.questionText}
					<g:form name="questionAnswerForm${q.id}" url="[controller:'question']">
						<g:hiddenField name = "questionId" value="${q.id}"/>
						<g:textArea name="responseText" rows="5" cols="40"/>
						<g:submitToRemote value="${message(code:'questions.answer')}" action="answerQuestion" update="messages" onSuccess="deleteQuestionDiv(${q.id})"/>
						<g:submitToRemote value="${message(code:'questions.delete')}" action="deleteQuestion" update="messages" onSuccess="deleteQuestionDiv(${q.id})"/>
					</g:form>
				</div>
			</g:each>
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
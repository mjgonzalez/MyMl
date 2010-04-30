<head>
	<title>${message(code:'questions.pendingQuestions.title')}</title>
	<meta name="layout" content="main"></meta>
</head>

<body>
	<!--Long id
	String questionText
	String answerText
	Long itemId
	Long askerId
	Long sellerId-->
	<g:if test="${questions}">
		<g:each var="q" in="${questions}">
			<div id="div${q.id}">
				${q.questionText}
				<g:form name="questionAnswerForm${q.id}" url="[controller:'question']">
					<g:hiddenField name = "questionId" value="${q.id}"/>
					<g:textArea name="responseText" rows="5" cols="40"/>
					<g:submitToRemote name="btnAnswer${q.id}" value="${message(code:'questions.answer')}" action="answerQuestion" update="messages" onSuccess="deleteQuestionDiv('div${q.id}')"/>
					<g:submitToRemote name="btnDelete${q.id}" value="${message(code:'questions.delete')}" action="deleteQuestion" update="messages" onSuccess="deleteQuestionDiv('div${q.id}')"/>
				</g:form>
			</div>
		</g:each>
	</g:if>
	<g:else>
		<g:message code="questions.notPending"/>
	</g:else>

	<script>
		function deleteQuestionDiv(id){
			var o = document.getElementById(id);
			o.parentNode.removeChild(o);
		}
	</script>
</body>
<head>
	<title>${message(code:'questions.pendingQuestions.title')}</title>
	<meta name="layout" content="main"></meta>
	<jqui:resources/>
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
			<g:if test="${item.pendingQuestions}">
				${item.title}
				<g:each var="q" in="${item.pendingQuestions}">
					<div id="div${q.id}">
						${q.sender.nickname}: <div id="qText${q.id}">${q.questionText}</div>
						<g:form name="questionAnswerForm${q.id}" url="[controller:'question']">
							<g:hiddenField name = "questionId" value="${q.id}"/>
							<div>
								<g:textArea name="responseText" rows="5" cols="40"/>
							</div>
							<g:submitToRemote name="btnAnswer${q.id}" value="${message(code:'questions.answer')}" action="answerQuestion" update="messages" onSuccess="deleteQuestionDiv('div${q.id}')"/>
							<g:submitToRemote name="btnDelete${q.id}" value="${message(code:'questions.delete')}" action="deleteQuestion" update="messages" onSuccess="deleteQuestionDiv('div${q.id}')"/>
							<input type="button" id="btnBlock${q.id}" value="Bloquear" rel="${q.id}"/>
						</g:form>
					</div>
				</g:each>
			</g:if>
		</g:each>
	</g:if>
	<g:else>
		<g:message code="questions.notPending"/>
	</g:else>
	
	<div id="blockUsrDiv" style="display:none;">
		<g:form name="blockForm" url="[controller:'itemPendingQuestions']">
		<ul style="list-style:none;">
		<input type="hidden" value="" name="idPregunta" />
		<li><input name ="preguntar" id="preguntar" type="checkbox" /><label for="preguntar">Para preguntar</label></li>
		<li><input name ="ofertar" id="ofertar" type="checkbox" /><label for="ofertar">Para ofertar</label></li>
		<li><g:submitToRemote name="btnAceptar" value="Aceptar" action="blockUser" onSuccess="bloquearUsuario();"/>
        <input type="button" id="btnClose" value="Cancelar"/></li>
		</ul>
		</g:form>
	</div>
	
	<g:javascript src="pendingQuestions.js"/>
</body>
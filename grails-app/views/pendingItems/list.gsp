<html>
<head>
	<title>${message(code:'questions.pendingQuestions.title')}</title>
	<meta name="layout" content="myml"></meta>
</head>
<body>

	<g:if test="${sellerItems}">
			<table>
				<tr>
					<td>${message(code:'items.picture')}</td>
					<td>${message(code:'items.title')}</td>
					<td>${message(code:'items.price')}</td>
					<td>${message(code:'items.qtySell')}</td>
				</tr>
	    ${message(code:'items.sellerItems')}</br>
		<g:each var="item" in="${sellerItems}">
		<tr>
			<td><img src="${item.mainImageURL}"/></td>
			<td><a href="${item.vipURL}">(#${item.id}) ${item.title}</a></td>
			<td>$<span id="currentPrice${item.id}">${item.currentPrice}</span</td>
			<td><span id="quantityAvailable${item.id}">${item.quantityAvailable}</span></td>
		</tr>
		</g:each>
		</table>
	</g:if>
	<g:else>
		<g:message code="items.notPending"/>
	</g:else>


</body>
</html>
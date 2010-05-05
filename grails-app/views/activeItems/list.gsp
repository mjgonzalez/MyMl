<head>
	<title>${message(code:'items.activeItems.pageTitle')}</title>
	<meta name="layout" content="main"></meta>
</head>

<body>
	<g:if test="${sellerItems}">
		<g:each var="i" in="${sellerItems}">
			<div id="div${i.id}">
				<img src="${i.mainImageURL}"/>
				<a href="${i.vipURL}">${i.title}(${i.id})</a>
				<div id="currentPrice${i.id}">${i.currentPrice}</div>
				<div id="quantitySelled${i.id}">${i.quantitySelled}</div>
				<div id="offersReceived${i.id}">${i.offersReceived}</div>
				<div id="quantityAvailable${i.id}">${i.quantityAvailable}</div>	
			</div>
			<div>
				<table>
					<tbody>
						<tr>
							<td height="20">
								<a href="" class="hpepig">Modificar!</a>
							</td>
							<td height="20">
								<a href="" class="hpepig">Publicar ítem similar</a>
							</td>
							<td height="20">
								<a href="" class="hpepig">¡Cerrar Ya!</a>
							</td>
							<td height="20">
								<a href="" class="hpepig">Pausar venta</a>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</g:each>
	</g:if>
	<g:else>
		<g:message code="items.activeItems.empty"/>
	</g:else>
</body>
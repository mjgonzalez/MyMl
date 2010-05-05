<head>
	<title>${message(code:'items.activeItems.pageTitle')}</title>
	<meta name="layout" content="main"></meta>
</head>

<body>
	<g:if test="${sellerItems}">
		<table>
				<tr>
					<td>Foto</td>
					<td>Título</td>
					<td>Precio</td>
					<td>Cant. Vendida</td>
					<td>Ofertas</td>
					<td>Disponible</td>
				</tr>
		<g:each var="i" in="${sellerItems}">
			<tr>
				<td>	
					<img src="${i.mainImageURL}"/>
				</td>
				<td>
					<a href="${i.vipURL}">${i.title}(${i.id})</a>
				</td>
				<td id="currentPrice${i.id}">${i.currentPrice}</td>
				<td id="quantitySelled${i.id}">${i.quantitySelled}</td>
				<td id="offersReceived${i.id}">${i.offersReceived}</td>
				<td id="quantityAvailable${i.id}">${i.quantityAvailable}</td>
				<td>	
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
				</td>
			</tr>
		</g:each>
		</table>
	</g:if>
	<g:else>
		<g:message code="items.activeItems.empty"/>
	</g:else>
</body>
<html>
    <head>
        <title><g:layoutTitle default="${message(code:'layout.menu.myml')}" /></title>
        <link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
        <link rel="stylesheet" href="${resource(dir:'css',file:'myml.css')}" />
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <g:layoutHead />

    </head>
    <body>

		<g:render template="/common/topbar" />
        
        <g:layoutBody />
        
        <div id="footer">
        	<g:render template="/common/footer" />
       	</div>
    </body>
</html>
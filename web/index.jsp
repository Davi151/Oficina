<%@page import="Pojo.UsuarioPojo"%>
<%@page import="Dao.UsuarioDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
    HttpSession httpSession = request.getSession(true);            
    session.invalidate();       
%>
<html lang="pt-br">
	<head>
		<meta charset="utf-8">
		<title> LOGIN </title>
                <link href="css/login.css" rel="stylesheet" type="text/css">
	</head>

	<body>
                        
                        
                            <h1 ALIGN="center" id="logo"> HALTECH MOTOS </h1>
			
			<form class="box" action="LoginServlet" method="post">
			    	
				<input type="text" id="login" name="login" placeholder="Login" required/>
				<input type="password" id="senha" name="senha" placeholder="Senha" required/>
				<input type="submit" value="Entrar" />
			    	
			</form>
                             
                            <footer class="page-footer font-small blue">

                                <!-- Copyright -->
                                  <div class="footer-copyright footer py-3">
                                  © 2019 Copyright:
                                  <a href="https://github.com/Davi151/Oficina">https://github.com/Davi151/Oficina</a>
                                </div>
                                <!-- Copyright -->

                              </footer>

	</body>

</html>
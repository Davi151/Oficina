<%@page import="java.util.ArrayList"%>
<%@page import="Pojo.FuncionarioPojo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="css/funcionario.css" rel="stylesheet" type="text/css">
        <title>Tabela Funcionário</title>
    </head>
    <body>            
        <%           
            String usuario = (String) session.getAttribute("usuario");
            ArrayList<FuncionarioPojo> listFuncionarioPojo = (ArrayList<FuncionarioPojo>) request.getAttribute("listFuncionarioPojo");                                                                        
        %>
        <nav class="navbar navbar-expand-md navbar-dark bg-dark mb-4">
        <a class="navbar-brand" href="home.jsp">
            <img src="./img/logo.png" width="30" height="30" alt="logo oficina">
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
          <ul class="navbar-nav mr-auto">

            <li class="nav-item active">
              <a class="nav-link" href="home.jsp">Home</a>
            </li>

            <li class="nav-item active dropdown">
            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-toggle="dropdown" arial-labelledby="navbarDropdown">Listar</a>                  
            <div class="dropdown-menu" arial-labelledby="navbarDropdown">
                <a class="dropdown-item" href="PecaServlet?id=listar">Peças</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="FuncionarioServlet?id=listar">Funcionários</a>                
            </div>
          </li>
          
           <li class="nav-item active dropdown">
            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-toggle="dropdown" arial-labelledby="navbarDropdown">Cadastrar</a>                            
            <div class="dropdown-menu" arial-labelledby="navbarDropdown">
                <a class="dropdown-item" href="cadastroPeca.jsp">Peça</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="cadastroFuncionario.jsp">Funcionário</a>   
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="cadastroUsuario.jsp">Usuário</a>
            </div>
           </li> 
           
           <li class="nav-item active">
                <a class="nav-link " href="auditoria.jsp">Auditoria</a>
              </li>
           
            <li class="nav-item active">
                <a class="nav-link" href="Entrada.jsp">Entrada</a>
              </li>
           <li class="nav-item active">
                <a class="nav-link" href="Saida.jsp">Saída</a>
              </li>
              
             <li>   
               <div class="container">
                    <button type="submit" class="btn btn-link navbar-btn navbar-danger" data-toggle="modal" data-target="#myModal">Logout</button>
                    <div class="modal fade" id="myModal" role="dialog">
                        <div class="modal-dialog modal-sm">
                          <div class="modal-content">
                            <div class="modal-header">
                              <button type="button" class="close" data-dismiss="modal">×</button>
                              <!--h4>Logout</h4-->
                            </div>
                            <div class="modal-body">
                              <p align="center">Deseja realmente deslogar?</p>
                            </div>
                            <div class="modal-footer" align="center">
                                <form action="LogoutServlet" method="post">
                                    <button type="submit" class="btn btn-success" data-dismiss="">Sim</button>
                               </form>
                                <button type="button" class="btn btn-danger" data-dismiss="modal">Não</button>                                
                            </div>
                          </div>
                        </div>
                    </div> 
                </div> 
            </li>                        
  
            <li>                                    
                <a class="nav-link disabled" href="#">Usuário: <% out.print(usuario);%> </a>
            </li>
            
          </ul>                  
        </div>
      </nav>        
            
    <h3 ALIGN="center">Tabela de Funcionários</h3>    
    <div class="container scrollable">
        <div class="table-responsive">
            <%
            if(usuario == null){                
                response.sendRedirect("index.html");
            }else{                                                              
            %>
            <table class="table table-bordered scrollable table-hover table-sm bg-light text-center table-striepd">
                <thead>
                    
                  <tr>
                    <th scope="col">Identificador</th>
                    <th scope="col">Nome</th>
                    <th scope="col">CPF</th>  
                    <th scope="col">Ações</th>  
                  </tr>
                </thead>

                <tbody>
                <% for (int i = 0; i < listFuncionarioPojo.size(); i++) { %>
                    <tr>          
                     <% FuncionarioPojo fc = listFuncionarioPojo.get(i); %>
                     <td><%out.print(fc.getF_ID());%></td>
                      <td><%out.print(fc.getF_NOME());%></td>
                      <td><%out.print(fc.getF_CPF());%></td>     
                      <td>
                          <style>
                              .mesmo-tamanho{
                                  width: 100%;                           
                              }                                                           
                          </style>
                          <!--Botão editar-->
                          <div class="row" ALIGN="center">                          
                              
                            <div class="col">
                              <button type="button" class="btn btn-xs btn-primary mesmo-tamanho" data-toggle="modal" data-target="#exampleModal" data-whatever="<% out.print(fc.getF_ID()); %>" data-whatevernome="<% out.print(fc.getF_NOME()); %>" data-whatevercpf="<% out.print(fc.getF_CPF()); %>">
                                Editar
                              </button>
                            </div>
                          <!--Botão editar-->    
                          
                           <div class="col">                               
                               <button type="submit" class="btn btn-xs btn-danger mesmo-tamanho" data-toggle="modal" data-target="#myModalExcluir<% out.print(fc.getF_ID()); %>">Excluir</button>                                    
                                
                               <div class="modal fade" id="myModalExcluir<%out.print(fc.getF_ID()); %>" role="dialog">
                                   <div class="modal-dialog modal-sm">
                                       <div class="modal-content">
                                           <div class="modal-body">
                                               <p align="left">Deseja Realmente Excluir o Funcionário?</p>
                                               <p align="left">Identificador do Funcionário: <% out.print(fc.getF_ID()); %>.</p>
                                               <p align="left">CPF do Funcionário: <% out.print(fc.getF_CPF()); %>.</p>                                               
                                           </div>
                                           
                                           <div class="modal-footer" align="center">
                                                <form action="FuncionarioServlet?id=excluir" method="post">
                                                    <input type="hidden" id="identificador" value="<% out.print(fc.getF_ID()); %>" name="identificador">
                                                     <button type="submit" class="btn btn-success" >Sim</button>
                                                </form>  
                                                    <button type="button" class="btn btn-danger" data-dismiss="modal">Não</button>                                
                                           </div>
                                       </div> 
                                   </div>
                               </div>                                                                                                        
                            </div> 
                                                     
                          </div>
                      </td>        
                      
                    </tr>        
                  <%} %>
                </tbody>
          </table>
          <% } %>
        </div>
    </div>
      
    
    <div class="modal fade" id="exampleModal"  role="dialog" aria-labelledby="exampleModalLabel">
      <div class="modal-dialog" role="document">
            <div class="modal-content">
              
              <div class="modal-header">                    
                    <h4 class="modal-title" id="exampleModalLabel">Editar Funcionário</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
              </div>
                
              <div class="modal-body">                      
                  <form method="get" action="">
                      
                  <div class="form-group">
                            <label for="recipient-name" class="control-label">Nome:</label>
                            <input name="recipient-name" type="text" class="form-control" id="recipient-name">
                      </div>
                        
                      <div class="form-group">
                            <label for="cpf" class="control-label">CPF:</label>
                            <input name="cpf" class="form-control" id="cpf">
                      </div>                                              
                    
                    <input name="id" type="hidden" class="form-control" id="idnf" value="editar">
                    <input name="id-funcionario" type="hidden" class="form-control" id="id-funcionario">
                                                          
                     <button class="btn btn-success">Salvar</button>                     
                     <button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>                                        
                    </form>
              </div>

            </div>
      </div>
    </div>               
    
        
        
    <!-- Footer -->
    <footer class="page-footer font-small blue">

      <!-- Copyright -->
        <div class="footer-copyright footer py-3">
        © 2019 Copyright:
        <a href="https://github.com/Davi151/Oficina">https://github.com/Davi151/Oficina</a>
      </div>
      <!-- Copyright -->

    </footer>
    <!-- Footer -->
    
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <!-- src="./jquery/jquery-3.4.1.min.js"></-->
    <script src="./jquery/jquery.freezeheader.js"></script>
    <script>
       	$('#exampleModal').on('show.bs.modal', function (event) {
		  var button = $(event.relatedTarget) // Button that triggered the modal
		  var recipient = button.data('whatever') // Extract info from data-* attributes
		  var recipientnome = button.data('whatevernome')
		  var recipientcpf = button.data('whatevercpf')
                  
		  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
		  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
		  var modal = $(this)
		  
                  //modal.find('.modal-title').text('ID ' + recipient)
		  modal.find('#id-funcionario').val(recipient)
		  modal.find('#recipient-name').val(recipientnome)
		  modal.find('#cpf').val(recipientcpf)
                  
		})

    </script>
    </body>
</html>

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function limparCampos(){
    document.getElementById('login').value='';
    document.getElementById('senha').value='';
}

function valida() {  
  if( (document.getElementById('login').value !== "") &&
       (document.getElementById('senha').value !== "") &&
       (document.getElementById('funcionarios').value !== "Funcionários")) {
        swal({ 
          title: "Usuário cadastrado com êxito!!",
          text: "Cadastro efetuado com sucesso!",
          icon: "success",          
          closeModal: true,          
          buttons: false,
          timer: 30000
      });
      return true;
  
  } else {          
    swal({
            title: "Erro ao cadastrar Usuário!",
            text: "Erro Cadastro",
            icon: "error",
            buttons: false,
            timer: 9000
        });                    
        return false;            
  }
}


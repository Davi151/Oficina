/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function limparCampos(){
    document.getElementById('nome').value='';
    document.getElementById('preco').value='';
    document.getElementById('unidade').value='';
}

function valida() {  
  if( (document.getElementById('nome').value !== "") &&
      (document.getElementById('preco').value !== "") &&
      (document.getElementById('unidade').value !== "")) {
    {       
      return true;
  
  } 
  
    }else {
    swal({
            title: "Erro ao cadastrar Peça!",
            text: "Erro no Cadastro",
            icon: "error",
            buttons: false,
            timer: 9000
        });                    
        return false;            
  }
  }




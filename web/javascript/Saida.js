/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function limparCampos(){
    document.getElementById('u_saida').value='';
}


function valida() {  
  if( (document.getElementById('u_saida').value !== "") &&       
       (document.getElementById('peca').value !== "Peça" ) ) {
        swal({ 
          title: "Saida de Peça cadastrada com êxito!!",
          text: "Cadastro efetuado com sucesso!",
          icon: "success",          
          closeModal: true,          
          buttons: false,
          timer: 30000
      });
      return true;
  
  } else {          
    swal({
            title: "Erro ao cadastrar Saída de Peça!",
            text: "Erro Cadastro",
            icon: "error",
            buttons: false,
            timer: 9000
        });                    
        return false;            
   }
}
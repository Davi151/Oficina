/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojo;

/**
 *
 * @author Davi
 */
public class FuncionarioPojo {
    private int F_ID;
    private String F_CPF;
    private String F_NOME;
    private boolean F_ESTADO = false;

    /**
     * @return the F_ID
     */
    public int getF_ID() {
        return F_ID;
    }

    /**
     * @param F_ID the F_ID to set
     */
    public void setF_ID(int F_ID) {
        this.F_ID = F_ID;
    }

    /**
     * @return the F_CPF
     */
    public String getF_CPF() {
        return F_CPF;
    }

    /**
     * @param F_CPF the F_CPF to set
     */
    public void setF_CPF(String F_CPF) {
        this.F_CPF = F_CPF;
    }

    /**
     * @return the F_NOME
     */
    public String getF_NOME() {
        return F_NOME;
    }

    /**
     * @param F_NOME the F_NOME to set
     */
    public void setF_NOME(String F_NOME) {
        this.F_NOME = F_NOME;
    }

    /**
     * @return the F_ESTADO
     */
    public boolean isF_ESTADO() {
        return F_ESTADO;
    }

    /**
     * @param F_ESTADO the F_ESTADO to set
     */
    public void setF_ESTADO(boolean F_ESTADO) {
        this.F_ESTADO = F_ESTADO;
    }
    
}

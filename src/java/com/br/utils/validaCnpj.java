/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.utils;

import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.primefaces.validate.ClientValidator;

/**
 *
 * @author Giuliano S Cunha
 */
@FacesValidator("validaCnpj")
public class validaCnpj implements Validator, ClientValidator {

    @Override
    public void validate(FacesContext arg0, UIComponent arg1, Object valorTela) throws ValidatorException {

        valorTela = remove(String.valueOf(valorTela));

        if (validaCnpj(String.valueOf(valorTela)) != true) {

            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de Validação " + valorTela + " não é um CNPJ válido.", ""));
        }
    }
    public static boolean validaCnpj(String CNPJ) {
        if (!CNPJ.substring(0, 1).equals("")) {
            try {
                CNPJ = CNPJ.replace('.', ' ');//onde há ponto coloca espaço  
                CNPJ = CNPJ.replace('/', ' ');//onde há barra coloca espaço  
                CNPJ = CNPJ.replace('-', ' ');//onde há traço coloca espaço  
                CNPJ = CNPJ.replaceAll(" ", "");//retira espaço  
                int soma = 0, dig;
                String CNPJ_calc = CNPJ.substring(0, 12);

                if (CNPJ.length() != 14) {
                    return false;
                }
                if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111")
                        || CNPJ.equals("22222222222222") || CNPJ.equals("33333333333333")
                        || CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555")
                        || CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777")
                        || CNPJ.equals("88888888888888") || CNPJ.equals("99999999999999")
                        || (CNPJ.length() != 14)) {

                    return false;
                }

                char[] chr_CNPJ = CNPJ.toCharArray();
                /* Primeira parte */
                for (int i = 0; i < 4; i++) {
                    if (chr_CNPJ[i] - 48 >= 0 && chr_CNPJ[i] - 48 <= 9) {
                        soma += (chr_CNPJ[i] - 48) * (6 - (i + 1));
                    }
                }
                for (int i = 0; i < 8; i++) {
                    if (chr_CNPJ[i + 4] - 48 >= 0 && chr_CNPJ[i + 4] - 48 <= 9) {
                        soma += (chr_CNPJ[i + 4] - 48) * (10 - (i + 1));
                    }
                }
                dig = 11 - (soma % 11);
                CNPJ_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(
                        dig);
                /* Segunda parte */
                soma = 0;
                for (int i = 0; i < 5; i++) {
                    if (chr_CNPJ[i] - 48 >= 0 && chr_CNPJ[i] - 48 <= 9) {
                        soma += (chr_CNPJ[i] - 48) * (7 - (i + 1));
                    }
                }
                for (int i = 0; i < 8; i++) {
                    if (chr_CNPJ[i + 5] - 48 >= 0 && chr_CNPJ[i + 5] - 48 <= 9) {
                        soma += (chr_CNPJ[i + 5] - 48) * (10 - (i + 1));
                    }
                }
                dig = 11 - (soma % 11);
                CNPJ_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(
                        dig);
                return CNPJ.equals(CNPJ_calc);
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

    public static String remove(String CNPJ) {
        CNPJ = CNPJ.replace(".", "");
        CNPJ = CNPJ.replace("-", "");
        CNPJ = CNPJ.replace("/", "");

        return CNPJ;
    }

    @Override
    public Map<String, Object> getMetadata() {
        return null;
    }

    @Override
    public String getValidatorId() {
        return "validaCnpj";
    }

}

package br.com.casadocodigo.loja.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.casadocodigo.loja.models.Produto;

/**
 * 
 * @author Edson Cavalcanti
 * - O objetivo de criar essa classe de validação, é uma forma de isolarmos na aplicação todas as regras de validação do nosso modelo.
 * - Deixando as configurações nessa classe deixamos o código do controller limpo sem colocar essas regras de validação.
 * - Classe ValidationUtils possui diversos métodos que ajudam na validação dos campos.
 * - rejectIfEmpty = "rejeite de for vazio", esse método recebe 3 parâmetros: 
 * 	  1) Um objeto de errors que contém os erros de validação;
 *    2) O nome do campo que iremos validar passando uma String;
 *    3) E por último um errorCode que também é passado como String, mas que é reconhecido pelo Spring.
 * - supports esse método precisa se implementado, para indicar qual a classe a validação dará suporte, neste caso a classe Produto.
 */
public class ProdutoValidation implements Validator{

	@Override
	public boolean supports(Class<?> clazz) { // Ele verifica se o objeto recebido para validação tem a assinatura da classe Produto.
		return Produto.class.isAssignableFrom(clazz); // Com isso o  Spring pode verificar se o objeto é uma instância daquela classe ou não.
	}

	@Override
	public void validate(Object target, Errors errors) { // A Validação ocorrer neste método
		ValidationUtils.rejectIfEmpty(errors, "titulo", "field.required");
	    ValidationUtils.rejectIfEmpty(errors, "descricao", "field.required");
	  
	    Produto produto = (Produto) target;
		
		if(produto.getPaginas() <= 0 ) { // Rejeita quando o valor for menor que 0.
			errors.rejectValue("paginas", "field.required"); 
		}
		
	}

}

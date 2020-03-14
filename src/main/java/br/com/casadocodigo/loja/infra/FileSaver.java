package br.com.casadocodigo.loja.infra;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @author Edson Cavalcanti
 * - Objetivo dessa classe é de salvar o arquivo recebido.
 * - O método write será usado para salvar o arquivo em uma pasta recebida por parâmetro.
 * - esse método fará a transferencia do arquivo e irá retornar onde o arquivo foi salvo;
 * - Esse método precisara de duas informações, o local onde o arquivo será salvo e o arquivo em si.
 * - O local será recebido como String e o arquivo é um objeto MultpartFile.
 * - O file irá representar o arquivo a ser gravado no servidor, e o método transferTo é o responsável por transferir o arquivo
 *
 * - HttpServletRequest: seu objetivo é ser injetado pelo Spring para extrair o contexto atual em que usuário se encontra e então
 * - conseguir o caminho absoluto desse diretório no servidor. 
 * 
 * - Em resumo essa classe recebe um arquivo e o nome da pasta, transfere o arquivo enviado pelo formulário para a pasta 
 * - e retorna o caminho onde o arquivo foi salvo.
 * 
 * - Lembrar de configurar o Tomcat para que o mesmo utiliza a pasta de instalação, ir em ServerLocations 
 * -> marcar Use Tomcat instalation (take control of tomcat installation)
 */
@Component
public class FileSaver {

	@Autowired
	private HttpServletRequest request;

	public String write(String baseFolder, MultipartFile file) {

		try {
			
			String realPath = request.getServletContext().getRealPath("/" + baseFolder); // Extraindo contexto atual
			String path = realPath + "/" + file.getOriginalFilename();
			file.transferTo(new File(path));

			return baseFolder + "/" + file.getOriginalFilename();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}

package md5.Criptografia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.jasypt.util.text.BasicTextEncryptor;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.commons.configuration.ConfigurationException;

import org.apache.commons.configuration.PropertiesConfiguration;


@SpringBootApplication
public class Descriptografia {

	public static void main(String[] args) throws IOException, ConfigurationException {
		SpringApplication.run(Descriptografia.class, args);

        // ##################################################################################
        // CARREGAMENTO E LEITURA DO ARQUIVO .PROPERTIES
        // ################################################################################

        // para ler o arquivo de configuracao, instancie a classe Properties
        Properties properties = new Properties();

        // cria uma instancia da classe FileInputStream usando seu construtor. Ele pega
        // o caminho do arquivo de configuracao como sua entrada
       

        // CAMINHO PASTA
        FileInputStream inputStream = new FileInputStream("file.properties");

        properties.load(inputStream);

        // ##################################################################################
        // PARAMETROS DO ARQUIVO .PROPERTIES E CRIPTOGRAFIA
        // ############################################################################################

        BasicTextEncryptor textDecryptor = new BasicTextEncryptor();

        // #################################################################### CHAVE
        // PARA CRIPTOGRAFAR / DESCRIPTOGRAFAR
        // ################################################################
        textDecryptor.setPasswordCharArray("PASSWORD1".toCharArray());

        // Login
        String login = properties.getProperty("login");

        // Senha
        String senha = properties.getProperty("senha");

        if (login == null) {
            throw new IllegalArgumentException("O parametro 'login' nao foi encontrado no arquivo .properties");
        }
        if (senha == null) {
            throw new IllegalArgumentException("O parametro 'senha' nao foi encontrado no arquivo .properties");
        }
        System.out.println(
                "--------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("LOGIN");
        System.out.println("");
        System.out.println("ORIGINAL....: " + login);
        // System.out.println("");
        // System.out.println("ENCRIPTADO..: " + encryptedlogin);
        System.out.println("");
        String decryptedLogin = textDecryptor.decrypt(login);
        System.out.println("DECRIPTADO..: " + decryptedLogin);
        System.out.println("");
        System.out.println(
                "--------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("SENHA");
        System.out.println("");
        System.out.println("ORIGINAL....: " + senha);
        // System.out.println("");
        // System.out.println("ENCRIPTADO..: " + encryptedsenha);
        System.out.println("");
        String decryptedSenha = textDecryptor.decrypt(senha);
        System.out.println("DECRIPTADO..: " + decryptedSenha);
        // System.out.println("");
        System.out.println(
                "--------------------------------------------------------------------------------------------------------------------------------");

        // ##################################################################################
        // CARREGAMENTO E LEITURA DO ARQUIVO .PROPERTIES PARA EDICAO DOS PARAMETROS
        // ##########################################################
  
        // CAMINHO PASTA             
        PropertiesConfiguration config = new PropertiesConfiguration("file.properties");     
        
        config.setProperty("login", decryptedLogin);
        config.setProperty("senha", decryptedSenha);
        
        config.save();

        System.out.println("ARQUIVO .PROPERTIES DESCRIPTOGRAFADO COM SUCESSO!");

    }


}

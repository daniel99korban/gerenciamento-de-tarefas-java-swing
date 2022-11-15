
package util;

import java.io.File;

/**
 *
 * @author daniel
 * 
 * classe para lidar com arquivos necessários no projeto
 */
public class ArquivosProjeto {
    /**
     * método para obter o caminho para o diretório statico de arquivos do projeto
     * @param arquivo nome do arquivo a ser encontrado no sistema operacional
     * @return uma string contedo o caminho do arquivo
     */
    public static String getCaminhoDoArquivo(String arquivo){
        File directory = new File("");
        String caminho = (String) directory.getAbsolutePath();
        caminho += "\\src\\main\\java\\assets\\" + arquivo;
        return caminho;
    }
    
}

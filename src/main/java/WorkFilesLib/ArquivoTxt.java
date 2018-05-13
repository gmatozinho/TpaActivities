package WorkFilesLib;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

/* Código base professor Ernani - TPA*/

public class ArquivoTxt {
    private BufferedWriter bw = null;
    private BufferedReader br = null;
    private FileWriter fw = null;
    private FileReader fr = null;
    
    public static ArquivoTxt open(String nome_arq, String modo){
    	if(modo.toLowerCase().equals("wt") || modo.toLowerCase().equals("tw") || modo.toLowerCase().equals("w")){
	       try {
	    	   ArquivoTxt arqtxt = new ArquivoTxt();
	    	   arqtxt.fw = new FileWriter(nome_arq);
	    	   arqtxt.bw = new BufferedWriter(arqtxt.fw);
	    	   return arqtxt;
	        } catch (IOException e) {
	            LOGGER.log(Level.ALL,e.toString());
	       } // catch    		
    	}
    	else 
        	if(modo.toLowerCase().equals("rt") || modo.toLowerCase().equals("tr") || modo.toLowerCase().equals("r")){
     	       try {
     	    	   ArquivoTxt arqtxt = new ArquivoTxt();
     	    	   arqtxt.fr = new FileReader(nome_arq);
     	    	   arqtxt.br = new BufferedReader(arqtxt.fr);
     	    	   return arqtxt;
     	        } catch (IOException e) {
     	            LOGGER.log(Level.ALL,e.toString());
     	       } // catch    		
     	    }

		return null;
    }
    
    public String readline(){
        try {
        	String linha = br.readLine();
            return linha;
        } catch (IOException e) {
            LOGGER.log(Level.ALL,e.toString());
        } // catch
		return null;
    } // readline

    public void writeline(String content){
        try {
            bw.write(content + "\n");
        } catch (IOException e) {
            LOGGER.log(Level.ALL,e.toString());
        } // catch
    } // writeline
    
    public void write(String content){
        try {
            bw.write(content);
        } catch (IOException e) {
            LOGGER.log(Level.ALL,e.toString());
        } // catch
    } // write

    public void close() {
        try {
            if (bw != null) {
                bw.close();
            }
            else {
            	br.close();
            }

            if (fw != null) {
                fw.close();
            }
            else {
            	fr.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } // catch
    } // close
} // class ArquivoTxt

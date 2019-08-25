/**
 * Teste de conhecimento POO Java
 * @author Joao Vitor Gonzaga Jota
 */

import java.net.*;
import java.io.*;
import java.util.Scanner;

/**
 * Classe webScraper que contem os metodos necessarios para extrair informacoes
 * da pagina web usando as bibliotecas URLConnection e BufferedStreamReader
 */

class webScraper{
   
   private String nome = "";
   private double preco = 0.0;
   private String descricao = "";
   
   
   // Metodo responsavel por tratar as linhas de html da pagina, removendo todas as tags presentes numa linha
   private String removerTags(String linha){
      
      String resp = "";
      boolean tag = false;
      
      // A remocao das tags e' feita atraves de uma variavel booleana, que se for true, esta' lendo uma tag
      // E quando for false, esta lendo um texto
      for(int i = 0; i < linha.length(); i++){
          if(linha.charAt(i) == '<'){
             tag = true;
          }else if(linha.charAt(i) == '>'){
             tag = false;
          }                        
          if(!tag){  
             if(linha.charAt(i) != '<' && linha.charAt(i) != '>'){
                resp = resp + linha.charAt(i);
             }
          }
        }
        
      return resp;
   }
   
   // Metodo responsavel por ler a pagina atraves da URL passada por parametro
   // Usando URLConnection e BufferedReader
   
   public void lerPagina(String link){
      
      
      try{
         
         URL url = new URL(link);
         URLConnection conn = url.openConnection(); // Procedimento que realiza a conexao com a pagina web
         
         //Criacao do leitor da pagina
         InputStream is = url.openStream();
         InputStreamReader isr = new InputStreamReader(is);
         BufferedReader br = new BufferedReader(isr);
         
         String linha = br.readLine();
         System.out.println(linha);
         
         // Cada informacao que sera' tirada da pagina sera feita atraves de 'palavras-chave' que sao classes das tags que o site do MercadoLivre usa em suas paginas
         // Cada informacao na pagina tem um padrao especifico para a formatacao dentro de seu codigo HTML
         
         while (linha != null) {
            linha = br.readLine();
            
            // Lendo o nome do produto
            if(linha.contains("item-title__primary")){
               this.nome = linha = br.readLine();
               this.nome = this.nome.replaceAll("		", "");
            }
            
            // Lendo o preco do produto
            if(linha.contains("price-tag")){
               String resp = "";
               br.readLine();
               resp = resp + (removerTags(br.readLine()));
               
               br.readLine();
               linha = br.readLine();
               if(linha.contains("price-tag-decimal-separator")){
                  resp = resp + "." + (removerTags(br.readLine()));
               }
               
               resp = resp.replaceAll("				", "");
               resp = resp.replaceAll("	", "");
               this.preco = Double.parseDouble(resp);
               
            }
            
            // Lendo a descricao do produto
            if(linha.contains("item-description__text")){
               String resp = "";
               while(!linha.contains("</section>")){
                  linha = br.readLine();
                  resp = resp + removerTags(linha);   
                  resp = resp.replaceAll("		", "");
               }
               
               // For que consegue formatar a descricao na saida de dados de uma forma mais apresentavel
               for(int i = 0; i < resp.length(); i++){
                 if(resp.charAt(i) == '!' || resp.charAt(i) == '?' || resp.charAt(i) == '.'){
                    descricao = descricao + resp.charAt(i) + "\n";
                 }else{
                    descricao = descricao + resp.charAt(i);
                 }
               }
            }
         
         }
         
      }catch(Exception e) { }
   }
   
   // Metodo simples que apenas imprime na tela as informacoes do produto
   public void imprimir(){
      
      System.out.println("------Informações do produto------\n");
      System.out.println("Nome do produto: " + this.nome);
      System.out.println("Preço: R$" + this.preco + "\n");
      System.out.println("Descrição:\n " + this.descricao + "\n");
      
      
   }
   
}

class Teste2{

   public static void main(String [] args){
      
      webScraper a = new webScraper();
      Scanner s = new Scanner(System.in);
      
      String link = "";
      
      System.out.println("Cole aqui o URL da pagina: ");
      link = s.nextLine();
      
      a.lerPagina(link);
      a.imprimir();
      
   }

}
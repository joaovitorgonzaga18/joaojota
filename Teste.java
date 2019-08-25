/**
 * Teste de conhecimento POO Java
 * @author Joao Vitor Gonzaga Jota
 */

import java.util.Scanner;


/**
 * Classe que armazena os dados dos animais e suas ações
 * Nela pode ser gardado tambem a cor da plumagem/pelugem do animal
 */
class Animal{
   
   /**
    * Lista de codigos dos animais
    * 1 - Pato
    * 2 - Galinha
    * 3 - Vaca
    * 4 - Morcego
    */
   
   // Atributos da classe
   
   private int codigo; // Variavel que define o animal da classe
   
   private int Idade;
   private double Tamanho;
   private String Cor;
   private boolean Voar;
   
   // Construtor da classe
   
   public Animal(){
      int Idade = 0;
      double Tamanho = 0.0;
      String Cor = "";
      Voar = false;      
   } 
   
   public Animal (int codigo, int idade, double tamanho, String cor){
      this.codigo = codigo;
      this.Idade = idade;
      this.Tamanho = tamanho;
      this.Cor = cor;
   
      if(this.codigo == 1 || this.codigo == 4){
         this.Voar = true;
      }
   }
   
   
   // Funcoes da classe
   // Funcao simples que apenas mostra na tela o som que o animal faz
   public String Som(){
      String resp = "";
      switch (codigo){
         case 1 :
            resp = "QUACK!";
            break;
         case 2 :
            resp = "COCORICO!";
            break;
         case 3 :
            resp = "MUUUU!";
            break;
         case 4 : 
            resp = "QUICK!";
            break;
      }
      
      return resp;
   }
   
   public void Voar(){
   
      if(this.Voar){
         System.out.println("Pode voar"); Som();
      }else{
         System.out.println("Nao pode voar");
      }
   }
   
   // Funcao que mostra detalhadamente na tela todos os dados do animal
   
   public void imprimir(){
      
      String Animal = "";
      System.out.println("//------------------------------------------------");
      
      // Switch que define a string como o nome do animal de acordo com o codigo
      switch (this.codigo){
         case 1 : 
            Animal = "Pato";
            break;
         case 2 : 
            Animal = "Galinha";
            break;
         case 3 : 
            Animal = "Vaca";
            break;
         case 4 : 
            Animal = "Morcego";
            break;
      }
      
      // Impressao dos dados
      System.out.println("Animal: " + Animal + "(Som emitido: '" + Som() +"')" ); 
      System.out.println("Idade: " + getIdade());
      System.out.println("Tamanho: " + getTamanho());
      System.out.println("Cor: " + getCor());
      System.out.print("Voar: "); Voar();
      
   }
   
   // Getters e Setters da classe
   // Setters
   
   public void setIdade(int idade){
      if(idade >= 0){
         this.Idade = idade;
      }
   }
   
   public void setTamanho(double tamanho){
      if(tamanho > 0){
         this.Tamanho = tamanho;
      }
   }
   
   public void setCor(String cor){
      if(cor.length() > 3){
         this.Cor = cor;
      }
   }
   
   // Getters
   
   public int getIdade(){
      return (this.Idade);
   }
   
   public double getTamanho(){
      return (this.Tamanho);
   }
   
   public String getCor(){
      String resp = "";
      
      if(this.codigo == 1 || this.codigo == 2){
         resp = "A cor das penas e' " + this.Cor; 
      }else if(this.codigo == 3 || this.codigo == 4){
         resp = "A cor dos pelos e' " + this.Cor;
      }
      
      return resp;
   }
   
   
   
}

/**
 * Classe que permite administrar os dados e os animais contidos na fazenda
 * Nela e' possivel inserir animais na fazenda e remove-los
 */

class Fazenda{
   
   private Animal[] animal;
   private int n = 0; // Variavel que controla a quantidade de animais na fazenda, nao permitindo que ultrapasse o limite determinado no construtor
   
   // Construtor padrao
   // A quantidade padrao de animais na fazenda e' 4, sendo eles um pato, uma galinha, uma vaca e um morcego
   public Fazenda(){
      animal = new Animal[4];
   }
   
   // Construtor secundario
   // Pode se indicar a quantidade de animais na fazenda no parametro
   public Fazenda(int quantidade){
      n = quantidade;
      animal = new Animal[quantidade];
   }
     
   public void inserirAnimal(int codigo, int idade, double tamanho, String cor) throws Exception{
      
      
      if(n >= animal.length){
         System.out.println("Erro ao inserir, Limite de insercao alcancado!");
         throw new Exception("Erro ao inserir!");
      }
 
      animal[n] = new Animal(codigo, idade, tamanho, cor);
      n++;
      
   }
   
   public Animal removerAnimal(int pos) throws Exception{
   
      // validar remocao, verificar se a posicao escolhida e' valida ou se o arranjo esta' vazio
      if (n == 0 || pos < 0 || pos >= n) {
         System.out.println("Erro ao remover, nao ha animais para remover!");
         throw new Exception("Erro ao remover!");
      }
 
      Animal resp = new Animal();
      resp = animal[pos];

      n--;
      
      // for que garante que nao haja espaços vazios no arranjo
      for(int i = pos; i < n; i++){
         animal[i] = animal[i+1];
      }
 
      return resp;
   }
   
   public void MostrarAnimais() throws Exception{
      
      if(n == 0){
         throw new Exception("Erro ao mostrar!");
      }
      
      for(int i = 0; i < n; i++){
         animal[i].imprimir();
      }
      
   }
     
}

class Teste{
   
   public static void main(String [] args){
      try{
      
      Fazenda f = new Fazenda();
      Scanner s = new Scanner(System.in);
      
      int op = 0, codigo = 0, idade = 0;
      double tamanho = 0.0;
      String cor = "";
      
      System.out.println("Deseja inserir algum animal? (1 - SIM / 2 - NAO)");
      do{
         // Loop que garante que o usuario escolha uma das opcoes disponiveis
         while(op < 1 || 2 < op ){
            op = s.nextInt();
         }
         
         if(op == 1){
            
            op = 0;
            codigo = 0;
            idade = 0;
            tamanho = 0.0;
            cor = "";
            
            System.out.println("Escolha o animal a ser inserido pelo codigo");
            System.out.println("1 - Pato / 2 - Galinha / 3 - Vaca / 4 - Morcego");
            
            // Loop que garante que o usuario escolha uma das opcoes disponiveis
            while(codigo < 1 || 4 < codigo ){
               codigo = s.nextInt();
            }
            System.out.println("");
            System.out.print("Digite a idade do animal (Maximo = 100): ");
            
            while(idade <= 0 || 100 <= idade){
               idade = s.nextInt();
            }         
            
            
            System.out.println("");
            System.out.print("Digite o tamanho do animal (Exemplo = 0,25): ");
            
            while(tamanho <= 0){
               tamanho = s.nextDouble();
            }
            
            System.out.println("");
            System.out.print("Digite a cor do animal (Exemplo = Marrom): ");
            
            while(cor.length() <= 3){
               cor = s.nextLine();
            }
            
            f.inserirAnimal(codigo, idade, tamanho, cor);
         }
               
         System.out.println("//------------------------------------------------");
         System.out.println("Deseja inserir mais um animal? (1 - SIM / 2 - NAO)");
         
         op = s.nextInt();
      }while(op != 2);
      
      
      System.out.println("Mostrando animais inseridos....");
      f.MostrarAnimais();
 
      }catch(Exception e) { }
      
      
   }
   
}
import java.util.Scanner;

public class App{
	public static void main(String[] args){
		
		// Variavel opcao
        int opcao;
        // Variavel Scanner
        Scanner input = new Scanner(System.in);  

        do{
            Utils.menuPrint();
            opcao = input.nextInt();
            //System.out.println("Opcao escolhida: " + opcao);
            if(opcao > 3 || opcao < 0){
                System.out.println("Opcao Invalida! Selecione outra...");
            }else{
                switch(opcao){
					case 0: 
						System.out.println(" Opcao 0 - Sair da aplicacao ");
						try {
							Utils.sair();
						} catch (Exception e) {
							System.out.println("ERRO... ao sair da aplicacao ");
							e.printStackTrace();
						} 
						break;
                    case 1:
                        System.out.println(" Opcao 1 - Listar Distritos ");
						try {
							Utils.getDistritos();
						} catch (Exception e) {
							System.out.println("ERRO... ao obter distritos da base de dados ");
							e.printStackTrace();
						}
                        break;
                    case 2:
                        System.out.println("Opcao 2 - Listar Concelhos de Distrito");
						System.out.print("Insira ID do distrito: ");
						int id_distrito = input.nextInt();
						try {
							Utils.getConcelhos(id_distrito);
						} catch (Exception e) {
							System.out.println("ERRO... ao obter concelhos da base de dados ");
							e.printStackTrace();
						}
						break;
                    case 3:
                        System.out.println("Opcao 3 - Listar freguesias de determinado concelho");
						System.out.print("Insira ID do Concelho: ");
						int id_concelho = input.nextInt();
						try {
							Utils.getFreguesias(id_concelho);
						} catch (Exception e) {
							System.out.println("ERRO... ao obter freguesias da base de dados ");
							e.printStackTrace();
						}
                        break;
                    default:
                        System.out.println("Opcao selecionada nao existe!");
                }
            }
        }while(opcao != 0);
		input.close();        
	}
}

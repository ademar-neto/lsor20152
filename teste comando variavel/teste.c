/*
programa teste para ver como se comporta uma execucao de comandos system a partir de uma variavel char/String
o comando é armazendado na variavel comando e dps executado a partir da instrucao system
*/

#include<stdio.h>
#include<conio.h>
#include<stdlib.h>

main(){
	char comando[500] = " ";
	int opcao = 0;
	
	do{
		system("cls");
		printf("Teste de comandos do windows a partir de uma variavel.");
		printf("\nOBS: todos os comandos inseridos serao executados nesta maquina, cuidado\n\n\n");	
		printf("opcao 1 - Inserir Comando\n");
		printf("opcao 2 - ver infomacoes de rede\n");
		printf("opcao 3 - sair\n\n");
		printf("Escolha: ");
		scanf("%d", &opcao);
		printf("%d",opcao);
		switch(opcao){
			case 1:
				system("cls");
				printf("Digite um comando: ");
				scanf("%s", &comando);
				system("cls");
				printf("Comando inserido: %s", comando);
				printf("\n\n");
				system(comando);
				system("pause>null");
				break;
			case 2:
				system("cls");
				printf("Informacoes de rede deste computador\n\n");
				system("ipconfig");
				system("pause>null");
				break;
		}	
	}while(opcao != 3);	
	
	exit(0);
}

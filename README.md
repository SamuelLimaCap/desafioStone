# Desafio da Stone

---

# Para que serve

O programa é um algoritmo que lê as depesas e divide a quantia total entre as pessoas cadastradas
---
# Requisitos
- JRE 8+
- Arquivo de despesas no formato txt, obedecendo o seguinte padrão para despesas:
	```
	nome: "nome"
	Quantidade: 2
	valor unitário: R$999,00
	
	nome: "nome2"
	Quantidade: 3
	valor unitário: R$299,99
	```

- Arquivo de pessoas no formato txt, obedecendo o seguinte padrão para cada pessoa:
	```
	nome1
	nome2
	nome3
---

# Como funciona

O arquivo é um programa que é executado através da linguagem JAVA. Você pode rodar esa aplicação de 3 maneiras:


## 1- Passando o caminho do arquivo depois de executar a aplicação
Utilize o comando via prompt de comando:

	//Caminhe até a pasta src
	javac desafio/Main.java
	java desafio.Main
	
Após isso, a aplicação pedirá que digite o caminho absoluto de onde está o arquivo para leitura (caso esteja no mesmo lugar que o programa, informe apenas o nome do arquivo de leitura).

## 2- Passando o caminho do arquivo como parâmetro
Para isso, adicione o parâmetro 'path', e, em seguida, passe os caminhos absolutos do arquivo entre aspas duplas, como no exemplo a seguir:

	//Caminhe até a pasta src
	javac desafio/Main.java
	java desafio.Main path "C:/Users/TESTE/Desktop/despesas.txt" "C:/Users/TESTE/Desktop/despesas.txt"
	
---

Após a passagem dos caminhos dos arquivos, o algoritmo será executado e, caso os dados estejam todos corretos, gerará um arquivo "out-nomeDoArquivo.txt" no mesmo diretório do arquivo de entrada das despesas


---
# Observações
- Linhas em branco serão desconsideradas
- Linhas em branco com espaço serão contados como parâmetros
- No arquivo de despesas, todas as chaves deverão ser minúsculas e como indicado na seção 'Como funciona' (Não será aceito "Nome :", somente "nome:")

# Erros que podem ser gerados no console
- Caso o caminho do arquivo esteja errado, o programa irá soltar uma exceção informando que não foi encontrado o arquivo
- Caso algum valor das despesas esteja incorreto(Ex: "Valor Unitário" ao invés de "valor unitário"), gerará uma exceção no console informando a linha com erro
- Caso haja algum nome repetido no arquivo de nomes, este gerará uma exceção





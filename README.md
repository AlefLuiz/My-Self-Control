<h1 align="right"> My Self Control </h1>
<br>

<h2>Personal financial control software</h2>

#### Sobre:

Software totalmente idealizado por mim. No passado utilizava uma planilha no Excel para gerenciar meus gastos e ter um controle financeiro, após estudar sobre Hibernate e JavaFx me vi numa oportunidade de por em prática e testar/aprimorar meus conhecimentos.
A primeira etapa foi elaborar um WireFrame de Alta Fidelidade, utilizei o software Adobe XD e o resultado vemos logo abaixo:
<img src="https://github.com/AlefLuiz/My-Self-Control/blob/master/assets/wireframe.png" width="80%" align="center">
<br>
O próximo passo foi elaborar os modelos de entidades e testar a conexão/criação do banco de dados, estando tudo funcionando parti pras Views.
Utilizando JavaFx, foi possível projetar as Views perfeitamente iguais ao WireFrame, usando arquivos FXML, CSS e Controladores em Java.

 <h6> Bibliotecas utilizadas: Hibernate, JavaFx, JodaTime, MySQL Connector</h6>
 
 Para testar/utilizar o software disponibilizei na pasta Runnable o projeto exportado, onde é necessário ter MySql local pré-definidamente configurado como "root" e senha "123456" (possível trocar abrindo o arquivo .jar e substituindo o arquivo persistence.xml em META-INF)
 Abrir pelo arquivo Start.bat
 
#### Prints

| Users | Info | Expenses |
| :--------: | :--------: | :--------: |
| ![users.jpg](https://github.com/AlefLuiz/My-Self-Control/blob/master/assets/Users.png) | ![info.jpg](https://github.com/AlefLuiz/My-Self-Control/blob/master/assets/Info.png) | ![expense.jpg](https://github.com/AlefLuiz/My-Self-Control/blob/master/assets/Expenses.png) | 	
| *Tela de cadastro/alteração de usuários, informe seu nome completo, cpf, renda, quanto gostaria de guardar e o quanto gostaria de usar para lazer.* | *Tela de informações gerais com gráfico em Pizza, nela você consegue ver onde precisa melhorar para se encaixar no seu planejamento.* | *Tela de cadastro e gerenciamento de despesas, seja fixa ou variavel.* |

#### Releases:
 
 - Upload do projeto [01/09/2020]

<h5 align="center"> <a href="https://github.com/AlefLuiz/My-Self-Control/blob/master/LICENSE">License MIT</a></h5>

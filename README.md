# Mini Autenticador

### Respostas aos questionamentos:

1. Apresente observações/problemas sobre essa solução.
Comente qual(is) a(s) sua(s) estratégia(s) para melhorar este sistema em termos de qualidade e manutenção. Justifique suas decisões.
  - Levando em consideração as características aprsentadas posso elencar algumas ações:
    1. Retirar as lógicas de negócio do controller e colocá-las, de forma mais unitária possível nas classes de serviço;
    2. Deixar com que validações específficas de domínio(NotNull, tamanho de campos, etc.) sejam tratadas nas classes respectivas utilizando das anotações corretas de validation. Essas validações indiferem do banco utilizado e são melhor para dar manutenção;
    3. Começar gerando testes unitários das novas features e de alterações, e conforme possibilidade ir implementando das demais funcionalidades para diminuir gradativamente esse débito técnico;
2. Descreva quais são as principais limitações ao se adotar servidores de aplicação em uma arquitetura orientada a microsserviços.
    - Acredito que não haja grandes limitações em se utilizar servidores de aplicação, principalemte hoje, que eles estão embarcados em cada aplicação. Devemos apenas ter o cuidado de utilizar ferramentas de padronização, como um template default para as configurações, ou arquitetura como serviço(Terraform, etc) para que não haja desperdício de recurso e dificuldade de manutenção. Outro ponto de atenção é a segurança na comunicações entre esses microserviços e a orquestração dos mesmos, porém hoje com as clouds existentes temos muita coisa automatizada para isso (Kubernets, etc.).
3. Atualmente, diversas aplicações escritas em Java estão deixando de serem desenvolvidas para rodarem em servidores (JBoss, Tomcat), adotando ferramentas que disponibilizam um servidor embutido na própria ferramenta. Quais são os principais desafios ao se tomar uma decisão dessas? Justifique sua resposta.
    - Assim como dito na resposta anterior, para essa migração é interessante ter uma cultura Devops mais estruturada, com pipelines, deploys padronizados, para que haja melhor alocação dos recursos e manter uma boa escalabilidade.

### Definições e anotações sobre o teste prático:

 - Por um problema em minha máquina, não consegui utilizar os bancos pedidos, criei um banco H2 que sobe junto a aplicação, com a criação das tabelas feitas pelo liquibase, apenas para não invalidar o desenvolvimento. O banco pode ser acessado pelo caminho http://localhost:8080/h2;
 - Utilizei um template básico de apis neste projeto(domain,repository,service,controller);
 - Tive alguns problemas com o security, devido a alguma particularidade da minha IDE ou máquina, então optei por "desligar";
 - Os testes de crontroller estão bem genéricos, apenas validando os endpoints.
 - Os testes de regra de negócio estão bem "unitários", gostaria de ter feito testes de feature mas não tive tempo hábil;
 - Sobre a não utilização de Ifs, utilizei quando julguei ser mais simples(else nunca kkk);
 - Sobre as transações disparadas simultaneamente, tentei blindar utilizando Transacion e politica de Lock pessimista no update, confesso que não tenho muita experiencia real com esse tipo de transação;
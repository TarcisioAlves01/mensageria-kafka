
### Arquitetura do projeto
O projeto consiste em um sistema responsável por gerenciar o processo de recepção, validação e pagamento de boletos. A comunicação entre os diferentes serviços do sistema é realizada por meio de mensageria utilizando o **Apache Kafka**. Cada etapa do processo foi orquestrada para garantir alta disponibilidade, escalabilidade e integridade dos dados.

###Tecnologias Utilizadas

**Java:** Linguagem de programação utilizada para desenvolver a aplicação.
**Spring** Boot: Framework para facilitar a criação de APIs RESTful e microserviços.
**Docker:** Plataforma para automação da implantação e execução de aplicações em contêineres.
**Apache Kafka:** Sistema distribuído de mensageria para gerenciar o fluxo de dados em tempo real.
**Apache Avro:** Framework para serialização de dados, utilizado no processo de comunicação entre sistemas.
**Control Center:** Ferramenta para gerenciar e monitorar o Apache Kafka.
**Schema Registry:** Sistema que garante a validação e o controle de esquemas de dados no Apache Kafka.
**Banco de Dados H2:** Banco de dados relacional em memória utilizado para armazenamento temporário de dados.


![Alt Text](./imagens/arquitetura.gif)

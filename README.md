# Files on DB

 **File on DB** é um aplicativo desenvolvido em Java que possibilita o armazenamento de arquivos no banco de dados MYSQL utilizando criptografia, gerenciamento de tarefas, e controle de contas de clientes.


# Arquivos Criptografados

Os arquivos upados no banco de dados são *criptografados* com duas chaves diferentes onde uma delas é **AES** e outra **RSA**.
A chave **RSA** pública já esta contida na pasta **res**.

O arquivo é criptografado em chave **AES** gerada aleatóriamente, e logo em seguida essa chave é criptografada com a chave **RSA**.

Tendo isso, é feito o upload do arquivo *criptografado* junto com sua chave **AES** criptografada.

Para baixar, é necessário informar a chave **RSA** privada (ou publica caso tenha sido criptografado com a chave privada), e o caminho inverso é feito.

## Controle  de Atividades

O programa conta com uma parte destinada ao controle de atividades, onde é possível adicionar cards com titulos, data de entrega e setar cor.

Os cartões possuem checklists, anotações, descrição, e aviso por e-mail de data de entrega. 

## Clientes

Há uma área de cadastro de clientes.
Os **documentos** são vinculados aos clientes, assim como suas **contas**.

É possivel **enviar e-mail com anexo** para os clientes sem a necessidade de sair do programa.

## Contas

É possível adicionar contas ao cliente, informando se é pagamento em *dinheiro* ou *parcelado*, e se o parcelamento vai ser como *cartão* ou não (como por exemplo crediário).

Pagamentos em dinheiro e parcelamentos sem forma de cartão terão que ser debitados manualmente, pois depende do cliente vir pagar.
Já contas marcadas como pagamento em cartão serão baixadas do sistema automaticamente.

O sistema de contas também conta com 6 relatórios.

## Deletar arquivo

Remover um arquivo levara esse ficheiro para a lixeira mesmo que o banco de dados não esteja no seu computador. Isso é feito para que não corra risco de apagar documentos importantes sem a possibilidade de restaura-los caso necessário.

## Chat

Chat ainda em desenvolvimento e melhorias, futuras atualizações tornarão o ChatServer em uma aplicação separada do projeto atual, fazendo que o Server sempre esteja em execução.


# Sincronização

Programa desenvolvido para que mais de um compudator possa ter acesso a arquivos com segurança, em qualquer lugar que a aplicação esteja. Assim como atividades, contas e tudo que o programa ofereçe.

- Conta com backup do Banco de Dados.
	> Para realiza-lo basta clicar no botão tipo "nuvem" no canto esquerdo da tela.

- Chave RSA privada não precisa ser carregada a cada download de arquivo. Basta carrega-la uma vez e um botão com o icone de uma chave ficara verde sinalizando que ela já esta carregada. Assim podes baixar os próximos arquivos sem especifica-la.
	> É recomendado que se for ficar um tempo sem baixar nenhum arquivo então descarrege a chave privada clicando no botão da chave (que deve estar verde). Isso mantém a chave privada mais segura, sem ataques de acesso ao local de memória para obte-la.



Projeto desenvolvido por mim [Jonathan CR]([https://www.facebook.com/jonathan.cominribeiro](https://www.facebook.com/jonathan.cominribeiro))

<!--stackedit_data:
eyJoaXN0b3J5IjpbLTEwNzMyNTYxNzZdfQ==
-->
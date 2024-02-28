# PI_4_Semestre

Nome do Grupo: Homo Sapiens

Backlog:

 Criar conta no Trello.
 Criar lista "Backlog" no Trello.
 Adicionar histórias do Sprint 1 à lista "Backlog".
To-Do:

 Criar lista "To-Do" no Trello.
 Mover histórias do Sprint 1 para a lista "To-Do".
Doing:

 Criar lista "Doing" no Trello.
 Mover histórias em andamento para a lista "Doing".
Done:

 Criar lista "Done" no Trello.
 Mover histórias concluídas para a lista "Done".

Caros,
   Seguem as histórias do Sprint 1 - Entrega desta sprint em 08/03/2024
   Lembre-se, preciso do nome do grupo e integrantes para criar no black. Me envie por email ou whatsapp.
   Vocês precisam registrar o grupo de vocês no trello (ou outra ferramenta) e me coloca como participante - leonildo.cjunior@sp.senac.br.
   Vocês precisam registar o grupo no github (ou outra ferramenta) e me colocar como participante - leonildo.cjunior@sp.senac.br
Sprint
Como Um
Eu quero, eu posso
Para que
Critério de aceite
1
Usuário não logado
Identificar no sistema
Possa entrar no backoffice
- os dados de login devem ser validados no banco de dados
- O login é o email do usuário.
- A senha deve ser encriptada na ponta antes de validação com o dado no banco (que também estará encriptado).
- Não localizando o sistema deve negar a entrada do usuário no backoffice.
- Logando (usuário e senha corretos e habilitado) o sistema deve cair na página principal do backoffice onde terá o link para a página de lista de produtos(todos PERSONAS) e lista de usuário para administrador.
- Logando também o sistema deve criar a sessão com o usuáro e seu grupo(administrador ou estoquista).
- Se um cliente entrar com email e senha o mesmo deve ser rejeitado. Esta tela de login é apenas para usuários de backoffice
1
Administrador
Listar Usuário
Possa acessar as opções de incluir, alterar e habilitar/desabilitar
- Lista todos os usuários  usuários cadastrados no sistema na entrada da tela mostrando o Nome, email, status, Grupo - para administrador.
- Ao clicar em altera usuário o sistema deverá enviar para Alteração
- Ao clicar em inativar/reativar o sistema deverá troca (se ativo passa para inativo ou se inativo passa para ativo)
- O listar usuários deve permitir filtrar (por nome de usuário) a lista de usuários do sistema
1
Administrador
Cadastrar um usuário
Possa incluir um acesso ao backoffice
- Cadastrar o nome do usuário, cpdf, email, senha, grupo (admin/estoquista) no banco de dados.
- No cadastro, pedir a senha 2 vezes. Só permitir o cadastro quando as 2 senhas estiverem iguais.
- A senha deve ser encriptada antes de enviar para o banco de dados.
- O cadastro de usuário cadastra o registro como ativo (sempre)
- Não é permitido cadastrar dois usuários com mesmo login (email)
- O cpf deve ser validado antes da gravação.
1
Administrador
Alterar um usuário
Possa manter os dados de um usuário no backoffice
É permitido apenas alterar o grupo (se não for o usuário logado no momento)
- É permitido alterar o nome, cpf, senha (sempre validando a senha 2 vezes) e ela deve ser atualizada no banco de forma encriptada.
- não é permitido alterar o email.
- toda alteração deve refletir no banco de dados
1
Administrador
Habilitar e Desabilitar um usuário
Remover ou conceder acesso a um usuário cadastado no backoffic
- Na mesma tela de listagem de Usuário, o usuário admin poderá alterar o status dos usuários para ativo (se ele estiver inativado) ou inativar (se ele estiver ativo).
- Sem a necessidade de entrar em outr página para alteração. A alteração deve apenas ser confirmada por tela de mensagem de alerta pedindo se o usuário concorda ou não com a alteração.

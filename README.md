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

Critérios de Aceite - Login e Autenticação

1. Usuário não logado
	• Identificar no sistema.
	• Permitir entrada no backoffice.

2. Validação de Dados de Login
	• Validar dados de login no banco de dados.
	• Utilizar o email como login.
	• Encriptar a senha antes da validação.
	• Negar entrada se os dados não forem localizados no sistema.
	• Criar sessão com usuário e grupo ao logar corretamente.

3. Restrição de Acesso para Clientes
	• Rejeitar entrada de clientes com email e senha.
Critérios de Aceite - Página Principal do Backoffice

4. Página Principal do Backoffice
	• Após login bem-sucedido (usuário e senha corretos), redirecionar para a página principal do backoffice.
	• Na página principal, incluir links para listagem de produtos e lista de usuários para administrador.
Critérios de Aceite - Listagem de Usuários para Administrador

5. Listar Usuários
	• Exibir na entrada da tela: Nome, email, status e Grupo para administrador.
	• Permitir filtrar a lista por nome de usuário.
	• Ao clicar em "Alterar Usuário", enviar para a tela de alteração.
	• Ao clicar em "Inativar/Reativar", trocar o status do usuário.
Critérios de Aceite - Cadastro de Usuário

6. Cadastrar Usuário
	• Incluir acesso ao backoffice durante o cadastro.
	• Solicitar e confirmar senha durante o cadastro.
	• Encriptar a senha antes de enviar para o banco.
	• Cadastrar usuário como ativo.
	• Validar e garantir unicidade do email.
	• Validar CPF antes da gravação.
Critérios de Aceite - Alteração de Usuário

7. Alterar Usuário
	• Manter dados de um usuário no backoffice.
	• Permitir alteração de nome, CPF e senha (com validação dupla).
	• Encriptar a senha antes de atualizar no banco.
	• Não permitir alteração de email.
	• Refletir todas as alterações no banco de dados.
Critérios de Aceite - Habilitar/Desabilitar Usuário

8. Habilitar/Desabilitar Usuário
	• Alterar status (ativo/inativo) na tela de listagem de usuários.
	• Confirmação da alteração por tela de mensagem de alerta.
	• Não necessidade de entrar em outra página para a alteração.


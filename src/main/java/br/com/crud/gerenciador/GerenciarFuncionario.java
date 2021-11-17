package br.com.crud.gerenciador;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.crud.dao.FuncionarioDAO;
import br.com.crud.model.ModelFuncionario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GerenciarFuncionario")
public class GerenciarFuncionario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
   
    public GerenciarFuncionario() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String msg = "Registro excluido!";
			String acao = request.getParameter("acao");

			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {

				String idFuncionario = request.getParameter("id");

				FuncionarioDAO.deletarFuncionario(idFuncionario);
										
				request.setAttribute("msg", msg);
				//request.setAttribute("modolFuncionario", modelFuncionario);
				request.getRequestDispatcher("index.jsp").forward(request, response);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjax")) {

				String nomeBusca = request.getParameter("nomeBusca");

				List<ModelFuncionario> dadosJsonUser = (List<ModelFuncionario>) FuncionarioDAO
						.buscaPorSimilaridade(nomeBusca);

				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(dadosJsonUser);

				response.getWriter().write(json);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")) {

				String id = request.getParameter("id");

				ModelFuncionario modelFuncionario = FuncionarioDAO.consultaUsuarioID(id);

				request.setAttribute("msg", "Usuário em edição");
				request.setAttribute("modolFuncionario", modelFuncionario);
				request.getRequestDispatcher("index.jsp").forward(request, response);

			}
		}

		catch (Exception e) {
			System.out.println("Erro " + e);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			try {
				String msg = "Registro salvo com sucesso!";
				String id = request.getParameter("id");
				String nome = request.getParameter("nome");
				String idade = request.getParameter("idade");
				String sexo = request.getParameter("sexo");
				String email = request.getParameter("email");
				String profissao = request.getParameter("profissao");

				ModelFuncionario modelFuncionario = new ModelFuncionario();

				modelFuncionario.setFuncionarioId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
				modelFuncionario.setFuncionarioNome(nome);
				modelFuncionario.setFuncionarioIdade(Integer.parseInt(idade));
				modelFuncionario.setFuncionarioSexo(sexo);
				modelFuncionario.setFuncionarioEmail(email);
				modelFuncionario.setFuncionarioProfissao(profissao);

				if (modelFuncionario.isNovo()) {
					msg = "Gravado com sucesso!";
				}else {
					msg = "Atualizado com sucesso!";
				}	
				
				funcionarioDAO.adicionar(modelFuncionario);

				String filtro = request.getParameter("filtro");
				new FuncionarioDAO();
				Collection<ModelFuncionario> funcionarios = FuncionarioDAO.buscaPorSimilaridade(filtro);
				for (ModelFuncionario funcionario : funcionarios) {
					System.out.println(funcionario.getFuncionarioId() + " " + funcionario.getFuncionarioNome() + " "
							+ funcionario.getFuncionarioIdade() + " " + funcionario.getFuncionarioSexo() + " "
							+ funcionario.getFuncionarioEmail() + " " + funcionario.getFuncionarioProfissao());
				}

				request.setAttribute("msg", msg);
				request.setAttribute("modolFuncionario", modelFuncionario);
				request.getRequestDispatcher("index.jsp").forward(request, response);

			} catch (Exception e) {
				// TODO: handle exception
			}

		}

	}

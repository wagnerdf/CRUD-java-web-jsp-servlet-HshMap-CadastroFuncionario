package br.com.crud.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.crud.model.ModelFuncionario;


public class FuncionarioDAO {
	
	private final static Map<Long, ModelFuncionario> FUNCIONARIOS = new HashMap<>();
	static {
		
		geraIdAdiciona(new ModelFuncionario("Wagner Andrade",44,"Masculino","wagner@gmail.com","Desenvolvedor"));
		geraIdAdiciona(new ModelFuncionario("Marina Andrade",38,"Feminino","marina@gmail.com","Enfermeira"));
		geraIdAdiciona(new ModelFuncionario("Kelly Silva",27,"Feminino","Kelly@gmail.com","Secretária"));
		
	}
	
	public static Collection<ModelFuncionario> buscaPorSimilaridade(String nome){
		if (nome == null)
			return FUNCIONARIOS.values();
		
		List<ModelFuncionario> similares = new ArrayList<>();
		for (ModelFuncionario modelFuncionario : FUNCIONARIOS.values()) {
			if(modelFuncionario.getFuncionarioNome().toLowerCase().contains(nome.toLowerCase()))
				similares.add(modelFuncionario);
		}
		return similares;
	}
	
	
	public void adicionar(ModelFuncionario funcionario) {	
		if(funcionario.isNovo() == true) {
			geraIdAdiciona(funcionario);
		}else {
			
			FUNCIONARIOS.put(funcionario.getFuncionarioId(), funcionario);		
			
		}
	}

	private static void geraIdAdiciona(ModelFuncionario funcionario) {
		long id = FUNCIONARIOS.size()+1l;
		funcionario.setFuncionarioId(id);
		FUNCIONARIOS.put(id, funcionario);
	}
	
	public static void deletarFuncionario(String idFuncionario){
		for (ModelFuncionario funcionario : FUNCIONARIOS.values()) {
			if(funcionario.getFuncionarioId() == Long.parseLong(idFuncionario)) {
				FUNCIONARIOS.remove(Long.parseLong(idFuncionario));
			}
		}
	}
	
	public static ModelFuncionario consultaUsuarioID(String id) throws Exception {

		ModelFuncionario modelFuncionario = new ModelFuncionario();

		for (ModelFuncionario funcionario : FUNCIONARIOS.values()) {
			if(funcionario.getFuncionarioId() == Long.parseLong(id)) {
				modelFuncionario.setFuncionarioId(funcionario.getFuncionarioId());
				modelFuncionario.setFuncionarioNome(funcionario.getFuncionarioNome());
				modelFuncionario.setFuncionarioIdade(funcionario.getFuncionarioIdade());
				modelFuncionario.setFuncionarioSexo(funcionario.getFuncionarioSexo());
				modelFuncionario.setFuncionarioEmail(funcionario.getFuncionarioEmail());
				modelFuncionario.setFuncionarioProfissao(funcionario.getFuncionarioProfissao());
			
			}
		}
		
		return modelFuncionario;
	}
	
}












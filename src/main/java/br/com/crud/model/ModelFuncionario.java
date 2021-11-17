package br.com.crud.model;

public class ModelFuncionario {

	private Long funcionarioId = 0l;
	private String funcionarioNome;
	private Integer funcionarioIdade;
	private String funcionarioSexo;
	private String funcionarioEmail;
	private String funcionarioProfissao;
	
	public ModelFuncionario() {
		// TODO Auto-generated constructor stub
	}
	
	public ModelFuncionario(String funcionarioNome, Integer funcionarioIdade, String funcionarioSexo,
			String funcionarioEmail, String funcionarioProfissao) {
		
		this.funcionarioNome = funcionarioNome;
		this.funcionarioIdade = funcionarioIdade;
		this.funcionarioSexo = funcionarioSexo;
		this.funcionarioEmail = funcionarioEmail;
		this.funcionarioProfissao = funcionarioProfissao;
	}


	public boolean isNovo() {
		
		if (this.funcionarioId == null) {
			return true; /*Inserir novo*/
		}else if (this.funcionarioId != null && this.funcionarioId > 0) {
			return false; /*Atualizar*/
		}
		return funcionarioId == null;
	}
	
	

	public Long getFuncionarioId() {
		return funcionarioId;
	}

	public void setFuncionarioId(Long funcionarioId) {
		this.funcionarioId = funcionarioId;
	}

	public String getFuncionarioNome() {
		return funcionarioNome;
	}
	public void setFuncionarioNome(String funcionarioNome) {
		this.funcionarioNome = funcionarioNome;
	}
	public Integer getFuncionarioIdade() {
		return funcionarioIdade;
	}
	public void setFuncionarioIdade(Integer funcionarioIdade) {
		this.funcionarioIdade = funcionarioIdade;
	}
	public String getFuncionarioSexo() {
		return funcionarioSexo;
	}
	public void setFuncionarioSexo(String funcionarioSexo) {
		this.funcionarioSexo = funcionarioSexo;
	}
	public String getFuncionarioEmail() {
		return funcionarioEmail;
	}
	public void setFuncionarioEmail(String funcionarioEmail) {
		this.funcionarioEmail = funcionarioEmail;
	}
	public String getFuncionarioProfissao() {
		return funcionarioProfissao;
	}
	public void setFuncionarioProfissao(String funcionarioProfissao) {
		this.funcionarioProfissao = funcionarioProfissao;
	}
	
	
}

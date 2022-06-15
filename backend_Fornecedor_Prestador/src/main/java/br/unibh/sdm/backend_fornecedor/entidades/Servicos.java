package br.unibh.sdm.backend_fornecedor.entidades;

public enum Servicos {
	
	PINTOR("PINTOR"),
	JARDINEIRO("jARDINEIRO"),
	GESSEIRO("GESSEIRO"),
	ELETRICISTA("ELETRICISTA"),
	DIARISTA("DIARISTA"),
	BOMBEIRO_HIDRAULICO("BOMBEIRO HIDRÁULICO"),
	PEDREIRO("PEDREIRO");
	
	
	private String servico;

	Servicos(String servico){
		this.servico = servico;
	}

	public String getServico() {
		return servico;
	}

	
	

}
package utility;

public class Atributos {
	
	private String nomeDir;  //nome do diretorio
	private String uid;  //de quem é o arquivo
	private String modo; //leitura, escrita, execução
	private String dataCriacao; // quando arquivo foi criado 
    private String ultimoAcesso; // tempo do ultimo acesso;
    private String tempoExclusao;// tempo quando foi criado
    private String diretorioPai; // quando diretorio é pai
    private String tipo; //diretorio ou arquivo
   
    private String conteudo; // texto a ser escrito
	
    
    
    
    public Atributos(String nomeDir, String uid, String modo, 
			String dataCriacao, String ultimoAcesso, String tempoExclusao,
			String diretorioPai, String tipo,  String conteudo) {
		super();
		this.nomeDir = nomeDir;
		this.uid = uid;
		this.modo = modo;
		this.dataCriacao = dataCriacao;
		this.ultimoAcesso = ultimoAcesso;
		this.tempoExclusao = tempoExclusao;
		this.diretorioPai = diretorioPai;
		this.tipo = tipo;
		
		this.conteudo = conteudo;
	}
    
    
	public String getNomeDir() {
		return nomeDir;
	}
	public void setNomeDir(String nomeDir) {
		this.nomeDir = nomeDir;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getModo() {
		return modo;
	}
	public void setModo(String modo) {
		this.modo = modo;
	}
	
	public String getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(String dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public String getUltimoAcesso() {
		return ultimoAcesso;
	}
	public void setUltimoAcesso(String ultimoAcesso) {
		this.ultimoAcesso = ultimoAcesso;
	}
	public String getTempoExclusao() {
		return tempoExclusao;
	}
	public void setTempoExclusao(String tempoExclusao) {
		this.tempoExclusao = tempoExclusao;
	}
	public String getDiretorioPai() {
		return diretorioPai;
	}
	public void setDiretorioPai(String diretorioPai) {
		this.diretorioPai = diretorioPai;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
    
  
    
	
}

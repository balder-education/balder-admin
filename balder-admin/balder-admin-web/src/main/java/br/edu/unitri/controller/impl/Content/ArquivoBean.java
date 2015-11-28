/**
 * 
 */
package br.edu.unitri.controller.impl.Content;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.edu.unitri.controller.UtilBeanFaces;
import br.edu.unitri.dao.impl.Content.ArquivoDao;
import br.edu.unitri.model.Content.Arquivo;
import br.edu.unitri.repository.CrudBean;
import br.edu.unitri.util.FileUtil;

/**
 * @author marcos.fernando
 *
 */
@Named
@SessionScoped
public class ArquivoBean implements CrudBean, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Arquivo arquivo = new Arquivo();
	private Arquivo arquivoSel;
	private List<Arquivo> listArquivos = new ArrayList<Arquivo>();

	@Inject
	private ArquivoDao arquivoDao;

	public ArquivoBean() {
		super();
	}

	public Arquivo getArquivo() {
		return arquivo;
	}

	public void setArquivo(Arquivo arquivo) {
		this.arquivo = arquivo;
	}

	public Arquivo getArquivoSel() {
		return arquivoSel;
	}

	public void setArquivoSel(Arquivo arquivoSel) {
		this.arquivoSel = arquivoSel;
	}

	public List<Arquivo> getListArquivos() {
		return listArquivos;
	}

	public void setListArquivos(List<Arquivo> listArquivos) {
		this.listArquivos = listArquivos;
	}

	@PostConstruct
	public void init() {
		arquivo = new Arquivo();
		listarTodos();
	}
	
	private void moveFiles(){
		File diretorioOrigem = new File(UtilBeanFaces.getFileTempResource());
		File diretorioDestino = new File(UtilBeanFaces.getFilesResource());
		
        FileUtil fileUtil = new FileUtil(1024);

        File[] arquivos = null;       
        File arquivo = null;
        String filename = null;
        
        try { 
        	if (diretorioOrigem.isDirectory()) 
        	{       
		      arquivos = diretorioOrigem.listFiles();
		      for (int i = 0; i < arquivos.length; i++) 
		      {
		    	 arquivo = arquivos[i];
		    	 filename = arquivo.getName();
		    	 fileUtil.copyFile(new File(diretorioOrigem.getAbsolutePath().concat("//").concat(filename)),
		    			           new File(diretorioDestino.getAbsolutePath().concat("//").concat(filename)));
		    	 fileUtil.remover(new File(diretorioOrigem.getAbsolutePath().concat("//").concat(filename)));		    	 
		      }
        	}
         }
         catch (FileNotFoundException fnex) {       
		    fnex.printStackTrace();       
		 } 
         catch (IOException ioex) {       
		    ioex.printStackTrace();       
		 }     
	}
	

	@Override
	public void salvar() {
		if (arquivo.getId() != null) {
			arquivoDao.update(arquivo);
		} else {
			arquivoDao.save(arquivo);
		}
		moveFiles();
		UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
		init();
	}

	@Override
	public void buscar() {
		listArquivos.clear();
		listArquivos = arquivoDao.findByEntity(arquivo);
		if (listArquivos.size() == 0) {
			UtilBeanFaces.addMessage("Não existem arquivos para serem exibidos", null, "WARNING", null);
		}
	}

	@Override
	public void excluir() {
		if (arquivoSel != null) {
			File diretorioDestino = new File(UtilBeanFaces.getFilesResource());
			String fileName = arquivoSel.getNomeImagem().concat(arquivoSel.getExtensao());
			new FileUtil().remover(new File(diretorioDestino.getAbsolutePath().concat("//").concat(fileName)));
			arquivoDao.deleteEntity(arquivoSel);
			init();
		}
		UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
	}

	@Override
	public void editar() {
		setArquivo(getArquivoSel());
	}

	@Override
	public void limpar() {
		init();
	}

	@Override
	public void listarTodos() {
		listArquivos = arquivoDao.findAll();
	}
	
	public void doUpload(FileUploadEvent fileUploadEvent) {

		UploadedFile uploadedFile = fileUploadEvent.getFile();

		String fileName = fileUploadEvent.getFile().getFileName();
		File localFile = new File(fileName);
		String[] variaveis = localFile.getName().split("[.]");

		String extensao = variaveis[1];
		String fileIn = variaveis[0];

		Long fileSizeOriginal = uploadedFile.getSize();

		String fileInDestino = UtilBeanFaces.getFileTempResource() + "//" + fileIn + "." + extensao;
		FileUtil fileUtil = new FileUtil(fileSizeOriginal.intValue());
		try {
			if (fileUtil.copiaArquivo(uploadedFile.getInputstream(), fileInDestino)){
				arquivo.setExtensao(extensao);
				arquivo.setNomeImagem(fileName);
				arquivo.setTamanho(fileSizeOriginal);
				UtilBeanFaces.addMessage("Arquivo enviado com sucesso","fileArq","INFO",null);
				
			};
		} catch (IOException e) {
			UtilBeanFaces.addMessage("Erro de upload do arquivo","fileArq","ERRO",e.getMessage());
		}
	}


}

package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
public class Comment extends Model {
	@Id
	@GeneratedValue
	private Integer id;
	
	public static final Finder<Integer, Comment> finder = new Finder<Integer, Comment>(
			Integer.class, Comment.class);
	
	private String conteudo;
	
	public Comment(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

}

package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import play.db.ebean.Model;

@Entity
public class Post extends Model{
	@Id
	@GeneratedValue
	private Integer id;
	
	private String conteudo;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Comment> cometarios = new ArrayList<Comment>();
	
	public static final Finder<Integer, Post> finder = new Finder<Integer, Post>(
			Integer.class, Post.class);
	
	public Post(){}

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

	public List<Comment> getCometarios() {
		return cometarios;
	}

	public void setCometarios(List<Comment> cometarios) {
		this.cometarios = cometarios;
	}

	
}

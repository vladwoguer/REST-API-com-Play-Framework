package controllers;

import java.util.List;
import java.util.Map;

import models.Comment;
import models.Post;

import play.*;
import play.libs.Json;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public static Result addPost(){
    	Map<String, String[]> params = Controller.request().body()
				.asFormUrlEncoded();

		String[] msgs = params.get("post");
		
		if(msgs != null){
			for(String msg:msgs){
				Post postagem = new Post();
				postagem.setConteudo(msg);
				postagem.save();
			}
		}
    	return ok("Post salvo com sucesso");
    }
    
    public static Result getPost(Integer id) {
		Post postagem = Post.finder.byId(id);
		if (postagem == null) {
			return notFound("Desculpe Postagem não encotrada");
		}
		
		return ok(Json.toJson(postagem));
	}
    
    public static Result getAllComments(Integer id) {
		Post postagem = Post.finder.byId(id);
		if (postagem == null) {
			return notFound("Desculpe Cometários não encontrados");
		}
		
		return ok(Json.toJson(postagem.getCometarios()));
	}
    
    public static Result addComment(Integer id) {
		Post postagem = Post.finder.byId(id);
		if (postagem == null) {
			return notFound("Desculpe postagem não encontrada");
		}
		Map<String, String[]> params = Controller.request().body()
				.asFormUrlEncoded();

		String[] msgs = params.get("comment");
		
		if(msgs != null){
			for(String msg:msgs){
				Comment comentario = new Comment();
				comentario.setConteudo(msg);
				postagem.getCometarios().add(comentario);
				postagem.save();
			}
		}
		return ok("Comentário adicionado com sucesso");
	}
    
    public static Result editPost(Integer id) {
		Post postagem = Post.finder.byId(id);
		if (postagem == null) {
			return notFound("Desculpe Postagem não encotrada");
		}
		
		Map<String, String[]> params = Controller.request().body()
				.asFormUrlEncoded();

		String[] msgs = params.get("post");
		
		if(msgs != null){
			postagem.setConteudo(msgs[0]);
			postagem.save();
		}
		
		return ok("Post editado com sucesso");
	}
    
    public static Result deletePost(Integer id) {
 		Post postagem = Post.finder.byId(id);
 		if (postagem == null) {
 			return notFound("Desculpe Postagem não encotrada");
 		}
 		postagem.delete();
 		return ok("Post Deletado");
 	}
    
    public static Result getAllPosts() {
 		List<Post> postagens = Post.finder.findList();
 		if (postagens == null) {
 			return notFound("Nada foi postado ainda");
 		}
 		
 		return ok(Json.toJson(postagens));
 	}
}

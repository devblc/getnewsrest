package com.bluet.bring.getnewsrest.enviroment;

public class EnviromentMessages {


	private String language;
	
	/**
	 * 
	 * @param language: setup language for messages returns.
	 */
	public EnviromentMessages(String language) {
		this.language = language;
	}
	
	
	public String ProfileNotFound() {
		switch (language) {
		case "pt-br":
			return "Perfil não Cadastrado";
		default:
			break;
		}
		return "Profile Not Found";
	}
	
	
}

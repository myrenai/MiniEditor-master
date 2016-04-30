package fr.istic.m1.aco.miniediteur.v3.receiver;

public class PressPapier {
	private String cpp = "";

	public String getCpp() {
		return cpp;
	}

	public void setCpp(String cpp) {
		this.cpp = cpp;
	}

	@Override
	public String toString() {
		return cpp;
	}
	
	public PressPapier(){}
	private PressPapier(String cpp){
		this.cpp = cpp;
	}
	public PressPapier clone(){
		return new PressPapier(cpp);
	}
	
}

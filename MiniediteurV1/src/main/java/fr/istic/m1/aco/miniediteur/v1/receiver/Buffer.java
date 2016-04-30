package fr.istic.m1.aco.miniediteur.v1.receiver;


public class Buffer{
	private StringBuffer zoneT;
	public Buffer(){
		zoneT = new StringBuffer();
	}
	
	public void insert(int indexDebut, String clipBoard){
		zoneT.insert(indexDebut, clipBoard);
	}
	
	public void appendBlank(){
		zoneT.append(" ");
	}
	
	public void delete(int debut, int fin){
		zoneT.delete(debut, fin);		
	}
	
	public void deleteAll(){
		zoneT.delete(0, zoneT.length());
	}
	
	public String getContent(int debut, int fin){
		return zoneT.substring(debut, fin);
	}
	
	public String getContent(){
		return zoneT.toString();
	}
	
	public int getContentLength(){
		return zoneT.length();
	}
	
}

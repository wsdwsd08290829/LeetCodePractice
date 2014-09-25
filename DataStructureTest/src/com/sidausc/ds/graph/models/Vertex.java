package com.sidausc.ds.graph.models;

public class Vertex {
	private String id;	
	private String name;
	
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}

	public Vertex(String i){
		id=i;
	}
	public Vertex(String i, String n){
		this(i);
		name = n;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.id;
	}
	
	 @Override
	  public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((id == null) ? 0 : id.hashCode());
	    return result;
	  }
	 @Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		 if(!(obj instanceof Vertex)) return false;
		 if(this.id.equals(((Vertex)obj).id))return true;
		 else return false;
	}
}

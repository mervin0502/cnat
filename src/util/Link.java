package util;

import java.util.LinkedList;

/**
 * 表示一条链路
 * 
 * @author mervin
 *
 */
public class Link {
	private Number startNodeId = null;
	private Number endNodeId = null;
	LinkedList<Number> edges = null;
	
	/*************************************************************************
	 * 
	 */
	/*************************************************************************
	/**
	 * 初始化
	 */
	public Link(){
		
	}
	public Link(Number startNodeId, Number endNodeId){
		this.startNodeId = startNodeId;
		this.endNodeId = endNodeId;
		this.edges =  new LinkedList<Number>();
	}
	public Link(Number startNodeId, Number endNodeId, LinkedList<Number> edges){
		this.startNodeId = startNodeId;
		this.endNodeId = endNodeId;
		this.edges = edges;
	}
	
	public void setStartNodeId(Number nodeId){
		this.startNodeId = nodeId;
	}
	public void setEndNodeId(Number nodeId){
		this.endNodeId = nodeId;
	}
	public void setEdges(LinkedList<Number> edges){
		this.edges = edges;
	}
	
	public Number getStartNodeId(){
		return this.startNodeId;
	}
	public Number getEndNodeId(){
		return this.endNodeId;
	}
	public LinkedList<Number> getEdges(){
		return this.edges;
	}
	/*************************************************************************
	 * 
	 */
	/*************************************************************************/
	public void add(Number nodeId){
		this.edges.add(nodeId);
	}
	public void remove(Number nodeId){
		this.edges.remove(nodeId);
	}
	public void clear(){
		this.edges.clear();
	}
	public boolean contain(Number nodeId){
		return this.edges.contains(nodeId);
	}
	/*************************************************************************
	 * 
	 */
	/*************************************************************************/
   @Override  
    public String toString() {  
	   	 return this.startNodeId + "\t" + this.endNodeId+"\t"+this.edges.toString();
    }  
  
    @Override  
    public int hashCode() {  
        final int prime = 31;  
        int result = 1;  
        result = prime * result + this.startNodeId.hashCode();  
        result = prime * result + this.endNodeId.hashCode();  
        return result;  
    }  
  
    @Override  
    public boolean equals(Object obj) {  
        if (this == obj)  
            return true;  
        if (obj == null)  
            return false;  
        if (getClass() != obj.getClass())  
            return false;  
        Link other = (Link) obj; 
        //去掉左右端点相同的边
        if (other.startNodeId == other.endNodeId)  
            return false; 
        if (!this.startNodeId.equals(other.startNodeId))  
            return false;  
        if (!this.endNodeId.equals(other.endNodeId))  
            return false; 
        //如果是无向的，去掉左右相同的
       return true; 	
    }
	
	
}

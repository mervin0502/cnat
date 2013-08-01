/*****************************************************************************
 * 
 * Copyright [2013] [Mervin.Wong]
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 *       
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *  
 *****************************************************************************/
package core;

import java.util.LinkedList;

/**
 * Node.java
 * 
 *@author 王进法<Mervin.Wong>
 *@version 
 *@Date 2013-1-17上午11:56:15
 */
/*********************************************************************************
 *
 * 网络的节点类：网络ID，网络权重，网络的邻接点
 *
 **********************************************************************************/

public class Node {
	private Number nodeId = 0;//网络的ID
	private float nodeWeight = 0;//在网络中点的权重
	private LinkedList<Edge> adjNodes = null;//邻接点
	
	//初始化
	public Node(){
		super();
	}
	public Node(Number nodeId){
		this.nodeId = nodeId;
	}
	public Node(Number nodeId, int nodeWeight){
		this.nodeId = nodeId;
		this.nodeWeight = nodeWeight;
	}
	public Node(Number nodeId, int nodeWeight, LinkedList<Edge> adjNodes){
		this.nodeId = nodeId;
		this.nodeWeight = nodeWeight;
		this.adjNodes = adjNodes;
	}
	
	//SET GET
	public void setNodeId(Number nodeId){
		this.nodeId = nodeId;
	}
	public void setNodeWeight(float nodeWeight){
		this.nodeWeight = nodeWeight;
	}
	public void setAdjNodes(LinkedList<Edge> adjNodes){
		this.adjNodes = adjNodes;
	}
	
	public Number getNodeId(){
		return this.nodeId;
	}
	public float getNodeWeight(){
		return this.nodeWeight;
	}
	public LinkedList<Edge> getAdjNodes(){
		return this.adjNodes;
	}
	
	/**
	 * 
	 */
	public String toString(){
		return this.nodeId.toString()+"##"+this.nodeWeight;
	}
	/**
	 * 
	 */
	public int hashCode(){
/*        final int prime = 31;  
        int result = 1;  
        result = prime * result + this.nodeId.hashCode();  
        result = prime * result + this.nodeWeightt;  
        //result = prime * result + (this.nodeAdjNodes == null?0:this.nodeAdjNodes.hashCode());  
        return result; 	*/	
		return this.nodeId.hashCode();
	}
	/**
	 * 
	 */
	public boolean equals(Object obj){
        if (this == obj)  
            return true;  
        if (obj == null)  
            return false;  
        if (getClass() != obj.getClass())  
            return false;  
        Node other = (Node) obj; 
        if(!this.nodeId.equals(other.nodeId))
        	return false;
		return true;
	}
	
	public static void main(String[] args){

	}
}

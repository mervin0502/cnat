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

/**
 * Edge.java
 * 
 *@author 王进法<Mervin.Wong>
 *@version 
 *@Date 2013-1-17下午12:50:47
 */
/*********************************************************************************
 *
 * 网络拓扑中的边类
 *
 **********************************************************************************/

class Edge {
	//private Number adjNodeId = 0;//邻接点的ID
	private Node preNode = null;//源节点
	private Node postNode = null;//邻接点
	private float edgeWeight = 0;//边的权重
	
	//初始化
	public Edge(){
		super();
	}
	public Edge(Node preNode, Node postNode){
		this.preNode = preNode;
		this.postNode = postNode;
	}
	public Edge(Node preNode, Node postNode, int edgeWeight){
		this.preNode = preNode;
		this.postNode = postNode;
		this.edgeWeight = edgeWeight;
	}
	
	// GET SET
	public void setNode(Node preNode){
		this.preNode = preNode;
	}
	public void setAdjNode(Node postNode){
		this.postNode = postNode;
	}
	public void setEdgeWeight(float edgeWeight){
		this.edgeWeight = edgeWeight;
	}
	public Node getNode(){
		return this.preNode;
	}
	public Number getNodeId(){
		return this.preNode.getNodeId();
	}
	public Node getAdjNode(){
		return this.postNode;
	}
	public Number getAdjNodeId(){
		return this.postNode.getNodeId();
	}
	public float getEdgeWeight(){
		return this.edgeWeight;
	}
	
	/**
	 * @Override
	 */
	public String toString(){
		return this.postNode.getNodeId()+"##"+this.edgeWeight;
	}
	/**
	 * @Override
	 */
	public int hashCode(){
		return this.postNode.hashCode();
	}
	/**
	 * @Override
	 */
	public boolean equals(Object obj){
        if (this == obj)  
            return true;  
        if (obj == null)  
            return false;  
        if (getClass() != obj.getClass())  
            return false;  
        Edge other = (Edge) obj; 
        if(!this.preNode.equals(other.preNode) && !this.postNode.equals(other.postNode))
        	return false;
		return true;
	}
	
}

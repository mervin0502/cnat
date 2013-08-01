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
package feature.interfaces;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

import core.Network;

/**
 * InterfacePath.java
 * 
 *@author 王进法<Mervin.Wong>
 *@version 0.4
 */
/*
 * *************************************************************************************
 * 1, int[][] internodePath(preNodeId, postNodeId)//节点间的路径,多条路径
 * 2, int internodePathLength(preNodeId, postNodeId)//节点间的路径长度：最短路径边数
 * 3, float netAvgPathLength(network net)//网络平均路径长度
 ***************************************************************************************/
public interface InterfacePath {
	/**
	 *  bothNodesPath
	 *  计算两个节点间的路径
	 * @param preNodeId
	 * @param postNodeId
	 * @return ArrayList<ArrayList<Number>> path 保存节点的ID
	 */
	public ArrayList<Stack<Number>> bothNodesPath(Number preNodeId, Number postNodeId);
	
	/**
	 *  
	 *  计算两个节点间的路径
	 * @param net
	 * @param preNodeId
	 * @param postNodeId
	 * @return ArrayList<Stack<Number>> path 保存节点的ID
	 */
	public ArrayList<Stack<Number>> bothNodesPath(Network net,  Number preNodeId, Number postNodeId);
	
	
	/**
	 *  
	 *  节点间的路径长度
	 * @param preNodeId
	 * @param postNodeId
	 * @return int 
	 */
	public int bothNodesPathLength(Number preNodeId, Number postNodeId);
	
	/**
	 *  
	 *  节点间的路径长度
	 * @param net
	 * @param preNodeId
	 * @param postNodeId
	 * @return int 
	 */
	public int bothNodesPathLength(Network net,  Number preNodeId, Number postNodeId);
	
	/**
	 *  
	 *  两点之间的最短路径
	 * @param preNodeId
	 * @param postNodeId
	 * @return LinkedList<Number>
	 */
	public LinkedList<Number> bothNodesShortestPath(Number preNodeId, Number postNodeId);
	
	/**
	 *  
	 *  两点之间的最短路径
	 * @param net
	 * @param preNodeId
	 * @param postNodeId
	 * @return  LinkedList<Number>
	 */
	public LinkedList<Number> bothNodesShortestPath(Network net, Number preNodeId, Number postNodeId);
	

	/**
	 *  
	 *  网络直径
	 * @return int
	 */
	public int netDiameter();
	

	/**
	 *  
	 *  网络直径
	 * @param net
	 * @return int
	 */
	public int netDiameter(Network net);
	
	/**
	 *   
	 *  网络的平均路径长度
	 * @return double
	 */
	public double netAvgPathLength();
	
	/**
	 * 
	 *  网络的平均路径长度
	 * @param net
	 * @return double
	 */
	public double netAvgPathLength(Network net);
}

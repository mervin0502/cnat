/*********************************************************************************
 *
 *
 *
 **********************************************************************************/
package feature.interfaces;

import java.util.HashMap;

import util.Pair;
import util.PairMap;

import core.Network;

/**
 * InterfaceBetweenness.java
 * 
 *@author 王进法<Mervin.Wong>
 *@version 
 *@Date 2013-1-12下午5:28:50
 */
/*********************************************************************************
 *
 * 10, int nodeBetweenness(node)//节点的介数
 * 11, int edgeBetweenness(preNode, postNode) //边的介数
 *
 **********************************************************************************/

public interface InterfaceBetweenness {

/*	*//**
	 *  nodeBetweenness
	 *  节点的介数
	 * @param nodeId
	 * @return int betweenness
	 *//*
	public int nodeBetweenness( Number nodeId);
	
	*//**
	 *  nodeBetweenness
	 *  节点的介数
	 * @param Network net
	 * @param nodeId
	 * @return int betweenness
	 *//*
	public int nodeBetweenness(Network net, Number nodeId);
	
	*//**
	 *  nodesBetweenness
	 *  节点的介数
	 * @param HashSet<Number>nodesId
	 * @return HashMap<Number, Iteger> betweenness
	 *//*
	public HashMap<Number, Integer> nodesBetweenness( HashSet<Number> nodesId);
	
	*//**
	 *  nodesBetweenness
	 *  节点的介数
	 * @param Network net
	 * @param HashSet<Number>nodesId
	 * @return HashMap<Number, Integer> betweenness
	 *//*
	public HashMap<Number, Integer> nodesBetweenness(Network net, HashSet<Number> nodesId);
	
	*//**
	 *  edgeBetweenness
	 *  网络中一条边的介数
	 * @param preNodeId
	 * @param postNodeId
	 * @return int betweenness
	 *//*
	public int edgeBetweenness(Number preNodeId, Number postNodeId);
	
	*//**
	 *  edgeBetweenness
	 *  网络中一条边的介数
	 * @param net
	 * @param preNodeId
	 * @param postNodeId
	 * @return int betweenness
	 *//*
	public int edgeBetweenness(Network net, Number preNodeId, Number postNodeId);
	*/
	/**
	 *  
	 *  网络中所有节点介数
	 * @param net
	 * @return HashMap<Number, Double>
	 */
	public HashMap<Number, Double> nodesBetweenness(Network net); 
	
	/**
	 *  
	 *  网络中所有边的介数
	 * @param net
	 * @return PairMap<Number, Double>
	 */
	public PairMap<Number, Double> edgesBetweenness(Network net); 
}

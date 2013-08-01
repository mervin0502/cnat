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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import core.Network;

/**
 * InterfaceDegree.java
 * 
 *@author 王进法<Mervin.Wong>
 *@version 
 *@Date 2013-1-12下午5:01:06
 */
/*********************************************************************************
 * 4, int nodeDegree(nodeId)//节点的度
 * 5, int nodeInDegree(nodeId)//节点的入度，有向网
 * 6, int nodeOutDegree(nodeId)//节点的出度，有向网
 * 7, HashMap<Number,Number> netDegreeDistribution(network net)//度分布
 ********************************************************************************/
public interface InterfaceDegree {

	/**
	 *  
	 *  单个节点的度
	 * @param nodeId
	 * @return int 
	 */
	public int nodeDegree(Number nodeId);
	
	/**
	 *  
	 *  单个节点的度
	 * @param net
	 * @param nodeId
	 * @return int 
	 */
	public int nodeDegree(Network net, Number nodeId);
	
	/**
	 *  
	 *  获取多个节点的度
	 * @param nodeIdSet
	 * @return HashMap<Number,Number> 
	 */
	public HashMap<Number,Number> nodeDegree(Set<Number> nodeIdSet);
	
	/**
	 *  
	 *  获取多个节点的度
	 * @param net
	 * @param nodeIdSet
	 * @return HashMap<Number,Number> 
	 */
	public HashMap<Number, Number> nodeDegree(Network net, Set<Number> nodeIdSet);
	
	/**
	 *  
	 *  节点的入度
	 * @param nodeId
	 * @return int 
	 */
	public int nodeInDegree(Number nodeId);
	
	/**
	 *  
	 *  节点的入度
	 * @param net
	 * @param nodeId
	 * @return int 
	 */
	public int nodeInDegree(Network net, Number nodeId);
	
	/**
	 *  
	 *  多个节点的入度
	 * @param nodeIdSet
	 * @return HashMap<Number,Number> 
	 */
	public HashMap<Number,Number> nodeInDegree(HashSet<Number> nodeIdSet); 
	
	/**
	 *  
	 *  多个节点的入度
	 * @param net
	 * @param nodeIdSet
	 * @return HashMap<Number,Number> 
	 */
	public HashMap<Number,Number> nodeInDegree(Network net, HashSet<Number> nodeIdSet); 
	
	/**
	 *  
	 *  单个节点的出度
	 * @param nodeId
	 * @return int
	 */
	public int nodeOutDegree(Number nodeId);
	
	/**
	 * 
	 *  单个节点的出度
	 * @param net
	 * @param nodeId
	 * @return int
	 */
	public int nodeOutDegree(Network net, Number nodeId);
	
	/**
	 *  
	 *  多个节点的出度
	 * @param nodeIdSet
	 * @return HashMap<Number,Number> 
	 */
	public HashMap<Number,Number> nodeOutDegree(HashSet<Number> nodeIdSet); 
	
	/**
	 *  
	 *  多个节点的出度
	 * @param net
	 * @param nodeIdSet
	 * @return HashMap<Number,Number> 
	 */
	public HashMap<Number,Number> nodeOutDegree(Network net, HashSet<Number> nodeIdSet); 

	/**
	 *  
	 *  邻接点的度
	 * @param nodeId
	 * @return HashMap<Number, Number>
	 */
	public HashMap<Number, Number> nodeAdjDegree(Number nodeId);
	
	/**
	 *  
	 *  邻接点的度
	 * @param net
	 * @param nodeId
	 * @return HashMap<Number, Number>
	 */
	public HashMap<Number, Number> nodeAdjDegree(Network net, Number nodeId);
	/**
	 *  
	 *  多个节点的邻接点的度
	 * @param nodeIdSet
	 * @return HashMap<Number, Number>
	 */
	public HashMap<Number, Number> nodeAdjDegree(Set<Number> nodeIdSet);
	
	/**
	 *  
	 *  多个节点的邻接点的度
	 * @param net
	 * @param nodeIdSet
	 * @return HashMap<Number, Number>
	 */
	public HashMap<Number, Number> nodeAdjDegree(Network net, Set<Number> nodeIdSet);

	/**
	 * 
	 *  获取度为degree的节点集
	 *  @param degree 度值
	 *  @return Set<Number>
	 */
	public Set<Number> getNodeIdByDegree(int degree);
	/**
	 * 
	 *  获取度为degree的节点集
	 *  @param net 网络
	 *  @param degree 度值
	 *  @return Set<Number>
	 */
	public Set<Number> getNodeIdByDegree(Network net, int degree);

	/**
	 * 
	 * 将网络按度的由小到大或由大到小排列，获取网络中，比例为ratio的节点数量 
	 *  @param ratio 获取节点数量的比例
	 *  @param des true:由大到小；false:由小到大
	 *  @return Set<Number>
	 */
	public Set<Number> getNodeIdByRatio(float ratio, boolean des);
	/**
	 * 
	 * 将网络按度的由小到大或由大到小排列，获取网络中，比例为ratio的节点数量 
	 * @param net 网络
	 *  @param ratio 获取节点数量的比例
	 *  @param des true:由大到小；false:由小到大
	 *  @return Set<Number>
	 */
	public Set<Number> getNodeIdByRatio(Network net, float ratio, boolean des);
	/**
	 *  
	 *  节点集合中度的最大值
	 * @return int
	 */
	public int nodeDegreeMax(HashMap<Number, Number> degreeMap);
	/**
	 *  
	 *  获取网络度的最大值
	 * @return int
	 */
	public int netDegreeMax();
	
	/**
	 *  
	 *  获取网络net的度最大值
	 * @param net
	 * @return int
	 */
	public int netDegreeMax(Network net);
	
	/**
	 *  
	 *  网络的度分布
	 * @return HashMap<Integer,Integer>
	 */
	public Map<Number, Number> netDegreeDistribution();
	
	/**
	 *  
	 *  网络的度分布
	 * @param net
	 * @return HashMap<Number, Number> 
	 */
	public Map<Number, Number> netDegreeDistribution(Network net);
	
	/**
	 * 网络的入度分布
	 *  
	 *  @return Map<Number, Number>
	 */
	public Map<Number, Number> netInDegreeDistribution();
	/**
	 * 
	 * 网络的入度分布 
	 *  @param net 网络
	 *  @return Map<Number, Number>
	 */
	public Map<Number, Number> netInDegreeDistribution(Network net);
	/**
	 * 网络的出度分布
	 *  
	 *  @return Map<Number, Number>
	 */
	public Map<Number, Number> netOutDegreeDistribution();
	/**
	 * 
	 * 网络的出度分布 
	 *  @param net 网络
	 *  @return Map<Number, Number>
	 */
	public Map<Number, Number> netOutDegreeDistribution(Network net);
	/**
	 *  
	 *  网络的度分布率
	 * @return HashMap<Number, Number> 
	 */
	public Map<Number, Number> netDegreeDistributionRatio();
	
	/**
	 *  
	 *  网络的度分布率
	 * @param net
	 * @return HashMap<Number, Number> 
	 */
	public Map<Number, Number> netDegreeDistributionRatio(Network net);
	
	/**
	 * 
	 *  网络的入度分布率
	 *  @return Map<Number, Number>
	 */
	public Map<Number, Number> netInDegreeDistributionRatio();
	/**
	 * 
	 * 网络的入度分布率 
	 *  @param net 网络
	 *  @return Map<Number, Number>
	 */
	public Map<Number, Number> netInDegreeDistributionRatio(Network net);
	/**
	 * 
	 *  网络的出度分布率
	 *  @return Map<Number, Number>
	 */
	public Map<Number, Number> netOutDegreeDistributionRatio();
	/**
	 * 
	 * 网络的出度分布率 
	 *  @param net 网络
	 *  @return Map<Number, Number>
	 */
	public Map<Number, Number> netOutDegreeDistributionRatio(Network net);
	/**
	 *  
	 *  网络度的平均值
	 * @return float
	 */
	public float netDegreeAvg();
	
	/**
	 *  
	 *  网络度的平均值
	 * @param net
	 * @return float
	 */
	public float netDegreeAvg(Network net);
}

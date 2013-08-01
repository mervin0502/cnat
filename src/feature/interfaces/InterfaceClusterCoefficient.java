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
 * InterfaceClusterCoefficient.java
 * 
 *@author 王进法<Mervin.Wong>
 *@version 
 *@Date 2013-1-12下午5:24:12
 */
/*********************************************************************************
 *
 * 8, float nodeClusterCoefficient(node) //节点的聚类系数
 * 9, float netClusterCoefficient(network net) //网络的聚类系数
 *
 **********************************************************************************/

public interface InterfaceClusterCoefficient {

	/**
	 *  nodeClusterCofficient
	 *  节点的聚类系数
	 * @param nodeId
	 * @return float clusterCofficient
	 */
	public double nodeClusterCofficient(Number nodeId);
	
	/**
	 *  
	 *  节点的聚类系数
	 * @param  net
	 * @param nodeId
	 * @return float 
	 */
	public double nodeClusterCofficient(Network net, Number nodeId);

	/**
	 *  
	 *  多个节点的聚类系数
	 * @param nodesId
	 * @return HashMap<Number, Number>
	 */
	public Map<Number, Number> nodesClusterCofficient(Set<Number> nodesId);	
	
	/**
	 *  
	 *  多个节点的聚类系数
	 * @param net
	 * @param nodesId
	 * @return HashMap<Number, Number>
	 */
	public Map<Number, Number> nodesClusterCofficient(Network net, Set<Number> nodesId);	

	/**
	 *  
	 *  网络的聚类系数
	 * @return double 
	 */
	public double netClusterCoefficient();
	
	/**
	 *  
	 *  网络的聚类系数
	 * @param net
	 * @return float 
	 */
	public float netClusterCoefficient(Network net);

	//Map<Number, Number> nodesClusterCofficient(Set<Number> nodesId);
}

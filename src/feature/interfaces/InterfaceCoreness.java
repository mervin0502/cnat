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
 *   
 *  网络核的统计
 * @author Mervin.Wong
 * @version 0.4
 */
public interface InterfaceCoreness {
	/**
	 *  
	 *  单个节点的核
	 * @param  nodeId
	 * @return int
	 */
	public int nodeCore(Number nodeId);
	/**
	 *  
	 *  单个节点的核
	 * @param  net
	 * @param  nodeId
	 * @return int
	 */
	public int nodeCore(Network net, Number nodeId);
	/**
	 *  
	 *  多个节点的核
	 * @param  nodesId
	 * @return HashMap<Number, Integer>
	 */
	public Map<Number, Number>  nodeCore(Set<Number> nodesId);
	/**
	 *  
	 *  多个节点的核
	 * @param  net
	 * @param  nodesId
	 * @return HashMap<Number, Integer>
	 */
	public Map<Number, Number>  nodeCore(Network net, Set<Number> nodesId); 	
	
	/**
	 * 节点的邻接点的核
	 *  @param nodeId
	 *  @return Map<Number, Number>
	 */
	public Map<Number, Number> adjNodeCore(Number nodeId);
	/**
	 * 
	 *  节点的邻接点的核
	 *  @param net
	 *  @param nodeId
	 *  @return Map<Number, Number>
	 */
	public Map<Number, Number> adjNodeCore(Network net, Number nodeId);
	/**
	 * 
	 *  多个节点的邻接点的核
	 *  @param nodeIdSet
	 *  @return Map<Number, Number>
	 */
	public Map<Number, Number> adjNodeCore(Set<Number> nodeIdSet);
	/**
	 * 
	 * 多个节点的邻接点的核 
	 *  @param net
	 *  @param nodeIdSet
	 *  @return Map<Number, Number>
	 */
	public Map<Number, Number> adjNodeCore(Network net, Set<Number> nodeIdSet);
	
	/**
	 *  
	 *  网络的核:k-core
	 * @return int
	 */
	public int netCore();
	/**
	 *  
	 *  网络的核:k-core
	 * @param  net 网络
	 * @return int
	 */
	public int netCore(Network net);
	
	/**
	 *  kCoreNet
	 *  k核网络
	 * @param  k
	 * @return Network
	 */
	public Network kCoreNet(int k);
	
	/**
	 *  kCoreNet
	 *  k核网络
	 * @param  net
	 * @param  k
	 * @return Network
	 */
	public Network kCoreNet(Network net, int k);
}

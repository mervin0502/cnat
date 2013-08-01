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

import java.util.HashSet;
import java.util.Set;

import core.Network;

/**
 * InterfaceMultiNet.java
 * 
 *@author 王进法<Mervin.Wong>
 *@version 0.4
 */
public interface InterfaceMultiNet {

	/**
	 *  intersectionByNode
	 *  获取两个网络的节点交集
	 * @return Set<Number> 
	 */
	public Set<Number> intersectionByNode();
	
	/**
	 *  intersectionByNode
	 *  获取两个网络的节点交集
	 * @param  preNet
	 * @param  postNet
	 * @return Set<Number> 
	 */
	public Set<Number> intersectionByNode(Network preNet, Network postNet);
	
	/**
	 *  birthNodes
	 *  the birth nodes in postNet
	 * @return Set<Number>
	 */
	public Set<Number> birthNodes();
	
	/**
	 *  birthNodes
	 *  the birth nodes in postNet
	 * @param preNet
	 * @param postNet
	 * @return Set<Number>
	 */
	public Set<Number> birthNodes(Network preNet, Network postNet);
	
	/**
	 *  deathNodes
	 *  the disappear nodes in preNet
	 * @return Set<Number>
	 */
	public Set<Number> deathNodes();
	
	/**
	 *  deathNodes
	 *  the disappear nodes in preNet
	 * @param preNet
	 * @param postNet
	 * @return Set<Number>
	 */
	public Set<Number> deathNodes(Network preNet, Network postNet);
	/**
	 *  intersectionByEdge
	 *  获取两个网络的边交集，返回交集网络 
	 * @return Network net
	 */
	public Network intersectionByEdge();
	
	/**
	 *  
	 *  获取两个网络的边交集，返回交集网络 
	 * @param  preNet
	 * @param  postNet
	 * @return Network 
	 */
	public Network intersectionByEdge(Network preNet, Network postNet);
	
	/**
	 *  
	 *  消亡的内部边：A=》B  边的两个端点A和B均是两个网络的交集节点
	 * @return Network 
	 */
	public Network deathByInEdge();
	
	/**
	 *  
	 *  消亡的内部边：A=》B  边的两个端点A和B均是两个网络的交集节点
	 * @param  preNet
	 * @param  postNet
	 * @return Network 
	 */
	public Network deathByInEdge(Network preNet, Network postNet);	
	
	/**
	 *   
	 *   死亡的边界边，A=》B 边的两个端点A和B，其中A是消亡的节点，B是两个网络的交集节点
	 * @return Network 
	 */
	public Network deathByBoundaryEdge();
	
	/**
	 *  
	 *  死亡的边界边，A=》B 边的两个端点A和B，其中A是消亡的节点，B是两个网络的交集节点
	 * @param  preNet
	 * @param  postNet
	 * @return Network net
	 */
	public Network deathByBoundaryEdge(Network preNet, Network postNet);
		
	/**
	 *  
	 *  新生的内部边：A=》B  边的两个端点A和B均是两个网络的交集节点
	 * @return Network net
	 */
	public Network birthByInEdge();
	
	/**
	 *  
	 *  新生的内部边：A=》B  边的两个端点A和B均是两个网络的交集节点
	 * @param  preNet
	 * @param  postNet
	 * @return Network 
	 */
	public Network birthByInEdge(Network preNet, Network postNet);	

	/**
	 *  
	 *  新生的边界边：A=》B 边的两个端点A和B，其中A是新生的节点，B是两个网络的交集节点
	 * @return Network
	 */
	public Network birthByBoundaryEdge();
	
	/**
	 *  
	 *  新生的边界边：A=》B 边的两个端点A和B，其中A是新生的节点，B是两个网络的交集节点
	 * @param preNet
	 * @param postNet
	 * @return Network
	 */
	public Network birthByBoundaryEdge(Network preNet, Network postNet);
	
	/**
	 *  
	 *  新生的外部边：A=》B 边的两个端点A和B均是新生的节点
	 * @return Network
	 */
	public Network birthByOutEdge();
	
	/**
	 *  
	 *  新生的外部边：A=》B 边的两个端点A和B均是新生的节点
	 * @param preNet
	 * @param postNet
	 * @return Network
	 */
	public Network birthByOutEdge(Network preNet, Network postNet);
	
	/**
	 *  
	 *   网络连接率
	 * @return int 
	 */
	public int netConnectRate();
	
	/**
	 *  
	 *   网络连接率
	 * @param preNet
	 * @param postNet
	 * @return int 
	 */
	public int netConnectRate(Network preNet, Network postNet);
	/**
	 *  
	 *  网络吸引率
	 * @return int 
	 */
	public int netAttractRate();
	
	/**
	 *  
	 *  网络吸引率
	 * @param preNet
	 * @param postNet
	 * @return int 
	 */
	public int netAttractRate(Network preNet, Network postNet);
	
}

package feature;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import util.D;

import core.Network;
import core.Global.NetType;
import feature.interfaces.InterfaceCoreness;

public class Coreness implements InterfaceCoreness {
	private Network net = null;
	//private HashMap<Number, Integer> nodesDegreeMap = new HashMap<Number, Integer>();
	/**
	 * 构造函数
	 */
	public Coreness(){
		
	}
	public Coreness(Network net){
		this.net = net;
	}
	/**
	 *  nodeCore
	 *  单个节点的核
	 * @param  nodeId
	 * @return int
	 */
	public int nodeCore(Number nodeId){
		return this.nodeCore(this.net, nodeId);
	}
	/**
	 *  
	 *  单个节点的核
	 * @param  net
	 * @param  nodeId
	 * @return int
	 */
	public int nodeCore(Network net, Number nodeId){
		Network net3 = net.copyNet();
		Set<Number> nodeSet = net3.getAllNodeId();
		int i = 1;
		while(true){
			if(!nodeSet.contains(nodeId)){
				return i-2;
			}
			if(net3.nodeNum <= 0){
				D.s("节点："+nodeId+"不存在！");
				return 0;
			}
			delNode(net3, i);
			i++;
		}
	}
	/**
	 *  
	 *  多个节点的核
	 * @param  nodesId
	 * @return HashMap<Number, Integer>
	 */
	public Map<Number, Number>  nodeCore(Set<Number> nodesId){
		return this.nodeCore(this.net, nodesId);
	}
	/**
	 *  
	 *  多个节点的核
	 * @param  net
	 * @param  nodesId
	 * @return  HashMap<Number, Number>
	 */
	public Map<Number, Number>  nodeCore(Network net, Set<Number> nodesId){
		Set<Number> nodeIdSet = new HashSet<Number>();
		nodeIdSet.addAll(nodesId);
		
		Network net3 = net.copyNet();
		Set<Number> nodeSet = net3.getAllNodeId();
		Map<Number, Number> nodesCore = new HashMap<Number, Number>();
		Number nodeId = null;
		int i = 1;
		while(true){
			if(nodeIdSet.size() <= 0){
				break;
			}
			for (Iterator<Number> iterator = nodeIdSet.iterator(); iterator.hasNext();) {
				nodeId = (Number) iterator.next();
				if(!nodeSet.contains(nodeId)){
					nodesCore.put(nodeId, i-2);
					//nodeIdSet.remove(nodeId);
					iterator.remove();
				}
			}
			if(net3.nodeNum <= 0){
				D.s("节点：不存在！");
				break;
			}
			delNode(net3, i);
			i++;
		}
		return nodesCore;
	}
	/**
	 * 节点的邻接点的核
	 *  @param nodeId
	 *  @return Map<Number, Number>
	 */
	public Map<Number, Number> adjNodeCore(Number nodeId){
		return this.adjNodeCore(net, nodeId);
	}
	/**
	 * 
	 *  节点的邻接点的核
	 *  @param net
	 *  @param nodeId
	 *  @return Map<Number, Number>
	 */
	public Map<Number, Number> adjNodeCore(Network net, Number nodeId){
		Set<Number> adjNodeSet = net.getAdjNodeId(nodeId);
		return this.nodeCore(this.net, adjNodeSet);
	}
	/**
	 * 
	 *  多个节点的邻接点的核
	 *  @param nodeIdSet
	 *  @return Map<Number, Number>
	 */
	public Map<Number, Number> adjNodeCore(Set<Number> nodeIdSet){
		return this.adjNodeCore(this.net, nodeIdSet);
	}
	/**
	 * 
	 * 多个节点的邻接点的核 
	 *  @param net
	 *  @param nodeIdSet
	 *  @return Map<Number, Number>
	 */
	public Map<Number, Number> adjNodeCore(Network net, Set<Number> nodeIdSet){
		Set<Number> adjNodeSet = net.getAdjNodeId(nodeIdSet);
		return this.nodeCore(this.net, adjNodeSet);
	}
	/**
	 *  netCore
	 *  网络的核:k-core
	 * @return int
	 */
	public int netCore(){
		int k = 0, i = 1;
		Network net3 = this.net.copyNet();
		while(true){
			if(net3.nodeNum <= 0){
				k = i-1;
				break;
			}
			this.delNode(net3, i);
			i++;
		}
		return k;
	}
	/**
	 *  netCore
	 *  网络的核:k-core
	 * @param  net 网络
	 * @return int
	 */
	public int netCore(Network net){
		int k = 0, i = 1;
		Network net3 = net.copyNet();
		while(true){
			if(net3.nodeNum <= 0){
				k = i-1;
				break;
			}
			this.delNode(net3, i);
			i++;
		}
		return k;
	}
	
	/**
	 *  
	 *  k核网络
	 * @param  k
	 * @return Network
	 */
	public Network kCoreNet(int k){
		Network net3 = this.net.copyNet();
		delNode(net3, k);
		return net3;
	}
	
	/**
	 *  
	 *  k核网络
	 * @param  net
	 * @param  k
	 * @return Network
	 */
	public Network kCoreNet(Network net, int k){
		Network net3 = net.copyNet();
		delNode(net3, k);
		return net3;		
	}
	
	/*
	 *  
	 * @param 删除网络中所有度为k的节点
	 * @param Network netCopy this.net网络的副本
	 * @param k 
	 * @return HashSet<Number>
	 */
	private HashSet<Number> delNode(Network netCopy, int k){
		HashSet<Number> delNodeSet = new HashSet<Number>();
		Set<Number> nodeSet = new HashSet<Number>();
		nodeSet.addAll(netCopy.getAllNodeId());
		Number nodeId = null;
		int degreeNum = 0;
		boolean flag = false;
		Degree degree = new Degree(netCopy); 
		do{
			flag = false;
			for (Iterator<Number> iterator = nodeSet.iterator(); iterator.hasNext();) {
				nodeId = (Number) iterator.next();
				if(Network.netType.equals(NetType.DIRECTED)){
					degreeNum = degree.nodeInDegree(nodeId)+degree.nodeOutDegree(nodeId);
				}else{
					degreeNum = degree.nodeDegree(nodeId);
				}
				if(degreeNum <= k){
					flag = true;
					delNodeSet.add(nodeId);
					netCopy.deleteNode(nodeId);
					iterator.remove();
				}
			}			
		}while(flag && (nodeSet.size() > 0));
		return delNodeSet;
	}	
}

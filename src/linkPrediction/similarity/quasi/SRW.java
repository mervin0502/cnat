package linkPrediction.similarity.quasi;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import core.Network;
import core.Global.NetType;

import util.E;
import util.Pair;
import util.PairList;
import linkPrediction.similarity.Similarity;

public class SRW extends Similarity {

	public int t = 10;
	/**
	 * 构造函数
	 * @param net1 网络
	 */
	public SRW(Network net1) {
		super(net1);
		this.t = 10;
		// TODO Auto-generated constructor stub
	}
	/**
	 * 构造函数
	 * @param net1 网络
	 * @param t 参数
	 * @throws E 异常输出
	 */
	public SRW(Network net1, int t) throws E {
		super(net1);
		this.t = t;
		// TODO Auto-generated constructor stub
	}
	/**
	 * 构造函数
	 * @param net 网络
	 * @param k 存在边集合的划分个数
	 * @param t 参数
	 * @throws E 异常输出
	 */
	public SRW(Network net, int k, int t) throws E {
		super(net, k);
		this.t = t;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 *  通过算法对未知边赋值
	 *  @return Map<Pair<Number>, Double>
	 */
	@Override
	public Map<Pair<Number>, Double> run() {
		// TODO Auto-generated method stub
		Map<Pair<Number>, Double> score = new HashMap<Pair<Number>, Double>();//从x到y的概率值
		Map<Pair<Number>, Double> lScore1 = new HashMap<Pair<Number>, Double>();//从l到其他节点的概率值
		Map<Pair<Number>, Double> lScore2 = new HashMap<Pair<Number>, Double>();//从l到其他节点的概率值
		Network net2 = this.deleteProbeEdge();//删除探测集中的边后的网络
		Set<Number> allNodeIdSet = net2.getAllNodeId();//所有节点
		
		Pair<Number> p = null;
		Set<Number> adjNodeIdSet = null;
		//遍历所有的未知边，找到其概率值
		if(Network.netType.equals(NetType.INDIRECTED)){
			//无向 (l, r) == (r, l)
			for(Number nodeId1:allNodeIdSet){
				adjNodeIdSet = net2.getAdjNodeId(nodeId1);//nodeId1的邻接点
				
				lScore1.put(new Pair<Number>(nodeId1, nodeId1), (double) 1);
				this.walk(net2, this.t, 1, lScore1, lScore2);
				
				//从nodeId1出发，到达所有其他节点的概率
				for(Number nodeId2:allNodeIdSet){
					//nodeId1与nodeId2没有直接连边
					if(!nodeId2.equals(nodeId1) && !adjNodeIdSet.contains(nodeId2)){
						p = new Pair<Number>(nodeId1, nodeId2);
						if(lScore2.containsKey(p)){
							score.put(p, lScore2.get(p));
						}else{
							score.put(p, (double) 0);
						}
					}
				}
			}
		}else{
			//有向
			for(Number nodeId1:allNodeIdSet){
				adjNodeIdSet = net2.getAdjNodeId(nodeId1);//nodeId1的邻接点
				
				lScore1.put(new Pair<Number>(nodeId1, nodeId1), (double) 1);
				this.walk(net2, this.t, 1, lScore1, lScore2);
				
				//从nodeId1出发，到达所有其他节点的概率
				for(Number nodeId2:allNodeIdSet){
					//nodeId1与nodeId2没有直接连边
					if(!nodeId2.equals(nodeId1) && !adjNodeIdSet.contains(nodeId2)){
						p = new Pair<Number>(nodeId1, nodeId2, false);
						if(lScore2.containsKey(p)){
							score.put(p, lScore2.get(p));
						}else{
							score.put(p, (double) 0);
						}
					}
				}
			}
		}
		return score;
	}

	/*
	 * 
	 *  从x出发经过t步到达其他节点的概率值
	 *  @param net2 网络
	 *  @param t 经过t步
	 *  @param curT 当前的步数
	 *  @param oldScore 经过curT步，到达其他节点的概率值
	 *  @return Map<Pair<Number>, Double>
	 */
	private boolean walk(Network net2, int t, int curT, Map<Pair<Number>, Double> oldScore, Map<Pair<Number>, Double> score){
		if(curT <= t){
			Map<Pair<Number>, Double> newScore = new HashMap<Pair<Number>, Double>();//curT步，节点x到其他节点的概率值
			Pair<Number> p1 = null, p2 = null;
			Number l, r = null;
			double oldScoreValue = 0, tempScoreValue = 0;
			Set<Number> adjNodeIdSet = null;
			//遍历oldScore里, 从x经过curT-1跳到其他节点的概率值
			for(Iterator<Pair<Number>> it = oldScore.keySet().iterator(); it.hasNext();){
				p1 = it.next();
				l = p1.getL();
				r = p1.getR();
				oldScoreValue = oldScore.get(p1);//从l到r的概率值
				
				adjNodeIdSet = net2.getAdjNodeId(r);//获取r的邻接点，使其走向下一步
				tempScoreValue = oldScoreValue/adjNodeIdSet.size();
				//遍历邻接点
				for(Number adjNodeId:adjNodeIdSet){
					p2 = new Pair<Number>(l, adjNodeId);
					//newScore 每次递归产生新的值
					if(newScore.containsKey(p2)){
						newScore.put(p2, newScore.get(p2)+tempScoreValue);
					}else{
						newScore.put(p2, tempScoreValue);
					}
					//score 递归累加值
					if(score.containsKey(p2)){
						score.put(p2, score.get(p2)+tempScoreValue);
					}else{
						score.put(p2, tempScoreValue);
					}
				}
			}
			this.walk(net2, t, curT+1, newScore, score);//递归
			return true;
		}else{
			return false;//已经到达t步，停止递归
		}
	}
	
}

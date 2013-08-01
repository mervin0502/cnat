package linkPrediction.similarity.global;

import java.util.Map;

import core.Network;

import util.E;
import util.Pair;
import linkPrediction.similarity.Similarity;

public class RWR extends Similarity {
	
	/*
	 * 迭代次数
	 */
	private int t = 10;
	/**
	 * 构造函数
	 * @param net1 网络
	 */
	public RWR(Network net1) {
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
	public RWR(Network net1, int t) throws E {
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
	public RWR(Network net, int k, int t) throws E {
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
		return null;
	}


}

package usr;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;
import java.util.prefs.BackingStoreException;

import core.Global.NumberType;
import core.Network;
import core.Global.NetType;
import feature.ClusterCofficient;
import feature.Coreness;
import feature.Degree;
import model.BANetwork;
import model.GloballyCoupledNet;
import model.LocalWorldEvolvingNet;
import model.NWNetwork;
import model.NearestNeighborCoupledNet;
import model.WSNetwork;
import util.D;
import util.EdgeDeweigh;
import util.ExtractIPByLink;
import util.FileTool;

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


public class Index {
	public static void main(String[] args){
		
/*		GloballyCoupledNet net = new GloballyCoupledNet();
		net.setNetParam(10, 10);
		net.createModelNetwork();
		D.p(net.edgeNum+"##"+net.nodeNum);
		net.BFS();
		D.s();*/
/*		NearestNeighborCoupledNet net = new NearestNeighborCoupledNet();
		net.setNetParam(6, 4);
		net.createModelNetwork();
		net.BFS();*/
		
/*		WSNetwork network = new WSNetwork();
		network.setNetParam(1200, 4, (float) 0.50);
		network.createModelNetwork();
		network.BFS();*/
		
/*		NWNetwork network = new NWNetwork();
		network.setNetParam(10000, 4, (float)0.001);
		network.createModelNetwork();
		network.BFS();*/
		
/*		BANetwork network = new BANetwork(50);
		network.setNetParam(1000, 20);
		network.createModelNetwork();
		network.BFS();*/
		
/*		LocalWorldEvolvingNet net = new LocalWorldEvolvingNet(50);
		net.setNetParam(50, 15, 5);
		net.createModelNetwork();
		net.BFS();*/
		
/*		Network net = new Network("../data/net3.txt", NetType.DIRECTED);
		net.traverseEdge();
		//net.DFS();
		//D.p(net.getEdgeWeight(1, 2));
		//D.p("aaa");
		Path p = new Path(net);
		Collection<Stack<Number>> list = p.bothNodesPath(5, 6);
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Stack<Number> stack = (Stack<Number>) iterator.next();
			for (Iterator iterator2 = stack.iterator(); iterator2.hasNext();) {
				Number number = (Number) iterator2.next();
				D.p(number+"=>");
			}
			D.m();
		}
		*/
		
		//Network net = new Network("../data/net2.txt", NetType.DIRECTED, NumberType.LONG);
/*		Network net = new Network("../data/net2.txt", NetType.DIRECTED);
		//Coreness core = new Coreness(net);
		//D.p("core"+core.netCore());
		//D.p(core.nodeCore(4));
		//core.kCoreNet(1).traverseEdge();
		Path p = new Path(net);
		Collection<Stack<Number>> list = p.bothNodesPath(1, 5);
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Stack<Number> stack = (Stack<Number>) iterator.next();
			for (Iterator iterator2 = stack.iterator(); iterator2.hasNext();) {
				Number number = (Number) iterator2.next();
				D.p(number+"=>");
			}
			D.m();
		}
		LinkedList<Number> l = p.bothNodesShortestPath(1, 5);
		for (Iterator<Number> iterator = l.iterator(); iterator.hasNext();) {
			D.p((Number) iterator.next());
			
		}
		D.p(p.bothNodesPathLength(net, 1, 5));*/
		
		//(new ExtractIPByLink("../data/jfk-us-201201.txt","../data/201201.txt")).run();
		//D.s("提取IP链路为地址对");
		/**
		 *   地址对去重
		 */
		//(new EdgeDeweigh("../data/201201.txt","../data/201201-reduce.txt")).reduce();
		//Network net = new Network("../data/net4.txt", NetType.INDIRECTED, NumberType.INTEGER);
/*		Network net = new Network("../data/ip/11/reduce-1.txt", NetType.INDIRECTED, NumberType.LONG);
		//Degree d = new Degree(net);
		//D.p(d.netDegreeAvg());
		ClusterCofficient cc = new ClusterCofficient(net);
		D.p(cc.netClusterCoefficient());
		//D.p(cc.nodeClusterCofficient(12));
		//Path p = new Path(net);
		//D.p(p.netAvgPathLength());
		//D.p(p.netDiameter());
		//D.p(p.netAvgPathLength());
		Coreness c = new Coreness(net);
		D.p(c.netCore());
		D.m();*/
/*		int[] a = new int[1000000];
		a[0]= 1;
		D.p(a[0]);*/
		//D.p(Math.ceil(0.1/0.25));
		Network net = new Network("/home/mervin/CN/Data/filename.dat", NetType.INDIRECTED, NumberType.INTEGER);
		new FileTool().write(net.traverseEdge(), "/home/mervin/CN/Data/net.edges");
	}
}

/*********************************************************************************
 *
 *
 *
 **********************************************************************************/
package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

import core.Global.*;
/**
 * EdgeDeweigh.java
 * 
 *@author 王进法<Mervin.Wong>
 *@version 
 *@Date 2013-1-16上午10:36:55
 */
/*********************************************************************************
 *
 * 说明：
 * 	1，功能：对于网络拓扑中的边去重，
 * 			例如：（1），边的两个端点相同；
 * 				 （2），该条边已存在网络中，
 * 				 （3），如果时无向网络，还要删除[A-B][B-A]其中一条
 * 2，操作步骤：
 * 3，运行示例：	EdgeDeweigh ed = new EdgeDeweigh("../data/ips.txt","../data/edge2.txt");
				Pair.netType = ed.netType;
				ed.reduce();
 **********************************************************************************/

public class EdgeDeweigh {
	private String souPath = null;//要操作的文件
	private String desPath = null;//将结果保存的文件路径
	private NetType netType = null;//有向还是无向网络
	private NumberType numberType = null;
	public PairSet<Number> edgeSet = null;
	
	public  EdgeDeweigh(String souPath, String desPath){
		this.souPath = souPath;
		this.desPath = desPath;
		this.netType = NetType.INDIRECTED;//无向
		this.numberType = NumberType.INTEGER;
	}

	public  EdgeDeweigh(String souPath, String desPath, NetType netType){
		this.souPath = souPath;
		this.desPath = desPath;
		this.netType = netType;
		this.numberType = NumberType.INTEGER;
		
	}
	
	public  EdgeDeweigh(String souPath, String desPath, NetType netType, NumberType numberType){
		this.souPath = souPath;
		this.desPath = desPath;
		this.netType = netType;
		this.numberType = numberType;
		
	}
	
	public void run(){
		try {
			File[] fileArr = new FileTool().fielArr(this.souPath);
			D.p(this.souPath);
			BufferedReader reader = null;
			for (int i = 0; i < fileArr.length; i++) {
				reader = new BufferedReader(new FileReader(fileArr[i]));
				String line = null;
				String[] lineArr = null;
				edgeSet = new PairSet<Number>();
				//Pair edge = null;
				Number pre, post;
				while((line = reader.readLine()) != null){
					if(line.charAt(0) != '#' && line.charAt(0) != '*' && line.charAt(0) != '%'){
						lineArr = line.split("\t|(\\s{1,})");
						pre = MathTool.str2Number(this.numberType, lineArr[0]);
						post = MathTool.str2Number(this.numberType, lineArr[1]);
						D.p(lineArr[0]+"###"+lineArr[1]);
						if(this.netType == NetType.INDIRECTED){
							if(pre.longValue() < post.longValue()){
								edgeSet.add(new Pair<Number>(pre, post));
							}else if(pre.longValue() > post.longValue()){
								edgeSet.add(new Pair<Number>(post, pre));
							} else{
								continue;
							}
						}else{
							//有向网络
							if(!pre.equals(post)){
								edgeSet.add(new Pair<Number>(pre, post));
							}
						}
					}
				}
				reader.close();
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(this.desPath));
			System.out.println("总共"+edgeSet.size()+"行！");
			Iterator<Pair<Number>> it = edgeSet.iterator();
			StringBuffer sb = new StringBuffer();
			sb.append("#Edges number:").append(edgeSet.size()).append("\r\n");
			while(it.hasNext()){
				sb.append(it.next().toString()).append("\r\n");
/*				if(sb.length() > 1024*1024*20){
					writer.append(sb.toString());
					sb.delete(0, sb.length());
				}*/
			}
			writer.append(sb.toString());
			writer.flush();
			writer.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		EdgeDeweigh ed = new EdgeDeweigh("../data/f1.txt","../data/f3.txt");
		ed.run();
		D.s();
	}
	
}
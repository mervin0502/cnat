package usr;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


import util.FileTool;

import core.Network;
import core.Global.NetType;
import core.Global.NumberType;
import feature.Coreness;

/**
 * CoreEvolution
 *  计算核的演化
 * @author Mervin.Wong
 * **********************************************
 * == 死亡节点的核的变化
 * 在比对中死亡节点位于旧网络中，比如1～2中的1，2～3中的2
 * 因此，在文件夹中包含所有的网络 net-XX(xx代表月份) birthNodes-XX(XX表示第几次的比对)
 * 
 *  * **********************************************
 *  注：sourcePath desPath  目录路径最后需要加斜杠
 */
public class CoreEvolution {
	private String sourcePath = null;
	private String desPath = null;
	
	/**
	 * 构造函数
	 */
	public CoreEvolution(String sourcePath, String desPath) {
		// TODO Auto-generated constructor stub
		this.sourcePath = sourcePath;
		this.desPath = desPath;
		
		//(new File(this.sourcePath)).mkdirs();//创建相应的目录
		//(new File(this.desPath)).mkdirs();
	}
	
	public void birthNodesCore(int j){
		// j:1~11
		String netFileName = this.sourcePath+j+"/reduce-1.txt";
		String nodstFileName = null;
		Set<Number> birthNodes = new HashSet<Number>();
		HashMap<Number, Number> birthNodesCore = null;
		FileTool f = new FileTool();
		Network net = new Network(netFileName, NetType.INDIRECTED, NumberType.LONG);
		Coreness c = new Coreness(net);
		for(int i = 1; i <= j; i++){
			nodstFileName = this.sourcePath+i+"/birthNodes.txt";
			birthNodes = f.read2Set(nodstFileName, NumberType.LONG);
			birthNodesCore = (HashMap<Number, Number>) c.nodeCore(birthNodes);
			f.write(birthNodesCore, this.desPath+j+"/birthNodesCore-"+i+".txt", false);
		}
	}
	
	
	public void deathNodesCore(int j){
		// j:1~11
		String netFileName = this.sourcePath+j+"/reduce-"+(j+1)+".txt";
		String nodstFileName = null;
		Set<Number> birthNodes = new HashSet<Number>();
		HashMap<Number, Number> birthNodesCore = null;
		FileTool f = new FileTool();
 		Network net = new Network(netFileName, NetType.INDIRECTED, NumberType.LONG);
 		Coreness c = new Coreness(net);
		for(int i = 1; i <= j; i++){
			nodstFileName = this.sourcePath+j+"/deathNodes.txt";
			birthNodes = f.read2Set(nodstFileName, NumberType.LONG);
			birthNodesCore = (HashMap<Number, Number>) c.nodeCore(birthNodes);
			f.write(birthNodesCore, this.desPath+j+"/deathNodesCore-"+i+".txt", false);
		}
	}
	
	public static void main(String[] args){
		CoreEvolution ce = new CoreEvolution("../data/ip/sj2/", "../data/ip/sj2/");
		ce.birthNodesCore(1);
		//ce.deathNodesCore(1);
	}
	
}

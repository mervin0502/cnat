package usr;

import java.io.File;

import core.Global.NetType;
import core.Global.NumberType;

import util.D;
import util.EdgeDeweigh;


public class IpStable {
	public static void main(String[] args){
		/*
		 * 提取基本数据		
		File[] fList = new File("../data/IP/2012").listFiles();
		for (int i = 0; i < fList.length; i++) {
			D.p(fList[i].getPath());
			new ExtractIPByLink(fList[i].getPath(), fList[i].getParent()+"/extract-"+fList[i].getName()).run();
		}*/
		/**
		 * 网络合并和去重
		 */
		File[] fList = new File("../data/IP/2009/extract").listFiles();
		for (int i = 0; i < fList.length; i++) {
			new EdgeDeweigh(fList[i].getPath(), fList[i].getParent()+"/../reduce/"+fList[i].getName().substring(fList[i].getName().indexOf('-')+1), NetType.INDIRECTED, NumberType.LONG).run();
		}
	}
}

package usr;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import util.D;

/**
 * 将ip转换成具体的ID
 * @author mervin
 *
 */
public class Ip2Id {
	
	public static void main(String args[]){
		String souPath = "../data/ipp/";
		String desPath = "../data/ipp/";
		int curIndex = 1;
		HashMap<Number, Integer> nodesIdSet = new HashMap<Number, Integer>();
		File f = new File(souPath);
		BufferedReader reader = null;
		BufferedWriter writer = null;
		String line = null;
		String[] lineArr = null;
		Number preNode = null;
		Number postNode = null;
		StringBuffer sb = new StringBuffer();
		int index = 0;
		String[] fileArr = f.list();
		for (int i = 0; i < fileArr.length; i++) {
			try {
				index = 0;
				reader = new BufferedReader(new FileReader(souPath+"/"+fileArr[i]));
				while((line = reader.readLine()) != null){
					lineArr = line.split("\t|(\\s{1,})");
					preNode = Long.parseLong(lineArr[0]);
					postNode = Long.parseLong(lineArr[1]);
					D.p(lineArr[0]+"####"+lineArr[1]);
					if(nodesIdSet.containsKey(preNode)){
						sb.append(nodesIdSet.get(preNode)).append("\t");;
					}else{
						nodesIdSet.put(preNode, curIndex);
						sb.append(curIndex).append("\t");
						curIndex++;
					}
					if(nodesIdSet.containsKey(postNode)){
						sb.append(nodesIdSet.get(postNode)).append("\r\n");;
					}else{
						nodesIdSet.put(postNode, curIndex);
						sb.append(curIndex).append("\r\n");
						curIndex++;
					}
				}
				writer = new BufferedWriter(new FileWriter(desPath+"new-"+fileArr[i]));
				writer.write(sb.toString());
				writer.flush();
				sb.delete(0, sb.length());
				D.m();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
		
	}
}

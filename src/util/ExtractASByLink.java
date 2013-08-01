package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import core.Global.ExtractAS;

/*****************************************************
 * 在文本文件中提取AS连接
 * @author mervin
 * ***************************************************
 * 提取选项：direct  indirect  gad
 * ***************************************************
 *
 */
public class ExtractASByLink {
	private File file = null;
	private String souPath = null;
	private String desPath = null;
	
	private ExtractAS content = ExtractAS.ALL;
	private BufferedReader reader = null;
	/**
	 * 构造函数
	 */
	public ExtractASByLink(ExtractAS content, String path){
		this.content = content;
		file = new File(path);
		if(file.isFile()){
			this.souPath = file.getParent();
		}else{
			this.souPath = path;
		}
		this.desPath = this.souPath;
	}
	
	public ExtractASByLink(ExtractAS content, String path, String desPath){
		this.content = content;
		file = new File(path);
		if(file.isFile()){
			this.souPath = file.getParent();
		}else{
			this.souPath = path;
		}		
		this.desPath = desPath;
	}
	
	/**
	 *  run
	 *  读取文件，提取网络，并写入文件
	 */
	public void run(){
		FileTool f = new FileTool();
		if(this.file.isFile()){
			f.write(this.extract(this.file), this.desPath+file.getName().substring(file.getName().lastIndexOf('.')-8, file.getName().lastIndexOf('.'))+".txt", false);
		}else{
			D.p(this.file);
			File[] fileArr = this.file.listFiles();
			for (int i = 0; i < fileArr.length; i++) {
				//this.extract(fileArr[i]);
				f.write(this.extract(fileArr[i]), this.desPath+fileArr[i].getName().substring(fileArr[i].getName().lastIndexOf('.')-8, fileArr[i].getName().lastIndexOf('.'))+".txt", false);
/*				try {
					//D.p("休息一秒o(∩∩)o...哈哈");
					//Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			}
		}
		D.s("成功了……");
	}
	
	/**
	 *  extract
	 *   在文件中提取网络
	 * @param file
	 * @return StringBuffer
	 */
	public StringBuffer extract(File file){
		StringBuffer sb = new StringBuffer();
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = null;
			String[] lineArr = null;
			String preId = null, postId = null;
			while((line = reader.readLine()) != null){
				switch(this.content){
				case I:
					 if(line.charAt(0) == 'I'){
							lineArr = line.split("\t|(\\s{1,})");
							preId = lineArr[1];
							postId = lineArr[2];
							if(preId.contains(".") || postId.contains(".")){
								continue;
							}
							if(preId.contains("_")){
								preId = (preId.split("_"))[0];
							}
							if(preId.contains(",")){
								preId = (preId.split(","))[0];
							}
							if(postId.contains("_")){
								postId = (postId.split("_"))[0];
							}
							if(postId.contains(",")){
								postId = (postId.split(","))[0];
							}
							sb.append(preId).append("\t").append(postId).append("\r\n");
					 }
					 break;
				case D:
					if(line.charAt(0) == 'D'){
						lineArr = line.split("\t|(\\s{1,})");
						preId = lineArr[1];
						postId = lineArr[2];
						if(preId.contains(".") || postId.contains(".")){
							continue;
						}
						if(preId.contains("_")){
							preId = (preId.split("_"))[0];
						}
						if(preId.contains(",")){
							preId = (preId.split(","))[0];
						}
						if(postId.contains("_")){
							postId = (postId.split("_"))[0];
						}
						if(postId.contains(",")){
							postId = (postId.split(","))[0];
						}
						sb.append(preId).append("\t").append(postId).append("\r\n");
					}
					break;
				case ALL:
					if(line.charAt(0) == 'D'){
						lineArr = line.split("\t|(\\s{1,})");
						preId = lineArr[1];
						postId = lineArr[2];
						if(preId.contains(".") || postId.contains(".")){
							continue;
						}
						if(preId.contains("_")){
							preId = (preId.split("_"))[0];
						}
						if(preId.contains(",")){
							preId = (preId.split(","))[0];
						}
						if(postId.contains("_")){
							postId = (postId.split("_"))[0];
						}
						if(postId.contains(",")){
							postId = (postId.split(","))[0];
						}
						sb.append(preId).append("\t").append(postId).append("\r\n");
					}else if(line.charAt(0) == 'I'){
						lineArr = line.split("\t|(\\s{1,})");
						preId = lineArr[1];
						postId = lineArr[2];
						if(preId.contains(".") || postId.contains(".")){
							continue;
						}
						if(preId.contains("_")){
							preId = (preId.split("_"))[0];
						}
						if(preId.contains(",")){
							preId = (preId.split(","))[0];
						}
						if(postId.contains("_")){
							postId = (postId.split("_"))[0];
						}
						if(postId.contains(",")){
							postId = (postId.split(","))[0];
						}
						sb.append(preId).append("\t").append(postId).append("\r\n");
					}else{
						
					}
					default :;
					break;
				}
				D.p(preId+"####"+postId);
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb;
	}
	
	public static void main(String[] args){
		ExtractASByLink e = new ExtractASByLink(ExtractAS.ALL, "../data/AS-2012-3/team-3/2012/03", "../data/AS-2012-3/t3");
		e.run();
	}
}

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
package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import core.Global.NumberType;

/**
 * FileTool.java
 * 文件操作类
 *@author 王进法<Mervin.Wong>
 *@version 0.4
 */

public class FileTool {
	private String filePath = null;//读文件路径
	private String fileDirPath = null;
	private String dstPath = null;//写文件路径
	
	private BufferedReader reader = null;
	private BufferedWriter writer = null;
	
	/**
	 * 数字的类型
	 */
	public NumberType numberType = NumberType.INTEGER;
	
	public FileTool(){
		
	}
	public FileTool(String filePath){
		File file = new File(filePath);
		if(file.isFile()){
			this.filePath  = filePath;
		}else{
			this.fileDirPath = filePath;
		}
		
	}	
	public FileTool(String filePath, String dstPath){
		File file = new File(filePath);
		if(file.isFile()){
			this.filePath  = filePath;
		}else{
			this.fileDirPath = filePath;
		}
		
	}
	
	private void initFileRead(){
		if(this.filePath != null){
			try {
				this.reader =  new BufferedReader(new FileReader(this.filePath));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private void initFileWrite(){
		if(this.filePath != null){
			try {
				this.writer = new BufferedWriter(new FileWriter(this.dstPath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	/***********************************************************************************************
	 * 
	 * 
	 ***********************************************************************************************/
	/**
	 *  
	 *   统计文件中的某个字符串出现的次数
	 * @param str
	 * @return int
	 */
	public int statStringInFile(String str) {
		String line = new String();
		int num = 0;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(this.filePath));
			try {
				while((line = reader.readLine()) != null){
					if(line.contains(str)){
						num++;
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}
	
	/**
	 *  
	 *  统计文件中的行数
	 * @return int
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public int statFileLines(){
		int lineNum = 0;
		BufferedReader reader;
		String line = null;
		try {
			reader = new BufferedReader(new FileReader(this.filePath));
			while((line = reader.readLine()) != null){
				if(line.charAt(0) != '#' && line.charAt(0) != '*' && line.charAt(0) != '%'){
					++lineNum;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lineNum;
	}
	
	/**
	 *  statNodeNum
	 *  统计网络拓扑文件中节点的数量
	 * @param fileName
	 * @param numberType
	 * @return int
	 */
	public int nodeNum(String fileName, NumberType numberType){
		HashSet<Number> nodesSet =  new HashSet<Number>();
		String line = null;
		String[] lineArr = null;
		try {
			this.reader = new BufferedReader(new FileReader(fileName));
			while((line = this.reader.readLine()) != null){
				if(line.charAt(0) != '#' && line.charAt(0) != '*' && line.charAt(0) != '%'){
					lineArr = line.split("\t|(\\s{1,})");
					for (int i = 0; i < lineArr.length; i++) {
						nodesSet.add(MathTool.str2Number(numberType, lineArr[i]));
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nodesSet.size();
	}
	
	/**
	 * 
	 *  Function: 清空文件中的内容
	 * 
	 *  @param dstPath
	 */
	public void clear(String dstPath){
		this.dstPath = dstPath;
		File f = new File(dstPath);
		f.delete();
		//f.createNewFile();
	}
	/********************************************************************************************************
	 * 
	 * 写文件方法重载
	 * 
	 ********************************************************************************************************/
	/**
	 * 
	 *  将map里的键值对作为一行写入文件
	 * @param map Map数据
	 * @param dstPath 文件保存路径
	 * @param append 是否追加
	 */
	public void write(Map<Number, Number> map, String dstPath, boolean append){
		this.dstPath = dstPath;
		Set<Number> set = map.keySet();
		StringBuffer buffer= new StringBuffer();
		Object object = null;
		for (Iterator<Number> iterator = set.iterator(); iterator.hasNext();) {
			object = iterator.next();
			buffer.append(object.toString()).append("\t").append(map.get(object).toString()).append("\r\n");
		}
		File f = new File(dstPath);
		if(!(f.getParentFile().isDirectory())){
			f.getParentFile().mkdirs();
		}
		try {
			this.writer = new BufferedWriter(new FileWriter(this.dstPath, append));
			this.writer.write(buffer.toString());
			this.writer.flush();
			this.writer.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	/**
	 * 
	 *  将PairList数据写入文件
	 * @param list PairList数据
	 * @param dstPath 文件保存路径
	 * @param append 是否追加
	 */
	public void write(PairList<Number, Number> list, String dstPath, boolean append){
		this.dstPath = dstPath;
		StringBuffer buffer= new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			buffer.append(list.getL(i)).append("\t").append(list.getR(i)).append("\r\n");
		}
		File f = new File(dstPath);
		if(!(f.getParentFile().isDirectory())){
			f.getParentFile().mkdirs();
		}
		try {
			this.writer = new BufferedWriter(new FileWriter(this.dstPath, append));
			this.writer.write(buffer.toString());
			this.writer.flush();
			this.writer.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	/**
	 * 
	 *  将集合写入文件
	 * @param c 集合
	 * @param dstPath 文件保存路径
	 * @param append 是否追加
	 */
	public void write(Collection<Number> c, String dstPath, boolean append){
		new File(dstPath).getParentFile().mkdirs();
		this.dstPath = dstPath;
		
		StringBuffer buffer= new StringBuffer();
		Number object = null;
		for (Iterator<Number> iterator = c.iterator(); iterator.hasNext();) {
			object = iterator.next();
			buffer.append(object.toString()).append("\r\n");
		}
		File f = new File(dstPath);
		if(!(f.getParentFile().isDirectory())){
			f.getParentFile().mkdirs();
		}
		try {
			this.writer = new BufferedWriter(new FileWriter(this.dstPath, append));
			this.writer.write(buffer.toString());
			this.writer.flush();
			this.writer.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	/**
	 * 
	 *  将字符串写入文件
	 * @param str String
	 * @param dstPath 文件保存路径
	 * @param append 是否追加
	 */
	public void write(String str, String dstPath, boolean append){
		this.dstPath = dstPath;
		File f = new File(dstPath);
		if(!(f.getParentFile().isDirectory())){
			f.getParentFile().mkdirs();
		}
		try {
			this.writer = new BufferedWriter(new FileWriter(this.dstPath, append));
			this.writer.write(str);
			this.writer.flush();
			this.writer.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/**
	 * 
	 *  将StringBuffer对象写入文件
	 * @param sb StringBuffer 数据
	 * @param dstPath 文件保存路径
	 * @param append 是否追加
	 */
	public void write(StringBuffer sb, String dstPath, boolean append) {
		// TODO Auto-generated method stub
		this.dstPath = dstPath;
		File f = new File(dstPath);
		if(!(f.getParentFile().isDirectory())){
			f.getParentFile().mkdirs();
		}
		try {
			this.writer = new BufferedWriter(new FileWriter(this.dstPath, append));
			this.writer.write(sb.toString());
			this.writer.flush();
			this.writer.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
	}
	
	/**
	 * 
	 *  将map里的键值对作为一行写入文件
	 * @param map Map数据
	 * @param dstPath 文件保存路径
	 */
	public void write(Map<Number, Number> map, String dstPath){
		this.write(map, dstPath, false);
	}
	/**
	 * 
	 *  将PairList数据写入文件
	 * @param list PairList数据
	 * @param dstPath 文件保存路径
	 */
	public void write(PairList<Number, Number> list, String dstPath){
		this.write(list, dstPath, false);
	}
	/**
	 * 
	 *  将集合写入文件
	 * @param c 集合
	 * @param dstPath 文件保存路径
	 */
	public void write(Collection<Number> c, String dstPath){
		this.write(c, dstPath, false);
	}
	/**
	 * 
	 *  将字符串写入文件
	 * @param str String
	 * @param dstPath 文件保存路径
	 */
	public void write(String str, String dstPath){
		this.write(str, dstPath, false);
	}
	
	/**
	 * 
	 *  将StringBuffer对象写入文件
	 * @param sb StringBuffer 数据
	 * @param dstPath 文件保存路径
	 */
	public void write(StringBuffer sb, String dstPath) {
		// TODO Auto-generated method stub
		this.write(sb, dstPath, false);
	}
	
	/********************************************************************************************************
	 * 
	 * 读文件方法重载
	 * 
	 ********************************************************************************************************/
	/**
	 *  
	 *  对文件数据到集合中
	 * @param filePath 文件夹或者文件路径
	 * @return HashSet<Number>
	 */
	public Set<Number> read2Set(String filePath){
		return this.read2Set(filePath, NumberType.INTEGER);
	}
	/**
	 *  
	 *  对文件数据到集合中
	 * @param filePath
	 * @param numType
	 * @return HashSet<Number>
	 */
	public Set<Number> read2Set(String filePath,NumberType numType){
		this.filePath = filePath;
		HashSet<Number> set = new HashSet<Number>();
		//this.initFileRead();
		try {
			BufferedReader reader =  new BufferedReader(new FileReader(this.filePath));
			String line = null;
			String[] lineArr = null;
			while ((line = reader.readLine()) != null) {
				if(line.charAt(0) != '#' && line.charAt(0) != '*' && line.charAt(0) != '%'){
					lineArr = line.split("\t|(\\s+)");
					for (int i = 0; i < lineArr.length; i++) {
//						D.p(lineArr[i]);
						set.add(MathTool.str2Number(numType, lineArr[i]));
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return set;
	}
	/**
	 *  
	 *  对文件数据到集合中
	 * @param filePath
	 * @param numType
	 * @param col 需要提取的列
	 * @return Set<Number>
	 */
	public Set<Number> read2Set(String filePath,NumberType numType, int[] col){
//		public HashSet<Number> read2Set(String filePath,NumberType numType, List<Integer> cols){
		this.filePath = filePath;
		HashSet<Number> set = new HashSet<Number>();

		try {
			BufferedReader reader =  new BufferedReader(new FileReader(this.filePath));
			String line = null;
			String[] lineArr = null;
			while ((line = reader.readLine()) != null) {
				if(line.charAt(0) != '#' && line.charAt(0) != '*' && line.charAt(0) != '%'){
					lineArr = line.split("\t|(\\s+)");
					for(int i = 0; i < col.length; i++){
						D.p(lineArr[col[i]-1]);
						set.add(MathTool.str2Number(numType, lineArr[col[i]-1]));
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return set;
	}
	
	/**
	 * 
	 *  Function: 读取文件的内容到List类型中。
	 * 
	 *  @param filePath
	 *  @param numType
	 *  @param col
	 *  @return  List<Number>
	 */
	@SuppressWarnings("resource")
	public List<Number> read2List(String filePath, NumberType numType, int[] col){
		ArrayList<Number> list = new ArrayList<Number>();
		
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(filePath));
			String line = null;
			String[] lineArr = null;
			while ((line = reader.readLine()) != null) {
				if(line.charAt(0) != '#' && line.charAt(0) != '*' && line.charAt(0) != '%'){
					lineArr = line.split("\t|(\\s+)");
					for(int i = 0; i < col.length; i++){
						D.p(lineArr[col[i]-1]);
						list.add(MathTool.str2Number(numType, lineArr[col[i]-1]));
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/**
	 *  
	 *  读取文件的内容到map中
	 * @param filePath 文件夹或者文件路径
	 * @return HashMap<Number, Number>
	 */
	public Map<Number, Number> read2Map(String filePath){
		 HashMap<Number, Number> map = new  HashMap<Number, Number>();
		 File[] fileArr = this.fielArr(filePath);
		 for (int i = 0; i < fileArr.length; i++) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(fileArr[i]));
				String line = null;
				String[] lineArr = null;
				while((line = reader.readLine()) != null){
					if(line.charAt(0) != '#' && line.charAt(0) != '*' && line.charAt(0) != '%'){
						lineArr = line.split("\t|(\\s+)");
						map.put(MathTool.str2Number(NumberType.INTEGER, lineArr[0]), MathTool.str2Number(NumberType.INTEGER, lineArr[1]));
					}
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}//for
		 return map;
	}	
	/**
	 *  
	 *  读取文件的内容到map中
	 * @param filePath
	 * @param numberType
	 * @return HashMap<Number, Number>
	 */
	public Map<Number, Number> read2Map(String filePath, NumberType numberType){
		 HashMap<Number, Number> map = new  HashMap<Number, Number>();
		 File[] fileArr = this.fielArr(filePath);
		 for (int i = 0; i < fileArr.length; i++) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(fileArr[i]));
				String line = null;
				String[] lineArr = null;
				while((line = reader.readLine()) != null){
					if(line.charAt(0) != '#' && line.charAt(0) != '*' && line.charAt(0) != '%'){
						lineArr = line.split("\t|(\\s+)");
						map.put(MathTool.str2Number(numberType, lineArr[0]), MathTool.str2Number(numberType, lineArr[1]));
					}
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}//for
		 return map;
	}
	/**
	 *  
	 *  读取文件的内容到map中
	 * @param filePath
	 * @param leftNumberType
	 * @param rightNumberType
	 * @return HashMap<Number, Number>
	 */
	public Map<Number, Number> read2Map(String filePath, NumberType leftNumberType, NumberType rightNumberType){
		 HashMap<Number, Number> map = new  HashMap<Number, Number>();
		 File[] fileArr = this.fielArr(filePath);
		 for (int i = 0; i < fileArr.length; i++) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(fileArr[i]));
				String line = null;
				String[] lineArr = null;
				while((line = reader.readLine()) != null){
					if(line.charAt(0) != '#' && line.charAt(0) != '*' && line.charAt(0) != '%'){
						lineArr = line.split("\t|(\\s+)");
						map.put(MathTool.str2Number(leftNumberType, lineArr[0]), MathTool.str2Number(rightNumberType, lineArr[1]));
					}
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}//for
		 return map;
	}
	/**
	 * 将文本中的k和v列读取到map中
	 *  
	 *  @param filePath
	 *  @param k 将k列作为map的key
	 *  @param v 将v列作为map的alue
	 *  @return Map<Number, Number>
	 */
	public Map<Number, Number> read2Map(String filePath, int k, int v){
		return this.read2Map(filePath, k, NumberType.INTEGER, v, NumberType.INTEGER);
	}
	/**
	 * 将文本中的k和v列读取到map中
	 *  
	 *  @param filePath
	 *  @param k 将k列作为map的key
	 *  @param leftNumberType k的类型
	 *  @param v 将v列作为map的alue
	 *  @param rightNumberType v的类型
	 *  @return Map<Number, Number>
	 */
	public Map<Number, Number> read2Map(String filePath, int k, NumberType leftNumberType, int v, NumberType rightNumberType){
		 Map<Number, Number> map = new  HashMap<Number, Number>();
		 File[] fileArr = this.fielArr(filePath);
		 
		 for (int i = 0; i < fileArr.length; i++) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(fileArr[i]));
				String line = null;
				String[] lineArr = null;
				while((line = reader.readLine()) != null && line.trim().length() > 0){
					if(line.charAt(0) != '#' && line.charAt(0) != '*' && line.charAt(0) != '%'){
						lineArr = line.split("\t|(\\s+)");
						//D.p(line);
						//D.p(line.length());
						map.put(MathTool.str2Number(leftNumberType, lineArr[k-1]), MathTool.str2Number(rightNumberType, lineArr[v-1]));
					}
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}//for
		 return map;
	}
	/**
	 * 将文本中的k和v列读取到map中
	 *  
	 *  @param filePath 文件路径或者目录
	 *  @param k 将k列作为map的key
	 *  @param v 将v列作为map的alue
	 *  @param numberType 节点的类型
	 *  @return Map<Number, Number>
	 */
	public Map<Number, Number> read2Map(String filePath, int k, int v, NumberType numberType){
		 Map<Number, Number> map = new  HashMap<Number, Number>();
		 File[] fileArr = this.fielArr(filePath);
		 
		 for (int i = 0; i < fileArr.length; i++) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(fileArr[i]));
				String line = null;
				String[] lineArr = null;
				while((line = reader.readLine()) != null){
					if(line.charAt(0) != '#' && line.charAt(0) != '*' && line.charAt(0) != '%'){
						lineArr = line.split("\t|(\\s+)");
						//D.p(line);
						map.put(MathTool.str2Number(numberType, lineArr[k-1]), MathTool.str2Number(numberType, lineArr[v-1]));
					}
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}//for
		 return map;
	}
	/**
	 *  
	 *  读取文件的内容到pair中
	 * @param filePath
	 * @param numberType
	 * @return Set<Pair>
	 */
	public Set<Pair<Number>> read2SetPair(String filePath, NumberType numberType){
		// HashMap<Number, Number> map = new  HashMap<Number, Number>();
		 HashSet<Pair<Number>> pair = new HashSet<Pair<Number>>();
		 File[] fileArr = this.fielArr(filePath);
		 for (int i = 0; i < fileArr.length; i++) {
			try {
				@SuppressWarnings("resource")
				BufferedReader reader = new BufferedReader(new FileReader(fileArr[i]));
				String line = null;
				String[] lineArr = null;
				while((line = reader.readLine()) != null){
					if(line.charAt(0) != '#' && line.charAt(0) != '*' && line.charAt(0) != '%'){
						lineArr = line.split("\t|(\\s+)");
						pair.add(new Pair<Number>(MathTool.str2Number(numberType, lineArr[0]), MathTool.str2Number(numberType, lineArr[1])));
					}
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}//for
		 return pair;
	}
	
	/**
	 *  
	 *  读取文件的内容到List<Pair<Number>>中
	 * @param filePath
	 * @param numberType
	 * @return HashSet<Pair>
	 */
	public List<Pair<Number>> read2ListPair(String filePath, NumberType numberType){
		// HashMap<Number, Number> map = new  HashMap<Number, Number>();
		 List<Pair<Number>> pair = new ArrayList<Pair<Number>>();
		 File[] fileArr = this.fielArr(filePath);
		 for (int i = 0; i < fileArr.length; i++) {
			try {
				@SuppressWarnings("resource")
				BufferedReader reader = new BufferedReader(new FileReader(fileArr[i]));
				String line = null;
				String[] lineArr = null;
				while((line = reader.readLine()) != null){
					if(line.charAt(0) != '#' && line.charAt(0) != '*' && line.charAt(0) != '%'){
						lineArr = line.split("\t|(\\s+)");
						pair.add(new Pair<Number>(MathTool.str2Number(numberType, lineArr[0]), MathTool.str2Number(numberType, lineArr[1])));
					}
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}//for
		 return pair;
	}
	/*
	 * **************************************************************************************
	 * 
	 * 
	 * 
	 *
	***************************************************************************************/
	/**
	 *  
	 *  获取文件对象数组
	 * @param path
	 * @return File[]
	 */
	public File[] fielArr(String path){
		File[] fileArr = null;
		File f = new File(path);
		if(f.isDirectory()){
			fileArr = f.listFiles();
		}else{
			fileArr = new File[1];
			fileArr[0] = f;
		}
		return fileArr;
	}
	/**
	 *  
	 *  创建路径目录
	 * @param file
	 * @return boolean
	 */
	public boolean mkdir(String file, boolean flag){
		if(flag){
			return new File(file).mkdirs();
		}else{
			return new File(file).getParentFile().mkdirs();
		}
	}
	/**
	 *  
	 *  合并文件 文件格式是单列，或者两列
	 * @param file1
	 * @param file2
	 * @param dstFile
	 */
	public void combine(String file1, String file2, String dstFile, NumberType numberType, int cols){
		File[] fileArr = new File[2];
		fileArr[0] = new File(file1);
		fileArr[1] = new File(file2);
		this._combine(fileArr, dstFile, numberType, cols);
	}
	/**
	 * 
	 *  合并文件 文件格式是单列，或者两列
	 * @param fileArr
	 * @param dstFile
	 */
	public void combine(File[] fileArr, String dstFile, NumberType numberType, int cols){
		this._combine(fileArr, dstFile, numberType, cols);
	}
	/**
	 *  
	 *  合并文件夹里的文件, 文件格式是单列，或者两列  对网路是又向，还是无向不关注
	 * @param srcDirPath
	 * @param dstFile
	 * @param numberType
	 * @param cols
	 *
	 */
	public void combine(String srcDirPath, String dstFile, NumberType numberType, int cols){
		File[] fileArr = this.fielArr(srcDirPath);
		this._combine(fileArr, dstFile, numberType, cols);
	}
	
	/*
	 *  _combine
	 *  合并文件夹里的文件, 文件格式是单列，或者两列
	 * @param fileArr
	 * @param dstFile
	 * @param numberType
	 * @param cols
	 */
	private void _combine(File[] fileArr, String dstFile, NumberType numberType, int cols){
		new File(dstFile).getParentFile().mkdirs();//创建目录文件夹
		
		if(cols == 1){
			HashSet<Number> nodeSet= new HashSet<Number>();
			String line = null;
			for (int i = 0; i < fileArr.length; i++) {
				try {
					reader = new BufferedReader(new FileReader(fileArr[i]));
					while((line=reader.readLine()) != null){
						if(line.charAt(0) != '#' && line.charAt(0) != '*' && line.charAt(0) != '%'){
							nodeSet.add(MathTool.str2Number(numberType, line.toString()));
							D.p(line);
						}
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				BufferedWriter writer = null;
				try {
					writer = new BufferedWriter(new FileWriter(dstFile));
					Iterator<Number> it = nodeSet.iterator();
					StringBuffer sb = new StringBuffer();
					while(it.hasNext()){
						sb.append(it.next().toString()).append("\r\n");
						if(sb.length() > 1024*1024*20){
							writer.append(sb.toString());
							sb.delete(0, sb.length());
						}
					}
					writer.append(sb.toString());
					writer.flush();
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else{
			BufferedReader reader = null;
			PairSet<Number> edgeSet = new PairSet<Number>();
			String line = null;
			String[] lineArr = null;
			Number pre, post;
			for (int i = 0; i < fileArr.length; i++) {
				try {
					reader = new BufferedReader(new FileReader(fileArr[i]));
					
					while((line = reader.readLine()) != null){
						if(line.charAt(0) != '#' && line.charAt(0) != '*' && line.charAt(0) != '%'){
							lineArr = line.split("\t|(\\s{1,})");
							pre = MathTool.str2Number(numberType, lineArr[0]);
							post = MathTool.str2Number(numberType, lineArr[1]);
							D.p(lineArr[0]+"###"+lineArr[1]);
	
							if(!pre.equals(post)){
								edgeSet.add(new Pair<Number>(pre, post));
							}
						}
					}
					reader.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}//for
			D.p(edgeSet.size());
			BufferedWriter writer = null;
			Pair<Number> temp = null;
			try {
				writer = new BufferedWriter(new FileWriter(dstFile));
				Iterator<Pair<Number>> it = edgeSet.iterator();
				StringBuffer sb = new StringBuffer();
				while(it.hasNext()){
					temp = it.next();
					sb.append(temp.getL()).append("\t").append(temp.getR()).append("\r\n");
					/*if(sb.length() > 1024*1024*20){
						writer.append(sb.toString());
						sb.delete(0, sb.length());
					}*/
				}
				writer.append(sb.toString());
				writer.flush();
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
	}
	
	
	
	public static void main(String[] args){
		FileTool f = new FileTool();
		//f.combine("../data/f1.txt", "../data/f2.txt", "../data/f3.txt" ,NumberType.INTEGER, 2);
		
		D.m();
	}
}

package work;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class create_suanshi {
	static     String   wenTi = "Exercises.txt";   //对照答案时问题的路径,未选取的时候默认为Exercises.txt文件
	static     String  daAn = "Answers.txt";   //对照答案时问题的路径,未选取的时候默认为Exercises.txt文件
	static String sizi = new String();
	static char[] fuhao = {'+','-','*','÷'};
	static String str[][] = new String[10000][7];
	public static void main(String[] args)  {
		
		
		
		
		
		sizi=""; 
	
		
		
		
		//设置主界面
	    JFrame mainFrame= new JFrame("题目自动生成器");
	      mainFrame.setSize(410,400);
          mainFrame.setLocation(200, 200);
	      mainFrame.setLayout(new GridLayout(3, 1));
	      mainFrame.setLayout(null);
	      mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    
          
          
          
         // 创建输入框
          
          JPanel Input = new JPanel();
  		Input.setBounds(10, 10, 375, 120);
  		Input.setLayout(new GridLayout(4,3,10,10));
  		
  		JLabel geshu = new JLabel("生成题目个数:");
  		final JTextField geshuText = new JTextField();
  		
  		
  		JLabel  fanwei= new JLabel("生成数值范围:");
  		final JTextField fanweiText = new JTextField();

JButton b = new JButton("生成随机题目");
final JButton wenti = new JButton("选择题目文件");
final JButton daan= new JButton("选择答案文件");
JButton jieguo = new JButton("判断对错");
wenti.setLayout(null);	
daan.setLayout(null);		
b.setLayout(null);		
jieguo.setLayout(null);	
		Input.add(geshu);
		Input.add(geshuText);
		Input.add(fanwei);
		Input.add(fanweiText);
  
  
		//文本域
				
		        b.setBounds(120, 120 + 30, 160, 30); 
		        wenti.setBounds(10, 220, 160, 30);   
		        daan.setBounds(210, 220 , 160, 30);
		        jieguo.setBounds(120,250, 160, 30);
		      
			//主界面加入文本域，按钮和输入框	
				
				mainFrame.add(Input);
				mainFrame.add(b);
				mainFrame.add(wenti);
				mainFrame.add(daan);
				mainFrame.add(jieguo);
				mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
				wenti.addActionListener(new ActionListener() {      //选择问题的文件
			            public void actionPerformed(ActionEvent e) {
			                JFileChooser chooser = new JFileChooser(); //设置选择器
			                chooser.setMultiSelectionEnabled(true); //设为多选
			                int returnVal = chooser.showOpenDialog(wenti); //是否打开文件选择框
			                System.out.println("returnVal="+returnVal);
			                if (returnVal == JFileChooser.APPROVE_OPTION) { //如果符合文件类型

			                 wenTi=  chooser.getSelectedFile().getAbsolutePath();
			                }

			            }
			            });
				
			
				
			daan.addActionListener(new ActionListener() {      //选择问题的文件
		            public void actionPerformed(ActionEvent e) {
		                JFileChooser chooser = new JFileChooser(); //设置选择器
		                chooser.setMultiSelectionEnabled(true); //设为多选
		                int returnVal = chooser.showOpenDialog(daan); //是否打开文件选择框
		                System.out.println("returnVal="+returnVal);
		                if (returnVal == JFileChooser.APPROVE_OPTION) { //如果符合文件类型
                daAn=  chooser.getSelectedFile().getAbsolutePath();
		                }

		            }});
			
				
				
			
			
			
			
			jieguo.addActionListener(new ActionListener() {      //选择问题的文件
	            public void actionPerformed(ActionEvent e) {
	               
	            	try{ String daan=result(wenTi);
					saveAsFileWriter(daan,"Answer1.txt");         //将问题的答案放入答案文件
					}
					catch(IOException h)
					{h.printStackTrace();}
				  	  
	            	try{judge("Answer1.txt",daAn);}
	        		catch(IOException k)
	        		{k.printStackTrace();}
	            	
	            	
	            	
	            }
	            });
				
				

	//点击按钮，开始生成题目
				 b.addActionListener(new ActionListener() {
					 boolean checkedpass = true;
			          public void actionPerformed(ActionEvent e) {

				    		checkedpass = true;
			        	 //检验是否有数值没有填
			        	  checkNumber(geshuText,"题目个数");
			        	  checkNumber(fanweiText,"题目范围");  
			        	
			          String	 Strnum =	geshuText.getText();
			          String    Strscope = fanweiText.getText();
			          //将输入的值转化为整型
			  	      int  num  = Integer.parseInt(Strnum);
			  	      int scope = Integer.parseInt(Strscope);
			  	    create(scope,num);
			  	  saveAsFileWriter(sizi,"Exercises.txt"); //生成问题存进文件
			  	  
			  	  
			  	try{ String daan=result("Exercises.txt");
				saveAsFileWriter(daan,"Answers.txt");         //将问题的答案放入答案文件
				}
				catch(IOException h)
				{h.printStackTrace();}
			  	  
			  	  
			  	  
			  	JOptionPane.showMessageDialog(null,"随机生成题目成功\n题目与答案已经保存");
			           }
			          
				 
			        //检验输入数值是否为整数
				    	private void checkNumber(JTextField tf, String msg){
				    		if(!checkedpass)
				    			return;
				    		String value = tf.getText();
				    		try {
								Integer.parseInt(value);
							} catch (NumberFormatException e) {
								JOptionPane.showMessageDialog(null, msg + "必须是整数");
								tf.grabFocus();
								checkedpass = false;
							}
				    	}

		
			          
			          
				 
				 
				 });//按钮结束位置
          
				  mainFrame.setVisible(true);
          
	} //主函数大括号
          
          
          
     
          
          
          
          
          
          
          
          
          
          //final JButton zifu= new JButton("wc.exe -c(字符数)");
          
         // controlPanel.add(zifu); 
      
          //将按钮放入主界面
         // mainFrame.add(controlPanel);
	     
	    //统计file文档的行数
	   /*   hangshu.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	        	  int  k=zifu("file.c");
	  	    	JOptionPane.showMessageDialog(null, "file文件里面的行数为"+k); 	  
	  	              
	           }});
	    */  
  		// mainFrame.setVisible(true);  //将图形界面设置为可见
	       
	      
	
	
	
	
	
	   
	
	 private static void saveAsFileWriter(String content,String filePath) {
		 //content为写入的字符串，filepath为写入文件的路径
	
	        FileWriter fwriter = null;
	        try {
	            
	            fwriter = new FileWriter(filePath);
	            fwriter.write(content);
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        } finally {
	            try {
	                fwriter.flush();
	                fwriter.close();
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
	        }
	    }
	    
	    
	    
	    
	    
	 static void create(int r,int p) {    //随机生成题目，r为生成范围，p为生成个数
			
			for( int n=0 ; n<p  ;){   //开始循环
			Random ran = new Random();
			int flag=ran.nextInt(3)+1;
			  String [] array=new String[2*flag+1];
			char[] a = new char[flag];
			//char[] c = new char[flag];
			int[] number = new int[flag+1];
		    // int[]   paixu=new int[flag+1];
			
			for(int i=0;i<flag+1;i++) {
				number[i]=ran.nextInt(r)+1;//创建三个随机数
	
			}
			
			
			
			for(int j=0;j<flag;j++) {
				a[j]=fuhao[ran.nextInt(4)];
				switch(a[j]) {
				//  '-'对数有特殊要求
				case '-': if(number[j]>number[j+1])
							break;
				          else {j--;break;}
						  
				case '÷': if(number[j]%number[j+1]==0)
					break;
				  else {j--;break;}

				case '+':
				case '*':break;
			}
			
			}//该括号为生成字符结束
			
		
			
			for(int i=0; i < flag ; i++)
				array[i] =  String.valueOf(a[i]);
			for(int i=0; i < flag+1; i++)
				array[flag+i]=String.valueOf(number[i]); //将字符都放进array中
		int biaoji=0;
			for(int i=0 ; i< p; i++)
				{if(Arrays.equals(str[i],array))
					{biaoji =1;            //如果与已有式子一样，重新生成题目
					break;}
				}
			
			
			
			String shizi = new String();
			//对运算数和运算符进行连接
			shizi=String.valueOf(number[0]);
			for(int k=0;k<flag;k++) {
				shizi=shizi+a[k]+String.valueOf(number[k+1]);
			}
			if(count(shizi)==false)
        biaoji = 1;
			
			
			
			
			
						
		
			if(biaoji == 0){
				str[n] = array;
				
			n++;	
	  
			for(int k=0;k<flag;k++) {
			
				sizi=sizi+String.valueOf(number[k])+a[k];
			}
			
			sizi =sizi + String.valueOf(number[flag])+'=' + '\n';
			
			}	
			}

	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	static //对答案进行判断		
			void judge(String exe,String answer) throws IOException {
		        String correct=" ";//用于存储正确的题目的编号，即（2,3）这样的
		        int correct_=0;		//用于存储正确的题目总数
		        String wrong=" ";	//用于存储错误题目的编号
		        int wrong_=0;		//用于存储错误的题目总数
		        
				//读取回答文件，若不存在则抛出异常 
				File myexe=new File(exe);
				String exe2=null;		//用于存储读取的回答
		         if(!myexe.exists()||myexe.isDirectory())
		             throw new FileNotFoundException();
		         
		       //读取答案文件，若不存在则抛出异常  
		         File answer_1=new File(answer);
		         String answer_2=null;     //用于存储读取的答案
		         if(!answer_1.exists()||answer_1.isDirectory())
		             throw new FileNotFoundException();
		       
		         BufferedReader exe1=new BufferedReader(new FileReader(myexe));
		         BufferedReader answer_3=new BufferedReader(new FileReader(answer_1));
		       
		         //exe2和answer_3用于存储读取的行  
		         for(int i=1;((exe2=exe1.readLine())!=null && (answer_2=answer_3.readLine())!=null);i++) {
		        //如果文件没读完，循环继续
		        	 if(exe2.equals(answer_2)) {
		        		 //字符相同，说明答案一样
		        		 if(correct==" ")
		        			 correct=correct+String.valueOf(i);	////纯粹为了保持 “，”的数目正常
		        		 else
		        			 correct=correct+','+String.valueOf(i);	//将正确的编号接入字符串
		        		 correct_++;								//正确的题目数+1
		        	 }
		        	 else { 
		        		 if(wrong==" ")
		        			 wrong=wrong+String.valueOf(i);		//纯粹为了保持 “，”的数目正常
		        		 else
		        			 wrong=wrong+','+String.valueOf(i);	//将错误的编号接入字符串
		        	     wrong_++; 
		        	 }
		         }
		         //如果你要测试的话，main函数中的路径记得改
		    JOptionPane.showMessageDialog(null,"correct:"+String.valueOf(correct_)+'('+correct+" )"+"\nwrong:"+String.valueOf(wrong_)+'('+wrong+" )");
		        // System.out.println("wrong:"+String.valueOf(wrong_)+'('+wrong+" )");
		         exe1.close();
		         answer_3.close();
		         
			}
	 
	 
	 
	 
	 
	 
	
	
	
	
    static boolean count(String str) {            //单个判断正负
        str=remove(str, '=');
        str=str.replace('÷','/');
  ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine se = manager.getEngineByName("js");
        Double result = null;

        try {
            result = Double.valueOf(se.eval(str).toString());
            if (result == null) {
                result = new Double(0);
            }
        } catch (ScriptException e) {
            e.printStackTrace();
        }

       if(result>0) return true;
       else return false;
 }
    
    
    
	public static String remove(String sourceString, char chElemData) {  //删除一个字符
	    String deleteString = "";
	    for (int i = 0; i < sourceString.length(); i++) {
	        if (sourceString.charAt(i) != chElemData) {
	            deleteString += sourceString.charAt(i);
	        }
	    }
	    return deleteString;
	}	

	
	
	
	
	
	
	
	  static String result(String Lujin) throws IOException {
		    File answer_1=new File(Lujin);
		          String str=null;     //用于存储读取的答案
		          if(!answer_1.exists()||answer_1.isDirectory())
		              throw new FileNotFoundException();
		          
		          String result_0="";
		        
		          BufferedReader answer_3=new BufferedReader(new FileReader(answer_1));
		          while((str=answer_3.readLine())!=null) {
		         str=remove(str, '=');
		         str=str.replace('÷','/');
		   
		   ScriptEngineManager manager = new ScriptEngineManager();
		         ScriptEngine se = manager.getEngineByName("js");
		         Double result = null;

		         try {
		             result = Double.valueOf(se.eval(str).toString());
		             if (result == null) {
		                 result = new Double(0);
		             }
		         } catch (ScriptException e) {
		             e.printStackTrace();
		         }
		         if(result.equals(Math.floor(result))) {
		          double result1=result;
		          int i= (int)result1;
		          result_0=result_0+String.valueOf(i)+"\n";
		          }
		          }
		          answer_3.close();
		          return result_0;
		  }
	
	
	
	
	
	
	
	
	
	
	
	
	
	 
	 
	 
	 
	 



			}//整个类的大括号


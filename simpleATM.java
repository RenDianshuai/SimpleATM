
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

		public class Account{
		Scanner input = new Scanner(System.in);
		private double balance = 0;
		private static double annualInterestRate = 0;    //存储当前利率
		SimpleDateFormat dateCreated = new SimpleDateFormat("yyyy-MM-dd HH：mm：ss");  //存储开户日期
		private boolean OPEN = true;
		private static ArrayList<Integer> sumber = new ArrayList<Integer>(); //存储ID
		private static int id = 0;
		private String date;
	//判断ID是否重复
		private static boolean judge(int id)
		{
			boolean OK = true;
			for (int i=0; i<sumber.size(); i++){
				if (id == sumber.get(i)){             //ID存在
					OK = false;
					break;
				}else {
					OK = true;
				}
			}
			return OK;
		}
	//可能存在一个小问题{
	//创建默认账户	
		public Account()
		{
			if (OPEN){
				Account.id = 0;
				while (!judge(Account.id)){
					Account.id++;                    //若ID已经存在，ID+1
				}
				OPEN = false;
				sumber.add(Account.id);           //存储ID 
				date = dateCreated.format(new Date());
				
			}else {
				System.out.println("无法创建，请重新定义一个对象再尝试");
			}
			
		}
		//  在此中间           }
	//创建特定账户
		public Account (int id,double balance)         //特定id，初始余额
		{
			if (OPEN){
			    Account.id= id;
			    this.balance = balance;
				Scanner input = new Scanner(System.in);
			    while (!judge(Account.id)){
			    	System.out.println("此ID已经存在，请再输入一个！");
			    	Account.id = input.nextInt();
			    }
			    OPEN = false;
			    sumber.add(Account.id);      //存储ID 
				date = dateCreated.format(new Date());
			}else {
				System.out.println("无法创建，请重新定义一个对象再尝试");
			}
		}
	//修改ID           
		public boolean setId(int id)            //成功true
		{
			boolean OK = true;
			if (!judge(id)){
				System.out.println("错误！原因：此ID已经存在，请再输入一个：");
				OK =false;
			}else if (judge(id)) {
				Account.id = id;
				System.out.printf("修改成功，现在你的ID为%04d\n",Account.id);
				OK = true;
				sumber.add(Account.id);
			}else {
				System.out.println("未知错误，请再试一次：");
				OK = false;
			}
			return OK;
		}
	//返回ID
		public int getId()
		{
			return id;
		}
	//设置当前年利率
		public void setAnnualInterestRate(double annualInterestRate)
		{
			Account.annualInterestRate = annualInterestRate/100;
		}
	//返回当前年利率
		public double getAnnualInterestRate()
		{
			return annualInterestRate*100;
		}
	//得到余额
		public double getBalance()
		{
			return balance;
		}
	//得到日期
		public String getDate()
		{
			return date;
		}
	//得到月利率
		public double getMonthlyInterestRate()
		{
			return annualInterestRate/12*100;
		}
	//得到月利息
		public double getMonthiyInterest()
		{
			return balance * getMonthlyInterestRate();    //余额*月利率
		}
	//从账户提取特定数额
		public void withDraw(double money)
		{
			if (money<=balance){
			balance = balance - money;
			System.out.println("提取成功，提取金额"+money+"元");
			}else {
				System.out.println("提取失败！原因：账户余额不足");
			}
		}
	//向账户存储特定数额
		public void deposit(double money)
		{
			balance = balance + money;
			System.out.println("存储成功，存储金额"+money+"元");
		}	
	//返回一个容器中的值
		public int getValue(int index){
			return sumber.get(index-1);
		}
	}

public class Test {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("第一个账户：");
		Account account1 = new Account(1122,20000);
		account1.setAnnualInterestRate(0.45);
		account1.withDraw(2500);
		account1.deposit(3000);
		System.out.printf("账户ID：%04d,账户余额：%.2f元,月利息:%.2f元,创建时间:%s\n\n",account1.getId(),account1.getBalance(),account1.getMonthiyInterest(),account1.getDate());
		System.out.println("第二个至第四个测试账户");
		Account account2 = new Account();
		Account account3 = new Account();
		Account account4 = new Account();
		System.out.printf("account2 ID:%04d		余额：%.2f\naccount3 ID:%04d		余额：%.2f\naccount4 ID:%04d		余额：%.2f\n\n",account2.getValue(2),account2.getBalance(),account3.getValue(3),account3.getBalance(),account4.getValue(4),account4.getBalance());
		System.out.println("第五个账户：");
		Account account5 = new Account(1122,3000);
		System.out.println("账户余额:"+account5.getBalance());
		System.out.println("请输入新的数值来修改原来的ID：");
		while (!account5.setId(input.nextInt()));
		account2.withDraw(3500);
		System.out.printf("年利息:%.2f%%,账户ID:%04d，创建时间:%s",account5.getAnnualInterestRate(),account5.getId(),account5.getDate());
	}
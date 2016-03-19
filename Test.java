package test;

import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

		class Account{
		Scanner input = new Scanner(System.in);
		private double balance = 0;
		private static double annualInterestRate = 0;    //�洢��ǰ����
		SimpleDateFormat dateCreated = new SimpleDateFormat("yyyy-MM-dd HH��mm��ss");  //�洢��������
		private boolean OPEN = true;
		private static ArrayList<Integer> sumber = new ArrayList<Integer>(); //�洢ID
		private static int id = 0;
		private String date;
	//�ж�ID�Ƿ��ظ�
		private static boolean judge(int id)
		{
			boolean OK = true;
			for (int i=0; i<sumber.size(); i++){
				if (id == sumber.get(i)){             //ID����
					OK = false;
					break;
				}else {
					OK = true;
				}
			}
			return OK;
		}
	//���ܴ���һ��С����{
	//����Ĭ���˻�	
		public Account()
		{
			if (OPEN){
				Account.id = 0;
				while (!judge(Account.id)){
					Account.id++;                    //��ID�Ѿ����ڣ�ID+1
				}
				OPEN = false;
				sumber.add(Account.id);           //�洢ID 
				date = dateCreated.format(new Date());
				
			}else {
				System.out.println("�޷������������¶���һ�������ٳ���");
			}
			
		}
		//  �ڴ��м�           }
	//�����ض��˻�
		public Account (int id,double balance)         //�ض�id����ʼ����
		{
			if (OPEN){
			    Account.id= id;
			    this.balance = balance;
				Scanner input = new Scanner(System.in);
			    while (!judge(Account.id)){
			    	System.out.println("��ID�Ѿ����ڣ���������һ����");
			    	Account.id = input.nextInt();
			    }
			    OPEN = false;
			    sumber.add(Account.id);      //�洢ID 
				date = dateCreated.format(new Date());
			}else {
				System.out.println("�޷������������¶���һ�������ٳ���");
			}
		}
	//�޸�ID           
		public boolean setId(int id)            //�ɹ�true
		{
			boolean OK = true;
			if (!judge(id)){
				System.out.println("������ԭ�򣺴�ID�Ѿ����ڣ���������һ����");
				OK =false;
			}else if (judge(id)) {
				Account.id = id;
				System.out.printf("�޸ĳɹ�����������IDΪ%04d\n",Account.id);
				OK = true;
				sumber.add(Account.id);
			}else {
				System.out.println("δ֪������������һ�Σ�");
				OK = false;
			}
			return OK;
		}
	//����ID
		public int getId()
		{
			return id;
		}
	//���õ�ǰ������
		public void setAnnualInterestRate(double annualInterestRate)
		{
			Account.annualInterestRate = annualInterestRate/100;
		}
	//���ص�ǰ������
		public double getAnnualInterestRate()
		{
			return annualInterestRate*100;
		}
	//�õ�����
		public double getBalance()
		{
			return balance;
		}
	//�õ�����
		public String getDate()
		{
			return date;
		}
	//�õ�������
		public double getMonthlyInterestRate()
		{
			return annualInterestRate/12*100;
		}
	//�õ�����Ϣ
		public double getMonthiyInterest()
		{
			return balance * getMonthlyInterestRate();    //����*������
		}
	//���˻���ȡ�ض�����
		public void withDraw(double money)
		{
			if (money<=balance){
			balance = balance - money;
			System.out.println("��ȡ�ɹ�����ȡ����"+money+"Ԫ");
			}else {
				System.out.println("��ȡʧ�ܣ�ԭ�����˻������");
			}
		}
	//���˻��洢�ض�����
		public void deposit(double money)
		{
			balance = balance + money;
			System.out.println("�洢�ɹ����洢����"+money+"Ԫ");
		}	
	//����һ�������е�ֵ
		public int getValue(int index){
			return sumber.get(index-1);
		}
	}

		class Test {
			public static void main(String[] args) {
				Scanner input = new Scanner(System.in);
				System.out.println("��һ���˻���");
				Account account1 = new Account(1122,20000);
				account1.setAnnualInterestRate(0.45);
				account1.withDraw(2500);
				account1.deposit(3000);
				System.out.printf("�˻�ID��%04d,�˻����%.2fԪ,����Ϣ:%.2fԪ,����ʱ��:%s\n\n",account1.getId(),account1.getBalance(),account1.getMonthiyInterest(),account1.getDate());
				System.out.println("�ڶ��������ĸ������˻�");
				Account account2 = new Account();
				Account account3 = new Account();
				Account account4 = new Account();
				System.out.printf("account2 ID:%04d		���%.2f\naccount3 ID:%04d		���%.2f\naccount4 ID:%04d		���%.2f\n\n",account2.getValue(2),account2.getBalance(),account3.getValue(3),account3.getBalance(),account4.getValue(4),account4.getBalance());
				System.out.println("�������˻���");
				Account account5 = new Account(1122,3000);
				System.out.println("�˻�����:"+account5.getBalance());
				System.out.println("�������µ���ֵ���޸�ԭ����ID��");
				while (!account5.setId(input.nextInt()));
				account2.withDraw(3500);
				System.out.printf("����Ϣ:%.2f%%,�˻�ID:%04d������ʱ��:%s",account5.getAnnualInterestRate(),account5.getId(),account5.getDate());
			}

		}
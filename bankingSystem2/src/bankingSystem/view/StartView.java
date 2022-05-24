package bankingSystem.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import bankingSystem.controller.BankingController;
import bankingSystem.dto.Account;
import bankingSystem.dto.Customer;
import bankingexecption.BankingExecption;

public class StartView {
	
	
	// StartView에서 사용자 입력을 받기 위해 작성한 Method, IOException ( 입력 에러 방지 )
	public static String readString() throws IOException {
		BufferedReader readString = new BufferedReader(new InputStreamReader(System.in));

		try {
			return readString.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			readString.close();
		}

		return null;

	}

	public static void main(String[] args) throws IOException, BankingExecption {

		Account account1 = new Account("1002-886-111111", 1111, "0033");
		Account account2 = new Account("1002-886-222222", 2222, "1113");
		Account account3 = new Account("1002-886-333333", 3333, "0341");
		Account account4 = new Account("1002-806-444444", 4444, "0342");

		Customer customer1 = new Customer("010-1234-1234", "김진원", new ArrayList<Account>(Arrays.asList(account1)));
		Customer customer2 = new Customer("010-0000-9999", "조윤형",
				new ArrayList<Account>(Arrays.asList(account2, account3)));
		Customer customer3 = new Customer("010-1111-2222", "김규식", new ArrayList<Account>(Arrays.asList(account4)));

		ArrayList<Customer> allCustomerInfo = new ArrayList<Customer>();
		allCustomerInfo.add(customer1);
		allCustomerInfo.add(customer2);
		allCustomerInfo.add(customer3);

		String number = null;
		int accountNum = 0;

		System.out.println("*** 고객님의 전화번호를 입력해주세요 ex(010-****-****) *** ");
		number = readString();

		Customer c1 = BankingController.checkcustomer(number, allCustomerInfo);

		// 입력된 전화번호 정보에 Mapping 되는지 확인하기 위한 checkcustomer Method

		while (c1.getCustomerName() != null) {

			BankingController.checkaccount(c1); // 사용할 계좌의 목록을 출력하는 메소드
			accountNum = Integer.parseInt(readString()) - 1; // 사용자로부터 사용계좌의 입력을 받음

			if (BankingController.Checkinputexecption(accountNum, c1) == false) {
				System.out.println("잘못된 값을 입력하였으므로 종료합니다.");
				break;
			}
			// 입력값이 등록계좌수 보다 많거나 , 적거나 , 비정상입력된 경우 예외처리

			System.out.println("*** 이용하시려는 서비스를 선택하세요 ***");
			System.out.println("1.입금  2.출금  3.계좌이체  4.잔액조회");
			number = readString();// 이용할 서비스 선택

			int depositmoney = 0;
			String password = null;

			if (number.equals("1")) {
				// 입금 서비스 선택 시 입금 기능 진행
				System.out.println("입금하실 금액을 입력해주세요.");
				depositmoney = Integer.parseInt(readString());
				System.out.println(depositmoney);
				BankingController.depositMoney(c1.getOwnAccounts().get(accountNum).getAccountNumber(),
						c1.getOwnAccounts().get(accountNum).getAccountBalence(), depositmoney);

			} else if (number.equals("2")) {
				// 출금 서비스 선택 시 출금 기능 진행
				System.out.println("출금계좌의 비밀번호 4자리를 입력해주세요");
				password = readString(); // 출금 진행시에는 계좌의 비밀번호가 필요하므로, 계좌와 비밀번호 Mapping

				if (BankingController.passwordCheck(c1.getOwnAccounts().get(accountNum), password) == true) {
					System.out.println("출금하실 금액을 입력해주세요");
					depositmoney = Integer.parseInt(readString());
					BankingController.depositMoney(c1.getOwnAccounts().get(accountNum).getAccountNumber(),
							c1.getOwnAccounts().get(accountNum).getAccountBalence(), -depositmoney);
				}
			
			} else if (number.equals("3")) {
				number = null;
				password = null;
				depositmoney = 0;
				System.out.println("이체하실 계좌번호를 입력하세요");
				number = readString();
				Account A2 = BankingController.checkaccount2(allCustomerInfo, number);
				System.out.println("비밀번호 4자리를 입력해주세요");
				password = readString();
				int a = 0;
				if(BankingController.passwordCheck(c1.getOwnAccounts().get(accountNum), password) == true) {
					if (A2.getAccountNumber()!=null) {
						System.out.println("이체하실 금액을 입력하세요");
						depositmoney = Integer.parseInt(readString());
						System.out.println("이체할 계좌는 " + A2.getAccountNumber() + "으로 " + depositmoney + "원 입니다."
								+ "동의하시면 Y 를 입력해주세요.");
						number = null;
						number = readString();
					
						if(BankingController.lastcheck(number)==true){
						
							if(BankingController.depositMoney(c1.getOwnAccounts().get(accountNum).getAccountNumber(),
									c1.getOwnAccounts().get(accountNum).getAccountBalence(), -depositmoney)==true) {
							
								BankingController.depositMoney(A2.getAccountNumber(), A2.getAccountBalence(), depositmoney);
							
								System.out.println("계좌이체를 완료하였습니다.");
								System.out.println(c1.getOwnAccounts().get(accountNum).getAccountNumber() + "의 잔액은 "
										+ (c1.getOwnAccounts().get(accountNum).getAccountBalence() - depositmoney) + " 입니다.");
								
								break;
						}
						
						
					}
				}
					
				}
				
				

				

			} else if (number.equals("4")) {

				System.out.println("시스템 구현중입니다.");
				break;

			} else {

				System.out.println("잘못된 값을 입력하였으므로 종료합니다.");
				break;
			}
		}
	}

}

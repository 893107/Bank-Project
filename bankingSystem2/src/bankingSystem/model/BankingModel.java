package bankingSystem.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import bankingSystem.dto.Account;
import bankingSystem.dto.Customer;
import bankingSystem.view.SuccessView;
import bankingexecption.BankingExecption;

public class BankingModel {
	// Singleton 사용
	private static BankingModel instance = new BankingModel();
	
	// 외부 생성 방지를위한 private
	private BankingModel() {

	}

	public static BankingModel getInstance() {
		return instance;
	}

	// 비정상 입력 시 예외처리 Logic 고객이 보유하고 있지 않은 수가 입력된 경우 , 글자나 공백이 입력된 경우 예외처리발생
	public boolean Checkinputexecption(int number, Customer c) throws BankingExecption {

		if (number < c.getOwnAccounts().size() && number >= 0) {
			return true;
		}
		throw new BankingExecption("입력된 값은 잘못된 형식입니다.");

	}
	
	
	
	

	public boolean typingMoney(String accountNumber, int money, int depositmoney) {
		int myMoney = money + depositmoney;
		if (myMoney >= 0) {
			if (depositmoney >= 0) {
				 System.out.println("정상적으로 입금 완료 되었습니다." + "입금한 계좌 번호 : " + accountNumber + "\n 입금한 금액 : " + depositmoney
						+ ", 현재 잔액 : " + myMoney);
				 return true;
			} else {
				
				System.out.println("정상적으로 출금 완료 되었습니다." + "출금한 계좌 번호 : " + accountNumber + "\n 출금한 금액 : " + (-depositmoney)
						+ ", 현재 잔액 : " + myMoney);
				
				return true;
			}
		} else {
			System.out.println("잔액이 부족합니다. 출금하시려는 금액을 확인하세요");
			return false;
		}
	}

	/** 출금 */

	/** 계좌이체 */

	public boolean passwordCheck(Account account, String password) {
		// 출금 시작할때 true -> 진행 false -> 진행안하게끔
		if (account.getPassword().equals(password)) {
			System.out.println("정상 확인 되었습니다.");
			return true;
		} else {
			System.out.println("비밀번호가 일치하지 않습니다. 다시 확인해주세요.");
			return false;
		}
	}

	public void transferMoney(String sendAccount, String reciveAccount, String password, int money) {
//			account1 = m1(sendAccount)
//			account2 = m1(reciveAccount)
//			try{
//				m2(account1,password)
//				m3(account1,-money) //음수로 잔액이 충분해 출금이 가능해야 account2에 입금을 해주므로 출금먼저
//				m3(account2,money)
//			}catch
//				에러처리
//			}
	}
	public Account checkaccount2(ArrayList<Customer> allCustomerInfo , String account) {
		
		Account ac1 = new Account();
		Iterator<Customer> checkcustomer = allCustomerInfo.iterator();
		// Customer 동적배열 생성으로 생성된 배열 내 등록 번호가 있는지 순회하여 확인하는 Logic
		int cnt = 0;
		while (checkcustomer.hasNext()) {

			String numberMatch = checkcustomer.next().getOwnAccounts().get(cnt).getAccountNumber();

			if (account.equals(numberMatch)) {
				// 전화번호는 단일 Key이므로, 입력된 number와 numbermatch가 동일한 경우 해당 Customer 객체 반환
				ac1 = allCustomerInfo.get(cnt).getOwnAccounts().get(cnt);
				break;

			} else if (checkcustomer.hasNext() == false) {
				System.out.println("고객님의 정보를 확인 할 수 없습니다. 서비스 데스크에 문의하세요");
			}
			cnt++;
		}

		return ac1;
		
	}

	public void checkaccount(Customer c1) {

		int cnt = 0;
		System.out.println("***사용하실 계좌를 선택하세요*** ");
		for (cnt = 0; cnt < c1.getOwnAccounts().size(); cnt++) {
			System.out.println((cnt + 1) + ".  " + c1.getOwnAccounts().get(cnt).getAccountNumber());

		}

	}
	
	public boolean lastcheck(String x) {
		
		if(x.equals("Y") || x.equals("y")) {
			return true;
		}
		else {
			return false;
		}
	}

	// 등록된 고객정보인지 확인 하는 Logic
	public Customer checkcustomer(String number, ArrayList<Customer> allCustomerInfo) {

		Customer select1 = new Customer();
		Iterator<Customer> checkcustomer = allCustomerInfo.iterator();
		// Customer 동적배열 생성으로 생성된 배열 내 등록 번호가 있는지 순회하여 확인하는 Logic
		int cnt = 0;
		while (checkcustomer.hasNext()) {

			String numberMatch = checkcustomer.next().getCustomerNumber();

			if (number.equals(numberMatch)) {
				// 전화번호는 단일 Key이므로, 입력된 number와 numbermatch가 동일한 경우 해당 Customer 객체 반환
				select1 = allCustomerInfo.get(cnt);
				break;

			} else if (checkcustomer.hasNext() == false) {
				System.out.println("고객님의 정보를 확인 할 수 없습니다. 서비스 데스크에 문의하세요");
			}
			cnt++;
		}

		return select1;
	}

}
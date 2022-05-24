package bankingSystem.controller;

import java.util.ArrayList;
import java.util.Iterator;

import bankingSystem.dto.Account;
import bankingSystem.dto.Customer;
import bankingSystem.model.BankingModel;
import bankingSystem.view.FailView;
import bankingSystem.view.SuccessView;
import bankingexecption.BankingExecption;

public class BankingController {
	// Singleton patten 사용으로 controller 단에서 Model logic 호출하기 위한 instance 정의
	private static BankingModel service = BankingModel.getInstance();

	/**
	 * 입금 accountNumber : 계좌번호 money : 현재 잔고 depositmoney : 입금할 금액
	 */

	public static boolean depositMoney(String accountNumber, int money, int depositmoney) {
		if (accountNumber != null && money != 0 && depositmoney != 0) {
			return service.typingMoney(accountNumber, money, depositmoney);
		} else {
			FailView.printFail("입금 확인에 실패하였습니다. 입력 정보를 다시 확인해주세요.");
			return false;
		}
	}

	public static Customer checkcustomer(String number, ArrayList<Customer> allCustomerInfo) {

		if (number != null && allCustomerInfo != null) {
			return service.checkcustomer(number, allCustomerInfo);

		} else {
			FailView.printFail("고객님의 정보를 확인 할 수 없습니다. 서비스 데스크에 문의하세요");
		}

		return service.checkcustomer(number, allCustomerInfo);

	}

	public static String checkaccount(Customer c1) {

		if (c1 != null) {
			service.checkaccount(c1);
		} else {
			FailView.printFail("고객님의 정보를 확인 할 수 없습니다. 서비스 데스크에 문의하세요");
		}

		return null;

	}

	public static boolean passwordCheck(Account account, String password) {

		if (account != null && password != null) {
			return service.passwordCheck(account, password);
		} else {
			FailView.printFail("패스워드 입력 오류입니다.");
			return false;
		}

	}

	// MVC - Checkinputexecption 계좌 클래스 객체 인수 // number는 사용할계좌 //
	public static boolean Checkinputexecption(int number, Customer c) throws BankingExecption {

		try {
			return service.Checkinputexecption(number, c);

		} catch (BankingExecption e) {
			e.printStackTrace();
			FailView.printFail("잘못된 형식의 입력입니다.");
		}
		return false;

	}

	public static boolean lastcheck(String x) {

		if (x != "null") {
			return service.lastcheck(x);
		} else {
			return false;
		}
	}

	public static Account checkaccount2(ArrayList<Customer> allCustomerInfo , String account) {

		if (allCustomerInfo != null && account != null) {
			return service.checkaccount2(allCustomerInfo, account);
		} else {
			FailView.printFail("확인 할 수 없는 계좌입니다. ");
		}
		return service.checkaccount2(allCustomerInfo, account);

	}

}

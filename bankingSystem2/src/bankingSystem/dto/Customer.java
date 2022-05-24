package bankingSystem.dto;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import bankingSystem.dto.Account;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Customer {

	private String customerNumber;
	private String customerName;
	private ArrayList<Account> ownAccounts = new ArrayList<Account>();

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(" 고객번호 : ");
		builder.append(customerNumber);
		builder.append(" 고객명 : ");
		builder.append(customerName);
		builder.append(" 고객계좌 : ");
		builder.append(ownAccounts);

		return builder.toString();
	}

}

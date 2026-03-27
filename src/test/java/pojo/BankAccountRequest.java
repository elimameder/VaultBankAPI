package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class BankAccountRequest {
    @JsonProperty("name")
    private String name;

    @JsonProperty("account_number")
    private String accountNumber;

    @JsonProperty("type")
    private String type;

    @JsonProperty("account_group")
    private String accountGroup;

    @JsonProperty("available_balance")
    private Double availableBalance;

    public BankAccountRequest() {
    }

    public BankAccountRequest(String name, String accountNumber, String type,
                              String accountGroup, Double availableBalance) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.type = type;
        this.accountGroup = accountGroup;
        this.availableBalance = availableBalance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccountGroup() {
        return accountGroup;
    }

    public void setAccountGroup(String accountGroup) {
        this.accountGroup = accountGroup;
    }

    public Double getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(Double availableBalance) {
        this.availableBalance = availableBalance;
    }
}



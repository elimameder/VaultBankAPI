package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transactions {

    @JsonProperty("id")
    private String id;

    @JsonProperty("user_id")
    private String user_id;

    @JsonProperty("account_id")
    private String account_id;

    @JsonProperty("description")
    private String description;

    @JsonProperty("amount")
    private String amount;

    @JsonProperty("date")
    private String date;

    @JsonProperty("category")
    private String category;

    @JsonProperty("type")
    private String type;

    @JsonProperty("method")
    private String method;

    @JsonProperty("created_at")
    private String created_at;


//    "id": "37aa7621-b11b-4d61-9caa-af21bcfdfcb1",
//            "user_id": "d87fd373-a02d-411d-8563-c23511cf9f7c",
//            "account_id": "1422e3f8-e2b0-45ea-950b-e1ac9cbd6ec7",
//            "description": "Uber Eats",
//            "amount": -32.50,
//            "date": "2026-03-03",
//            "category": "Food",
//            "type": "debit",
//            "method": "card",
//            "created_at": "2026-03-08T06:19:39.208526+00:00"
//

}

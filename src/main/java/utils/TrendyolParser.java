package utils;

import entity.ClCard;
import org.json.JSONArray;
import org.json.JSONObject;


import static utils.Constants.*;
import static utils.Variables.clCards;

public class TrendyolParser {

    public void parse(JSONObject jsonObject) {
        clearStaticVars();

        JSONArray contentArray = jsonObject.getJSONArray(CONTENT_KEY);
        for (int i = 0; i < contentArray.length(); i++) {
            JSONObject content = contentArray.getJSONObject(i);
            ClCard clCard = createClCard(content);
            if(!isClCardListContains(clCard))
                clCards.add(clCard);
        }
    }


    private ClCard createClCard(JSONObject content) {
        ClCard clCard = new ClCard();
        clCard.setFirstName(content.getString(FIRST_NAME_KEY));
        clCard.setLastName(content.getString(LAST_NAME_KEY));
        clCard.setEmail(content.getString(MAIL_KEY));
        if(!content.isNull(TAX_KEY))
            clCard.setTaxNumber(content.getString(TAX_KEY));
        if (!content.isNull(TCKN_KEY))
            clCard.setTckn(content.getString(TCKN_KEY));
        clCard.setDate(content.getLong(ORDER_DATE_KEY));

        JSONObject invoiceAddressObject = content.getJSONObject(INVOICE_ADDRESS_KEY);
        clCard.setAddress1(invoiceAddressObject.getString(ADDRESS_1_KEY));
        clCard.setAddress2(invoiceAddressObject.getString(ADDRESS_2_KEY));
        clCard.setCity(invoiceAddressObject.getString(CITY_KEY));
        clCard.setDistrict(invoiceAddressObject.getString(DISTRICT_KEY));

        return clCard;
    }

    private boolean isClCardListContains(ClCard clCard) {
        for (ClCard card : clCards) {
            if (card.equals(clCard)) {
                return true;
            }
        }
        return false;
    }

    private void clearStaticVars() {
        clCards.clear();
    }
}

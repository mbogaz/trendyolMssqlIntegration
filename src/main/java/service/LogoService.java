package service;

import com.microsoft.sqlserver.jdbc.StringUtils;
import entity.ClCard;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;

import static utils.Constants.*;

public class LogoService {

    private SqlConnector sqlConnector;

    public LogoService() {
        sqlConnector = new SqlConnector();
    }

    public boolean createClCard(ClCard clCard)  {
        HashMap<String, String> map = new HashMap<>();

        map.put("CODE", sqlConnector.getLastCode(clCardTableName, CODE_KEY, LOGICAL_REF_KEY, "BRY"));
        map.put("DEFINITION_", "'" + clCard.getFirstName() + " " + clCard.getLastName() + "'");
        map.put("ADDR1", "'" + clCard.getAddress1() + " " + clCard.getAddress2() + "'");
        map.put("ADDR2", "'" + clCard.getCity() + " " + clCard.getDistrict() + "'");
        if (!StringUtils.isEmpty(clCard.getTaxNumber())) {
            map.put("TAXNR", "'" + clCard.getTaxNumber() + "'");
        } else if (StringUtils.isEmpty(clCard.getTaxNumber()) && StringUtils.isEmpty(clCard.getTckn())) {
            map.put("TAXNR", DEFAULT_TAX_NUM);
        }
        map.put("EMAILADDR", "'" + clCard.getEmail() + "'");

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(clCard.getDate());
        SimpleDateFormat sdfSource = new SimpleDateFormat("yyyy-MM-ddThh:mm:ss");
        String formattedDate = sdfSource.format(calendar.getTime());
        map.put("CAPIBLOCK_CREADEDDATE", "'" + formattedDate + "'");
        map.put("CAPIBLOCK_CREATEDHOUR", String.valueOf(calendar.get(Calendar.HOUR)));
        map.put("CAPIBLOCK_CREATEDMIN", String.valueOf(calendar.get(Calendar.MINUTE)));
        map.put("CAPIBLOCK_CREATEDSEC", String.valueOf(calendar.get(Calendar.SECOND)));
        if (!StringUtils.isEmpty(clCard.getTckn())) {
            map.put("TCKNO", "'" + clCard.getTckn() + "'");
        }
        map.put("LOWLEVELCODES1", sqlConnector.getLastCode(clCardTableName, CODE_KEY, LOGICAL_REF_KEY, "LOWLEVELCODES1"));//<çek bi sonrakini yaz>

        map.put("ACTIVE", "0");
        map.put("CARDTYPE", "3");
        map.put("CLANGUAGE", "1");
        map.put("RECSTATUS", "1");
        map.put("CAPIBLOCK_CREATEDBY", "1");
        map.put("PAYMENTTYPE", "1");
        map.put("CLORDFREQ", "1");
        map.put("PURCHBRWS", "1");
        map.put("SALESBRWS", "1");
        map.put("IMPBRWS", "1");
        map.put("EXPBRWS", "1");
        map.put("FINBRWS", "1");
        map.put("PROFILEID", "2");
        map.put("SHIPBEGTIME1", "134217728");
        map.put("SHIPENDTIME1", "288817152");
        map.put("GUID", UUID.randomUUID().toString());
        map.put("CREATEWHFICHE", "1");
        map.put("PROFILEIDDESP", "1");

        sqlConnector.executeInsertQuery(clCardTableName, map);

        return true;
    }


}

package utils;

public class Constants {

    //http headers
    public static String HEADER_USER_AGENT_KEY = "User-Agent";
    public static String HEADER_ACCEPT_KEY = "Accept";
    public static String HEADER_ACCEPT_VALUE = "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8";
    public static String HEADER_ACCEPT_LANGUAGE_KEY = "Accept-Language";
    public static String HEADER_ACCEPT_LANGUAGE_VALUE = "en-US,en;q=0.5";

    //Carisql-server credentials
    public static String SQL_USER_NAME = "sa";
    public static String SQL_PASSWORD = "ArmodesK2012";
    public static String SQL_SERVER_NAME = "192.168.1.108";
    public static String SQL_DB_NAME = "GO3";

    //api links
    public static String domain = "";

    //sql keys
    public static String INSERT_INTO_KEY = "INSERT INTO ";
    public static String SELECT_KEY = "SELECT ";
    public static String TOP_ONE_KEY = "TOP 1 ";
    public static String FROM_KEY = " FROM ";
    public static String WHERE_KEY = " WHERE ";
    public static String LIKE_KEY = " LIKE ";
    public static String ORDER_BY_KEY = " ORDER BY ";
    public static String DESC_KEY = " DESC";

    //trendyol
    static final String CONTENT_KEY = "content";
    static final String FIRST_NAME_KEY = "customerFirstName";
    static final String LAST_NAME_KEY = "customerLastName";
    static final String MAIL_KEY = "customerEmail";
    static final String TCKN_KEY = "tcIdentityNumber";
    static final String TAX_KEY = "taxNumber";
    static final String INVOICE_ADDRESS_KEY = "invoiceAddress";
    static final String ADDRESS_1_KEY = "address1";
    static final String ADDRESS_2_KEY = "address2";
    static final String CITY_KEY = "city";
    static final String DISTRICT_KEY = "district";
    static final String TRACKING_KEY = "cargoTrackingNumber";
    static final String ORDER_DATE_KEY = "orderDate";

    //logo
    public static String clCardTableName = "[GO3].[dbo].[LG_001_CLCARD]";
    public static String CODE_KEY = "CODE";
    public static String LOGICAL_REF_KEY = "LOGICALREF";
    public static String DEFAULT_TAX_NUM = "'1111111111'";

    //file io
    public static String clCardsFileName = "clCards.txt";
}

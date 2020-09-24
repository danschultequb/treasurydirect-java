package qub;

public class SearchSecuritiesOptions implements MutableMap<String,String>
{
    private static final String today = "today";
    private static final String notNull = "notNull";

    private final MutableMap<String,String> options;

    private SearchSecuritiesOptions()
    {
        this.options = Map.create();
    }

    public static SearchSecuritiesOptions create()
    {
        return new SearchSecuritiesOptions();
    }
    
    @Override
    public SearchSecuritiesOptions set(String propertyName, String propertyValue)
    {
        PreCondition.assertNotNullAndNotEmpty(propertyName, "propertyName");
        PreCondition.assertNotNullAndNotEmpty(propertyValue, "propertyValue");

        this.options.set(propertyName, propertyValue);

        return this;
    }

    public SearchSecuritiesOptions setDate(String propertyName, Date propertyValue)
    {
        PreCondition.assertNotNullAndNotEmpty(propertyName, "propertyName");
        PreCondition.assertNotNull(propertyValue, "propertyValue");

        return this.set(propertyName, SearchSecuritiesOptions.dateToString(propertyValue));
    }

    public SearchSecuritiesOptions setNotNull(String propertyName)
    {
        PreCondition.assertNotNullAndNotEmpty(propertyName, "propertyName");

        return this.set(propertyName, SearchSecuritiesOptions.notNull);
    }

    public SearchSecuritiesOptions setToday(String propertyName)
    {
        PreCondition.assertNotNullAndNotEmpty(propertyName, "propertyName");

        return this.set(propertyName, SearchSecuritiesOptions.today);
    }

    public SearchSecuritiesOptions setAnnouncementDate(String announcementDate)
    {
        PreCondition.assertNotNullAndNotEmpty(announcementDate, "announcementDate");

        return this.set(TreasuryDirectSecurity.announcementDatePropertyName, announcementDate);
    }

    public SearchSecuritiesOptions setAnnouncementDateToToday()
    {
        return this.setAnnouncementDate(SearchSecuritiesOptions.today);
    }

    public SearchSecuritiesOptions setAnnouncementDate(Date announcementDate)
    {
        PreCondition.assertNotNull(announcementDate, "announcementDate");

        return this.setAnnouncementDate(SearchSecuritiesOptions.dateToString(announcementDate));
    }

    public SearchSecuritiesOptions setAuctionDate(String auctionDate)
    {
        PreCondition.assertNotNullAndNotEmpty(auctionDate, "auctionDate");

        return this.set(TreasuryDirectSecurity.auctionDatePropertyName, auctionDate);
    }

    public SearchSecuritiesOptions setAuctionDateToToday()
    {
        return this.setToday(TreasuryDirectSecurity.auctionDatePropertyName);
    }

    public SearchSecuritiesOptions setAuctionDate(Date auctionDate)
    {
        PreCondition.assertNotNull(auctionDate, "auctionDate");

        return this.setDate(TreasuryDirectSecurity.auctionDatePropertyName, auctionDate);
    }

    public SearchSecuritiesOptions setAuctionDateYear(String auctionDateYear)
    {
        PreCondition.assertNotNullAndNotEmpty(auctionDateYear, "auctionDateYear");

        return this.set(TreasuryDirectSecurity.auctionDateYearPropertyName, auctionDateYear);
    }

    public SearchSecuritiesOptions setAuctionDateYear(int auctionDateYear)
    {
        PreCondition.assertGreaterThanOrEqualTo(auctionDateYear, 0, "auctionDateYear");

        return this.set(TreasuryDirectSecurity.auctionDateYearPropertyName, Integers.toString(auctionDateYear));
    }

    public SearchSecuritiesOptions setCusip(String cusip)
    {
        PreCondition.assertNotNullAndNotEmpty(cusip, "cusip");

        return this.set(TreasuryDirectSecurity.cusipPropertyName, cusip);
    }

    public SearchSecuritiesOptions setInterestRate(String interestRate)
    {
        PreCondition.assertNotNullAndNotEmpty(interestRate, "interestRate");

        return this.set(TreasuryDirectSecurity.interestRatePropertyName, interestRate);
    }

    public SearchSecuritiesOptions setInterestRate(double interestRate)
    {
        return this.setInterestRate(Doubles.toString(interestRate));
    }

    public SearchSecuritiesOptions setIssueDate(String issueDate)
    {
        PreCondition.assertNotNullAndNotEmpty(issueDate, "issueDate");

        return this.set(TreasuryDirectSecurity.issueDatePropertyName, issueDate);
    }

    public SearchSecuritiesOptions setIssueDateToToday()
    {
        return this.setToday(TreasuryDirectSecurity.issueDatePropertyName);
    }

    public SearchSecuritiesOptions setIssueDate(Date issueDate)
    {
        PreCondition.assertNotNull(issueDate, "issueDate");

        return this.setDate(TreasuryDirectSecurity.issueDatePropertyName, issueDate);
    }

    public SearchSecuritiesOptions setMaturityDate(String maturityDate)
    {
        PreCondition.assertNotNullAndNotEmpty(maturityDate, "maturityDate");

        return this.set(TreasuryDirectSecurity.maturityDatePropertyName, maturityDate);
    }

    public SearchSecuritiesOptions setMaturityDateToToday()
    {
        return this.setToday(TreasuryDirectSecurity.maturityDatePropertyName);
    }

    public SearchSecuritiesOptions setMaturityDate(Date maturityDate)
    {
        PreCondition.assertNotNull(maturityDate, "maturityDate");

        return this.setDate(TreasuryDirectSecurity.maturityDatePropertyName, maturityDate);
    }

    public SearchSecuritiesOptions setSecurityType(String securityType)
    {
        PreCondition.assertNotNullAndNotEmpty(securityType, "securityType");

        return this.set(TreasuryDirectSecurity.securityTypePropertyName, securityType);
    }

    public SearchSecuritiesOptions setSecurityType(TreasuryDirectSecurityType securityType)
    {
        PreCondition.assertNotNull(securityType, "securityType");

        return this.setSecurityType(securityType.toString());
    }

    public SearchSecuritiesOptions setSecurityTerm(String securityTerm)
    {
        PreCondition.assertNotNullAndNotEmpty(securityTerm, "securityTerm");

        return this.set(TreasuryDirectSecurity.securityTermPropertyName, securityTerm);
    }

    @Override
    public SearchSecuritiesOptions clear()
    {
        this.options.clear();
        return this;
    }

    @Override
    public Result<String> remove(String propertyName)
    {
        return this.options.remove(propertyName);
    }

    @Override
    public boolean containsKey(String propertyName)
    {
        return this.options.containsKey(propertyName);
    }

    @Override
    public Result<String> get(String propertyName)
    {
        return this.options.get(propertyName);
    }

    @Override
    public Iterable<String> getKeys()
    {
        return this.options.getKeys();
    }

    @Override
    public Iterable<String> getValues()
    {
        return this.options.getValues();
    }

    @Override
    public Iterator<MapEntry<String, String>> iterate()
    {
        return this.options.iterate();
    }
    
    public JSONObject toJson()
    {
        final JSONObject result = JSONObject.create();
        for (final MapEntry<String,String> option : this)
        {
            result.setString(option.getKey(), option.getValue());
        }
        
        PostCondition.assertNotNull(result, "result");
        
        return result;
    }

    @Override
    public String toString()
    {
        return this.toJson().toString();
    }
    
    @Override
    public boolean equals(Object rhs)
    {
        return rhs instanceof SearchSecuritiesOptions && this.equals((SearchSecuritiesOptions)rhs);
    }
    
    public boolean equals(SearchSecuritiesOptions rhs)
    {
        return rhs != null && this.options.equals(rhs.options);
    }

    public static String dateToString(Date date)
    {
        PreCondition.assertNotNull(date, "date");

        final String year = Integers.toString(date.getYear());
        final String month = Integers.toString(date.getMonth());
        final String dayOfMonth = Integers.toString(date.getDayOfMonth());
        return year + '-' + month + '-' + dayOfMonth;
    }
}

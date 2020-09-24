package qub;

public interface TreasuryDirect
{
    static RealTreasuryDirect create(HttpClient httpClient)
    {
        return RealTreasuryDirect.create(httpClient);
    }

    static RealTreasuryDirect create(HttpClient httpClient, String baseUrl)
    {
        return RealTreasuryDirect.create(httpClient, baseUrl);
    }

    default Result<TreasuryDirectSecurity> getSecurity(String cusip, DateTime issueDate)
    {
        PreCondition.assertNotNullAndNotEmpty(cusip, "cusip");
        PreCondition.assertNotNull(issueDate, "issueDate");

        return this.getSecurity(cusip, issueDate.toDate());
    }

    default Result<TreasuryDirectSecurity> getSecurity(String cusip, Date issueDate)
    {
        PreCondition.assertNotNullAndNotEmpty(cusip, "cusip");
        PreCondition.assertNotNull(issueDate, "issueDate");

        final String issueMonth = Integers.toString(issueDate.getMonth());
        final String issueDayOfMonth = Integers.toString(issueDate.getDayOfMonth());
        final String issueYear = Integers.toString(issueDate.getYear());
        return this.getSecurity(cusip, issueMonth, issueDayOfMonth, issueYear);
    }

    Result<TreasuryDirectSecurity> getSecurity(String cusip, String issueMonth, String issueDayOfMonth, String issueYear);

    default Result<Iterable<TreasuryDirectSecurity>> getAnnouncedSecurities()
    {
        return this.getAnnouncedSecurities(GetAnnouncedSecuritiesOptions.create());
    }

    Result<Iterable<TreasuryDirectSecurity>> getAnnouncedSecurities(GetAnnouncedSecuritiesOptions options);

    default Result<Iterable<TreasuryDirectSecurity>> getAuctionedSecurities()
    {
        return this.getAuctionedSecurities(GetAuctionedSecuritiesOptions.create());
    }

    Result<Iterable<TreasuryDirectSecurity>> getAuctionedSecurities(GetAuctionedSecuritiesOptions options);

    Result<Iterable<TreasuryDirectSecurity>> getSecuritiesByType(TreasuryDirectSecurityType type);

    Result<Iterable<TreasuryDirectSecurity>> searchSecurities(SearchSecuritiesOptions options);
}

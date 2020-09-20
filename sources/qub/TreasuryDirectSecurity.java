package qub;

public interface TreasuryDirectSecurity
{
    String announcementDatePropertyName = "announcementDate";
    String auctionDatePropertyName = "auctionDate";
    String auctionDateYearPropertyName = "auctionDateYear";
    String cusipPropertyName = "cusip";
    String interestRatePropertyName = "interestRate";
    String issueDatePropertyName = "issueDate";
    String maturityDatePropertyName = "maturityDate";
    String securityTypePropertyName = "securityType";
    String securityTermPropertyName = "securityTerm";

    default Result<DateTime> getAnnouncementDate()
    {
        return this.getDateTime(TreasuryDirectSecurity.announcementDatePropertyName);
    }

    default Result<DateTime> getAuctionDate()
    {
        return this.getDateTime(TreasuryDirectSecurity.auctionDatePropertyName);
    }

    default Result<Double> getAuctionDateYear()
    {
        return this.getNumber(TreasuryDirectSecurity.auctionDateYearPropertyName);
    }

    default Result<String> getCusip()
    {
        return this.getString(TreasuryDirectSecurity.cusipPropertyName);
    }

    default Result<Double> getInterestRate()
    {
        return this.getNumber(TreasuryDirectSecurity.interestRatePropertyName);
    }

    default Result<DateTime> getIssueDate()
    {
        return this.getDateTime(TreasuryDirectSecurity.issueDatePropertyName);
    }

    default Result<DateTime> getMaturityDate()
    {
        return this.getDateTime(TreasuryDirectSecurity.maturityDatePropertyName);
    }

    default Result<String> getSecurityType()
    {
        return this.getString(TreasuryDirectSecurity.securityTypePropertyName);
    }

    default Result<String> getSecurityTerm()
    {
        return this.getString(TreasuryDirectSecurity.securityTermPropertyName);
    }

    /**
     * Get the String value of the property with the provided name.
     * @param propertyName The name of the property to get.
     * @return The String value of the property with the provided name.
     */
    Result<String> getString(String propertyName);

    /**
     * Get the DateTime value of the property with the provided name.
     * @param propertyName The name of the property to get.
     * @return The DateTime value of the property with the provided name.
     */
    default Result<DateTime> getDateTime(String propertyName)
    {
        PreCondition.assertNotNullAndNotEmpty(propertyName, "propertyName");

        return Result.create(() ->
        {
            String propertyValue = this.getString(propertyName).await();
            if (!Strings.isNullOrEmpty(propertyValue) && !propertyValue.endsWith("Z"))
            {
                propertyValue += "Z";
            }
            return TreasuryDirectSecurity.parse(propertyName, propertyValue, "DateTime", DateTime::parse);
        });
    }

    /**
     * Get the Date value of the property with the provided name.
     * @param propertyName The name of the property to get.
     * @return The Date value of the property with the provided name.
     */
    default Result<Date> getDate(String propertyName)
    {
        PreCondition.assertNotNullAndNotEmpty(propertyName, "propertyName");

        return Result.create(() ->
        {
            return this.getDateTime(propertyName).await().toDate();
        });
    }

    /**
     * Get the Double value of the property with the provided name.
     * @param propertyName The name of the property to get.
     * @return The Double value of the property with the provided name.
     */
    default Result<Double> getNumber(String propertyName)
    {
        PreCondition.assertNotNullAndNotEmpty(propertyName, "propertyName");

        return Result.create(() ->
        {
            final String propertyValue = this.getString(propertyName).await();
            return TreasuryDirectSecurity.parse(propertyName, propertyValue, "number", Doubles::parse);
        });
    }

    static <T> T parse(String propertyName, String propertyValue, String parseTypeName, Function1<String,Result<T>> parseFunction)
    {
        PreCondition.assertNotNullAndNotEmpty(propertyName, "propertyName");
        PreCondition.assertNotNullAndNotEmpty(parseTypeName, "parseTypeName");
        PreCondition.assertNotNull(parseFunction, "parseFunction");

        if (Strings.isNullOrEmpty(propertyValue))
        {
            throw new ParseException(Strings.escapeAndQuote(propertyName) + " value (" + Strings.escapeAndQuote(propertyValue) + ") is not a valid " + parseTypeName + ".");
        }

        return parseFunction.run(propertyValue)
            .convertError(ParseException.class, () -> new ParseException(Strings.escapeAndQuote(propertyName) + " value (" + Strings.escapeAndQuote(propertyValue) + ") is not a valid " + parseTypeName + "."))
            .await();
    }
}

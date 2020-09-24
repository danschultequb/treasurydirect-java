package qub;

/**
 * A real implementation of the TreasuryDirect interface that makes HTTP requests to
 * treasurydirect.gov.
 */
public class RealTreasuryDirect implements TreasuryDirect
{
    private static final String defaultBaseUrl = "https://www.treasurydirect.gov/TA_WS/";

    private final HttpClient httpClient;
    private final String baseUrl;

    private RealTreasuryDirect(HttpClient httpClient, String baseUrl)
    {
        PreCondition.assertNotNull(httpClient, "httpClient");
        PreCondition.assertNotNullAndNotEmpty(baseUrl, "baseUrl");

        this.httpClient = httpClient;
        this.baseUrl = baseUrl;
    }

    public static RealTreasuryDirect create(HttpClient httpClient)
    {
        return RealTreasuryDirect.create(httpClient, RealTreasuryDirect.defaultBaseUrl);
    }

    public static RealTreasuryDirect create(HttpClient httpClient, String baseUrl)
    {
        return new RealTreasuryDirect(httpClient, baseUrl);
    }

    @Override
    public Result<TreasuryDirectSecurity> getSecurity(String cusip, String issueMonth, String issueDayOfMonth, String issueYear)
    {
        PreCondition.assertNotNullAndNotEmpty(cusip, "cusip");
        PreCondition.assertNotNullAndNotEmpty(issueMonth, "issueMonth");
        PreCondition.assertNotNullAndNotEmpty(issueDayOfMonth, "issueDayOfMonth");
        PreCondition.assertNotNullAndNotEmpty(issueYear, "issueYear");

        return Result.create(() ->
        {
            final URL url = URL.parse(this.baseUrl + "securities/" + cusip + "/" + issueMonth + "/" + issueDayOfMonth + "/" + issueYear).await();
            url.setQueryParameter("format", "json");

            TreasuryDirectSecurity result;
            try (final HttpResponse response = this.httpClient.get(url).await())
            {
                final String responseBody = CharacterReadStream.create(response.getBody()).readEntireString().await();
                if (responseBody.equalsIgnoreCase("No data"))
                {
                    throw new NotFoundException("No TreasuryDirect security found for CUSIP: " + cusip + ", IssueMonth: " + issueMonth + ", IssueDayOfMonth: " + issueDayOfMonth + ", and IssueYear: " + issueYear + ".");
                }

                final JSONObject responseBodyObject = JSON.parseObject(responseBody).await();
                result = MutableTreasuryDirectSecurity.create(responseBodyObject);
            }

            PostCondition.assertNotNull(result, "result");

            return result;
        });
    }

    @Override
    public Result<Iterable<TreasuryDirectSecurity>> getAnnouncedSecurities(GetAnnouncedSecuritiesOptions options)
    {
        PreCondition.assertNotNull(options, "options");

        return Result.create(() ->
        {
            final URL url = URL.parse(this.baseUrl + "securities/announced").await();
            url.setQueryParameter("format", "json");

            final Integer pageSize = options.getPageSize();
            if (pageSize != null)
            {
                url.setQueryParameter("pagesize", pageSize.toString());
            }

            final TreasuryDirectSecurityType type = options.getType();
            if (type != null)
            {
                url.setQueryParameter("type", type.toString());
            }

            final Integer days = options.getDays();
            if (days != null)
            {
                url.setQueryParameter("days", days.toString());
            }

            final Boolean reopening = options.getReopening();
            if (reopening != null)
            {
                url.setQueryParameter("reopening", reopening ? "Yes" : "No");
            }

            final Iterable<TreasuryDirectSecurity> result;
            try (final HttpResponse response = this.httpClient.get(url).await())
            {
                final JSONArray responseBodyArray = JSON.parseArray(response.getBody()).await();
                result = responseBodyArray
                    .instanceOf(JSONObject.class)
                    .map(MutableTreasuryDirectSecurity::create);
            }

            PostCondition.assertNotNull(result, "result");

            return result;
        });
    }

    @Override
    public Result<Iterable<TreasuryDirectSecurity>> getAuctionedSecurities(GetAuctionedSecuritiesOptions options)
    {
        PreCondition.assertNotNull(options, "options");

        return Result.create(() ->
        {
            final URL url = URL.parse(this.baseUrl + "securities/auctioned").await();
            url.setQueryParameter("format", "json");

            final Integer pageSize = options.getPageSize();
            if (pageSize != null)
            {
                url.setQueryParameter("pagesize", pageSize.toString());
            }

            final TreasuryDirectSecurityType type = options.getType();
            if (type != null)
            {
                url.setQueryParameter("type", type.toString());
            }

            final Integer days = options.getDays();
            if (days != null)
            {
                url.setQueryParameter("days", days.toString());
            }

            final Boolean reopening = options.getReopening();
            if (reopening != null)
            {
                url.setQueryParameter("reopening", reopening ? "Yes" : "No");
            }

            final Iterable<TreasuryDirectSecurity> result;
            try (final HttpResponse response = this.httpClient.get(url).await())
            {
                final JSONArray responseBodyArray = JSON.parseArray(response.getBody()).await();
                result = responseBodyArray
                    .instanceOf(JSONObject.class)
                    .map(MutableTreasuryDirectSecurity::create);
            }

            PostCondition.assertNotNull(result, "result");

            return result;
        });
    }

    @Override
    public Result<Iterable<TreasuryDirectSecurity>> getSecuritiesByType(TreasuryDirectSecurityType type)
    {
        PreCondition.assertNotNull(type, "type");

        return Result.create(() ->
        {
            final URL url = URL.parse(this.baseUrl + "securities/" + type).await();
            url.setQueryParameter("format", "json");

            final Iterable<TreasuryDirectSecurity> result;
            try (final HttpResponse response = this.httpClient.get(url).await())
            {
                final JSONArray responseBodyArray = JSON.parseArray(response.getBody()).await();
                result = responseBodyArray
                    .instanceOf(JSONObject.class)
                    .map(MutableTreasuryDirectSecurity::create);
            }

            PostCondition.assertNotNull(result, "result");

            return result;
        });
    }

    @Override
    public Result<Iterable<TreasuryDirectSecurity>> searchSecurities(SearchSecuritiesOptions options)
    {
        PreCondition.assertNotNull(options, "options");

        return Result.create(() ->
        {
            final URL url = URL.parse(this.baseUrl + "securities/search").await();
            url.setQueryParameter("format", "json");

            for (final MapEntry<String,String> option : options)
            {
                url.setQueryParameter(option.getKey(), option.getValue());
            }

            final Iterable<TreasuryDirectSecurity> result;
            try (final HttpResponse response = this.httpClient.get(url).await())
            {
                final JSONArray responseBodyArray = JSON.parseArray(response.getBody()).await();
                result = responseBodyArray
                    .instanceOf(JSONObject.class)
                    .map(MutableTreasuryDirectSecurity::create);
            }

            PostCondition.assertNotNull(result, "result");

            return result;
        });
    }
}

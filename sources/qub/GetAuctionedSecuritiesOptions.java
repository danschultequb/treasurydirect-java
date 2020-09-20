package qub;

/**
 * Options that can be passed to TreasuryDirect.getAnnouncedSecurities().
 */
public class GetAuctionedSecuritiesOptions
{
    private Integer pageSize;
    private TreasuryDirectSecurityType type;
    private Integer days;
    private Boolean reopening;

    private GetAuctionedSecuritiesOptions()
    {
    }

    public static GetAuctionedSecuritiesOptions create()
    {
        return new GetAuctionedSecuritiesOptions();
    }

    /**
     * Set the maximum number of results to return.
     * @param pageSize The maximum number of results to return.
     * @return This object for method chaining.
     */
    public GetAuctionedSecuritiesOptions setPageSize(int pageSize)
    {
        PreCondition.assertGreaterThanOrEqualTo(pageSize, 1, "pageSize");

        this.pageSize = pageSize;

        return this;
    }

    /**
     * Get the maximum number of results to return.
     * @return The maximum number of results to return.
     */
    public Integer getPageSize()
    {
        return this.pageSize;
    }

    /**
     * Set the type of securities to return.
     * @param type The type of securities to return.
     * @return This object for method chaining.
     */
    public GetAuctionedSecuritiesOptions setType(TreasuryDirectSecurityType type)
    {
        PreCondition.assertNotNull(type, "type");

        this.type = type;

        return this;
    }

    /**
     * Get the type of securities to return.
     * @return The type of securities to return.
     */
    public TreasuryDirectSecurityType getType()
    {
        return this.type;
    }

    /**
     * Set the number of previous days worth of results to return. 0 will provide results for today.
     * @param days The number of previous days worth of results to return.
     * @return This object for method chaining.
     */
    public GetAuctionedSecuritiesOptions setDays(int days)
    {
        PreCondition.assertGreaterThanOrEqualTo(days, 0, "days");

        this.days = days;

        return this;
    }

    /**
     * Get the number of previous days worth of results to return.
     * @return The number of previous days worth of results to return.
     */
    public Integer getDays()
    {
        return this.days;
    }

    /**
     * Set whether or not to filter securities by reopening status.
     * @param reopening Whether or not to filter securities by reopening status.
     * @return This object for method chaining.
     */
    public GetAuctionedSecuritiesOptions setReopening(boolean reopening)
    {
        this.reopening = reopening;

        return this;
    }

    /**
     * Get whether or not to filter securities by reopening status.
     * @return Whether or not to filter securities by reopening status.
     */
    public Boolean getReopening()
    {
        return this.reopening;
    }

    public JSONObject toJson()
    {
        final JSONObject result = JSONObject.create();
        if (this.pageSize != null)
        {
            result.setNumber("pagesize", this.pageSize);
        }
        if (this.type != null)
        {
            result.setString("type", this.type.toString());
        }
        if (this.days != null)
        {
            result.setNumber("days", this.days);
        }
        if (this.reopening != null)
        {
            result.setBoolean("reopening", this.reopening);
        }

        PostCondition.assertNotNull(result, "result");

        return result;
    }

    @Override
    public String toString()
    {
        return this.toJson().toString();
    }
}

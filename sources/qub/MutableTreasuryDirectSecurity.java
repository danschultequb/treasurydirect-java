package qub;

public class MutableTreasuryDirectSecurity implements TreasuryDirectSecurity
{
    private final JSONObject json;

    private MutableTreasuryDirectSecurity(JSONObject json)
    {
        PreCondition.assertNotNull(json, "json");

        this.json = json;
    }

    public static MutableTreasuryDirectSecurity create()
    {
        return MutableTreasuryDirectSecurity.create(JSONObject.create());
    }

    public static MutableTreasuryDirectSecurity create(JSONObject json)
    {
        return new MutableTreasuryDirectSecurity(json);
    }

    @Override
    public Result<String> getString(String propertyName)
    {
        PreCondition.assertNotNullAndNotEmpty(propertyName, "propertyName");

        return this.json.getString(propertyName);
    }
}

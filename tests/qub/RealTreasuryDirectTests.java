package qub;

public interface RealTreasuryDirectTests
{
    static void test(TestRunner runner)
    {
        runner.testGroup(RealTreasuryDirect.class, () ->
        {
            TreasuryDirectTests.test(runner, (Test test) ->
            {
                return RealTreasuryDirect.create(HttpClient.create(test.getNetwork()));
            });

            runner.testGroup("create(HttpClient)", () ->
            {
                runner.test("with null httpClient", (Test test) ->
                {
                    test.assertThrows(() -> RealTreasuryDirect.create(null),
                        new PreConditionFailure("httpClient cannot be null."));
                });
            });

            runner.testGroup("create(HttpClient,String)", () ->
            {
                runner.test("with null httpClient", (Test test) ->
                {
                    test.assertThrows(() -> RealTreasuryDirect.create(null, "fake-base-url"),
                        new PreConditionFailure("httpClient cannot be null."));
                });

                runner.test("with null baseUrl", (Test test) ->
                {
                    final HttpClient httpClient = HttpClient.create(test.getNetwork());
                    test.assertThrows(() -> RealTreasuryDirect.create(httpClient, null),
                        new PreConditionFailure("baseUrl cannot be null."));
                });

                runner.test("with empty baseUrl", (Test test) ->
                {
                    final HttpClient httpClient = HttpClient.create(test.getNetwork());
                    test.assertThrows(() -> RealTreasuryDirect.create(httpClient, ""),
                        new PreConditionFailure("baseUrl cannot be empty."));
                });
            });
        });
    }
}

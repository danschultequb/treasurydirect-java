package qub;

public interface TreasuryDirectTests
{
    static void test(TestRunner runner)
    {
        runner.testGroup(TreasuryDirect.class, () ->
        {
            runner.testGroup("create(HttpClient)", () ->
            {
                runner.test("with null httpClient", (Test test) ->
                {
                    test.assertThrows(() -> TreasuryDirect.create(null),
                        new PreConditionFailure("httpClient cannot be null."));
                });
            });

            runner.testGroup("create(HttpClient,String)", () ->
            {
                runner.test("with null httpClient", (Test test) ->
                {
                    test.assertThrows(() -> TreasuryDirect.create(null, "fake-base-url"),
                        new PreConditionFailure("httpClient cannot be null."));
                });

                runner.test("with null baseUrl", (Test test) ->
                {
                    final HttpClient httpClient = HttpClient.create(test.getNetwork());
                    test.assertThrows(() -> TreasuryDirect.create(httpClient, null),
                        new PreConditionFailure("baseUrl cannot be null."));
                });

                runner.test("with empty baseUrl", (Test test) ->
                {
                    final HttpClient httpClient = HttpClient.create(test.getNetwork());
                    test.assertThrows(() -> TreasuryDirect.create(httpClient, ""),
                        new PreConditionFailure("baseUrl cannot be empty."));
                });
            });
        });
    }

    static void test(TestRunner runner, Function1<Test,TreasuryDirect> creator)
    {
        runner.testGroup(TreasuryDirect.class, () ->
        {
            runner.testGroup("getSecurity(String,String,String,String)", () ->
            {
                final Action5<String,String,String,String,Throwable> getSecurityErrorTest = (String cusip, String issueMonth, String issueDayOfMonth, String issueYear, Throwable expected) ->
                {
                    runner.test("with " + English.andList(Iterable.create(cusip, issueMonth, issueDayOfMonth, issueYear).map(Strings::escapeAndQuote)), (Test test) ->
                    {
                        final TreasuryDirect treasuryDirect = creator.run(test);
                        test.assertThrows(() -> treasuryDirect.getSecurity(cusip, issueMonth, issueDayOfMonth, issueYear).await(),
                            expected);
                    });
                };

                getSecurityErrorTest.run(null, "02", "11", "2014", new PreConditionFailure("cusip cannot be null."));
                getSecurityErrorTest.run("", "02", "11", "2014", new PreConditionFailure("cusip cannot be empty."));
                getSecurityErrorTest.run("912796CJ6", null, "11", "2020", new PreConditionFailure("issueMonth cannot be null."));
                getSecurityErrorTest.run("912796CJ6", "", "11", "2020", new PreConditionFailure("issueMonth cannot be empty."));
                getSecurityErrorTest.run("912796CJ6", "2", null, "2020", new PreConditionFailure("issueDayOfMonth cannot be null."));
                getSecurityErrorTest.run("912796CJ6", "2", "", "2020", new PreConditionFailure("issueDayOfMonth cannot be empty."));
                getSecurityErrorTest.run("912796CJ6", "2", "11", null, new PreConditionFailure("issueYear cannot be null."));
                getSecurityErrorTest.run("912796CJ6", "2", "11", "", new PreConditionFailure("issueYear cannot be empty."));

                runner.test("with non-existing cusip", (Test test) ->
                {
                    final TreasuryDirect treasuryDirect = creator.run(test);

                    final String cusip = "952796CJ6";
                    final String issueMonth = "2";
                    final String issueDayOfMonth = "11";
                    final String issueYear = "2014";

                    test.assertThrows(() -> treasuryDirect.getSecurity(cusip, issueMonth, issueDayOfMonth, issueYear).await(),
                        new NotFoundException("No TreasuryDirect security found for CUSIP: " + cusip + ", IssueMonth: " + issueMonth + ", IssueDayOfMonth: " + issueDayOfMonth + ", and IssueYear: " + issueYear + "."));
                });

                runner.test("with wrong issueMonth", (Test test) ->
                {
                    final TreasuryDirect treasuryDirect = creator.run(test);

                    final String cusip = "912796CJ6";
                    final String issueMonth = "3";
                    final String issueDayOfMonth = "11";
                    final String issueYear = "2014";

                    test.assertThrows(() -> treasuryDirect.getSecurity(cusip, issueMonth, issueDayOfMonth, issueYear).await(),
                        new NotFoundException("No TreasuryDirect security found for CUSIP: " + cusip + ", IssueMonth: " + issueMonth + ", IssueDayOfMonth: " + issueDayOfMonth + ", and IssueYear: " + issueYear + "."));
                });

                runner.test("with wrong issueDayOfMonth", (Test test) ->
                {
                    final TreasuryDirect treasuryDirect = creator.run(test);

                    final String cusip = "912796CJ6";
                    final String issueMonth = "2";
                    final String issueDayOfMonth = "12";
                    final String issueYear = "2014";

                    test.assertThrows(() -> treasuryDirect.getSecurity(cusip, issueMonth, issueDayOfMonth, issueYear).await(),
                        new NotFoundException("No TreasuryDirect security found for CUSIP: " + cusip + ", IssueMonth: " + issueMonth + ", IssueDayOfMonth: " + issueDayOfMonth + ", and IssueYear: " + issueYear + "."));
                });

                runner.test("with wrong issueYear", (Test test) ->
                {
                    final TreasuryDirect treasuryDirect = creator.run(test);

                    final String cusip = "912796CJ6";
                    final String issueMonth = "2";
                    final String issueDayOfMonth = "11";
                    final String issueYear = "2019";

                    test.assertThrows(() -> treasuryDirect.getSecurity(cusip, issueMonth, issueDayOfMonth, issueYear).await(),
                        new NotFoundException("No TreasuryDirect security found for CUSIP: " + cusip + ", IssueMonth: " + issueMonth + ", IssueDayOfMonth: " + issueDayOfMonth + ", and IssueYear: " + issueYear + "."));
                });

                runner.test("with valid security data", (Test test) ->
                {
                    final TreasuryDirect treasuryDirect = creator.run(test);

                    final String cusip = "912796CJ6";
                    final String issueMonth = "2";
                    final String issueDayOfMonth = "11";
                    final String issueYear = "2014";

                    final TreasuryDirectSecurity security = treasuryDirect.getSecurity(cusip, issueMonth, issueDayOfMonth, issueYear).await();
                    test.assertNotNull(security);
                    test.assertEqual(DateTime.create(2014, 2, 11), security.getIssueDate().await());
                    test.assertEqual("Bill", security.getSecurityType().await());
                    test.assertEqual("72-Day", security.getSecurityTerm().await());
                });
            });

            runner.test("getAnnouncedSecurities()", (Test test) ->
            {
                final TreasuryDirect treasuryDirect = creator.run(test);
                final Iterable<TreasuryDirectSecurity> announcedSecurities = treasuryDirect.getAnnouncedSecurities().await();
                test.assertNotNullAndNotEmpty(announcedSecurities);
                test.assertEqual(250, announcedSecurities.getCount());
                for (final TreasuryDirectSecurity security : announcedSecurities)
                {
                    test.assertNotNull(security);

                    final String cusip = security.getCusip().await();
                    test.assertNotNullAndNotEmpty(cusip);

                    final DateTime issueDate = security.getIssueDate().await();
                    test.assertNotNull(issueDate);

                    final String securityType = security.getSecurityType().await();
                    test.assertNotNullAndNotEmpty(securityType);

                    final String securityTerm = security.getSecurityTerm().await();
                    test.assertNotNullAndNotEmpty(securityTerm);

                    final DateTime maturityDate = security.getMaturityDate().await();
                    test.assertNotNull(maturityDate);
                }
            });

            runner.testGroup("getAnnouncedSecurities(GetAnnouncedSecuritiesOptions)", () ->
            {
                runner.test("with null options", (Test test) ->
                {
                    final TreasuryDirect treasuryDirect = creator.run(test);
                    test.assertThrows(() -> treasuryDirect.getAnnouncedSecurities(null),
                        new PreConditionFailure("options cannot be null."));
                });

                runner.test("with empty options", (Test test) ->
                {
                    final TreasuryDirect treasuryDirect = creator.run(test);
                    final GetAnnouncedSecuritiesOptions options = GetAnnouncedSecuritiesOptions.create();
                    final Iterable<TreasuryDirectSecurity> announcedSecurities = treasuryDirect.getAnnouncedSecurities(options).await();
                    test.assertNotNullAndNotEmpty(announcedSecurities);
                    test.assertEqual(250, announcedSecurities.getCount());
                    for (final TreasuryDirectSecurity security : announcedSecurities)
                    {
                        test.assertNotNull(security);

                        final String cusip = security.getCusip().await();
                        test.assertNotNullAndNotEmpty(cusip);

                        final DateTime issueDate = security.getIssueDate().await();
                        test.assertNotNull(issueDate);

                        final String securityType = security.getSecurityType().await();
                        test.assertNotNullAndNotEmpty(securityType);

                        final String securityTerm = security.getSecurityTerm().await();
                        test.assertNotNullAndNotEmpty(securityTerm);

                        final DateTime maturityDate = security.getMaturityDate().await();
                        test.assertNotNull(maturityDate);
                    }
                });

                runner.test("with pagesize=10", (Test test) ->
                {
                    final TreasuryDirect treasuryDirect = creator.run(test);
                    final int pageSize = 10;
                    final GetAnnouncedSecuritiesOptions options = GetAnnouncedSecuritiesOptions.create()
                        .setPageSize(pageSize);
                    final Iterable<TreasuryDirectSecurity> announcedSecurities = treasuryDirect.getAnnouncedSecurities(options).await();
                    test.assertNotNullAndNotEmpty(announcedSecurities);
                    test.assertEqual(pageSize, announcedSecurities.getCount());
                    for (final TreasuryDirectSecurity security : announcedSecurities)
                    {
                        test.assertNotNull(security);
                    }
                });

                runner.test("with pagesize=251", (Test test) ->
                {
                    final TreasuryDirect treasuryDirect = creator.run(test);
                    final GetAnnouncedSecuritiesOptions options = GetAnnouncedSecuritiesOptions.create()
                        .setPageSize(251);
                    final Iterable<TreasuryDirectSecurity> announcedSecurities = treasuryDirect.getAnnouncedSecurities(options).await();
                    test.assertNotNullAndNotEmpty(announcedSecurities);
                    test.assertEqual(250, announcedSecurities.getCount());
                    for (final TreasuryDirectSecurity security : announcedSecurities)
                    {
                        test.assertNotNull(security);
                    }
                });

                runner.test("with type=Bill", (Test test) ->
                {
                    final TreasuryDirect treasuryDirect = creator.run(test);
                    final GetAnnouncedSecuritiesOptions options = GetAnnouncedSecuritiesOptions.create()
                        .setType(TreasuryDirectSecurityType.Bond);
                    final Iterable<TreasuryDirectSecurity> announcedSecurities = treasuryDirect.getAnnouncedSecurities(options).await();
                    test.assertNotNullAndNotEmpty(announcedSecurities);
                    for (final TreasuryDirectSecurity security : announcedSecurities)
                    {
                        test.assertNotNull(security);

                        final String cusip = security.getCusip().await();
                        test.assertNotNullAndNotEmpty(cusip);

                        final DateTime issueDate = security.getIssueDate().await();
                        test.assertNotNull(issueDate);

                        final String securityType = security.getSecurityType().await();
                        test.assertNotNullAndNotEmpty(securityType);
                        test.assertEqual(TreasuryDirectSecurityType.Bond.toString(), securityType);

                        final String securityTerm = security.getSecurityTerm().await();
                        test.assertNotNullAndNotEmpty(securityTerm);

                        final DateTime maturityDate = security.getMaturityDate().await();
                        test.assertNotNull(maturityDate);

                        final double interestRate = security.getInterestRate().await();
                        test.assertGreaterThanOrEqualTo(interestRate, 0);

                        final DateTime auctionDate = security.getAuctionDate().await();
                        test.assertNotNull(auctionDate);

                        final double auctionDateYear = security.getAuctionDateYear().await();
                        test.assertEqual(auctionDate.getYear(), auctionDateYear);
                    }
                });

                runner.test("with type=Bond", (Test test) ->
                {
                    final TreasuryDirect treasuryDirect = creator.run(test);
                    final GetAnnouncedSecuritiesOptions options = GetAnnouncedSecuritiesOptions.create()
                        .setType(TreasuryDirectSecurityType.Bond);
                    final Iterable<TreasuryDirectSecurity> announcedSecurities = treasuryDirect.getAnnouncedSecurities(options).await();
                    test.assertNotNullAndNotEmpty(announcedSecurities);
                    for (final TreasuryDirectSecurity security : announcedSecurities)
                    {
                        test.assertNotNull(security);

                        final String cusip = security.getCusip().await();
                        test.assertNotNullAndNotEmpty(cusip);

                        final DateTime issueDate = security.getIssueDate().await();
                        test.assertNotNull(issueDate);

                        final String securityType = security.getSecurityType().await();
                        test.assertNotNullAndNotEmpty(securityType);
                        test.assertEqual(TreasuryDirectSecurityType.Bond.toString(), securityType);

                        final String securityTerm = security.getSecurityTerm().await();
                        test.assertNotNullAndNotEmpty(securityTerm);

                        final DateTime maturityDate = security.getMaturityDate().await();
                        test.assertNotNull(maturityDate);

                        final double interestRate = security.getInterestRate().await();
                        test.assertGreaterThanOrEqualTo(interestRate, 0);

                        final DateTime auctionDate = security.getAuctionDate().await();
                        test.assertNotNull(auctionDate);

                        final double auctionDateYear = security.getAuctionDateYear().await();
                        test.assertEqual(auctionDate.getYear(), auctionDateYear);
                    }
                });

                runner.test("with type=Note", (Test test) ->
                {
                    final TreasuryDirect treasuryDirect = creator.run(test);
                    final GetAnnouncedSecuritiesOptions options = GetAnnouncedSecuritiesOptions.create()
                        .setType(TreasuryDirectSecurityType.Note);
                    final Iterable<TreasuryDirectSecurity> announcedSecurities = treasuryDirect.getAnnouncedSecurities(options).await();
                    test.assertNotNullAndNotEmpty(announcedSecurities);
                    for (final TreasuryDirectSecurity security : announcedSecurities)
                    {
                        test.assertNotNull(security);

                        final String cusip = security.getCusip().await();
                        test.assertNotNullAndNotEmpty(cusip);

                        final DateTime issueDate = security.getIssueDate().await();
                        test.assertNotNull(issueDate);

                        final String securityType = security.getSecurityType().await();
                        test.assertNotNullAndNotEmpty(securityType);
                        test.assertEqual(TreasuryDirectSecurityType.Note.toString(), securityType);

                        final String securityTerm = security.getSecurityTerm().await();
                        test.assertNotNullAndNotEmpty(securityTerm);

                        final DateTime maturityDate = security.getMaturityDate().await();
                        test.assertNotNull(maturityDate);

                        final DateTime auctionDate = security.getAuctionDate().await();
                        test.assertNotNull(auctionDate);

                        final double auctionDateYear = security.getAuctionDateYear().await();
                        test.assertEqual(auctionDate.getYear(), auctionDateYear);
                    }
                });

                runner.test("with days=0", (Test test) ->
                {
                    final TreasuryDirect treasuryDirect = creator.run(test);
                    final GetAnnouncedSecuritiesOptions options = GetAnnouncedSecuritiesOptions.create()
                        .setDays(0);
                    final Iterable<TreasuryDirectSecurity> announcedSecurities = treasuryDirect.getAnnouncedSecurities(options).await();
                    test.assertNotNull(announcedSecurities);
                    for (final TreasuryDirectSecurity security : announcedSecurities)
                    {
                        test.assertNotNull(security);

                        final String cusip = security.getCusip().await();
                        test.assertNotNullAndNotEmpty(cusip);

                        final DateTime issueDate = security.getIssueDate().await();
                        test.assertNotNull(issueDate);

                        final String securityType = security.getSecurityType().await();
                        test.assertNotNullAndNotEmpty(securityType);

                        final String securityTerm = security.getSecurityTerm().await();
                        test.assertNotNullAndNotEmpty(securityTerm);

                        final DateTime maturityDate = security.getMaturityDate().await();
                        test.assertNotNull(maturityDate);

                        final double interestRate = security.getInterestRate().await();
                        test.assertGreaterThanOrEqualTo(interestRate, 0);

                        final DateTime auctionDate = security.getAuctionDate().await();
                        test.assertNotNull(auctionDate);

                        final double auctionDateYear = security.getAuctionDateYear().await();
                        test.assertEqual(auctionDate.getYear(), auctionDateYear);
                    }
                });

                runner.test("with days=1", (Test test) ->
                {
                    final TreasuryDirect treasuryDirect = creator.run(test);
                    final GetAnnouncedSecuritiesOptions options = GetAnnouncedSecuritiesOptions.create()
                        .setDays(1);
                    final Iterable<TreasuryDirectSecurity> announcedSecurities = treasuryDirect.getAnnouncedSecurities(options).await();
                    test.assertNotNull(announcedSecurities);
                    for (final TreasuryDirectSecurity security : announcedSecurities)
                    {
                        test.assertNotNull(security);

                        final String cusip = security.getCusip().await();
                        test.assertNotNullAndNotEmpty(cusip);

                        final DateTime issueDate = security.getIssueDate().await();
                        test.assertNotNull(issueDate);

                        final String securityType = security.getSecurityType().await();
                        test.assertNotNullAndNotEmpty(securityType);

                        final String securityTerm = security.getSecurityTerm().await();
                        test.assertNotNullAndNotEmpty(securityTerm);

                        final DateTime maturityDate = security.getMaturityDate().await();
                        test.assertNotNull(maturityDate);

                        final double interestRate = security.getInterestRate().await();
                        test.assertGreaterThanOrEqualTo(interestRate, 0);

                        final DateTime auctionDate = security.getAuctionDate().await();
                        test.assertNotNull(auctionDate);

                        final double auctionDateYear = security.getAuctionDateYear().await();
                        test.assertEqual(auctionDate.getYear(), auctionDateYear);
                    }
                });
            });

            runner.test("getAuctionedSecurities()", (Test test) ->
            {
                final TreasuryDirect treasuryDirect = creator.run(test);
                final Iterable<TreasuryDirectSecurity> announcedSecurities = treasuryDirect.getAuctionedSecurities().await();
                test.assertNotNullAndNotEmpty(announcedSecurities);
                test.assertEqual(250, announcedSecurities.getCount());
                for (final TreasuryDirectSecurity security : announcedSecurities)
                {
                    test.assertNotNull(security);

                    final String cusip = security.getCusip().await();
                    test.assertNotNullAndNotEmpty(cusip);

                    final DateTime issueDate = security.getIssueDate().await();
                    test.assertNotNull(issueDate);

                    final String securityType = security.getSecurityType().await();
                    test.assertNotNullAndNotEmpty(securityType);

                    final String securityTerm = security.getSecurityTerm().await();
                    test.assertNotNullAndNotEmpty(securityTerm);

                    final DateTime maturityDate = security.getMaturityDate().await();
                    test.assertNotNull(maturityDate);
                }
            });

            runner.testGroup("getAuctionedSecurities(GetAuctionedSecuritiesOptions)", () ->
            {
                runner.test("with null options", (Test test) ->
                {
                    final TreasuryDirect treasuryDirect = creator.run(test);
                    test.assertThrows(() -> treasuryDirect.getAuctionedSecurities(null),
                        new PreConditionFailure("options cannot be null."));
                });

                runner.test("with empty options", (Test test) ->
                {
                    final TreasuryDirect treasuryDirect = creator.run(test);
                    final GetAuctionedSecuritiesOptions options = GetAuctionedSecuritiesOptions.create();
                    final Iterable<TreasuryDirectSecurity> announcedSecurities = treasuryDirect.getAuctionedSecurities(options).await();
                    test.assertNotNullAndNotEmpty(announcedSecurities);
                    test.assertEqual(250, announcedSecurities.getCount());
                    for (final TreasuryDirectSecurity security : announcedSecurities)
                    {
                        test.assertNotNull(security);

                        final String cusip = security.getCusip().await();
                        test.assertNotNullAndNotEmpty(cusip);

                        final DateTime issueDate = security.getIssueDate().await();
                        test.assertNotNull(issueDate);

                        final String securityType = security.getSecurityType().await();
                        test.assertNotNullAndNotEmpty(securityType);

                        final String securityTerm = security.getSecurityTerm().await();
                        test.assertNotNullAndNotEmpty(securityTerm);

                        final DateTime maturityDate = security.getMaturityDate().await();
                        test.assertNotNull(maturityDate);
                    }
                });

                runner.test("with pagesize=10", (Test test) ->
                {
                    final TreasuryDirect treasuryDirect = creator.run(test);
                    final int pageSize = 10;
                    final GetAuctionedSecuritiesOptions options = GetAuctionedSecuritiesOptions.create()
                        .setPageSize(pageSize);
                    final Iterable<TreasuryDirectSecurity> announcedSecurities = treasuryDirect.getAuctionedSecurities(options).await();
                    test.assertNotNullAndNotEmpty(announcedSecurities);
                    test.assertEqual(pageSize, announcedSecurities.getCount());
                    for (final TreasuryDirectSecurity security : announcedSecurities)
                    {
                        test.assertNotNull(security);
                    }
                });

                runner.test("with pagesize=251", (Test test) ->
                {
                    final TreasuryDirect treasuryDirect = creator.run(test);
                    final GetAuctionedSecuritiesOptions options = GetAuctionedSecuritiesOptions.create()
                        .setPageSize(251);
                    final Iterable<TreasuryDirectSecurity> announcedSecurities = treasuryDirect.getAuctionedSecurities(options).await();
                    test.assertNotNullAndNotEmpty(announcedSecurities);
                    test.assertEqual(250, announcedSecurities.getCount());
                    for (final TreasuryDirectSecurity security : announcedSecurities)
                    {
                        test.assertNotNull(security);
                    }
                });

                runner.test("with type=Bill", (Test test) ->
                {
                    final TreasuryDirect treasuryDirect = creator.run(test);
                    final GetAuctionedSecuritiesOptions options = GetAuctionedSecuritiesOptions.create()
                        .setType(TreasuryDirectSecurityType.Bond);
                    final Iterable<TreasuryDirectSecurity> announcedSecurities = treasuryDirect.getAuctionedSecurities(options).await();
                    test.assertNotNullAndNotEmpty(announcedSecurities);
                    for (final TreasuryDirectSecurity security : announcedSecurities)
                    {
                        test.assertNotNull(security);

                        final String cusip = security.getCusip().await();
                        test.assertNotNullAndNotEmpty(cusip);

                        final DateTime issueDate = security.getIssueDate().await();
                        test.assertNotNull(issueDate);

                        final String securityType = security.getSecurityType().await();
                        test.assertNotNullAndNotEmpty(securityType);
                        test.assertEqual(TreasuryDirectSecurityType.Bond.toString(), securityType);

                        final String securityTerm = security.getSecurityTerm().await();
                        test.assertNotNullAndNotEmpty(securityTerm);

                        final DateTime maturityDate = security.getMaturityDate().await();
                        test.assertNotNull(maturityDate);

                        final double interestRate = security.getInterestRate().await();
                        test.assertGreaterThanOrEqualTo(interestRate, 0);

                        final DateTime auctionDate = security.getAuctionDate().await();
                        test.assertNotNull(auctionDate);

                        final double auctionDateYear = security.getAuctionDateYear().await();
                        test.assertEqual(auctionDate.getYear(), auctionDateYear);
                    }
                });

                runner.test("with type=Bond", (Test test) ->
                {
                    final TreasuryDirect treasuryDirect = creator.run(test);
                    final GetAuctionedSecuritiesOptions options = GetAuctionedSecuritiesOptions.create()
                        .setType(TreasuryDirectSecurityType.Bond);
                    final Iterable<TreasuryDirectSecurity> announcedSecurities = treasuryDirect.getAuctionedSecurities(options).await();
                    test.assertNotNullAndNotEmpty(announcedSecurities);
                    for (final TreasuryDirectSecurity security : announcedSecurities)
                    {
                        test.assertNotNull(security);

                        final String cusip = security.getCusip().await();
                        test.assertNotNullAndNotEmpty(cusip);

                        final DateTime issueDate = security.getIssueDate().await();
                        test.assertNotNull(issueDate);

                        final String securityType = security.getSecurityType().await();
                        test.assertNotNullAndNotEmpty(securityType);
                        test.assertEqual(TreasuryDirectSecurityType.Bond.toString(), securityType);

                        final String securityTerm = security.getSecurityTerm().await();
                        test.assertNotNullAndNotEmpty(securityTerm);

                        final DateTime maturityDate = security.getMaturityDate().await();
                        test.assertNotNull(maturityDate);

                        final double interestRate = security.getInterestRate().await();
                        test.assertGreaterThanOrEqualTo(interestRate, 0);

                        final DateTime auctionDate = security.getAuctionDate().await();
                        test.assertNotNull(auctionDate);

                        final double auctionDateYear = security.getAuctionDateYear().await();
                        test.assertEqual(auctionDate.getYear(), auctionDateYear);
                    }
                });

                runner.test("with type=Note", (Test test) ->
                {
                    final TreasuryDirect treasuryDirect = creator.run(test);
                    final GetAuctionedSecuritiesOptions options = GetAuctionedSecuritiesOptions.create()
                        .setType(TreasuryDirectSecurityType.Note);
                    final Iterable<TreasuryDirectSecurity> announcedSecurities = treasuryDirect.getAuctionedSecurities(options).await();
                    test.assertNotNullAndNotEmpty(announcedSecurities);
                    for (final TreasuryDirectSecurity security : announcedSecurities)
                    {
                        test.assertNotNull(security);

                        final String cusip = security.getCusip().await();
                        test.assertNotNullAndNotEmpty(cusip);

                        final DateTime issueDate = security.getIssueDate().await();
                        test.assertNotNull(issueDate);

                        final String securityType = security.getSecurityType().await();
                        test.assertNotNullAndNotEmpty(securityType);
                        test.assertEqual(TreasuryDirectSecurityType.Note.toString(), securityType);

                        final String securityTerm = security.getSecurityTerm().await();
                        test.assertNotNullAndNotEmpty(securityTerm);

                        final DateTime maturityDate = security.getMaturityDate().await();
                        test.assertNotNull(maturityDate);

                        final double interestRate = security.getInterestRate().await();
                        test.assertGreaterThanOrEqualTo(interestRate, 0);

                        final DateTime auctionDate = security.getAuctionDate().await();
                        test.assertNotNull(auctionDate);

                        final double auctionDateYear = security.getAuctionDateYear().await();
                        test.assertEqual(auctionDate.getYear(), auctionDateYear);
                    }
                });

                runner.test("with days=0", (Test test) ->
                {
                    final TreasuryDirect treasuryDirect = creator.run(test);
                    final GetAuctionedSecuritiesOptions options = GetAuctionedSecuritiesOptions.create()
                        .setDays(0);
                    final Iterable<TreasuryDirectSecurity> announcedSecurities = treasuryDirect.getAuctionedSecurities(options).await();
                    test.assertNotNull(announcedSecurities);
                    for (final TreasuryDirectSecurity security : announcedSecurities)
                    {
                        test.assertNotNull(security);

                        final String cusip = security.getCusip().await();
                        test.assertNotNullAndNotEmpty(cusip);

                        final DateTime issueDate = security.getIssueDate().await();
                        test.assertNotNull(issueDate);

                        final String securityType = security.getSecurityType().await();
                        test.assertNotNullAndNotEmpty(securityType);

                        final String securityTerm = security.getSecurityTerm().await();
                        test.assertNotNullAndNotEmpty(securityTerm);

                        final DateTime maturityDate = security.getMaturityDate().await();
                        test.assertNotNull(maturityDate);

                        final double interestRate = security.getInterestRate().await();
                        test.assertGreaterThanOrEqualTo(interestRate, 0);

                        final DateTime auctionDate = security.getAuctionDate().await();
                        test.assertNotNull(auctionDate);

                        final double auctionDateYear = security.getAuctionDateYear().await();
                        test.assertEqual(auctionDate.getYear(), auctionDateYear);
                    }
                });

                runner.test("with days=1", (Test test) ->
                {
                    final TreasuryDirect treasuryDirect = creator.run(test);
                    final GetAuctionedSecuritiesOptions options = GetAuctionedSecuritiesOptions.create()
                        .setDays(1);
                    final Iterable<TreasuryDirectSecurity> announcedSecurities = treasuryDirect.getAuctionedSecurities(options).await();
                    test.assertNotNull(announcedSecurities);
                    for (final TreasuryDirectSecurity security : announcedSecurities)
                    {
                        test.assertNotNull(security);

                        final String cusip = security.getCusip().await();
                        test.assertNotNullAndNotEmpty(cusip);

                        final DateTime issueDate = security.getIssueDate().await();
                        test.assertNotNull(issueDate);

                        final String securityType = security.getSecurityType().await();
                        test.assertNotNullAndNotEmpty(securityType);

                        final String securityTerm = security.getSecurityTerm().await();
                        test.assertNotNullAndNotEmpty(securityTerm);

                        final DateTime maturityDate = security.getMaturityDate().await();
                        test.assertNotNull(maturityDate);

                        final double interestRate = security.getInterestRate().await();
                        test.assertGreaterThanOrEqualTo(interestRate, 0);

                        final DateTime auctionDate = security.getAuctionDate().await();
                        test.assertNotNull(auctionDate);

                        final double auctionDateYear = security.getAuctionDateYear().await();
                        test.assertEqual(auctionDate.getYear(), auctionDateYear);
                    }
                });
            });

            runner.testGroup("getSecuritiesByType(TreasuryDirectSecurityType)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final TreasuryDirect treasuryDirect = creator.run(test);
                    test.assertThrows(() -> treasuryDirect.getSecuritiesByType(null).await(),
                        new PreConditionFailure("type cannot be null."));
                });

                for (final TreasuryDirectSecurityType type : TreasuryDirectSecurityType.values())
                {
                    runner.test("with " + type, (Test test) ->
                    {
                        final TreasuryDirect treasuryDirect = creator.run(test);
                        final Iterable<TreasuryDirectSecurity> announcedSecurities = treasuryDirect.getSecuritiesByType(type).await();
                        test.assertNotNullAndNotEmpty(announcedSecurities);
                        for (final TreasuryDirectSecurity security : announcedSecurities)
                        {
                            test.assertNotNull(security);

                            final String cusip = security.getCusip().await();
                            test.assertNotNullAndNotEmpty(cusip);

                            final DateTime issueDate = security.getIssueDate().await();
                            test.assertNotNull(issueDate);

                            final String securityType = security.getSecurityType().await();
                            test.assertNotNullAndNotEmpty(securityType);
                            final List<String> expectedTypeString = List.create();
                            switch (type)
                            {
                                case CMB:
                                    expectedTypeString.add("Bill");
                                    break;

                                case FRN:
                                    expectedTypeString.add("Note");
                                    break;

                                case TIPS:
                                    expectedTypeString.addAll("Bond", "Note");
                                    break;

                                default:
                                    expectedTypeString.add(type.toString());
                                    break;
                            }
                            test.assertOneOf(expectedTypeString, securityType);

                            final String securityTerm = security.getSecurityTerm().await();
                            test.assertNotNullAndNotEmpty(securityTerm);
                        }
                    });
                }
            });
        });
    }
}

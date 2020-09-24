package qub;

public interface SearchSecuritiesOptionsTests
{
    static void test(TestRunner runner)
    {
        runner.testGroup(SearchSecuritiesOptions.class, () ->
        {
            runner.test("create()", (Test test) ->
            {
                final SearchSecuritiesOptions options = SearchSecuritiesOptions.create();
                test.assertNotNull(options);
                test.assertFalse(options.any());
            });

            runner.testGroup("set(String,String)", () ->
            {
                final Action3<String,String,Throwable> setErrorTest = (String propertyName, String propertyValue, Throwable expected) ->
                {
                    runner.test("with " + English.andList(Iterable.create(propertyName, propertyValue).map(Strings::escapeAndQuote)), (Test test) ->
                    {
                        final SearchSecuritiesOptions options = SearchSecuritiesOptions.create();
                        test.assertThrows(() -> options.set(propertyName, propertyValue),
                            expected);
                        test.assertFalse(options.any());
                    });
                };

                setErrorTest.run(null, "there", new PreConditionFailure("propertyName cannot be null."));
                setErrorTest.run("", "there", new PreConditionFailure("propertyName cannot be empty."));
                setErrorTest.run("hello", null, new PreConditionFailure("propertyValue cannot be null."));
                setErrorTest.run("hello", "", new PreConditionFailure("propertyValue cannot be empty."));

                final Action2<String,String> setTest = (String propertyName, String propertyValue) ->
                {
                    runner.test("with " + English.andList(Iterable.create(propertyName, propertyValue).map(Strings::escapeAndQuote)), (Test test) ->
                    {
                        final SearchSecuritiesOptions options = SearchSecuritiesOptions.create();
                        final SearchSecuritiesOptions setResult = options.set(propertyName, propertyValue);
                        test.assertSame(options, setResult);
                        test.assertEqual(propertyValue, options.get(propertyName).await());
                        test.assertEqual(1, options.getCount());
                    });
                };

                setTest.run("hello", "there");
            });

            runner.testGroup("setDate(String,Date)", () ->
            {
                final Action3<String,Date,Throwable> setDateErrorTest = (String propertyName, Date propertyValue, Throwable expected) ->
                {
                    runner.test("with " + English.andList(Iterable.create(propertyName, propertyValue).map(Strings::escapeAndQuote)), (Test test) ->
                    {
                        final SearchSecuritiesOptions options = SearchSecuritiesOptions.create();
                        test.assertThrows(() -> options.setDate(propertyName, propertyValue),
                            expected);
                        test.assertFalse(options.any());
                    });
                };

                setDateErrorTest.run(null, Date.create(1, 2, 3), new PreConditionFailure("propertyName cannot be null."));
                setDateErrorTest.run("", Date.create(1, 2, 3), new PreConditionFailure("propertyName cannot be empty."));
                setDateErrorTest.run("hello", null, new PreConditionFailure("propertyValue cannot be null."));

                final Action3<String,Date,String> setDateTest = (String propertyName, Date propertyValue, String expected) ->
                {
                    runner.test("with " + English.andList(Iterable.create(propertyName, propertyValue).map(Strings::escapeAndQuote)), (Test test) ->
                    {
                        final SearchSecuritiesOptions options = SearchSecuritiesOptions.create();
                        final SearchSecuritiesOptions setResult = options.setDate(propertyName, propertyValue);
                        test.assertSame(options, setResult);
                        test.assertEqual(expected, options.get(propertyName).await());
                        test.assertEqual(1, options.getCount());
                    });
                };

                setDateTest.run("hello", Date.create(2020, 3, 14), "2020-3-14");
            });

            runner.testGroup("setDate(String,Date)", () ->
            {
                final Action3<String,Date,Throwable> setDateErrorTest = (String propertyName, Date propertyValue, Throwable expected) ->
                {
                    runner.test("with " + English.andList(Iterable.create(propertyName, propertyValue).map(Strings::escapeAndQuote)), (Test test) ->
                    {
                        final SearchSecuritiesOptions options = SearchSecuritiesOptions.create();
                        test.assertThrows(() -> options.setDate(propertyName, propertyValue),
                            expected);
                        test.assertFalse(options.any());
                    });
                };

                setDateErrorTest.run(null, Date.create(1, 2, 3), new PreConditionFailure("propertyName cannot be null."));
                setDateErrorTest.run("", Date.create(1, 2, 3), new PreConditionFailure("propertyName cannot be empty."));
                setDateErrorTest.run("hello", null, new PreConditionFailure("propertyValue cannot be null."));

                final Action3<String,Date,String> setDateTest = (String propertyName, Date propertyValue, String expected) ->
                {
                    runner.test("with " + English.andList(Iterable.create(propertyName, propertyValue).map(Strings::escapeAndQuote)), (Test test) ->
                    {
                        final SearchSecuritiesOptions options = SearchSecuritiesOptions.create();
                        final SearchSecuritiesOptions setResult = options.setDate(propertyName, propertyValue);
                        test.assertSame(options, setResult);
                        test.assertEqual(expected, options.get(propertyName).await());
                        test.assertEqual(1, options.getCount());
                    });
                };

                setDateTest.run("hello", Date.create(2020, 3, 14), "2020-3-14");
            });

            runner.testGroup("setNotNull(String)", () ->
            {
                final Action2<String,Throwable> setNotNullErrorTest = (String propertyName, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(propertyName), (Test test) ->
                    {
                        final SearchSecuritiesOptions options = SearchSecuritiesOptions.create();
                        test.assertThrows(() -> options.setNotNull(propertyName),
                            expected);
                        test.assertFalse(options.any());
                    });
                };

                setNotNullErrorTest.run(null, new PreConditionFailure("propertyName cannot be null."));
                setNotNullErrorTest.run("", new PreConditionFailure("propertyName cannot be empty."));

                final Action1<String> setNotNullTest = (String propertyName) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(propertyName), (Test test) ->
                    {
                        final SearchSecuritiesOptions options = SearchSecuritiesOptions.create();
                        final SearchSecuritiesOptions setNotNullResult = options.setNotNull(propertyName);
                        test.assertSame(options, setNotNullResult);
                        test.assertEqual("notNull", options.get(propertyName).await());
                        test.assertEqual(1, options.getCount());
                    });
                };

                setNotNullTest.run("hello");
            });

            runner.testGroup("setToday(String)", () ->
            {
                final Action2<String,Throwable> setTodayErrorTest = (String propertyName, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(propertyName), (Test test) ->
                    {
                        final SearchSecuritiesOptions options = SearchSecuritiesOptions.create();
                        test.assertThrows(() -> options.setToday(propertyName),
                            expected);
                        test.assertFalse(options.any());
                    });
                };

                setTodayErrorTest.run(null, new PreConditionFailure("propertyName cannot be null."));
                setTodayErrorTest.run("", new PreConditionFailure("propertyName cannot be empty."));

                final Action1<String> setTodayTest = (String propertyName) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(propertyName), (Test test) ->
                    {
                        final SearchSecuritiesOptions options = SearchSecuritiesOptions.create();
                        final SearchSecuritiesOptions setTodayResult = options.setToday(propertyName);
                        test.assertSame(options, setTodayResult);
                        test.assertEqual("today", options.get(propertyName).await());
                        test.assertEqual(1, options.getCount());
                    });
                };

                setTodayTest.run("hello");
            });

            runner.testGroup("setAnnouncementDate(String)", () ->
            {
                final Action2<String,Throwable> setAnnouncementDateErrorTest = (String announcementDate, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(announcementDate), (Test test) ->
                    {
                        final SearchSecuritiesOptions options = SearchSecuritiesOptions.create();
                        test.assertThrows(() -> options.setAnnouncementDate(announcementDate),
                            expected);
                        test.assertFalse(options.any());
                    });
                };

                setAnnouncementDateErrorTest.run(null, new PreConditionFailure("announcementDate cannot be null."));
                setAnnouncementDateErrorTest.run("", new PreConditionFailure("announcementDate cannot be empty."));

                final Action1<String> setAnnouncementDateTest = (String announcementDate) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(announcementDate), (Test test) ->
                    {
                        final SearchSecuritiesOptions options = SearchSecuritiesOptions.create();
                        final SearchSecuritiesOptions setAnnouncementDateResult = options.setAnnouncementDate(announcementDate);
                        test.assertSame(options, setAnnouncementDateResult);
                        test.assertEqual(announcementDate, options.get(TreasuryDirectSecurity.announcementDatePropertyName).await());
                        test.assertEqual(1, options.getCount());
                    });
                };

                setAnnouncementDateTest.run("hello");
                setAnnouncementDateTest.run("1-2-3");
                setAnnouncementDateTest.run("1/2/3");
            });

            runner.test("setAnnouncementDateToToday()", (Test test) ->
            {
                final SearchSecuritiesOptions options = SearchSecuritiesOptions.create();
                final SearchSecuritiesOptions setAnnouncementDateToTodayResult = options.setAnnouncementDateToToday();
                test.assertSame(options, setAnnouncementDateToTodayResult);
                test.assertEqual("today", options.get(TreasuryDirectSecurity.announcementDatePropertyName).await());
                test.assertEqual(1, options.getCount());
            });

            runner.testGroup("setAnnouncementDate(Date)", () ->
            {
                final Action2<Date,Throwable> setAnnouncementDateErrorTest = (Date announcementDate, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(announcementDate), (Test test) ->
                    {
                        final SearchSecuritiesOptions options = SearchSecuritiesOptions.create();
                        test.assertThrows(() -> options.setAnnouncementDate(announcementDate),
                            expected);
                        test.assertFalse(options.any());
                    });
                };

                setAnnouncementDateErrorTest.run(null, new PreConditionFailure("announcementDate cannot be null."));

                final Action2<Date,String> setAnnouncementDateTest = (Date announcementDate, String expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(announcementDate), (Test test) ->
                    {
                        final SearchSecuritiesOptions options = SearchSecuritiesOptions.create();
                        final SearchSecuritiesOptions setAnnouncementDateResult = options.setAnnouncementDate(announcementDate);
                        test.assertSame(options, setAnnouncementDateResult);
                        test.assertEqual(expected, options.get(TreasuryDirectSecurity.announcementDatePropertyName).await());
                        test.assertEqual(1, options.getCount());
                    });
                };

                setAnnouncementDateTest.run(Date.create(2020, 1, 3), "2020-1-3");
                setAnnouncementDateTest.run(Date.create(700, 7, 13), "700-7-13");
                setAnnouncementDateTest.run(Date.create(2010, 11, 27), "2010-11-27");
            });

            runner.testGroup("setAuctionDate(String)", () ->
            {
                final Action2<String,Throwable> setAuctionDateErrorTest = (String auctionDate, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(auctionDate), (Test test) ->
                    {
                        final SearchSecuritiesOptions options = SearchSecuritiesOptions.create();
                        test.assertThrows(() -> options.setAuctionDate(auctionDate),
                            expected);
                        test.assertFalse(options.any());
                    });
                };

                setAuctionDateErrorTest.run(null, new PreConditionFailure("auctionDate cannot be null."));
                setAuctionDateErrorTest.run("", new PreConditionFailure("auctionDate cannot be empty."));

                final Action1<String> setAuctionDateTest = (String auctionDate) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(auctionDate), (Test test) ->
                    {
                        final SearchSecuritiesOptions options = SearchSecuritiesOptions.create();
                        final SearchSecuritiesOptions setAuctionDateResult = options.setAuctionDate(auctionDate);
                        test.assertSame(options, setAuctionDateResult);
                        test.assertEqual(auctionDate, options.get(TreasuryDirectSecurity.auctionDatePropertyName).await());
                        test.assertEqual(1, options.getCount());
                    });
                };

                setAuctionDateTest.run("hello");
                setAuctionDateTest.run("1-2-3");
                setAuctionDateTest.run("1/2/3");
            });

            runner.test("setAuctionDateToToday()", (Test test) ->
            {
                final SearchSecuritiesOptions options = SearchSecuritiesOptions.create();
                final SearchSecuritiesOptions setAuctionDateToTodayResult = options.setAuctionDateToToday();
                test.assertSame(options, setAuctionDateToTodayResult);
                test.assertEqual("today", options.get(TreasuryDirectSecurity.auctionDatePropertyName).await());
                test.assertEqual(1, options.getCount());
            });

            runner.testGroup("setAuctionDate(Date)", () ->
            {
                final Action2<Date,Throwable> setAuctionDateErrorTest = (Date auctionDate, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(auctionDate), (Test test) ->
                    {
                        final SearchSecuritiesOptions options = SearchSecuritiesOptions.create();
                        test.assertThrows(() -> options.setAuctionDate(auctionDate),
                            expected);
                        test.assertFalse(options.any());
                    });
                };

                setAuctionDateErrorTest.run(null, new PreConditionFailure("auctionDate cannot be null."));

                final Action2<Date,String> setAuctionDateTest = (Date auctionDate, String expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(auctionDate), (Test test) ->
                    {
                        final SearchSecuritiesOptions options = SearchSecuritiesOptions.create();
                        final SearchSecuritiesOptions setAuctionDateResult = options.setAuctionDate(auctionDate);
                        test.assertSame(options, setAuctionDateResult);
                        test.assertEqual(expected, options.get(TreasuryDirectSecurity.auctionDatePropertyName).await());
                        test.assertEqual(1, options.getCount());
                    });
                };

                setAuctionDateTest.run(Date.create(2020, 1, 3), "2020-1-3");
                setAuctionDateTest.run(Date.create(700, 7, 13), "700-7-13");
                setAuctionDateTest.run(Date.create(2010, 11, 27), "2010-11-27");
            });

            runner.testGroup("setAuctionDateYear(String)", () ->
            {
                final Action2<String,Throwable> setAuctionDateYearErrorTest = (String auctionDateYear, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(auctionDateYear), (Test test) ->
                    {
                        final SearchSecuritiesOptions options = SearchSecuritiesOptions.create();
                        test.assertThrows(() -> options.setAuctionDateYear(auctionDateYear),
                            expected);
                        test.assertFalse(options.any());
                    });
                };

                setAuctionDateYearErrorTest.run(null, new PreConditionFailure("auctionDateYear cannot be null."));
                setAuctionDateYearErrorTest.run("", new PreConditionFailure("auctionDateYear cannot be empty."));

                final Action1<String> setAuctionDateYearTest = (String auctionDateYear) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(auctionDateYear), (Test test) ->
                    {
                        final SearchSecuritiesOptions options = SearchSecuritiesOptions.create();
                        final SearchSecuritiesOptions setAuctionDateYearResult = options.setAuctionDateYear(auctionDateYear);
                        test.assertSame(options, setAuctionDateYearResult);
                        test.assertEqual(auctionDateYear, options.get(TreasuryDirectSecurity.auctionDateYearPropertyName).await());
                        test.assertEqual(1, options.getCount());
                    });
                };

                setAuctionDateYearTest.run("hello");
                setAuctionDateYearTest.run("2020");
            });

            runner.testGroup("setAuctionDateYear(int)", () ->
            {
                final Action1<Integer> setAuctionDateYearTest = (Integer auctionDateYear) ->
                {
                    runner.test("with " + auctionDateYear, (Test test) ->
                    {
                        final SearchSecuritiesOptions options = SearchSecuritiesOptions.create();
                        final SearchSecuritiesOptions setAuctionDateYearResult = options.setAuctionDateYear(auctionDateYear);
                        test.assertSame(options, setAuctionDateYearResult);
                        test.assertEqual(Integers.toString(auctionDateYear), options.get(TreasuryDirectSecurity.auctionDateYearPropertyName).await());
                        test.assertEqual(1, options.getCount());
                    });
                };

                setAuctionDateYearTest.run(2020);
                setAuctionDateYearTest.run(2000);
                setAuctionDateYearTest.run(1900);
            });

            runner.testGroup("setCusip(String)", () ->
            {
                final Action2<String,Throwable> setCusipErrorTest = (String cusip, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(cusip), (Test test) ->
                    {
                        final SearchSecuritiesOptions options = SearchSecuritiesOptions.create();
                        test.assertThrows(() -> options.setCusip(cusip),
                            expected);
                        test.assertFalse(options.any());
                    });
                };

                setCusipErrorTest.run(null, new PreConditionFailure("cusip cannot be null."));
                setCusipErrorTest.run("", new PreConditionFailure("cusip cannot be empty."));

                final Action1<String> setCusipTest = (String cusip) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(cusip), (Test test) ->
                    {
                        final SearchSecuritiesOptions options = SearchSecuritiesOptions.create();
                        final SearchSecuritiesOptions setCusipResult = options.setCusip(cusip);
                        test.assertSame(options, setCusipResult);
                        test.assertEqual(cusip, options.get(TreasuryDirectSecurity.cusipPropertyName).await());
                        test.assertEqual(1, options.getCount());
                    });
                };

                setCusipTest.run("hello");
                setCusipTest.run("2020");
                setCusipTest.run("912796CJ6");
            });

            runner.testGroup("setInterestRate(String)", () ->
            {
                final Action2<String,Throwable> setInterestRateErrorTest = (String interestRate, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(interestRate), (Test test) ->
                    {
                        final SearchSecuritiesOptions options = SearchSecuritiesOptions.create();
                        test.assertThrows(() -> options.setInterestRate(interestRate),
                            expected);
                        test.assertFalse(options.any());
                    });
                };

                setInterestRateErrorTest.run(null, new PreConditionFailure("interestRate cannot be null."));
                setInterestRateErrorTest.run("", new PreConditionFailure("interestRate cannot be empty."));

                final Action1<String> setInterestRateTest = (String interestRate) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(interestRate), (Test test) ->
                    {
                        final SearchSecuritiesOptions options = SearchSecuritiesOptions.create();
                        final SearchSecuritiesOptions setInterestRateResult = options.setInterestRate(interestRate);
                        test.assertSame(options, setInterestRateResult);
                        test.assertEqual(interestRate, options.get(TreasuryDirectSecurity.interestRatePropertyName).await());
                        test.assertEqual(1, options.getCount());
                    });
                };

                setInterestRateTest.run("hello");
                setInterestRateTest.run("2");
                setInterestRateTest.run("0.125");
            });

            runner.testGroup("setIssueDate(String)", () ->
            {
                final Action2<String,Throwable> setIssueDateErrorTest = (String issueDate, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(issueDate), (Test test) ->
                    {
                        final SearchSecuritiesOptions options = SearchSecuritiesOptions.create();
                        test.assertThrows(() -> options.setIssueDate(issueDate),
                            expected);
                        test.assertFalse(options.any());
                    });
                };

                setIssueDateErrorTest.run(null, new PreConditionFailure("issueDate cannot be null."));
                setIssueDateErrorTest.run("", new PreConditionFailure("issueDate cannot be empty."));

                final Action1<String> setIssueDateTest = (String issueDate) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(issueDate), (Test test) ->
                    {
                        final SearchSecuritiesOptions options = SearchSecuritiesOptions.create();
                        final SearchSecuritiesOptions setIssueDateResult = options.setIssueDate(issueDate);
                        test.assertSame(options, setIssueDateResult);
                        test.assertEqual(issueDate, options.get(TreasuryDirectSecurity.issueDatePropertyName).await());
                        test.assertEqual(1, options.getCount());
                    });
                };

                setIssueDateTest.run("hello");
                setIssueDateTest.run("1-2-3");
                setIssueDateTest.run("1/2/3");
            });

            runner.test("setIssueDateToToday()", (Test test) ->
            {
                final SearchSecuritiesOptions options = SearchSecuritiesOptions.create();
                final SearchSecuritiesOptions setIssueDateToTodayResult = options.setIssueDateToToday();
                test.assertSame(options, setIssueDateToTodayResult);
                test.assertEqual("today", options.get(TreasuryDirectSecurity.issueDatePropertyName).await());
                test.assertEqual(1, options.getCount());
            });

            runner.testGroup("setIssueDate(Date)", () ->
            {
                final Action2<Date,Throwable> setIssueDateErrorTest = (Date issueDate, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(issueDate), (Test test) ->
                    {
                        final SearchSecuritiesOptions options = SearchSecuritiesOptions.create();
                        test.assertThrows(() -> options.setIssueDate(issueDate),
                            expected);
                        test.assertFalse(options.any());
                    });
                };

                setIssueDateErrorTest.run(null, new PreConditionFailure("issueDate cannot be null."));

                final Action2<Date,String> setIssueDateTest = (Date issueDate, String expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(issueDate), (Test test) ->
                    {
                        final SearchSecuritiesOptions options = SearchSecuritiesOptions.create();
                        final SearchSecuritiesOptions setIssueDateResult = options.setIssueDate(issueDate);
                        test.assertSame(options, setIssueDateResult);
                        test.assertEqual(expected, options.get(TreasuryDirectSecurity.issueDatePropertyName).await());
                        test.assertEqual(1, options.getCount());
                    });
                };

                setIssueDateTest.run(Date.create(2020, 1, 3), "2020-1-3");
                setIssueDateTest.run(Date.create(700, 7, 13), "700-7-13");
                setIssueDateTest.run(Date.create(2010, 11, 27), "2010-11-27");
            });

            runner.testGroup("setMaturityDate(String)", () ->
            {
                final Action2<String,Throwable> setMaturityDateErrorTest = (String maturityDate, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(maturityDate), (Test test) ->
                    {
                        final SearchSecuritiesOptions options = SearchSecuritiesOptions.create();
                        test.assertThrows(() -> options.setMaturityDate(maturityDate),
                            expected);
                        test.assertFalse(options.any());
                    });
                };

                setMaturityDateErrorTest.run(null, new PreConditionFailure("maturityDate cannot be null."));
                setMaturityDateErrorTest.run("", new PreConditionFailure("maturityDate cannot be empty."));

                final Action1<String> setMaturityDateTest = (String maturityDate) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(maturityDate), (Test test) ->
                    {
                        final SearchSecuritiesOptions options = SearchSecuritiesOptions.create();
                        final SearchSecuritiesOptions setMaturityDateResult = options.setMaturityDate(maturityDate);
                        test.assertSame(options, setMaturityDateResult);
                        test.assertEqual(maturityDate, options.get(TreasuryDirectSecurity.maturityDatePropertyName).await());
                        test.assertEqual(1, options.getCount());
                    });
                };

                setMaturityDateTest.run("hello");
                setMaturityDateTest.run("1-2-3");
                setMaturityDateTest.run("1/2/3");
            });

            runner.test("setMaturityDateToToday()", (Test test) ->
            {
                final SearchSecuritiesOptions options = SearchSecuritiesOptions.create();
                final SearchSecuritiesOptions setMaturityDateToTodayResult = options.setMaturityDateToToday();
                test.assertSame(options, setMaturityDateToTodayResult);
                test.assertEqual("today", options.get(TreasuryDirectSecurity.maturityDatePropertyName).await());
                test.assertEqual(1, options.getCount());
            });

            runner.testGroup("setMaturityDate(Date)", () ->
            {
                final Action2<Date,Throwable> setMaturityDateErrorTest = (Date maturityDate, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(maturityDate), (Test test) ->
                    {
                        final SearchSecuritiesOptions options = SearchSecuritiesOptions.create();
                        test.assertThrows(() -> options.setMaturityDate(maturityDate),
                            expected);
                        test.assertFalse(options.any());
                    });
                };

                setMaturityDateErrorTest.run(null, new PreConditionFailure("maturityDate cannot be null."));

                final Action2<Date,String> setMaturityDateTest = (Date maturityDate, String expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(maturityDate), (Test test) ->
                    {
                        final SearchSecuritiesOptions options = SearchSecuritiesOptions.create();
                        final SearchSecuritiesOptions setMaturityDateResult = options.setMaturityDate(maturityDate);
                        test.assertSame(options, setMaturityDateResult);
                        test.assertEqual(expected, options.get(TreasuryDirectSecurity.maturityDatePropertyName).await());
                        test.assertEqual(1, options.getCount());
                    });
                };

                setMaturityDateTest.run(Date.create(2020, 1, 3), "2020-1-3");
                setMaturityDateTest.run(Date.create(700, 7, 13), "700-7-13");
                setMaturityDateTest.run(Date.create(2010, 11, 27), "2010-11-27");
            });

            runner.testGroup("setSecurityType(String)", () ->
            {
                final Action2<String,Throwable> setSecurityTypeErrorTest = (String securityType, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(securityType), (Test test) ->
                    {
                        final SearchSecuritiesOptions options = SearchSecuritiesOptions.create();
                        test.assertThrows(() -> options.setSecurityType(securityType),
                            expected);
                        test.assertFalse(options.any());
                    });
                };

                setSecurityTypeErrorTest.run(null, new PreConditionFailure("securityType cannot be null."));
                setSecurityTypeErrorTest.run("", new PreConditionFailure("securityType cannot be empty."));

                final Action1<String> setSecurityTypeTest = (String securityType) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(securityType), (Test test) ->
                    {
                        final SearchSecuritiesOptions options = SearchSecuritiesOptions.create();
                        final SearchSecuritiesOptions setSecurityTypeResult = options.setSecurityType(securityType);
                        test.assertSame(options, setSecurityTypeResult);
                        test.assertEqual(securityType, options.get(TreasuryDirectSecurity.securityTypePropertyName).await());
                        test.assertEqual(1, options.getCount());
                    });
                };

                setSecurityTypeTest.run("hello");
                setSecurityTypeTest.run("1-2-3");
                setSecurityTypeTest.run("1/2/3");
                setSecurityTypeTest.run("bill");
                setSecurityTypeTest.run("bond");
            });

            runner.testGroup("setSecurityType(TreasuryDirectSecurityType)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final SearchSecuritiesOptions options = SearchSecuritiesOptions.create();
                    test.assertThrows(() -> options.setSecurityType((TreasuryDirectSecurityType)null),
                        new PreConditionFailure("securityType cannot be null."));
                    test.assertFalse(options.any());
                });

                for (final TreasuryDirectSecurityType securityType : TreasuryDirectSecurityType.values())
                {
                    runner.test("with " + Strings.escapeAndQuote(securityType), (Test test) ->
                    {
                        final SearchSecuritiesOptions options = SearchSecuritiesOptions.create();
                        final SearchSecuritiesOptions setSecurityTypeResult = options.setSecurityType(securityType);
                        test.assertSame(options, setSecurityTypeResult);
                        test.assertEqual(securityType.toString(), options.get(TreasuryDirectSecurity.securityTypePropertyName).await());
                        test.assertEqual(1, options.getCount());
                    });
                }
            });

            runner.testGroup("setSecurityTerm(String)", () ->
            {
                final Action2<String,Throwable> setSecurityTermErrorTest = (String securityTerm, Throwable expected) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(securityTerm), (Test test) ->
                    {
                        final SearchSecuritiesOptions options = SearchSecuritiesOptions.create();
                        test.assertThrows(() -> options.setSecurityTerm(securityTerm),
                            expected);
                        test.assertFalse(options.any());
                    });
                };

                setSecurityTermErrorTest.run(null, new PreConditionFailure("securityTerm cannot be null."));
                setSecurityTermErrorTest.run("", new PreConditionFailure("securityTerm cannot be empty."));

                final Action1<String> setSecurityTermTest = (String securityTerm) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(securityTerm), (Test test) ->
                    {
                        final SearchSecuritiesOptions options = SearchSecuritiesOptions.create();
                        final SearchSecuritiesOptions setSecurityTermResult = options.setSecurityTerm(securityTerm);
                        test.assertSame(options, setSecurityTermResult);
                        test.assertEqual(securityTerm, options.get(TreasuryDirectSecurity.securityTermPropertyName).await());
                        test.assertEqual(1, options.getCount());
                    });
                };

                setSecurityTermTest.run("hello");
                setSecurityTermTest.run("1-2-3");
                setSecurityTermTest.run("1/2/3");
                setSecurityTermTest.run("bill");
                setSecurityTermTest.run("bond");
            });
        });
    }
}

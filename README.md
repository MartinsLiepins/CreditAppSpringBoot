"# Java Back-End project"

Use Postman or equivalent software to test application.

In project H2 database is used:
path: localhost:8080/h2-console
 username=martins
 password=student

To test application use:

1. register new User (POST message):
    /quickloan/register
    email -> your email
    password -> your password

2. login in application (POST message):
    /quickloan/login
    email -> your email
    password -> your password

3. take loan (GET message):
    /quickloan/App/loan
    loanAmount -> your loan applicationAmount
    passingTermDays -> your passing term in days

4. extend loan (GET message):
    /quickloan/App/extendLoan
    extendTermWeeks -> your term in weeks to extend loan

5.  view all user loans (GET message):
    /quickloan/App/viewLoans

6. repay loan (POST message):
    /quickloan/App/repayLoan
    repayAmount - applicationAmount to repay with interest factor



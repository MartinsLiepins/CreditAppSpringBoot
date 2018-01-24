"# Java Back-End project"

In project H2 database is used,
path on browser to check DB: localhost:8080/h2-console
 username=martins
 password=student

To test application use Postman or equivalent software:

1. register new User (POST message):

    localhost:8080/quickloan/register

    POST body:
    email -> your email
    password -> your password

2. login in application (POST message):
    localhost:8080/quickloan/login

    POST body:
    email -> your email
    password -> your password

3. take loan (GET message):
    localhost:8080/quickloan/App/loan

    GET parameters:
    loanAmount -> your loan applicationAmount
    passingTermDays -> your passing term in days

4. extend loan (GET message):

    GET parameters:
    localhost:8080/quickloan/App/extendLoan
    extendTermWeeks -> your term in weeks to extend loan

5.  view all user loans (GET message):
    localhost:8080/quickloan/App/viewLoans

6. repay loan (POST message):
    localhost:8080/quickloan/App/repayLoan

    POST body:
    repayAmount - applicationAmount to repay, including interest factor



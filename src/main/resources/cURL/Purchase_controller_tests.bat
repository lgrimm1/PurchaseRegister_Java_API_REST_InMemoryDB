@echo off
title PurchaseRegister cURL tests

echo ===== Add New Purchases - Success =====
curl -X PUT -H "Accept: application/json" -H "Content-Type:application/json" --data "{\"purchaseId\": 5, \"purchaseDate\": \"2010-06-03\", \"purchaseType\": \"CARD\", \"purchaseValue\": 12, \"purchaseDescription\": \"abc\"}" "localhost:8080/api/v1/newPurchase"
echo.
echo ===== Expected: -9223372036854775807 =====
echo.
curl -X PUT -H "Accept: application/json" -H "Content-Type:application/json" --data "{\"purchaseId\": 5, \"purchaseDate\": \"1980-12-31\", \"purchaseType\": \"INTERNET\", \"purchaseValue\": 123.45, \"purchaseDescription\": \"pqr\"}" "localhost:8080/api/v1/newPurchase"
echo.
echo ===== Expected: -9223372036854775806 =====
echo.
curl -X PUT -H "Accept: application/json" -H "Content-Type:application/json" --data "{\"purchaseId\": 5, \"purchaseDate\": \"2020-06-03\", \"purchaseType\": \"INTERNET\", \"purchaseValue\": 48, \"purchaseDescription\": \"ooo\"}" "localhost:8080/api/v1/newPurchase"
echo.
echo ===== Expected: -9223372036854775805 =====
echo.

echo ===== Add New Purchase - Failure =====
curl -X PUT -H "Accept: application/json" -H "Content-Type:application/json" --data "{\"purchaseId\": 5, \"purchaseDate\": null, \"purchaseType\": \"CASH\", \"purchaseValue\": 12.24, \"purchaseDescription\": \"abc\"}" "localhost:8080/api/v1/newPurchase"
echo.
echo ===== Expected: -9223372036854775808 =====
echo.

echo ===== Get All Purchases =====
curl -X GET -H "Accept: application/json" -H "Content-Type:application/json" "localhost:8080/api/v1/purchases"
echo.
echo ===== Expected: [{"purchaseId":-9223372036854775807,"purchaseDate":"2010-06-03","purchaseType":"CARD","purchaseValue":12.0,"purchaseDescription":"abc"},{"purchaseId":-9223372036854775806,"purchaseDate":"1980-12-31","purchaseType":"INTERNET","purchaseValue":123.45,"purchaseDescription":"pqr"},{"purchaseId":-9223372036854775805,"purchaseDate":"2020-06-03","purchaseType":"INTERNET","purchaseValue":48.0,"purchaseDescription":"ooo"}] =====
echo.

echo ===== Count Purchases =====
curl -X GET -H "Accept: application/json" -H "Content-Type:application/json" "localhost:8080/api/v1/count"
echo.
echo ===== Expected: 3 =====
echo.

echo ===== Get Purchase by ID - success =====
curl -X GET -H "Accept: application/json" -H "Content-Type:application/json" "localhost:8080/api/v1/purchase/-9223372036854775806"
echo.
echo ===== Expected: {"purchaseId":-9223372036854775806,"purchaseDate":"1980-12-31","purchaseType":"INTERNET","purchaseValue":123.45,"purchaseDescription":"pqr"} =====
echo.

echo ===== Get Purchase by ID - failure =====
curl -X GET -H "Accept: application/json" -H "Content-Type:application/json" "localhost:8080/api/v1/purchase/5"
echo.
echo ===== Expected:  =====
echo.

echo ===== Update Purchase by ID - failure =====
curl -X PUT -H "Accept: application/json" -H "Content-Type:application/json" --data "{\"purchaseId\": 5, \"purchaseDate\": \"2010-08-16\", \"purchaseType\": \"CASH\", \"purchaseValue\": 24, \"purchaseDescription\": \"xyz\"}" "localhost:8080/api/v1/purchase"
echo.
echo ===== Expected: false =====
echo.

echo ===== Update Purchase by ID - success =====
curl -X PUT -H "Accept: application/json" -H "Content-Type:application/json" --data "{\"purchaseId\": -9223372036854775806, \"purchaseDate\": \"2010-08-16\", \"purchaseType\": \"CASH\", \"purchaseValue\": 24, \"purchaseDescription\": \"xyz\"}" "localhost:8080/api/v1/purchase"
echo.
echo ===== Expected: true =====
echo.

echo ===== Get Purchase by ID - success =====
curl -X GET -H "Accept: application/json" -H "Content-Type:application/json" "localhost:8080/api/v1/purchase/-9223372036854775806"
echo.
echo ===== Expected: {"purchaseId":-9223372036854775806,"purchaseDate":"2010-08-16","purchaseType":"CASH","purchaseValue":24.0,"purchaseDescription":"xyz"} =====
echo.

echo ===== Get Annual Statistics =====
curl -X GET -H "Accept: application/json" -H "Content-Type:application/json" "localhost:8080/api/v1/stat/annual"
echo.
echo ===== Expected: [{"year":2020,"total":48.0,"count":1,"average":48.0},{"year":2010,"total":36.0,"count":2,"average":18.0}] =====
echo.

echo ===== Get Full Statistics =====
curl -X GET -H "Accept: application/json" -H "Content-Type:application/json" "localhost:8080/api/v1/stat/full"
echo.
echo ===== Expected: {"total":84.0,"count":3,"average":28.0} =====
echo.

echo ===== Delete Purchase by ID - failure =====
curl -X DELETE -H "Accept: application/json" -H "Content-Type:application/json" "localhost:8080/api/v1/purchase/3"
echo.
echo ===== Expected: false =====
echo.

echo ===== Get All Purchases =====
curl -X GET -H "Accept: application/json" -H "Content-Type:application/json" "localhost:8080/api/v1/purchases"
echo.
echo ===== Expected: [{"purchaseId":-9223372036854775807,"purchaseDate":"2010-06-03","purchaseType":"CARD","purchaseValue":12.0,"purchaseDescription":"abc"},{"purchaseId":-9223372036854775806,"purchaseDate":"2010-08-16","purchaseType":"CASH","purchaseValue":24.0,"purchaseDescription":"xyz"},{"purchaseId":-9223372036854775805,"purchaseDate":"2020-06-03","purchaseType":"INTERNET","purchaseValue":48.0,"purchaseDescription":"ooo"}] =====
echo.

echo ===== Delete Purchase by ID - success =====
curl -X DELETE -H "Accept: application/json" -H "Content-Type:application/json" "localhost:8080/api/v1/purchase/-9223372036854775806"
echo.
echo ===== Expected: true =====
echo.

echo ===== Get All Purchases =====
curl -X GET -H "Accept: application/json" -H "Content-Type:application/json" "localhost:8080/api/v1/purchases"
echo.
echo ===== Expected: [{"purchaseId":-9223372036854775807,"purchaseDate":"2010-06-03","purchaseType":"CARD","purchaseValue":12.0,"purchaseDescription":"abc"},{"purchaseId":-9223372036854775805,"purchaseDate":"2020-06-03","purchaseType":"INTERNET","purchaseValue":48.0,"purchaseDescription":"ooo"}] =====
echo.

echo ===== Delete Purchases by ID List =====
curl -X DELETE -H "Accept: application/json" -H "Content-Type:application/json" --data "[-9223372036854775807, -9223372036854775806, -9223372036854775805, 5]" localhost:8080/api/v1/purchases"
echo.
echo ===== Expected: [-9223372036854775807, -9223372036854775805] =====
echo.

echo ===== Count Purchases =====
curl -X GET -H "Accept: application/json" -H "Content-Type:application/json" "localhost:8080/api/v1/count"
echo.
echo ===== Expected: 0 =====
echo.

echo ===== Get All Purchases =====
curl -X GET -H "Accept: application/json" -H "Content-Type:application/json" "localhost:8080/api/v1/purchases"
echo.
echo ===== Expected: [] =====
echo.

echo ===== Add New Purchases - Success =====
curl -X PUT -H "Accept: application/json" -H "Content-Type:application/json" --data "{\"purchaseId\": 5, \"purchaseDate\": \"2010-06-03\", \"purchaseType\": \"CARD\", \"purchaseValue\": 12, \"purchaseDescription\": \"abc\"}" "localhost:8080/api/v1/newPurchase"
echo.
echo ===== Expected: -9223372036854775804 =====
echo.
curl -X PUT -H "Accept: application/json" -H "Content-Type:application/json" --data "{\"purchaseId\": 5, \"purchaseDate\": \"2010-06-16\", \"purchaseType\": \"CASH\", \"purchaseValue\": 24, \"purchaseDescription\": \"xyz\"}" "localhost:8080/api/v1/newPurchase"
echo.
echo ===== Expected: -9223372036854775803 =====
echo.
curl -X PUT -H "Accept: application/json" -H "Content-Type:application/json" --data "{\"purchaseId\": 5, \"purchaseDate\": \"2010-08-30\", \"purchaseType\": \"CASH\", \"purchaseValue\": 48, \"purchaseDescription\": \"xyz\"}" "localhost:8080/api/v1/newPurchase"
echo.
echo ===== Expected: -9223372036854775802 =====
echo.
curl -X PUT -H "Accept: application/json" -H "Content-Type:application/json" --data "{\"purchaseId\": 5, \"purchaseDate\": \"2020-06-16\", \"purchaseType\": \"CASH\", \"purchaseValue\": 99, \"purchaseDescription\": \"xyz\"}" "localhost:8080/api/v1/newPurchase"
echo.
echo ===== Expected: -9223372036854775801 =====
echo.

echo ===== Get Monthly Statistics =====
curl -X GET -H "Accept: application/json" -H "Content-Type:application/json" "localhost:8080/api/v1/stat/months"
echo.
echo ===== Expected: [{"year":2020,"month":6,"total":99.0,"count":1,"average":99.0},{"year":2010,"month":6,"total":36.0,"count":2,"average":18.0},{"year":2010,"month":8,"total":48.0,"count":1,"average":48.0}] =====
echo.

echo ===== Delete Purchases by ID List =====
curl -X DELETE -H "Accept: application/json" -H "Content-Type:application/json" --data "[-9223372036854775804, -9223372036854775803, -9223372036854775802, -9223372036854775801]" localhost:8080/api/v1/purchases"
echo.
echo ===== Expected: [-9223372036854775804, -9223372036854775803, -9223372036854775802, -9223372036854775801] =====
echo.

echo ===== Count Purchases =====
curl -X GET -H "Accept: application/json" -H "Content-Type:application/json" "localhost:8080/api/v1/count"
echo.
echo ===== Expected: 0 =====
echo.

pause

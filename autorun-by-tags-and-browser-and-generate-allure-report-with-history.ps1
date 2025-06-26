# ---------- EDGE ----------
Write-Host "Running SMOKE tests on EDGE..." -ForegroundColor Green
mvn test -Dgroups="Smoke,uiUx" -Dbrowser=edge
Write-Host "Copying history after EDGE..."
Copy-Item -Recurse -Force "target/allure-report/history/*" "target/allure-results/history/" -ErrorAction SilentlyContinue
Write-Host "Generating Allure report after EDGE..."
allure generate target/allure-results --clean -o target/allure-report

# ---------- CHROME ----------
Write-Host "Running SMOKE tests on CHROME..." -ForegroundColor Green
mvn test -Dgroups=Smoke -Dbrowser=chrome
Write-Host "Copying history after CHROME..."
Copy-Item -Recurse -Force "target/allure-report/history/*" "target/allure-results/history/" -ErrorAction SilentlyContinue
Write-Host "Generating Allure report after CHROME..."
allure generate target/allure-results --clean -o target/allure-report

# ---------- FIREFOX ----------
Write-Host "Running SMOKE tests on FIREFOX..." -ForegroundColor Green
mvn test -Dgroups=Smoke -Dbrowser=firefox
Write-Host "Copying history after FIREFOX..."
Copy-Item -Recurse -Force "target/allure-report/history/*" "target/allure-results/history/" -ErrorAction SilentlyContinue
Write-Host "Generating and opening Allure report after FIREFOX..." -ForegroundColor DarkBlue
allure generate target/allure-results --clean -o target/allure-report
allure open target/allure-report
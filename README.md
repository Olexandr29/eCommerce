# 🛒 eCommerce UI Automation Testing Project

## 🎯Why this Project
This project demonstrates:
- Real-world automation using cross-browser and cross-platform testing
- CI\CD integration from scratch (no frameworks)
- Generating and publishing Allure reports with history
- Running tests by tags and browser combinations

---

## Technologies Used:
- **Java 21**, **Maven**
- **Selenium WebDriver**
- **jUnit5**
- **Allure Reports** with custom executor & environment
- **GitHub Actions**: CI/CD + matrix strategy
- **GitHub Pages**: auto-publish Allure report
- **Browsers**: Chrome, Firefox, Edge, Safari
- **OS**: Windows, Linux, macOS
---

## 🗃️Test Scenarios Covered
- ✅ **Smoke tests**: basic login, logout, product presence
- ✅ **Sanity tests**: cart, checkout flow, navigation
- ✅ **Functional tests**: sorting, product details, calculations
- ✅ **Negative tests**: SQL injection, long inputs, spacing
- ✅ **UI/UX tests**: responsive layout, button states, logo

---
# ▶️ The ways of running tests (remote and local):
## 🌐 1) Remote run via 🚀 CI/CD (GitHub Actions)
### ✅ Triggered:
1) [Manually](https://github.com/Olexandr29/eCommerce/blob/main/.github/workflows/manually-triggered-run-tests-on-windows.yml) 
on Windows+Chrome 
2) By push:
<br>- [on Linux+Chrome + published Allure report on GitHub Pages](https://github.com/Olexandr29/eCommerce/blob/main/.github/workflows/auto-triggered-by-push-on-linux-chrome-and-publish-allure-report-on-github-pages.yml)
<br>- [on matrix OS & Browsers + published separated Allure reports as artifacts](https://github.com/Olexandr29/eCommerce/blob/main/.github/workflows/auto-triggered-tests-by-push-on-matrix-os-browser.yml)
3) [By schedule](https://github.com/Olexandr29/eCommerce/blob/main/.github/workflows/auto-triggered-tests-by-schedule.yml)

### 🧩 Matrix Strategy
| OS      | Browsers               |
|---------|------------------------|
| Windows | Chrome, Firefox, Edge  |
| Linux   | Chrome, Firefox        |
| macOS   | Chrome, Firefox, Safari|

## 📊 Allure Report
[👉 Click to see the Allure report on GitHub Pages](https://olexandr29.github.io/eCommerce/)

## 💻 2) Local run via ⬛>_Terminal (inside IDEA)
### ✅ Triggered on Windows and:

**Chrome browser** (by default) use the command below:
```
mvn clean test
```
**Other browser** (e.g. Firefox or Edge) use:
```
mvn clean test -Dbrowser=Firefox
```
**Cross-browser**(Chrome, Firefox, Edge) use:
```
mvn clean test -Dbrowser="Chrome Firefox Edge"
```
**Default Chrome browser** and specific **tag** use:
```
mvn clean test -Dgroups=Smoke
```
Specific **Browsers** and **tags** use:
```
mvn clean test -Dgroups="Functional Negative" -Dbrowser="Edge Firefox"
```

### 🧩 Local run Strategy (Options)
| OS      | Browsers        | Tags                                    |
|---------|-----------------------|-----------------------------------|
| Windows | Chrome, Firefox, Edge | Smoke, Sanity, Functional, Negative, UiUx |

## 📊 Allure Reports:
**###Temporary:**

For generating temporary Allure report (on the **temp** folder not related to the project) and without history
after completing the previous command like
```
mvn clean test
```
use:
```
allure serve
```
**###Constant with history:**

For generating Allure report (on the **target** folder inside the project) and add testing history (displays after second and more runs)
use run without the **clear** phase:
```
mvn test
```
and then the commands:
```
allure generate target/allure-results --clean -o target/allure-report
```
and:
```
allure open target/allure-report
```

For cross-browser testing and generating an Allure report with history 
was created the [next script](https://github.com/Olexandr29/eCommerce/blob/main/autorun-by-tags-and-browser-and-generate-allure-report-with-history.ps1)

Just run it and look at the Allure report with history (use the command below):
```
./autorun-by-tags-and-browser-and-generate-allure-report-with-history.ps1
```

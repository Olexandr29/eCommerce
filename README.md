# 🛒 eCommerce UI Automation Testing Project

## 🎯The goal of the repo is to demonstrate the skills with:
- UI autotests (on Java) for cross-browser and cross-platform execution
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
- **Jenkins**: CI/CD
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

## ▶️ The ways of running tests (remote and local):
### 1) 🌐 Remote run via 🚀 CI/CD (GitHub Actions)
- #### ✅ Triggered:
    <details>
        <summary>Expand details about triggered methods </summary>
  
  -  a) [Manually](https://github.com/Olexandr29/eCommerce/blob/main/.github/workflows/manually-triggered-run-tests-on-windows.yml)
     on Windows+Chrome 
  - b) By push:
             <br>- [on Linux+Chrome + published Allure report on GitHub Pages](https://github.com/Olexandr29/eCommerce/blob/main/.github/workflows/auto-triggered-by-push-on-linux-chrome-and-publish-allure-report-on-github-pages.yml)
             <br>- [on matrix OS & Browsers + published separated Allure reports as artifacts](https://github.com/Olexandr29/eCommerce/blob/main/.github/workflows/auto-triggered-tests-by-push-on-matrix-os-browser.yml)
  - c) [By schedule](https://github.com/Olexandr29/eCommerce/blob/main/.github/workflows/auto-triggered-tests-by-schedule.yml)
               on Windows+Chrome just with tag Smoke
  </details>
    
- #### 🧩 Matrix strategy
    <details>
  <summary>Expand details about matrix strategy</summary>

  <table>
    <thead>
      <tr>
        <th>OS</th>
        <th>Browsers</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>Windows</td>
        <td>Chrome, Firefox, Edge</td>
      </tr>
      <tr>
        <td>Linux</td>
        <td>Chrome, Firefox</td>
      </tr>
      <tr>
        <td>macOS</td>
        <td>Chrome, Firefox, Safari</td>
      </tr>
    </tbody>
  </table>

</details>
   

- #### 📊 Allure Report
    <details>
    <summary> Expand details about Allure report</summary>

  [👉 The lates Allure report on GitHub Pages](https://olexandr29.github.io/eCommerce/)
    </details>
___

### 💻 2) Local run via ⬛>_Terminal (inside IDEA)
- #### ✅ Triggered manually on Windows and:
<details>
        <summary>Expand details about manually triggered commands</summary>
<br>

**Chrome browser** (by default) use the command below:
```
mvn clean test
```
**Other browser** (e.g. Firefox or Edge) use:

```
mvn clean test -Dbrowser=Firefox
```
or
```
mvn clean test -Dbrowser=Edge
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
🔙 [click here and click on 'Expand details about manually triggered commands' to collaps the section](https://github.com/Olexandr29/eCommerce?tab=readme-ov-file#-triggered-manually-on-windows-and)
  </details>

- #### 🧩 Matrix Browser and Tags Strategy
<details>
<summary>Expand details about available options</summary>

| OS      | Browsers        | Tags                                    |
|---------|-----------------------|-----------------------------------|
| Windows | Chrome, Firefox, Edge | Smoke, Sanity, Functional, Negative, UiUx |

</details>

- #### 📊 Allure Report:
<details>
    <summary>Expand details about methods to generate Allure report</summary>

###### ⏱️Temporary:

For generating temporary Allure report (on the **temp** folder not related to the project) and without history
after completing the previous command like
```
mvn clean test
```
use:
```
allure serve target/allure-results
```
Here is an example of such Allure report
![Allure screen page without history](</screenShots/1.0 Allure report without history.png>)
[if you want to look at the folder where the report is saved click the link](https://github.com/Olexandr29/eCommerce/blob/main/screenShots/1.1%20report%20generated%20to%20temp%20folder%20not%20related%20to%20the%20project.png)
___

###### 💼 **Constant with history:**

For generating Allure report (on the **target** folder inside the project) and add testing history (displays after second and more runs)
use this 3 steps:
    <details>
    <summary>1) use any command for local run but without the **clear** phase, like:</summary>
```
mvn test
```
```
mvn test -Dbrowser=Firefox
```
```
mvn test -Dbrowser="Chrome Firefox Edge"
```
```
mvn test -Dgroups=Smoke
```
```
mvn test -Dgroups="Functional Negative" -Dbrowser="Edge Firefox"
```
or any of you want, but without *clean* phase
  </details>

<details>
<summary>2) copy history from the run:</summary>

- 2.1) create folder if it deleted
```
New-Item -ItemType Directory -Force -Path "target/allure-results/history"
```
- 2.2) copy history from previous report to the *result* folder for generate a new report
```
Copy-Item -Recurse -Force "target/allure-report/history/*" "target/allure-results/history/" -ErrorAction SilentlyContinue
```
</details>

  <details>
  <summary>3) generate and open a report:</summary>

  - 3.1) generate:
```
allure generate target/allure-results --clean -o target/allure-report
```

- 3.2) and open:
```
allure open target/allure-report
```
  </details>

Here is an example of such Allure report
![Allure screen page with history](https://github.com/Olexandr29/eCommerce/blob/main/screenShots/2.0%20Allure%20report%20with%20historytrends.png)
[if you want to look at the folder where the report is saved click the link](https://github.com/Olexandr29/eCommerce/blob/main/screenShots/2.1%20report%20generated%20to%20Report%20folder%20insdie%20the%20project%20target.png)
___
For cross-browser testing and generating an Allure report with history 
was created the [next script](https://github.com/Olexandr29/eCommerce/blob/main/autorun-by-tags-and-browser-and-generate-allure-report-with-history.ps1)

Just run it and look at the Allure report with history (use the command below):
```
./autorun-by-tags-and-browser-and-generate-allure-report-with-history.ps1
```
Here is an example of such Allure report
![Allure screen page with history (has run by script)](<./screenShots/3.0 Allure report with history run by ps script.png>)
[if you want to look at the folder where the report is saved click the link](https://github.com/Olexandr29/eCommerce/blob/main/screenShots/3.1.%20inside%20the%20project%20target%20folder%20.png)
  </details>
<!-- <img alt="Allure report" src="https://github.com/Olexandr29/eCommerce/blob/main/screenShots/3.0%20Allure%20report%20with%20history%20run%20by%20ps%20script.png?raw=true" width="500" style="float: left"> -->

### 3) 🌐 Remote run via 🚀 CI/CD (Jenkins)
- #### ✅ Triggered:
<details>
        <summary>Expand details about triggered methods </summary>

  *Manually via UI (by clicking "Build Now") on Windows+Chrome*
   </details>

- #### 🧩 Matrix strategy
<details>
    <summary>Expand details about matrix strategy</summary>

|OS|Browser|
|--|-------|
|Windows|Chrome|

  </details>

- #### 📊 Allure Report
<details>
<summary> Expand details about Allure report</summary>

Generated via the command inside 'eCommerce-Jenkins-CI' pipeline

```
mvn allure:report
```

and opened in Jenkins via 'Allure Report' plugin

https://github.com/Olexandr29/eCommerce/blob/main/screenShots/5.0%20Jenkins%20buil%20results.png
https://github.com/Olexandr29/eCommerce/blob/main/screenShots/5.1%20Allure%20report%20where%20Executors%20is%20Jenkins.png

</details>
___

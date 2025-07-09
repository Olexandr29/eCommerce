# 🛒 eCommerce UI Automation Testing Project

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

## 🚀 CI/CD with GitHub Actions
### ✅ Triggered:
1) [Manually](https://github.com/Olexandr29/eCommerce/blob/main/.github/workflows/manually-triggered-run-tests-on-windows.yml) 
on Windows+Chrome 
2) By push:<br>*[on Linux+Chrome](https://github.com/Olexandr29/eCommerce/blob/main/.github/workflows/auto-triggered-tests-by-push-on-linux-chrome.yml)
<br>*[on matrix OS & Browsers + published separated Allure reports as artifacts](https://github.com/Olexandr29/eCommerce/blob/main/.github/workflows/auto-triggered-tests-by-push-on-matrix-os-browser.yml)
<br>[on Linux+Chrome + published Allure report on GitHub Pages](https://github.com/Olexandr29/eCommerce/blob/main/.github/workflows/publish-allure-report-on-github-pages.yml)
3) By schedule

Legend / Status Keys of the file:
- [ ]  task
- [x] or ✅ completed task 
  - ⏳  In progress
  - ❌ Cancelled
  - ⚠️ Blocked
___

- [ ] Automate test cases 031-033
<details>
<summary>✅ Automated:</summary>

TC-031: Cart state persists after logout
- Preconditions: Logged in as standard_user
- Steps:
1. Add item to cart
2. Logout
3. Login again with credentials from Preconditions
- Expected Result: Cart should retain previously added items (cart is persisted across sessions)
 
TC-032: Add item, go to cart, then back, remove from inventory
- Preconditions: Logged in
- Steps:
1. Add item to cart
2. Go to cart → Back to inventory
3. Click "Remove"
- Expected: Cart badge disappears, cart updated

<br>

⚠️  **Blocked until the bug will be fixed**

TC-033: Checkout with empty cart - 🐞 **Bug is found**
- Preconditions: Logged in, cart is empty
- Steps:
1. Navigate to cart
2. Click "Checkout"
- Expected: User stays on cart page, no progress possible
</details>


___

- [x] [ The latest Allure report is here] (https://olexandr29.github.io/eCommerce/)

- [ ] Add Allure report for cross-browser and cross-platform testing workflow
---
- [x] [![Automatically triggered Smoke tests by schedule every Tuesday and Thursday](https://github.com/Olexandr29/Automation/actions/workflows/auto-triggered-tests-by-schedule.yml/badge.svg)](https://github.com/Olexandr29/Automation/actions/workflows/auto-triggered-tests-by-schedule.yml)

- [ ] Create a workflow for running Smoke tests on Windows, triggered by schedule every Tuesday and Thursday
---
- [x] [![Automatically triggered JUnit5 tests by push on matrix OS and Browsers](https://github.com/Olexandr29/Automation/actions/workflows/auto-triggered-tests-by-push-on-matrix-os-browser.yml/badge.svg)](https://github.com/Olexandr29/Automation/actions/workflows/auto-triggered-tests-by-push-on-matrix-os-browser.yml)

- [ ] Create a remote (GitHub) workflow for cross-browser and cross-platform testing, triggered by push
---
- [x] Created: 
- remote - [![Manually triggered JUnit5 tests on Windows](https://github.com/Olexandr29/Automation/actions/workflows/manually-triggered-run-tests-on-windows.yml/badge.svg)](https://github.com/Olexandr29/Automation/actions/workflows/manually-triggered-run-tests-on-windows.yml)

- local - [PowerShell script for run by tabs and browsers on Windows](https://github.com/Olexandr29/eCommerce/blob/main/autorun-by-tags-and-browser-and-generate-allure-report-with-history.ps1)


- [ ] Create and run:
- local workflow
- remote workflow
___

- [ ] Generate such Allure report data: Environment, Categories and Executors ✅
___
- [ ] Make cross-browser testing for Chrome, Mozilla Firefox and Edge ✅
___
- [ ] Run tests by tags in order: smoke, sanity, functional and ui/ux with negative ✅
___
- [ ] Add more info for Allure report ✅
___
- [ ] Migrate from jUnit4 to jUnit5 and create Allure Report
for all these 30 tc ✅
___
- [ ] Automate test @Negative (tc028-030)
<details>
<summary>
✅ Automated:
</summary>

TC-028: Entering SQL Injection in Username
- Preconditions: User is on the login page: https://www.saucedemo.com/
- Steps:
1. Enter admin' OR '1'='1 into the Username field
2. Enter any password
3. Click the Login button
- Expected Result: Login is rejected, and an error message is shown

TC-029: Long Value in Username Field
- Preconditions: User is on the login page
- Steps:
1. Enter a string of 500 characters (e.g., "a" × 500) into the Username field
2. Enter any password
3. Click the Login button
- Expected Result: Login fails, either due to an error message or input being truncated

TC-030: Login with Leading/Trailing Spaces in Username
- Preconditions: User is on the login page
- Steps: 
1. Enter standard_user (with spaces before and after) in the Username field
2. Enter the correct password
3. Click the Login button
- Expected Result: Login fails because the spaces are treated as part of the username
</details>

___
- [ ] Automate tests @UI/UX (tc025-027)
<details>
<summary>
✅ Automated:
</summary>

TC-025: Logo and header visibility
- Preconditions: User is logged in as standard_user, on /inventory.html
- Step: 1. Observe top of the page
- Expected Result: Logo + "Products" heading visible

TC-026: Browser window resizing
- Preconditions: User is logged in as standard_user, on /inventory.html
- Step: 1. Resize browser window (e.g. shrink, expand)
- Expected Result: Layout adjusts responsively, no broken layout

TC-027: Button state change on interaction
- Preconditions: User is logged in as standard_user, on /inventory.html
- Steps:
1. Click “Add to cart”
2. Observe button changes
3. Click “Remove”
- Expected Result: Button text/color changes appropriately at each step
</details>

___
- [ ] Automate tests @Functional (tc014-024)
<details>
<summary>✅Automated:
</summary>

*Products Page*
<br>TC-014: Sort products by price (low to high)
- Preconditions: User is logged in as standard_user, on /inventory.html
- Step: 1. Select "Price (low to high)"
- Expected Result: Items sorted correctly from cheapest to most expensive

TC-015: Sort products by name (Z to A)
- Preconditions: User is logged in as standard_user, on /inventory.html
- Step: 1. Select "Name (Z to A)"
- Expected Result: Items sorted in reverse alphabetical order

TC-016: Product details view
- Preconditions: User is logged in as standard_user, on /inventory.html
- Step: 1. Click on product name
- Expected Result: New page shows full info: image, name, description, price

___
*Cart Functionality*
<br>TC-017: Add multiple items to cart
- Preconditions: User is logged in as standard_user, on /inventory.html
- Step: 1. Add 3 different products
- Expected Result: Cart badge shows "3"

TC-018: Remove one item from cart
- Preconditions: Logged in as standard_user, 3 items already added
- Steps:
1. Navigate to cart
2. Click "Remove" for one item
- Expected Result: Badge shows "2", only 2 items remain

TC-019: Cart state persists across navigation
- Preconditions: Logged in as standard_user, 1 item added to cart
- Step: 1. Navigate to another page (e.g. product detail) and return
- Expected Result: Cart badge is preserved, item still in cart

___
*Checkout Flow*
<br>TC-020: Full purchase flow
- Preconditions: Logged in as standard_user, on /inventory.html
- Step: 1. Add product → Go to cart → Checkout → Fill info → Finish
- Expected Result: Confirmation page with "Thank you for your order!"

TC-021: Checkout form validation
- Preconditions: Logged in as standard_user, in checkout step one
- Steps:
1. Leave fields empty
2. Click Continue
- Expected Result: Error like "First Name is required"

TC-022: Total price with tax is calculated correctly
- Preconditions: Logged in, 2+ known items added, in checkout overview
- Step: 1. Observe item prices, subtotal, tax, and total
- Expected Result: Subtotal + Tax = Total (matches UI values)

___
*Navigation*
<br>TC-023: Back from product detail to product list
- Preconditions: Logged in as standard_user, on product detail page
- Step: 1. Click “Back to products”
- Expected Result: Return to /inventory.html

TC-024: Use browser back button from product detail
- Preconditions: Logged in as standard_user, on product detail page
- Steps 1. Press browser back
- Expected Result: Returns to /inventory.html
</details>

___
- [ ] Automate tests @Sanity (tc006-013)  
<details>
<summary>✅ Automated:</summary>

TC-006: Successful login as performance_glitch_user
- Preconditions: The website saucedemo.com is open
- Steps:
1. Enter performance_glitch_user in the Username field.
2. Enter secret_sauce in the Password field.
3. Click the Login button.
- Expected Result:
The user is redirected to the products page (/inventory.html) with the heading "Products".

TC-007: Unsuccessful login with empty fields
- Preconditions: The website saucedemo.com is open
- Steps:
1. Leave both Username and Password fields empty.
2. Click the Login button.
- Expected Result:
An error message "Username is required" is displayed.

TC-008: Unsuccessful login with non-existent user
- Preconditions: The website saucedemo.com is open
- Steps:
1. Enter fake_user in the Username field.
2. Enter fake_password in the Password field.
3. Click the Login button.
- Expected Result:
An error message "Username and password do not match any user" is displayed.

TC-009: Navigate to the cart page
- Preconditions: The user is logged in as standard_user
- Step: 1. Click the shopping cart icon in the top-right corner.
- Expected Result:
The user is redirected to /cart.html and sees the contents of the cart.

TC-010: Remove item from the cart
- Preconditions: The user is logged in as standard_user and has added one item to the cart
- Step: 1. Click the "Remove" button next to the added item.
- Expected Result:
   The item is removed from the cart and the cart badge disappears.

TC-011: Proceed to checkout
- Preconditions: The user is logged in as standard_user and has at least one item in the cart
- Steps:
1. Navigate to the cart page.
2. Click the "Checkout" button.
- Expected Result:
The user is redirected to /checkout-step-one.html.

TC-012: Fill in user information at the checkout
- Preconditions: The user is on the /checkout-step-one.html page
- Steps:
1. Enter First Name, Last Name, and Zip/Postal Code.
2. Click the "Continue" button.
- Expected Result:
The user is redirected to /checkout-step-two.html.

TC-013: Cancel from the overview page
- Preconditions: The user is on the /checkout-step-two.html page
 - Step: 1. Click the "Cancel" button.
- Expected Result:
The user is redirected back to the inventory page (/inventory.html).
</details>

___

- [ ] Automate tests @Smoke (tc001-005)
<details><summary>✅ Automated:</summary>
TC-001: Successful login with valid credentials 

- Preconditions: The website saucedemo.com is open 
- Steps:
1. Enter standard_user in the Username field.
2. Enter secret_sauce in the Password field.
3. Click the Login button. 
- Expected Result:
The user is redirected to the products page (/inventory.html) with the heading "Products".

TC-002: Unsuccessful login with locked user
- Preconditions: The website saucedemo.com is open 
- Steps:
1. Enter locked_out_user in Username
2. Enter secret_sauce in Password
3. Click Login
- Expected Result: 
Error message "Sorry, this user has been locked out."

TC-003: Check presence of product list after login
- Preconditions: Login as standard_user
- Step: 1. Verify that multiple products are displayed
- Expected Result: Product list contains items with names and prices

TC-004: Logout from application
- Preconditions: Login as standard_user
- Steps:
1. Click the menu button
2. Click Logout
- Expected Result: 
User is redirected to login page

TC-005: Add item to cart and check badge
- Preconditions: Login as standard_user
- Steps: 
1. Click "Add to cart" for any item
2. Check the cart icon
- Expected Result: 
Cart icon shows badge with "1"
</details>
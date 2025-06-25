Generate such Allure report data: Environment, Categories and Executors  

Make cross-browser testing for Chrome, Mozilla Firefox and Edge

Run tests by tags in order: smoke, sanity, functional and ui/ux with negative

Add more info for Allure report

Migrate from jUnit4 to jUnit5 and create Allure Report
for all these 30 tc

@Negative test - automate:

TC-028: Entering SQL Injection in Username
Preconditions: User is on the login page: https://www.saucedemo.com/
Steps:
1. Enter admin' OR '1'='1 into the Username field
2. Enter any password
3. Click the Login button
Expected Result: Login is rejected, and an error message is shown
Note: Validates resistance to SQL injection

TC-029: Long Value in Username Field
Preconditions: User is on the login page
Steps:
1. Enter a string of 500 characters (e.g., "a" × 500) into the Username field
2. Enter any password
3. Click the Login button
Expected Result: Login fails, either due to an error message or input being truncated
Note: Tests input length limitations

TC-030: Login with Leading/Trailing Spaces in Username
Preconditions: User is on the login page
Steps:
1. Enter standard_user (with spaces before and after) in the Username field
2. Enter the correct password
3. Click the Login button
Expected Result: Login fails because the spaces are treated as part of the username
Note: Checks if spaces are trimmed automatically


@UI/UX Checks (tc25-27) - automated:

TC-025: Logo and header visibility
Preconditions: User is logged in as standard_user, on /inventory.html
Steps:
1. Observe top of the page
Expected Result: Logo + "Products" heading visible

TC-026: Browser window resizing
Preconditions: User is logged in as standard_user, on /inventory.html
Steps:
1. Resize browser window (e.g. shrink, expand)
Expected Result: Layout adjusts responsively, no broken layout

TC-027: Button state change on interaction
Preconditions: User is logged in as standard_user, on /inventory.html
Steps:
1. Click “Add to cart”
2. Observe button changes
3. Click “Remove”
Expected Result: Button text/color changes appropriately at each step



@Functional tests - automated:

Products Page
TC-014: Sort products by price (low to high)
Preconditions: User is logged in as standard_user, on /inventory.html
Steps:
1. Select "Price (low to high)"
Expected Result: Items sorted correctly from cheapest to most expensive

TC-015: Sort products by name (Z to A)
Preconditions: User is logged in as standard_user, on /inventory.html
Steps:
1. Select "Name (Z to A)"
Expected Result: Items sorted in reverse alphabetical order

TC-016: Product details view
Preconditions: User is logged in as standard_user, on /inventory.html
Steps:
1. Click on product name
Expected Result: New page shows full info: image, name, description, price


Cart Functionality
TC-017: Add multiple items to cart
Preconditions: User is logged in as standard_user, on /inventory.html
Steps:
1. Add 3 different products
Expected Result: Cart badge shows "3"

TC-018: Remove one item from cart
Preconditions: Logged in as standard_user, 3 items already added
Preconditions: 3 items added
Steps:
1. Navigate to cart
2. Click "Remove" for one item
Expected Result: Badge shows "2", only 2 items remain

TC-019: Cart state persists across navigation
Preconditions: Logged in as standard_user, 1 item added to cart
Steps:
1. Navigate to another page (e.g. product detail) and return
Expected Result: Cart badge is preserved, item still in cart


Checkout Flow
TC-020: Full purchase flow
Preconditions: Logged in as standard_user, on /inventory.html
Steps:
1. Add product → Go to cart → Checkout → Fill info → Finish
Expected Result: Confirmation page with "Thank you for your order!"

TC-021: Checkout form validation
Preconditions: Logged in as standard_user, in checkout step one
Steps:
1. Leave fields empty
2. Click Continue
Expected Result: Error like "First Name is required"

TC-022: Total price with tax is calculated correctly
Preconditions: Logged in, 2+ known items added, in checkout overview
Steps:
1. Observe item prices, subtotal, tax, and total
Expected Result: Subtotal + Tax = Total (matches UI values)


Navigation
TC-023: Back from product detail to product list
Preconditions: Logged in as standard_user, on product detail page
Steps:
1. Click “Back to products”
Expected Result: Return to /inventory.html

TC-024: Use browser back button from product detail
Preconditions: Logged in as standard_user, on product detail page
Steps:
1. Press browser back
Expected Result: Returns to /inventory.html





@Sanity tests - automated:
TC-006: Successful login as performance_glitch_user
Preconditions: The website saucedemo.com is open
Steps:
1. Enter performance_glitch_user in the Username field.
2. Enter secret_sauce in the Password field.
3. Click the Login button.
Expected Result:
The user is redirected to the products page (/inventory.html) with the heading "Products".

TC-007: Unsuccessful login with empty fields
Preconditions: The website saucedemo.com is open
Steps:
1. Leave both Username and Password fields empty.
2. Click the Login button.
Expected Result:
An error message "Username is required" is displayed.

TC-008: Unsuccessful login with non-existent user
Preconditions: The website saucedemo.com is open
Steps:
1. Enter fake_user in the Username field.
2. Enter fake_password in the Password field.
3. Click the Login button.
Expected Result:
An error message "Username and password do not match any user" is displayed.

TC-009: Navigate to the cart page
Preconditions: The user is logged in as standard_user
Steps:
1. Click the shopping cart icon in the top-right corner.
Expected Result:
The user is redirected to /cart.html and sees the contents of the cart.

TC-010: Remove item from the cart
Preconditions: The user is logged in as standard_user and has added one item to the cart
Steps:
1. Click the "Remove" button next to the added item.
   Expected Result:
   The item is removed from the cart and the cart badge disappears.

TC-011: Proceed to checkout
Preconditions: The user is logged in as standard_user and has at least one item in the cart
Steps:
1. Navigate to the cart page.
2. Click the "Checkout" button.
Expected Result:
The user is redirected to /checkout-step-one.html.

TC-012: Fill in user information at the checkout
Preconditions: The user is on the /checkout-step-one.html page
Steps:
1. Enter First Name, Last Name, and Zip/Postal Code.
2. Click the "Continue" button.
Expected Result:
The user is redirected to /checkout-step-two.html.

TC-013: Cancel from the overview page
Preconditions: The user is on the /checkout-step-two.html page
Steps:
1. Click the "Cancel" button.
Expected Result:
The user is redirected back to the inventory page (/inventory.html).



@Smoke tests - automated:
TC-001: Successful login with valid credentials
Preconditions: The website saucedemo.com is open
Steps:
1. Enter standard_user in the Username field.
2. Enter secret_sauce in the Password field.
3. Click the Login button.
Expected Result:
The user is redirected to the products page (/inventory.html) with the heading "Products".

TC-002: Unsuccessful login with locked user
Preconditions: The website saucedemo.com is open
Steps:
1. Enter locked_out_user in Username
2. Enter secret_sauce in Password
3. Click Login
Expected Result: 
Error message "Sorry, this user has been locked out."

TC-003: Check presence of product list after login
Preconditions: Login as standard_user
Steps:
1. Verify that multiple products are displayed
Expected Result: 
Product list contains items with names and prices

TC-004: Logout from application
Preconditions: Login as standard_user
Steps:
1. Click the menu button
2. Click Logout
Expected Result: 
User is redirected to login page

TC-005: Add item to cart and check badge
Preconditions: Login as standard_user
Steps:
1. Click "Add to cart" for any item
2. Check the cart icon
Expected Result: 
Cart icon shows badge with "1"
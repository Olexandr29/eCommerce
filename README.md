automate
@Sanity tests:

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

TC-009: Remove item from the cart
Preconditions: The user is logged in as standard_user and has added one item to the cart
Steps:
1. Click the "Remove" button next to the added item.
Expected Result:
The item is removed from the cart, the "Add to cart" button reappears, and the cart badge disappears.

TC-010: Navigate to the cart page
Preconditions: The user is logged in as standard_user
Steps:
1. Click the shopping cart icon in the top-right corner.
Expected Result:
The user is redirected to /cart.html and sees the contents of the cart.

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
The user is redirected back to the cart page (/cart.html).



@Smoke tests: - automated
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
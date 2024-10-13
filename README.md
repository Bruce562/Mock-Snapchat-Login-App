# Snapchat Login and Registration Clone
This project is an Android application that replicates the Snapchat login and registration process. It focuses on mimicking the iOS version of Snapchat's UI rather than the Play Store version. The app is developed using Java for the backend, PHP/SQL for database operations, and XML for layout and design. Additionally, it includes SMS-based phone number verification.

**Features**
* Login and Registration: Users can sign up and log in with a username, email, password, and phone number.
* SMS Verification: Phone numbers are verified using an SMS-based authentication system.
* UI Design: The user interface is inspired by the iOS version of Snapchat, focusing on a clean and minimalist design. XML files are used extensively for layout and custom-designed assets like buttons.
* Database Integration: User data is managed through an online SQL database connected via PHP/SQL scripts for storage and retrieval.

**Technologies Used**
* Android Studio (Java): For backend logic and overall application structure.
* PHP/SQL: Backend for managing the database connection and handling user data.
* XML: Used for creating layouts and customizing UI elements such as buttons and text fields.
* SmsManager: Library used for sending sms messages for phone verification

**Project Structure**
* /java/: Contains the Java files for backend logic, including login, registration, SMS verification functionality, and network request files.
* /res/layout/: XML files for layouts, including login and registration screens, designed to resemble Snapchat's iOS version.
* /res/drawable/: Custom assets and button designs created using XML.
* /res/values/: Contains XML files for colors, strings, styles, and other values.
* /DatabaseFiles/: PHP scripts for database interaction (hosted on an external server).

**How It Works**
* Login: Users log in by entering their email/username and password. The app verifies the credentials via a PHP script that communicates with the online SQL database.
* Registration: New users register by providing their email, password, and phone number. After registration, an SMS with a verification code is sent for phone number confirmation.
* SMS Verification: The app sends an SMS verification code to the user's phone using the SmsManager library, ensuring the phone number is valid.

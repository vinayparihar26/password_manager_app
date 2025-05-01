🔐 Password Manager App
A secure and simple Password Manager app built using Jetpack Compose with features like encryption, password generation, strength meter, and CRUD operations using Room DB.

🚀 Features
✅ Add, Edit, View, Delete Passwords
🔒 AES Encryption for password storage
🔄 Password Strength Meter (Weak/Medium/Strong)
🔐 Random Strong Password Generator
📦 Local Room Database
🧪 Input Validation (no empty fields)

📸 UI Overview
🏠 Home Screen:
List of saved passwords (Account, Username, Encrypted Password)
Click to open Bottom Sheet (View/Edit/Delete)
➕ Floating Button:
Opens Bottom Sheet to Add new password

🧑‍💻 Tech Stack
Jetpack Compose
Room Database
ViewModel
State Management with collectAsState()
AES Encryption
Kotlin Coroutines
Material3 UI

├── MainActivity.kt
├── screens/
│   └── HomeScreen.kt
├── viewmodel/
│   └── PasswordViewModel.kt
├── database/
│   └── AppDatabase.kt
├── dao/
│   └── PasswordDao.kt
├── entity/
│   └── PasswordEntity.kt
├── repository/
│   └── PasswordRepository.kt
├── util/
│   ├── EncryptionUtils.kt
│   └── PasswordUtils.kt


🛠️ How to Run
Clone the repo
Open in Android Studio
Run the project on emulator/device (Android 6.0+)

📌 Notes
Data stored locally with AES encryption
All mandatory fields are validated
Password strength logic: length, uppercase, lowercase, special characters

🧠 Future Improvements (Optional)
Biometric Authentication
Backup & Restore
Dark Mode
Sync with Cloud

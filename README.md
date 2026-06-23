# 🏆 ZGold Danny - Gold Zakat Calculator

<p align="center">
<img src="./ZGoldDanny_logo.png" alt="Logo" width="500">
</p>

A premium, responsive, and user-friendly native Android application designed to estimate gold zakat payments accurately based on standard Islamic Shariah rules in Malaysia. Built with a modern Champagne Gold and Navy design system, it provides instant calculations, helpful threshold notices, and full error-handling.

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)
![XML](https://img.shields.io/badge/XML-FF6600?style=for-the-badge&logo=xml&logoColor=white)

---

## 🤝 Academic & Corporate Partners

![Collaboration](https://img.shields.io/badge/Collaboration-UiTM%20Kelantan%20%7C%20Google%20Antigravity-blue?style=for-the-badge&logo=google&logoColor=white)

* **Academic Institution:** Universiti Teknologi MARA (UiTM) Cawangan Kelantan, Kampus Machang
* **Course:** ICT602 - Mobile Technology and Development
* **Developer:** Tengku Ahmad Danial Bin Tengku Noor Ashraf (2025197383)
* **Engineering Support:** Google Antigravity AI Coding Team

---

## 📁 Directory Structure

```text
MyApplication/
├── build.gradle.kts            # Project-level Gradle build configuration
├── settings.gradle.kts         # Gradle settings (module declarations)
├── gradlew / gradlew.bat       # Gradle wrapper executables
├── ZGoldDanny_logo.png         # Main application logo asset (HD)
├── Mobile Technology Assignment  20262.docx # Assignment requirement specifications
└── app/
    ├── build.gradle.kts        # App-level build file and dependencies
    └── src/
        └── main/
            ├── AndroidManifest.xml # App configuration, launcher activity, icons
            ├── java/com/example/myapplication/
            │   └── MainActivity.java # Core Java logic, calculations, validations
            └── res/
                ├── drawable/   # Icons (ic_calculator.xml, ic_info.xml, ic_share.xml, zgolddanny_logo.png)
                ├── layout/     # View designs (activity_main.xml)
                ├── menu/       # Menus (bottom_nav_menu.xml, main_menu.xml)
                └── values/     # Resources (colors.xml, strings.xml, themes.xml)
```

---

## 🎯 Rubric Fulfillment Map

Here is how the project maps directly to the assignment grading requirements:

| Marks | Section | Implementation Details |
| :--- | :--- | :--- |
| **6 Marks** | **Input Specifications** | * **Weight:** Double precision input for gold weight in grams (`input_weight`).<br>* **Type:** Gold usage type selection via `radio_group_category` (`Keep Gold` vs `Wear Gold`).<br>* **Price:** Double precision input for current price per gram in RM (`input_price`). |
| **6 Marks** | **Output Specifications** | * **Total Gold Value:** Calculated as `Weight * Price`.<br>* **Zakat Payable Weight:** Computed as `Max(0, Weight - Threshold)` (Threshold is 85g for Keep and 200g for Wear).<br>* **Zakat Payable Value:** Computed as `Payable Weight * Price`.<br>* **Total Zakat to Pay:** Evaluated at the 2.5% rate: `Payable Value * 0.025`. |
| **6 Marks** | **Customizations** | * **Themes:** Beautiful custom color system using Champagne Gold (`#C5A059`) and Deep Navy (`#1A2F4C`) in `colors.xml` and `themes.xml`.<br>* **Title Bar:** Custom toolbar displaying the app name **ZGold Danny**.<br>* **Icons:** Custom launcher icons and drawer icons (`ic_calculator`, `ic_info`, `ic_share`). |
| **6 Marks** | **About Page** | * Dedicated tab displaying developer details, course code (ICT602), copyright statement.<br>* Clickable project GitHub URL that opens the repository in a browser window. |
| **6 Marks** | **Good Design Practice** | * Real-time validation with detailed error highlighting (`TextInputLayout.setError`).<br>* Soft helper warning when the input weight is below the threshold (`notice_not_payable`).<br>* Instructional text for user guidance. |
| **ActionBar** | **Share Feature** | * Top-right action bar contains a **Share** button that broadcasts the app website/GitHub link using an `ACTION_SEND` Intent. |

---

## 💻 Running the App Locally

To build and run the application on your computer:
1. Install **Android Studio** (Koala or later recommended).
2. Clone this repository to your local drive.
3. Open Android Studio, select **Open**, and navigate to this project's root folder.
4. Let Gradle sync and resolve dependencies automatically.
5. Connect an Android device (via USB Debugging) or start a Virtual Device (Emulator).
6. Click the **Run** (green triangle) button to compile and launch.


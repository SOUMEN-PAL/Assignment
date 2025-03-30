# 📱 KMM Developer Assignment – Product Catalog App

## 📌 Project Overview
This is a Kotlin Multiplatform Mobile (KMM) Product Catalog App that fetches and displays product information from the [DummyJSON API](https://dummyjson.com/docs/products). The app follows Clean Architecture and MVVM principles, leveraging Jetpack Compose for UI development.
## Apk Download Link
https://drive.google.com/file/d/1INhBssYQsVqLGpc9QxS4abVHqxixuUGP/view?usp=sharing

## 🚀 Features
### 1️⃣ Product List Screen (on App Launch)
- Fetches product data from the API and displays a list of products.
- Each product includes:
  - 🏷️ Title
  - 📝 Description
  - 🖼️ Image
- Supports smooth scrolling and responsive UI.

### 2️⃣ Product Details Screen
- When a product is tapped, navigates to a detailed product screen displaying:
  - 🖼️ Product Images (carousel/gallery layout)
  - 🏷️ Product Title
  - 📝 Product Description
  - ⭐ Product Rating
  - 🗂️ Product Category
  - 💰 Product Price
  - 📄 Product Description (repeated)

## 🛠 Tech Stack
- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM + Clean Architecture
- **API Handling**: Retrofit
- **State Management**: Jetpack ViewModel + StateFlow

## 🌟 Bonus Features Implemented
- ✅ **Error Handling & Retry Logic**: Implemented to manage API failures gracefully.
- ✅ **Loading Indicators**: Shows progress while fetching data.
- ✅ **Smooth Navigation**: Optimized UX with seamless transitions.
- ✅ **Responsive & Polished UI**: Ensures adaptability across different screen sizes.

## 🔧 Setup Instructions
1. Clone the repository:
   git clone https://github.com/SOUMEN-PAL/Assignment.git
1. Open the project in **Android Studio** (latest stable version recommended).
2. Sync the **Gradle dependencies**.
3. Run the project on an **emulator** or **physical device**.

## 📌 Assumptions & Notes
- API responses are assumed to be **stable and consistent**.
- The app handles **basic network failures**, but deeper **caching mechanisms** are not included.
- Only **core dependencies** are used to keep the project **lightweight**.

## 📦 Submission Details
- The **complete source code** is available in this repository.

##App Screenshots

![WhatsApp Image 2025-03-30 at 7 21 04 PM(1)](https://github.com/user-attachments/assets/d8ce4b7e-de32-4d52-9591-13906f005d46)
![WhatsApp Image 2025-03-30 at 7 21 04 PM](https://github.com/user-attachments/assets/58ab54c2-85c9-4cad-bcc8-d79abea0bb6d)
![WhatsApp Image 2025-03-30 at 7 21 03 PM](https://github.com/user-attachments/assets/a8c2e283-3b48-49d6-93f7-08950aa2411d)



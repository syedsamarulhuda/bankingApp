# Banking App: Bank Transaction Screen

## Overview

This project showcases a user interface (UI) for a banking application, allowing users to seamlessly
deposit and withdraw funds while monitoring their transaction history. The app incorporates
safeguards to prevent withdrawals exceeding available balances and provides a clear view of past
transactions.

## Features

* **Deposit and Withdrawal:** Users can easily deposit and withdraw money from their account.
* **Balance Protection:** The app prevents withdrawals if the user has insufficient funds.
* **Transaction History:** Users can view a detailed history of their past transactions, including
  date, amount, and type.
* **Real-time Updates:** The transaction history and available balance are updated in real-time
  after each transaction.

## Tech Stack

* **Kotlin:** Modern and concise programming language for Android development.
* **Android Architecture Components:**  Provides a robust foundation for building maintainable and
  testable apps.
* **ViewModel:**  Exposes state to the UI and encapsulates business logic.
* **Clean Architecture:** Ensures a well-structured and modular codebase.
* **Koin:** Lightweight dependency injection framework for simplified object creation and management.
* **Room Database:**  Provides an abstraction layer over SQLite for efficient local data
  persistence.

## Architecture and Data Flow

The app follows a clean architecture approach, separating concerns and promoting testability. Here's
a simplified overview of the data flow:

1. **User Interaction:** The user interacts with the UI (e.g., clicks deposit/withdraw buttons,
   views transaction history).
2. **ViewModel:** The `TransactionViewModel` handles user actions and updates the UI state.
3. **Use Cases:**  `TransactionUseCase` and `TransactionHistoryUseCase` encapsulate specific
   business logic related to transactions and transaction history.
4. **Repositories:** `TransactionRepository` and `TransactionHistoryRepository` abstract data access
   operations, interacting with the local database.
5. **Data Source:** `DatabaseClient` provides access to the Room database for data persistence.

**Example: Viewing Transaction History**

1. The user navigates to the Transaction History screen.
2. The UI triggers the `getTransactionsHistory()` function in the `TransactionViewModel`.
3. The ViewModel calls the `getTransactionHistory()` use case.
4. The use case retrieves transaction data from the `TransactionRepository`.
5. The repository fetches the data from the Room database.
6. The data is returned to the ViewModel, which updates the UI with the transaction history.

## Future Improvements

* **Paging:** Implement paging to efficiently handle and display large transaction histories as the
  data grows.
* **Sorting:** Add sorting functionality to the transaction history for better user experience.
* **Network Integration:** Integrate with a remote API to fetch and update transaction data from a
  server.
* **Enhanced Testing:** Expand test coverage to include unit tests, integration tests, and UI tests.
* **Multiplatform Compatibility (KMP):** Explore using Kotlin Multiplatform to share code and
  potentially target other platforms like iOS.
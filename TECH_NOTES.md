## Persistence Notes
- The application uses in-memory storage for simplicity and zero setup.
- Data is lost on restart.
- Persistence is isolated behind a store layer and can be swapped with Redis or a database.

## Offer Configuration
- Active offers are defined in `src/main/resources/examples/offers-example.json`.
- Offers are loaded at application startup and applied dynamically.

## Design Notes
- Controllers handle HTTP concerns only.
- TransactionService orchestrates idempotency and state updates.
- ConfigurableOfferEngine contains pure business logic.

## AI Tool Usage

AI tools were intentionally used throughout this exercise, as encouraged, to simulate how I would work in a real development environment.
They were used to:
- Accelerate iteration and exploration of design approaches
- Generate initial drafts of code and configuration
- Discuss edge cases, trade-offs, and alternative implementations 
- AI-assisted output was treated as a starting point, not a final answer. 
- All code was reviewed, adapted, and validated manually, and the final structure and behavior reflect my own decisions and understanding.

## How to Try the Application (End-to-End Flow)

All APIs can be run via Swagger UI at:
http://localhost:8080/swagger-ui.html

---

### 1. Health Check
Verify the service is running.

- `GET /health`

---

### 2. Process a Transaction (first time)
Submit a transaction using the sample payload.

- `POST /transactions`

```
{
  "transactionId": "tx-1001",
  "shopperId": "shopper-123",
  "storeId": "store-01",
  "timestamp": "2025-01-10T10:15:00Z",
  "items": [
    {
      "sku": "SKU-MILK",
      "name": "Milk",
      "quantity": 2,
      "unitPrice": 5,
      "category": "grocery"
    },
    {
      "sku": "SKU-PLUSH",
      "name": "Promo Plush Toy",
      "quantity": 1,
      "unitPrice": 15,
      "category": "promo"
    }
  ]
}
```
Offers are applied and stickers are earned.

### 3. Process the Same Transaction Again (idempotency)

Submit the same request again with the same transactionId.

- `POST /transactions`

The transaction is not reprocessed and the cached result is returned.

### 4. View Shopper Status

Check the shopper’s current sticker balance and history.

- `GET /shoppers/shopper-123`

### 5. Attempt Redemption (expected to fail)

Try redeeming a reward before enough stickers are earned.

- `POST /redemptions/shopper-123`
```
{
"reward": "MUG"
}
```

Redemption fails due to insufficient stickers.

### 6. Process Another Transaction

Submit another transaction for the same shopper, changing only the transactionId.

- `POST /transactions`
```
{
  "transactionId": "tx-1002",
  "shopperId": "shopper-123",
  "storeId": "store-01",
  "timestamp": "2025-01-10T10:20:00Z",
  "items": [
    {
      "sku": "SKU-MILK",
      "name": "Milk",
      "quantity": 2,
      "unitPrice": 5,
      "category": "grocery"
    },
    {
      "sku": "SKU-PLUSH",
      "name": "Promo Plush Toy",
      "quantity": 1,
      "unitPrice": 15,
      "category": "promo"
    }
  ]
}
```

Stickers are accumulated on top of the existing balance.

### 7. Redeem a Reward (success case)

- `POST /redemptions/shopper-123`
```
{
"reward": "MUG"
}
```
### 8. View Shopper Status Again

Verify final shopper state.

- `GET /shoppers/shopper-123`
**DELIVERY APPLICATION**  
  
**Order Service (Order Lifecycle Owner)**  
  
**Responsible for everything about the order itself:**  

- Create delivery order
- Store pickup & delivery addresses
- Assign courier (via another service)
- Track order status:
- CREATED
- ASSIGNED
- PICKED_UP
- DELIVERED

Calculate delivery price  
Trigger events when:  

- ORDER_CREATED
- ORDER_ASSIGNED
- ORDER_DELIVERED

**Important boundary:**  

- Knows which courier is assigned
- Does NOT handle courier earnings or balance

**Courier Service (Courier State Manager)**  
  
**Responsible for courier-related operations:**  

- Create/manage couriers
- Track availability:
- FREE
- BUSY

**Event-driven responsibilities:**  

- On ORDER_ASSIGNED → mark courier as **BUSY**
- On ORDER_DELIVERED → mark courier as **FREE**

**Provides:**  

- Available courier for assignment

**Important boundary:**  

- Knows courier status
- Does NOT calculate earnings or payments

**Payment Service (Courier Earnings & Turnover)**  
  
**Responsible for financial logic:**  

- Calculate courier earnings per delivery
- Track courier balance
- Track turnover (total earnings over time)
- Store all payment records

**Core features:**  

- On ORDER_CREATED:
- Create payment record
- Mark as PENDING or UNPAID

On ORDER_DELIVERED:  

- Calculate earning (e.g., % or fixed fee)
- Add to courier balance
- Update turnover
- Mark payment as COMPLETED

**Courier can:**  

- View total earnings (turnover)
- View payment history
- See current balance

**Important boundary:**  

- Does NOT manage orders or courier availability
- Owns all financial data
- Only reacts to events

**Notification Service (Optional but simple)**  
  
**Responsible for:**  

- Informing users about events

**Features:**  

- On ORDER_CREATED → “Order created”
- On ORDER_ASSIGNED → “Courier assigned”
- On ORDER_DELIVERED → “Order delivered”

**Important:**  

- Pure listener, no business logic

**Example Flow**  
  
**1. Order Creation (Synchronous part)**  
  
Order Service:  

- Validates request
- Calculates delivery fee
- Calls Courier Service (Feign) → get available courier

**If no courier available:**  

- Order is **REJECTED**
- No events are published

**If courier found:**  

- Creates order:
- status = ASSIGNED (or CREATED → ASSIGNED)
- saves courierId
- saves price

**2. Order Service publishes events**  
  
ORDER_CREATED  
  
ORDER_ASSIGNED  
  
  
  
**3. Asynchronous Processing**  
  
**Payment Service**  

- On ORDER_CREATED:
- Creates payment record (PENDING)

**Courier Service**  

- On ORDER_ASSIGNED:
- Marks courier as **BUSY**

**Notification Service**  

- “Order created”
- “Courier assigned”

**4. Delivery Completion**  
  
Order Service:  

- Updates status → DELIVERED

**Validation rules:**  

- Cannot deliver before PICKED_UP
- Cannot update after DELIVERED

Publishes:  
  
ORDER_DELIVERED  
  
  
  
**5. After Delivery (Async)**  
  
**Payment Service**  

- Calculates courier earning
- Updates:
- balance
- turnover

Marks payment as COMPLETED  
  
  
**Courier Service**  

- Marks courier as **FREE**

**Notification Service**  

- “Order delivered”

**System Rules**  

- Services communicate:
- Synchronously → Feign (only when required)
- Asynchronously → Events (RabbitMQ)

No service accesses another service’s database  
Each service owns its own data  
Events represent facts, not commands  

-  ORDER_DELIVERED
-  UPDATE_COURIER_STATUS

**Mental Model**  

- Order Service → controls the flow
- Courier Service → controls availability
- Payment Service → controls money
- Notification Service → informs users
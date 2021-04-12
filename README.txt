Account:
	IBAN: String (Unique ID)
	Type: Enum (Current, Savings)
	Balance: Float
	
Holder:
	UserID: Int ID
	Username/Email: String
	Password: String
	Salt: String
	Role: Enum (Employee, Customer, Bank)
	Accounts<List<Account>>

Transaction
	transactionID: Int ID
	userPerforming: Int ID (Holder.UserID)
	fromAccount: Account
	toAccount: Account
	Amount: Float
	transactionTime: DateTime

Notities:
- Holder admin heeft role bank met een account die IBAN string NL01INHO0000000001 heeft
- Als fromAccount Savings is kan je alleen transaction uitvoeren van die account naar een account van dezelfde holder
- Als toAccount type Savings is moet fromAccount van dezelfde holder zijn
- Voor holder username of email moet bepaald worden
- Voor holder password genereer een salt die mag plain text blijven, combineer met password en encrypt met SHA256 voor password db field
- Customer role mag alleen zijn eigen transactions zien als hij toAccount of fromAccount is

Customer transaction notities:
-	Balance cannot become lower than a certain number defined per account, referred to as absolute limit
-	The cumulative value of transactions occurring on a day cannot surpass a certain number defined per user, referred to as day limit
-	The maximum amount per transaction cannot be higher than a certain number defined per user, referred to as transaction limit

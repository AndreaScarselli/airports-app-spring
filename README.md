##How to run the application

`./mvnw install; ./mvnw spring-boot:run -Dspring-boot.run.profiles=prod`

When the database will be filled with the rows, the application will ask to enter:
1. to see the airports in a country
2. to have a list with the countries with the highest number of airports
3. to exit


The application support partial country names as input. For example, by inserting 'ghan', the response will be:

---Country Ghana:

Tamale Airport: 236093,

Wa Airport: 236096,

Sunyani Airport: 236094,

Kumasi Airport: 236091,

Takoradi Airport: 236095,

Kotoka International Airport: 236092,

---Country Afghanistan:

Bost Airport: 329139,

Jalalabad Airport: 232782,

Mazar I Sharif Airport: 232778, 271059,

Sheberghan Airport: 232785,

Kandahar Airport: 232780,

Konduz Airport: 232783,

Herat Airport: 232777,

Shindand Airport: 232781,

Hamid Karzai International Airport: 232784,

Maimana Airport: 232786,

Tarin Kowt Airport: 329140,

Andkhoi Airport: 269407,

Bagram Air Base: 232779, 337570, 


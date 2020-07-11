# d2h-service
This system is designed for SatTV to operate their d2h business online.

####Feature Offered
1. View current balance in the account
2. Recharge Account
3. View available packs, channels and services
4. Subscribe to base packs
5. Add channels
6. Subscribe to special services
7. View current subscription details
8. Update email and phone number for notifications

#
####Code Highlights
1. The system has Service and DAO layer.
2. The service layer is responsible to validate the inputs and throw exception
3. The DAO layer is responsible to fetch the data from the DB
4. As of now, private methods has been placed to form the data at DAO layer. The new package/channel/subscription added in those method will reflect in the complete system.
5. Various Design patterns has been used to make the code reusable and take advantages of those patters.
   Ex- Customer class has many fields and many of them has to be set as null, so used Builder Design Pattern to initialize customer object
6. Minimum recharge amount is being set to Rs. 20 for the user.
7. All the beans has been made serializable
8. Class Level and Method level comments has been added to the interface.
9. Methods with access specifier has code comments in the implementation layer itself
10. Unit test cases has been written for all the core logical functionalities.
11. Some of the private methods in App.class is just for UI/UX formatting.
12. The program starts with App.class 



#
######Contact
Email: test@sattv.com

Call: 1800 XXX XXXX


#Coding Event App

##Section1: Purpose of the App.
Organize events by allowing user to create events with description, venue, 
number of participants and registration requirements.

##Section2: Current State of the App
Currently, the app is able to do the following to one or multiple events:
 - create.
 - edit. 
 - save.
 - delete. 

##Section3: Future Improvements
App need a person class to be able to create user account.
Person class will need the following properties:
- name(String)
- username(string)
- password(password)
- dob(date)
- Contact info(Object)
- gender(String)

###Methods:
getters, setters, and toString methods and an empty constructor

###Possible Support Classes: 
- Contact info class (name, mailing address, email, phone number),
- Account class (Account #, user id, password, account preferences),
- Friends class(UserId, name).

###Relationships Between Classes:
- Person /events: many-to-many
- person /contact: one-to-many
- person /account: one-to-one
- person /friend: one-to-many

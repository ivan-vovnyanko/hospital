# ⚕️ Hospital
**I present to you a simple project implementing a hospital service.**

This application is written in **java** which very partially implements a **hospital service.**</br>
There is interaction with the **local MySql database** (the instruction is present).</br>
The application is launched using **SmartTomcat** or **Tomcat** (the instruction will be for SmartTomcat).</br>
The application has a built-in **logger** that was created for convenient fixing of bugs.</br>

## Table of contents
- [**Functional**](https://github.com/ivan-vovnyanko/hospital#%EF%B8%8F-functional)
    - [**Doctor**](https://github.com/ivan-vovnyanko/hospital#1-doctor-%EF%B8%8F%EF%B8%8F)
    - [**Patient**](https://github.com/ivan-vovnyanko/hospital#2-patient-%EF%B8%8F%EF%B8%8F)
    - [**Medicine**](https://github.com/ivan-vovnyanko/hospital#3-medicine-%EF%B8%8F)
- [**Application link**](https://github.com/ivan-vovnyanko/hospital#-application-link)
- [**Used technologies**](https://github.com/ivan-vovnyanko/hospital#-used-technologies)
- [**Startup instructions**](https://github.com/ivan-vovnyanko/hospital#-startup-instructions)
- [**Structure**](https://github.com/ivan-vovnyanko/hospital#%EF%B8%8F-structure)


## ⚙️ Functional

**Below we will describe what tasks this web application performs.**

### ***1. Doctor ‍👨‍⚕️️***
The basic unit is the ***doctor‍️***.</br>

The doctor has a **date of birth, name, username and password.**
You can use the service only by authorizing your doctor account.
Authorization occurs through login and password.
If you don't have an account, you can register a new one by filling out a simple form.
You can also change the doctor's data (*date of birth, name, login or password*) after registration, but please note that you can only change your account.
If necessary, **you can permanently delete your doctor account.**
You can also *view other doctors' information* (except for the password).

>❗ Additional information about functionality with a doctor ❗
>- Until you log in, you will not be able to visit any pages of the service except for ***the login and register page.***
>- There is a restriction on the input data when registering a new user: </br>
   >  - The name must contain only English letters and spaces, the length of the name must be from 6 to 40 characters.
>  - Must be registered by a person who is 18 or older.
>  - The login must be *unique*, consist of English letters of two registers and numbers. Login length must be from 6 to 20 characters.
>  - The password must consist of English letters of two registers and numbers, and its length must be from 6 to 20 characters.
>- You can only change ***your*** account information.
   The same is about deletion, you can only delete the account on which you are authorized.
>- In the form for updating a doctor, you can leave those fields that do not need to be changed ***empty.***
>- When deleting an account, **all patients with this doctor become without a doctor** and the doctor **needs to be reassigned.**

### ***2. Patient 😷️️***
***Patients*** are very closely related to the ***doctor*** objects. </br>

The patient has fields for **name, diagnosis, treating physician, and a list of prescribed medicines.**
*Only doctors can register new patients*.
**Any doctor can change the basic information about the patient**, but only ***the current attending doctor can prescribe medicines.***
Any patient **can be removed** from the database.

>❗ Additional information about functionality with a patient ❗
>- There is a data entry restriction when creating a new patient: </br>
   >  - The name must consist of English characters and spaces, and the length of the name must be between 6 and 20 characters.
>  - You can leave the diagnosis field **empty** and add a diagnosis to him at any time after adding a patient.
>- When a new patient is added, ***the attending doctor who added him is assigned to him.***
>- When changing the attending doctor, you must specify **his ID**.
   ID can be seen in the ***"Doctors"*** tab.

### ***3. Medicine 💊️***
***Medicines*** can be prescribed to patients.<br>

**Medicine** are a separate entity. 
It has only one field - **the name.**
Any doctor **has access to all medicines.**
You have the ability to **delete** and **change the name** of existing medicines.

### ***4. Prescription 📝***
This is a doctor's prescription that indicates **the type of medicine** and **the amount.** <br>

This object stores information about **who wrote this prescription** and to **whom it was issued.** 
The functionality for creating a new prescription can be found **in the details page for a particular patient.**
On the creation page at the bottom there is a list of **all medicine IDs.**
You just need to enter this ID, and the count in ***numbers.***
You can also **delete** a prescription on **the patient page.**


## 🌐 Application link
**Now, having read about the functionality, you can try the application that is uploaded to the network.**

### <a style="color:#30edc5; text-decoration: none;" href="">Link not yet available(<a/>

## 💻 Used technologies
+ <a style="color:#ffed3a; text-decoration: none; font-size: 22px; font-weight: bold;" href="https://blogs.apache.org/maven/entry/apache-maven-compiler-plugin-version">Apache Maven Compiler Plugin</a>
+ <a style="color:#ffed3a; text-decoration: none; font-size: 22px; font-weight: bold;" href="https://mvnrepository.com/artifact/org.apache.maven.plugins/maven-war-plugin/3.3.2">Apache Maven WAR Plugin</a>
+ <a style="color:#ffed3a; text-decoration: none; font-size: 22px; font-weight: bold;" href="https://blogs.apache.org/maven/entry/apache-maven-checkstyle-plugin-version1">Apache Maven Checkstyle Plugin</a>
+ <a style="color:#ffed3a; text-decoration: none; font-size: 22px; font-weight: bold;" href="https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core/2.19.0">Apache Maven Log4j V.2 Plugin</a>
+ <a style="color:#ffed3a; text-decoration: none; font-size: 22px; font-weight: bold;" href="https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api/4.0.1">JavaServlet API</a>
+ <a style="color:#ffed3a; text-decoration: none; font-size: 22px; font-weight: bold;" href="https://mvnrepository.com/artifact/mysql/mysql-connector-java/8.0.22">MySQL Connector Java</a>
+ <a style="color:#ffed3a; text-decoration: none; font-size: 22px; font-weight: bold;" href="https://mvnrepository.com/artifact/javax.servlet/jstl/1.2">Java JSTL</a>
+ <a style="color:#ffed3a; text-decoration: none; font-size: 22px; font-weight: bold;" href="https://www.tutorialspoint.com/guice/guice_field_injection.htm#:~:text=Injection%20is%20a%20process%20of,the%20field%20of%20an%20object.">Dependency Injection With Field Injection (Injector)</a>
+ <a style="color:#ffed3a; text-decoration: none; font-size: 22px; font-weight: bold;" href="https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api/5.9.2">JUnit Jupiter API</a>



## 🔧 Startup instructions
1. Download the code from <a href="https://github.com/ivan-vovnyanko/hospital/archive/refs/heads/develop.zip">**GitHub**</a> and save it to your computer.
2. Now we are **setting up your local database based on MySql:**
    - **Install MySql** on your device, you can find information on how to set it up during installation on the network
      (<a href="https://dev.mysql.com/downloads/installer/">***Windows***</a> or <a href="https://dev.mysql.com/doc/refman/8.0/en/macos-installation.html">***MacOS***</a>).
    - Open **MySql Workbench** and **create a new database.** For your convenience, in the folder **"/src/main/resources/"** there is a file **table_statements.sql** in which there are all statements for creating a working local database.
    - **Create all the tables** in your database from the list in the **table_statements.sql** file.
    - Now we need to **configure the connector** in the application itself.
      Open the **ConnectionUtil** class in the **hospital.util** package and change the following fields:
        - In field ***PATH*** you must enter **the address of your local database.**
          *Usually, such links are the same for everyone and you do not need to change them.*
        - In field ***LOGIN*** you must enter **the login that you specified when installing mysql.**
        - In field ***PASSWORD*** you must enter **the password that you specified when installing mysql.**
3. Now you need **to set up your Tomcat:**
    - <a href="https://tomcat.apache.org/download-90.cgi#9.0.72">Download Tomcat</a> from open resources. I have version ***9.0.72***, I advise you to use it.
    - Download **SmartTomcat plugin** for Intellij idea.
    - Open the application launch options and select **SmartTomcat** as the plugin to launch.
      Select **the folder with your Tomcat plugin** in the settings.
    - Leave the ***Context path*** field blank.
4. Now click on the ***Run*** button or the hotkey combination ***Shift + F10.***
   If you see the line below **in the console**, then we have ***successfully launched the program.***


    http://localhost:8080/

5. The logger does not need to be configured, it is done in advance.
>❗ Please note that log files **may not be saved in the project folder.** If it is not there, look **in the folder below:** <br><br>
> ***C:\Users\"YOUR USER"\.SmartTomcat\hospital-service\hospital-service\logs***

## 🏗️ Structure

Below there will be **information about all the files that are in our project.**

- ### ⚛️ Model
  This package contains the main classes **Doctor, Patient and Medicine.**
- ### 🔗 Connection Util
  The package **util** has a ***Connection Util*** class that is responsible for connecting the application to the database using the **MySQL Connector.**
- ### 📚 Lib
  This package has a ***Injector class*** that helps achieve **Dependency Injection.**
  There are also necessary **annotations** for convenient interaction **between the injector and the application.**
- ### ⚠️ Exception
  This package contains all the custom **exceptions** that are used in the application.
- ### 💾 DAO
  This package contains *interfaces and their implementations* **for interaction between a program and an application.**
  **Recording all information in the database** is done thanks to this package.
- ### 🔧 Service
  This package contains *interfaces and their implementations* **to perform basic tasks with our objects.**
  The **service level** lies between the **controller level** and the **DAO level.**
- ### 🎮 Controller
  This package contains **classes for user interaction with the program.**
  The user **makes requests on the browser**, which are processed by our **Tomcat** using the same **controllers.**
- ### 🚫 Filter
  The program does **not allow actions to be taken by those who should not have access to it.**
  This is exactly what ***filters*** do.
- ### 🖼️ Web App
  This is the part that is responsible for ***the outer shell*** of our application.
  This folder contains the following files:
    - ***web.xml***</br>
      This file stores **settings for proper interaction between controllers and Tomcat.**
    - ***JSP Files***</br>
      The jsp files store **the markup that you see when using the service.**
    - ***CSS File***</br>
      The css file contains **the style settings for jsp files.**
      Without css style, the appearance of the program **was very uncomfortable to use.**
- ### 📝 Log4j Properties
  There is a ***log4j2.properties*** file in the application resources.
  It contains **settings for the correct operation of the logger.**

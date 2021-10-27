# ETA Notifications for Java - Servlets
[![Java Servlet CI](https://github.com/TwilioDevEd/eta-notifications-servlets/actions/workflows/gradle.yml/badge.svg)](https://github.com/TwilioDevEd/eta-notifications-servlets/actions/workflows/gradle.yml)

ETA notifications implementation with Java - Servlets and Twilio.

## Setup

### Requirements

- [Java Development Kit](https://adoptopenjdk.net/) version 8
- [ngrok](https://ngrok.com)
- [PostgreSQL](https://www.postgresql.org/)
- A Twilio account - [sign up](https://www.twilio.com/try-twilio)

### Twilio Account Settings

This application should give you a ready-made starting point for writing your
own appointment reminder application. Before we begin, we need to collect
all the config values we need to run the application:

| Config Value | Description                                                                                                                                                  |
| :---------------- | :----------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Account Sid  | Your primary Twilio account identifier - find this [in the Console](https://www.twilio.com/console).                                                         |
| Auth Token   | Used to authenticate - [just like the above, you'll find this here](https://www.twilio.com/console).                                                         |
| Phone number | A Twilio phone number in [E.164 format](https://en.wikipedia.org/wiki/E.164) - you can [get one here](https://www.twilio.com/console/phone-numbers/incoming) |

### Local Development

1. Clone this repository and `cd` into it

1. Create the database

   _The application uses PostgreSQL as the persistence layer. If you
   don't have it already, you should install it. The easiest way is by
   using [Postgres.app](http://postgresapp.com/)._

   ```bash
   createdb eta_notifications

   ```

1. Copy the sample configuration file `env.example`:

   ```bash
   cp .env.example .env
   ```

1. Edit the sample configuration file `.env` to match your configuration.

   See [Twilio Account Settings](#twilio-account-settings) to locate the necessary environment variables.

1. Execute the migrations.

   ```bash
   ./gradlew flywayMigrate
   ```

1. Modify seed data

   We have provided an example of name and phone number in the seed data. In order for
   the application to send sms notifications, you must edit this seed data providing
   a real phone number where you want the sms notifications to be received.

   Modify the [DBSeeder.java file](src/main/java/com/twilio/etanotifications/DBSeeder.java)

1. Seed the database

   ```bash
   ./gradlew dbSeed
   ```

1. Expose your application to the wider internet using ngrok

   You can click
   [here](#expose-the-application-to-the-wider-internet) for more details. This step
   is important because the application won't work as expected if you run it through
   localhost.

   ```bash
   ngrok http 8080
   ```

1. Run the application

   ```bash
   ./gradlew appRun
   ```
   Once Ngrok is running, open up your browser and go to your ngrok URL. It will
   look like this: `http://<your_subdomain>.ngrok.io`
 
   That's it!

### Expose the Application to the Wider Internet

If you want your application to be accessible from the internet, you can either
forward the necessary ports in your router, or use a tool like
[ngrok](https://ngrok.com/) that will expose your local host to the internet.

You can read [this blog post](https://www.twilio.com/blog/2015/09/6-awesome-reasons-to-use-ngrok-when-testing-webhooks.html)
for more details on how to use ngrok, but if you are using version 2.x, exposing
a specific port should be easily done with the following command:

```bash
ngrok http 8080
```

### Run the tests
In order to test the application, you must run the database migrations.

```bash
./gradlew check
```

## Meta

* No warranty expressed or implied. Software is as is. Diggity.
* [MIT License](http://www.opensource.org/licenses/mit-license.html)
* Lovingly crafted by Twilio Developer Education.

# ETA Notifications for Java - Servlets
[![Java Servlet CI](https://github.com/TwilioDevEd/eta-notifications-servlets/actions/workflows/gradle.yml/badge.svg)](https://github.com/TwilioDevEd/eta-notifications-servlets/actions/workflows/gradle.yml)

ETA notifications implementation with Java - Servlets and Twilio.

## Local Development

1. Clone this repository and `cd` into it

1. Create the database

   _The application uses PostgreSQL as the persistence layer. If you
   don't have it already, you should install it. The easiest way is by
   using [Postgres.app](http://postgresapp.com/)._

   ```bash
   $ createdb eta_notifications

   ```

1. Copy the sample configuration file `env.example`:

   ```bash
   $ cp .env.example .env
   ```

1. Edit the sample configuration file `.env` to match your configuration:

   Once you have edited the `.env` file, if you are using a UNIX operating system,
   just use the `source` command to load the variables into your environment:

   ```bash
   $ source .env
   ```

   _If you are using a different operating system, make sure that all the
   variables from the `.env` file are loaded into your environment._

   You can find your `TWILIO_ACCOUNT_SID` and `TWILIO_AUTH_TOKEN` here
   [Twilio Console](https://www.twilio.com/console).

   You can buy Twilio phone numbers at [Twilio numbers](https://www.twilio.com/console/phone-numbers/incoming).
   Your `TWILIO_NUMBER` should be set to a phone number you have purchased.

1. Execute the migrations.

   ```bash
   $ ./gradlew flywayMigrate
   ```

1. Modify seed data

   We have provided an example of name and phone number in the seed data. In order for
   the application to send sms notifications, you must edit this seed data providing
   a real phone number where you want the sms notifications to be received.

   Modify the
   [DBSeeder.java file](https://github.com/TwilioDevEd/eta-notifications-servlets/blob/master/src/main/java/com/twilio/etanotifications/DBSeeder.java)
   that is located at: `project_root/src/main/java/com/twilio/etanotifications/DBSeeder.java`

1. Seed the database

   ```bash
   $ ./gradlew dbSeed
   ```

1. Expose your application to the wider internet using ngrok

   You can click
   [here](#expose-the-application-to-the-wider-internet) for more details. This step
   is important because the application won't work as expected if you run it through
   localhost.

   ```bash
   $ ngrok http 8080
   ```

1. Run the application

   ```bash
   $ ./gradlew appRun
   ```
   Once Ngrok is running, open up your browser and go to your ngrok URL. It will
   look like this: `http://<your_subdomain>.ngrok.io`
 
   That's it!

## Expose the Application to the Wider Internet

If you want your application to be accessible from the internet, you can either
forward the necessary ports in your router, or use a tool like
[ngrok](https://ngrok.com/) that will expose your local host to the internet.

You can read [this blog post](https://www.twilio.com/blog/2015/09/6-awesome-reasons-to-use-ngrok-when-testing-webhooks.html)
for more details on how to use ngrok, but if you are using version 2.x, exposing
a specific port should be easily done with the following command:

```bash
$ ngrok http 8080
```

## Run the tests
In order to test the application, you must run the database migrations.

```bash
$ ./gradlew check
```

## Meta

* No warranty expressed or implied. Software is as is. Diggity.
* [MIT License](http://www.opensource.org/licenses/mit-license.html)
* Lovingly crafted by Twilio Developer Education.

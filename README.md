# LLD Practice

This workspace contains small low-level design (LLD) exercises in Java.

## Amazon Locker Demo

The console runner prints a few scenarios: deposit, pickup, invalid code, reuse, and staff cleanup for expired packages.

Run:

```sh
./mvnw -q -DskipTests package
java -cp target/classes io.lld_practice.amazonlocker.AmazonLockerMain
```

## Connect Four Demo

Run:

```sh
./mvnw -q -DskipTests package
java -cp target/classes io.lld_practice.connectfour.entity.ConnectFourApplication
```


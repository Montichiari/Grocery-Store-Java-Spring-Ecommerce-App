# GDiSA Java EE CA (by Team 3)

This project is for NUS GDiSA Batch 60 SA4105 CA, by Team 3 (Jared Chua, Dion Yao, Thina, Hiroyo, Ma Li, Sheng Yi).

## Deployment

To run the app on your machine, you'll need the follow pre-requisites:

- Docker Desktop (or just the Docker daemon) to be installed
- MySQL to be forcefully closed (or anything that occupies port 3306, 5173 and 8080)

Then run the following

```
  docker compose up
```

## Application Stack

Frontend: React, Vite, Typescript, Mantine

Backend: Java Spring

API Documentation: [Click here](https://docs.google.com/document/d/1kjor99ttRCs_Zh_7Qs4eF56zLAih-MexsZRGz5YNFDU/edit?usp=sharing)

## Class/Method Authors

In the Java Spring app, methods are annotated with `@author` to indicate the person who worked on it. For functions/methods that aren't annotated, the group worked on it together.

The React app was worked on by Jared Chua.

## Known Issues

- The `backend` container's logs may indicate that it has failed to connect, but it should automatically restart itself after it has failed a couple of times. This is mainly due to the `db` container not fully ready to accept connections yet.
